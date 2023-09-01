package com.cy.rs.service.impl;

import com.cy.rs.entity.*;
import com.cy.rs.mapper.EmployeeMapper;
import com.cy.rs.mapper.PerformaneMapper;
import com.cy.rs.mapper.PostMapper;
import com.cy.rs.mapper.UserMapper;
import com.cy.rs.service.IEmployeeService;
import com.cy.rs.service.ex.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired(required = false)
    private EmployeeMapper employeeMapper;

    @Autowired(required = false)
    @Qualifier("performaneMapper")
    private PerformaneMapper performaneMapper;

    @Autowired(required = false)
    @Qualifier("postMapper")
    private PostMapper postMapper;

    @Override
    /**
     * 新增员工信息
     */
    public Employee insert(Employee employee) {
        //获取员工编号和日期，判断是否存在数据，为保证员工编号的一致性
        Integer number = employee.getNumber();
        String createdTime = employee.getCreatedTime();
        String post = employee.getPost();
        Employee result = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        System.out.println(result);
        //若存在则抛出错误信息
        if (result != null) {
            throw new NumberDuplicatedException("员工编号已存在");
        }
        List<Post> results = postMapper.findByPost(post);
        if (results == null){
            throw new PostNotFoundException("该岗位不存在，请输入正确的岗位名称");
        }
        Integer rows = employeeMapper.insert(employee);
        if (rows != 1) {
            throw new InsertException("新增员工信息过程中发生了未知异常");
        }
        Employee result1 = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        return result1;
    }

    @Override
    /**
     * 修改员工信息
     */
    public void update(Employee employee) {
        //修改员工数据，根据id查询改用户信息是否存在，存在则进行下一步，不存在则抛出错误信息
        Integer id = employee.getId();
        Employee result = employeeMapper.findById(id);
        String post = employee.getPost();
        if (result == null) {
            throw new EmployeeNotFoundException("员工信息不存在");
        }
        List<Post> results = postMapper.findByPost(post);
        if (results == null){
            throw new PostNotFoundException("该岗位不存在，请输入正确的岗位名称");
        }
        //修改员工信息，返回受影响的行数，判断是否符合预期，不符合则抛出错误信息
        Integer rows = employeeMapper.updateById(employee);
        if (rows != 1) {
            throw new UpdateExcetion("修改员工信息发生了未知的异常");
        }
    }

    /**
     * 删除员工信息的
     *
     * @param id id主键
     */
    @Override
    public void delete(Integer id) {
        //删除员工信息，首先根据id查询员工信息，判断员工信息是否存在，存在则进行下一步，不存在则抛出错误
        Employee result = employeeMapper.findById(id);
        if (result == null) {
            throw new EmployeeNotFoundException("员工信息不存在");
        }
        Integer rows = employeeMapper.deleteById(id);
        if (rows != 1) {
            throw new DeleteExcetion("删除员工信息时发生了未知的异常");
        }
    }

    /**
     * 查询所有员工信息，用于展示
     */
    @Override
    public List<Employee> findByCreatedTime(String createdTime) {
        List<Employee> result = employeeMapper.findByCreatedTime(createdTime);
        return result;
    }

    /**
     * 根据编号查询员工信息
     * @param name
     * @return
     */
    @Override
    public List<Employee> findByNumber(String name) {
        List<Employee> result = employeeMapper.findByNumber(name);
        if (result == null) {
            throw new EmployeeNotFoundException("员工信息不存在");
        }
        return result;
    }

    /**
     * 根据日期查询员工姓名，具有分页功能
     *
     * @param pageNum
     * @param pageSize
     * @param createdTime
     * @return
     */
    @Override
    public List<Employee> findByName(int pageNum, int pageSize, String createdTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employees = employeeMapper.findByCreatedTime1(createdTime);
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
        return pageInfo.getList();
    }
    /**
     * 查询该日期员工总数
     * @param createdTime
     * @return
     */
    @Override
    public Double findByCount(String createdTime) {
        Double number = performaneMapper.findCountNumber(createdTime);
        return number;
    }

    /**
     * 查询员工信息所有月份
     * @return
     */
    public List<String> selectByCreatedTime(){
        List<String> result = employeeMapper.selectByCreatedTime();
        return result;
    }
    /**
     * 查询员工信息所有单位
     * @return
     */
    public List<String> selectByUnit(){
        List<String> result = employeeMapper.selectByUnit();
        return result;
    }

///////////////////////////////////////////员工画像页面///////////////////////////////////////////////////////////////////////////


    //第一部分，根据日期和姓名查询员工信息
    public Employee EmployeeMessage(Integer number, String createdTime){
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        return employee;
    }
    //第二部分，查出画像特征,也就是员工拥有的优秀特征
    public FeaturesAnalyse PostFeatures(Integer number, String createdTime){
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String post = employee.getPost();
        FeaturesAnalyse featuresAnalyse = null;
        if (post.equals("终端专员")) {
            featuresAnalyse  = terminalSpecialistNew(number, createdTime);
        } else if (post.equals("市场经理")) {
            featuresAnalyse = marketingManagerNew(number, createdTime);
        } else if (post.equals("信息专员")) {
            featuresAnalyse = informationCommissionerNew(number, createdTime);
        } else if (post.equals("综合管理员")) {
            featuresAnalyse = comprehensiveAdministratorNew(number, createdTime);
        } else if (post.equals("客户专员")) {
            featuresAnalyse = accountSpecialistNew(number, createdTime);
        }
        return featuresAnalyse;
    }
    public FeaturesAnalyse PostFeaturesByName(String name, String createdTime){
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        String post = employee.getPost();
        FeaturesAnalyse featuresAnalyse = null;
        if (post.equals("终端专员")) {
            featuresAnalyse  = terminalSpecialist(name, createdTime);
        } else if (post.equals("市场经理")) {
            featuresAnalyse = marketingManager(name, createdTime);
        } else if (post.equals("信息专员")) {
            featuresAnalyse = informationCommissioner(name, createdTime);
        } else if (post.equals("综合管理员")) {
            featuresAnalyse = comprehensiveAdministrator(name, createdTime);
        } else if (post.equals("客户专员")) {
            featuresAnalyse = accountSpecialist(name, createdTime);
        }
        return featuresAnalyse;
    }
    /**
     * 获取当前员工在全部岗位的匹配系数，以及全部岗位最高最低平均匹配系数
     * @param number
     * @param createdTime
     * @return
     */
    public List<PostMessage>PostCountFactor(Integer number, String createdTime){
        List<PostMessage> list = new ArrayList<>();
        Double factor = 0.0;
        Double max = 0.0;
        Double min = 0.0;
        Double avg = 0.0;
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        Performane performane = performaneMapper.findNumberAndCreatedTime(number, createdTime);
        Double scores = performane.getScores();
        PerformaneServiceImpl performaneService = new PerformaneServiceImpl();
        //终端专员
        System.out.println(employee);
        factor = performaneService.terminalSpecialist(employee, scores);
        max = performaneMapper.findByFactorMax("终端专员", createdTime);
        min = performaneMapper.findByFactorMin("终端专员", createdTime);
        avg = performaneMapper.findByFactorAvg("终端专员", createdTime);
        PostMessage postMessage = new PostMessage();
        postMessage.setId(1);
        postMessage.setPost("终端专员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //市场经理
        factor = performaneService.marketingManager(employee, scores);
        max = performaneMapper.findByFactorMax("市场经理", createdTime);
        min = performaneMapper.findByFactorMin("市场经理", createdTime);
        avg = performaneMapper.findByFactorAvg("市场经理", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(2);
        postMessage.setPost("市场经理");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //信息专员
        factor = performaneService.informationCommissioner(employee, scores);
        max = performaneMapper.findByFactorMax("信息专员", createdTime);
        min = performaneMapper.findByFactorMin("信息专员", createdTime);
        avg = performaneMapper.findByFactorAvg("信息专员", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(3);
        postMessage.setPost("信息专员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //综合管理员
        factor = performaneService.comprehensiveAdministrator(employee, scores);
        max = performaneMapper.findByFactorMax("综合管理员", createdTime);
        min = performaneMapper.findByFactorMin("综合管理员", createdTime);
        avg = performaneMapper.findByFactorAvg("综合管理员", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(4);
        postMessage.setPost("综合管理员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //客户专员
        factor = performaneService.accountSpecialist(employee, scores);
        max = performaneMapper.findByFactorMax("客户专员", createdTime);
        min = performaneMapper.findByFactorMin("客户专员", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(5);
        postMessage.setPost("客户专员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        return list;
    }
    public List<PostMessage>PostCountFactorNew(String name, String createdTime){
        List<PostMessage> list = new ArrayList<>();
        Double factor = 0.0;
        Double max = 0.0;
        Double min = 0.0;
        Double avg = 0.0;
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        Performane performane = performaneMapper.findNameAndCreatedTime(name, createdTime);
        Double scores = performane.getScores();
        PerformaneServiceImpl performaneService = new PerformaneServiceImpl();
        //终端专员
        System.out.println(employee);
        factor = performaneService.terminalSpecialist(employee, scores);
        max = performaneMapper.findByFactorMax("终端专员", createdTime);
        min = performaneMapper.findByFactorMin("终端专员", createdTime);
        avg = performaneMapper.findByFactorAvg("终端专员", createdTime);
        PostMessage postMessage = new PostMessage();
        postMessage.setId(1);
        postMessage.setPost("终端专员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //市场经理
        factor = performaneService.marketingManager(employee, scores);
        max = performaneMapper.findByFactorMax("市场经理", createdTime);
        min = performaneMapper.findByFactorMin("市场经理", createdTime);
        avg = performaneMapper.findByFactorAvg("市场经理", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(2);
        postMessage.setPost("市场经理");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //信息专员
        factor = performaneService.informationCommissioner(employee, scores);
        max = performaneMapper.findByFactorMax("信息专员", createdTime);
        min = performaneMapper.findByFactorMin("信息专员", createdTime);
        avg = performaneMapper.findByFactorAvg("信息专员", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(3);
        postMessage.setPost("信息专员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //综合管理员
        factor = performaneService.comprehensiveAdministrator(employee, scores);
        max = performaneMapper.findByFactorMax("综合管理员", createdTime);
        min = performaneMapper.findByFactorMin("综合管理员", createdTime);
        avg = performaneMapper.findByFactorAvg("综合管理员", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(4);
        postMessage.setPost("综合管理员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        //客户专员
        factor = performaneService.accountSpecialist(employee, scores);
        max = performaneMapper.findByFactorMax("客户专员", createdTime);
        min = performaneMapper.findByFactorMin("客户专员", createdTime);
        postMessage = new PostMessage();
        postMessage.setId(5);
        postMessage.setPost("客户专员");
        postMessage.setFactor(factor);
        postMessage.setMax(max);
        postMessage.setMin(min);
        postMessage.setAvg(avg);
        list.add(postMessage);

        return list;
    }

    /**
     * 获取员工匹配系数
     */
    public Double findFactorByname(Integer number, String createdTime){
        Double result = performaneMapper.findFactorByNumber(number, createdTime);
        return result;
    }

    /**
     * 获取该员工的所有优秀标签
     */
    public List<String> EmployeeNiceFeatures(Integer number, String createdTime){
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String one = employee.getOne();
        String two = employee.getTwo();
        String three = employee.getThree();
        String four = employee.getFour();
        String five = employee.getFive();
        String six = employee.getSix();
        String seven = employee.getSeven();
        String eight = employee.getEight();
        String nine = employee.getNine();
        String ten = employee.getTen();
        String eleven = employee.getEleven();
        String twelve = employee.getTwelve();
        String thirteen = employee.getThirteen();
        String fourteen = employee.getFourteen();
        String fifteen = employee.getFifteen();
        String sixteen = employee.getSixteen();
        String seventeen = employee.getSeventeen();
        String eighteen = employee.getEighteen();
        String nineteen = employee.getNineteen();
        String twenty = employee.getTwenty();
        String twentyOne = employee.getTwentyOne();
        String twentyTwo = employee.getTwentyTwo();
        String twentyThree = employee.getTwentyThree();
        String twentyFour = employee.getTwentyFour();
        String twentyFive = employee.getTwentyFive();
        String twentySix = employee.getTwentySix();
        String twentySeven = employee.getTwentySeven();
        String twentyEight = employee.getTwentyEight();
        String twentyNine = employee.getTwentyNine();
        String thirty = employee.getThirty();
        String thirtyOne = employee.getThirtyOne();
        String thirtyTwo = employee.getThirtyTwo();
        String thirtyThree = employee.getThirtyThree();
        String thirtyFour = employee.getThirtyFour();
        String thirtyFive = employee.getThirtyFive();
        String thirtySix = employee.getThirtySix();
        String thirtySeven = employee.getThirtySeven();
        String thirtyEight = employee.getThirtyEight();
        String thirtyNine = employee.getThirtyNine();
        String forty = employee.getForty();
        String fortyOne = employee.getFortyOne();
        String fortyTwo = employee.getFortyTwo();
        String fortyThree = employee.getFortyThree();
        String fortyFour = employee.getFortyFour();
        String fortyFive = employee.getFortyFive();
        String fortySix = employee.getFortySix();
        String fortySeven = employee.getFortySeven();
        String fortyEight = employee.getFortyEight();
        String fortyNine = employee.getFortyNine();
        String fifty = employee.getFifty();
        String fiftyOne = employee.getFiftyOne();
        String fiftyTwo = employee.getFiftyTwo();
        String fiftyThree = employee.getFiftyThree();
        List<String> list=new ArrayList<>();
        if (one.equals("是")){
            employee.setOne("有体育特长");
            list.add("有体育特长");
        }

        if (two.equals("是")){
            employee.setTwo("有艺术特长");
            list.add("有艺术特长");
        }

        if (three.equals("是")){
            employee.setThree("是体育类兴趣小组成员");
            list.add("是体育类兴趣小组成员");
        }

        if (four.equals("是")){
            employee.setFour("是艺术类兴趣小组成员");
            list.add("是艺术类兴趣小组成员");
        }

        if (five.equals("很好")){
            employee.setFive("公文写作能力良好");
            list.add("公文写作能力良好");
        }else if (five.equals("较好")){
            employee.setFive("公文写作能力良好");
            list.add("公文写作能力良好");
        }

        if (six.equals("很好")){
            employee.setSix("数据分析能力良好");
            list.add("数据分析能力良好");
        }else if (six.equals("较好")){
            employee.setSix("数据分析能力良好");
            list.add("数据分析能力良好");
        }

        if (seven.equals("很好")){
            employee.setSeven("新媒体营销技术水平良好");
            list.add("新媒体营销技术水平良好");
        }else if (seven.equals("较好")){
            employee.setSeven("新媒体营销技术水平良好");
            list.add("新媒体营销技术水平良好");
        }

        if (eight.equals("很好")){
            employee.setEight("业务规章制度掌握和执行能力良好");
            list.add("业务规章制度掌握和执行能力良好");
        }else if (eight.equals("较好")){
            employee.setEight("业务规章制度掌握和执行能力良好");
            list.add("业务规章制度掌握和执行能力良好");
        }

        if (nine.equals("很好")){
            employee.setNine("创新能力（意识、行为和成效）良好");
            list.add("创新能力（意识、行为和成效）良好");
        }else if (nine.equals("较好")){
            employee.setNine("创新能力（意识、行为和成效）良好");
            list.add("创新能力（意识、行为和成效）良好");
        }

        if (ten.equals("很好")){
            employee.setTen("沟通能力良好");
            list.add("沟通能力良好");
        }else if (ten.equals("较好")){
            employee.setTen("沟通能力良好");
            list.add("沟通能力良好");
        }

        if (eleven.equals("很好")){
            employee.setEleven("营销策划及执行能力良好");
            list.add("营销策划及执行能力良好");
        }else if (eleven.equals("较好")){
            employee.setEleven("营销策划及执行能力良好");
            list.add("营销策划及执行能力良好");
        }

        if (twelve.equals("很好")){
            employee.setTwelve("团队意识及协作能力良好");
            list.add("团队意识及协作能力良好");
        }else if (twelve.equals("较好")){
            employee.setTwelve("团队意识及协作能力良好");
            list.add("团队意识及协作能力良好");
        }

        if (thirteen.equals("是")){
            employee.setThirteen("是中级或以上经济师");
            list.add("是中级或以上经济师");
        }

        if (fourteen.equals("是")){
            employee.setFourteen("是初级经济师");
            list.add("是初级经济师");
        }

        if (fifteen.equals("是")){
            employee.setFifteen("是二级烟草制品购销职业资格（最高级别）");
            list.add("是二级烟草制品购销职业资格（最高级别）");
        }

        if (sixteen.equals("是")){
            employee.setSixteen("是三级烟草制品购销职业资格（最高级别）");
            list.add("是三级烟草制品购销职业资格（最高级别）");
        }

        if (seventeen.equals("是")){
            employee.setSeventeen("是四级烟草制品购销职业资格（最高级别）");
            list.add("是四级烟草制品购销职业资格（最高级别）");
        }

        if (eighteen.equals("是")){
            employee.setEighteen("是五级烟草制品购销职业资格（最高级别）");
            list.add("是五级烟草制品购销职业资格（最高级别）");
        }

        if (nineteen.equals("是")){
            employee.setNineteen("是具有中级以上计算机方面的资格证书");
            list.add("是具有中级以上计算机方面的资格证书");

        }

        if (twenty.equals("是")){
            employee.setTwenty("是有参加市局组织的新媒体培训经历");
            list.add("是有参加市局组织的新媒体培训经历");

        }

        if (twentyOne.equals("是")){
            employee.setTwentyOne("是有参加市局组织的数据分析培训经历");
            list.add("是有参加市局组织的数据分析培训经历");
        }

        if (twentyTwo.equals("是")){
            employee.setTwentyTwo("是有市局轮训经历");
            list.add("是有市局轮训经历");
        }

        if (twentyThree.equals("是")){
            employee.setTwentyThree("是有省局轮训经历");
            list.add("是有省局轮训经历");
        }

        if (twentyFour.equals("是")){
            employee.setTwentyFour("是省级内训师");
            list.add("是省级内训师");
        }

        if (twentyFive.equals("是")){
            employee.setTwentyFive("是市级或县级内训师");
            list.add("是市级或县级内训师");

        }

        if (twentySix.equals("是")){
            employee.setTwentySix("近两年有参与线下授课经历");
            list.add("近两年有参与线下授课经历");
        }

        if (twentySeven.equals("是")){
            employee.setTwentySeven("近两年有参与内训师相关竞赛的经历");
            list.add("近两年有参与内训师相关竞赛的经历");
        }

        if (twentyEight.equals("是")){
            employee.setTwentyEight("近两年有参加视频课程开发经历");
            list.add("近两年有参加视频课程开发经历");
        }

        if (twentyNine.equals("是")){
            employee.setTwentyNine("是数据分析团队成员");
            list.add("是数据分析团队成员");
        }

        if (thirty.equals("是")){
            employee.setThirty("是新媒体营销团队成员");
            list.add("是新媒体营销团队成员");
        }

        if (thirtyOne.equals("是")){
            employee.setThirtyOne("是省局劳模工作室成员");
            list.add("是省局劳模工作室成员");
        }

        if (thirtyTwo.equals("是")){
            employee.setThirtyTwo("是省局创客工作室成员");
            list.add("是省局创客工作室成员");
        }

        if (thirtyThree.equals("是")){
            employee.setThirtyThree("是有参加省局专项工作的经历");
            list.add("是有参加省局专项工作的经历");
        }

        if (thirtyFour.equals("会说")){
            employee.setThirtyFour("当地主要使用的方言掌握情况良好");
            list.add("当地主要使用的方言掌握情况良好");
        }

        if (thirtyFive.equals("比较标准")){
            employee.setThirtyFive("普通话标准情况良好");
            list.add("普通话标准情况良好");
        }

        if (thirtySix.equals("会说")){
            employee.setThirtySix("粤语掌握情况良好");
            list.add("粤语掌握情况良好");
        }

        if (thirtySeven.equals("是")){
            employee.setThirtySeven("参加工作前已定居广东");
            list.add("参加工作前已定居广东");
        }

        if (thirtyEight.equals("是")){
            employee.setThirtyEight("参加工作前已定居韶关");
            list.add("参加工作前已定居韶关");
        }

        if (thirtyNine.equals("是")){
            employee.setThirtyNine("在工作地（区/县/市）定居");
            list.add("在工作地（区/县/市）定居");
        }

        if (forty.equals("是")){
            employee.setForty("有任职营销以外岗位的工作经历");
            list.add("有任职营销以外岗位的工作经历");
        }

        if (fortyOne.equals("是")){
            employee.setFortyOne("有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）");
            list.add("有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）");
        }

        if (fortyTwo.equals("是")){
            employee.setFortyTwo("已婚");
            list.add("已婚");
        }

        if (fortyThree.equals("三孩及以上")){
            employee.setFortyThree("生育情况良好");
            list.add("生育情况良好");
        }else if (fortyThree.equals("二孩")){
            employee.setFortyThree("生育情况良好");
            list.add("生育情况良好");
        }

        if (fortyFour.equals("是")){
            employee.setFortyFour("有QC项目获奖情况");
            list.add("有QC项目获奖情况");
        }

        if (fortyFive.equals("是")){
            employee.setFortyFive("有论文发表或获奖情况");
            list.add("有论文发表或获奖情况");
        }

        if (fortySix.equals("是")){
            employee.setFortySix("有文章在省局以上媒体发表情况");
            list.add("有文章在省局以上媒体发表情况");
        }

        if (fortySeven.equals("是")){
            employee.setFortySeven("有参与数字化转型项目情况");
            list.add("有参与数字化转型项目情况");
        }

        if (fortyEight.equals("是")){
            employee.setFortyEight("有作为主要成员参加营销创新项目的经历");
            list.add("有作为主要成员参加营销创新项目的经历");
        }

        if (fortyNine.equals("是")){
            employee.setFortyNine("有参与的视频项目并在省局以上媒体发表情况");
            list.add("有参与的视频项目并在省局以上媒体发表情况");
        }

        if (fifty.equals("是")){
            employee.setFifty("有参与市局营销竞赛并获奖的情况");
            list.add("有参与市局营销竞赛并获奖的情况");
        }

        if (fiftyOne.equals("是")){
            employee.setFiftyOne("受到国家局（总公司）表彰");
            list.add("受到国家局（总公司）表彰");
        }

        if (fiftyTwo.equals("是")){
            employee.setFiftyTwo("受到省局（公司）表彰");
            list.add("受到省局（公司）表彰");
        }

        if (fiftyThree.equals("是")){
            employee.setFiftyThree("受到市局（公司）表彰");
            list.add("受到市局（公司）表彰");
        }
        employee.setId(null);
        employee.setNumber(null);
        employee.setName(null);
        employee.setUnit(null);
        employee.setPost(null);
        employee.setSex(null);
        employee.setStatus(null);
        employee.setDegree(null);
        employee.setAge(null);
        employee.setSeniority(null);
        return list;

    }

/////////////////////////////////////以上员工画像页面完成///////////////////////////////////////////////////////////////

    /**
     * 人岗匹配分析，查询该员工对该岗位优秀标签的拥有情况
     * @param name
     * @param createdTime
     * @param post
     * @return
     */
    public FeaturesAnalyse findByNameAndCreatedTime3(String name, String createdTime,String post){
        FeaturesAnalyse featuresAnalyse = null;
        if (post.equals("终端专员")) {
             featuresAnalyse = terminalSpecialist(name, createdTime);
        } else if (post.equals("市场经理")) {
             featuresAnalyse = marketingManager(name, createdTime);
        } else if (post.equals("信息专员")) {
             featuresAnalyse = informationCommissioner(name, createdTime);
        } else if (post.equals("综合管理员")) {
             featuresAnalyse = comprehensiveAdministrator(name, createdTime);
        } else if (post.equals("客户专员")) {
             featuresAnalyse = accountSpecialist(name, createdTime);
        }
        return featuresAnalyse;
    }
    /**
     * 人岗匹配分析，查询该员工对该岗位的匹配系数，以及该岗位的最高最低平均匹配系数
     * @param name
     * @param createdTime
     * @param post
     * @return
     */
    public Map<String,List<Double>> EmployeeAndPostMatching(String name, String createdTime,String post){
        Map<String,List<Double>> map = new HashMap<>();
        List<Double> list = new ArrayList<>();
        Double factor = 0.0;
        Double max = 0.0;
        Double min = 0.0;
        Double avg = 0.0;
        //获取员工信息，用于计算匹配系数
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        //获取员工绩效信息，为了得到绩效
        Performane performane = performaneMapper.findNameAndCreatedTime(name, createdTime);
        Double scores = performane.getScores();
        PerformaneServiceImpl performaneService = new PerformaneServiceImpl();
        //获取员工对该岗位的匹配系数
        if        (post.equals("终端专员")) {
            factor = performaneService.terminalSpecialist(employee, scores);
        } else if (post.equals("市场经理")) {
            factor = performaneService.marketingManager(employee, scores);
        } else if (post.equals("信息专员")) {
            factor = performaneService.informationCommissioner(employee, scores);
        } else if (post.equals("综合管理员")) {
            factor = performaneService.comprehensiveAdministrator(employee, scores);
        } else if (post.equals("客户专员")) {
            factor = performaneService.accountSpecialist(employee, scores);
        }
        max = performaneMapper.findByFactorMax(post, createdTime);
        min = performaneMapper.findByFactorMin(post, createdTime);
        avg = performaneMapper.findByFactorAvg(post, createdTime);
        list.add(factor);
        list.add(max);
        list.add(min);
        list.add(avg);
        map.put(post,list);
        return map;
    }
    public List<Map<String,String>> EmployeeAndPostMatchingNew(String name, String createdTime,String post){
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,Map<String,String>> map = null;
        Double factor = 0.0;
        Double max = 0.0;
        Double min = 0.0;
        Double avg = 0.0;
        //获取员工信息，用于计算匹配系数
        List<Employee> employee = employeeMapper.findByNameAndCreatedTimeNew(name, createdTime);
        for (Employee employee1 : employee) {
            map = new HashMap<>();
            Map<String,String> map1=new HashMap<>();
            Integer number = employee1.getNumber();
            Performane performane = performaneMapper.findNumberAndCreatedTime(number, createdTime);
            Double scores = performane.getScores();
            if (scores==null){
                throw new ScoresNotFoundException("员工该月绩效信息不存在");
            }
            PerformaneServiceImpl performaneService = new PerformaneServiceImpl();
            //获取员工对该岗位的匹配系数
            if        (post.equals("终端专员")) {
                factor = performaneService.terminalSpecialist(employee1, scores);
            } else if (post.equals("市场经理")) {
                factor = performaneService.marketingManager(employee1, scores);
            } else if (post.equals("信息专员")) {
                factor = performaneService.informationCommissioner(employee1, scores);
            } else if (post.equals("综合管理员")) {
                factor = performaneService.comprehensiveAdministrator(employee1, scores);
            } else if (post.equals("客户专员")) {
                factor = performaneService.accountSpecialist(employee1, scores);
            }
            max = performaneMapper.findByFactorMax(post, createdTime);
            min = performaneMapper.findByFactorMin(post, createdTime);
            avg = performaneMapper.findByFactorAvg(post, createdTime);
            map1.put("post",post);
            map1.put("number", String.valueOf(number));
            map1.put("factor", String.valueOf(factor));
            map1.put("max", String.valueOf(max));
            map1.put("min", String.valueOf(min));
            map1.put("avg", String.valueOf(avg));
            list.add(map1);
            map.put(name,map1);
        }
        return list;
    }

    /**
     * 岗位画像页面
     * 传入日期和岗位，根据岗位罗列出全部优秀特征，挑选出除基础标签和绩效，然后排序，
     *     返回前五，返回标签名称和占比，拥有标签的人人数占比和为拥有优秀标签的人数占比
     *     数据存储List<FeaturesMessage>
     */
    public List<FeaturesMessage> findByPostAndCreatedTime5(String createdTime,String post){
        List<FeaturesMessage> list = new ArrayList<>();
        if (post.equals("终端专员")) {
            list = terminalSpecialistByPost(createdTime);
        } else if (post.equals("市场经理")) {
            list = marketingManagerByPost( createdTime);
        } else if (post.equals("信息专员")) {
            list = informationCommissionerByPost( createdTime);
        } else if (post.equals("综合管理员")) {
            list = comprehensiveAdministratorByPost( createdTime);
        } else if (post.equals("客户专员")) {
            list = accountSpecialistByPost(createdTime);
        }
        return list;
    }


    /**
     * 根据员工岗位和姓名或者员工岗位和编号查询信息.注意姓名和编号的数据类型是不一样的
     * @param post 岗位
     * @param condition 员工姓名或编号
     * @return
     */
    @Override
    public List<Employee> findByPostAndCondition(String post, Object condition) {
        List<Employee> result = null;
        System.out.println(condition);
        if (condition==null){
            result = employeeMapper.findByPost(post);
        }
        if(condition instanceof Integer){
            result = employeeMapper.findByPostAndNumber(post, (Integer) condition);
        }else if (condition instanceof String){
            result = employeeMapper.findByPostAndName(post, (String) condition);
        }
        return result;
    }

    @Override
    public List<Employee> findByPost(String post) {
        List<Employee> result = employeeMapper.findByPost(post);
        return result;
    }

    /**
     * 从excel表格中导入数据到数据库中
     * @param file
     * @return
     * @throws IOException
     */
    public Integer addToDatabase(MultipartFile file) throws IOException {
        int result = 0;
        List<Employee> employeeList = new ArrayList<>();                    // 创建billList

        String fileName = file.getOriginalFilename();                           // 获取上传的文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);    // 获取上传文件后缀

        System.out.println("文件后缀为：" + suffix);
        InputStream inputStream = file.getInputStream();            // 输入流读取文件
        Workbook workbook = null;                                   // 1. 新建工作簿

        if(suffix.equals("xlsx")){
            workbook = new XSSFWorkbook(inputStream);               // Excel2007版本及以后 用XSSF(即Poi-ooxml)处理
        } else {
            workbook = new HSSFWorkbook(inputStream);               // Excel2003版本 用HSSF(即Poi)处理
        }

        Sheet sheet = workbook.getSheetAt(0);                     // 2. 获取当前工作表

        if(sheet != null){
            for (int i = 1; i <= sheet.getLastRowNum(); i++){       // 3. 从第二行开始遍历工作表的每一行
                Row row = sheet.getRow(i);
                if (row != null){
                    List<String> list = new ArrayList<>();
                    for (Cell cell : row){                          // 4. 遍历每一个单元格
                        if (cell != null) {
                            cell.setCellType(CellType.STRING);        // 将单元格值转化为字符串
                            String cellValue = cell.getStringCellValue();   // 获取字符串形式的值
                            list.add(cellValue);                            // 将值加入数组
                        }
                    }

                    // 值的转化
                    Integer number = Integer.parseInt(list.get(1));
                    String name=list.get(2);
                    String unit=list.get(3);
                    String post=list.get(4);
                    String sex=list.get(5);
                    String status=list.get(6);
                    String degree=list.get(7);
                    Integer age = Integer.parseInt(list.get(8));
                    Integer seniority = Integer.parseInt(list.get(9));
                     String one=list.get(10);
                     String two=list.get(11);
                     String three=list.get(12);
                     String four=list.get(13);
                     String five=list.get(14);
                     String six=list.get(15);
                     String seven=list.get(16);
                     String eight=list.get(17);
                     String nine=list.get(18);
                     String ten=list.get(19);
                     String eleven=list.get(20);
                     String twelve=list.get(21);
                     String thirteen=list.get(22);
                     String fourteen=list.get(23);
                     String fifteen=list.get(24);
                     String sixteen=list.get(25);
                     String seventeen=list.get(26);
                     String eighteen=list.get(27);
                     String nineteen=list.get(28);
                     String twenty=list.get(29);
                     String twentyOne=list.get(30);
                     String twentyTwo=list.get(31);
                     String twentyThree=list.get(32);
                     String twentyFour=list.get(33);
                     String twentyFive=list.get(34);
                     String twentySix=list.get(35);
                     String twentySeven=list.get(36);
                     String twentyEight=list.get(37);
                     String twentyNine=list.get(38);
                     String thirty=list.get(39);
                     String thirtyOne=list.get(40);
                     String thirtyTwo=list.get(41);
                     String thirtyThree=list.get(42);
                     String thirtyFour=list.get(43);
                     String thirtyFive=list.get(44);
                     String thirtySix=list.get(45);
                     String thirtySeven=list.get(46);
                     String thirtyEight=list.get(47);
                     String thirtyNine=list.get(48);
                     String forty=list.get(49);
                     String fortyOne=list.get(50);
                     String fortyTwo=list.get(51);
                     String fortyThree=list.get(52);
                     String fortyFour=list.get(53);
                     String fortyFive=list.get(54);
                     String fortySix=list.get(55);
                     String fortySeven=list.get(56);
                     String fortyEight=list.get(57);
                     String fortyNine=list.get(58);
                     String fifty=list.get(59);
                     String fiftyOne=list.get(60);
                     String fiftyTwo=list.get(61);
                     String fiftyThree=list.get(62);
                     String createdTime=list.get(63);
//                    System.out.println(list.get(0));
//                    System.out.println("============================");
//                    Integer number = Integer.parseInt(list.get(0));
//                    String name=list.get(1);
//                    String unit=list.get(2);
//                    String post=list.get(3);
//                    String sex=list.get(4);
//                    String status=list.get(5);
//                    String degree=list.get(6);
//                    Integer age = Integer.parseInt(list.get(7));
//                    Integer seniority = Integer.parseInt(list.get(8));
//                    String one=list.get(9);
//                    String two=list.get(10);
//                    String three=list.get(11);
//                    String four=list.get(12);
//                    String five=list.get(13);
//                    String six=list.get(14);
//                    String seven=list.get(15);
//                    String eight=list.get(16);
//                    String nine=list.get(17);
//                    String ten=list.get(18);
//                    String eleven=list.get(19);
//                    String twelve=list.get(20);
//                    String thirteen=list.get(21);
//                    String fourteen=list.get(22);
//                    String fifteen=list.get(23);
//                    String sixteen=list.get(24);
//                    String seventeen=list.get(25);
//                    String eighteen=list.get(26);
//                    String nineteen=list.get(27);
//                    String twenty=list.get(28);
//                    String twentyOne=list.get(29);
//                    String twentyTwo=list.get(30);
//                    String twentyThree=list.get(31);
//                    String twentyFour=list.get(32);
//                    String twentyFive=list.get(33);
//                    String twentySix=list.get(34);
//                    String twentySeven=list.get(35);
//                    String twentyEight=list.get(36);
//                    String twentyNine=list.get(37);
//                    String thirty=list.get(38);
//                    String thirtyOne=list.get(39);
//                    String thirtyTwo=list.get(40);
//                    String thirtyThree=list.get(41);
//                    String thirtyFour=list.get(42);
//                    String thirtyFive=list.get(43);
//                    String thirtySix=list.get(44);
//                    String thirtySeven=list.get(45);
//                    String thirtyEight=list.get(46);
//                    String thirtyNine=list.get(47);
//                    String forty=list.get(48);
//                    String fortyOne=list.get(49);
//                    String fortyTwo=list.get(50);
//                    String fortyThree=list.get(51);
//                    String fortyFour=list.get(52);
//                    String fortyFive=list.get(53);
//                    String fortySix=list.get(54);
//                    String fortySeven=list.get(55);
//                    String fortyEight=list.get(56);
//                    String fortyNine=list.get(57);
//                    String fifty=list.get(58);
//                    String fiftyOne=list.get(59);
//                    String fiftyTwo=list.get(60);
//                    String fiftyThree=list.get(61);
//                    String createdTime=list.get(62);

                    // 构造一个账单对象，并将从个单元格获取的值赋给它
                    Employee employee = new Employee(number,name,unit,post,sex,status,degree,age,seniority,one,
                            two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,
                            thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,
                            twenty,twentyOne,twentyTwo,twentyThree,twentyFour,twentyFive,
                            twentySix,twentySeven,twentyEight,twentyNine,thirty,thirtyOne,
                            thirtyTwo,thirtyThree,thirtyFour,thirtyFive,thirtySix,thirtySeven,
                            thirtyEight,thirtyNine,forty,fortyOne,fortyTwo,fortyThree,fortyFour,
                            fortyFive,fortySix,fortySeven,fortyEight,fortyNine,fifty,fiftyOne,
                            fiftyTwo,fiftyThree,createdTime);
                    employeeList.add(employee);                                 // 将新的一条账单加入billList
                }
            }

            for (Employee employees: employeeList){
                result = employeeMapper.addBillExcelFileToDatabase(employees);      // 将每一条账单插入数据库
            }
        }
        return result;
    }

    /**
     * 查询员工信息表中的所有数据，导出excel表格
     */
    public List<Employee> select(){
        List<Employee> employees = employeeMapper.select();
        return employees;
    }
    //////////////////////////人岗匹配页面///////////////////////////////////////////////////////////////
    public FeaturesAnalyse PostFeaturesByNumberAndPost(Integer number, String createdTime,String post){
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        FeaturesAnalyse featuresAnalyse = null;
        if (post.equals("终端专员")) {
            featuresAnalyse  = terminalSpecialistNew(number, createdTime);
        } else if (post.equals("市场经理")) {
            featuresAnalyse = marketingManagerNew(number, createdTime);
        } else if (post.equals("信息专员")) {
            featuresAnalyse = informationCommissionerNew(number, createdTime);
        } else if (post.equals("综合管理员")) {
            featuresAnalyse = comprehensiveAdministratorNew(number, createdTime);
        } else if (post.equals("客户专员")) {
            featuresAnalyse = accountSpecialistNew(number, createdTime);
        }
        return featuresAnalyse;
    }

    ///////////////////////////数据分析////////////////////////////////////////////////////
    public Map<String,Map<String,Map<String,Double>>> cockpit(String post,String createdTime){
        Map<String,Map<String,Map<String,Double>>> map = new HashMap<>();
        if (post.equals("终端专员")){
             map = terminalSpecialistFeaturesMessage(post, createdTime);
        }else if (post.equals("市场经理")){
            map = marketingManagerFeaturesMessage(post, createdTime);
        }else if (post.equals("信息专员")){
            map = informationCommissionerFeaturesMessage(post, createdTime);
        }else if (post.equals("综合管理员")){
            map = comprehensiveAdministratorFeaturesMessage(post, createdTime);
        }else if (post.equals("客户专员")){
            map = accountSpecialistFeaturesMessage(post, createdTime);
        }
        return map;
    }
    ///////////////////////////计算岗位前五优秀标签的占比以及员工拥有占比///////////////////////////////////////


//    public Map<String, List<Double>> terminalSpecialistByPost1(String createdTime){
//        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
//        Map<String,List<Double>> map = new HashMap<>();
//        List<Double> list = new ArrayList<>();
//        Double thirtySixYes=0.0;
//        Double thirtySixNo = 0.0;
//        Double thirtyYes=0.0;
//        Double thirtyNo = 0.0;
//        Double oneYes=0.0;
//        Double oneNo = 0.0;
//        Double fortyFiveYes=0.0;
//        Double fortyFiveNo = 0.0;
//        Double nineTeenYes=0.0;
//        Double nineTeenNo = 0.0;
//        for (Employee employee : employees) {
//            String thirtySix = employee.getThirtySix();
//            String thirty = employee.getThirty();
//            String one = employee.getOne();
//            String fortyFive = employee.getFortyFive();
//            String nineTeen = employee.getNineteen();
//
//            if (thirtySix.equals("会说")){
//                thirtySixYes+=1;
//            }else if (thirtySix.equals("能听不会说")){
//                thirtySixNo+=1;
//            }else {
//                thirtySixNo+=1;
//            }
//
//            if (thirty.equals("是")){
//                thirtyYes+=1;
//            }else{
//                thirtyNo+=1;
//            }
//
//            if (one.equals("是")){
//                oneYes+=1;
//            }else{
//                oneNo+=1;
//            }
//
//            if (fortyFive.equals("是")){
//                fortyFiveYes+=1;
//            }else{
//                fortyFiveNo+=1;
//            }
//
//            if (nineTeen.equals("是")){
//                nineTeenYes+=1;
//            }else{
//                nineTeenNo+=1;
//            }
//        }
//        Double thirtySixYesPossess = thirtySixYes/(thirtySixYes+thirtySixNo);
//        Double thirtySixNoPossess = thirtySixNo/(thirtySixYes+thirtySixNo);
//        Double thirtyYesPossess = thirtyYes/(thirtyYes+thirtyNo);
//        Double thirtyNoPossess = thirtyNo/(thirtyYes+thirtyNo);
//        Double oneYesPossess = oneYes/(oneYes+oneNo);
//        Double oneNoPossess = oneNo/(oneYes+oneNo);
//        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
//        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
//        Double nineTeenYesPossess = nineTeenYes/(nineTeenYes+nineTeenNo);
//        Double nineTeenNoPossess = nineTeenNo/(nineTeenYes+nineTeenNo);
//        Double thirtySixWeight = 0.16;
//        Double thirtyWeight = 0.12;
//        Double oneWeight = 0.11;
//        Double fortyFiveWeight = 0.11;
//        Double nineTeenWeight = 0.08;
//
//        list.add(thirtySixWeight);
//        list.add(thirtySixYesPossess);
//        list.add(thirtySixNoPossess);
//        map.put("粤语掌握情况",list);
//        list.clear();
//
//        list.add(thirtyWeight);
//        list.add(thirtyYesPossess);
//        list.add(thirtyNoPossess);
//        map.put("是否新媒体营销团队成员",list);
//        list.clear();
//
//        list.add(oneWeight);
//        list.add(oneYesPossess);
//        list.add(oneNoPossess);
//        map.put("是否有体育特长",list);
//        list.clear();
//
//        list.add(fortyFiveWeight);
//        list.add(fortyFiveYesPossess);
//        list.add(fortyFiveNoPossess);
//        map.put("是否有论文发表或获奖情况",list);
//        list.clear();
//
//        list.add(nineTeenWeight);
//        list.add(nineTeenYesPossess);
//        list.add(nineTeenNoPossess);
//        map.put("是否具有中级以上计算机方面的资格证书",list);
//        list.clear();
//
//        return map;
//    }


    /**
     * 终端专员,传入员工日期，算出该岗位前五的优秀标签以及员工拥有占比
     * 例如终端专员优秀标签前五为
     * 1.粤语掌握情况 0.16 会说、能听不会说、基本听不懂  36 thirty_six
     * 2.是否新媒体营销团队成员 0.12 是否  30 thirty
     * 3.是否有体育特长 0.11 是否  1 one
     * 4.是否有论文发表或获奖情况 0.11 是否  45 forty_five
     * 5.是否具有中级以上计算机方面的资格证书 0.08 是否   19 nineteen
     * @param createdTime
     * @return
     */
    public List<FeaturesMessage> terminalSpecialistByPost(String createdTime){
        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
        List<FeaturesMessage> list = new ArrayList<>();
        Double thirtySixYes=0.0;
        Double thirtySixNo = 0.0;
        Double thirtyYes=0.0;
        Double thirtyNo = 0.0;
        Double oneYes=0.0;
        Double oneNo = 0.0;
        Double fortyFiveYes=0.0;
        Double fortyFiveNo = 0.0;
        Double nineTeenYes=0.0;
        Double nineTeenNo = 0.0;
        for (Employee employee : employees) {
            String thirtySix = employee.getThirtySix();
            String thirty = employee.getThirty();
            String one = employee.getOne();
            String fortyFive = employee.getFortyFive();
            String nineTeen = employee.getNineteen();

            if (thirtySix.equals("会说")){
                thirtySixYes+=1;
            }else if (thirtySix.equals("能听不会说")){
                thirtySixNo+=1;
            }else {
                thirtySixNo+=1;
            }

            if (thirty.equals("是")){
                thirtyYes+=1;
            }else{
                thirtyNo+=1;
            }

            if (one.equals("是")){
                oneYes+=1;
            }else{
                oneNo+=1;
            }

            if (fortyFive.equals("是")){
                fortyFiveYes+=1;
            }else{
                fortyFiveNo+=1;
            }

            if (nineTeen.equals("是")){
                nineTeenYes+=1;
            }else{
                nineTeenNo+=1;
            }
        }
        Double thirtySixYesPossess = thirtySixYes/(thirtySixYes+thirtySixNo);
        Double thirtySixNoPossess = thirtySixNo/(thirtySixYes+thirtySixNo);
        Double thirtyYesPossess = thirtyYes/(thirtyYes+thirtyNo);
        Double thirtyNoPossess = thirtyNo/(thirtyYes+thirtyNo);
        Double oneYesPossess = oneYes/(oneYes+oneNo);
        Double oneNoPossess = oneNo/(oneYes+oneNo);
        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
        Double nineTeenYesPossess = nineTeenYes/(nineTeenYes+nineTeenNo);
        Double nineTeenNoPossess = nineTeenNo/(nineTeenYes+nineTeenNo);
        Double thirtySixWeight = 0.16;
        Double thirtyWeight = 0.12;
        Double oneWeight = 0.11;
        Double fortyFiveWeight = 0.11;
        Double nineTeenWeight = 0.08;

        FeaturesMessage featuresMessage = new FeaturesMessage();
        featuresMessage.setId(1);
        featuresMessage.setFeatures("粤语掌握情况");
        featuresMessage.setWeight(thirtySixWeight);
        featuresMessage.setNiceFeatures("粤语掌握情况良好");
        featuresMessage.setNiceNumber(thirtySixYesPossess);
        featuresMessage.setBadFeatures("粤语掌握情况一般");
        featuresMessage.setBadNumber(thirtySixNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(2);
        featuresMessage.setFeatures("是否新媒体营销团队成员");
        featuresMessage.setWeight(thirtyWeight);
        featuresMessage.setNiceFeatures("是新媒体营销团队成员");
        featuresMessage.setNiceNumber(thirtyYesPossess);
        featuresMessage.setBadFeatures("不是新媒体营销团队成员");
        featuresMessage.setBadNumber(thirtyNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(3);
        featuresMessage.setFeatures("是否有体育特长");
        featuresMessage.setWeight(oneWeight);
        featuresMessage.setNiceFeatures("有体育特长");
        featuresMessage.setNiceNumber(oneYesPossess);
        featuresMessage.setBadFeatures("没有体育特长");
        featuresMessage.setBadNumber(oneNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(4);
        featuresMessage.setFeatures("是否有论文发表或获奖情况");
        featuresMessage.setWeight(fortyFiveWeight);
        featuresMessage.setNiceFeatures("有论文发表或获奖情况");
        featuresMessage.setNiceNumber(fortyFiveYesPossess);
        featuresMessage.setBadFeatures("没有论文发表或获奖情况");
        featuresMessage.setBadNumber(fortyFiveNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(5);
        featuresMessage.setFeatures("是否具有中级以上计算机方面的资格证书");
        featuresMessage.setWeight(nineTeenWeight);
        featuresMessage.setNiceFeatures("具有中级以上计算机方面的资格证书");
        featuresMessage.setNiceNumber(nineTeenYesPossess);
        featuresMessage.setBadFeatures("不具有中级以上计算机方面的资格证书");
        featuresMessage.setBadNumber(nineTeenNoPossess);
        list.add(featuresMessage);
        return list;
    }

//    public List<FeaturesMessage> marketingManagerByPost1(String createdTime){
//        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
//        Map<String,List<Double>> map = new HashMap<>();
//        List<Double> list = new ArrayList<>();
//        Double thirteenYes=0.0;
//        Double thirteenNo = 0.0;
//        Double eighteenYes=0.0;
//        Double eighteenNo = 0.0;
//        Double fortyFiveYes=0.0;
//        Double fortyFiveNo = 0.0;
//        Double fortyFourYes=0.0;
//        Double fortyFourNo = 0.0;
//        Double fortyTwoYes=0.0;
//        Double fortyTwoNo = 0.0;
//        for (Employee employee : employees) {
//            String thirteen = employee.getThirteen();
//            String eighteen = employee.getEighteen();
//            String fortyFive = employee.getFortyFive();
//            String fortyFour = employee.getFortyFour();
//            String fortyTwo = employee.getFortyTwo();
//
//            if (thirteen.equals("是")){
//                thirteenYes+=1;
//            }else{
//                thirteenNo+=1;
//            }
//
//            if (eighteen.equals("是")){
//                eighteenYes+=1;
//            }else{
//                eighteenNo+=1;
//            }
//
//            if (fortyFive.equals("是")){
//                fortyFiveYes+=1;
//            }else{
//                fortyFiveNo+=1;
//            }
//
//            if (fortyFour.equals("是")){
//                fortyFourYes+=1;
//            }else{
//                fortyFourNo+=1;
//            }
//
//            if (fortyTwo.equals("是")){
//                fortyTwoYes+=1;
//            }else{
//                fortyTwoNo+=1;
//            }
//        }
//        Double thirteenYesPossess = thirteenYes/(thirteenYes+thirteenNo);
//        Double thirteenNoPossess = thirteenNo/(thirteenYes+thirteenNo);
//        Double eighteenYesPossess = eighteenYes/(eighteenYes+eighteenNo);
//        Double eighteenNoPossess = eighteenNo/(eighteenYes+eighteenNo);
//        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
//        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
//        Double fortyFourYesPossess = fortyFourYes/(fortyFourYes+fortyFourNo);
//        Double fortyFourNoPossess = fortyFourNo/(fortyFourYes+fortyFourNo);
//        Double fortyTwoYesPossess = fortyTwoYes/(fortyTwoYes+fortyTwoNo);
//        Double fortyTwoNoPossess = fortyTwoNo/(fortyTwoYes+fortyTwoNo);
//        Double thirteenWeight = 0.26;
//        Double eighteenWeight = 0.25;
//        Double fortyFiveWeight = 0.10;
//        Double fortyFourWeight = 0.10;
//        Double fortyTwoWeight = 0.10;
//
//        list.add(thirteenWeight);
//        list.add(thirteenYesPossess);
//        list.add(thirteenNoPossess);
//        map.put("是否中级或以上经济师",list);
//        list.clear();
//
//        list.add(eighteenWeight);
//        list.add(eighteenYesPossess);
//        list.add(eighteenNoPossess);
//        map.put("是否五级烟草制品购销职业资格（最高级别）",list);
//        list.clear();
//
//        list.add(fortyFiveWeight);
//        list.add(fortyFiveYesPossess);
//        list.add(fortyFiveNoPossess);
//        map.put("是否有体育特长",list);
//        list.clear();
//
//        list.add(fortyFourWeight);
//        list.add(fortyFourYesPossess);
//        list.add(fortyFourNoPossess);
//        map.put("是否有QC项目获奖情况",list);
//        list.clear();
//
//        list.add(fortyTwoWeight);
//        list.add(fortyTwoYesPossess);
//        list.add(fortyTwoNoPossess);
//        map.put("是否已婚",list);
//        list.clear();
//        return map;
//    }

    /**
     * 市场经理,传入员工日期，算出该岗位前五的优秀标签以及员工拥有占比
     * 例如市场经理优秀标签前五为
     * thirteen    0.26  是否中级或以上经济师  是(0.26)  否(0.20)
     * eighteen    0.25  是否五级烟草制品购销职业资格（最高级别）  是(0.20)  否(0.15)
     * fortyfive   0.10  是否有论文发表或获奖情况   是(0.10)  否(0.06)
     * fortyfour   0.10  是否有QC项目获奖情况    是(0.10)  否(0.06)
     * fortytwo    0.10  是否已婚   是(0.10)  否(0.06)
     * @param createdTime
     * @return
     */
    public List<FeaturesMessage> marketingManagerByPost(String createdTime){
        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
        List<FeaturesMessage> list = new ArrayList<>();
        Double thirteenYes=0.0;
        Double thirteenNo = 0.0;
        Double eighteenYes=0.0;
        Double eighteenNo = 0.0;
        Double fortyFiveYes=0.0;
        Double fortyFiveNo = 0.0;
        Double fortyFourYes=0.0;
        Double fortyFourNo = 0.0;
        Double fortyTwoYes=0.0;
        Double fortyTwoNo = 0.0;
        for (Employee employee : employees) {
            String thirteen = employee.getThirteen();
            String eighteen = employee.getEighteen();
            String fortyFive = employee.getFortyFive();
            String fortyFour = employee.getFortyFour();
            String fortyTwo = employee.getFortyTwo();

            if (thirteen.equals("是")){
                thirteenYes+=1;
            }else{
                thirteenNo+=1;
            }

            if (eighteen.equals("是")){
                eighteenYes+=1;
            }else{
                eighteenNo+=1;
            }

            if (fortyFive.equals("是")){
                fortyFiveYes+=1;
            }else{
                fortyFiveNo+=1;
            }

            if (fortyFour.equals("是")){
                fortyFourYes+=1;
            }else{
                fortyFourNo+=1;
            }

            if (fortyTwo.equals("是")){
                fortyTwoYes+=1;
            }else{
                fortyTwoNo+=1;
            }
        }
        Double thirteenYesPossess = thirteenYes/(thirteenYes+thirteenNo);
        Double thirteenNoPossess = thirteenNo/(thirteenYes+thirteenNo);
        Double eighteenYesPossess = eighteenYes/(eighteenYes+eighteenNo);
        Double eighteenNoPossess = eighteenNo/(eighteenYes+eighteenNo);
        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
        Double fortyFourYesPossess = fortyFourYes/(fortyFourYes+fortyFourNo);
        Double fortyFourNoPossess = fortyFourNo/(fortyFourYes+fortyFourNo);
        Double fortyTwoYesPossess = fortyTwoYes/(fortyTwoYes+fortyTwoNo);
        Double fortyTwoNoPossess = fortyTwoNo/(fortyTwoYes+fortyTwoNo);
        Double thirteenWeight = 0.26;
        Double eighteenWeight = 0.25;
        Double fortyFiveWeight = 0.10;
        Double fortyFourWeight = 0.10;
        Double fortyTwoWeight = 0.10;

        FeaturesMessage featuresMessage = new FeaturesMessage();
        featuresMessage.setId(1);
        featuresMessage.setFeatures("是否中级或以上经济师");
        featuresMessage.setWeight(thirteenWeight);
        featuresMessage.setNiceFeatures("是中级或以上经济师");
        featuresMessage.setNiceNumber(thirteenYesPossess);
        featuresMessage.setBadFeatures("不是是中级或以上经济师");
        featuresMessage.setBadNumber(thirteenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(2);
        featuresMessage.setFeatures("是否五级烟草制品购销职业资格(最高级别)");
        featuresMessage.setWeight(eighteenWeight);
        featuresMessage.setNiceFeatures("是五级烟草制品购销职业资格(最高级别)");
        featuresMessage.setNiceNumber(eighteenYesPossess);
        featuresMessage.setBadFeatures("不是五级烟草制品购销职业资格(最高级别)");
        featuresMessage.setBadNumber(eighteenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(3);
        featuresMessage.setFeatures("是否有论文发表或获奖情况");
        featuresMessage.setWeight(fortyFiveWeight);
        featuresMessage.setNiceFeatures("有论文发表或获奖情况");
        featuresMessage.setNiceNumber(fortyFiveYesPossess);
        featuresMessage.setBadFeatures("没有论文发表或获奖情况");
        featuresMessage.setBadNumber(fortyFiveNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(4);
        featuresMessage.setFeatures("是否有OC项目获奖情况");
        featuresMessage.setWeight(fortyFourWeight);
        featuresMessage.setNiceFeatures("有OC项目获奖情况");
        featuresMessage.setNiceNumber(fortyFourYesPossess);
        featuresMessage.setBadFeatures("没有OC项目获奖情况");
        featuresMessage.setBadNumber(fortyFourNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(5);
        featuresMessage.setFeatures("是否已婚");
        featuresMessage.setWeight(fortyTwoWeight);
        featuresMessage.setNiceFeatures("已婚");
        featuresMessage.setNiceNumber(fortyTwoYesPossess);
        featuresMessage.setBadFeatures("未婚");
        featuresMessage.setBadNumber(fortyTwoNoPossess);
        list.add(featuresMessage);
        return list;
    }



//    public List<FeaturesMessage> informationCommissionerByPost1(String createdTime){
//        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
//        Map<String,List<Double>> map = new HashMap<>();
//        List<Double> list = new ArrayList<>();
//        Double fortyFiveYes=0.0;
//        Double fortyFiveNo = 0.0;
//        Double twelveYes=0.0;
//        Double twelveNo = 0.0;
//        Double seventeenYes=0.0;
//        Double seventeenNo = 0.0;
//        Double twentyNineYes=0.0;
//        Double twentyNineNo = 0.0;
//        Double fourteenYes=0.0;
//        Double fourteenNo = 0.0;
//        for (Employee employee : employees) {
//            String fortyFive = employee.getFortyFive();
//            String twelve = employee.getTwelve();
//            String seventeen = employee.getSeventeen();
//            String twentyNine = employee.getTwentyNine();
//            String fourteen = employee.getFourteen();
//
//
//            if (fortyFive.equals("是")){
//                fortyFiveYes+=1;
//            }else{
//                fortyFiveNo+=1;
//            }
//
//            if (twelve.equals("很好")){
//                twelveYes+=1;
//            }else if (twelve.equals("较好")){
//                twelveYes+=1;
//            }else if (twelve.equals("一般")){
//                twelveNo+=1;
//            }else{
//                twelveNo+=1;
//            }
//
//            if (seventeen.equals("是")){
//                seventeenYes+=1;
//            }else{
//                seventeenNo+=1;
//            }
//
//            if (twentyNine.equals("是")){
//                twentyNineYes+=1;
//            }else{
//                twentyNineNo+=1;
//            }
//
//            if (fourteen.equals("是")){
//                fourteenYes+=1;
//            }else{
//                fourteenNo+=1;
//            }
//        }
//        //拥有人数占比
//        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
//        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
//        Double twelveYesPossess = twelveYes/(twelveYes+twelveNo);
//        Double twelveNoPossess = twelveNo/(twelveYes+twelveNo);
//        Double seventeenYesPossess = seventeenYes/(seventeenYes+seventeenNo);
//        Double seventeenNoPossess = seventeenNo/(seventeenYes+seventeenNo);
//        Double twentyNineYesPossess = twentyNineYes/(twentyNineYes+twentyNineNo);
//        Double twentyNineNoPossess = twentyNineNo/(twentyNineYes+twentyNineNo);
//        Double fourteenYesPossess = fourteenYes/(fourteenYes+fourteenNo);
//        Double fourteenNoPossess = fourteenNo/(fourteenYes+fourteenNo);
//        //权重赋值
//        Double fortyFiveWeight = 0.14;
//        Double twelveWeight = 0.10;
//        Double seventeenWeight = 0.08;
//        Double twentyNineWeight = 0.08;
//        Double fourteenWeight = 0.08;
//
//        list.add(fortyFiveWeight);
//        list.add(fortyFiveYesPossess);
//        list.add(fortyFiveNoPossess);
//        map.put("是否有论文发表或获奖情况",list);
//        list.clear();
//
//        list.add(twelveWeight);
//        list.add(twelveYesPossess);
//        list.add(twelveNoPossess);
//        map.put("团队意识及协作能力",list);
//        list.clear();
//
//        list.add(seventeenWeight);
//        list.add(seventeenYesPossess);
//        list.add(seventeenNoPossess);
//        map.put("是否四级烟草制品购销职业资格(最高级别)",list);
//        list.clear();
//
//        list.add(twentyNineWeight);
//        list.add(twentyNineYesPossess);
//        list.add(twentyNineNoPossess);
//        map.put("是否数据分析团队成员",list);
//        list.clear();
//
//        list.add(fourteenWeight);
//        list.add(fourteenYesPossess);
//        list.add(fourteenNoPossess);
//        map.put("是否初级经济师",list);
//        list.clear();
//        return map;
//    }

    /**
     * 信息专员,传入员工日期，算出该岗位前五的优秀标签以及员工拥有占比
     * 例如信息专员优秀标签前五为
     * fortyFive  是否有论文发表或获奖情况 0.14    是(0.14)  否(0.10)
     * twelve  团队意识及协作能力  0.10   很好较好(0.10)  一般(0.08)  较差(0.06)
     * senvenTeen 是否四级烟草制品购销职业资格（最高级别)  0.08   是(0.08)  否(0.05)
     * twentyNine  是否数据分析团队成员  0.08    是(0.08)  否(0.05)
     * fourteen  是否初级经济师  0.08    是(0.08)  否(0.05)
     * @param createdTime
     * @return
     */
    public List<FeaturesMessage> informationCommissionerByPost(String createdTime){
        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
        List<FeaturesMessage> list = new ArrayList<>();
        Double fortyFiveYes=0.0;
        Double fortyFiveNo = 0.0;
        Double twelveYes=0.0;
        Double twelveNo = 0.0;
        Double seventeenYes=0.0;
        Double seventeenNo = 0.0;
        Double twentyNineYes=0.0;
        Double twentyNineNo = 0.0;
        Double fourteenYes=0.0;
        Double fourteenNo = 0.0;
        for (Employee employee : employees) {
            String fortyFive = employee.getFortyFive();
            String twelve = employee.getTwelve();
            String seventeen = employee.getSeventeen();
            String twentyNine = employee.getTwentyNine();
            String fourteen = employee.getFourteen();


            if (fortyFive.equals("是")){
                fortyFiveYes+=1;
            }else{
                fortyFiveNo+=1;
            }

            if (twelve.equals("很好")){
                fortyFiveYes+=1;
            }else if (twelve.equals("较好")){
                fortyFiveYes+=1;
            }else if (twelve.equals("一般")){
                fortyFiveNo+=1;
            }else{
                fortyFiveNo+=1;
            }

            if (seventeen.equals("是")){
                seventeenYes+=1;
            }else{
                seventeenNo+=1;
            }

            if (twentyNine.equals("是")){
                twentyNineYes+=1;
            }else{
                twentyNineNo+=1;
            }

            if (fourteen.equals("是")){
                fourteenYes+=1;
            }else{
                fourteenNo+=1;
            }
        }
        //拥有人数占比
        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
        Double twelveYesPossess = twelveYes/(twelveYes+twelveNo);
        Double twelveNoPossess = twelveNo/(twelveYes+twelveNo);
        Double seventeenYesPossess = seventeenYes/(seventeenYes+seventeenNo);
        Double seventeenNoPossess = seventeenNo/(seventeenYes+seventeenNo);
        Double twentyNineYesPossess = twentyNineYes/(twentyNineYes+twentyNineNo);
        Double twentyNineNoPossess = twentyNineNo/(twentyNineYes+twentyNineNo);
        Double fourteenYesPossess = fourteenYes/(fourteenYes+fourteenNo);
        Double fourteenNoPossess = fourteenNo/(fourteenYes+fourteenNo);
        //权重赋值
        Double fortyFiveWeight = 0.14;
        Double twelveWeight = 0.10;
        Double seventeenWeight = 0.08;
        Double twentyNineWeight = 0.08;
        Double fourteenWeight = 0.08;

        FeaturesMessage featuresMessage = new FeaturesMessage();
        featuresMessage.setId(1);
        featuresMessage.setFeatures("是否有论文发表或获奖情况");
        featuresMessage.setWeight(fortyFiveWeight);
        featuresMessage.setNiceFeatures("有论文发表或获奖情况");
        featuresMessage.setNiceNumber(fortyFiveYesPossess);
        featuresMessage.setBadFeatures("没有论文发表或获奖情况");
        featuresMessage.setBadNumber(fortyFiveNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(2);
        featuresMessage.setFeatures("团队意识及协作能力");
        featuresMessage.setWeight(twelveWeight);
        featuresMessage.setNiceFeatures("团队意识及协作能力良好");
        featuresMessage.setNiceNumber(twelveYesPossess);
        featuresMessage.setBadFeatures("团队意识及协作能力一般");
        featuresMessage.setBadNumber(twelveNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(3);
        featuresMessage.setFeatures("是否四级烟草制品购销职业资格(最高级别)");
        featuresMessage.setWeight(seventeenWeight);
        featuresMessage.setNiceFeatures("有四级烟草制品购销职业资格(最高级别)");
        featuresMessage.setNiceNumber(seventeenYesPossess);
        featuresMessage.setBadFeatures("没有四级烟草制品购销职业资格(最高级别)");
        featuresMessage.setBadNumber(seventeenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(4);
        featuresMessage.setFeatures("是否数据分析团队成员 ");
        featuresMessage.setWeight(twentyNineWeight);
        featuresMessage.setNiceFeatures("是数据分析团队成员 ");
        featuresMessage.setNiceNumber(twentyNineYesPossess);
        featuresMessage.setBadFeatures("不是数据分析团队成员 ");
        featuresMessage.setBadNumber(twentyNineNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(5);
        featuresMessage.setFeatures("是否初级经济师");
        featuresMessage.setWeight(fourteenWeight);
        featuresMessage.setNiceFeatures("是初级经济师");
        featuresMessage.setNiceNumber(fourteenYesPossess);
        featuresMessage.setBadFeatures("不是初级经济师");
        featuresMessage.setBadNumber(fourteenNoPossess);
        list.add(featuresMessage);
        return list;
    }



//    public List<FeaturesMessage> comprehensiveAdministratorByPost1(String createdTime){
//        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
//        Map<String,List<Double>> map = new HashMap<>();
//        List<Double> list = new ArrayList<>();
//        Double thirtySixYes=0.0;
//        Double thirtySixNo = 0.0;
//        Double thirtyYes=0.0;
//        Double thirtyNo = 0.0;
//        Double oneYes=0.0;
//        Double oneNo = 0.0;
//        Double fortyFiveYes=0.0;
//        Double fortyFiveNo = 0.0;
//        Double nineTeenYes=0.0;
//        Double nineTeenNo = 0.0;
//        for (Employee employee : employees) {
//            String thirtySix = employee.getThirtySix();
//            String thirty = employee.getThirty();
//            String one = employee.getOne();
//            String fortyFive = employee.getFortyFive();
//            String nineTeen = employee.getNineteen();
//
//            if (thirtySix.equals("是")){
//                thirtySixYes+=1;
//            }else{
//                thirtySixNo+=1;
//            }
//
//            if (thirty.equals("是")){
//                thirtyYes+=1;
//            }else{
//                thirtyNo+=1;
//            }
//
//            if (one.equals("是")){
//                oneYes+=1;
//            }else{
//                oneNo+=1;
//            }
//
//            if (fortyFive.equals("是")){
//                fortyFiveYes+=1;
//            }else{
//                fortyFiveNo+=1;
//            }
//
//            if (nineTeen.equals("是")){
//                nineTeenYes+=1;
//            }else{
//                nineTeenNo+=1;
//            }
//        }
//        Double thirtySixYesPossess = thirtySixYes/(thirtyYes+thirtySixNo);
//        Double thirtySixNoPossess = thirtySixNo/(thirtyYes+thirtySixNo);
//        Double thirtyYesPossess = thirtyYes/(thirtyYes+thirtyNo);
//        Double thirtyNoPossess = thirtyNo/(thirtyYes+thirtyNo);
//        Double oneYesPossess = oneYes/(oneYes+oneNo);
//        Double oneNoPossess = oneNo/(oneYes+oneNo);
//        Double fortyFiveYesPossess = fortyFiveYes/(fortyFiveYes+fortyFiveNo);
//        Double fortyFiveNoPossess = fortyFiveNo/(fortyFiveYes+fortyFiveNo);
//        Double nineTeenYesPossess = nineTeenYes/(nineTeenYes+nineTeenNo);
//        Double nineTeenNoPossess = nineTeenNo/(nineTeenYes+nineTeenNo);
//        Double thirtySixWeight = 0.16;
//        Double thirtyWeight = 0.12;
//        Double oneWeight = 0.11;
//        Double fortyFiveWeight = 0.11;
//        Double nineTeenWeight = 0.08;
//
//        list.add(thirtySixWeight);
//        list.add(thirtySixYesPossess);
//        list.add(thirtySixNoPossess);
//        map.put("粤语掌握情况",list);
//        list.clear();
//
//        list.add(thirtyWeight);
//        list.add(thirtyYesPossess);
//        list.add(thirtyNoPossess);
//        map.put("是否新媒体营销团队成员",list);
//        list.clear();
//
//        list.add(oneWeight);
//        list.add(oneYesPossess);
//        list.add(oneNoPossess);
//        map.put("是否有体育特长",list);
//        list.clear();
//
//        list.add(fortyFiveWeight);
//        list.add(fortyFiveYesPossess);
//        list.add(fortyFiveNoPossess);
//        map.put("是否有论文发表或获奖情况",list);
//        list.clear();
//
//        list.add(nineTeenWeight);
//        list.add(nineTeenYesPossess);
//        list.add(nineTeenNoPossess);
//        map.put("是否具有中级以上计算机方面的资格证书",list);
//        list.clear();
//        return map;
//    }
    /**
     * 综合管理员,传入员工日期，算出该岗位前五的优秀标签以及员工拥有占比
     * 例如综合管理员优秀标签前五为
     * four 是否艺术类兴趣小组成员 0.15 是(0.15) 否(0.08)
     * seventeen 是否四级烟草制品购销职业资格（最高级别) 0.10 是(0.10)  否(0.06)
     * eighteen 是否五级烟草制品购销职业资格（最高级别） 0.07  是(0.07)  否(0.04)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.07   是(0.07)  否(0.04)
     * thirteen 是否中级或以上经济师 0.06   是(0.06)  否(0.04)
     * @param createdTime
     * @return
     */
    public List<FeaturesMessage> comprehensiveAdministratorByPost(String createdTime){
        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
        List<FeaturesMessage> list = new ArrayList<>();
        Double fourYes=0.0;
        Double fourNo = 0.0;
        Double seventeenYes=0.0;
        Double seventeenNo = 0.0;
        Double eighteenYes=0.0;
        Double eighteenNo = 0.0;
        Double nineteenYes=0.0;
        Double nineteenNo = 0.0;
        Double thirteenYes=0.0;
        Double thirteenNo = 0.0;
        for (Employee employee : employees) {
            String four = employee.getFour();
            String seventeen = employee.getSeventeen();
            String eighteen = employee.getEighteen();
            String nineteen = employee.getNineteen();
            String thirteen = employee.getThirteen();


            if (four.equals("是")){
                fourYes+=1;
            }else{
                fourNo+=1;
            }

            if (seventeen.equals("是")){
                seventeenYes+=1;
            }else{
                seventeenNo+=1;
            }

            if (eighteen.equals("是")){
                eighteenYes+=1;
            }else{
                eighteenNo+=1;
            }

            if (nineteen.equals("是")){
                nineteenYes+=1;
            }else{
                nineteenNo+=1;
            }

            if (thirteen.equals("是")){
                thirteenYes+=1;
            }else{
                thirteenNo+=1;
            }
        }
        //拥有人数占比
        Double fourYesPossess = fourYes/(fourYes+fourNo);
        Double fourNoPossess = fourNo/(fourYes+fourNo);
        Double seventeenYesPossess = seventeenYes/(seventeenYes+seventeenNo);
        Double seventeenNoPossess = seventeenNo/(seventeenYes+seventeenNo);
        Double eighteenYesPossess = eighteenYes/(eighteenYes+eighteenNo);
        Double eighteenNoPossess = eighteenNo/(eighteenYes+eighteenNo);
        Double nineteenYesPossess = nineteenYes/(nineteenYes+nineteenNo);
        Double nineteenNoPossess = nineteenNo/(nineteenYes+nineteenNo);
        Double thirteenYesPossess = thirteenYes/(thirteenYes+thirteenNo);
        Double thirteenNoPossess = thirteenNo/(thirteenYes+thirteenNo);
        //权重赋值
        Double fourWeight = 0.15;
        Double seventeenWeight = 0.10;
        Double eighteenWeight = 0.07;
        Double nineteenWeight = 0.07;
        Double thirteenWeight = 0.06;

        FeaturesMessage featuresMessage = new FeaturesMessage();
        featuresMessage.setId(1);
        featuresMessage.setFeatures("是否艺术类兴趣小组成员");
        featuresMessage.setWeight(fourWeight);
        featuresMessage.setNiceFeatures("是艺术类兴趣小组成员");
        featuresMessage.setNiceNumber(fourYesPossess);
        featuresMessage.setBadFeatures("不是艺术类兴趣小组成员");
        featuresMessage.setBadNumber(fourNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(2);
        featuresMessage.setFeatures("是否四级烟草制品购销职业资格(最高级别)");
        featuresMessage.setWeight(seventeenWeight);
        featuresMessage.setNiceFeatures("拥有四级烟草制品购销职业资格(最高级别)");
        featuresMessage.setNiceNumber(seventeenYesPossess);
        featuresMessage.setBadFeatures("未拥有是否四级烟草制品购销职业资格(最高级别)");
        featuresMessage.setBadNumber(seventeenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(3);
        featuresMessage.setFeatures("是否五级烟草制品购销职业资格(最高级别)");
        featuresMessage.setWeight(eighteenWeight);
        featuresMessage.setNiceFeatures("拥有五级烟草制品购销职业资格(最高级别)");
        featuresMessage.setNiceNumber(eighteenYesPossess);
        featuresMessage.setBadFeatures("未拥有五级烟草制品购销职业资格(最高级别)");
        featuresMessage.setBadNumber(eighteenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(4);
        featuresMessage.setFeatures("是否具有中级以上计算机方面的资格证书 ");
        featuresMessage.setWeight(nineteenWeight);
        featuresMessage.setNiceFeatures("具有中级以上计算机方面的资格证书 ");
        featuresMessage.setNiceNumber(nineteenYesPossess);
        featuresMessage.setBadFeatures("未具有中级以上计算机方面的资格证书 ");
        featuresMessage.setBadNumber(nineteenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(5);
        featuresMessage.setFeatures("是否中级或以上经济师");
        featuresMessage.setWeight(thirteenWeight);
        featuresMessage.setNiceFeatures("是中级或以上经济师");
        featuresMessage.setNiceNumber(thirteenYesPossess);
        featuresMessage.setBadFeatures("不是中级或以上经济师");
        featuresMessage.setBadNumber(thirteenNoPossess);
        list.add(featuresMessage);
        return list;
    }


    /**
     * 客户专员,传入员工日期，算出该岗位前五的优秀标签以及员工拥有占比
     * 例如终端专员优秀标签前五为
     * forty_three 生育情况 0.12  无(0.06) 一孩(0.08)  二孩(0.10)  三孩(0.12)
     * thirty 是否新媒体营销团队成员  0.09  是(0.09) 否(0.05)
     * seven 新媒体营销技术水平  0.08     很好 较好(0.08)  一般(0.05) 较差(0.03)
     * forty（区/县/市）定居 0.08  是(0.08) 否(0.04)
     * forty_one 是否有任职营销以外岗位的工作经历 0.04   是(0.04) 否(0.02)
     * @param createdTime
     * @return
     */
    public List<FeaturesMessage> accountSpecialistByPost(String createdTime){
        List<Employee> employees = employeeMapper.findByCreatedTime(createdTime);
        List<FeaturesMessage> list = new ArrayList<>();
        Double fortyThreeYes=0.0;
        Double fortyThreeNo = 0.0;
        Double thirtyYes=0.0;
        Double thirtyNo = 0.0;
        Double sevenYes=0.0;
        Double sevenNo = 0.0;
        Double fortyYes=0.0;
        Double fortyNo = 0.0;
        Double fortyOneYes=0.0;
        Double fortyOneNo = 0.0;
        for (Employee employee : employees) {
            String fortyThree = employee.getFortyThree();
            String thirty = employee.getThirty();
            String seven = employee.getSeven();
            String forty = employee.getForty();
            String fortyOne = employee.getFortyOne();

            if (fortyThree.equals("三孩及以上")){
                fortyThreeYes+=1;
            }else if (fortyThree.equals("二孩")){
                fortyThreeYes+=1;
            }else if (fortyThree.equals("一孩")){
                fortyThreeNo+=1;
            }else{
                fortyThreeNo+=1;
            }

            if (thirty.equals("是")){
                thirtyYes+=1;
            }else{
                thirtyNo+=1;
            }

            if (seven.equals("很好")){
                sevenYes+=1;
            }else if (seven.equals("较好")){
                sevenYes+=1;
            }else if (seven.equals("一般")){
                sevenNo+=1;
            }else {
                sevenNo+=1;
            }

            if (forty.equals("是")){
                fortyYes+=1;
            }else{
                fortyNo+=1;
            }

            if (fortyOne.equals("是")){
                fortyOneYes+=1;
            }else{
                fortyOneNo+=1;
            }
        }
        Double fortyThreeYesPossess = fortyThreeYes/(fortyThreeYes+fortyThreeNo);
        Double fortyThreeNoPossess = fortyThreeNo/(fortyThreeYes+fortyThreeNo);
        Double thirtyYesPossess = thirtyYes/(thirtyYes+thirtyNo);
        Double thirtyNoPossess = thirtyNo/(thirtyYes+thirtyNo);
        Double sevenYesPossess = sevenYes/(sevenYes+sevenNo);
        Double sevenNoPossess = sevenNo/(sevenYes+sevenNo);
        Double fortyYesPossess = fortyYes/(fortyYes+fortyNo);
        Double fortyNoPossess = fortyNo/(fortyYes+fortyNo);
        Double fortyOneYesPossess = fortyOneYes/(fortyOneYes+fortyOneNo);
        Double fortyOneNoPossess = fortyOneNo/(fortyOneYes+fortyOneNo);
        Double fortyThreeWeight = 0.12;
        Double thirtyWeight = 0.09;
        Double sevenWeight = 0.08;
        Double fortyWeight = 0.08;
        Double fortyOneWeight = 0.04;

        FeaturesMessage featuresMessage = new FeaturesMessage();
        featuresMessage.setId(1);
        featuresMessage.setFeatures("生育情况");
        featuresMessage.setWeight(fortyThreeWeight);
        featuresMessage.setNiceFeatures("三孩及以上");
        featuresMessage.setNiceNumber(fortyThreeYesPossess);
        featuresMessage.setBadFeatures("最多一个孩子");
        featuresMessage.setBadNumber(fortyThreeNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(2);
        featuresMessage.setFeatures("是否新媒体营销团队成员");
        featuresMessage.setWeight(thirtyWeight);
        featuresMessage.setNiceFeatures("是新媒体营销团队成员");
        featuresMessage.setNiceNumber(thirtyYesPossess);
        featuresMessage.setBadFeatures("不是新媒体营销团队成员");
        featuresMessage.setBadNumber(thirtyNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(3);
        featuresMessage.setFeatures("新媒体营销技术水平");
        featuresMessage.setWeight(sevenWeight);
        featuresMessage.setNiceFeatures("新媒体营销技术水平优秀");
        featuresMessage.setNiceNumber(sevenYesPossess);
        featuresMessage.setBadFeatures("新媒体营销技术一般");
        featuresMessage.setBadNumber(sevenNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(4);
        featuresMessage.setFeatures("是否在工作地（区/县/市）定居");
        featuresMessage.setWeight(fortyWeight);
        featuresMessage.setNiceFeatures("是否在工作地（区/县/市）定居");
        featuresMessage.setNiceNumber(fortyYesPossess);
        featuresMessage.setBadFeatures("是否在工作地（区/县/市）定居");
        featuresMessage.setBadNumber(fortyNoPossess);
        list.add(featuresMessage);

        featuresMessage = new FeaturesMessage();
        featuresMessage.setId(5);
        featuresMessage.setFeatures("是否中级或以上经济师");
        featuresMessage.setWeight(fortyOneWeight);
        featuresMessage.setNiceFeatures("是否中级或以上经济师");
        featuresMessage.setNiceNumber(fortyOneYesPossess);
        featuresMessage.setBadFeatures("是否中级或以上经济师");
        featuresMessage.setBadNumber(fortyOneNoPossess);
        list.add(featuresMessage);
        return list;
    }



    /////////////////////////////算出该员工对该岗位优秀标签的拥有情况////////////////////////////////////////

    /**
     * 终端专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * performance 绩效 0.20 82.984-87(0.08) 87-91(0.12) 91-95(0.16) 95-99(0.20)
     * one 是否有体育特长 0.11 是(0.11)  否(0.06)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.08  是(0.08)  否(0.04)
     * twenty 是否有参加市局组织的新媒体培训经历 0.07   是(0.07)  否(0.04)
     * thirty 是否新媒体营销团队成员 0.12  是(0.12)  否(0.06)
     * thirtyFour 当地主要使用的方言掌握情况  0.04 会说(0.04)  能听不会说(0.03)  基本听不懂(0.02)
     * thirtySix 粤语掌握情况  0.16  会说(0.16)  能听不会说(0.12)  基本听不懂(0.08)
     * thirtyNine 是否在工作地（区/县/市）定居 0.05  是(0.05)  否(0.03)
     * fortyFive 是否有论文发表或获奖情况 0.11   是(0.11)  否(0.06)
     * fortyNine 是否有参与的视频项目并在省局以上媒体发表情况  0.06  是(0.06)  否(0.03)
     * @param name
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse terminalSpecialist(String name, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        String one = employee.getOne();
        String nineteen = employee.getNineteen();
        String twenty = employee.getTwenty();
        String thirty = employee.getThirty();
        String thirtyFour = employee.getThirtyFour();
        String thirtySix = employee.getThirtySix();
        String thirtyNine = employee.getThirtyNine();
        String fortyFive = employee.getFortyFive();
        String fortyNine = employee.getFortyNine();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [9];
        String [] employeeBadFeaturesMessage = new String [9];
        if (one.equals("是")) {
            employeeGoodFeaturesMessage[0]="有体育特长";
        }else {
            employeeBadFeaturesMessage[0]="没有体育特长";
        }

        if (nineteen.equals("是")) {
            employeeGoodFeaturesMessage[1]="具有中级以上计算机方面的资格证书";
        }else {
            employeeBadFeaturesMessage[1]="还未具有中级以上计算机方面的资格证书";
        }

        if (twenty.equals("是")) {
            employeeGoodFeaturesMessage[2]="有参加市局组织的新媒体培训经历";
        }else {
            employeeBadFeaturesMessage[2]="还没有参加市局组织的新媒体培训经历";
        }

        if (thirty.equals("是")) {
            employeeGoodFeaturesMessage[3]="是新媒体营销团队成员";
        }else {
            employeeBadFeaturesMessage[3]="还不是新媒体营销团队成员";
        }

        if (thirtyFour.equals("会说")) {
            employeeGoodFeaturesMessage[4]="会说当地主要使用的方言";
        }else {
            employeeBadFeaturesMessage[4]="当地主要使用的方言掌握情况一般";
        }

        if (thirtySix.equals("会说")) {
            employeeGoodFeaturesMessage[5]="粤语掌握情况良好";
        }else {
            employeeBadFeaturesMessage[5]="粤语掌握情况一般";
        }

        if (thirtyNine.equals("是")) {
            employeeGoodFeaturesMessage[6]="在工作地（区/县/市）定居";
        }else {
            employeeBadFeaturesMessage[6]="未在工作地（区/县/市）定居";
        }

        if (fortyFive.equals("是")) {
            employeeGoodFeaturesMessage[7]="有论文发表或获奖情况";
        }else {
            employeeBadFeaturesMessage[7]="还没有论文发表或获奖情况";
        }

        if (fortyNine.equals("是")) {
            employeeGoodFeaturesMessage[8]="有参与的视频项目并在省局以上媒体发表情况";
        }else {
            employeeBadFeaturesMessage[8]="还没有参与的视频项目并在省局以上媒体发表情况";
        }

        String [] postGoodFeaturesMessage = {"是否有体育特长",
                "是否具有中级以上计算机方面的资格证书",
                "是否有参加市局组织的新媒体培训条历",
                "是否新媒体营销团队成员",
                "当地主要使用的方言掌握情况",
                "粤语掌握情况",
                "是否在工作地 (区/县/市)定居",
                "是否有论文发表或获奖情况",
                "是否有参与的视频项目并在省局以上媒体发表情况",};
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }


//    public Map<String, String> marketingManager(String name, String createdTime) {
//        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
//        Performane performane = performaneMapper.findNameAndCreatedTime(name,createdTime);
//        Double scores = performane.getScores();
//        Double factor = performane.getFactor();
//        Map<String, String> map = new HashMap<>();
//        String one = employee.getOne();
//        String nineteen = employee.getNineteen();
//        String twenty = employee.getTwenty();
//        String thirty = employee.getThirty();
//        String thirtyFour = employee.getThirtyFour();
//        String thirtySix = employee.getThirtySix();
//        String thirtyNine = employee.getThirtyNine();
//        String fortyFive = employee.getFortyFive();
//        String fortyNine = employee.getFortyNine();
//        if (one.equals("是")) {
//            map.put("是否有体育特长", "是");
//        } else {
//            map.put("是否有体育特长", "否");
//        }
//
//        if (nineteen.equals("是")) {
//            map.put("是否具有中级以上计算机方面的资格证书", "是");
//        } else {
//            map.put("是否具有中级以上计算机方面的资格证书", "否");
//        }
//
//        if (twenty.equals("是")) {
//            map.put("是否有参加市局组织的新媒体培训经历", "是");
//        } else {
//            map.put("是否有参加市局组织的新媒体培训经历", "否");
//        }
//
//        if (thirty.equals("是")) {
//            map.put("是否新媒体营销团队成员", "是");
//        } else {
//            map.put("是否新媒体营销团队成员", "否");
//        }
//
//        if (thirtyFour.equals("是")) {
//            map.put("当地主要使用的方言掌握情况", "是");
//        } else {
//            map.put("当地主要使用的方言掌握情况", "否");
//        }
//
//        if (thirtySix.equals("是")) {
//            map.put("粤语掌握情况", "是");
//        } else {
//            map.put("粤语掌握情况", "否");
//        }
//
//        if (thirtyNine.equals("是")) {
//            map.put("是否在工作地（区/县/市）定居", "是");
//        } else {
//            map.put("是否在工作地（区/县/市）定居", "否");
//        }
//
//        if (fortyFive.equals("是")) {
//            map.put("是否有论文发表或获奖情况", "是");
//        } else {
//            map.put("是否有论文发表或获奖情况", "否");
//        }
//
//        if (fortyNine.equals("是")) {
//            map.put("是否有参与的视频项目并在省局以上媒体发表情况", "是");
//        } else {
//            map.put("是否有参与的视频项目并在省局以上媒体发表情况", "否");
//        }
//        map.put("绩效", String.valueOf(scores));
//        map.put("匹配系数", String.valueOf(factor));
//        return map;
//    }////
    /**
     * 市场经理,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * thirteen    0.26  是否中级或以上经济师  是(0.26)  否(0.20)
     * eighteen    0.25  是否五级烟草制品购销职业资格（最高级别）  是(0.20)  否(0.15)
     * fortyfive   0.10  是否有论文发表或获奖情况   是(0.10)  否(0.06)
     * fortyfour   0.10  是否有QC项目获奖情况    是(0.10)  否(0.06)
     * fortytwo    0.10  是否已婚   是(0.10)  否(0.06)
     * performance 0.08  绩效   82.984-87(0.02) 87-91(0.04) 91-95(0.06) 95-99(0.08)
     * fortyseven  0.05  是否有参与数字化转型项目情况    是(0.05)  否(0.03)
     * five        0.03  公文写作能力   很好较好(0.03)  一般(0.02)  较差(0.01)
     * thirtyfour  0.03  当地主要使用的方言掌握情况  会说(0.03)  能听不会说(0.02)  基本听不懂(0.01)
     * @param name
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse marketingManager(String name, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        String thirty = employee.getThirty();
        String eighteen = employee.getEighteen();
        String fortyFive = employee.getFortyFive();
        String fortyFour = employee.getFortyFour();
        String fortyTwo = employee.getFortyTwo();
        String fortySeven = employee.getFortySeven();
        String five = employee.getFive();
        String thirtyFour = employee.getThirtyFour();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [8];
        String [] employeeBadFeaturesMessage = new String [8];
        if (thirty.equals("是")) {
            employeeGoodFeaturesMessage[0]="是中级或以上经济师";
        }else {
            employeeBadFeaturesMessage[0]="不是中级或以上经济师";
        }

        if (eighteen.equals("是")) {
            employeeGoodFeaturesMessage[1]="是五级烟草制品购销职业资格（最高级别）";
        }else {
            employeeBadFeaturesMessage[1]="不是五级烟草制品购销职业资格（最高级别）";
        }

        if (fortyFive.equals("是")) {
            employeeGoodFeaturesMessage[2]="有论文发表或获奖情况";
        }else {
            employeeBadFeaturesMessage[2]="没有论文发表或获奖情况";
        }

        if (fortyFour.equals("是")) {
            employeeGoodFeaturesMessage[3]="有QC项目获奖情况";
        }else {
            employeeBadFeaturesMessage[3]="没有QC项目获奖情况";
        }

        if (fortyTwo.equals("是")) {
            employeeGoodFeaturesMessage[4]="已婚";
        }else {
            employeeBadFeaturesMessage[4]="未婚";
        }

        if (fortySeven.equals("是")) {
            employeeGoodFeaturesMessage[5]="有参与数字化转型项目情况";
        }else {
            employeeBadFeaturesMessage[5]="没有参与数字化转型项目情况";
        }

        if (five.equals("很好")) {
            employeeGoodFeaturesMessage[6]="公文写作能力优秀";
        }else if (five.equals("较好")){
            employeeGoodFeaturesMessage[6]="公文写作能力优秀";
        }else {
            employeeBadFeaturesMessage[6]="公文写作能力一般";
        }

        if (thirtyFour.equals("会说")) {
            employeeGoodFeaturesMessage[7]="会说当地主要使用的方言掌握情况";
        }else {
            employeeBadFeaturesMessage[7]="当地主要使用的方言掌握情况一般";
        }
        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"是否中级或以上经济师",
                "是否五级烟草制品购销职业资格（最高级别）",
                "是否有论文发表或获奖情况",
                "是否有QC项目获奖情况",
                "是否已婚",
                "是否有参与数字化转型项目情况",
                "公文写作能力",
                "当地主要使用的方言掌握情况"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }



    /**
     * 信息专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * age 年龄  0.17     22.966-31.5(0.11) 31.5-40(0.13) 40-48.5(0.15) 48.5-57(0.17)
     * fortyFive  是否有论文发表或获奖情况 0.14    是(0.14)  否(0.10)
     * seniority 工龄 0.11   -0.01-9(0.05) 9-16(0.07)  16-22(0.09) 22-(0.11)
     * twelve  团队意识及协作能力  0.10   很好较好(0.10)  一般(0.08)  较差(0.06)
     * senventeen 是否四级烟草制品购销职业资格（最高级别)  0.08   是(0.08)  否(0.05)
     * twentynine  是否数据分析团队成员  0.08    是(0.08)  否(0.05)
     * fourteen  是否初级经济师  0.08    是(0.08)  否(0.05)
     * fortyTwo 是否已婚  0.06    是(0.06)  否(0.03)
     * fortyOne  是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）  0.05    是(0.05)  否(0.03)
     * performance  绩效  0.13  82.984-87(0.07) 87-91(0.09) 91-95(0.11) 95-99(0.13)
     * @param name
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse informationCommissioner(String name, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        String fortyFive = employee.getFortyFive();
        String twelve = employee.getTwelve();
        String seventeen = employee.getSeventeen();
        String twentyNine = employee.getTwentyNine();
        String fourteen = employee.getFourteen();
        String fortyTwo = employee.getFortyTwo();
        String fortyOne = employee.getFortyOne();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [7];
        String [] employeeBadFeaturesMessage = new String [7];
        if (fortyFive.equals("是")) {
            employeeGoodFeaturesMessage[0]="有论文发表或获奖情况";
        }else {
            employeeBadFeaturesMessage[0]="没有论文发表或获奖情况";
        }

        if (twelve.equals("很好")) {
            employeeGoodFeaturesMessage[1]="团队意识及协作能力优秀";
        }else if (twelve.equals("较好")){
            employeeGoodFeaturesMessage[1]="团队意识及协作能力优秀";
        }else {
            employeeBadFeaturesMessage[1]="团队意识及协作能力一般";
        }

        if (seventeen.equals("是")) {
            employeeGoodFeaturesMessage[2]="有四级烟草制品购销职业资格(最高级别)";
        }else {
            employeeBadFeaturesMessage[2]="没有四级烟草制品购销职业资格(最高级别)";
        }

        if (twentyNine.equals("是")) {
            employeeGoodFeaturesMessage[3]="是数据分析团队成员";
        }else {
            employeeBadFeaturesMessage[3]="不是数据分析团队成员";
        }

        if (fourteen.equals("是")) {
            employeeGoodFeaturesMessage[4]="是否初级经济师";
        }else {
            employeeBadFeaturesMessage[4]="不是否初级经济师";
        }

        if (fortyTwo.equals("是")) {
            employeeGoodFeaturesMessage[5]="已婚";
        }else {
            employeeBadFeaturesMessage[5]="未婚";
        }

        if (fortyOne.equals("是")) {
            employeeGoodFeaturesMessage[6]="有任职当前岗位以外营销岗位的工作经历(客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位)";
        }else {
            employeeBadFeaturesMessage[6]="没有任职当前岗位以外营销岗位的工作经历(客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位)";
        }

        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"是否有论文发表或获奖情况",
                "团队意识及协作能力",
                "是否四级烟草制品购销职业资格",
                "是否数据分析团队成员",
                "是否初级经济师",
                "是否已婚",
                "是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }

    /**
     * 综合管理员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * performance 绩效 0.25   82.984-87(0.16) 87-91(0.19) 91-95(0.22) 95-99(0.25)
     * age  年龄 0.18  22.966-31.5(0.09) 31.5-40(0.12) 40-48.5(0.15) 48.5-57(0.18)
     * four 是否艺术类兴趣小组成员 0.15 是(0.15) 否(0.08)
     * seventeen 是否四级烟草制品购销职业资格（最高级别) 0.10 是(0.10)  否(0.06)
     * eighteen 是否五级烟草制品购销职业资格（最高级别） 0.07  是(0.07)  否(0.04)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.07   是(0.07)  否(0.04)
     * thirteen 是否中级或以上经济师 0.06   是(0.06)  否(0.04)
     * eleven 营销策划及执行能力 0.04 很好较好(0.04)  一般(0.03)  较差(0.02)
     * twentyOne 是否有参加市局组织的数据分析培训经历  0.04   是(0.04)  否(0.02)
     * twelve 团队意识及协作能力  0.04   很好较好(0.04)  一般(0.03)  较差(0.02)
     * @param name
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse comprehensiveAdministrator(String name, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        String four = employee.getFour();
        String seventeen = employee.getSeventeen();
        String eighteen = employee.getEighteen();
        String nineteen = employee.getNineteen();
        String thirteen = employee.getThirteen();
        String eleven = employee.getEleven();
        String twentyOne = employee.getTwentyOne();
        String twelve = employee.getTwelve();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [8];
        String [] employeeBadFeaturesMessage = new String [8];
        if (four.equals("是")) {
            employeeGoodFeaturesMessage[0]="是艺术类兴趣小组成员";
        }else {
            employeeBadFeaturesMessage[0]="不是艺术类兴趣小组成员";
        }

        if (seventeen.equals("是")) {
            employeeGoodFeaturesMessage[1]="有四级烟草制品购销职业资格（最高级别)";
        }else {
            employeeBadFeaturesMessage[1]="没有四级烟草制品购销职业资格（最高级别)";
        }

        if (eighteen.equals("是")) {
            employeeGoodFeaturesMessage[2]="有五级烟草制品购销职业资格（最高级别)";
        }else {
            employeeBadFeaturesMessage[2]="没有五级烟草制品购销职业资格（最高级别)";
        }

        if (nineteen.equals("是")) {
            employeeGoodFeaturesMessage[3]="具有中级以上计算机方面的资格证书";
        }else {
            employeeBadFeaturesMessage[3]="不具有中级以上计算机方面的资格证书";
        }

        if (thirteen.equals("是")) {
            employeeGoodFeaturesMessage[4]="是中级或以上经济师";
        }else {
            employeeBadFeaturesMessage[4]="不是中级或以上经济师";
        }


        if (eleven.equals("很好")) {
            employeeGoodFeaturesMessage[5]="营销策划及执行能力优秀";
        }else if (eleven.equals("较好")){
            employeeGoodFeaturesMessage[5]="营销策划及执行能力优秀";
        }else {
            employeeBadFeaturesMessage[5]="营销策划及执行能力一般";
        }

        if (twentyOne.equals("是")) {
            employeeGoodFeaturesMessage[6]="有参加市局组织的数据分析培训经历";
        }else {
            employeeBadFeaturesMessage[6]="没有参加市局组织的数据分析培训经历";
        }

        if (twelve.equals("很好")) {
            employeeGoodFeaturesMessage[7]="团队意识及协作能力优秀";
        }else if (twelve.equals("较好")){
            employeeGoodFeaturesMessage[7]="团队意识及协作能力优秀";
        }else {
            employeeBadFeaturesMessage[7]="团队意识及协作能力一般";
        }

        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"是否艺术类兴趣小组成员",
                "是否四级烟草制品购销职业资格（最高级别)",
                "是否五级烟草制品购销职业资格（最高级别)",
                "是否具有中级以上计算机方面的资格证书",
                "是否中级或以上经济师",
                "营销策划及执行能力",
                "是否有参加市局组织的数据分析培训经历",
                "团队意识及协作能力"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }

    /**
     * 客户专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * degree 最高学历  0.10 大学(0.10) 大专(0.08) 高中(0.06) 中专、初级、技校(0.04)
     * age 年龄 0.05  22.966-31.5(0.02) 31.5-40(0.03) 40-48.5(0.04) 48.5-57(0.05)
     * seniority 工作年龄 0.19  -0.01-9(0.07) 9-16(0.11)  16-22(0.15) 22-(0.19)
     * performance月度绩效  0.22     82.984-87(0.10) 87-91(0.14) 91-95(0.18) 95-99(0.22)
     * seven 新媒体营销技术水平  0.08     很好 较好(0.08)  一般(0.05) 较差(0.03)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.03    是(0.03)  否(0.02)
     * thirty 是否新媒体营销团队成员  0.09  是(0.09) 否(0.05)
     * forty  是否在工作地（区/县/市）定居 0.08  是(0.08) 否(0.04)
     * forty_one 是否有任职营销以外岗位的工作经历 0.04   是(0.04) 否(0.02)
     * forty_three 生育情况 0.12  无(0.06) 一孩(0.08)  二孩(0.10)  三孩(0.12)
     * @param name
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse accountSpecialist(String name, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        String degree = employee.getDegree();
        String seven = employee.getSeven();
        String nineteen = employee.getNineteen();
        String thirty = employee.getThirty();
        String forty = employee.getForty();
        String fortyOne = employee.getFortyOne();
        String fortyThree = employee.getFortyThree();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [7];
        String [] employeeBadFeaturesMessage = new String [7];
        if (degree.equals("大学")) {
            employeeGoodFeaturesMessage[0]="高学历人才";
        }else if (degree.equals("大专")){
            employeeGoodFeaturesMessage[0]="高学历人才";
        }else {
            employeeGoodFeaturesMessage[0]="非高学历人才";
        }

        if (seven.equals("很好")) {
            employeeGoodFeaturesMessage[1]="新媒体营销技术水平优秀";
        }else if (seven.equals("较好")){
            employeeGoodFeaturesMessage[1]="新媒体营销技术水平优秀";
        }else {
            employeeGoodFeaturesMessage[1]="新媒体营销技术水平一般";
        }

        if (nineteen.equals("是")) {
            employeeGoodFeaturesMessage[2]="具有中级以上计算机方面的资格证书";
        }else {
            employeeGoodFeaturesMessage[2]="不具有中级以上计算机方面的资格证书";
        }

        if (thirty.equals("是")) {
            employeeGoodFeaturesMessage[3]="是新媒体营销团队成员";
        }else {
            employeeGoodFeaturesMessage[3]="不是新媒体营销团队成员";
        }

        if (forty.equals("是")) {
            employeeGoodFeaturesMessage[4]="在工作地（区/县/市）定居";
        }else {
            employeeGoodFeaturesMessage[4]="不在工作地（区/县/市）定居";
        }


        if (fortyOne.equals("是")) {
            employeeGoodFeaturesMessage[5]="有任职营销以外岗位的工作经历";
        }else {
            employeeGoodFeaturesMessage[5]="没有任职营销以外岗位的工作经历";
        }

        if (fortyThree.equals("三孩及以上")) {
            employeeGoodFeaturesMessage[6]="三孩及以上";
        }else if (fortyThree.equals("二孩")){
            employeeGoodFeaturesMessage[6]="二孩";
        }else {
            employeeGoodFeaturesMessage[6]="一孩及以下";
        }


        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"最高学历",
                "新媒体营销技术水平",
                "是否具有中级以上计算机方面的资格证书",
                "是否具有中级以上计算机方面的资格证书",
                "是否新媒体营销团队成员",
                "是否在工作地（区/县/市）定居",
                "是否有任职营销以外岗位的工作经历",
                "生育情况"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeGoodFeaturesMessage);
        return featuresAnalyse;
    }



    ///////////////////////////改成根据日期和员工编号查询员工优秀标签信息/////////////////////

    /**
     * 终端专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * performance 绩效 0.20 82.984-87(0.08) 87-91(0.12) 91-95(0.16) 95-99(0.20)
     * one 是否有体育特长 0.11 是(0.11)  否(0.06)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.08  是(0.08)  否(0.04)
     * twenty 是否有参加市局组织的新媒体培训经历 0.07   是(0.07)  否(0.04)
     * thirty 是否新媒体营销团队成员 0.12  是(0.12)  否(0.06)
     * thirtyFour 当地主要使用的方言掌握情况  0.04 会说(0.04)  能听不会说(0.03)  基本听不懂(0.02)
     * thirtySix 粤语掌握情况  0.16  会说(0.16)  能听不会说(0.12)  基本听不懂(0.08)
     * thirtyNine 是否在工作地（区/县/市）定居 0.05  是(0.05)  否(0.03)
     * fortyFive 是否有论文发表或获奖情况 0.11   是(0.11)  否(0.06)
     * fortyNine 是否有参与的视频项目并在省局以上媒体发表情况  0.06  是(0.06)  否(0.03)
     * @param number
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse terminalSpecialistNew(Integer number, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String one = employee.getOne();
        String nineteen = employee.getNineteen();
        String twenty = employee.getTwenty();
        String thirty = employee.getThirty();
        String thirtyFour = employee.getThirtyFour();
        String thirtySix = employee.getThirtySix();
        String thirtyNine = employee.getThirtyNine();
        String fortyFive = employee.getFortyFive();
        String fortyNine = employee.getFortyNine();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [9];
        String [] employeeBadFeaturesMessage = new String [9];
        if (one.equals("是")) {
            employeeGoodFeaturesMessage[0]="有体育特长";
        }else {
            employeeBadFeaturesMessage[0]="体育特长";
        }

        if (nineteen.equals("是")) {
            employeeGoodFeaturesMessage[1]="具有中级以上计算机方面的资格证书";
        }else {
            employeeBadFeaturesMessage[1]="中级以上计算机方面的资格证书";
        }

        if (twenty.equals("是")) {
            employeeGoodFeaturesMessage[2]="有参加市局组织的新媒体培训经历";
        }else {
            employeeBadFeaturesMessage[2]="参加市局组织的新媒体培训经历";
        }

        if (thirty.equals("是")) {
            employeeGoodFeaturesMessage[3]="是新媒体营销团队成员";
        }else {
            employeeBadFeaturesMessage[3]="新媒体营销团队成员";
        }

        if (thirtyFour.equals("会说")) {
            employeeGoodFeaturesMessage[4]="会说当地主要使用的方言";
        }else {
            employeeBadFeaturesMessage[4]="当地主要使用的方言掌握情况";
        }

        if (thirtySix.equals("会说")) {
            employeeGoodFeaturesMessage[5]="粤语掌握情况良好";
        }else {
            employeeBadFeaturesMessage[5]="粤语掌握情况";
        }

        if (thirtyNine.equals("是")) {
            employeeGoodFeaturesMessage[6]="在工作地（区/县/市）定居";
        }else {
            employeeBadFeaturesMessage[6]="在工作地（区/县/市）定居";
        }

        if (fortyFive.equals("是")) {
            employeeGoodFeaturesMessage[7]="有论文发表或获奖情况";
        }else {
            employeeBadFeaturesMessage[7]="论文发表或获奖情况";
        }

        if (fortyNine.equals("是")) {
            employeeGoodFeaturesMessage[8]="有参与的视频项目并在省局以上媒体发表情况";
        }else {
            employeeBadFeaturesMessage[8]="参与的视频项目并在省局以上媒体发表情况";
        }

        String [] postGoodFeaturesMessage = {"是否有体育特长",
                "是否具有中级以上计算机方面的资格证书",
                "是否有参加市局组织的新媒体培训条历",
                "是否新媒体营销团队成员",
                "当地主要使用的方言掌握情况",
                "粤语掌握情况",
                "是否在工作地 (区/县/市)定居",
                "是否有论文发表或获奖情况",
                "是否有参与的视频项目并在省局以上媒体发表情况",};
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }


//    public Map<String, String> marketingManager(String name, String createdTime) {
//        Employee employee = employeeMapper.findByNameAndCreatedTime(name, createdTime);
//        Performane performane = performaneMapper.findNameAndCreatedTime(name,createdTime);
//        Double scores = performane.getScores();
//        Double factor = performane.getFactor();
//        Map<String, String> map = new HashMap<>();
//        String one = employee.getOne();
//        String nineteen = employee.getNineteen();
//        String twenty = employee.getTwenty();
//        String thirty = employee.getThirty();
//        String thirtyFour = employee.getThirtyFour();
//        String thirtySix = employee.getThirtySix();
//        String thirtyNine = employee.getThirtyNine();
//        String fortyFive = employee.getFortyFive();
//        String fortyNine = employee.getFortyNine();
//        if (one.equals("是")) {
//            map.put("是否有体育特长", "是");
//        } else {
//            map.put("是否有体育特长", "否");
//        }
//
//        if (nineteen.equals("是")) {
//            map.put("是否具有中级以上计算机方面的资格证书", "是");
//        } else {
//            map.put("是否具有中级以上计算机方面的资格证书", "否");
//        }
//
//        if (twenty.equals("是")) {
//            map.put("是否有参加市局组织的新媒体培训经历", "是");
//        } else {
//            map.put("是否有参加市局组织的新媒体培训经历", "否");
//        }
//
//        if (thirty.equals("是")) {
//            map.put("是否新媒体营销团队成员", "是");
//        } else {
//            map.put("是否新媒体营销团队成员", "否");
//        }
//
//        if (thirtyFour.equals("是")) {
//            map.put("当地主要使用的方言掌握情况", "是");
//        } else {
//            map.put("当地主要使用的方言掌握情况", "否");
//        }
//
//        if (thirtySix.equals("是")) {
//            map.put("粤语掌握情况", "是");
//        } else {
//            map.put("粤语掌握情况", "否");
//        }
//
//        if (thirtyNine.equals("是")) {
//            map.put("是否在工作地（区/县/市）定居", "是");
//        } else {
//            map.put("是否在工作地（区/县/市）定居", "否");
//        }
//
//        if (fortyFive.equals("是")) {
//            map.put("是否有论文发表或获奖情况", "是");
//        } else {
//            map.put("是否有论文发表或获奖情况", "否");
//        }
//
//        if (fortyNine.equals("是")) {
//            map.put("是否有参与的视频项目并在省局以上媒体发表情况", "是");
//        } else {
//            map.put("是否有参与的视频项目并在省局以上媒体发表情况", "否");
//        }
//        map.put("绩效", String.valueOf(scores));
//        map.put("匹配系数", String.valueOf(factor));
//        return map;
//    }////
    /**
     * 市场经理,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * thirteen    0.26  是否中级或以上经济师  是(0.26)  否(0.20)
     * eighteen    0.25  是否五级烟草制品购销职业资格（最高级别）  是(0.20)  否(0.15)
     * fortyfive   0.10  是否有论文发表或获奖情况   是(0.10)  否(0.06)
     * fortyfour   0.10  是否有QC项目获奖情况    是(0.10)  否(0.06)
     * fortytwo    0.10  是否已婚   是(0.10)  否(0.06)
     * performance 0.08  绩效   82.984-87(0.02) 87-91(0.04) 91-95(0.06) 95-99(0.08)
     * fortyseven  0.05  是否有参与数字化转型项目情况    是(0.05)  否(0.03)
     * five        0.03  公文写作能力   很好较好(0.03)  一般(0.02)  较差(0.01)
     * thirtyfour  0.03  当地主要使用的方言掌握情况  会说(0.03)  能听不会说(0.02)  基本听不懂(0.01)
     * @param number
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse marketingManagerNew(Integer number, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String thirty = employee.getThirty();
        String eighteen = employee.getEighteen();
        String fortyFive = employee.getFortyFive();
        String fortyFour = employee.getFortyFour();
        String fortyTwo = employee.getFortyTwo();
        String fortySeven = employee.getFortySeven();
        String five = employee.getFive();
        String thirtyFour = employee.getThirtyFour();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [8];
        String [] employeeBadFeaturesMessage = new String [8];
        if (thirty.equals("是")) {
            employeeGoodFeaturesMessage[0]="是中级或以上经济师";
        }else {
            employeeBadFeaturesMessage[0]="中级或以上经济师";
        }

        if (eighteen.equals("是")) {
            employeeGoodFeaturesMessage[1]="是五级烟草制品购销职业资格（最高级别）";
        }else {
            employeeBadFeaturesMessage[1]="五级烟草制品购销职业资格（最高级别）";
        }

        if (fortyFive.equals("是")) {
            employeeGoodFeaturesMessage[2]="有论文发表或获奖情况";
        }else {
            employeeBadFeaturesMessage[2]="论文发表或获奖情况";
        }

        if (fortyFour.equals("是")) {
            employeeGoodFeaturesMessage[3]="有QC项目获奖情况";
        }else {
            employeeBadFeaturesMessage[3]="QC项目获奖情况";
        }

        if (fortyTwo.equals("是")) {
            employeeGoodFeaturesMessage[4]="已婚";
        }else {
            employeeBadFeaturesMessage[4]="婚姻状况";
        }

        if (fortySeven.equals("是")) {
            employeeGoodFeaturesMessage[5]="有参与数字化转型项目情况";
        }else {
            employeeBadFeaturesMessage[5]="参与数字化转型项目情况";
        }

        if (five.equals("很好")) {
            employeeGoodFeaturesMessage[6]="公文写作能力优秀";
        }else if (five.equals("较好")){
            employeeGoodFeaturesMessage[6]="公文写作能力优秀";
        }else {
            employeeBadFeaturesMessage[6]="公文写作能力";
        }

        if (thirtyFour.equals("会说")) {
            employeeGoodFeaturesMessage[7]="会说当地主要使用的方言掌握情况";
        }else {
            employeeBadFeaturesMessage[7]="当地主要使用的方言掌握情况";
        }
        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"是否中级或以上经济师",
                "是否五级烟草制品购销职业资格（最高级别）",
                "是否有论文发表或获奖情况",
                "是否有QC项目获奖情况",
                "是否已婚",
                "是否有参与数字化转型项目情况",
                "公文写作能力",
                "当地主要使用的方言掌握情况"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }



    /**
     * 信息专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * age 年龄  0.17     22.966-31.5(0.11) 31.5-40(0.13) 40-48.5(0.15) 48.5-57(0.17)
     * fortyFive  是否有论文发表或获奖情况 0.14    是(0.14)  否(0.10)
     * seniority 工龄 0.11   -0.01-9(0.05) 9-16(0.07)  16-22(0.09) 22-(0.11)
     * twelve  团队意识及协作能力  0.10   很好较好(0.10)  一般(0.08)  较差(0.06)
     * senventeen 是否四级烟草制品购销职业资格（最高级别)  0.08   是(0.08)  否(0.05)
     * twentynine  是否数据分析团队成员  0.08    是(0.08)  否(0.05)
     * fourteen  是否初级经济师  0.08    是(0.08)  否(0.05)
     * fortyTwo 是否已婚  0.06    是(0.06)  否(0.03)
     * fortyOne  是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）  0.05    是(0.05)  否(0.03)
     * performance  绩效  0.13  82.984-87(0.07) 87-91(0.09) 91-95(0.11) 95-99(0.13)
     * @param number
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse informationCommissionerNew(Integer number, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String fortyFive = employee.getFortyFive();
        String twelve = employee.getTwelve();
        String seventeen = employee.getSeventeen();
        String twentyNine = employee.getTwentyNine();
        String fourteen = employee.getFourteen();
        String fortyTwo = employee.getFortyTwo();
        String fortyOne = employee.getFortyOne();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [7];
        String [] employeeBadFeaturesMessage = new String [7];
        if (fortyFive.equals("是")) {
            employeeGoodFeaturesMessage[0]="有论文发表或获奖情况";
        }else {
            employeeBadFeaturesMessage[0]="论文发表或获奖情况";
        }

        if (twelve.equals("很好")) {
            employeeGoodFeaturesMessage[1]="团队意识及协作能力优秀";
        }else if (twelve.equals("较好")){
            employeeGoodFeaturesMessage[1]="团队意识及协作能力优秀";
        }else {
            employeeBadFeaturesMessage[1]="团队意识及协作能力";
        }

        if (seventeen.equals("是")) {
            employeeGoodFeaturesMessage[2]="有四级烟草制品购销职业资格(最高级别)";
        }else {
            employeeBadFeaturesMessage[2]="没有四级烟草制品购销职业资格(最高级别)";
        }

        if (twentyNine.equals("是")) {
            employeeGoodFeaturesMessage[3]="是数据分析团队成员";
        }else {
            employeeBadFeaturesMessage[3]="数据分析团队成员";
        }

        if (fourteen.equals("是")) {
            employeeGoodFeaturesMessage[4]="是否初级经济师";
        }else {
            employeeBadFeaturesMessage[4]="初级经济师";
        }

        if (fortyTwo.equals("是")) {
            employeeGoodFeaturesMessage[5]="已婚";
        }else {
            employeeBadFeaturesMessage[5]="婚姻状况";
        }

        if (fortyOne.equals("是")) {
            employeeGoodFeaturesMessage[6]="有任职当前岗位以外营销岗位的工作经历(客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位)";
        }else {
            employeeBadFeaturesMessage[6]="任职当前岗位以外营销岗位的工作经历(客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位)";
        }

        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"是否有论文发表或获奖情况",
                "团队意识及协作能力",
                "是否四级烟草制品购销职业资格",
                "是否数据分析团队成员",
                "是否初级经济师",
                "是否已婚",
                "是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }

    /**
     * 综合管理员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * performance 绩效 0.25   82.984-87(0.16) 87-91(0.19) 91-95(0.22) 95-99(0.25)
     * age  年龄 0.18  22.966-31.5(0.09) 31.5-40(0.12) 40-48.5(0.15) 48.5-57(0.18)
     * four 是否艺术类兴趣小组成员 0.15 是(0.15) 否(0.08)
     * seventeen 是否四级烟草制品购销职业资格（最高级别) 0.10 是(0.10)  否(0.06)
     * eighteen 是否五级烟草制品购销职业资格（最高级别） 0.07  是(0.07)  否(0.04)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.07   是(0.07)  否(0.04)
     * thirteen 是否中级或以上经济师 0.06   是(0.06)  否(0.04)
     * eleven 营销策划及执行能力 0.04 很好较好(0.04)  一般(0.03)  较差(0.02)
     * twentyOne 是否有参加市局组织的数据分析培训经历  0.04   是(0.04)  否(0.02)
     * twelve 团队意识及协作能力  0.04   很好较好(0.04)  一般(0.03)  较差(0.02)
     * @param number
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse comprehensiveAdministratorNew(Integer number, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String four = employee.getFour();
        String seventeen = employee.getSeventeen();
        String eighteen = employee.getEighteen();
        String nineteen = employee.getNineteen();
        String thirteen = employee.getThirteen();
        String eleven = employee.getEleven();
        String twentyOne = employee.getTwentyOne();
        String twelve = employee.getTwelve();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [8];
        String [] employeeBadFeaturesMessage = new String [8];
        if (four.equals("是")) {
            employeeGoodFeaturesMessage[0]="是艺术类兴趣小组成员";
        }else {
            employeeBadFeaturesMessage[0]="艺术类兴趣小组成员";
        }

        if (seventeen.equals("是")) {
            employeeGoodFeaturesMessage[1]="有四级烟草制品购销职业资格（最高级别)";
        }else {
            employeeBadFeaturesMessage[1]="四级烟草制品购销职业资格（最高级别)";
        }

        if (eighteen.equals("是")) {
            employeeGoodFeaturesMessage[2]="有五级烟草制品购销职业资格（最高级别)";
        }else {
            employeeBadFeaturesMessage[2]="五级烟草制品购销职业资格（最高级别)";
        }

        if (nineteen.equals("是")) {
            employeeGoodFeaturesMessage[3]="具有中级以上计算机方面的资格证书";
        }else {
            employeeBadFeaturesMessage[3]="中级以上计算机方面的资格证书";
        }

        if (thirteen.equals("是")) {
            employeeGoodFeaturesMessage[4]="是中级或以上经济师";
        }else {
            employeeBadFeaturesMessage[4]="中级或以上经济师";
        }


        if (eleven.equals("很好")) {
            employeeGoodFeaturesMessage[5]="营销策划及执行能力优秀";
        }else if (eleven.equals("较好")){
            employeeGoodFeaturesMessage[5]="营销策划及执行能力优秀";
        }else {
            employeeBadFeaturesMessage[5]="营销策划及执行能力";
        }

        if (twentyOne.equals("是")) {
            employeeGoodFeaturesMessage[6]="有参加市局组织的数据分析培训经历";
        }else {
            employeeBadFeaturesMessage[6]="参加市局组织的数据分析培训经历";
        }

        if (twelve.equals("很好")) {
            employeeGoodFeaturesMessage[7]="团队意识及协作能力优秀";
        }else if (twelve.equals("较好")){
            employeeGoodFeaturesMessage[7]="团队意识及协作能力优秀";
        }else {
            employeeBadFeaturesMessage[7]="团队意识及协作能力";
        }

        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"是否艺术类兴趣小组成员",
                "是否四级烟草制品购销职业资格（最高级别)",
                "是否五级烟草制品购销职业资格（最高级别)",
                "是否具有中级以上计算机方面的资格证书",
                "是否中级或以上经济师",
                "营销策划及执行能力",
                "是否有参加市局组织的数据分析培训经历",
                "团队意识及协作能力"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeBadFeaturesMessage);
        return featuresAnalyse;
    }

    /**
     * 客户专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * degree 最高学历  0.10 大学(0.10) 大专(0.08) 高中(0.06) 中专、初级、技校(0.04)
     * age 年龄 0.05  22.966-31.5(0.02) 31.5-40(0.03) 40-48.5(0.04) 48.5-57(0.05)
     * seniority 工作年龄 0.19  -0.01-9(0.07) 9-16(0.11)  16-22(0.15) 22-(0.19)
     * performance月度绩效  0.22     82.984-87(0.10) 87-91(0.14) 91-95(0.18) 95-99(0.22)
     * seven 新媒体营销技术水平  0.08     很好 较好(0.08)  一般(0.05) 较差(0.03)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.03    是(0.03)  否(0.02)
     * thirty 是否新媒体营销团队成员  0.09  是(0.09) 否(0.05)
     * forty  是否在工作地（区/县/市）定居 0.08  是(0.08) 否(0.04)
     * forty_one 是否有任职营销以外岗位的工作经历 0.04   是(0.04) 否(0.02)
     * forty_three 生育情况 0.12  无(0.06) 一孩(0.08)  二孩(0.10)  三孩(0.12)
     * @param number
     * @param createdTime
     * @return
     */
    public FeaturesAnalyse accountSpecialistNew(Integer number, String createdTime) {
        //获取员工对应优秀标签的拥有信息
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number, createdTime);
        String degree = employee.getDegree();
        String seven = employee.getSeven();
        String nineteen = employee.getNineteen();
        String thirty = employee.getThirty();
        String forty = employee.getForty();
        String fortyOne = employee.getFortyOne();
        String fortyThree = employee.getFortyThree();
        FeaturesAnalyse featuresAnalyse = new FeaturesAnalyse();
        String [] employeeGoodFeaturesMessage = new String [7];
        String [] employeeBadFeaturesMessage = new String [7];
        if (degree.equals("大学")) {
            employeeGoodFeaturesMessage[0]="高学历人才";
        }else if (degree.equals("大专")){
            employeeGoodFeaturesMessage[0]="高学历人才";
        }else {
            employeeBadFeaturesMessage[0]="最高学历";
        }

        if (seven.equals("很好")) {
            employeeGoodFeaturesMessage[1]="新媒体营销技术水平优秀";
        }else if (seven.equals("较好")){
            employeeGoodFeaturesMessage[1]="新媒体营销技术水平优秀";
        }else {
            employeeBadFeaturesMessage[1]="新媒体营销技术水平";
        }

        if (nineteen.equals("是")) {
            employeeGoodFeaturesMessage[2]="具有中级以上计算机方面的资格证书";
        }else {
            employeeBadFeaturesMessage[2]="中级以上计算机方面的资格证书";
        }

        if (thirty.equals("是")) {
            employeeGoodFeaturesMessage[3]="是新媒体营销团队成员";
        }else {
            employeeBadFeaturesMessage[3]="新媒体营销团队成员";
        }

        if (forty.equals("是")) {
            employeeGoodFeaturesMessage[4]="在工作地（区/县/市）定居";
        }else {
            employeeBadFeaturesMessage[4]="工作地（区/县/市）定居";
        }


        if (fortyOne.equals("是")) {
            employeeGoodFeaturesMessage[5]="有任职营销以外岗位的工作经历";
        }else {
            employeeBadFeaturesMessage[5]="任职营销以外岗位的工作经历";
        }

        if (fortyThree.equals("三孩及以上")) {
            employeeGoodFeaturesMessage[6]="三孩及以上";
        }else if (fortyThree.equals("二孩")){
            employeeGoodFeaturesMessage[6]="二孩";
        }else {
            employeeBadFeaturesMessage[6]="生育情况";
        }


        //岗位优秀标签
        String [] postGoodFeaturesMessage = {"最高学历",
                "新媒体营销技术水平",
                "是否具有中级以上计算机方面的资格证书",
                "是否具有中级以上计算机方面的资格证书",
                "是否新媒体营销团队成员",
                "是否在工作地（区/县/市）定居",
                "是否有任职营销以外岗位的工作经历",
                "生育情况"};
        //将信息保存
        featuresAnalyse.setId(1);
        featuresAnalyse.setPostGoodFeaturesMessage(postGoodFeaturesMessage);
        featuresAnalyse.setEmployeeGoodFeaturesMessage(employeeGoodFeaturesMessage);
        featuresAnalyse.setEmployeeBadFeaturesMessage(employeeGoodFeaturesMessage);
        return featuresAnalyse;
    }

    /**
     * 终端专员
     * performance 绩效 0.20 82.984-87(0.08) 87-91(0.12) 91-95(0.16) 95-99(0.20)
     * one 是否有体育特长 0.11 是(0.11)  否(0.06)
     * thirty 是否新媒体营销团队成员 0.12  是(0.12)  否(0.06)
     * thirtySix 粤语掌握情况  0.16  会说(0.16)  能听不会说(0.12)  基本听不懂(0.08)
     * fortyFive 是否有论文发表或获奖情况 0.11   是(0.11)  否(0.06)
     * @return
     */
    public Map<String,Map<String,Map<String,Double>>> terminalSpecialistFeaturesMessage(String post,String createdTime){
        List<Employee> employees = employeeMapper.findByPostAndCreatedTime(post, createdTime);
        Map<String,Map<String,Double>> map = new HashMap<>();
        Map<String,Map<String,Map<String,Double>>> map0 = new HashMap<>();
        Double oneNice =0.0;
        Double oneBad =0.0;
        Double oneNiceScale =0.0;
        Double oneBadScale =0.0;
        Double thirtyNice =0.0;
        Double thirtyBad =0.0;
        Double thirtyNiceScale =0.0;
        Double thirtyBadScale =0.0;
        Double thirtySixNice =0.0;
        Double thirtySixBad =0.0;
        Double thirtySixNiceScale =0.0;
        Double thirtySixBadScale =0.0;
        Double fortyFiveNice =0.0;
        Double fortyFiveBad =0.0;
        Double fortyFiveNiceScale =0.0;
        Double fortyFiveBadScale =0.0;
        Double count1=0.0;
        Double count2=0.0;
        Double count3=0.0;
        Double count1Scale=0.0;
        Double count2Scale=0.0;
        Double count3Scale=0.0;
        for (Employee employee : employees) {
            String one = employee.getOne();
            String thirty = employee.getThirty();
            String thirtySix = employee.getThirtySix();
            String fortyFive = employee.getFortyFive();
            if (one.equals("是")){
                oneNice+=1;
            }else{
                oneBad+=1;
            }

            if (thirty.equals("是")){
                thirtyNice+=1;
            }else{
                thirtyBad+=1;
            }

            if (thirtySix.equals("会说")){
                thirtySixNice+=1;
            }else{
                thirtySixBad+=1;
            }

            if (fortyFive.equals("是")){
                fortyFiveNice+=1;
            }else{
                fortyFiveBad+=1;
            }
            oneNiceScale = oneNice/(oneNice+oneBad);
            oneBadScale = oneBad/(oneNice+oneBad);
            thirtyNiceScale = thirtyNice/(thirtyNice+thirtyBad);
            thirtyBadScale = thirtyBad/(thirtyNice+thirtyBad);
            thirtySixNiceScale = thirtySixNice/(thirtySixNice+thirtySixBad);
            thirtySixBadScale = thirtySixBad/(thirtySixNice+thirtySixBad);
            fortyFiveNiceScale = fortyFiveNice/(fortyFiveNice+fortyFiveBad);
            fortyFiveBadScale = fortyFiveBad/(fortyFiveNice+fortyFiveBad);
        }
        List<Performane> performanes = performaneMapper.findByPostAndCreatedTime(post, createdTime);
        for (Performane performane : performanes) {
            Double scores = performane.getScores();
            if (scores<=80){
                count1+=1;
            }else if (scores>80 && scores<=90){
                count2+=1;
            }else if (scores>90){
                count3+=1;
            }
        }
        count1Scale = count1/(count1+count2+count3);
        count2Scale = count2/(count1+count2+count3);
        count3Scale = count3/(count1+count2+count3);

        Map<String,Double> map1= new HashMap<>();
        Map<String,Double> map2= new HashMap<>();
        map1.put("有体育特长",oneNice);
        map1.put("没有体育特长",oneBad);
        map2.put("有体育特长",oneNiceScale);
        map2.put("没有体育特长",oneBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否有体育特长",map);

         map1= new HashMap<>();
         map2= new HashMap<>();
         map = new HashMap<>();
        map1.put("新媒体营销团队成员",thirtyNice);
        map1.put("不是新媒体营销团队成员",thirtyBad);
        map2.put("新媒体营销团队成员",thirtyNiceScale);
        map2.put("不是新媒体营销团队成员",thirtyBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否是新媒体营销团队成员",map);

         map1= new HashMap<>();
         map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("会说粤语",thirtySixNice);
        map1.put("不会说会说粤语",thirtySixBad);
        map2.put("会说粤语",thirtySixNiceScale);
        map2.put("不会说会说粤语",thirtySixBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("粤语掌握情况",map);

         map1= new HashMap<>();
         map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("有论文发表或获奖情况",fortyFiveNice);
        map1.put("没有论文发表或获奖情况",fortyFiveBad);
        map2.put("有论文发表或获奖情况",fortyFiveNiceScale);
        map2.put("没有论文发表或获奖情况",fortyFiveBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否有论文发表或获奖情况",map);

         map1= new HashMap<>();
         map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("绩效90分以上",count3);
        map1.put("绩效80-90分",count2);
        map1.put("绩效80分以下",count1);
        map2.put("绩效90分以上",count3Scale);
        map2.put("绩效80-90分",count2Scale);
        map2.put("绩效80分以下",count1Scale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("绩效分布情况",map);
        return map0;
    }

    /**
     * 市场经理,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * thirteen    0.26  是否中级或以上经济师  是(0.26)  否(0.20)
     * eighteen    0.25  是否五级烟草制品购销职业资格（最高级别）  是(0.20)  否(0.15)
     * fortyfive   0.10  是否有论文发表或获奖情况   是(0.10)  否(0.06)
     * fortyfour   0.10  是否有QC项目获奖情况    是(0.10)  否(0.06)
     * fortytwo    0.10  是否已婚   是(0.10)  否(0.06)
     */
    public Map<String,Map<String,Map<String,Double>>> marketingManagerFeaturesMessage(String post,String createdTime){
        List<Employee> employees = employeeMapper.findByPostAndCreatedTime(post, createdTime);
        Map<String,Double> map1= new HashMap<>();
        Map<String,Double> map2= new HashMap<>();
        Map<String,Map<String,Double>> map = new HashMap<>();
        Map<String,Map<String,Map<String,Double>>> map0 = new HashMap<>();
        Double thirteenNice =0.0;
        Double thirteenBad =0.0;
        Double thirteenNiceScale =0.0;
        Double thirteenBadScale =0.0;
        Double eighteenNice =0.0;
        Double eighteenBad =0.0;
        Double eighteenNiceScale =0.0;
        Double eighteenBadScale =0.0;
        Double fortyFiveNice =0.0;
        Double fortyFiveBad =0.0;
        Double fortyFiveNiceScale =0.0;
        Double fortyFiveBadScale =0.0;
        Double fortyFourNice =0.0;
        Double fortyFourBad =0.0;
        Double fortyFourNiceScale =0.0;
        Double fortyFourBadScale =0.0;
        Double fortyTwoNice =0.0;
        Double fortyTwoBad =0.0;
        Double fortyTwoNiceScale =0.0;
        Double fortyTwoBadScale =0.0;
        for (Employee employee : employees) {
            String thirteen = employee.getThirteen();
            String eighteen = employee.getEighteen();
            String fortyFive = employee.getFortyFive();
            String fortyFour = employee.getFortyFour();
            String fortyTwo = employee.getFortyTwo();
            if (thirteen.equals("是")){
                thirteenNice+=1;
            }else{
                thirteenBad+=1;
            }

            if (eighteen.equals("是")){
                eighteenNice+=1;
            }else{
                eighteenBad+=1;
            }

            if (fortyFive.equals("是")){
                fortyFiveNice+=1;
            }else{
                fortyFiveBad+=1;
            }

            if (fortyFour.equals("会说")){
                fortyFourNice+=1;
            }else{
                fortyFourBad+=1;
            }

            if (fortyTwo.equals("是")){
                fortyTwoNice+=1;
            }else{
                fortyTwoBad+=1;
            }
            thirteenNiceScale = thirteenNice/(thirteenNice+thirteenBad);
            thirteenBadScale = thirteenBad/(thirteenNice+thirteenBad);
            eighteenNiceScale = eighteenNice/(eighteenNice+eighteenBad);
            eighteenBadScale = eighteenBad/(eighteenNice+eighteenBad);
            fortyFiveNiceScale = fortyFiveNice/(fortyFiveNice+fortyFiveBad);
            fortyFiveBadScale = fortyFiveBad/(fortyFiveNice+fortyFiveBad);
            fortyFourNiceScale = fortyFourNice/(fortyFourNice+fortyFourBad);
            fortyFourBadScale = fortyFourBad/(fortyFourNice+fortyFourBad);
            fortyTwoNiceScale = fortyTwoNice/(fortyTwoNice+fortyTwoBad);
            fortyTwoBadScale = fortyTwoBad/(fortyTwoNice+fortyTwoBad);
        }

        map1= new HashMap<>();
        map2= new HashMap<>();
        map1.put("是中级或以上经济师",thirteenNice);
        map1.put("不是中级或以上经济师",thirteenBad);
        map2.put("是中级或以上经济师",thirteenNiceScale);
        map2.put("不是中级或以上经济师",thirteenBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否中级或以上经济师",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是五级烟草制品购销职业资格（最高级别）",eighteenNice);
        map1.put("不是五级烟草制品购销职业资格（最高级别）",eighteenBad);
        map2.put("是五级烟草制品购销职业资格（最高级别）",eighteenNiceScale);
        map2.put("不是五级烟草制品购销职业资格（最高级别）",eighteenBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否五级烟草制品购销职业资格（最高级别）",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是有论文发表或获奖情况",fortyFiveNice);
        map1.put("不是有论文发表或获奖情况",fortyFiveBad);
        map2.put("是有论文发表或获奖情况",fortyFiveNiceScale);
        map2.put("不是有论文发表或获奖情况",fortyFiveBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否有论文发表或获奖情况",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是有QC项目获奖情况",fortyFourNice);
        map1.put("不是有QC项目获奖情况",fortyFourBad);
        map2.put("是有QC项目获奖情况",fortyFourNiceScale);
        map2.put("不是有QC项目获奖情况",fortyFourBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否有QC项目获奖情况",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("已婚",fortyTwoNice);
        map1.put("未婚",fortyTwoBad);
        map2.put("已婚",fortyTwoNiceScale);
        map2.put("未婚",fortyTwoBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否已婚",map);
        return map0;
    }
    /**
     * 信息专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * age 年龄  0.17     22.966-31.5(0.11) 31.5-40(0.13) 40-48.5(0.15) 48.5-57(0.17)
     * fortyFive  是否有论文发表或获奖情况 0.14    是(0.14)  否(0.10)
     * seniority 工龄 0.11   -0.01-9(0.05) 9-16(0.07)  16-22(0.09) 22-(0.11)
     * twelve  团队意识及协作能力  0.10   很好较好(0.10)  一般(0.08)  较差(0.06)
     * performance  绩效  0.13  82.984-87(0.07) 87-91(0.09) 91-95(0.11) 95-99(0.13)
     */
    public Map<String,Map<String,Map<String,Double>>> informationCommissionerFeaturesMessage(String post,String createdTime){
        List<Employee> employees = employeeMapper.findByPostAndCreatedTime(post, createdTime);
        Map<String,Double> map1= new HashMap<>();
        Map<String,Double> map2= new HashMap<>();
        Map<String,Map<String,Double>> map = new HashMap<>();
        Map<String,Map<String,Map<String,Double>>> map0 = new HashMap<>();
        Double twelveNice =0.0;
        Double twelveBad =0.0;
        Double twelveNiceScale =0.0;
        Double twelveBadScale =0.0;
        Double fortyFiveNice =0.0;
        Double fortyFiveBad =0.0;
        Double fortyFiveNiceScale =0.0;
        Double fortyFiveBadScale =0.0;
        Double ageCount1 = 0.0;
        Double ageCount2 = 0.0;
        Double ageCount3 = 0.0;
        Double ageScale1 = 0.0;
        Double ageScale2 = 0.0;
        Double ageScale3 = 0.0;
        Double scoresCount1 = 0.0;
        Double scoresCount2 = 0.0;
        Double scoresCount3 = 0.0;
        Double scoresScale1 = 0.0;
        Double scoresScale2 = 0.0;
        Double scoresScale3  = 0.0;
        Double seniorityCount1 = 0.0;
        Double seniorityCount2 = 0.0;
        Double seniorityCount3 = 0.0;
        Double seniorityScale1 = 0.0;
        Double seniorityScale2 = 0.0;
        Double seniorityScale3 = 0.0;
        for (Employee employee : employees) {
            Integer age = employee.getAge();
            Integer seniority = employee.getSeniority();
            String twelve = employee.getTwelve();
            String fortyFive = employee.getFortyFive();

            if (age<=30){
                ageCount1+=1;
            }else if (age >30 && age <=40){
                ageCount2+=1;
            }else if (age>40){
                ageCount3+=1;
            }

            if (seniority<=5){
                seniorityCount1+=1;
            }else if (seniority >5 && seniority <=10){
                seniorityCount2+=1;
            }else if (seniority>10){
                seniorityCount3+=1;
            }

            if (twelve.equals("很好")){
                twelveNice+=1;
            }else if (twelve.equals("较好")){
                twelveNice+=1;
            }else if (twelve.equals("一般")){
                twelveBad+=1;
            }else {
                twelveBad+=1;
            }

            if (fortyFive.equals("是")){
                fortyFiveNice+=1;
            }else{
                fortyFiveBad+=1;
            }
            ageScale1 = ageCount1/(ageCount1+ageCount2+ageCount3);
            ageScale2 = ageCount2/(ageCount1+ageCount2+ageCount3);
            ageScale3 = ageCount3/(ageCount1+ageCount2+ageCount3);
            seniorityScale1 = seniorityCount1/(seniorityCount1+seniorityCount2+seniorityCount3);
            seniorityScale2 = seniorityCount2/(seniorityCount1+seniorityCount2+seniorityCount3);
            seniorityScale3 = seniorityCount3/(seniorityCount1+seniorityCount2+seniorityCount3);
            twelveNiceScale = twelveNice/(twelveNice+twelveBad);
            twelveBadScale = twelveBad/(twelveNice+twelveBad);
            fortyFiveNiceScale = fortyFiveNice/(fortyFiveNice+fortyFiveBad);
            fortyFiveBadScale = fortyFiveBad/(fortyFiveNice+fortyFiveBad);
        }
        List<Performane> performanes = performaneMapper.findByPostAndCreatedTime(post, createdTime);
        for (Performane performane : performanes) {
            Double scores = performane.getScores();
            if (scores<=80){
                scoresCount1+=1;
            }else if (scores>80 && scores<=90){
                scoresCount2+=1;
            }else if (scores>90){
                scoresCount3+=1;
            }
        }
        scoresScale1 = scoresCount1/(scoresCount1+scoresCount2+scoresCount3);
        scoresScale2 = scoresCount2/(scoresCount1+scoresCount2+scoresCount3);
        scoresScale3 = scoresCount3/(scoresCount1+scoresCount2+scoresCount3);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("30岁及以下",ageCount1);
        map1.put("30岁-40岁",ageCount2);
        map1.put("40岁以上",ageCount3);
        map2.put("30岁及以下",ageScale1);
        map2.put("30岁-40岁",ageScale2);
        map2.put("40岁以上",ageScale3);
        map.put("scale",map1);
        map.put("count",map2);
        map0.put("年龄分布",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("5年以内",seniorityCount1);
        map1.put("5年-10年",seniorityCount2);
        map1.put("十年以上",seniorityCount3);
        map2.put("5年以内",seniorityScale1);
        map2.put("5年-10年",seniorityScale2);
        map2.put("十年以上",seniorityScale3);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("工龄分布情况",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("团队意识及协作能力优秀",twelveNice);
        map1.put("团队意识及协作能力一般",twelveBad);
        map2.put("团队意识及协作能力优秀",twelveNiceScale);
        map2.put("团队意识及协作能力一般",twelveBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("团队意识及协作能力",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("有论文发表或获奖情况",fortyFiveNice);
        map1.put("没有论文发表或获奖情况",fortyFiveBad);
        map2.put("有论文发表或获奖情况",fortyFiveNiceScale);
        map2.put("没有论文发表或获奖情况",fortyFiveBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否有论文发表或获奖情况",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("绩效90分以上",scoresCount3);
        map1.put("绩效80-90分",scoresCount2);
        map1.put("绩效80分以下",scoresCount1);
        map2.put("绩效90分以上",scoresScale3);
        map2.put("绩效80-90分",scoresScale2);
        map2.put("绩效80分以下",scoresScale1);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("绩效分布情况",map);

        return map0;
    }

    /**
     * 综合管理员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * performance 绩效 0.25   82.984-87(0.16) 87-91(0.19) 91-95(0.22) 95-99(0.25)
     * age  年龄 0.18  22.966-31.5(0.09) 31.5-40(0.12) 40-48.5(0.15) 48.5-57(0.18)
     * four 是否艺术类兴趣小组成员 0.15 是(0.15) 否(0.08)
     * seventeen 是否四级烟草制品购销职业资格（最高级别) 0.10 是(0.10)  否(0.06)
     * eighteen 是否五级烟草制品购销职业资格（最高级别） 0.07  是(0.07)  否(0.04)
     */
    public Map<String,Map<String,Map<String,Double>>> comprehensiveAdministratorFeaturesMessage(String post,String createdTime){
        List<Employee> employees = employeeMapper.findByPostAndCreatedTime(post, createdTime);
        Map<String,Double> map1= new HashMap<>();
        Map<String,Double> map2= new HashMap<>();
        Map<String,Map<String,Double>> map = new HashMap<>();
        Map<String,Map<String,Map<String,Double>>> map0 = new HashMap<>();
        Double fourNice =0.0;
        Double fourBad =0.0;
        Double fourNiceScale =0.0;
        Double fourBadScale =0.0;
        Double seventeenNice =0.0;
        Double seventeenBad =0.0;
        Double seventeenNiceScale =0.0;
        Double seventeenBadScale =0.0;
        Double eighteenNice =0.0;
        Double eighteenBad =0.0;
        Double eighteenNiceScale =0.0;
        Double eighteenBadScale =0.0;
        Double ageCount1 = 0.0;
        Double ageCount2 = 0.0;
        Double ageCount3 = 0.0;
        Double ageScale1 = 0.0;
        Double ageScale2 = 0.0;
        Double ageScale3 = 0.0;
        Double scoresCount1 = 0.0;
        Double scoresCount2 = 0.0;
        Double scoresCount3 = 0.0;
        Double scoresScale1 = 0.0;
        Double scoresScale2 = 0.0;
        Double scoresScale3  = 0.0;

        for (Employee employee : employees) {
            Integer age = employee.getAge();
            String four = employee.getFour();
            String seventeen = employee.getSeventeen();
            String eighteen = employee.getEighteen();

            if (age<=30){
                ageCount1+=1;
            }else if (age >30 && age <=40){
                ageCount2+=1;
            }else {
                ageCount3+=1;
            }

            if (four.equals("是")){
                fourNice+=1;
            }else{
                fourBad+=1;
            }

            if (seventeen.equals("是")){
                seventeenNice+=1;
            }else{
                seventeenBad+=1;
            }

            if (eighteen.equals("是")){
                eighteenNice+=1;
            }else{
                eighteenBad+=1;
            }

            ageScale1 = ageCount1/(ageCount1+ageCount2+ageCount3);
            ageScale2 = ageCount2/(ageCount1+ageCount2+ageCount3);
            ageScale3 = ageCount3/(ageCount1+ageCount2+ageCount3);
            fourNiceScale = fourNice/(fourNice+fourBad);
            fourBadScale = fourBad/(fourNice+fourBad);
            seventeenNiceScale = seventeenNice/(seventeenNice+seventeenBad);
            seventeenBadScale = seventeenBad/(seventeenNice+seventeenBad);
            eighteenNiceScale = eighteenNice/(eighteenNice+eighteenBad);
            eighteenBadScale = eighteenBad/(eighteenNice+eighteenBad);
        }
        List<Performane> performanes = performaneMapper.findByPostAndCreatedTime(post, createdTime);
        for (Performane performane : performanes) {
            Double scores = performane.getScores();
            if (scores<=80){
                scoresCount1+=1;
            }else if (scores>80 && scores<=90){
                scoresCount2+=1;
            }else if (scores>90){
                scoresCount3+=1;
            }
        }
        scoresScale1 = scoresCount1/(scoresCount1+scoresCount2+scoresCount3);
        scoresScale2 = scoresCount2/(scoresCount1+scoresCount2+scoresCount3);
        scoresScale3 = scoresCount3/(scoresCount1+scoresCount2+scoresCount3);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("30岁及以下",ageCount1);
        map1.put("30岁-40岁",ageCount2);
        map1.put("40岁以上",ageCount3);
        map2.put("30岁及以下",ageScale1);
        map2.put("30岁-40岁",ageScale2);
        map2.put("40岁以上",ageScale3);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("年龄分布",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是艺术类兴趣小组成员",fourNice);
        map1.put("不是艺术类兴趣小组成员",fourBad);
        map2.put("是艺术类兴趣小组成员",fourNiceScale);
        map2.put("不是艺术类兴趣小组成员",fourBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否艺术类兴趣小组成员",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是四级烟草制品购销职业资格（最高级别)",seventeenNice);
        map1.put("不是四级烟草制品购销职业资格（最高级别)",seventeenBad);
        map2.put("是四级烟草制品购销职业资格（最高级别)",seventeenNiceScale);
        map2.put("不是四级烟草制品购销职业资格（最高级别)",seventeenBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否四级烟草制品购销职业资格（最高级别)",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是五级烟草制品购销职业资格（最高级别）",eighteenNice);
        map1.put("不是五级烟草制品购销职业资格（最高级别）",eighteenBad);
        map2.put("是五级烟草制品购销职业资格（最高级别）",eighteenNiceScale);
        map2.put("不是五级烟草制品购销职业资格（最高级别）",eighteenBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否五级烟草制品购销职业资格（最高级别）",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("绩效90分以上",scoresCount3);
        map1.put("绩效80-90分",scoresCount2);
        map1.put("绩效80分以下",scoresCount1);
        map2.put("绩效90分以上",scoresScale3);
        map2.put("绩效80-90分",scoresScale2);
        map2.put("绩效80分以下",scoresScale1);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("绩效分布情况",map);

        return map0;
    }

    /**
     * 客户专员,传入员工姓名和日期，算出该员工对该岗位优秀标签的拥有情况，和绩效以及匹配系数
     * degree 最高学历  0.10 大学(0.10) 大专(0.08) 高中(0.06) 中专、初级、技校(0.04)
     * seniority 工作年龄 0.19  -0.01-9(0.07) 9-16(0.11)  16-22(0.15) 22-(0.19)
     * performance 月度绩效  0.22     82.984-87(0.10) 87-91(0.14) 91-95(0.18) 95-99(0.22)
     * thirty 是否新媒体营销团队成员  0.09  是(0.09) 否(0.05)
     * forty_three 生育情况 0.12  无(0.06) 一孩(0.08)  二孩(0.10)  三孩(0.12)
     */
    public Map<String,Map<String,Map<String,Double>>> accountSpecialistFeaturesMessage(String post,String createdTime){
        List<Employee> employees = employeeMapper.findByPostAndCreatedTime(post, createdTime);
        Map<String,Double> map1= new HashMap<>();
        Map<String,Double> map2= new HashMap<>();
        Map<String,Map<String,Double>> map = new HashMap<>();
        Map<String,Map<String,Map<String,Double>>> map0 = new HashMap<>();
        Double thirtyNice =0.0;
        Double thirtyBad =0.0;
        Double thirtyNiceScale =0.0;
        Double thirtyBadScale =0.0;
        Double fortyThreeNice =0.0;
        Double fortyThreeBad =0.0;
        Double fortyThreeNiceScale =0.0;
        Double fortyThreeBadScale =0.0;
        Double scoresCount1 = 0.0;
        Double scoresCount2 = 0.0;
        Double scoresCount3 = 0.0;
        Double scoresScale1 = 0.0;
        Double scoresScale2 = 0.0;
        Double scoresScale3  = 0.0;
        Double seniorityCount1 = 0.0;
        Double seniorityCount2 = 0.0;
        Double seniorityCount3 = 0.0;
        Double seniorityScale1 = 0.0;
        Double seniorityScale2 = 0.0;
        Double seniorityScale3  = 0.0;
        Double degreeCount1 = 0.0;
        Double degreeCount2 = 0.0;
        Double degreeCount3 = 0.0;
        Double degreeScale1 = 0.0;
        Double degreeScale2 = 0.0;
        Double degreeScale3  = 0.0;

        for (Employee employee : employees) {
            String degree = employee.getDegree();
            Integer seniority = employee.getSeniority();
            String thirty = employee.getThirty();
            String fortyThree = employee.getFortyThree();

            if (seniority<=5){
                seniorityCount1+=1;
            }else if (seniority >5 && seniority <=10){
                seniorityCount2+=1;
            }else {
                seniorityCount3+=1;
            }

            if (degree.equals("大学")){
                degreeCount1+=1;
            }else if (degree.equals("大专")){
                degreeCount2+=1;
            }else {
                degreeCount3+=1;
            }

            if (thirty.equals("是")){
                thirtyNice+=1;
            }else{
                thirtyBad+=1;
            }

            if (fortyThree.equals("三孩及以上")){
                fortyThreeNice+=1;
            }else{
                fortyThreeBad+=1;
            }

            seniorityScale1 = seniorityCount1/(seniorityCount1+seniorityCount2+seniorityCount3);
            seniorityScale2 = seniorityCount2/(seniorityCount1+seniorityCount2+seniorityCount3);
            seniorityScale3 = seniorityCount3/(seniorityCount1+seniorityCount2+seniorityCount3);
            degreeScale1 = degreeCount1/(degreeCount1+degreeCount2+degreeCount3);
            degreeScale2 = degreeCount1/(degreeCount1+degreeCount2+degreeCount3);
            degreeScale3 = degreeCount1/(degreeCount1+degreeCount2+degreeCount3);
            thirtyNiceScale = thirtyNice/(thirtyNice+thirtyBad);
            thirtyBadScale = thirtyBad/(thirtyNice+thirtyBad);
            fortyThreeNiceScale = fortyThreeNice/(fortyThreeNice+fortyThreeBad);
            fortyThreeBadScale = fortyThreeBad/(fortyThreeNice+fortyThreeBad);
        }
        List<Performane> performanes = performaneMapper.findByPostAndCreatedTime(post, createdTime);
        for (Performane performane : performanes) {
            Double scores = performane.getScores();
            if (scores<=80){
                scoresCount1+=1;
            }else if (scores>80 && scores<=90){
                scoresCount2+=1;
            }else if (scores>90){
                scoresCount3+=1;
            }
        }
        scoresScale1 = scoresCount1/(scoresCount1+scoresCount2+scoresCount3);
        scoresScale2 = scoresCount2/(scoresCount1+scoresCount2+scoresCount3);
        scoresScale3 = scoresCount3/(scoresCount1+scoresCount2+scoresCount3);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("5年以内",seniorityCount1);
        map1.put("5年-10年",seniorityCount2);
        map1.put("十年以上",seniorityCount3);
        map2.put("5年以内",seniorityScale1);
        map2.put("5年-10年",seniorityScale2);
        map2.put("十年以上",seniorityScale3);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("工龄分布情况",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("大学",degreeCount1);
        map1.put("大专",degreeCount2);
        map1.put("高中及以下",degreeCount3);
        map2.put("大学",degreeScale1);
        map2.put("大专",degreeScale2);
        map2.put("高中及以下",degreeScale3);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("最高学历",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("是新媒体营销团队成员",thirtyNice);
        map1.put("不是新媒体营销团队成员",thirtyBad);
        map2.put("是新媒体营销团队成员",thirtyNiceScale);
        map2.put("不是新媒体营销团队成员",thirtyBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("是否新媒体营销团队成员",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("三孩及以上",fortyThreeNice);
        map1.put("二孩及以下",fortyThreeBad);
        map2.put("三孩及以上",fortyThreeNiceScale);
        map2.put("二孩及以下",fortyThreeBadScale);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("生育情况",map);

        map1= new HashMap<>();
        map2= new HashMap<>();
        map = new HashMap<>();
        map1.put("绩效90分以上",scoresCount3);
        map1.put("绩效80-90分",scoresCount2);
        map1.put("绩效80分以下",scoresCount1);
        map2.put("绩效90分以上",scoresScale3);
        map2.put("绩效80-90分",scoresScale2);
        map2.put("绩效80分以下",scoresScale1);
        map.put("scale",map2);
        map.put("count",map1);
        map0.put("绩效分布情况",map);

        return map0;
    }
}

package com.cy.rs.service.impl;

import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Label;
import com.cy.rs.entity.Performane;
import com.cy.rs.entity.PostAnalyse;
import com.cy.rs.mapper.EmployeeMapper;
import com.cy.rs.mapper.PerformaneMapper;
import com.cy.rs.service.IPerformaneService;
import com.cy.rs.service.ex.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerformaneServiceImpl implements IPerformaneService {


    @Autowired(required = false)
    @Qualifier("performaneMapper")
    private PerformaneMapper performaneMapper;

    @Autowired(required = false)
    @Qualifier("employeeMapper")
    private EmployeeMapper employeeMapper;

    /**
     * 员工每月绩效新增方法
     * @param performane 员工的数据对象
     */
    @Override
    public Performane insert(Performane performane) {
        //获取员工编号和日期，判断是否存在数据，防止数据重复
        String createdTime = performane.getCreatedTime();
        Integer number = performane.getNumber();
        Performane result = performaneMapper.findNumberAndCreatedTime(number, createdTime);
        if(result != null){
            throw new FactorDuplicatedException("此员工该月份绩效已存在");
        }
        if (result == null){
            createdTime="2023-05";
        }
        //根据员工编号和日期去employee表中查询信息，根据查询的信息计算匹配系数
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number,createdTime);
        Double scores = performane.getScores();
        String post = employee.getPost();
        double factor=0.0;
        if(post.equals("终端专员")){
             factor = terminalSpecialist(employee, scores);
        }else if(post.equals("市场经理")){
             factor = marketingManager(employee, scores);
        }else if(post.equals("信息专员")){
             factor = informationCommissioner(employee, scores);
        }else if(post.equals("综合管理员")){
             factor = comprehensiveAdministrator(employee, scores);
        }else if(post.equals("客户专员")){
             factor = accountSpecialist(employee, scores);
        }
        performane.setFactor(factor);
        System.out.println(performane);
        Integer rows = performaneMapper.insert(performane);
        if(rows != 1){
            throw new InsertException("新增员工该月份绩效时发生了未知的错误");
        }
        Performane newResult = performaneMapper.findNumberAndCreatedTime(number, createdTime);
        return newResult;
    }

    /**
     * 修改员工绩效的方法 要求前端表单能修改的字段只有绩效,id是必须传入的数据
     * @param performane 员工的绩效信息
     */
    @Override
    public Performane update(Performane performane) {
        //修改绩效信息，能修改的只有此条记录的绩效
        //获取员工编号和日期，判断是否存在数据，防止数据丢失
        String createdTime = performane.getCreatedTime();
        Integer number = performane.getNumber();
        Performane result = performaneMapper.findNumberAndCreatedTime(number, createdTime);
        if(result == null){
            throw new FactorNotFoundException("此员工该月份绩效已丢失，请联系管理员处理");
        }
        //根据员工编号和日期去employee表中查询信息，根据查询的信息计算匹配系数
        Employee employee = employeeMapper.findByNumberAndCreatedTime(number,createdTime);
        Double scores = performane.getScores();
        String post = employee.getPost();
        double factor=0.0;
        if(post.equals("终端专员")){
            factor = terminalSpecialist(employee, scores);
        }else if(post.equals("市场经理")){
            factor = marketingManager(employee, scores);
        }else if(post.equals("信息专员")){
            factor = informationCommissioner(employee, scores);
        }else if(post.equals("综合管理员")){
            factor = comprehensiveAdministrator(employee, scores);
        }else if(post.equals("客户专员")){
            factor = accountSpecialist(employee, scores);
        }
        performane.setFactor(factor);
        System.out.println(performane);
        Integer rows = performaneMapper.update(performane);
        if(rows != 1){
            throw new UpdateExcetion("修改员工该月份绩效时发生了未知的错误");
        }
        Performane newResult = performaneMapper.findNumberAndCreatedTime(number, createdTime);
        return newResult;
    }

    /**
     * 根据id删除员工绩效信息，返回受影响的行数
     * @param id id主键
     */
    @Override
    public void deleteById(Integer id) {
        Performane result = performaneMapper.findById(id);
        if(result ==null){
            throw new FactorNotFoundException("此员工该月份绩效已丢失，请联系管理员处理");
        }
        Integer rows = performaneMapper.deleteById(id);
        if(rows != 1){
            throw new DeleteExcetion("删除员工该月份绩效时发生了未知的错误");
        }
    }
    /**
     * 查询所有绩效信息
     * @return
     */
    @Override
    public List<Performane> select() {
        List<Performane> result = performaneMapper.select();
        return result;
    }

    /**
     * 根据员工岗位和姓名或者员工岗位和编号查询信息.注意姓名和编号的数据类型是不一样的
     * @param post 岗位
     * @param condition 员工姓名或编号
     * @return
     */
    @Override
    public List<Performane> findByPostAndCondition(String post, Object condition) {
        List<Performane> result = null;
        System.out.println(condition);
        if (condition==null){
             result = performaneMapper.findByPost(post);
        }
        if(condition instanceof Integer){
             result = performaneMapper.findByPostAndNumber(post, (Integer) condition);
        }else if (condition instanceof String){
             result = performaneMapper.findByPostAndName(post, (String) condition);
        }
        return result;
    }

    /**
     * 根据岗位去查询员工绩效信息
     * @param post
     * @return
     */
    public List<Performane> findByPost(String post){
        List<Performane> result = performaneMapper.findByPost(post);
        return result;
    }

    /**
     * 根据员工编号去employee表中查询信息
     * @param number
     * @return
     */
    @Override
    public List<Employee> findByNumber(Integer number) {
        List<Employee> result = employeeMapper.findByNumber2(number);
        return result;
    }

    /**
     * 根据员工姓名去employee表中查询信息
     * @param name
     * @return
     */
    @Override
    public List<Employee> findByName(String name) {
        List<Employee> result = employeeMapper.findByName(name);
        return result;
    }

    /**
     * 根据员工编号和日期去employee表中查询信息
     * @param number
     * @param createdTime
     * @return
     */
    @Override
    public Employee findByNumberAndCreatedTime(Integer number, String createdTime) {
        Employee result = employeeMapper.findByNumberAndCreatedTime2(number, createdTime);
        return result;
    }

    /**
     * 根据员工姓名和日期去employee表中查询信息
     * @param name
     * @param createdTime
     * @return
     */
    @Override
    public Employee findByNameAndCreatedTime(String name, String createdTime) {
        Employee result = employeeMapper.findByNameAndCreatedTime(name, createdTime);
        return result;
    }
    @Override
    public List<Employee> findByNameAndCreatedTimeNew(String name, String createdTime) {
        List<Employee> result = employeeMapper.findByNameAndCreatedTimeNew(name, createdTime);
        return result;
    }
    /**
     * 用于人岗匹配页面，岗位画像右侧也是用这个
     * 根据岗位和日期查询该岗位匹配系数，按降序排序,
     * @param post
     * @param createdTime
     * @return
     */
    @Override
    public List<Performane> findByPostAndCreatedTime(String post, String createdTime) {
        List<Performane> result = performaneMapper.findByPostAndCreatedTime(post, createdTime);
        return result;
    }

    /**
     * 用于数据分析页面，获取员工数，岗位数，总体匹配系数，平均年龄
     * @param createdTime
     * @return
     */
    @Override
    public Map<String,Double> dataAnalysis1(String createdTime){
        Map<String,Double> map = new HashMap<>();
        Double number = performaneMapper.findCountNumber(createdTime);
        Double countPost = performaneMapper.findCountPost(createdTime);
        Double avgFactor = performaneMapper.findAvgFactor(createdTime);
        Double avgAge = employeeMapper.findAvgAge(createdTime);
        map.put("员工总数",number);
        map.put("岗位总数",countPost);
        map.put("总体匹配系数",avgFactor);
        map.put("平均年龄",avgAge);
        return map;
    }


    /**
     * 用于数据分析页面，各岗位人数以及匹配系数
     * @param createdTime
     * @return
     */
    @Override
    public Map<String,Double> dataAnalysis2(String createdTime){
        Map<String,Double> map = new HashMap<>();
        Double Number1 = performaneMapper.findCountNumberByPost(createdTime, "终端专员");
        Double Number2 = performaneMapper.findCountNumberByPost(createdTime, "市场经理");
        Double Number3 = performaneMapper.findCountNumberByPost(createdTime, "信息专员");
        Double Number4 = performaneMapper.findCountNumberByPost(createdTime, "综合管理员");
        Double Number5 = performaneMapper.findCountNumberByPost(createdTime, "客户专员");
        Double Factor1 = performaneMapper.findAvgFactorByPost(createdTime, "终端专员");
        Double Factor2 = performaneMapper.findAvgFactorByPost(createdTime, "市场经理");
        Double Factor3 = performaneMapper.findAvgFactorByPost(createdTime, "信息专员");
        Double Factor4 = performaneMapper.findAvgFactorByPost(createdTime, "综合管理员");
        Double Factor5 = performaneMapper.findAvgFactorByPost(createdTime, "客户专员");
        map.put("终端专员人数",Number1);
        map.put("市场经理人数",Number2);
        map.put("信息专员人数",Number3);
        map.put("综合管理员人数",Number4);
        map.put("客户专员人数",Number5);
        map.put("终端专员平均匹配系数",Factor1);
        map.put("市场经理平均匹配系数",Factor2);
        map.put("信息专员平均匹配系数",Factor3);
        map.put("综合管理员平均匹配系数",Factor4);
        map.put("客户专员平均匹配系数",Factor5);
        return map;
    }

    /**
     * 用于总体画像页面
     */
    @Override
    public PostAnalyse overallPortrait(String createdTime,String unit, String post,String sex, Integer minAge,Integer maxAge, String degree){
        List<Integer> numbers = employeeMapper.overallPortrait(createdTime,unit,post,sex,minAge,maxAge,degree);
        Integer terminalSpecialistPostCount = 0;
        Integer accountSpecialistPostCount= 0;
        Integer comprehensiveAdministratorPostCount= 0;
        Integer informationCommissionerPostCount= 0;
        Integer marketingManagerPostCount= 0;
        Double terminalSpecialistFactor = 0.0;
        Double accountSpecialistFactor = 0.0;
        Double comprehensiveAdministratorFactor = 0.0;
        Double informationCommissionerFactor = 0.0;
        Double marketingManagerFactor = 0.0;
        Double terminalSpecialistFactorAvg = 0.0;
        Double accountSpecialistFactorAvg = 0.0;
        Double comprehensiveAdministratorFactorAvg = 0.0;
        Double informationCommissionerFactorAvg = 0.0;
        Double marketingManagerFactorAvg = 0.0;
        Double terminalSpecialistFactorMax = 0.0;
        Double accountSpecialistFactorMax = 0.0;
        Double comprehensiveAdministratorFactorMax = 0.0;
        Double informationCommissionerFactorMax = 0.0;
        Double marketingManagerFactorMax = 0.0;
        Double terminalSpecialistFactorMin = 0.0;
        Double accountSpecialistFactorMin = 0.0;
        Double comprehensiveAdministratorFactorMin = 0.0;
        Double informationCommissionerFactorMin = 0.0;
        Double marketingManagerFactorMin = 0.0;
        for (Integer number : numbers) {
            Performane performane = performaneMapper.findNumberAndCreatedTime(number, createdTime);
            //查询各岗位人数
             terminalSpecialistPostCount = 0;
             accountSpecialistPostCount = 0;
             comprehensiveAdministratorPostCount = 0;
             informationCommissionerPostCount = 0;
             marketingManagerPostCount = 0;
            String posts = performane.getPost();
            if (posts.equals("终端专员")){
                terminalSpecialistPostCount+=1;
            }else if (posts.equals("客户专员")){
                accountSpecialistPostCount+=1;
            }else if (posts.equals("综合管理员")){
                comprehensiveAdministratorPostCount+=1;
            }else if (posts.equals("信息专员")){
                informationCommissionerPostCount+=1;
            }else{
                marketingManagerPostCount+=1;
            }
             terminalSpecialistFactor = 0.0;
             accountSpecialistFactor = 0.0;
             comprehensiveAdministratorFactor = 0.0;
             informationCommissionerFactor = 0.0;
             marketingManagerFactor = 0.0;
             terminalSpecialistFactorAvg = 0.0;
             accountSpecialistFactorAvg = 0.0;
             comprehensiveAdministratorFactorAvg = 0.0;
             informationCommissionerFactorAvg = 0.0;
             marketingManagerFactorAvg = 0.0;
             terminalSpecialistFactorMax = 0.0;
             accountSpecialistFactorMax = 0.0;
             comprehensiveAdministratorFactorMax = 0.0;
             informationCommissionerFactorMax = 0.0;
             marketingManagerFactorMax = 0.0;
             terminalSpecialistFactorMin = 100.0;
             accountSpecialistFactorMin = 100.0;
             comprehensiveAdministratorFactorMin = 100.0;
             informationCommissionerFactorMin = 100.0;
             marketingManagerFactorMin = 100.0;
            Double factors = performane.getFactor();
            //求最高最低匹配系数
            if (posts.equals("终端专员")){
                terminalSpecialistFactor+=factors;
                if (terminalSpecialistFactorMax<factors){
                    terminalSpecialistFactorMax=factors;
                }
                if (terminalSpecialistFactorMin>factors){
                    terminalSpecialistFactorMin=factors;
                }
            }else if (posts.equals("客户专员")){
                accountSpecialistFactor+=factors;
                if (accountSpecialistFactorMax<factors){
                    accountSpecialistFactorMax=factors;
                }
                if (accountSpecialistFactorMin>factors){
                    accountSpecialistFactorMin=factors;
                }
            }else if (posts.equals("综合管理员")){
                comprehensiveAdministratorFactor+=factors;
                if (comprehensiveAdministratorFactorMax<factors){
                    comprehensiveAdministratorFactorMax=factors;
                }
                if (comprehensiveAdministratorFactorMin>factors){
                    comprehensiveAdministratorFactorMin=factors;
                }
            }else if (posts.equals("信息专员")){
                informationCommissionerFactor+=factors;
                if (informationCommissionerFactorMax<factors){
                    informationCommissionerFactorMax=factors;
                }
                if (informationCommissionerFactorMin>factors){
                    informationCommissionerFactorMin=factors;
                }
            }else{
                marketingManagerFactor+=factors;
                if (marketingManagerFactorMax<factors){
                    marketingManagerFactorMax=factors;
                }
                if (marketingManagerFactorMin>factors){
                    marketingManagerFactorMin=factors;
                }
            }
            //求人岗匹配系数
            if (terminalSpecialistPostCount!=0){
                terminalSpecialistFactorAvg=terminalSpecialistFactor/terminalSpecialistPostCount;
            }
            if (accountSpecialistPostCount!=0){
                accountSpecialistFactorAvg=accountSpecialistFactor/accountSpecialistPostCount;
            }
            if (comprehensiveAdministratorPostCount!=0){
                comprehensiveAdministratorFactorAvg=comprehensiveAdministratorFactor/comprehensiveAdministratorPostCount;
            }
            if (informationCommissionerPostCount!=0){
                informationCommissionerFactorAvg=informationCommissionerFactor/informationCommissionerPostCount;
            }
            if (marketingManagerPostCount!=0){
                marketingManagerFactorAvg=marketingManagerFactor/marketingManagerPostCount;
            }
        }
        PostAnalyse postAnalyse = new PostAnalyse();

            if (terminalSpecialistPostCount!=0){
                postAnalyse.setId(1);
                postAnalyse.setPost("终端专员");
                postAnalyse.setCount(terminalSpecialistPostCount);
                postAnalyse.setPeoplePostFactor(terminalSpecialistFactorAvg);
                postAnalyse.setMaxFactor(terminalSpecialistFactorMax);
                postAnalyse.setMinFactor(terminalSpecialistFactorMin);
            }
        if (accountSpecialistPostCount!=0){
            postAnalyse.setId(2);
            postAnalyse.setPost("客户专员");
            postAnalyse.setCount(accountSpecialistPostCount);
            postAnalyse.setPeoplePostFactor(accountSpecialistFactorAvg);
            postAnalyse.setMaxFactor(accountSpecialistFactorMax);
            postAnalyse.setMinFactor(accountSpecialistFactorMin);
        }
        if (terminalSpecialistPostCount!=0){
            postAnalyse.setId(3);
            postAnalyse.setPost("综合管理员");
            postAnalyse.setCount(comprehensiveAdministratorPostCount);
            postAnalyse.setPeoplePostFactor(comprehensiveAdministratorFactorAvg);
            postAnalyse.setMaxFactor(comprehensiveAdministratorFactorMax);
            postAnalyse.setMinFactor(comprehensiveAdministratorFactorMin);
        }
        if (terminalSpecialistPostCount!=0){
            postAnalyse.setId(4);
            postAnalyse.setPost("信息专员");
            postAnalyse.setCount(informationCommissionerPostCount);
            postAnalyse.setPeoplePostFactor(informationCommissionerFactorAvg);
            postAnalyse.setMaxFactor(informationCommissionerFactorMax);
            postAnalyse.setMinFactor(informationCommissionerFactorMin);
        }
        if (terminalSpecialistPostCount!=0){
            postAnalyse.setId(5);
            postAnalyse.setPost("市场经理");
            postAnalyse.setCount(marketingManagerPostCount);
            postAnalyse.setPeoplePostFactor(marketingManagerFactorAvg);
            postAnalyse.setMaxFactor(marketingManagerFactorMax);
            postAnalyse.setMinFactor(marketingManagerFactorMin);
        }
    return postAnalyse;

    }

    /**
     * 从excel表格中导入数据到数据库中
     * @param file
     * @return
     * @throws IOException
     */
    public Integer addToDatabase(MultipartFile file) throws IOException {
        int result = 0;
        List<Performane> performaneList = new ArrayList<>();                    // 创建billList

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
                    Integer number = Integer.parseInt(list.get(0));
                    String name=list.get(1);
                    String unit=list.get(2);
                    String post=list.get(3);
                    String createdTime=list.get(4);
                    Double scores=Double.parseDouble(list.get(5));
                    Double factor=Double.parseDouble(list.get(6));
                    // 构造一个账单对象，并将从个单元格获取的值赋给它
                    Performane performane = new Performane(number,name,unit,post,createdTime,scores,factor);
                    performaneList.add(performane);                                 // 将新的一条账单加入billList
                }
            }

            for (Performane performanes: performaneList){
                result = performaneMapper.addBillExcelFileToDatabase(performanes);      // 将每一条账单插入数据库
            }
        }
        return result;
    }


    //////////////////////计算各岗位匹配系数///////////////////////////////////////////////

    /**
     * 用于新建/修改员工绩效信息时算出员工的匹配系数
     * 终端专员,传入员工信息和绩效算出匹配系数
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
     * @param employee
     * @return
     */
    public double terminalSpecialist(Employee employee,Double scores){
        String one = employee.getOne();
        String nineteen = employee.getNineteen();
        String twenty = employee.getTwenty();
        String thirty = employee.getThirty();
        String thirtyFour = employee.getThirtyFour();
        String thirtySix = employee.getThirtySix();
        String thirtyNine = employee.getThirtyNine();
        String fortyFive = employee.getFortyFive();
        String fortyNine = employee.getFortyNine();
        double sum = 0;
        if(one.equals("是")){
            sum+=0.11;
        }else {
            sum+=0.06;
        }
        if(nineteen.equals("是")){
            sum+=0.08;
        }else {
            sum+=0.04;
        }
        if(twenty.equals("是")){
            sum+=0.07;
        }else {
            sum+=0.04;
        }
        if(thirty.equals("是")){
            sum+=0.12;
        }else{
            sum+=0.06;
        }
        if(thirtyFour.equals("会说")){
            sum+=0.04;
        }else if (thirtyFour.equals("能听不会说")){
            sum+=0.03;
        }else if(thirtyFour.equals("基本听不懂")){
            sum+=0.02;
        }
        if(thirtySix.equals("会说")){
            sum+=0.16;
        }else if (thirtySix.equals("能听不会说")){
            sum+=0.12;
        }else if(thirtySix.equals("基本听不懂")){
            sum+=0.08;
        }
        if(thirtyNine.equals("是")){
            sum+=0.05;
        }else {
            sum+=0.03;
        }
        if(fortyFive.equals("是")){
            sum+=0.11;
        }else {
            sum+=0.06;
        }
        if(fortyNine.equals("是")){
            sum+=0.06;
        }else {
            sum+=0.03;
        }
        if (scores<=87){
            sum+=0.08;
        }else if (scores<=91 && scores>87){
            sum+=0.12;
        }else if(scores<=95 && scores>91){
            sum+=0.16;
        }else if(scores>95){
            sum+=0.20;
        }
        return sum;
    }

    /**
     * 用于新建/修改员工绩效信息时算出员工的匹配系数
     * 市场经理,传入员工信息和绩效算出匹配系数
     * thirteen    0.26  是否中级或以上经济师  是(0.26)  否(0.20)
     * eighteen    0.25  是否五级烟草制品购销职业资格（最高级别）  是(0.20)  否(0.15)
     * fortyfive   0.10  是否有论文发表或获奖情况   是(0.10)  否(0.06)
     * fortyfour   0.10  是否有QC项目获奖情况    是(0.10)  否(0.06)
     * fortytwo    0.10  是否已婚   是(0.10)  否(0.06)
     * performance 0.08  绩效   82.984-87(0.02) 87-91(0.04) 91-95(0.06) 95-99(0.08)
     * fortyseven  0.05  是否有参与数字化转型项目情况    是(0.05)  否(0.03)
     * five        0.03  公文写作能力   很好较好(0.03)  一般(0.02)  较差(0.01)
     * thirtyfour  0.03  当地主要使用的方言掌握情况  会说(0.03)  能听不会说(0.02)  基本听不懂(0.01)
     * @param employee
     * @return
     */
    public double marketingManager(Employee employee,Double scores){
        String thirteen = employee.getThirteen();
        String eighteen = employee.getEighteen();
        String fortyFive = employee.getFortyFive();
        String fortyFour = employee.getFortyFour();
        String fortyTwo = employee.getFortyTwo();
        String fortySeven = employee.getFortySeven();
        String five = employee.getFive();
        String thirtyFour = employee.getThirtyFour();
        double sum = 0;
        if (thirteen.equals("是")){
            sum+=0.26;
        }else{
            sum+=0.20;
        }

        if (eighteen.equals("是")){
            sum+=0.20;
        }else{
            sum+=0.15;
        }

        if (fortyFive.equals("是")){
            sum+=0.10;
        }else{
            sum+=0.06;
        }

        if (fortyFour.equals("是")){
            sum+=0.10;
        }else{
            sum+=0.06;
        }

        if (fortyTwo.equals("是")){
            sum+=0.10;
        }else{
            sum+=0.06;
        }

        if (scores<=87){
            sum+=0.02;
        }else if (scores>87 && scores<=91){
            sum+=0.04;
        }else if (scores>91 && scores<=95){
            sum+=0.06;
        }else if (scores>95){
            sum+=0.08;
        }

        if (fortySeven.equals("是")){
            sum+=0.05;
        }else{
            sum+=0.03;
        }

        if (five.equals("很好")){
            sum+=0.03;
        }else if (five.equals("较好")){
            sum+=0.03;
        }else if (five.equals("一般")){
            sum+=0.02;
        }else {
            sum+=0.01;
        }

        if(thirtyFour.equals("会说")){
            sum+=0.03;
        }else if (thirtyFour.equals("能听不会说")){
            sum+=0.02;
        }else if(thirtyFour.equals("基本听不懂")){
            sum+=0.01;
        }
        return sum;
    }

    /**
     * 用于新建/修改员工绩效信息时算出员工的匹配系数
     * 信息专员,传入员工信息和绩效算出匹配系数
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
     * @param employee
     * @return
     */
    public double informationCommissioner(Employee employee,Double scores){
        Integer age = employee.getAge();
        String fortyFive = employee.getFortyFive();
        Integer seniority = employee.getSeniority();
        String twelve = employee.getTwelve();
        String seventeen = employee.getSeventeen();
        String twentyNine = employee.getTwentyNine();
        String fourteen = employee.getFourteen();
        String fortyTwo = employee.getFortyTwo();
        String fortyOne = employee.getFortyOne();
        double sum = 0;
        if (age<=31.5){
            sum+=0.11;
        }else if (age>31.5 && age<=40){
            sum+=0.13;
        }else if (age>40 && age<=48.5){
            sum+=0.15;
        }else if (age>48.5){
            sum+=0.17;
        }

        if (fortyFive.equals("是")){
            sum+=0.14;
        }else{
            sum+=0.10;
        }

        if (seniority<=9){
            sum+=0.05;
        }else if (seniority>9 && seniority<=16){
            sum+=0.07;
        }else if (seniority>16 && seniority<=22){
            sum+=0.09;
        }else if (seniority>22){
            sum+=0.11;
        }

        if (twelve.equals("很好")){
            sum+=0.10;
        }else if (twelve.equals("较好")){
            sum+=0.010;
        }else if (twelve.equals("一般")){
            sum+=0.08;
        }else {
            sum+=0.06;
        }

        if (seventeen.equals("是")){
            sum+=0.08;
        }else{
            sum+=0.05;
        }

        if (twentyNine.equals("是")){
            sum+=0.08;
        }else{
            sum+=0.05;
        }

        if (fourteen.equals("是")){
            sum+=0.08;
        }else{
            sum+=0.05;
        }

        if (fortyTwo.equals("是")){
            sum+=0.06;
        }else{
            sum+=0.03;
        }

        if (fortyOne.equals("是")){
            sum+=0.05;
        }else{
            sum+=0.03;
        }

        if (scores<=87){
            sum+=0.07;
        }else if (scores>87 && scores<=91){
            sum+=0.09;
        }else if (scores>91 && scores<=95){
            sum+=0.11;
        }else if (scores>95){
            sum+=0.13;
        }
        return sum;
    }

    /**
     * 用于新建/修改员工绩效信息时算出员工的匹配系数
     * 综合管理员,传入员工信息和绩效算出匹配系数
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
     * @param employee
     * @return
     */
    public double comprehensiveAdministrator(Employee employee,Double scores){
        Integer age = employee.getAge();
        String four = employee.getFour();
        String seventeen = employee.getSeventeen();
        String eighteen = employee.getEighteen();
        String nineteen = employee.getNineteen();
        String thirteen = employee.getThirteen();
        String eleven = employee.getEleven();
        String twentyOne = employee.getTwentyOne();
        String twelve = employee.getTwelve();
        double sum = 0;
        if (scores<=87){
            sum+=0.16;
        }else if (scores>87 && scores<=91){
            sum+=0.19;
        }else if (scores>91 && scores<=95){
            sum+=0.22;
        }else if (scores>95){
            sum+=0.25;
        }

        if (age<=31.5){
            sum+=0.09;
        }else if (age>31.5 && age<=40){
            sum+=0.12;
        }else if (age>40 && age<=48.5){
            sum+=0.15;
        }else if (age>48.5){
            sum+=0.18;
        }

        if (four.equals("是")){
            sum+=0.15;
        }else{
            sum+=0.08;
        }

        if (seventeen.equals("是")){
            sum+=0.10;
        }else{
            sum+=0.06;
        }

        if (eighteen.equals("是")){
            sum+=0.07;
        }else{
            sum+=0.04;
        }

        if (nineteen.equals("是")){
            sum+=0.07;
        }else{
            sum+=0.04;
        }

        if (thirteen.equals("是")){
            sum+=0.06;
        }else{
            sum+=0.04;
        }

        if(eleven.equals("很好")){
            sum+=0.04;
        }else if (eleven.equals("较好")){
            sum+=0.04;
        }else if (eleven.equals("一般")){
            sum+=0.03;
        }else{
            sum+=0.02;
        }

        if (twentyOne.equals("是")){
            sum+=0.04;
        }else {
            sum+=0.02;
        }

        if (twelve.equals("很好")){
            sum+=0.04;
        }else if (twelve.equals("较好")){
            sum+=0.04;
        }else if (twelve.equals("一般")){
            sum+=0.03;
        }else {
            sum+=0.02;
        }
        return sum;
    }

    /**
     * 用于新建/修改员工绩效信息时算出员工的匹配系数
     * 客户专员,传入员工信息和绩效算出匹配系数
     * degree 最高学历  0.10 大学(0.10) 大专(0.08) 高中(0.06) 中专、初级、技校(0.04)
     * age 年龄 0.05  22.966-31.5(0.02) 31.5-40(0.03) 40-48.5(0.04) 48.5-57(0.05)
     * seniority 工作年龄 0.19  -0.01-9(0.07) 9-16(0.11)  16-22(0.15) 22-(0.19)
     * performance月度绩效  0.22     82.984-87(0.10) 87-91(0.14) 91-95(0.18) 95-99(0.22)
     * seven 新媒体营销技术水平  0.08     很好 较好(0.08)  一般(0.05) 较差(0.03)
     * nineteen 是否具有中级以上计算机方面的资格证书 0.03    是(0.03)  否(0.02)
     * thirty 是否新媒体营销团队成员  0.09  是(0.09) 否(0.05)
     * forty（区/县/市）定居 0.08  是(0.08) 否(0.04)
     * forty_one 是否有任职营销以外岗位的工作经历 0.04   是(0.04) 否(0.02)
     * forty_three 生育情况 0.12  无(0.06) 一孩(0.08)  二孩(0.10)  三孩(0.12)
     * @param employee
     * @return
     */
    public double accountSpecialist(Employee employee,Double scores){
        String degree = employee.getDegree();
        Integer age = employee.getAge();
        Integer seniority = employee.getSeniority();
        String seven = employee.getSeven();
        String nineteen = employee.getNineteen();
        String thirty = employee.getThirty();
        String forty = employee.getForty();
        String fortyOne = employee.getFortyOne();
        String fortyThree = employee.getFortyThree();
        double sum = 0;
        if (degree.equals("大学")){
            sum+=0.10;
        }else if (degree.equals("大专")){
            sum+=0.08;
        }else if (degree.equals("高中")){
            sum+=0.06;
        }else if (degree.equals("初中")){
            sum+=0.04;
        }else if (degree.equals("技校")){
            sum+=0.04;
        }else{
            sum+=0.04;
        }

        if (age<=31.5){
            sum+=0.02;
        }else if (age>31.5 && age<=40){
            sum+=0.03;
        }else if (age>40 && age<=48.5){
            sum+=0.04;
        }else if (age>48.5){
            sum+=0.05;
        }

        if (seniority<=9){
            sum+=0.07;
        }else if (seniority>9 && seniority<=16){
            sum+=0.11;
        }else if (seniority>16 && seniority<=22){
            sum+=0.15;
        }else if (seniority>22){
            sum+=0.19;
        }

        if (scores<=87){
            sum+=0.10;
        }else if (scores>87 && scores<=91){
            sum+=0.14;
        }else if (scores>91 && scores<=95){
            sum+=0.18;
        }else if (scores>95){
            sum+=0.22;
        }

        if (seven.equals("很好")){
            sum+=0.08;
        }else if (seven.equals("较好")){
            sum+=0.08;
        }else if (seven.equals("一般")){
            sum+=0.05;
        }else {
            sum+=0.03;
        }

        if (nineteen.equals("是")){
            sum+=0.03;
        }else{
            sum+=0.02;
        }

        if (thirty.equals("是")){
            sum+=0.09;
        }else{
            sum+=0.05;
        }

        if (forty.equals("是")){
            sum+=0.08;
        }else{
            sum+=0.04;
        }

        if (fortyOne.equals("是")){
            sum+=0.04;
        }else{
            sum+=0.02;
        }

        if(fortyThree.equals("无")){
            sum+=0.06;
        }else if (fortyThree.equals("一孩")){
            sum+=0.08;
        }else if(fortyThree.equals("二孩")){
            sum+=0.10;
        }else {
            sum+=0.12;
        }
        return sum;
    }
}

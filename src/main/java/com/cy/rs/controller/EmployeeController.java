package com.cy.rs.controller;

import com.cy.rs.entity.*;
import com.cy.rs.service.IEmployeeService;
import com.cy.rs.service.IUserService;
import com.cy.rs.util.DownExcel;
import com.cy.rs.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("employee")
@RestController//@RestController=@Controller+@ResponseBody
public class EmployeeController extends BaseController{

    @Autowired
    private IEmployeeService employeeService;
    @ApiOperation("员工信息新建方法，员工编号number是必须的，为保证能根据number查询该记录")
    @PostMapping("insert")
    public JsonResult<Employee> insert(@RequestBody Employee employee) {
        Employee result = employeeService.insert(employee);
        return new JsonResult<Employee>(Ok,result);
    }
    @ApiOperation("员工信息修改方法，必须传入id")
    @PutMapping ("update")
    public JsonResult<Void> update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return new JsonResult<>(Ok);
    }
    @ApiOperation("员工信息删除方法，需传入员工信息id")
    @DeleteMapping("delete")
    public JsonResult<Void> delete(Integer id) {
        employeeService.delete(id);
        return new JsonResult<>(Ok);
    }
    @ApiOperation("员工信息查询方法，用于展示员工信息，必须传入日期，格式如 “2023-05”")
    @GetMapping("select")
    public JsonResult<List<Employee>> select(String createdTime) {
        List<Employee> result = employeeService.findByCreatedTime(createdTime);
        return new JsonResult<List<Employee>>(Ok,result);
    }
    @ApiOperation("员工信息查询方法，根据员工姓名查询，用于表单的快速填写")
    @GetMapping("findByNumber")
    public JsonResult<List<Employee>> findByNumber(String name) {
        List<Employee> result = employeeService.findByNumber(name);
        return new JsonResult<List<Employee>>(Ok,result);
    }
//    @ApiOperation("查询员工在当前岗位对于优秀标签是否拥有，以及绩效和匹配系数")
//    @GetMapping("selectLabel")
//    public JsonResult<Map<String, String>> findByNameAndCreatedTime1(String name, String createdTime) {
//        Map<String, String> result = employeeService.findByNameAndCreatedTime1(name, createdTime);
//        return new JsonResult<Map<String, String>>(Ok,result);
//    }

    @ApiOperation("员工信息查询方法，有分页功能，pageNum：当前页码，pageSize：每页展示条数，必须传入的字段为pageNum，pageSize,createdTime,createdTime为字符串形式，注意格式如 “2023-05”")
    @GetMapping("selectAll")
    public JsonResult<List<Employee>> selectAll( int pageNum,  int pageSize, String createdTime){
        List<Employee> employees = employeeService.findByName(pageNum,pageSize,createdTime);
        Double count = employeeService.findByCount(createdTime);
        return new JsonResult<List<Employee>>(Ok,employees,count.toString());
    }

    @ApiOperation("查询员工信息所有月份的方法")
    @GetMapping("selectByCreatedTime")
    public JsonResult<List<String>> selectByCreatedTime(){
        List<String> result = employeeService.selectByCreatedTime();
        return new JsonResult<List<String>>(Ok,result);
    }
    @ApiOperation("查询员工信息所有单位的方法")
    @GetMapping("selectByUnit")
    public JsonResult<List<String>> selectByUnit(){
        List<String> result = employeeService.selectByUnit();
        return new JsonResult<List<String>>(Ok,result);
    }


    ///////////////////////////////员工画像页面，一共四个接口//////////////////////////////////////////
    @ApiOperation("根据日期和姓名查询员工信息,需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("EmployeeMessage")
    public JsonResult<Employee> EmployeeMessage(Integer number, String createdTime) {
        Employee result = employeeService.EmployeeMessage(number, createdTime);
        return new JsonResult<Employee>(Ok,result);
    }
    @ApiOperation("查出画像特征,也就是员工拥有的优秀特征,需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("PostFeatures")
    public JsonResult<FeaturesAnalyse> PostFeatures(Integer number, String createdTime) {
        FeaturesAnalyse result = employeeService.PostFeatures(number, createdTime);
        return new JsonResult<FeaturesAnalyse>(Ok,result);
    }
    @ApiOperation("查出画像特征,也就是员工拥有的优秀特征,需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("PostFeaturesByName")
    public JsonResult<FeaturesAnalyse> PostFeaturesByName(String name, String createdTime) {
        FeaturesAnalyse result = employeeService.PostFeaturesByName(name, createdTime);
        return new JsonResult<FeaturesAnalyse>(Ok,result);
    }
    @ApiOperation("查询该员工对全部岗位的匹配系数，以及全部岗位匹配系数的最高最低平均值,需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("PostCountFactorByNumber")
    public JsonResult<List<PostMessage>> PostCountFactorByNumber(Integer number, String createdTime) {
        List<PostMessage> result = employeeService.PostCountFactor(number, createdTime);
        return new JsonResult<List<PostMessage>>(Ok,result);
    }
    @ApiOperation("查询该员工对全部岗位的匹配系数，以及全部岗位匹配系数的最高最低平均值,需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("PostCountFactorByName")
    public JsonResult<List<PostMessage>> PostCountFactorByName(String name, String createdTime) {
        List<PostMessage> result = employeeService.PostCountFactorNew(name, createdTime);
        return new JsonResult<List<PostMessage>>(Ok,result);
    }
    @ApiOperation("获取员工匹配系数需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("findFactorByname")
    public JsonResult<Double> findFactorByname(Integer number, String createdTime) {
        Double result = employeeService.findFactorByname(number, createdTime);
        return new JsonResult<Double>(Ok,result);
    }
    @ApiOperation("获取员工匹配系数需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("EmployeeNiceFeatures")
    public JsonResult<List<String>> EmployeeNiceFeatures(Integer number, String createdTime) {
        List<String> result = employeeService.EmployeeNiceFeatures(number, createdTime);
        return new JsonResult<List<String>>(Ok,result);
    }


    //////////////////////////////////人岗匹配页面/////////////////////////////////////////////////
    @ApiOperation("人岗匹配分析，查询该员工对该岗位的匹配系数，以及该岗位的最高最低平均匹配系数,需要传入的参数有员工姓名和日期、岗位，类型都是string，日期格式例如”2023-05“")
    @GetMapping("EmployeeAndPostMatching")
    public JsonResult<List<Map<String,String>>> EmployeeAndPostMatching(String name, String createdTime,String post) {
        List<Map<String,String>> result = employeeService.EmployeeAndPostMatchingNew(name, createdTime, post);
        return new JsonResult<List<Map<String,String>>>(Ok,result);
    }
    @ApiOperation("查出画像特征,也就是员工拥有的优秀特征,需要传入的参数有员工姓名和日期，类型都是string，日期格式例如”2023-05“")
    @GetMapping("PostFeaturesByNumberAndPost")
    public JsonResult<FeaturesAnalyse> PostFeaturesByNumberAndPost(Integer number, String createdTime,String post) {
        FeaturesAnalyse result = employeeService.PostFeaturesByNumberAndPost(number, createdTime,post);
        return new JsonResult<FeaturesAnalyse>(Ok,result);
    }
    ///////////////////////////////////////岗位画像/////////////////////////////////////////////////////
    @ApiOperation("传入日期和岗位，根据岗位罗列出全部优秀特征，挑选出除基础标签和绩效，然后排序，" +
            "返回前五，返回标签名称和占比，拥有标签的人人数占比和为拥有优秀标签的人数占比" +
            ",需要传入的参数有员工姓名和日期、岗位，类型都是string，日期格式例如”2023-05“")
    @GetMapping("findByPostAndCreatedTime5")
    public JsonResult<List<FeaturesMessage>> findByPostAndCreatedTime5(String createdTime,String post) {
        List<FeaturesMessage> result = employeeService.findByPostAndCreatedTime5(createdTime, post);
        return new JsonResult<List<FeaturesMessage>>(Ok,result);
    }

    @ApiOperation("用于右上角的查询，根据岗位和员工编号或岗位和员工姓名进行查询，注意编号和姓名的数据类型是不一样的，会根据传入的数据自动判断是姓名还是编号")
    @GetMapping("findByPostAndCondition")
    public JsonResult<List<Employee>> findByPostAndCondition(String post, String conditions) {
        Object condition;
        System.out.println(conditions);
        try {
            // 尝试将 identifier 参数转换为整数类型
            int employeeId = Integer.parseInt(conditions);

            // 如果转换成功，则将其作为员工编号进行查询
            condition = employeeId;
        } catch (NumberFormatException e) {
            // 如果转换失败，则将其作为姓名进行查询
            condition = conditions;
        }
        System.out.println(condition);
        List<Employee> result = employeeService.findByPostAndCondition(post, condition);

        return new JsonResult<List<Employee>>(Ok,result);
    }
    @ApiOperation("用于右上角的查询，根据岗位进行查询")
    @GetMapping("findByUnit")
    public JsonResult<List<Employee>> findByUnit(String post) {
        List<Employee> result = employeeService.findByPost(post);
        return new JsonResult<List<Employee>>(Ok,result);
    }

    /**
     * excel表格数据导入数据库
     * @param file
     * @return
     */
    @ApiOperation("excel数据导入数据库的方法")
    @PostMapping("upload")
    @ResponseBody
    public String excelImport(@RequestParam(value = "file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int result = 0;
        try {
            result = employeeService.addToDatabase(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            return "恭喜，账单导入成功！";
        } else {
            return "害！导入失败了...";
        }
    }


    @ApiOperation("数据导入到excel表格的方法")
    @GetMapping("downloadexcel")
    public void getExcel(HttpServletResponse response) throws IllegalAccessException, IOException,
            InstantiationException {
        List<Employee> list = employeeService.select();
        DownExcel.download(response, Employee.class, list);
    }

    @ApiOperation("数据分析页面，驾驶舱")
    @GetMapping("cockpit")
    public JsonResult<Map<String,Map<String,Map<String,Double>>>> cockpit(String post,String createdTime) {
        Map<String,Map<String,Map<String,Double>>> result = employeeService.cockpit(post,createdTime);
        return new JsonResult<Map<String,Map<String,Map<String,Double>>>>(Ok,result);
    }
}

package com.cy.rs.controller;

import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Label;
import com.cy.rs.entity.Performane;
import com.cy.rs.entity.PostAnalyse;
import com.cy.rs.service.IPerformaneService;
import com.cy.rs.service.IUserService;
import com.cy.rs.util.DownExcel;
import com.cy.rs.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("performane")
@RestController//@RestController=@Controller+@ResponseBody
public class PerformaneController extends BaseController{

    @Autowired
    private IPerformaneService performaneService;

    @ApiOperation("员工绩效新增方法，除factor和employee字段其他都不能为空，填入员工编号或员工姓名时会自动填入其他信息，不能自己更改,为方便前端更新数据，新增成功后自动返回新增的数据")
    @PostMapping("insert")
    public JsonResult<Performane> insert(@RequestBody Performane performane) {
        Performane result = performaneService.insert(performane);
        return new JsonResult<Performane>(Ok,result);
    }

    @ApiOperation("修改员工绩效的方法 要求前端表单能修改的字段只有绩效,为方便前端更新数据，新增成功后自动返回新增的数据")
    @PutMapping("update")
    public JsonResult<Performane> update(@RequestBody Performane performane) {
        Performane result = performaneService.update(performane);
        return new JsonResult<Performane>(Ok,result);
    }
    @ApiOperation("员工绩效删除方法,")
    @GetMapping("deleteById")
    public JsonResult<Void> findByName(Integer id) {
        performaneService.deleteById(id);
        return new JsonResult<>(Ok);
    }
    @ApiOperation("员工绩效查询方法,查询所有数据,用于前端展示")
    @GetMapping("select")
    public JsonResult<List<Performane>> select() {
        List<Performane> result = performaneService.select();
        return new JsonResult<List<Performane>>(Ok,result);
    }
    @ApiOperation("用于右上角的查询，根据岗位和员工编号或岗位和员工姓名进行查询，注意编号和姓名的数据类型是不一样的，会根据传入的数据自动判断是姓名还是编号")
    @GetMapping("findByPostAndCondition")
    public JsonResult<List<Performane>> findByPostAndCondition(String post, String conditions) {
        Object condition;
        try {
            // 尝试将 identifier 参数转换为整数类型
            int employeeId = Integer.parseInt(conditions);

            // 如果转换成功，则将其作为员工编号进行查询
            condition = employeeId;
        } catch (NumberFormatException e) {
            // 如果转换失败，则将其作为姓名进行查询
            condition = conditions;
        }
        List<Performane> result = performaneService.findByPostAndCondition(post, condition);
        return new JsonResult<List<Performane>>(Ok,result);
    }
    @ApiOperation("用于右上角的查询，根据岗位进行查询")
    @GetMapping("findByUnit")
    public JsonResult<List<Performane>> findByUnit(String post) {
        List<Performane> result = performaneService.findByPost(post);
        return new JsonResult<List<Performane>>(Ok,result);
    }
    @ApiOperation("员工绩效根据员工姓名查询方法，用于快速填写表单")
    @GetMapping("findByName")
    public JsonResult<List<Employee>> findByName(String name) {
        List<Employee> result = performaneService.findByName(name);
        return new JsonResult<List<Employee>>(Ok,result);
    }
    @ApiOperation("员工绩效根据员工编号查询方法，用于快速填写表单")
    @GetMapping("findByNumber")
    public JsonResult<List<Employee>> findByNumber(Integer number) {
        List<Employee> result = performaneService.findByNumber(number);
        return new JsonResult<List<Employee>>(Ok,result);
    }
    @ApiOperation("员工绩效根据员工姓名和日期查询方法，用于快速填写表单")
    @GetMapping("findByNameAndCreatedTime")
    public JsonResult<Employee> findByNameAndCreatedTime(String name,String createdTime) {
        Employee result = performaneService.findByNameAndCreatedTime(name,createdTime);
        return new JsonResult<Employee>(Ok,result);
    }
    @ApiOperation("员工绩效根据员工姓名和日期查询方法，用于快速填写表单")
    @GetMapping("findByNameAndCreatedTimeNew")
    public JsonResult<List<Employee>> findByNameAndCreatedTimeNew(String name,String createdTime) {
        List<Employee> result = performaneService.findByNameAndCreatedTimeNew(name,createdTime);
        return new JsonResult<List<Employee>>(Ok,result);
    }
    @ApiOperation("员工绩效根据员工编号和日期查询方法，用于快速填写表单")
    @GetMapping("findByNumberAndCreatedTime")
    public JsonResult<Employee> findByNumberAndCreatedTime(Integer number,String createdTime) {
        Employee result = performaneService.findByNumberAndCreatedTime(number,createdTime);
        return new JsonResult<Employee>(Ok,result);
    }

    @ApiOperation("根据岗位和日期查询该岗位匹配系数，按降序排序,需要传入的参数有日期、岗位，类型都是string，日期格式例如”2023-05“")
    @GetMapping("findPostFactorDesc")
    public JsonResult<List<Performane>> findByPostAndCreatedTime(String post, String createdTime) {
        List<Performane> result = performaneService.findByPostAndCreatedTime(post, createdTime);
        return new JsonResult<List<Performane>>(Ok,result);
    }

    //////////////////////////////数据分析页面///////////////////////////////////////////////////////
    @ApiOperation("用于数据分析页面，获取员工数，岗位数，总体匹配系数，平均年龄,需要传入的参数有日期,类型都是string，日期格式例如”2023-05“")
    @GetMapping("dataAnalysis1")
    public JsonResult<Map<String,Double>> dataAnalysis1( String createdTime) {
        Map<String, Double> result = performaneService.dataAnalysis1(createdTime);
        return new JsonResult<Map<String,Double>>(Ok,result);
    }
    @ApiOperation("用于数据分析页面，各岗位人数以及匹配系数,需要传入的参数有日期类型都是string，日期格式例如”2023-05“")
    @GetMapping("dataAnalysis2")
    public JsonResult<Map<String,Double>> dataAnalysis2(String createdTime) {
        Map<String, Double> result = performaneService.dataAnalysis2(createdTime);
        return new JsonResult<Map<String,Double>>(Ok,result);
    }

    @ApiOperation("用于总体画像页面，各岗位人数以及匹配系数,需要传入的参数有日期类型都是string，日期格式例如”2023-05“")
    @GetMapping("overallPortrait")
    public JsonResult<PostAnalyse> overallPortrait(String createdTime,String unit, String post,String sex, Integer minAge,Integer maxAge, String degree) {
        PostAnalyse result = performaneService.overallPortrait(createdTime, unit, post, sex, minAge, maxAge, degree);
        return new JsonResult<PostAnalyse>(Ok,result);
    }

    @ApiOperation("数据导入到excel表格的方法")
    @GetMapping("downloadexcel")
    public void getExcel(HttpServletResponse response) throws IllegalAccessException, IOException,
            InstantiationException {
        List<Performane> list = performaneService.select();
        DownExcel.download(response, Performane.class, list);
    }

    @ApiOperation("excel数据导入数据库的方法")
    @PostMapping("upload")
    @ResponseBody
    public String excelImport(@RequestParam(value = "file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int result = 0;
        try {
            result = performaneService.addToDatabase(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            return "恭喜，账单导入成功！";
        } else {
            return "害！导入失败了...";
        }
    }
}

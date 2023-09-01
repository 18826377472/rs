package com.cy.rs.service;

import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Performane;
import com.cy.rs.entity.PostAnalyse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 员工绩效模块业务层接口
 */
public interface IPerformaneService {

    /**
     * 员工每月绩效新增方法
     * @param performane 员工的数据对象
     */
    Performane insert (Performane performane);

    /**
     * 修改员工绩效的方法 要求前端表单能修改的字段只有绩效
     * @param performane 员工的绩效信息
     */
    Performane update (Performane performane);

    /**
     * 根据id删除员工绩效的方法
     * @param id id主键
     */
    void deleteById(Integer id);

    /**
     * 查询所有绩效信息
     * @return
     */
    List<Performane> select();

    /**
     * 根据岗位和员工编号或者姓名查询信息，自动判断员工姓名和编号
     * @param post 岗位
     * @param condition 员工姓名或编号
     * @return
     */
    List<Performane> findByPostAndCondition(String post,Object condition);

    /**
     *
     */
    List<Performane> findByPost(String post);

    /**
     * 根据员工编号去employee表中查询信息
     * @param number
     * @return
     */
    List<Employee> findByNumber(Integer number);

    /**
     * 根据员工姓名去employee表中查询信息
     * @param name
     * @return
     */
    List<Employee> findByName(String name);

    /**
     * 根据员工编号和日期去employee表中查询信息
     * @param number
     * @param createdTime
     * @return
     */
    Employee findByNumberAndCreatedTime(Integer number,String createdTime);

    /**
     * 根据员工姓名和日期去employee表中查询信息
     * @param name
     * @param createdTime
     * @return
     */
    Employee findByNameAndCreatedTime(String name,String createdTime);
    List<Employee> findByNameAndCreatedTimeNew(String name,String createdTime);

    /**
     * 根据岗位和日期查询该岗位匹配系数，按降序排序
     * @param post
     * @param createdTime
     * @return
     */
    List<Performane> findByPostAndCreatedTime(String post, String createdTime);

    Map<String,Double> dataAnalysis1(String createdTime);

    Map<String,Double> dataAnalysis2(String createdTime);

    PostAnalyse overallPortrait(String createdTime, String unit, String post, String sex, Integer minAge, Integer maxAge, String degree);

    /**
     * 从excel表格导入数据到数据库中
     * @param file
     * @return
     * @throws IOException
     */
    Integer addToDatabase(MultipartFile file) throws IOException;
}

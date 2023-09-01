package com.cy.rs.mapper;

import com.cy.rs.entity.Bill;
import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Performane;
import com.cy.rs.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

/**
 * 员工模块的持久化接口
 */

public interface EmployeeMapper {

    /**
     * 新增员工信息及其标签
     * @param employee 员工的数据
     * @return  受影响的行数
     */
    Integer insert (Employee employee);

    /**
     * 根据员工编号和日期查询信息
     * @param number 员工编号
     * @return  找到则返回员工信息
     */
    Employee findByNumberAndCreatedTime (@Param("number") Integer number, @Param("createdTime")String createdTime);

    /**
     * 根据员工姓名查询信息
     * @param name 员工编号
     * @return 返回员工信息
     */
    List<Employee> findByNumber (String name);


    /**
     * 根据id修改员工信息
     * @return 返回受影响的行数
     */
    Integer updateById (Employee employee);

    /**
     * 根据id查询员工信息
     * @param id id主键
     * @return 返回员工信息
     */
    Employee findById(Integer id);

    /**
     * 根据日期查询所有员工信息
     * @param createdTime 日期
     * @return 返回List<Employee>
     */
    List<Employee> findByCreatedTime(String createdTime);

    List<Employee> findByCreatedTime1(String createdTime);

    /**
     * 根据id删除员工信息
     * @param id id主键
     * @return 返回受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 查询所有员工信息
     * @return
     */
    List<Employee> select();

    /**
     * 根据员工编号去employee表中查询信息,用于快速填表
     * @param number 员工编号
     * @return  找到则返回员工姓名，单位，岗位
     */
    List<Employee> findByNumber2 (Integer number);

    /**
     * 根据员工姓名去employee表中查询信息，用于快速填表
     * @param name 员工姓名
     * @return
     */
    List<Employee> findByName (String name);

    /**
     * 根据员工编号和日期去employee表中查询信息，获取全部数据，用于计算匹配系数
     * @param number 员工编号
     * @param createdTime 日期
     * @return
     */
    Employee findByNumberAndCreatedTime2 (@Param("number")Integer number,@Param("createdTime")String createdTime);

    /**
     * 根据员工姓名和日期去employee表中查询信息，获取全部数据，用于计算匹配系数
     * @param name 员工姓名
     * @param createdTime 日期
     * @return
     */
    Employee findByNameAndCreatedTime (@Param("name")String name,@Param("createdTime")String createdTime);
    List<Employee> findByNameAndCreatedTimeNew (@Param("name")String name,@Param("createdTime")String createdTime);

    /**
     * 查询平均年龄
     * @param createdTime
     * @return
     */
    Double findAvgAge(String createdTime);

    List<Integer> overallPortrait(@Param("createdTime")String createdTime,@Param("unit")String unit,
                                   @Param("post")String post,@Param("sex")String sex,
                                   @Param("minAge")Integer minAge,@Param("maxAge")Integer maxAge,
                                   @Param("degree")String degree);

    /**
     * 查询员工信息的所有月份
     */
    List<String> selectByCreatedTime();
    /**
     * 查询员工信息的所有单位
     */
    List<String> selectByUnit();

    /**
     * 根据岗位和员工编号去performane表中查询信息
     * @param post 岗位
     * @param number 员工编号
     * @return
     */
    List<Employee> findByPostAndNumber (@Param("post")String post, @Param("number")Integer number);

    /**
     * 根据岗位和员工姓名去performane表中查询信息
     * @param post 岗位
     * @param name 员工姓名
     * @return
     */
    List<Employee> findByPostAndName (@Param("post")String post,@Param("name")String name);

    /**
     * 根据岗位和员工姓名去performane表中查询信息
     * @param post 岗位
     * @return
     */
    List<Employee> findByPost (String post);

    /**
     * 从excel表格导入数据到数据库中
     * @param employee
     * @return
     */
    Integer addBillExcelFileToDatabase(Employee employee);

    /**
     * 根据岗位和员工姓名去performane表中查询信息
     * @param post 岗位
     * @return
     */
    List<Employee> findByPostAndCreatedTime (@Param("post")String post,@Param("createdTime")String createdTime);
}

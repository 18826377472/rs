package com.cy.rs.mapper;

import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Performane;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public interface PerformaneMapper {

    /**
     * 根据岗位和员工编号去performane表中查询信息
     * @param post 岗位
     * @param number 员工编号
     * @return
     */
    List<Performane> findByPostAndNumber (@Param("post")String post,@Param("number")Integer number);

    /**
     * 根据岗位和员工姓名去performane表中查询信息
     * @param post 岗位
     * @param name 员工姓名
     * @return
     */
    List<Performane> findByPostAndName (@Param("post")String post,@Param("name")String name);
    /**
     * 根据员工编号和日期去performane表中查询信息，
     * @param number 员工编号
     * @param createdTime 日期
     * @return
     */
    Performane findNumberAndCreatedTime (@Param("number") Integer number, @Param("createdTime")String createdTime);

    /**
     * 根据员工编号和日期去performane表中查询信息，
     * @param name 员工编号
     * @param createdTime 日期
     * @return
     */
    Performane findNameAndCreatedTime (@Param("name") String name, @Param("createdTime")String createdTime);


    /**
     *
     */
    List<Performane> findByPost(String post);

    /**
     * 新增员工绩效信息
     * @param performane 员工的数据
     * @return  受影响的行数
     */
    Integer insert (Performane performane);

    /**
     * 查询performane表中所有信息
     * @return 返回List<Performane>
     */
    List<Performane> select ();

    /**
     * 修改员工绩效信息
     * @param performane 员工绩效信息
     * @return 放回受影响的行数
     */
    Integer update (Performane performane);

    /**
     * 根据id删除员工绩效信息
     * @param id id主键
     * @return 返回受影响的行数
     */
    Integer deleteById (Integer id);

    /**
     * 根据id查询信息
     */
    Performane findById(Integer id);

    /**
     * 查询各个岗位最高匹配系数
     * @param post
     * @return
     */
    Double findByFactorMax(@Param("post")String post,@Param("createdTime")String createdTime);

    /**
     * 查询各个岗位最低匹配系数
     * @param post
     * @return
     */
    Double findByFactorMin(@Param("post")String post,@Param("createdTime")String createdTime);
    /**
     * 查询各个岗位匹配系数平均值
     * @param post
     * @return
     */
    Double findByFactorAvg(@Param("post")String post,@Param("createdTime")String createdTime);

    /**
     * 查询performane表中所有信息
     * @return 返回List<Performane>
     */
    List<Performane> findByPostAndCreatedTime (@Param("post")String post, @Param("createdTime")String createdTime);

    /**
     * 查询员工总数
     * @param createdTime
     * @return
     */
    Double findCountNumber(String createdTime);
    /**
     * 查询岗位总数
     * @param createdTime
     * @return
     */
    Double findCountPost(String createdTime);
    /**
     * 查询匹配系数平均值
     * @param createdTime
     * @return
     */
    Double findAvgFactor(String createdTime);

    /**
     * 查询各个岗位人数
     * @param createdTime
     * @param post
     * @return
     */
    Double findCountNumberByPost(@Param("createdTime")String createdTime,@Param("post")String post);

    /**
     * 查询各个岗位匹配系数平均值
     * @param createdTime
     * @param post
     * @return
     */
    Double findAvgFactorByPost(@Param("createdTime")String createdTime,@Param("post")String post);

    /**
     * 查询员工的匹配系数，但是为了防止名字重复，查询出多条信息，所以设置为集合
     * @param createdTime
     * @param name
     * @return
     */
    List<Double> findFactorByname (@Param("name")String name,@Param("createdTime")String createdTime);
    /**
     * 查询员工的匹配系数，但是为了防止名字重复，查询出多条信息，所以设置为集合
     * @param createdTime
     * @param number
     * @return
     */
    Double findFactorByNumber (@Param("number")Integer number,@Param("createdTime")String createdTime);

    /**
     * 从excel表格导入数据到数据库中
     * @param performane
     * @return
     */
    Integer addBillExcelFileToDatabase(Performane performane);

}


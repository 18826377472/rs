package com.cy.rs.service;

import com.cy.rs.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 员工模块业务层接口
 */
public interface IEmployeeService {


    /**
     * 员工信息新增方法
     * @param employee 员工的数据对象
     */
    Employee insert (Employee employee);

    /**
     * 修改员工信息的方法
     * @param employee  员工的数据对象
     */
    void update (Employee employee);

    /**
     * 根据id删除员工信息
     * @param id id 主键
     */
    void delete(Integer id);

    /**
     * 根据日期查询员工信息，展示
     * @param createdTime 日期
     * @return
     */
    List<Employee> findByCreatedTime(String createdTime);

    /**
     * 根据员工编号查询员工信息
     * @param name 员工编号
     * @return 返回List<Employee>
     */
    List<Employee> findByNumber(String name);

    List<Employee> findByName(int pageNum,int pageSize,String createdTime);
    Double findByCount(String createdTime);
    /**
     * 查询员工信息所有月份
     * @return
     */
    List<String> selectByCreatedTime();
    /**
     * 查询员工信息所有单位
     * @return
     */
    List<String> selectByUnit();

    /**
     * 根据员工姓名和日期查询员工信息，主要是想获取岗位，然后返回map集合，包含员工当前岗位的优秀标签及其拥有状态和绩效、匹配系数
     * @param number
     * @param createdTime
     * @return
     */
    FeaturesAnalyse PostFeatures(Integer number, String createdTime);
    FeaturesAnalyse PostFeaturesByName(String name, String createdTime);
    FeaturesAnalyse PostFeaturesByNumberAndPost(Integer number, String createdTime,String post);
    /**
     * 员工画像
     * 第一部分，根据日期和姓名查询员工信息
     */
    Employee EmployeeMessage(Integer number, String createdTime);
    /**
     * 获取当前员工在全部岗位的匹配系数，以及全部岗位最高最低平均匹配系数
     * @param number
     * @param createdTime
     * @return
     */
    List<PostMessage> PostCountFactor(Integer number, String createdTime);
    List<PostMessage> PostCountFactorNew(String name, String createdTime);

    Double findFactorByname(Integer number, String createdTime);
    List<String> EmployeeNiceFeatures(Integer number, String createdTime);

    /**
     * 人岗匹配分析，查询该员工对该岗位的匹配系数，以及该岗位的最高最低平均匹配系数
     * @param name
     * @param createdTime
     * @param post
     * @return
     */
    FeaturesAnalyse findByNameAndCreatedTime3(String name, String createdTime, String post);
    /**
     * 人岗匹配分析，查询该员工对该岗位的匹配系数，以及该岗位的最高最低平均匹配系数
     * @param name
     * @param createdTime
     * @param post
     * @return
     */
     Map<String,List<Double>> EmployeeAndPostMatching(String name, String createdTime,String post);
    List<Map<String,String>> EmployeeAndPostMatchingNew(String name, String createdTime,String post);
     /**
     * 用于岗位画像页面
     * 传入日期和岗位，根据岗位罗列出全部优秀特征，挑选出除基础标签和绩效，然后排序，
     *     返回前五，返回标签名称和占比，拥有标签的人人数占比和为拥有优秀标签的人数占比
     *     数据存储Map<String List<Double>>
     */
    List<FeaturesMessage> findByPostAndCreatedTime5(String createdTime, String post);

    /**
     * 根据岗位和员工编号或者姓名查询信息，自动判断员工姓名和编号
     * @param post 岗位
     * @param condition 员工姓名或编号
     * @return
     */
    List<Employee> findByPostAndCondition(String post, Object condition);

    List<Employee> findByPost(String post);

    /**
     * 从excel表格导入数据到数据库中
     * @param file
     * @return
     * @throws IOException
     */
    Integer addToDatabase(MultipartFile file) throws IOException;

    /**
     * 从excel表格导出数据到数据库中
     * @return
     */
    List<Employee> select();

    Map<String,Map<String,Map<String,Double>>> terminalSpecialistFeaturesMessage(String post,String createdTime);
    Map<String,Map<String,Map<String,Double>>> marketingManagerFeaturesMessage(String post,String createdTime);
    Map<String,Map<String,Map<String,Double>>> informationCommissionerFeaturesMessage(String post,String createdTime);
    Map<String,Map<String,Map<String,Double>>> comprehensiveAdministratorFeaturesMessage(String post,String createdTime);
    Map<String,Map<String,Map<String,Double>>> accountSpecialistFeaturesMessage(String post,String createdTime);

      Map<String,Map<String,Map<String,Double>>> cockpit(String post,String createdTime);
}

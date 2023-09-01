package com.cy.rs.mapper;

import com.cy.rs.entity.Label;
import com.cy.rs.entity.Performane;

import java.util.List;

public interface LabelMapper {


    /**
     * 新增标签信息
     * @param label 标签信息
     * @return  受影响的行数
     */
    Integer insert (Label label);

    /**
     * 查询t_label表中所有信息
     * @return 返回List<Label>
     */
    List<Label> select ();

    /**
     * 修改标签信息
     * @param label 标签信息
     * @return 放回受影响的行数
     */
    Integer update (Label label);

    /**
     * 根据id删除标签信息
     * @param id id主键
     * @return 返回受影响的行数
     */
    Integer deleteById (Integer id);

    /**
     * 根据标签查询信息
     * @param features 标签信息
     * @return 返回label中所有信息
     */
    Label findByFeatures (String features);

    /**
     * 根据id查询标签信息
     * @param id id主键
     * @return 返回标签信息
     */
    Label findById(Integer id);
}

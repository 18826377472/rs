package com.cy.rs.service;

import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Label;
import com.cy.rs.entity.Performane;

import java.util.List;

public interface ILabelService {

    /**
     * 新增标签
     * @param label 标签信息
     */
    Label insert (Label label);

    /**
     * 修改标签信息
     * @param label 标签信息
     */
    Label update (Label label);

    /**
     * 根据id删除标签的方法
     * @param id id主键
     */
    void deleteById(Integer id);

    /**
     * 查询所有标签
     * @return 返回所有标签信息
     */
    List<Label> select();
}

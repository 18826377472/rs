package com.cy.rs.service;

import com.cy.rs.entity.User;

import java.util.List;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user  用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登入功能
     * @param username 用户名
     * @param password 密码
     * @return 返回当前匹配的用户数据，如果没有则返回null
     */
    User login(String username,String password);

    /**
     * 查询所有用户信息
     * @return 返回所有用户信息
     */
    List<User> select ();

    /**
     * 根据id修改密码
     * @param id id主键
     * @param password 密码
     */
    User updateByPassword(Integer id,String password);

    /**
     * 根据id修改用户名
     * @param id id主键
     * @param username 用户名
     */
    User updateByUsername(Integer id,String username);

    /**
     * 根据id删除用户
     * @param id id主键
     */
    void deleteById(Integer id);

    /**
     * 修改用户头像
     * @param id
     * @param avatar
     */
    void changeAvatar(Integer id,String avatar);

    User findById(Integer id);
}

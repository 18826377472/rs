package com.cy.rs.mapper;


import com.cy.rs.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户模块的持久化借口
 */
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户的数据
     * @return  受影响的行数
     */
    Integer insert (User user);

    /**
     * 根据用户名查询用户的数据
     * @param username  用户名
     * @return 找到则返回用户的数据，未找到则返回null
     */
    User findByUsername(String username);

    /**
     * 根据id查询用户信息
     * @param id id主键
     * @return 返回用户信息
     */
    User findById(Integer id);

    /**
     * 查询所有用户信息
     * @return 返回所有用户信息
     */
    List<User> select ();

    /**
     * 根据id修改密码
     * @param id id主键
     * @param password 新密码
     * @return 返回受影响的行数
     */
    Integer updateByPassword(@Param("id") Integer id,@Param("password")String password);

    /**
     * 根据id修改用户名
     * @param id id主键
     * @param username 用户名
     * @return 返回受影响的行数
     */
    Integer updateByUsername(@Param("id") Integer id,@Param("username")String username);

    Integer deleteById(Integer id);

    Integer updateAvatarByUid (@Param("id") Integer id,@Param("avatar")String avatar);
}

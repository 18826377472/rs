package com.cy.rs.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.cy.rs.entity.Label;
import com.cy.rs.entity.User;
import com.cy.rs.mapper.UserMapper;
import com.cy.rs.service.IUserService;
import com.cy.rs.service.ex.*;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户模块业务层的实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //获取用户名，根据用户名查询信息，判断该用户是否已经存在
        String username = user.getUsername();
        System.out.println("==================");
        User result = userMapper.findByUsername(username);
        //存在则抛出错误信息，不存在则进行下一步
        if(result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //密码加密处理:盐值+密码+盐值，加密三次
        String oldpassword = user.getPassword();
        //随机获取一个盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Password = getMd5Password(oldpassword, salt);
        //将加密过后的密码重新补全设置到user；
        user.setPassword(md5Password);
        //在插入数据之前先补全数据
        user.setRole("普通用户");
        Date date =new Date();
        user.setCreatedTime(date);
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("注册过程中发生了未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名查询用户的数据是否存在，不存在抛出异常
        User result = userMapper.findByUsername(username);
        System.out.println(result);
        if (result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //先获取数据库中的密码
        //获取数据库中的盐值
        //获取传递过来的密码
        //密码与盐值组合，调用md5加密算法进行加密
        //将密码进行比对
        String oldpassword = result.getPassword();
        String salt = result.getSalt();
        String newmd5password = getMd5Password(password, salt);
        if (!newmd5password.equals(oldpassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }

        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public List<User> select() {
        List<User> result = userMapper.select();
        return result;
    }

    /**
     * 根据id修改密码
     * @param id id主键
     * @param password 密码
     */
    @Override
    public User updateByPassword(Integer id, String password) {
        //根据id查询用户，获取原先密码，判断是否与原密码相同，判断该数据是否存在
        User user = userMapper.findById(id);
        if (user == null){
            throw new UserNotFoundException("该用户数据不存在，请联系管理员处理");
        }
        String ordpassword = user.getPassword();
        String salt = user.getSalt();
        String md5Password = getMd5Password(password, salt);
        if(md5Password.equals(ordpassword)){
            throw new PasswordDuplicatedException("新密码与原始密码相同，请重新输入");
        }
        Integer rows = userMapper.updateByPassword(id, md5Password);
        if(rows != 1){
            throw new UpdateExcetion("在修改密码过程中发生了未知的异常");
        }
        User user1 = userMapper.findById(id);
        return user1;
    }

    /**
     * 根据id修改用户名
     * @param id id主键
     * @param username 用户名
     */
    @Override
    public User updateByUsername(Integer id, String username) {
        //根据id查询用户，获取原先密码，判断是否与原密码相同，判断该数据是否存在
        User user = userMapper.findById(id);
        if (user == null){
            throw new UserNotFoundException("该用户数据不存在，请联系管理员处理");
        }
        String username1 = user.getUsername();
        if(username1.equals(username)){
            throw new UsernameDuplicatedException("与原先用户名相同，请重新输入");
        }
        Integer rows = userMapper.updateByUsername(id, username);
        if(rows != 1){
            throw new UpdateExcetion("在修改用户名过程中发生了未知的异常");
        }
        User user1 = userMapper.findById(id);
        return user1;
    }

    @Override
    public void deleteById(Integer id) {
        User result = userMapper.findById(id);
        if(result == null){
            throw new UserNotFoundException("该用户数据不存在，请联系管理员处理");
        }
        Integer rows = userMapper.deleteById(id);
        if(rows != 1){
            throw new DeleteExcetion("在删除标签信息过程中发生了未知的异常");
        }
    }

    @Override
    public void changeAvatar(Integer id, String avatar) {
        User result = userMapper.findById(id);
        if(result == null){
            throw new UserNotFoundException("该用户数据不存在，请联系管理员处理");
        }
        Integer rows = userMapper.updateAvatarByUid(id, avatar);
        if(rows != 1){
            throw new UpdateExcetion("在修改头像过程中发生了未知的异常");
        }
    }
    @Override
    public User findById(Integer id){
        User result = userMapper.findById(id);
        return result;
    }

    //定义一个密码加密方法
    private String getMd5Password(String password,String salt){
        for (int i=0;i<3;i++){
            password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        //放回加密之后的密码
        return password;
    }



}

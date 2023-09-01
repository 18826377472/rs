package com.cy.rs.controller;


import com.alibaba.fastjson.JSON;
import com.cy.rs.entity.User;
import com.cy.rs.entity.VerifyCode;
import com.cy.rs.service.IUserService;
import com.cy.rs.service.IVerifyCodeGen;
import com.cy.rs.service.impl.SimpleCharVerifyCodeGenImpl;
import com.cy.rs.util.*;
import io.swagger.annotations.ApiOperation;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("user")
@RestController//@RestController=@Controller+@ResponseBody
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;
    @ApiOperation("用户注册方法,只需要传入用户名和密码")
    @PostMapping("reg")
    public JsonResult<Void> reg(@RequestBody User user) {
        String username=user.getUsername();
        String role=user.getRole();
        String password=user.getPassword();
        System.out.println(username);
        System.out.println(role);
        System.out.println(password);
        userService.reg(user);
        return new JsonResult<>(Ok);
    }
//    @ApiOperation("用户登入方法，只需要传入用户名和密码")
//    @GetMapping("login")
//    public JsonResult<User> login(String username, String password){
//        User data = userService.login(username, password);
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpSession  session= ((ServletRequestAttributes)requestAttributes).getRequest().getSession();
//        session.setAttribute("uid",data.getId());
//        session.setAttribute("username",data.getUsername());
//        session.setAttribute("role",data.getRole());
//        String sessionId = session.getId();
//        Object username1 = session.getAttribute("username");
//        System.out.println(username1);
//        System.out.println(sessionId);
//        return new JsonResult<User>(Ok,data,sessionId);
//
//    }


    @ApiOperation("用户登入方法，只需要传入用户名和密码")
    @GetMapping("login")
    public JsonResult<Map<String,Object>> login(String username, String password){
        User data = userService.login(username, password);
        String token = UUID.randomUUID().toString();
        RedisUtils.INSTANCE.set(token, JSON.toJSONString(data),60*30);
        Map<String,Object> map =new HashMap<>();
        map.put("uToken",token);
        map.put("Username",data);
        return new JsonResult<Map<String, Object>>(Ok,map);

    }

    @ApiOperation("用户展示方法，不需要传入参数")
    @GetMapping("select")
    public JsonResult<List<User>> select(){
        List<User> result = userService.select();
        return new JsonResult<List<User>>(Ok,result);

    }
    @ApiOperation("修改密码方法，需要传入id和密码")
    @GetMapping("updateByPassword")
    public JsonResult<User> updateByPassword( Integer id,String password){
        User user = userService.updateByPassword(id, password);
        return new JsonResult<User>(Ok,user);

    }
    @ApiOperation("修改用户名方法需要传入id和用户名")
    @GetMapping("updateByUsername")
    public JsonResult<User> updateByUsername( Integer id,String username){
        System.out.println(id);
        User user = userService.updateByUsername(id, username);
        return new JsonResult<User>(Ok,user);

    }

    @ApiOperation("用户信息删除方法，需要传入id")
    @DeleteMapping("delete")
    public JsonResult<Void> delete( Integer id) {
        userService.deleteById(id);
        return new JsonResult<>(Ok);
    }

    @GetMapping("/userInfo")
    public String userInfo(Integer id) {
        //从session获取User对象
        User user = userService.findById(id);
        //如果用户为空，则创建新的对象

        //否则直接进入用户信息页面
        return "userInfo";
    }


}

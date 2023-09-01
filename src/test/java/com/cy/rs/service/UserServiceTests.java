//package com.cy.rs.service;
//
//import com.cy.rs.entity.User;
//import com.cy.rs.service.ex.ServiceException;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserServiceTests {
//    @Autowired(required = false)
//    private IUserService userService;
//
//    @Test
//    public void reg(){
//        try {
//            User user =new User();
//            user.setUsername("王五");
//            user.setPassword("123456");
//            userService.reg(user);
//            System.out.println("=====================");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//
//        }
//    }
//    @Test
//    public void login(){
//        User user = userService.login("王五", "123456");
//        System.out.println(user);
//    }
//
//    @Test
//    public void updateByUsername(){
//        userService.updateByUsername(1, "张三");
//
//    }
//
//}

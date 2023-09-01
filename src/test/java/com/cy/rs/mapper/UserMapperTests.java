//package com.cy.rs.mapper;
//
//import com.cy.rs.entity.User;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserMapperTests {
//
//    @Autowired(required = false)
//    private UserMapper userMapper;
//
//    @Test
//    public void insert(){
//        User user =new User();
//        user.setUsername("张三");
//        user.setPassword("123456");
//        Integer rows = userMapper.insert(user);
//        System.out.println(rows);
//    }
//    @Test
//    public void findByUsername(){
//        User user =userMapper.findByUsername("张三");
//        System.out.println(user);
//    }
//
//    @Test
//    public void updateByPassword(){
//        Integer rows = userMapper.updateByPassword(1, "1234567");
//        System.out.println(rows);
//    }
//
//    @Test
//    public void updateByUsername(){
//        Integer rows = userMapper.updateByUsername(1, "李四");
//        System.out.println(rows);
//    }
//}

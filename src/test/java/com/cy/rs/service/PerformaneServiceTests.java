//package com.cy.rs.service;
//
//import com.cy.rs.entity.Employee;
//import com.cy.rs.entity.Performane;
//import com.cy.rs.mapper.EmployeeMapper;
//import com.cy.rs.mapper.PerformaneMapper;
//import com.cy.rs.service.ex.ServiceException;
//import com.cy.rs.service.impl.PerformaneServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class PerformaneServiceTests {
//
//    @Autowired(required = false)
//    private IPerformaneService performaneService;
//
////    @Autowired(required = false)
////    @Qualifier("employeeMapper")
////    private EmployeeMapper employeeMapper;
////
////    @Autowired(required = false)
////    @Qualifier("performaneMapper")
////    private PerformaneMapper performaneMapper;
//
//    @Test
//    public void insert(){
//        Performane performane = new Performane();
//        performane.setNumber(93125);
//        performane.setName("A125");
//        performane.setUnit("I单位");
//        performane.setPost("客户专员");
//        performane.setCreatedTime("2023-05");
//        performane.setScores(98.11);
//        Performane result = performaneService.insert(performane);
//        System.out.println(result);
//    }
//    @Test
//    public void update(){
//        Performane performane = new Performane();
//        performane.setId(10);
//        performane.setNumber(93128);
//        performane.setName("A128");
//        performane.setUnit("I单位");
//        performane.setPost("客户专员");
//        performane.setCreatedTime("2023-05");
//        performane.setScores(90.11);
//        Performane result = performaneService.update(performane);
//        System.out.println(result);
//    }
//    @Test
//    public void deleteById(){
//        performaneService.deleteById(10);
//    }
//    @Test
//    public void select(){
//        List<Performane> result = performaneService.select();
//        System.out.println(result);
//    }
//    @Test
//    public void findByUnitAndCondition(){
//        List<Performane> result = performaneService.findByPostAndCondition("客户专员", null);
//        System.out.println(result);
//    }
//    @Test
//    public void findByUnit(){
//        List<Performane> result = performaneService.findByPost("单位");
//        System.out.println(result);
//    }
//    @Test
//    public void findByNumber(){
//        List<Employee> result = performaneService.findByNumber(93131);
//        System.out.println(result);
//    }
//    @Test
//    public void findByName(){
//        List<Employee> result = performaneService.findByName("A131");
//        System.out.println(result);
//    }
////    @Test
////    public void findByNameAndCreatedTime(){
////        List<Employee> result = performaneService.findByNameAndCreatedTime("A131","2023-05");
////        System.out.println(result);
////    }
//    @Test
//    public void findByNumberAndCreatedTime(){
//        Employee result = performaneService.findByNumberAndCreatedTime(93131,"2023-05");
//        System.out.println(result);
//    }
//
//
//    @Test
//    public void findByPostAndCreatedTime(){
//        List<Performane> result = performaneService.findByPostAndCreatedTime("客户专员", "2023-05");
//        System.out.println(result);
//    }
//}

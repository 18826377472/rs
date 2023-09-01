//package com.cy.rs.mapper;
//
//import com.cy.rs.entity.Employee;
//import com.cy.rs.entity.Performane;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class PerformaneMapperTests {
//
//    @Autowired(required = false)
//    private PerformaneMapper performaneMapper;
//
////    @Test
////    public void findByNumber(){
////        List<Employee> result = performaneMapper.findByNumber(93131);
////        System.out.println(result);
////    }
//    @Test
//    public void findNumberAndCreatedTime(){
//        Performane result = performaneMapper.findNumberAndCreatedTime(93131, "2023-05");
//        System.out.println(result);
//    }
//    @Test
//    public void insert(){
//        Performane performane = new Performane();
//        performane.setNumber(93127);
//        performane.setName("A127");
//        performane.setUnit("I单位");
//        performane.setPost("客户专员");
//        performane.setCreatedTime("2023-05");
//        performane.setScores(96.11);
//        Integer rows = performaneMapper.insert(performane);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void select(){
//        List<Performane> result = performaneMapper.select();
//        System.out.println(result);
//    }
//
//    @Test
//    public void update(){
//        Performane performane = new Performane();
//        performane.setId(3);
//        performane.setCreatedTime("2023-07");
//        Integer rows = performaneMapper.update(performane);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void deleteById(){
//
//        Integer rows = performaneMapper.deleteById(3);
//        System.out.println(rows);
//    }
//
////    @Test
////    public void findByUnitAndName(){
////        List<Performane> result = performaneMapper.findByUnitAndName("I单位", "A131");
////        System.out.println(result);
////    }
////
////    @Test
////    public void findByUnitAndNumber(){
////        List<Performane> result = performaneMapper.findByUnitAndNumber("I单位", 93132);
////        System.out.println(result);
////    }
////    @Test
////    public void findByNumberAndCreatedTime(){
////        Employee result = performaneMapper.findByNumberAndCreatedTime(93131, "2023-05");
////        System.out.println(result);
////    }
//    @Test
//    public void findByFactorBesc(){
//        Double ss = performaneMapper.findByFactorAvg("客户专员","2023-05");
//        System.out.println(ss);
//    }
//
//    @Test
//    public void findCountNumber(){
//        Double ss = performaneMapper.findCountNumber("2023-05");
//        System.out.println(ss);
//    }
//    @Test
//    public void findCountPost(){
//        Double ss = performaneMapper.findCountPost("2023-05");
//        System.out.println(ss);
//    }
//    @Test
//    public void findAvgFactor(){
//        Double ss = performaneMapper.findAvgFactor("2023-05");
//        System.out.println(ss);
//    }
//    @Test
//    public void findFactorByname(){
//        List<Double> ss = performaneMapper.findFactorByname("A001", "2023-05");
//        System.out.println(ss);
//    }
//
//
//}

//package com.cy.rs.service;
//
//import com.cy.rs.entity.*;
//import com.cy.rs.service.ex.ServiceException;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class EmployeeServiceTests {
//
//    @Autowired(required = false)
//    private IEmployeeService employeeService;
//    @Test
//    public void insert(){
//        Employee result =null;
//        try {
//            Employee employee = new Employee();
//            employee.setNumber(931321);
//            employee.setCreatedTime("2023-05");
//            result = employeeService.insert(employee);
//            System.out.println("=====================");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//
//        }
//        System.out.println(result);
//    }
//    @Test
//    public void update(){
//        try {
//            Employee employee = new Employee();
//            employee.setId(134);
//            employee.setNumber(93133);
//            employee.setCreatedTime("2023-05");
//            employeeService.update(employee);
//            System.out.println("=====================");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//
//        }
//    }
//    @Test
//    public void delete(){
//        try {
//            employeeService.delete(133);
//            System.out.println("=====================");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//
//        }
//    }
//    @Test
//    public void findByCreatedTime(){
//        try {
//            List<Employee> result = employeeService.findByCreatedTime("2023-05");
//            System.out.println(result);
//            System.out.println("=====================");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//
//        }
//    }
//
//    @Test
//    public void findByName(){
//        try {
//            List<Employee> result = employeeService.findByName(1,15,"2023-05");
//            System.out.println(result);
//            System.out.println("=====================");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//
//        }
//    }
//
////    @Test
////    public void findByNameAndCreatedTime2(){
////        List<PostMessage> result = employeeService.PostCountFactor("A001", "2023-05");
////        System.out.println(result);
////    }
//
//    @Test
//    public void findByPostAndCreatedTime5(){
//        List<FeaturesMessage> result = employeeService.findByPostAndCreatedTime5("2023-05", "信息专员");
//        System.out.println(result);
//    }
//    @Test
//    public void findByUnitAndCondition(){
//        List<Employee> result = employeeService.findByPostAndCondition("客户专员", "A131");
//        System.out.println(result);
//    }
//
//    @Test
//    public void terminalSpecialistFeaturesMessage(){
//        Map<String, Map<String, Map<String, Double>>> result = employeeService.terminalSpecialistFeaturesMessage("终端专员", "2023-05");
//        System.out.println(result);
//    }
//    @Test
//    public void informationCommissionerFeaturesMessage(){
//        Map<String, Map<String, Map<String, Double>>> result = employeeService.informationCommissionerFeaturesMessage("信息专员", "2023-05");
//        System.out.println(result);
//    }
//
//}

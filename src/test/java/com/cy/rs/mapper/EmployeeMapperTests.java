//package com.cy.rs.mapper;
//
//import com.cy.rs.entity.Employee;
//import com.cy.rs.entity.User;
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
//public class EmployeeMapperTests {
//
//    @Autowired(required = false)
//    private EmployeeMapper employeeMapper;
//
//    @Test
//    public void insert(){
//        Employee employee = new Employee();
//        employee.setAge(20);
//        employee.setEight("是");
//        employee.setNumber(93131);
//        employee.setCreatedTime("2023-05");
//        Integer rows = employeeMapper.insert(employee);
//        System.out.println(rows);
//    }
//    @Test
//    public void findByNumberAndCreatedTime(){
//        Integer number =11001;
//        String createdTime="2023-05";
//        Employee employee =employeeMapper.findByNumberAndCreatedTime(number,createdTime);
//        System.out.println(employee);
//    }
//
//    @Test
//    public void findByNumber(){
//        List<Employee> result = employeeMapper.findByNumber2(11001);
//        System.out.println(result);
//    }
//    @Test
//    public void updateById(){
//        Employee employee = employeeMapper.findById(1);
//        System.out.println(employee);
//        employee.setUnit("B单位");
//        System.out.println(employee);
//        Integer rows = employeeMapper.updateById(employee);
//        System.out.println(rows);
//    }
//    @Test
//    public void findById(){
//        Employee employee = employeeMapper.findById(1);
//        System.out.println("+++++++++++++++");
//        System.out.println(employee);
//    }
//    @Test
//    public void findByCreatedTime(){
//        List<Employee> list = employeeMapper.findByCreatedTime("2023-05");
//        System.out.println(list);
//    }
//
//    @Test
//    public void deleteById(){
//        Integer rows = employeeMapper.deleteById(132);
//        System.out.println(rows);
//    }
//    @Test
//    public void select(){
//        List<Employee> select = employeeMapper.select();
//        System.out.println(select);
//    }
//
//    @Test
//    public void findAvgAge(){
//        Double ss = employeeMapper.findAvgAge("2023-05");
//        System.out.println(ss);
//    }
//    @Test
//    public void overallPortrait(){
//        List<Integer> result = employeeMapper.overallPortrait("2023-05", "A单位", null, null, null, null, null);
//        System.out.println(result);
//    }
//
//    @Test
//    public void selectByCreatedTime(){
//        List<String> result = employeeMapper.selectByCreatedTime();
//        System.out.println(result);
//    }
//
//
//}

//package com.cy.rs.service;
//
//import com.cy.rs.entity.Employee;
//import com.cy.rs.entity.Label;
//import com.cy.rs.service.ex.ServiceException;
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
//public class LabelServiceTests {
//
//    @Autowired(required = false)
//    private ILabelService labelService;
//
//    @Test
//    public void insert(){
//        Label label = new Label();
//        label.setFeatures("wahhhhhhhh");
//        Label result = labelService.insert(label);
//        System.out.println(result);
//    }
//    @Test
//    public void update(){
//        Label label = new Label();
//        label.setId(53);
//        label.setFeatures("zhuzhuzhu");
//        Label result = labelService.update(label);
//        System.out.println(result);
//    }
//    @Test
//    public void deleteById(){
//        labelService.deleteById(53);
//    }
//    @Test
//    public void select(){
//        List<Label> result = labelService.select();
//        System.out.println(result);
//    }
//}

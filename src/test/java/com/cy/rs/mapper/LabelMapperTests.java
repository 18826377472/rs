//package com.cy.rs.mapper;
//
//import com.cy.rs.entity.Label;
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
//public class LabelMapperTests {
//
//    @Autowired(required = false)
//    private LabelMapper labelMapper;
//
//    @Test
//    public void insert(){
//        Label label = new Label();
//        label.setFeatures("是否有体育特长");
//        Integer rows = labelMapper.insert(label);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void select(){
//        List<Label> result = labelMapper.select();
//        System.out.println(result);
//    }
//
//    @Test
//    public void update(){
//        Label label = new Label();
//        label.setId(1);
//        label.setFeatures("是否有艺术特长");
//        Integer rows = labelMapper.update(label);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void deleteById(){
//
//        Integer rows = labelMapper.deleteById(1);
//        System.out.println(rows);
//    }
//
//}

package com.cy.rs.service.impl;

import com.cy.rs.entity.Label;
import com.cy.rs.mapper.EmployeeMapper;
import com.cy.rs.mapper.LabelMapper;
import com.cy.rs.service.ILabelService;
import com.cy.rs.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaberServiceImpl implements ILabelService {

    @Autowired(required = false)
    private LabelMapper labelMapper;



    @Override
    public Label insert(Label label) {
        String features = label.getFeatures();
        Label result = labelMapper.findByFeatures(features);
        if(result != null){
            throw new FactorDuplicatedException("该标签已存在");
        }
        Integer rows = labelMapper.insert(label);
        if(rows != 1){
            throw new InsertException("在新增标签时发生了未知错误");
        }
        Label result1 = labelMapper.findByFeatures(features);
        return result1;
    }

    @Override
    public Label update(Label label) {
        Integer id = label.getId();
        Label result = labelMapper.findById(id);
        String features = label.getFeatures();
        String name = result.getName();
        if(result == null){
            throw new FeaturesNotFoundException("标签信息不存在，请联系管理员处理");
        }
        if(name != null){
            throw new FeaturesImportantException("改标签参与模型运算，不允许修改");
        }
        Integer rows = labelMapper.update(label);
        if(rows != 1){
            throw new UpdateExcetion("在修改标签信息过程中发生了未知的异常");
        }
        Label result1 = labelMapper.findByFeatures(features);
        return result1;
    }

    @Override
    public void deleteById(Integer id) {
        Label result = labelMapper.findById(id);
        String name = result.getName();
        if(result == null){
            throw new FeaturesNotFoundException("标签信息不存在，请联系管理员处理");
        }
        if(name != null){
            throw new FeaturesImportantException("改标签参与模型运算，不允许删除");
        }
        Integer rows = labelMapper.deleteById(id);
        if(rows != 1){
            throw new DeleteExcetion("在删除标签信息过程中发生了未知的异常");
        }
    }

    @Override
    public List<Label> select() {
        List<Label> select = labelMapper.select();
        return select;
    }
}

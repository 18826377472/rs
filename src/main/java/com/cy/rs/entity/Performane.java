package com.cy.rs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel("员工绩效实体类")
public class Performane implements Serializable {
    @ExcelProperty("序号")
    @ApiModelProperty("id主键")
    private Integer id;

    @ExcelProperty("员工编号")
    @ApiModelProperty("员工编号")
    private Integer number;

    @ExcelProperty("员工姓名")
    @ApiModelProperty("员工姓名")
    private String name;

    @ExcelProperty("员工单位")
    @ApiModelProperty("员工单位")
    private String unit;

    @ExcelProperty("员工岗位")
    @ApiModelProperty("员工岗位")
    private String post;

    @ExcelProperty("日期")
    @ApiModelProperty("日期")
    private String createdTime;

    @ExcelProperty("绩效")
    @ApiModelProperty("绩效")
    private Double scores;

    @ExcelProperty("匹配系数")
    @ApiModelProperty("匹配系数")
    private Double factor;

    @ExcelProperty("员工Id,新增数据时不用管")
    @ApiModelProperty("员工Id,新增数据时不用管")
    private Integer employeeId;

    public Performane() {
    }

    public Performane(Integer number, String name, String unit, String post, String createdTime, Double scores, Double factor) {
        this.number = number;
        this.name = name;
        this.unit = unit;
        this.post = post;
        this.createdTime = createdTime;
        this.scores = scores;
        this.factor = factor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Double getScores() {
        return scores;
    }

    public void setScores(Double scores) {
        this.scores = scores;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Performane)) return false;

        Performane that = (Performane) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getUnit() != null ? !getUnit().equals(that.getUnit()) : that.getUnit() != null) return false;
        if (getPost() != null ? !getPost().equals(that.getPost()) : that.getPost() != null) return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals(that.getCreatedTime()) : that.getCreatedTime() != null)
            return false;
        if (getScores() != null ? !getScores().equals(that.getScores()) : that.getScores() != null) return false;
        if (getFactor() != null ? !getFactor().equals(that.getFactor()) : that.getFactor() != null) return false;
        return getEmployeeId() != null ? getEmployeeId().equals(that.getEmployeeId()) : that.getEmployeeId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getScores() != null ? getScores().hashCode() : 0);
        result = 31 * result + (getFactor() != null ? getFactor().hashCode() : 0);
        result = 31 * result + (getEmployeeId() != null ? getEmployeeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Performane{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", post='" + post + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", scores=" + scores +
                ", factor=" + factor +
                ", employeeId=" + employeeId +
                '}';
    }
}

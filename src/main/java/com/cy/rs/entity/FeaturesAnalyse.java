package com.cy.rs.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

@ApiModel("员工绩效实体类")
public class FeaturesAnalyse implements Serializable{
    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("岗位优秀标签")
    private String[] PostGoodFeaturesMessage;
    @ApiModelProperty("员工优秀标签")
    private String[] EmployeeGoodFeaturesMessage;
    @ApiModelProperty("员工一般标签")
    private String[] EmployeeBadFeaturesMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getPostGoodFeaturesMessage() {
        return PostGoodFeaturesMessage;
    }

    public void setPostGoodFeaturesMessage(String[] postGoodFeaturesMessage) {
        PostGoodFeaturesMessage = postGoodFeaturesMessage;
    }

    public String[] getEmployeeGoodFeaturesMessage() {
        return EmployeeGoodFeaturesMessage;
    }

    public void setEmployeeGoodFeaturesMessage(String[] employeeGoodFeaturesMessage) {
        EmployeeGoodFeaturesMessage = employeeGoodFeaturesMessage;
    }

    public String[] getEmployeeBadFeaturesMessage() {
        return EmployeeBadFeaturesMessage;
    }

    public void setEmployeeBadFeaturesMessage(String[] employeeBadFeaturesMessage) {
        EmployeeBadFeaturesMessage = employeeBadFeaturesMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeaturesAnalyse)) return false;

        FeaturesAnalyse that = (FeaturesAnalyse) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getPostGoodFeaturesMessage(), that.getPostGoodFeaturesMessage())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getEmployeeGoodFeaturesMessage(), that.getEmployeeGoodFeaturesMessage())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getEmployeeBadFeaturesMessage(), that.getEmployeeBadFeaturesMessage());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getPostGoodFeaturesMessage());
        result = 31 * result + Arrays.hashCode(getEmployeeGoodFeaturesMessage());
        result = 31 * result + Arrays.hashCode(getEmployeeBadFeaturesMessage());
        return result;
    }

    @Override
    public String toString() {
        return "FeaturesAnalyse{" +
                "id=" + id +
                ", PostGoodFeaturesMessage=" + Arrays.toString(PostGoodFeaturesMessage) +
                ", EmployeeGoodFeaturesMessage=" + Arrays.toString(EmployeeGoodFeaturesMessage) +
                ", EmployeeBadFeaturesMessage=" + Arrays.toString(EmployeeBadFeaturesMessage) +
                '}';
    }
}

package com.cy.rs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel("标签实体类")
public class Label implements Serializable {
    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("标签名称")
    private String features;
    @ApiModelProperty("对应的字段名")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;

        Label label = (Label) o;

        if (getId() != null ? !getId().equals(label.getId()) : label.getId() != null) return false;
        if (getFeatures() != null ? !getFeatures().equals(label.getFeatures()) : label.getFeatures() != null)
            return false;
        return getName() != null ? getName().equals(label.getName()) : label.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFeatures() != null ? getFeatures().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", features='" + features + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.cy.rs.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("岗位信息实体类")
public class FeaturesMessage implements Serializable{

    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("标签名称")
    private String features;
    @ApiModelProperty("相关性系数")
    private Double weight;
    @ApiModelProperty("优秀标签名称")
    private String niceFeatures;
    @ApiModelProperty("优秀标签拥有人数")
    private Double niceNumber;
    @ApiModelProperty("较差标签名称")
    private String badFeatures;
    @ApiModelProperty("较差标签拥有人数")
    private Double badNumber;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getNiceFeatures() {
        return niceFeatures;
    }

    public void setNiceFeatures(String niceFeatures) {
        this.niceFeatures = niceFeatures;
    }

    public Double getNiceNumber() {
        return niceNumber;
    }

    public void setNiceNumber(Double niceNumber) {
        this.niceNumber = niceNumber;
    }

    public String getBadFeatures() {
        return badFeatures;
    }

    public void setBadFeatures(String badFeatures) {
        this.badFeatures = badFeatures;
    }

    public Double getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(Double badNumber) {
        this.badNumber = badNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeaturesMessage)) return false;

        FeaturesMessage that = (FeaturesMessage) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getFeatures() != null ? !getFeatures().equals(that.getFeatures()) : that.getFeatures() != null)
            return false;
        if (getWeight() != null ? !getWeight().equals(that.getWeight()) : that.getWeight() != null) return false;
        if (getNiceFeatures() != null ? !getNiceFeatures().equals(that.getNiceFeatures()) : that.getNiceFeatures() != null)
            return false;
        if (getNiceNumber() != null ? !getNiceNumber().equals(that.getNiceNumber()) : that.getNiceNumber() != null)
            return false;
        if (getBadFeatures() != null ? !getBadFeatures().equals(that.getBadFeatures()) : that.getBadFeatures() != null)
            return false;
        return getBadNumber() != null ? getBadNumber().equals(that.getBadNumber()) : that.getBadNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFeatures() != null ? getFeatures().hashCode() : 0);
        result = 31 * result + (getWeight() != null ? getWeight().hashCode() : 0);
        result = 31 * result + (getNiceFeatures() != null ? getNiceFeatures().hashCode() : 0);
        result = 31 * result + (getNiceNumber() != null ? getNiceNumber().hashCode() : 0);
        result = 31 * result + (getBadFeatures() != null ? getBadFeatures().hashCode() : 0);
        result = 31 * result + (getBadNumber() != null ? getBadNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FeaturesMessage{" +
                "id=" + id +
                ", features='" + features + '\'' +
                ", weight=" + weight +
                ", niceFeatures='" + niceFeatures + '\'' +
                ", niceNumber=" + niceNumber +
                ", badFeatures='" + badFeatures + '\'' +
                ", badNumber=" + badNumber +
                '}';
    }
}

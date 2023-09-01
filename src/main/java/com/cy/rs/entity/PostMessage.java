package com.cy.rs.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("岗位信息实体类")
public class PostMessage implements Serializable {
    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("岗位名称")
    private String post;
    @ApiModelProperty("匹配系数")
    private Double factor;
    @ApiModelProperty("最高匹配系数")
    private Double max;
    @ApiModelProperty("最低匹配系数")
    private Double min;
    @ApiModelProperty("平均匹配系数")
    private Double avg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostMessage)) return false;

        PostMessage that = (PostMessage) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getPost() != null ? !getPost().equals(that.getPost()) : that.getPost() != null) return false;
        if (getFactor() != null ? !getFactor().equals(that.getFactor()) : that.getFactor() != null) return false;
        if (getMax() != null ? !getMax().equals(that.getMax()) : that.getMax() != null) return false;
        if (getMin() != null ? !getMin().equals(that.getMin()) : that.getMin() != null) return false;
        return getAvg() != null ? getAvg().equals(that.getAvg()) : that.getAvg() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getFactor() != null ? getFactor().hashCode() : 0);
        result = 31 * result + (getMax() != null ? getMax().hashCode() : 0);
        result = 31 * result + (getMin() != null ? getMin().hashCode() : 0);
        result = 31 * result + (getAvg() != null ? getAvg().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostMessage{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", factor=" + factor +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                '}';
    }
}

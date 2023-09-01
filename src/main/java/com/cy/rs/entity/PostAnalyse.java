package com.cy.rs.entity;

import java.io.Serializable;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("岗位绩效信息实体类")
public class PostAnalyse implements Serializable {
    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("岗位名称")
    private String post;
    @ApiModelProperty("人数")
    private Integer count;
    @ApiModelProperty("人岗匹配系数")
    private Double peoplePostFactor;
    @ApiModelProperty("最高匹配系数")
    private Double maxFactor;
    @ApiModelProperty("最低匹配系数")
    private Double minFactor;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPeoplePostFactor() {
        return peoplePostFactor;
    }

    public void setPeoplePostFactor(Double peoplePostFactor) {
        this.peoplePostFactor = peoplePostFactor;
    }

    public Double getMaxFactor() {
        return maxFactor;
    }

    public void setMaxFactor(Double maxFactor) {
        this.maxFactor = maxFactor;
    }

    public Double getMinFactor() {
        return minFactor;
    }

    public void setMinFactor(Double minFactor) {
        this.minFactor = minFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostAnalyse)) return false;

        PostAnalyse that = (PostAnalyse) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getPost() != null ? !getPost().equals(that.getPost()) : that.getPost() != null) return false;
        if (getCount() != null ? !getCount().equals(that.getCount()) : that.getCount() != null) return false;
        if (getPeoplePostFactor() != null ? !getPeoplePostFactor().equals(that.getPeoplePostFactor()) : that.getPeoplePostFactor() != null)
            return false;
        if (getMaxFactor() != null ? !getMaxFactor().equals(that.getMaxFactor()) : that.getMaxFactor() != null)
            return false;
        return getMinFactor() != null ? getMinFactor().equals(that.getMinFactor()) : that.getMinFactor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getCount() != null ? getCount().hashCode() : 0);
        result = 31 * result + (getPeoplePostFactor() != null ? getPeoplePostFactor().hashCode() : 0);
        result = 31 * result + (getMaxFactor() != null ? getMaxFactor().hashCode() : 0);
        result = 31 * result + (getMinFactor() != null ? getMinFactor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostAnalyse{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", count=" + count +
                ", peoplePostFactor=" + peoplePostFactor +
                ", maxFactor=" + maxFactor +
                ", minFactor=" + minFactor +
                '}';
    }
}

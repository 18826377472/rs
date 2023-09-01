package com.cy.rs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel("岗位实体类")
public class Post implements Serializable {
    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("岗位名称")
    private String post;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post1 = (Post) o;

        if (getId() != null ? !getId().equals(post1.getId()) : post1.getId() != null) return false;
        return getPost() != null ? getPost().equals(post1.getPost()) : post1.getPost() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post='" + post + '\'' +
                '}';
    }
}

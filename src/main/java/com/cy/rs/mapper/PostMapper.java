package com.cy.rs.mapper;

import com.cy.rs.entity.Post;

import java.util.List;

public interface PostMapper {

    List<Post> findByPost(String post);
}

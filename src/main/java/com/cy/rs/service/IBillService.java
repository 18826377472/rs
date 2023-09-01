package com.cy.rs.service;

import com.cy.rs.entity.Bill;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBillService {

    Integer addToDatabase(MultipartFile file) throws IOException;
    List<Bill> findById(Integer id);
    List<Bill> select();

}

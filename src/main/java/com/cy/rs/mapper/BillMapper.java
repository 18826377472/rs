package com.cy.rs.mapper;

import com.cy.rs.entity.Bill;

import java.util.List;

public interface BillMapper {

    Integer addBillExcelFileToDatabase(Bill bill);
    List<Bill> findById(Integer id);
    List<Bill> select();

}

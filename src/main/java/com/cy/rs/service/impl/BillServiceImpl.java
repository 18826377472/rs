package com.cy.rs.service.impl;

import com.cy.rs.entity.Bill;
import com.cy.rs.mapper.BillMapper;
import com.cy.rs.service.IBillService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {

    @Autowired(required = false)
    private BillMapper billMapper;

    public Integer addToDatabase(MultipartFile file) throws IOException {
        int result = 0;
        List<Bill> billList = new ArrayList<>();                    // 创建billList

        String fileName = file.getOriginalFilename();                           // 获取上传的文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);    // 获取上传文件后缀

        System.out.println("文件后缀为：" + suffix);
        InputStream inputStream = file.getInputStream();            // 输入流读取文件
        Workbook workbook = null;                                   // 1. 新建工作簿

        if(suffix.equals("xlsx")){
            workbook = new XSSFWorkbook(inputStream);               // Excel2007版本及以后 用XSSF(即Poi-ooxml)处理
        } else {
            workbook = new HSSFWorkbook(inputStream);               // Excel2003版本 用HSSF(即Poi)处理
        }

        Sheet sheet = workbook.getSheetAt(0);                     // 2. 获取当前工作表

        if(sheet != null){
            for (int i = 1; i <= sheet.getLastRowNum(); i++){       // 3. 从第二行开始遍历工作表的每一行
                Row row = sheet.getRow(i);
                if (row != null){
                    List<String> list = new ArrayList<>();
                    for (Cell cell : row){                          // 4. 遍历每一个单元格
                        if (cell != null) {
                            cell.setCellType(CellType.STRING);        // 将单元格值转化为字符串
                            String cellValue = cell.getStringCellValue();   // 获取字符串形式的值
                            list.add(cellValue);                            // 将值加入数组
                        }
                    }

                    // 值的转化
                    System.out.println(list.get(0));
                    Integer billYear = Integer.parseInt(list.get(0));
                    Integer billMonth = Integer.parseInt(list.get(1));
                    Integer buildingId = Integer.parseInt(list.get(2));
                    Integer roomId = Integer.parseInt(list.get(3));
                    Double waterUsed = Double.parseDouble(list.get(4));
                    Double waterFee = Double.parseDouble(list.get(5));
                    Double energyUsed = Double.parseDouble(list.get(6));
                    Double energyFee = Double.parseDouble(list.get(7));
                    Double totalFee = Double.parseDouble(list.get(8));
                    Integer paid = Integer.parseInt(list.get(9));

                    // 构造一个账单对象，并将从个单元格获取的值赋给它
                    Bill bill = new Bill();
                    bill.setBillYear(billYear);
                    bill.setBillMonth(billMonth);
                    bill.setBuildingId(buildingId);
                    bill.setRoomId(roomId);
                    bill.setWaterUsed(waterUsed);
                    bill.setWaterFee(waterFee);
                    bill.setEnergyUsed(energyUsed);
                    bill.setEnergyFee(energyFee);
                    bill.setTotalFee(totalFee);
                    bill.setPaid(paid);
                    billList.add(bill);                                 // 将新的一条账单加入billList
                }

            }

            for (Bill bill: billList){
                result = billMapper.addBillExcelFileToDatabase(bill);      // 将每一条账单插入数据库
            }
        }
        return result;
    }

    public List<Bill> findById(Integer id){
        List<Bill> result = billMapper.findById(id);
        return result;
    }
    public List<Bill> select(){
        List<Bill> result = billMapper.select();
        return result;
    }



}

package com.cy.rs.controller;



import com.alibaba.excel.EasyExcel;
import com.cy.rs.entity.Bill;
import com.cy.rs.service.IBillService;
import com.cy.rs.util.DownExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("bill")
@Controller
public class BillController extends BaseController {

    @Autowired
    private IBillService billService;

    @PostMapping("/upload")
    @ResponseBody
    public String excelImport(@RequestParam(value = "filename") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int result = 0;
        try {
            result = billService.addToDatabase(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            return "恭喜，账单导入成功！";
        } else {
            return "害！导入失败了...";
        }

    }

//    /**
//     * 根据用户名查询出的数据导出到excel文件，并存放到指定目录下
//     */
//    @RequestMapping("/export")
//    public JsonResult<Bill> exportExecl(@RequestParam Map<String,Object> params) throws IOException {
//        Integer id= (Integer) params.get("id");
//        Bill bill = new Bill();
//        bill.setId(id);
//        String path="";
//        String fileName = "用户信息数据";
//        //数据头
//        List<String> headerList = Arrays.asList("id","bill_year","bill_month","building_id","room_id","water_used","water_fee","energy_used","energy_fee","total_fee","paid");
//        //根据接收到的用户名查询用户数据
//        List<Bill> list = billService.findById(id);
//        if(list.size()>0){
//            ExportExcel ee = new ExportExcel(fileName, headerList);
//            //为每一行,每一个单元格赋值
//            for (int i = 0; i < list.size(); i++) {
//                Row row = ee.addRow();
//                ee.addCell(row,0,(list.get(i)).getId()+"");
//                ee.addCell(row,1,(list.get(i)).getBillYear());
//                ee.addCell(row,2,(list.get(i)).getBillMonth());
//                ee.addCell(row,3,(list.get(i)).getBuildingId());
//                ee.addCell(row,4,(list.get(i)).getRoomId());
//                ee.addCell(row,5,(list.get(i)).getWaterUsed());
//                ee.addCell(row,6,(list.get(i)).getWaterFee());
//                ee.addCell(row,7,(list.get(i)).getEnergyUsed());
//                ee.addCell(row,8,(list.get(i)).getEnergyFee());
//                ee.addCell(row,9,(list.get(i)).getTotalFee());
//                ee.addCell(row,10,(list.get(i)).getPaid());
//            }
//            //导出路径
//            fileName = fileName==""? DateUtils.dateTimeNow():fileName+DateUtils.dateTimeNow();
//            path = excel.getPath()+ fileName+".xlsx";
//            try {
//                ee.writeFile(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            ee.dispose();
//        }
//        //返回文件名称，从指定目录下载时需要用到此生成的excel文件名称
//        return R.ok().put("fileName",fileName);
//    }

    @GetMapping("/downloadexcel")
    public void getExcel(HttpServletResponse response) throws IllegalAccessException, IOException,
            InstantiationException {
        List<Bill> list = billService.select();
        DownExcel.download(response, Bill.class, list);


    }

}
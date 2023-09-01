package com.cy.rs.controller;

import com.cy.rs.entity.Employee;
import com.cy.rs.entity.Label;
import com.cy.rs.service.ILabelService;
import com.cy.rs.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("label")
@RestController//@RestController=@Controller+@ResponseBody
public class LabelController extends BaseController {

    @Autowired
    private ILabelService labelService;
    @ApiOperation("标签信息新建方法，需要传入标签信息")
    @PostMapping("insert")
    public JsonResult<Label> insert(@RequestBody Label label) {
        Label result = labelService.insert(label);
        return new JsonResult<Label>(Ok,result);
    }
    @ApiOperation("标签信息修改方法，需要传入id和标签信息")
    @PutMapping("update")
    public JsonResult<Void> update(@RequestBody Label label) {
        labelService.update(label);
        return new JsonResult<>(Ok);
    }
    @ApiOperation("标签信息删除方法，需要传入id")
    @DeleteMapping("delete")
    public JsonResult<Void> delete( Integer id) {
        labelService.deleteById(id);
        return new JsonResult<>(Ok);
    }
    @ApiOperation("标签信息查询方法")
    @GetMapping("select")
    public JsonResult<List<Label>> select() {
        List<Label> result = labelService.select();
        return new JsonResult<List<Label>>(Ok,result);
    }
}

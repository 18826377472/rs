package com.cy.rs.controller;

import com.cy.rs.controller.ex.*;
import com.cy.rs.service.ex.*;
import com.cy.rs.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    //操作成功的状态码
    public static final int Ok=200;
    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当项目产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result =new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户数据不存在");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("用户密码错误");
        } else if(e instanceof NumberDuplicatedException){
            result.setState(4003);
            result.setMessage("员工编号重复");
        }else if(e instanceof EmployeeNotFoundException){
            result.setState(4004);
            result.setMessage("员工信息不存在");
        }else if(e instanceof FeaturesDuplicatedException){
            result.setState(4005);
            result.setMessage("该标签已存在");
        }else if(e instanceof FeaturesNotFoundException){
            result.setState(4006);
            result.setMessage("该标签信息不存在");
        }else if(e instanceof FactorDuplicatedException){
            result.setState(4007);
            result.setMessage("此员工该月份绩效已存在");
        }else if(e instanceof PostNotFoundException){
            result.setState(4008);
            result.setMessage("该岗位信息不存在");
        } else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时发生未知异常");
        }else if(e instanceof InsertException){
            result.setState(5001);
            result.setMessage("新增员工信息时发生未知异常");
        }else if(e instanceof UpdateExcetion){
            result.setState(5002);
            result.setMessage("修改员工信息时发生未知异常");
        }else if(e instanceof DeleteExcetion){
            result.setState(5003);
            result.setMessage("删除员工信息时发生未知异常");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("上传的文件为空异常");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("上传的文件的大小有异常");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("上传的文件类型有异常");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("上传的文件状态异常");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("上传文件时读写异常");
        }else if (e instanceof ScoresNotFoundException) {
            result.setState(6005);
            result.setMessage("员工该月绩效信息不存在");
        }

        return result;
    }
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}

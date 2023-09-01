package com.cy.rs.controller;



import com.cy.rs.entity.VerifyCode;
import com.cy.rs.service.IVerifyCodeGen;
import com.cy.rs.service.impl.SimpleCharVerifyCodeGenImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class verifyCode{

    private static final Logger LOGGER = LoggerFactory.getLogger(verifyCode.class);
    String code;
    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            code = verifyCode.getCode();
            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        }
        catch (IOException e) {
            LOGGER.info("", e);
        }

    }

    @GetMapping(value ="/login")
    public String Login (String CODE) {
        if (CODE.equals(code))
            return "success";
        //如果验证码填写错误就刷新页面
        return "redirect:/";
    }


}


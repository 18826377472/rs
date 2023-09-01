package com.cy.rs.interceptor;

import com.alibaba.fastjson.JSON;
import com.cy.rs.util.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session是否有id数据，有则放行，没有则重定向到登入页面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        Object uid = request.getSession().getAttribute("uid");
//            if(uid == null){
//                //结束后续调用
//                PrintWriter writer = response.getWriter();
//
//                String jsonMap = new ObjectMapper().writeValueAsString("session-error");
//                response.setContentType("application/json;charset=UTF-8");
//                writer.write(jsonMap);
//                return false;
//            }
//
//        return true;
//        List<String> asList = Arrays.asList("/user/reg", "/user/login", "/swagger/**","/swagger-resources/**","/webjars/**","/v2/**","/swagger-ui.html/**","/swagger-*");
//        List<String> asList = Arrays.asList("/**");
        List<String> asList = Arrays.asList("/user/reg", "/user/login","/swagger/**");
        String uri = request.getRequestURI();
        //1.设置放行路径
        if(asList.contains(uri)){
            return true;
        }
        //2.拿到请求头里面的token（如果是第一次登录，那么是没有请求头的）
        String token = request.getHeader("authentication");
        if(token==null){
            response.setContentType("application/json; charset=utf-8");
            //2.1 拦截请求并返回信息给前台 （前台后置拦截器就是根据这里面返回的json数据，来判读并跳转到登录界面）
            PrintWriter writer = response.getWriter();
            Map<String,String> map = new HashMap<>();
            map.put("null","token-null");
            String json= JSON.toJSONString(map);
            System.out.println(json);
            writer.write(json);
//            response.getWriter().print("{\"success\":false,\"msg\":\"NoUser\"}");
            return false;
        }
        //3、如果有token，那么就根据这个token从redis查询登录用户信息，如果redis里面还没过期，那么就正常放行，没有就进行拦截，并返回信息，叫他重新登录
        String tokenUser = RedisUtils.INSTANCE.get(token);
        if(tokenUser==null){
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            Map<String,String> map = new HashMap<>();
            map.put("error","token-error");
            String json= JSON.toJSONString(map);
            System.out.println(json);
            writer.write(json);
//            response.getWriter().print("{\"success\":false,\"msg\":\"NoUser\"}");
            return false;
        }
        //4.如果没有过期，那么就重新将token和登录用户信息存到redis
        RedisUtils.INSTANCE.set(token, tokenUser, 60*30);
        return true;
    }
}

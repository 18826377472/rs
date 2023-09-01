package com.cy.rs.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    //状态码
    //描述信息
    //数据
    private Integer state;
    private E data;
    private String message;

    public JsonResult() {
    }

    public JsonResult(E data) {
        this.data = data;
    }
    public JsonResult(Throwable e){
        this.message=e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, E data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

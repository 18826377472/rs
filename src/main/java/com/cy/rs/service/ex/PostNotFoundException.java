package com.cy.rs.service.ex;
//岗位信息不存在
public class PostNotFoundException extends ServiceException{
    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostNotFoundException(Throwable cause) {
        super(cause);
    }

    public PostNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

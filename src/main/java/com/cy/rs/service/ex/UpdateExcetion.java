package com.cy.rs.service.ex;

/**
 * 数据在修改过程中发生未知的异常
 */
public class UpdateExcetion extends ServiceException{
    public UpdateExcetion() {
        super();
    }

    public UpdateExcetion(String message) {
        super(message);
    }

    public UpdateExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateExcetion(Throwable cause) {
        super(cause);
    }

    protected UpdateExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

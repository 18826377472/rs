package com.cy.rs.service.ex;

/**
 * 数据在删除过程中发生未知的异常
 */
public class DeleteExcetion extends ServiceException{
    public DeleteExcetion() {
        super();
    }

    public DeleteExcetion(String message) {
        super(message);
    }

    public DeleteExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteExcetion(Throwable cause) {
        super(cause);
    }

    protected DeleteExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

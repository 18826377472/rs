package com.cy.rs.service.ex;
//员工编号重复异常
public class NumberDuplicatedException extends ServiceException{
    public NumberDuplicatedException() {
        super();
    }

    public NumberDuplicatedException(String message) {
        super(message);
    }

    public NumberDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected NumberDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

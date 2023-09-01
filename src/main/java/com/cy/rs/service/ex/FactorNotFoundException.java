package com.cy.rs.service.ex;

/**
 * 员工该月份绩效信息不存在
 */
public class FactorNotFoundException extends ServiceException{
    public FactorNotFoundException() {
        super();
    }

    public FactorNotFoundException(String message) {
        super(message);
    }

    public FactorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected FactorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

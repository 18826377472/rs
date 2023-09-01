package com.cy.rs.service.ex;

/**
 * 员工该月份绩效已存在
 */
public class FactorDuplicatedException extends ServiceException{
    public FactorDuplicatedException() {
        super();
    }

    public FactorDuplicatedException(String message) {
        super(message);
    }

    public FactorDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactorDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected FactorDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

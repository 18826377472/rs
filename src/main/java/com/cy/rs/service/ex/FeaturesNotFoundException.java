package com.cy.rs.service.ex;
/**
 * 标签信息不存在
 */
public class FeaturesNotFoundException extends ServiceException{

    public FeaturesNotFoundException() {
        super();
    }

    public FeaturesNotFoundException(String message) {
        super(message);
    }

    public FeaturesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeaturesNotFoundException(Throwable cause) {
        super(cause);
    }

    protected FeaturesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

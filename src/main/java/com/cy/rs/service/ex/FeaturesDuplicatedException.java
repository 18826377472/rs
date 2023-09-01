package com.cy.rs.service.ex;
/**
 * 该标签已存在
 */
public class FeaturesDuplicatedException extends ServiceException{
    public FeaturesDuplicatedException() {
        super();
    }

    public FeaturesDuplicatedException(String message) {
        super(message);
    }

    public FeaturesDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeaturesDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected FeaturesDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.cy.rs.service.ex;
/**
 * 该标签已参与运算不允许删除
 */
public class FeaturesImportantException extends ServiceException{
    public FeaturesImportantException() {
        super();
    }

    public FeaturesImportantException(String message) {
        super(message);
    }

    public FeaturesImportantException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeaturesImportantException(Throwable cause) {
        super(cause);
    }

    protected FeaturesImportantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

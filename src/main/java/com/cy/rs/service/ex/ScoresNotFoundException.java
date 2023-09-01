package com.cy.rs.service.ex;
//员工该月绩效信息不存在
public class ScoresNotFoundException extends ServiceException{
    public ScoresNotFoundException() {
        super();
    }

    public ScoresNotFoundException(String message) {
        super(message);
    }

    public ScoresNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScoresNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ScoresNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

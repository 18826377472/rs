package com.cy.rs.service.ex;

/**
 * 密码重复异常（用户输入密码与原密码相同）
 */
public class PasswordDuplicatedException extends ServiceException{

    public PasswordDuplicatedException() {
        super();
    }

    public PasswordDuplicatedException(String message) {
        super(message);
    }

    public PasswordDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected PasswordDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

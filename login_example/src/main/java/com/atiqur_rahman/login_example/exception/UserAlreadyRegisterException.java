package com.atiqur_rahman.login_example.exception;

/**
 * Created by salem on 23/10/2018.
 */
public class UserAlreadyRegisterException extends Exception {

    public UserAlreadyRegisterException() {
    }

    public UserAlreadyRegisterException(String message) {
        super(message);
    }

    public UserAlreadyRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyRegisterException(Throwable cause) {
        super(cause);
    }

    public UserAlreadyRegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

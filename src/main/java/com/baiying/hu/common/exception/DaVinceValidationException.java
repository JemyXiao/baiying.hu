package com.baiying.hu.common.exception;

/**
 * Created by jmx on 17/6/20.
 * 业务校验异常
 */
public class DaVinceValidationException extends RuntimeException {
    private int errorCode;
    private Object data;

    public DaVinceValidationException(int errorCode, Object data) {
        this.errorCode = errorCode;
        this.data = data;
    }

    public DaVinceValidationException() {
        super();
    }

    public DaVinceValidationException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DaVinceValidationException(String message, int errorCode, Object data) {
        super(message);
        this.errorCode = errorCode;
        this.data = data;
    }

    public DaVinceValidationException(String message, Throwable cause, int errorCode, Object data) {
        super(message, cause);
        this.errorCode = errorCode;
        this.data = data;
    }

    public DaVinceValidationException(Throwable cause, int errorCode, Object data) {
        super(cause);
        this.errorCode = errorCode;
        this.data = data;
    }

    public DaVinceValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode, Object data) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.data = data;
    }
}

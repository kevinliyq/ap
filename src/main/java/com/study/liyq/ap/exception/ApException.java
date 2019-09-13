package com.study.liyq.ap.exception;

public class ApException extends RuntimeException {

    private int errorCode;
    private String errorMsg = "Internal Error";

    public ApException(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public ApException(String message, int errorCode, String errorMsg) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ApException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ApException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

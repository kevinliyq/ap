package com.study.liyq.ap.exception;

public enum ErrorEnum {
    NO_ERROR(null, ""),
    INTERNAL_ERROR(1, "Internal Error"),
    INVALID_REQUEST(2, "payload is invalid"),
    NO_FOUND(3, "Result is not found")
    ;

    private Integer errorCode;
    private String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg)
    {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

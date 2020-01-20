package com.springboot.robot.common.exception;

public class SystemException extends RuntimeException {
    private int code;
    //资源不足时的特殊描述
    private String value;

    SystemException(int code) {
        this.code = code;
    }

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

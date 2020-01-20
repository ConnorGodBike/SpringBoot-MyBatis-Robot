package com.springboot.robot.common.exception;

/**
 * 手机号码不存在
 */
public class SMSSendException extends SystemException {
    public SMSSendException() {
        super(ErrorMapping.getCode(SMSSendException.class), ErrorMapping.getDefaultMessage(SMSSendException.class));
    }

    public SMSSendException(String message) {
        super(ErrorMapping.getCode(SMSSendException.class), message);
    }
}

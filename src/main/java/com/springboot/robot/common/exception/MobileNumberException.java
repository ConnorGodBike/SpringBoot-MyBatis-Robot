package com.springboot.robot.common.exception;

/**
 * 手机号码不存在
 */
public class MobileNumberException extends SystemException {
    public MobileNumberException() {
        super(ErrorMapping.getCode(MobileNumberException.class), ErrorMapping.getDefaultMessage(MobileNumberException.class));
    }

    public MobileNumberException(String message) {
        super(ErrorMapping.getCode(MobileNumberException.class), message);
    }
}

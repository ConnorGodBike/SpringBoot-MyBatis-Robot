package com.springboot.robot.common.exception;

/**
 * 无效参数
 */
public class InvalidParameterException extends SystemException {
    public InvalidParameterException() {
        super(ErrorMapping.getCode(InvalidParameterException.class), ErrorMapping.getDefaultMessage(InvalidParameterException.class));
    }

    public InvalidParameterException(String message) {
        super(ErrorMapping.getCode(InvalidParameterException.class), message);
    }
}

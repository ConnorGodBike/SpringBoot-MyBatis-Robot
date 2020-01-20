package com.springboot.robot.common.exception;

/**
 * 未知异常
 */
public class UnknowException extends SystemException {

    public UnknowException() {
        super(ErrorMapping.getCode(UnknowException.class), ErrorMapping.getDefaultMessage(UnknowException.class));
    }

    public UnknowException(String message) {
        super(ErrorMapping.getCode(UnknowException.class), message);
    }
}

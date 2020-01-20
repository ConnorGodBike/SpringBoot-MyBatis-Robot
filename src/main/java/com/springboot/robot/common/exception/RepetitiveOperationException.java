package com.springboot.robot.common.exception;

/**
 * 重复操作
 */
public class RepetitiveOperationException extends SystemException {
    public RepetitiveOperationException() {
        super(ErrorMapping.getCode(RepetitiveOperationException.class), ErrorMapping.getDefaultMessage(RepetitiveOperationException.class));
    }

    public RepetitiveOperationException(String message) {
        super(ErrorMapping.getCode(RepetitiveOperationException.class), message);
    }
}

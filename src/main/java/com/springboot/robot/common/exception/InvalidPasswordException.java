package com.springboot.robot.common.exception;

/**
 * 无效密码
 */
public class InvalidPasswordException extends SystemException {
    public InvalidPasswordException() {
        super(ErrorMapping.getCode(InvalidPasswordException.class), ErrorMapping.getDefaultMessage(InvalidPasswordException.class));
    }

    public InvalidPasswordException(String message) {
        super(ErrorMapping.getCode(InvalidPasswordException.class), message);
    }
}

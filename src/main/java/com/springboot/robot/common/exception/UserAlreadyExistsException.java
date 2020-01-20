package com.springboot.robot.common.exception;

public class UserAlreadyExistsException extends SystemException {
    public UserAlreadyExistsException() {
        super(ErrorMapping.getCode(UserAlreadyExistsException.class), ErrorMapping.getDefaultMessage(UserAlreadyExistsException.class));
    }

    public UserAlreadyExistsException(String message) {
        super(ErrorMapping.getCode(UserAlreadyExistsException.class), message);
    }
}

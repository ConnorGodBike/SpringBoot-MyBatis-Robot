package com.springboot.robot.common.exception;

/**
 * 用户注册异常
 */
public class UserRegisterException extends SystemException {

    public UserRegisterException() {
        super(ErrorMapping.getCode(UserRegisterException.class), ErrorMapping.getDefaultMessage(UserRegisterException.class));
    }

    public UserRegisterException(String message) {
        super(ErrorMapping.getCode(UserRegisterException.class), message);
    }
}

package com.springboot.robot.common.exception;

/**
 * 冗余用户
 */
public class RedundantUserException extends SystemException {
    public RedundantUserException() {
        super(ErrorMapping.getCode(RedundantUserException.class), ErrorMapping.getDefaultMessage(RedundantUserException.class));
    }

    public RedundantUserException(String message) {
        super(ErrorMapping.getCode(RedundantUserException.class), message);
    }
}

package com.springboot.robot.common.exception;

/**
 * 手机号码不存在
 */
public class MobileNotExistsException extends SystemException {
    public MobileNotExistsException() {
        super(ErrorMapping.getCode(MobileNotExistsException.class), ErrorMapping.getDefaultMessage(MobileNotExistsException.class));
    }

    public MobileNotExistsException(String message) {
        super(ErrorMapping.getCode(MobileNotExistsException.class), message);
    }
}

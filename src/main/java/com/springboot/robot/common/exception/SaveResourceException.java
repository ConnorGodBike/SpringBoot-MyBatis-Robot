package com.springboot.robot.common.exception;

/**
 * 保存资源异常
 */
public class SaveResourceException extends SystemException {

    public SaveResourceException() {
        super(ErrorMapping.getCode(SaveResourceException.class), ErrorMapping.getDefaultMessage(SaveResourceException.class));
    }

    public SaveResourceException(String message) {
        super(ErrorMapping.getCode(SaveResourceException.class), message);
    }

}

package com.springboot.robot.common.exception;

/**
 * 作者：Connor on 2018/7/2
 * 说明：资源不足
 */
public class ResourceInsufficientException extends SystemException{

    public ResourceInsufficientException() {
        super(ErrorMapping.getCode(ResourceInsufficientException.class), ErrorMapping.getDefaultMessage(ResourceInsufficientException.class));
    }

    public ResourceInsufficientException(String message) {
        super(ErrorMapping.getCode(ResourceInsufficientException.class), message);
    }

    public ResourceInsufficientException(String message , String desc) {
        super(ErrorMapping.getCode(ResourceInsufficientException.class), message);
        setValue(desc);
    }

}

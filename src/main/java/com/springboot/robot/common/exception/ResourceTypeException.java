package com.springboot.robot.common.exception;

/**
 * 作者：Connor on 2018/7/2
 * 说明：资源类型不合法
 */
public class ResourceTypeException extends SystemException{

    public ResourceTypeException() {
        super(ErrorMapping.getCode(ResourceTypeException.class), ErrorMapping.getDefaultMessage(ResourceTypeException.class));
    }

    public ResourceTypeException(String message) {
        super(ErrorMapping.getCode(ResourceTypeException.class), message);
    }
}

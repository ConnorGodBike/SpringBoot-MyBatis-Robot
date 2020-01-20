package com.springboot.robot.common.exception;

/**
 * 资源状态异常（状态不允许修改等）
 */
public class ResourceStatusException extends SystemException {

    public ResourceStatusException() {
        super(ErrorMapping.getCode(ResourceStatusException.class), ErrorMapping.getDefaultMessage(ResourceStatusException.class));
    }

    public ResourceStatusException(String message) {
        super(ErrorMapping.getCode(ResourceStatusException.class), message);
    }

}

package com.springboot.robot.common.exception;


public class ResourceNotExistsException extends SystemException {

    public ResourceNotExistsException() {
        super(ErrorMapping.getCode(ResourceNotExistsException.class), ErrorMapping.getDefaultMessage(ResourceNotExistsException.class));
    }

    public ResourceNotExistsException(String message) {
        super(ErrorMapping.getCode(ResourceNotExistsException.class), message);
    }

}

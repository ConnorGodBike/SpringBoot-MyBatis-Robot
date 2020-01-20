package com.springboot.robot.common.exception;

/**
 * 服务器内部异常
 */
public class ServiceException extends SystemException {

    public ServiceException() {
        super(ErrorMapping.getCode(ServiceException.class), ErrorMapping.getDefaultMessage(ServiceException.class));
    }

    public ServiceException(String message) {
        super(ErrorMapping.getCode(ServiceException.class), message);
    }

}

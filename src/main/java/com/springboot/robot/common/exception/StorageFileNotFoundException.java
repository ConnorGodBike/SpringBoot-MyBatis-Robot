package com.springboot.robot.common.exception;

public class StorageFileNotFoundException extends SystemException {

    public StorageFileNotFoundException() {
        super(ErrorMapping.getCode(StorageFileNotFoundException.class), ErrorMapping.getDefaultMessage(StorageFileNotFoundException.class));
    }

    public StorageFileNotFoundException(String message) {
        super(ErrorMapping.getCode(StorageFileNotFoundException.class), message);
    }
}
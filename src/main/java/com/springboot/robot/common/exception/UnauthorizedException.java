package com.springboot.robot.common.exception;

/**
 * 创 建 人：Connor
 * 创建时间：2017/10/26 8:43
 * 说    明：未授权异常
 */
public class UnauthorizedException extends SystemException {
    public UnauthorizedException() {
        super(ErrorMapping.getCode(UnauthorizedException.class), ErrorMapping.getDefaultMessage(UnauthorizedException.class));
    }

    public UnauthorizedException(String message) {
        super(ErrorMapping.getCode(UnauthorizedException.class), message);
    }
}

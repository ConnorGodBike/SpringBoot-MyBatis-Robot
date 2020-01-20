package com.springboot.robot.common.exception;

/**
 * 创 建 人：Connor
 * 创建时间：2017/10/30 10:23
 * 说    明：关联资源异常
 */
public class RelevanceResourceException extends SystemException {
    public RelevanceResourceException() {
        super(ErrorMapping.getCode(RelevanceResourceException.class), ErrorMapping.getDefaultMessage(RelevanceResourceException.class));
    }

    public RelevanceResourceException(String message) {
        super(ErrorMapping.getCode(RelevanceResourceException.class), message);
    }
}

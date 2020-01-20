package com.springboot.robot.common.rabbitmq.entity;

import java.io.Serializable;

/**
 * @author Connor
 * websocket订单推送的实体类
 */
public class WebSocketOrderDelayEntity implements Serializable {

    private int times;

    private String messageId;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}

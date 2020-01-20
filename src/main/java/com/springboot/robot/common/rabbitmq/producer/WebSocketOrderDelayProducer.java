package com.springboot.robot.common.rabbitmq.producer;

import com.springboot.robot.common.rabbitmq.config.WebSocketOrderDelayConfig;
import com.springboot.robot.common.utils.LogUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Connor
 * 2019年12月4日10:45:38
 * socket消息回复的生产者
 */
@Component
public class WebSocketOrderDelayProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message, int delay) {
        rabbitTemplate.setMandatory(true);
        LogUtil.debug(WebSocketOrderDelayProducer.class, "收到socket消息回复延迟消息 " + message);
        this.rabbitTemplate.convertAndSend(WebSocketOrderDelayConfig.DELAY_EXCHANGE_WEBSOCKET_ORDER, WebSocketOrderDelayConfig.DELAY_ROUTING_KEY_WEBSOCKET_ORDER, message, messagePost -> {
            messagePost.getMessageProperties().setDelay(delay);
            return messagePost;
        });
    }
}
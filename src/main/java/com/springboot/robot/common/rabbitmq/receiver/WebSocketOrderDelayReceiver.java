package com.springboot.robot.common.rabbitmq.receiver;

import com.rabbitmq.client.Channel;
import com.springboot.robot.common.rabbitmq.config.WebSocketOrderDelayConfig;
import com.springboot.robot.common.utils.LogUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Connor
 * 2019年12月4日10:45:38
 * socket消息回复的消费者
 */
@Component
@Configuration
public class WebSocketOrderDelayReceiver {

    @RabbitListener(queues = WebSocketOrderDelayConfig.DELAY_QUEUE_WEBSOCKET_ORDER)
    public void get(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        LogUtil.debug(WebSocketOrderDelayReceiver.class, "收到socket消息回复延时消息:" + message);
        try {
            // TODO
            // 消息签收
            channel.basicAck(tag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
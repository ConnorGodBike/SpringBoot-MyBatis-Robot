package com.springboot.robot.common.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Connor
 * 2019年12月4日 socket消息回复的延迟队列
 */
@Configuration
public class WebSocketOrderDelayConfig {

    public static final String DELAY_QUEUE_WEBSOCKET_ORDER = "queue.delay.websocket.order";
    public static final String DELAY_EXCHANGE_WEBSOCKET_ORDER = "exchange.delay.websocket.order";
    public static final String DELAY_ROUTING_KEY_WEBSOCKET_ORDER = "routing.delay.websocket.order";

    @Bean
    public Queue immediateQueueWebSocketOrderDelay() {
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(WebSocketOrderDelayConfig.DELAY_QUEUE_WEBSOCKET_ORDER, true);
    }

    @Bean
    public CustomExchange delayExchangeWebSocketOrderDelay() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        /**
         * 如果想要使用x-delayed-message，需要安装插件
         * 插件安装
         * https://www.cnblogs.com/wintercloud/p/10877234.html
         * 或百度搜rabbitmq延迟插件
         * rabbitmq_delayed_message_exchange
         * 需要注意的是，插件版本要和mq版本一致
         */
        return new CustomExchange(WebSocketOrderDelayConfig.DELAY_EXCHANGE_WEBSOCKET_ORDER, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotifyWebSocketOrderDelay() {
        return BindingBuilder.bind(immediateQueueWebSocketOrderDelay()).to(delayExchangeWebSocketOrderDelay()).with(WebSocketOrderDelayConfig.DELAY_ROUTING_KEY_WEBSOCKET_ORDER).noargs();
    }
}
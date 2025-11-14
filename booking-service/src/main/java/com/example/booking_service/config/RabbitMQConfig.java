package com.example.booking_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "booking.created.queue";
    @Bean
    public Queue bookingCreatedQueue() {
        // Tạo ra 1 queue tên là "booking.created.queue"
        // (durable = true: tin nhắn sẽ không bị mất nếu RabbitMQ restart)
        return new Queue(QUEUE_NAME, true);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        // Sử dụng Jackson để chuyển đổi tin nhắn sang định dạng JSON, giúp dễ dàng serialize/deserialize
        return new Jackson2JsonMessageConverter();
    }
}

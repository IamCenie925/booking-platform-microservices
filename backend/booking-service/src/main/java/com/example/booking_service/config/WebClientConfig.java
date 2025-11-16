package com.example.booking_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean
    @LoadBalanced // cho phép WebClient sử dụng LoadBalancer để gọi các dịch vụ khác bằng tên dịch vụ thay vì URL cố định
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}

package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // where message broker broadcasts user messages to
        config.setApplicationDestinationPrefixes("/app"); // prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // sockJs is a library that provides websocket like behaviour for browser that don't support web socket
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS(); // it registers the stomp endpoints : stomp protocol : http://localhost:8080/ws
    }
    
}

package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.dto.*;
import com.example.manager.WebsocketSessionManager;

import java.util.ArrayList;

@Controller
public class WebsocketController {

    private final SimpMessagingTemplate template;
    private final WebsocketSessionManager manager;

    @Autowired
    public WebsocketController(SimpMessagingTemplate template, WebsocketSessionManager manager) {
        this.template = template;
        this.manager = manager;
    }

    @MessageMapping("/message") // maps to /app/message
    public void handleMessage(Message message) {
        System.out.println("Received message from user: " + message.getUser() + ": " + message.getMessage());
        template.convertAndSend("/topic/messages", message);
        System.out.println("Sent message to /topic/messages: " + message.getUser() + ": " + message.getMessage());
    }

    @MessageMapping("/connect")
    public void connectUser(String username) {
        System.out.println(">>> testing out here");
        manager.addUsername(username);
        manager.broadcastActiveUsernames();
        System.out.println("Connected user: " + username);        
    }

    @MessageMapping("/disconnect")
    public void disconnectUser(String username) {
        manager.removeUsername(username);
        manager.broadcastActiveUsernames();
        System.out.println("Disconnected user: " + username);
    }

    @MessageMapping("/request-users")
    public void requestUsers() {
        manager.broadcastActiveUsernames();
        System.out.println("Requesting users...");
    }
}

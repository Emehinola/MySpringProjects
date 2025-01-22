package com.example.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketSessionManager {
    
    private final ArrayList<String> activeUsernames = new ArrayList<>();
    private final SimpMessagingTemplate template;

    @Autowired
    private WebsocketSessionManager(SimpMessagingTemplate template){
        this.template = template;
    }

    public void addUsername(String username) {
        this.activeUsernames.add(username);
        System.out.println("Username added successfully ✅");
    }

    public void removeUsername(String username) {
        this.activeUsernames.remove(username);
    }

    public void broadcastActiveUsernames() {
        template.convertAndSend("/topic/users", activeUsernames);
        System.out.println("Broadcasting active users to /topic/users 🚀" + activeUsernames);
    }
    
}

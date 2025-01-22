package com.example.client;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.example.dto.Message;

import java.util.*;

public class StompClient {
    
    private String username;
    private StompSession session;

    public StompClient(MessageListener messageListener, String username) throws ExecutionException, InterruptedException {
        this.username = username;

        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter()); // for serializing and deserializing data

        StompSessionHandler handler = new StompSessionHandler(messageListener, this.username);
        String url = "ws://localhost:8080/ws";

        session = stompClient.connectAsync(url, handler).get();
    }


    public void sendMessage(Message message) {
        try{
            session.send("/app/message", message);
            System.out.println("Message sent: " + message.getMessage());
        } catch(Exception e){
            System.out.println("Send message exception: >>> ");
            e.printStackTrace();
        }
    }

    public void disconnectUser(String username) {
        session.send("/app/disconnect", username);
        System.out.println("Client: Disconnected user: " + username);
    }
}
 
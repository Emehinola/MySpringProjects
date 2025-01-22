package com.example.client;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.example.dto.*;

public class StompSessionHandler extends StompSessionHandlerAdapter {

    private String username;
    private MessageListener messageListener;

    public StompSessionHandler(MessageListener messageListener, String username) {
        this.messageListener = messageListener;
        this.username = username;
    }
    
    // after successful connection
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

        System.out.println(">>>>>>> Client connected <<<<<<<<");
        session.send("/app/connect", this.username); // send message to /connect to add user to active users
        
        session.subscribe("/topic/messages", new StompFrameHandler() {
            
            // convert to the expected payload type: json -> Message object
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try {

                    if (payload instanceof Message message) {
                        messageListener.onMessageReceived(message);
                        System.out.println("Message received: " + message.getUser() + " " + message.getUser());
                    } else {
                        System.out.println("Unexpected payload type");
                    }

                } catch (Exception e) {
                    System.out.println(">>>> trace: ");
                    e.printStackTrace();
                }
            }
        });

        System.out.println(">>>>>>>>> Client subscribed to /topic/messages <<<<<<<<<<");

        session.subscribe("/topic/users", new StompFrameHandler() {
            
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return new ArrayList<String>().getClass();
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try{
                    if(payload instanceof ArrayList users){
                        ArrayList<String> activeUsers = (ArrayList<String>) users;
                        messageListener.onActiveUsersUpdated(activeUsers);
                        System.out.println("Received active users");
                    }
                } catch(Exception e) {

                }
            }
        });
        System.out.println("Subscribed to /topic/users");
        session.send("/app/request-users", "");
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println(">>>>>> Stack trace here");
        exception.printStackTrace();
    }
}

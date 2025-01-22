package com.example.client;

import java.util.ArrayList;

import com.example.dto.Message;

public interface MessageListener {
    void onMessageReceived(Message message);
    void onActiveUsersUpdated(ArrayList<String> users);
}

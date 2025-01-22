package com.example.client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.example.dto.Message;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClientGUI extends JFrame implements MessageListener {

    private JPanel connectedUsersPanel, messagePanel;
    private StompClient stompClient;
    private String username;
    private JScrollPane messageJScrollPane;
    
    public ClientGUI(String username) throws ExecutionException, InterruptedException {
        super("User: " + username);

        this.username = username;
        stompClient = new StompClient(this, this.username); // passing this cause our class implements it

        setSize(1218, 685);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e){
                final int option = JOptionPane.showConfirmDialog(ClientGUI.this, "Do you really want to close this window?", "Exit", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION) {
                    stompClient.disconnectUser(username);
                    ClientGUI.this.dispose();
                }
            }
        });

        getContentPane().setBackground(Utilities.PRIMARY_COLOR);
        addGUIComponents();
    }

    private void addGUIComponents() {
        addConnectedUsersComponents();
        addChatComponents();
    }

    private void addConnectedUsersComponents() {
        connectedUsersPanel = new JPanel();
        connectedUsersPanel.setBorder(Utilities.addPadding(10,10,10,10));
        connectedUsersPanel.setLayout(new BoxLayout(connectedUsersPanel, BoxLayout.Y_AXIS));
        connectedUsersPanel.setBackground(Utilities.SECONDARY_COLOR);
        connectedUsersPanel.setPreferredSize(new Dimension(200, getHeight()));

        JLabel connectedUsersLabel = new JLabel("Connected Users");
        connectedUsersLabel.setFont(new Font("Inter", Font.BOLD, 18));
        connectedUsersLabel.setForeground(Utilities.TEXT_COLOR);
        connectedUsersPanel.add(connectedUsersLabel);

        add(connectedUsersPanel, BorderLayout.WEST);
    }

    private void addChatComponents() {
        JPanel chatJPanel = new JPanel();
        chatJPanel.setLayout(new BorderLayout());
        chatJPanel.setBackground(Utilities.TRANSPARENT_COLOR);

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Utilities.TRANSPARENT_COLOR);

        messageJScrollPane = new JScrollPane(messagePanel);
        messageJScrollPane.setBackground(Utilities.TRANSPARENT_COLOR);
        messageJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        messageJScrollPane.getViewport().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                revalidate();
                repaint();
            }
        });

        chatJPanel.add(messageJScrollPane, BorderLayout.CENTER);

        JPanel inputJPanel = new JPanel();
        inputJPanel.setBorder(Utilities.addPadding(10, 10, 10, 10));
        inputJPanel.setLayout(new BorderLayout());
        inputJPanel.setBackground(Utilities.TRANSPARENT_COLOR);

        // input text field
        JTextField textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                String message = textField.getText();
                
                if (!message.isEmpty()) {
                    if(e.getKeyChar() == KeyEvent.VK_ENTER) { // enter button pressed
                        textField.setText("");
                        stompClient.sendMessage(new Message(username, message));
                    }
                }
            }
        });

        textField.setBackground(Utilities.SECONDARY_COLOR);
        textField.setForeground(Utilities.TEXT_COLOR);
        textField.setBorder(Utilities.addPadding(0, 10, 0, 10));
        textField.setFont(new Font("Inter", Font.PLAIN, 18));
        textField.setPreferredSize(new Dimension(getWidth(), 50));
        inputJPanel.add(textField, BorderLayout.CENTER);
        chatJPanel.add(inputJPanel, BorderLayout.SOUTH);
        
        add(chatJPanel, BorderLayout.CENTER);
    }

    public JPanel createChatMessageComponent(Message message) {
        JPanel chatMessage = new JPanel();
        chatMessage.setBackground(Utilities.TRANSPARENT_COLOR);
        chatMessage.setLayout(new BoxLayout(chatMessage, BoxLayout.Y_AXIS));
        chatMessage.setBorder(Utilities.addPadding(20, 20, 10, 20));

        JLabel usernameLabel = new JLabel(message.getUser());
        usernameLabel.setFont(new Font("Inter", Font.BOLD, 18));
        usernameLabel.setForeground(Utilities.TEXT_COLOR);
        chatMessage.add(usernameLabel);

        JLabel messageLabel = new JLabel(message.getMessage());
        messageLabel.setForeground(Utilities.TEXT_COLOR);
        messageLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        chatMessage.add(messageLabel);

        return chatMessage;
    }

    public void onMessageReceived(Message message){
        messagePanel.add(createChatMessageComponent(message));
        repaint();
        revalidate();

        messageJScrollPane.getVerticalScrollBar().setValue(Integer.MAX_VALUE);
    }

    public void onActiveUsersUpdated(ArrayList<String> users) {
        System.out.println("on active users updated: " + users);
        if(connectedUsersPanel.getComponents().length >= 2){
            connectedUsersPanel.remove(1);
        }

        JPanel usersListJPanel = new JPanel();
        usersListJPanel.setBackground(Utilities.TRANSPARENT_COLOR);
        usersListJPanel.setLayout(new BoxLayout(usersListJPanel, BoxLayout.Y_AXIS));

        // add users
        for (String user : users) {
            JLabel username = new JLabel();
            username.setText(user);
            username.setFont(new Font("Inter", Font.BOLD, 16));
            username.setBackground(Utilities.SECONDARY_COLOR);
            username.setForeground(Utilities.TEXT_COLOR);
            usersListJPanel.add(username);
        }

        connectedUsersPanel.add(usersListJPanel);
        repaint();
        revalidate();

    }

}
 
package com.example.client;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String username = JOptionPane.showInputDialog(null,
                        "Enter username", "Chat Application", JOptionPane.QUESTION_MESSAGE);
                if(username == null || username.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    ClientGUI gui = new ClientGUI(username);
                    gui.setVisible(true);
                }catch(Exception e){
                    //
                }
            }
        });
    }
}

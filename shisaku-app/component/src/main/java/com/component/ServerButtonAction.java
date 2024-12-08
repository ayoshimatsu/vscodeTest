package com.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;

public class ServerButtonAction extends JButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent aEvent) {
        System.out.println("button is pressed.");

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket socket = serverSocket.accept();
                try (PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
                    // Invoke only when client socket is connected.
                    System.out.println("Client socket is connected.");
                    writer.println("Hello. This is Server.");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } 
    }
}
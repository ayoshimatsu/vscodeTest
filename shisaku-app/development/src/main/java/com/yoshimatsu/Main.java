package com.yoshimatsu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        PropertiesParameter property = new PropertiesParameter(
            "/var/myconf/setting.properties");
        String user = property.getUser();
        String serverIpAddress = property.getServerIpAddress();

        System.out.println("User: " + user);
        System.out.println("Server IP address: " + serverIpAddress);

        if(user.equals("Server")) {
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
        } else {
            new ClientFrame(serverIpAddress).setVisible(true);
        }
    }
}
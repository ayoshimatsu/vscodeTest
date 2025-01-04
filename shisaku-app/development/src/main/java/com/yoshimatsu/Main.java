package com.yoshimatsu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        PropertiesParameter property = new PropertiesParameter("/var/myconf/setting.properties");
        String user = property.getUser();
        String serverIpAddress = property.getServerIpAddress();
        int serverPort = Integer.parseInt(property.getServerPort());

        // System.out.println("User: " + user);
        // System.out.println("Server IP address: " + serverIpAddress);
        // System.out.println("Server port: " + serverPort);

        if(user.equals("Server")) {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
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
            new ClientFrame(serverIpAddress, serverPort).setVisible(true);
        }
    }
}
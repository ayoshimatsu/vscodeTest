package com.yoshimatsu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start program!");

        PropertiesParameter property;
        // Read Properties file
        try {
            property = readPropertiesFile();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

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

    private static PropertiesParameter readPropertiesFile() throws IOException{
        Path linuxPath = Paths.get("/var/myconf/setting.properties");
        Path windowPath = Paths.get("/Users/yoshi/myconf/setting.properties");
        Path targetPath = null;

        if (Files.exists(linuxPath)){
            targetPath = linuxPath;
        } else if (Files.exists(windowPath)) {
            targetPath = windowPath;
        } else {
            System.out.println("properties file does not exist.");
        }

        if (targetPath == null) {
            throw new IOException("Can't find path: " + targetPath);
        }

        return new PropertiesParameter(targetPath.toString());
    }
}
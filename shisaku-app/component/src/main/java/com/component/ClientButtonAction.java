package com.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;

public class ClientButtonAction extends JButton implements ActionListener {

    private final String oServerIpAddress;
    private final int oServerPort;

    public ClientButtonAction(String aServerIpAddress, int aServerPort) {
        oServerIpAddress = aServerIpAddress;
        oServerPort = aServerPort;
    }

    @Override
    public void actionPerformed(ActionEvent aEvent) {
        System.out.println("button is pressed.");

        try {
            Socket clientSocket = new Socket(oServerIpAddress, oServerPort);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String message = reader.readLine();
                System.out.println("From server: " + message);
            }
        } catch (IOException e) {
            System.out.println(e);
        } 
    }
}
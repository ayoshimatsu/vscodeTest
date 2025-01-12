package com.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ClientButtonAction extends JButton implements ActionListener {

    private final String oServerIpAddress;
    private final int oServerPort;
    private final JPanel oDrawPanel;
    private boolean oIsServerConnected = false;

    public ClientButtonAction(String aServerIpAddress, int aServerPort, JPanel aDrawPanel) {
        oServerIpAddress = aServerIpAddress;
        oServerPort = aServerPort;
        oDrawPanel = aDrawPanel;
    }

    @Override
    public void actionPerformed(ActionEvent aEvent) {
        System.out.println("button is pressed.");

        try {
            Socket clientSocket = new Socket(oServerIpAddress, oServerPort);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                oIsServerConnected = true;
                String message = reader.readLine();
                System.out.println("From server: " + message);
                oDrawPanel.repaint();
            }
        } catch (IOException e) {
            oIsServerConnected = false;
            System.out.println(e);
        } 
    }

    public boolean isServerConnected() {
        return oIsServerConnected;
    }
}
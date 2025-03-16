package com.yoshimatsu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.component.ClientButtonAction;
import com.component.ServerButtonAction;

public class ClientFrame extends JFrame{

    private final JPanel oConnectButtonPanel;
    private final JButton oConnectButton1;
    private final ConnectLabel oConnectLabel;

    private final JPanel oSendMessagePane2;
    private final JTextArea oTextArea2;
    private final JButton oSendButton2;

    private final JPanel oReceiveMessagePane3;
    //private final JTextArea oTextArea;


    private boolean oIsConnectedServer = false;

    public ClientFrame(String aServerIpAddress, int aServerPort) {
        // JButton serverButton = new JButton("Start Server");  
        // serverButton.addActionListener(new ServerButtonAction());

        setTitle("Client window");
        setLayout(new BorderLayout());
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel 1. Connect Server
        oConnectButtonPanel = new JPanel();
        oConnectButtonPanel.setBackground(Color.ORANGE);
        oConnectButtonPanel.setPreferredSize(new Dimension(600, 100));
        oConnectButton1 = new JButton("Connect to Server");
        oConnectButton1.setPreferredSize(new Dimension(200, 50));
        oConnectButtonPanel.add(oConnectButton1);
        oConnectLabel = new ConnectLabel("Initial");
        oConnectButtonPanel.add(oConnectLabel);

        // Panel 2. Send Message
        oSendMessagePane2 = new JPanel();
        oSendMessagePane2.setBackground(Color.GREEN);
        oSendMessagePane2.setPreferredSize(new Dimension(600, 200));
        oTextArea2 = new JTextArea(7, 100);
        oTextArea2.setColumns(40);
        oSendMessagePane2.add(oTextArea2);
        oSendButton2 = new JButton("Send message");
        oSendButton2.setPreferredSize(new Dimension(200, 50));
        oSendMessagePane2.add(oSendButton2);

        // Panel 3. Display from partner
        oReceiveMessagePane3 = new JPanel();
        oReceiveMessagePane3.setBackground(Color.YELLOW);
        oReceiveMessagePane3.setPreferredSize(new Dimension(600, 200));


        // Set Panels
        getContentPane().add(BorderLayout.NORTH, oConnectButtonPanel);
        getContentPane().add(BorderLayout.CENTER, oSendMessagePane2);
        getContentPane().add(BorderLayout.SOUTH, oReceiveMessagePane3);

        oConnectButton1.addActionListener(event -> {
            System.out.println("Connection button is pressed.");
            try {
                Socket clientSocket = new Socket(aServerIpAddress, aServerPort);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    oIsConnectedServer = true;
                    String message = reader.readLine();
                    System.out.println("From server: " + message);
                    oConnectLabel.repaint();
                    clientSocket.close();
                }
            } catch (IOException error) {
                oIsConnectedServer = false;
                System.out.println(error);
                oConnectLabel.repaint();
            } 
        });

        oSendButton2.addActionListener(event -> {
            System.out.println("Send button is pressed.");
            try {
                Socket clientSocket = new Socket(aServerIpAddress, aServerPort);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    oIsConnectedServer = true;
                    String message = reader.readLine();
                    System.out.println("From server: " + message);
                    oConnectLabel.repaint();
                    clientSocket.close();
                }
            } catch (IOException error) {
                oIsConnectedServer = false;
                System.out.println(error);
                oConnectLabel.repaint();
            }

        });



    }

    private class ConnectLabel extends JLabel{

        public ConnectLabel(String alabalString) {
            super(alabalString);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            if (oIsConnectedServer) {
                setText("Connected to server");
            } else {
                setText("Not Connected to server");
            }
        }
    }


    private class DrawPanel extends JPanel{

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //g.setColor(Color.BLACK);
            // g.fillOval(0, 0, 50, 50);
            //g.drawString("?????", 10, 30);
        }
    }

}

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

    private final JButton oConnectButton;
    private final JPanel oConnectButtonPanel;
    private final DrawPanel oConnectDrawPanel;
    private final MyLabel oLabel;
    private final JTextArea oTextArea = new JTextArea();
    private boolean oIsConnectedServer = false;

    public ClientFrame(String aServerIpAddress, int aServerPort) {
        // JButton serverButton = new JButton("Start Server");  
        // serverButton.addActionListener(new ServerButtonAction());

        oConnectButton = new JButton("Connect to Server");
        oConnectButtonPanel = new JPanel();
        oConnectDrawPanel = new DrawPanel();
        oLabel = new MyLabel("Initial");

        setTitle("Client window");
        setLayout(new BorderLayout());
        oConnectButton.setPreferredSize(new Dimension(300, 50));
        oConnectButtonPanel.add(oConnectButton);
        getContentPane().add(BorderLayout.NORTH, oLabel);
        getContentPane().add(BorderLayout.CENTER, oConnectDrawPanel);
        getContentPane().add(BorderLayout.SOUTH, oConnectButtonPanel);
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        oConnectButton.addActionListener(event -> {
            System.out.println("button is pressed.");
            try {
                Socket clientSocket = new Socket(aServerIpAddress, aServerPort);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    oIsConnectedServer = true;
                    String message = reader.readLine();
                    System.out.println("From server: " + message);
                    oLabel.repaint();
                }
            } catch (IOException error) {
                oIsConnectedServer = false;
                System.out.println(error);
                oLabel.repaint();
            } 
        });
    }

    private class MyLabel extends JLabel{

        public MyLabel(String alabalString) {
            super(alabalString);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println(g);
            g.setColor(Color.BLACK);
            System.out.println("Repaint");
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
            g.setColor(Color.BLACK);
            System.out.println("Repaint-canvas");
            // g.fillOval(0, 0, 50, 50);
            g.drawString("?????", 0, 50);
        }
    }

}

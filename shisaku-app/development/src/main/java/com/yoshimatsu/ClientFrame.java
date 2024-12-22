package com.yoshimatsu;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.component.ClientButtonAction;
import com.component.ServerButtonAction;

public class ClientFrame extends JFrame{

    public ClientFrame(String aServerIpAddress) {
        // JButton serverButton = new JButton("Start Server");  
        // serverButton.addActionListener(new ServerButtonAction());

        JButton clientButton = new JButton("Start client");  
        clientButton.addActionListener(new ClientButtonAction(aServerIpAddress));

        // getContentPane().add(BorderLayout.WEST, serverButton);
        getContentPane().add(BorderLayout.EAST, clientButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 500);
    }

}
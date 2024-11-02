package com.yoshimatsu;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.component.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        JButton button1 = new JButton("test");  
        button1.addActionListener(new ButtonAction());

        getContentPane().add(BorderLayout.WEST, button1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 500);
    }

}
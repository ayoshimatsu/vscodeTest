package com.yoshimatsu;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame frame = new MainFrame();

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
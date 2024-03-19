package com.component;

import java.awt.event.*;
import javax.swing.JButton;

public class ButtonAction extends JButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent aEvent) {
        System.out.println("button is pressed.");
    }

}
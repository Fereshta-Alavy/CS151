package com.company;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;


public class Main {



    public static void main(String[] args) {
	// write your code here

        JFrame frame = new JFrame();
        JButton Red = new JButton("Red");
        frame.add(Red);
        JButton Green = new JButton("Green");
        frame.add(Green);
        JButton Blue = new JButton("Blue");
        frame.add(Blue);
        circleIcon circle = new circleIcon(50);
        JLabel label = new JLabel(circle);
        frame.add(label);
        Green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle.setColor(Color.green);
                label.repaint();
            }
        });
        Red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle.setColor(Color.red);
                label.repaint();
            }
        });
        Blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle.setColor(Color.blue);
                label.repaint();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

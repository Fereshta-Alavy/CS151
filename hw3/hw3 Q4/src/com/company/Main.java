package com.company;

import com.company.MoveableShape.MoveableShape;
import com.company.ShapeIcon.ShapeIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame();
        JButton zoomIn = new JButton("Zoom In");
        JButton zoomOut = new JButton("Zoom Out");
        frame.add(zoomIn);
        frame.add(zoomOut);
        MoveableShape car = new carShape(60, 60, 100);
        ShapeIcon icon = new ShapeIcon(car, 200, 200);
        JLabel label = new JLabel(icon);
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int w = ((carShape) car).getWidth();
                w += 10;
                ((carShape) car).setWidth(w);
                label.repaint();
            }
        });

        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int w = ((carShape) car).getWidth();
                w -= 10;
                ((carShape) car).setWidth(w);
                label.repaint();
            }
        });

        frame.add(label);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

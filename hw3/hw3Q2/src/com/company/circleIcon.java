package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class circleIcon implements Icon {
    public circleIcon(int size){
        this.size = size;
        this.color = Color.white;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, size, size);
        g2.setColor(color);
        g2.fill(circle);
    }


    private int size;
    private Color color;
}
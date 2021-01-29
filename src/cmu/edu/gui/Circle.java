package cmu.edu.gui;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {

    private int x1 = 100;
    private int y1 = 100;
    private int x2 = 80;

    private int y2 = 80;

    public Circle(){

    }

    public Circle(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        double radius = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
        g.drawOval((int)(x1 - radius), (int)(y1 - radius), (int)(2*radius), (int)(2*radius));
        System.out.println(x1 + " " + x2 + " " + y1 + " " + y2 + " " + radius);
        System.out.println("PPPP");

    }
}

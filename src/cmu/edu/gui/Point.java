package cmu.edu.gui;

import javax.swing.*;
import java.awt.*;

public class Point extends JPanel {

    private int squareX = 5;
    private int squareY = 5;
    private int squareW = 10;
    private int squareH = 10;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(squareX,squareY,squareW,squareH);
        System.out.println("PPPP1");
    }
}

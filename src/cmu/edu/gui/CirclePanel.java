package cmu.edu.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CirclePanel {

    private static JFrame frame;
    private static JPanel panel;

//    private static void drawCircle(int x1, int y1, int x2, int y2){
//        Circle circle = new Circle(x1, y1, x2, y2);
//        panel.add(circle);
//    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("Circle Artist");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(20, 20, 10,10));
//
        for(int i=0; i<400; i++){
            Point button = new Point();
            panel.add(button);
        }

        Circle circle = new Circle(20, 20, 20, 20);
        panel.add(circle);


        for(int i=0; i<20; i++){
            Point button = new Point();
            panel.add(button);
        }

        panel.addMouseListener(new MouseAdapter() {

            int startX = Integer.MIN_VALUE;;
            int startY = Integer.MIN_VALUE;

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    startX = x;
                    startY = y;
                    System.out.println("The mouse is clicking: " + x + " , " + y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("The mouse is releasing: " + x + " , " + y);
//                drawCircle(startX, startY, x, y);
            }
        });

//        Circle circle = new Circle(200, 200, 240, 240);
        frame.add(circle);
//        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}

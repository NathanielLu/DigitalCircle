package cmu.edu.gui;

import cmu.edu.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class PainterPanel extends JPanel implements Listener{

    private final CirclePainter painter;

    private SquarePoint[][] squarePoints;

    interface Shape {
        void draw(Graphics g);
    }

    class SquarePoint implements Shape {
        int x;
        int y;
        Color c;
        public SquarePoint(int x, int y, Color c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(c);
            g.fillRect(x - 2, y - 2,4, 4);
        }
    }

    class Circle implements Shape {
        int x;
        int y;
        int r;
        Color c;
        public Circle(int x, int y, int r, Color c) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.c = c;
        }

        public void draw(Graphics g) {
            g.setColor(c);
            g.drawOval(x - r, y - r, 2 * r, 2 * r);
        }
    }

    private List<Shape> shapes = new ArrayList<>();

    public PainterPanel(CirclePainter painter) {
        this.painter = painter;
        painter.setListener(this);

        //set background square points
        squarePoints = new SquarePoint[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int x = 10 + 20 * i;
                int y = 10 + 20 * j;
                SquarePoint sp = new SquarePoint(x, y, Color.GRAY);
                squarePoints[i][j] = sp;
                shapes.add(sp);
            }
        }

        addMouseListener(new MouseAdapter() {

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

                    painter.press(x, y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("The mouse is releasing: " + x + " , " + y);

                painter.release(x, y);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    @Override
    public void drawCircle(int x, int y, int r, Color color) {
        Circle circle = new Circle(x, y, r, color);
        shapes.add(circle);
        circle.draw(getGraphics());
    }

    @Override
    public void togglePoint(int x, int y) {
        if ((x % 20) < 5 || (x % 20) > 15) return;
        if ((y % 20) < 5 || (y % 20) > 15) return;
//        SquarePoint sp = new SquarePoint(x / 20 * 20 + 10, y / 20 * 20 + 10);
//        int idx = y / 20 * 20 + x / 20;
        SquarePoint sp = squarePoints[x / 20][y / 20];
        sp.c = sp.c.equals(Color.GRAY) ? Color.BLUE : Color.GRAY;
        sp.draw(getGraphics());
    }

    @Override
    public void clear() {
        for(SquarePoint[] sps: squarePoints){
            for(SquarePoint sp: sps){
                sp.c = Color.GRAY;
                sp.draw(getGraphics());
            }
        }

        List<Shape> list = new ArrayList<>();

        for(Shape shape: shapes){
            if(shape instanceof SquarePoint){
                ((SquarePoint) shape).c = Color.GRAY;
                list.add(shape);
            }
        }
        shapes = list;
        for (Shape shape : shapes) {
            shape.draw(getGraphics());
        }
    }

    @Override
    public void alert(String message) {

    }

}

package cmu.edu.gui;

import cmu.edu.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainterPanel extends JPanel implements Listener{

    private final CirclePainter painter;

    public PainterPanel(CirclePainter painter) {
        this.painter = painter;
        painter.setListener(this);

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawRect(g);
        drawOval(g);
    }

    private void drawRect(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);

        // 1. 绘制一个矩形: 起点(30, 20), 宽80, 高100
        g2d.drawRect(30, 20, 80, 100);

        // 2. 填充一个矩形
        g2d.fillRect(140, 20, 80, 100);

        // 3. 绘制一个圆角矩形: 起点(30, 150), 宽80, 高100, 圆角宽30, 圆角高30
        g2d.drawRoundRect(30, 150, 80, 100, 30, 30);

        // 4. 绘制一个多边形(收尾相连): 点(140, 150), 点(180, 250), 点(220, 200)
        int[] xPoints = new int[] { 140, 180, 220};
        int[] yPoints = new int[] { 150,  250, 200};
        int nPoints = 3;
        g2d.drawPolygon(xPoints, yPoints, nPoints);

        g2d.dispose();
    }

    private void drawOval(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);

        // 1. 绘制一个圆: 圆的外切矩形 左上角坐标为(0, 0), 宽高为100
        g2d.drawOval(0, 0, 100, 100);

        g2d.setColor(Color.GRAY);
        g2d.drawOval(50, 50, 100, 100);

        g2d.setColor(Color.BLUE);

        // 2. 填充一个椭圆
        g2d.fillOval(120, 100, 100, 150);

        g2d.dispose();
    }

    @Override
    public void drawCircle(int x, int y, double r, Color color) {

    }

    @Override
    public void togglePoint(int x, int y) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void alert(String message) {

    }

}

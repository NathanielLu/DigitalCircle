package cmu.edu.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CircleDemo {

    private static final int GRID_SIZE = 5;
    private Circle[][] circlePanels
            = new Circle[GRID_SIZE][GRID_SIZE];
    private JPanel gridPanel
            = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));

    public CircleDemo() {
        initCircles();
        JFrame f = new JFrame();
        f.add(gridPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private void initCircles() {
        for (int i = 0; i < circlePanels.length; i++) {
            for (int j = 0; j < circlePanels[i].length; j++) {
                Circle circle = new Circle();
                circlePanels[i][j] = circle;
                gridPanel.add(circle);
            }
        }
    }

    class Circle extends JPanel {
        private boolean draw = false;
        private Color color = Color.BLUE;

        public Circle() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e) {
                    if (isDraw()) {
                        setDraw(false);
                    } else {
                        setDraw(true);
                    }
                }
            });
        }

        public boolean isDraw() {
            return draw;
        }

        public void setDraw(boolean draw) {
            this.draw = draw;
            repaint();
        }

        public void setColor(Color color) {
            this.color = color;
            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(75, 75);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (draw) {
                g.setColor(color);
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new CircleDemo();
            }
        });
    }
}

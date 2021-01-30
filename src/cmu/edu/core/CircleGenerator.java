package cmu.edu.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;



public class CircleGenerator extends CirclePainter{

    private static final int LEAST_POINT_NUM = 3;
    private static final String ALERT_MSG = "At least three points are required to generate a circle";

    private List<Point> toggledPoints = new ArrayList<>();

    @Override
    public void press(int x, int y) {
        toggledPoints.add(new Point(x, y));
        listener.togglePoint(x, y);
    }

    public void generateCircle() {
        if (toggledPoints.size() < LEAST_POINT_NUM) {
            listener.alert(ALERT_MSG);
            return;
        }
        Circle circle = generateCircleHelper(toggledPoints);
        listener.drawCircle(circle.centerX, circle.centerY, circle.r, Color.BLUE);
    }

    public void reset() {
        listener.clear();
        toggledPoints.clear();
    }


    private Circle generateCircleHelper(List<Point> points) {
        // TODO: calculate R from toggle points
        return new Circle(0, 0, 0.0);
    }

    class Circle {
        int centerX;
        int centerY;
        double r;

        public Circle(int x, int y, double r) {
            centerX = x;
            centerY = y;
            this.r = r;
        }
    }
}

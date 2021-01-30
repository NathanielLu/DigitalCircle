package cmu.edu.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CircleGenerator extends CirclePainter{

    private static final int LEAST_POINT_NUM = 3;
    private static final String ALERT_MSG = "At least three points are required to generate a circle";

    private List<Point> toggledPoints = new ArrayList<>();

    /**
     * when user click the point, the point should be toggle on or off
     * @param x
     * @param y
     */
    @Override
    public void press(int x, int y) {
        toggledPoints.add(new Point(x, y));
        listener.togglePoint(x, y);
    }

    /**
     * generate the circle based on the toggled points
     * using the least squares-based algorithm
     */
    public void generateCircle() {
        if (toggledPoints.size() < LEAST_POINT_NUM) {
            listener.alert(ALERT_MSG);
            return;
        }
        Circle circle = generateCircleHelper(toggledPoints);
        listener.drawCircle(circle.centerX, circle.centerY, circle.r, Color.BLUE);
    }

    /**
     * clear all the toggled points
     */
    public void reset() {
        listener.clear();
        toggledPoints.clear();
    }


    /**
     * the least squares-based algorithm using the calculation
     * @param points
     * @return
     */
    private Circle generateCircleHelper(List<Point> points) {
        // TODO: calculate R from toggle points

        double sum_x = 0.0f, sum_y = 0.0f;
        double sum_x2 = 0.0f, sum_y2 = 0.0f;
        double sum_x3 = 0.0f, sum_y3 = 0.0f;
        double sum_xy = 0.0f, sum_x1y2 = 0.0f, sum_x2y1 = 0.0f;

        int N = points.size();
        for (int i = 0; i < N; i++) {
            double x = points.get(i).x();
            double y = points.get(i).y();
            double x2 = x * x;
            double y2 = y * y;
            sum_x += x;
            sum_y += y;
            sum_x2 += x2;
            sum_y2 += y2;
            sum_x3 += x2 * x;
            sum_y3 += y2 * y;
            sum_xy += x * y;
            sum_x1y2 += x * y2;
            sum_x2y1 += x2 * y;
        }

        double C, D, E, G, H;
        double a, b, c;

        C = N * sum_x2 - sum_x * sum_x;
        D = N * sum_xy - sum_x * sum_y;
        E = N * sum_x3 + N * sum_x1y2 - (sum_x2 + sum_y2) * sum_x;
        G = N * sum_y2 - sum_y * sum_y;
        H = N * sum_x2y1 + N * sum_y3 - (sum_x2 + sum_y2) * sum_y;
        a = (H * D - E * G) / (C * G - D * D);
        b = (H * C - E * D) / (D * D - G * C);
        c = -(a * sum_x + b * sum_y + sum_x2 + sum_y2) / N;

        double center_x = a / (-2);
        double center_y = b / (-2);
        double radius = Math.sqrt(a * a + b * b - 4 * c) / 2;
        return new Circle((int)center_x, (int)center_y, (int)radius);
    }

    class Circle {
        int centerX;
        int centerY;
        int r;

        public Circle(int x, int y, int r) {
            centerX = x;
            centerY = y;
            this.r = r;
        }
    }
}

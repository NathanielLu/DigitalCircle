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
    @Override
    public void generate(){
        generateCircle();
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

        double sumX = 0, sumY = 0;
        double sumX2 = 0, sumY2 = 0;
        double sumX3 = 0, sumY3 = 0;
        double sumXY = 0, sumX1Y2 = 0, sumX2Y1 = 0;

        int N = points.size();
        for (int i = 0; i < N; i++) {
            double x = points.get(i).x();
            double y = points.get(i).y();
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumY2 += y * y;
            sumX3 += x * x * x;
            sumY3 += y * y * y;
            sumXY += x * y;
            sumX1Y2 += x * y * y;
            sumX2Y1 += x * x * y;
        }

        double tmp1, tmp2, tmp3, tmp4, tmp5, a, b, c;

        tmp1 = N * sumX2 - sumX * sumX;
        tmp2 = N * sumXY - sumX * sumY;
        tmp3 = N * sumX3 + N * sumX1Y2 - (sumX2 + sumY2) * sumX;
        tmp4 = N * sumY2 - sumY * sumY;
        tmp5 = N * sumX2Y1 + N * sumY3 - (sumX2 + sumY2) * sumY;
        a = (tmp5 * tmp2 - tmp3 * tmp4) / (tmp1 * tmp4 - tmp2 * tmp2);
        b = (tmp5 * tmp1 - tmp3 * tmp2) / (tmp2 * tmp2 - tmp4 * tmp1);
        c = -(a * sumX + b * sumY + sumX2 + sumY2) / N;

        double centerX = a / (-2);
        double centerY = b / (-2);
        double radius = Math.sqrt(a * a + b * b - 4 * c) / 2;
        return new Circle((int)centerX, (int)centerY, (int)radius);
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

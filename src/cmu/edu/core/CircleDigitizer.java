package cmu.edu.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CircleDigitizer extends CirclePainter {
    @Override
    public void release(int x, int y) {
        if(lastClickPoint != null) {
            Point newPoint = new Point(x, y);
            List<Point> points = getClosestPoints(lastClickPoint, newPoint);

            double radius = Utils.getRadius(lastClickPoint, newPoint);
            double minR = radius, maxR = radius;
            for (Point p : points) {
                double r = Utils.getRadius(lastClickPoint, p);
                if (r > maxR)
                    maxR = r;
                else if (r < minR)
                    minR = r;

                //Toggle points
                listener.togglePoint(p.x(), p.y());
            }
            //draw circles
            listener.drawCircle(lastClickPoint.x(), lastClickPoint.y(), radius, Color.BLUE);
            listener.drawCircle(lastClickPoint.x(), lastClickPoint.y(), minR, Color.RED);
            listener.drawCircle(lastClickPoint.x(), lastClickPoint.y(), maxR, Color.RED);

        }
    }


    private List<Point> getClosestPoints(Point centerPoint, Point edgePoint) {
        List<Point> points = new ArrayList<>();
        // TODO: get closest approximation points
        return points;
    }
}

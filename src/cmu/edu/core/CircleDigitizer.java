package cmu.edu.core;

import java.awt.Color;
import java.util.*;

public class CircleDigitizer extends CirclePainter {

    /**
     * when user releases the mouse, automatically highlight the points and draw three circles
     * @param x
     * @param y
     */
    @Override
    public void release(int x, int y) {
        //decide whether the start point exists
        if(lastClickPoint != null) {
            Point newPoint = new Point(x, y);
            List<Point> points = getClosestPoints(lastClickPoint, newPoint);
            System.out.println("Nearest points number is: " + points.size());
            System.out.println(points);

            //get radius of the current circle
            int radius = Utils.getRadius(lastClickPoint, newPoint);
            int minR = radius, maxR = radius;
            for (Point p : points) {
                int r = Utils.getRadius(lastClickPoint, p);
                if (r > maxR)
                    maxR = r;
                if (r < minR)
                    minR = r;
                //Toggle points
                listener.togglePoint(p.x(), p.y());
            }

            System.out.println(radius + " " + minR + " " + maxR);
            //draw circles
            listener.drawCircle(lastClickPoint.x(), lastClickPoint.y(), radius, Color.BLUE);
            listener.drawCircle(lastClickPoint.x(), lastClickPoint.y(), minR, Color.RED);
            listener.drawCircle(lastClickPoint.x(), lastClickPoint.y(), maxR, Color.RED);

        }
    }

    /**
     * return the nearest point of the intersection between the circle and grid
     * @param values
     * @param possible
     * @param i
     * @param flag
     * @return
     */
    private Point getNearestPoint(List<Double> values, double possible, int i, boolean flag){
        if (possible >= 10 && possible <= 390) {
            int idx = Collections.binarySearch(values, possible);
            if(idx < 0){
                idx = -idx - 1;
            }
            double left = possible - values.get(idx-1);
            double right = values.get(idx) - possible;


            if(flag){ // if checking horizontal lines
                if(left < right){
                    return new Point((int)(values.get(idx - 1).doubleValue()), i);
                }else{
                    return new Point((int)(values.get(idx).doubleValue()), i);
                }
            }else { // if checking vertical lines
                if (left < right) {
                    return new Point(i, (int) (values.get(idx - 1).doubleValue()));
                } else {
                    return new Point(i, (int) (values.get(idx).doubleValue()));
                }
            }
        }
        return null;
    }

    /**
     * return the highlighted points approximate to the circle
     * calculating all the intersection between the circle and grid line
     * and then find the closest points
     * @param centerPoint
     * @param edgePoint
     * @return
     */
    private List<Point> getClosestPoints(Point centerPoint, Point edgePoint) {
        List<Point> points = new ArrayList<>();
        // TODO: get closest approximation points

        double radius = Utils.getRadius(centerPoint, edgePoint);
        int centerX = centerPoint.x();
        int centerY = centerPoint.y();

        Set<Point> set = new HashSet<>();

        List<Double> values = new ArrayList<>();
        for(int i=10; i<=390; i+=20){
            values.add(i*1.0);
        }

        // horizontal lines
        for(int i=10; i<=390; i+=20){
            // if grid lines intersect with the circle
            if(centerY - radius <= i && i <= centerY + radius) {
                double possibleX1 = centerX + Math.sqrt(radius * radius - (centerY - i) * (centerY - i));
                double possibleX2 = centerX - Math.sqrt(radius * radius - (centerY - i) * (centerY - i));

                Point p = getNearestPoint(values, possibleX1, i, true);
                if(p != null){
                    set.add(p);
                }
                Point p2 = getNearestPoint(values, possibleX2, i, true);
                if(p2 != null){
                    set.add(p2);
                }
            }
        }

        // vertical lines
        for(int i=10; i<=390; i+=20){
            // if grid lines intersect with the circle
            if(centerX - radius <= i && i <= centerX + radius){
                double possibleY1 = centerY + Math.sqrt(radius * radius - (centerX - i)* (centerX - i));
                double possibleY2 = centerY - Math.sqrt(radius * radius - (centerX - i)* (centerX - i));

                Point p = getNearestPoint(values, possibleY1, i, false);
                if(p != null){
                    set.add(p);
                }
                Point p2 = getNearestPoint(values, possibleY2, i, false);
                if(p2 != null){
                    set.add(p2);
                }
            }
        }

        for(Point p: set){
            boolean found = false;
            for(Point tmp: points){
                if(tmp.x() == p.x() && tmp.y() == p.y()){
                    found = true;
                    break;
                }
            }
            if(!found){
                points.add(p);
            }
        }

        return points;
    }
}

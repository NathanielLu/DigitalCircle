package cmu.edu.core;

public class Utils {
    public static double getRadius(Point a, Point b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double getDistance(Point a, Point b, double radius){
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        double dis = Math.sqrt(dx * dx + dy * dy);

        return Math.abs(radius - dis);
    }
}

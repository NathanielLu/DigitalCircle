package cmu.edu.core;

public class Utils {

    /**
     * calculating the radius of the circle
     * @param a
     * @param b
     * @return
     */
    public static int getRadius(Point a, Point b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        return (int)Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * get distance between a circle and a point
     * @param a
     * @param b
     * @param radius
     * @return
     */
    public static double getDistance(Point a, Point b, double radius){
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();
        double dis = Math.sqrt(dx * dx + dy * dy);

        return Math.abs(radius - dis);
    }
}

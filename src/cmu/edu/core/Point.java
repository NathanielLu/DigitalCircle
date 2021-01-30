package cmu.edu.core;

/**
 * class for the point on the frame
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public String toString(){
        return "Point("+x+","+y+")";
    }
}

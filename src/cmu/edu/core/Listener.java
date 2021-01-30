package cmu.edu.core;

import java.awt.Color;

public interface Listener {

    /**
     * highlight the points and draw three circles
     * @param x
     * @param y
     * @param r
     * @param color
     */
    void drawCircle(int x, int y, int r, Color color);

    /**
     * toggle point on or off
     * @param x
     * @param y
     */
    void togglePoint(int x, int y);

    /**
     * clear all toggled points
     */
    void clear();

    /**
     * alert any error message
     * @param message
     */
    void alert(String message);

}

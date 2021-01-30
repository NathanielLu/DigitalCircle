package cmu.edu.core;

import java.awt.Color;

public interface Listener {
    void drawCircle(int x, int y, int r, Color color);

    void togglePoint(int x, int y);

    void clear();

    void alert(String message);

}

package cmu.edu.core;

public abstract class CirclePainter {

    Listener listener;

    Point lastClickPoint;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void press(int x, int y) {
        lastClickPoint = new Point(x, y);
    }

    public void release(int x, int y) {
        return;
    }

    public void reset() {
        listener.clear();
    }


}

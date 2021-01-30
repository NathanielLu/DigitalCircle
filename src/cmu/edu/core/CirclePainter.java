package cmu.edu.core;

public abstract class CirclePainter {

    Listener listener;

    Point lastClickPoint; // denoting the last click point

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void press(int x, int y) {
        lastClickPoint = new Point(x, y);
    } // setting the last click point

    public void release(int x, int y) {
        return;
    } // release the mouse

    public void reset() {
        listener.clear();
    }


}

package cmu.edu.core;

import java.awt.Color;
import java.util.*;

public class CircleDigitizer extends CirclePainter {
    @Override
    public void release(int x, int y) {
        if(lastClickPoint != null) {
            Point newPoint = new Point(x, y);
            List<Point> points = getClosestPoints(lastClickPoint, newPoint);

            int radius = Utils.getRadius(lastClickPoint, newPoint);
            int minR = radius, maxR = radius;
            for (Point p : points) {
                int r = Utils.getRadius(lastClickPoint, p);
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

        double radius = Utils.getRadius(centerPoint, edgePoint);

        Map<Point, Double> nearPoints = new HashMap<>();

        for(int i=0; i<20; i++){
            int y = 20 * i;
            if(Math.abs(y - centerPoint.y()) > radius + 20 || Math.abs(y - centerPoint.y()) < radius - 20){
                continue;
            }
            for(int j=0; j<20; j++){
                int x = 20 * j;
                if(Math.abs(x - centerPoint.x()) > radius + 20 || Math.abs(x - centerPoint.x()) < radius - 20){
                    continue;
                }
                double distance = Utils.getDistance(new Point(x, y), centerPoint, radius);
                nearPoints.put(new Point(x, y), distance);
            }
        }

        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<>(){
            public int compare(Point a, Point b){
                double cal = nearPoints.get(a) - nearPoints.get(b);
                if(cal < 0){
                    return -1;
                }else if(cal > 0){
                    return 1;
                }
                return 0;
            }
        });
        for(Point p: nearPoints.keySet()){
            queue.add(p);
        }

        Set<Integer> tobeDeleteRows = new HashSet<>();
        Set<Integer> tobeDeleteCols = new HashSet<>();

        double leftX = Math.max(centerPoint.x() - radius, 0);
        double rightX = Math.min(centerPoint.x() + radius, 400);
        double topY = Math.max(centerPoint.y() - radius, 0);
        double downY = Math.min(centerPoint.y() + radius, 400);

        if(rightX != (int)rightX){
            rightX++;
        }

        for(int x=(int)leftX; x<=rightX; x++){
            tobeDeleteRows.add(x);
        }

        if(downY != (int)downY){
            downY++;
        }

        for(int y=(int)topY; y<=downY; y++){
            tobeDeleteRows.add(y);
        }

        while(tobeDeleteCols.size() > 0  || tobeDeleteCols.size() > 0){
            if(!queue.isEmpty()){
                Point tmp = queue.poll();
                tobeDeleteCols.add(tmp.y());
                tobeDeleteRows.add(tmp.x());
                points.add(tmp);
            }else{
                break;
            }
        }

        return points;
    }
}

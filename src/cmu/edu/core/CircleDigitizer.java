package cmu.edu.core;

import java.awt.Color;
import java.util.*;

public class CircleDigitizer extends CirclePainter {
    @Override
    public void release(int x, int y) {
        if(lastClickPoint != null) {
            Point newPoint = new Point(x, y);
            List<Point> points = getClosestPoints(lastClickPoint, newPoint);
            System.out.println("Nearest points number is: " + points.size());
            System.out.println(points);

            int radius = Utils.getRadius(lastClickPoint, newPoint);
            int minR = radius, maxR = radius;
            for (Point p : points) {
                int r = Utils.getRadius(lastClickPoint, p);
                if (r + 2 > maxR)
                    maxR = r + 2;
                if (r - 2 < minR)
                    minR = r - 2;
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


    private List<Point> getClosestPoints(Point centerPoint, Point edgePoint) {
        List<Point> points = new ArrayList<>();
        // TODO: get closest approximation points

        double radius = Utils.getRadius(centerPoint, edgePoint);

        Map<Point, Double> nearPoints = new HashMap<>();

        for(int i=0; i<20; i++){
            int y = 10 + 20 * i;
            if(Math.abs(y - centerPoint.y()) > radius + 15 || Math.abs(y - centerPoint.y()) < radius - 15){
                continue;
            }
            for(int j=0; j<20; j++){
                int x = 10 + 20 * j;
                if(Math.abs(x - centerPoint.x()) > radius + 15 || Math.abs(x - centerPoint.x()) < radius - 15){
                    continue;
                }
                double distance = Utils.getDistance(new Point(x, y), centerPoint, radius);
                nearPoints.put(new Point(x, y), distance);
            }
        }

        System.out.println(nearPoints);

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

        double leftX = Math.max(centerPoint.x() - radius, 10);
        double rightX = Math.min(centerPoint.x() + radius, 390);
        double topY = Math.max(centerPoint.y() - radius, 10);
        double downY = Math.min(centerPoint.y() + radius, 390);

        if((int)(leftX-10) / 20 * 20 != (leftX-10)){
            leftX = Math.max((int)(leftX-10) / 20 * 20 + 10, 10);
        }

        if((int)(rightX-10) / 20 * 20 != (rightX-10)){
            rightX = Math.min((int)(rightX-10) / 20 * 20 + 30, 390);
        }

        if((int)(topY-10) / 20 * 20 != (topY-10)){
            topY = Math.max((int)(topY-10) / 20 * 20 + 10, 10);
        }

        if((int)(downY-10) / 20 * 20 != (downY-10)){
            downY = Math.min((int)(downY-10) / 20 * 20 + 30, 390);
        }

        for(int x=(int)leftX; x<=rightX; x+=20){
            tobeDeleteCols.add(x);
        }

        for(int y=(int)topY; y<=downY; y+=20){
            tobeDeleteRows.add(y);
        }

        System.out.println(tobeDeleteRows);
        System.out.println(tobeDeleteCols);
        System.out.println(queue.size());

        while(tobeDeleteCols.size() > 0  || tobeDeleteRows.size() > 0){
            if(!queue.isEmpty()){
                Point tmp = queue.poll();
                tobeDeleteCols.remove(tmp.x());
                tobeDeleteRows.remove(tmp.y());
                points.add(tmp);
            }else{
                break;
            }
        }

        return points;
    }
}

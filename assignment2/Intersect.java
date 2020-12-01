package com.akamenev.assignment2;

public class Intersect{
    public static int direction(Point i, Point j, Point k){
        return ((k.x - i.x)*(j.y-i.y)) - ((j.x-i.x)*(k.y-i.y));
    }
    private static boolean onSegment(Point i, Point j, Point k){
        if(((Math.min(i.x, j.x) <= k.x) && (Math.max(i.x, j.x) >= k.x))
                && ((Math.min(i.y, j.y) <= k.y) && (Math.max(i.y, j.y) >= k.y)))
            return true;
        else
            return false;
    }
    public static boolean segmentIntersect(Point p1, Point p2, Point p3, Point p4){
        int d1 = direction(p3, p4, p1);
        int d2 = direction(p3, p4, p2);
        int d3 = direction(p1, p2, p3);
        int d4 = direction(p1, p2, p4);
        if((((d1 > 0) && (d2 < 0)) || ((d1 < 0) && (d2 > 0))) && (((d3 > 0) && (d4 < 0)) || ((d3 < 0) && (d4 > 0))))
            return true;
        else if ((d1 == 0) && onSegment(p3, p4, p1))
            return true;
        else if ((d2 == 0) && onSegment(p3, p4, p2))
            return true;
        else if ((d3 == 0) && onSegment(p1, p2, p3))
            return true;
        else if ((d4 == 0) && onSegment(p1, p2, p4))
            return true;
        else
            return false;
    }
}

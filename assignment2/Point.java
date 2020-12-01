package com.akamenev.assignment2;

public class Point {
    int x;
    int y;
    boolean end;
    Segment s;
    int segmentNum;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
        end  = false;
        s = null;
        segmentNum = -1;
    }

    public Point (int x, int y, Segment s, boolean end) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x = " + x +
                ", y = " + y +
                ", end = " + end +
                ", segment = " + segmentNum +
                "}";
    }
}

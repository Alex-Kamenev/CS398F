package com.akamenev.assignment2;

import java.util.TreeMap;

public class IntersectAny {
    private static int xNot = 0;
    //helper method for testing and ease of mind
    //Takes number of desired segments, max values for x and y coordinates
    //Returns a Segment array - each Segment object holds 2 point objects
    public Segment[] generateSegmentArray(int numSegments, int maxX, int maxY){
        Segment[] SegmentArray = new Segment[numSegments];
        for (int i = 0; i < numSegments; i++){
            int x1 = (int) ((Math.random() * (maxX - 0)) + 0);
            int x2 = (int) ((Math.random() * (maxX - 0)) + 1);
            int y1 = (int) ((Math.random() * (maxY - 0)) + 0);
            int y2 = (int) ((Math.random() * (maxY - 0)) + 0);
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            if(p1.x > p2.x) {
                p1.end = true;
                SegmentArray[i] = new Segment(p2, p1);
            } else {
                p2.end = true;
                SegmentArray[i] = new Segment(p1, p2);
            }
            p1.s = SegmentArray[i];
            p2.s = SegmentArray[i];
            p1.segmentNum = i;
            p2.segmentNum = i;
        }
        return SegmentArray;
    }
    //For heap implementation
    //Takes a Segment array as input and spits out a sorted
    //Point[] array. This is dome by first separating the Point
    //objects from the segments while still keeping awareness what
    //segment they belong to. Heap sort takes care of the sorting
    public Point[] generateQueueHeap(Segment[] segmentsArray){
        int queueSize = (segmentsArray.length * 2);
        Point[] queue = new Point[queueSize];
        int j = 0;
        for (int i = 0; i < segmentsArray.length; i++){
            queue[j] = segmentsArray[i].point1;
            j++;
            queue[j] = segmentsArray[i].point2;
            j++;
        }
        HeapSort sortHelper = new HeapSort();
        sortHelper.sort(queue);
        return queue;
    }
    //Method to determine if any intersect using heap and AVL
    public boolean IntersectAnyAVL (Segment[] segmentsArray){
        Point[] queue = generateQueueHeap(segmentsArray);
        int queueSize = queue.length;
        TreeMap<Segment, Segment> preOrder = new TreeMap<>();
        for(int i = 0; i < queueSize; i++){
            xNot = queue[i].x;

            if(queue[i].end == false){
                preOrder.put(queue[i].s, queue[i].s);
                System.out.println(preOrder.higherEntry(queue[i].s));
                System.out.println(queue[i].s);
                if(preOrder.higherEntry(queue[i].s) != null){
                    if((Intersect.segmentIntersect(preOrder.higherEntry(queue[i].s).getValue().point1,
                            preOrder.higherEntry(queue[i].s).getValue().point2, queue[i].s.point1, queue[i].s.point2))) {
                        return true;
                    }
                }
                if(queue[i].s != (preOrder.lowerEntry(queue[i].s).getValue())){
                    if ((preOrder.lowerEntry(queue[i].s) != null)) {
                        if ((Intersect.segmentIntersect(preOrder.lowerEntry(queue[i].s).getValue().point1,
                                preOrder.lowerEntry(queue[i].s).getValue().point2, queue[i].s.point1, queue[i].s.point2))) {
                            return true;
                        }
                    }
                }
            }
            if(queue[i].end){
                if((preOrder.higherEntry(queue[i].s) != null) && (preOrder.lowerEntry(queue[i].s) != null)
                        && (Intersect.segmentIntersect(preOrder.higherEntry(queue[i].s).getValue().point1,
                        preOrder.higherEntry(queue[i].s).getValue().point2,
                        preOrder.lowerEntry(queue[i].s).getValue().point1, preOrder.lowerEntry(queue[i].s).getValue().point2))){
                    return true;
                }
                preOrder.remove(queue[i].s ,queue[i].s);
            }
        }
        return false;
    }
    //    public Segment above(TreeMap<Segment, Segment> T, Segment s) {
    //
    //    }

    //    public Segment below(TreeMap<Segment, Segment> T, Segment s) {
    //
    //    }

    public static int getXnot(){
        return xNot;
    }
    public static void main(String[] args){
        IntersectAny obi = new IntersectAny();
        Segment[] S = new Segment[2];
        S[0] = new Segment(new Point(3,3), new Point(5,5));
        S[0].point2.end = true;
        S[0].point1.s = S[0];
        S[0].point2.s= S[0];
        S[0].point1.segmentNum = 0;
        S[0].point2.segmentNum = 0;
        S[1] = new Segment(new Point(11,12), new Point(20,22));
        S[1].point2.end = true;
        S[1].point1.s = S[1];
        S[1].point2.s= S[1];
        S[0].point1.segmentNum = 1;
        S[0].point2.segmentNum = 1;
        for(int i = 0; i < S.length; i++){
//            System.out.println(S[i].toString());
        }
        System.out.print(obi.IntersectAnyAVL(S));
    }
}

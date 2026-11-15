package com.akamenev.assignment2;

import com.akamenev.assignment1.BinaryHeapPriorityQueue;

public class AlgorithmRegression {
    public static void main(String[] args) {
        BinaryHeapPriorityQueue<Integer> queue = new BinaryHeapPriorityQueue<>(1);
        for (int i = -20; i < 180; i++) queue.heapInsert(i, i);
        for (int expected = 179; expected >= -20; expected--) {
            if (queue.heapMaximum() != expected) throw new AssertionError("Heap extraction order");
            queue.heapExtractMax();
        }
        try { queue.heapMaximum(); throw new AssertionError("Empty queue accepted"); }
        catch (ArithmeticException expected) { }
        Segment segment = new Segment(new Point(0, 0), new Point(3, 3));
        segment.point2.end = true;
        if (segment.compareTo(segment) != 0) throw new AssertionError("Comparator identity");
        if (new IntersectAny().IntersectAnyAVL(new Segment[]{segment})) throw new AssertionError("Single segment intersects itself");
        System.out.println("CS398F regressions passed");
    }
}

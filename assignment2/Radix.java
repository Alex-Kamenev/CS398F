package com.akamenev.assignment2;

import java.util.Arrays;

class Radix {
    static int getMax(Point arr[], int n)
    {
        int mx = arr[0].x;
        for (int i = 1; i < n; i++)
            if (arr[i].x > mx)
                mx = arr[i].x;
        return mx;
    }
    static void countSort(Point arr[], int n, int exp)
    {
        Point output[] = new Point[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
        for (i = 0; i < n; i++)
            count[(arr[i].x / exp) % 10]++;
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i].x / exp) % 10] - 1] = arr[i];
            count[(arr[i].x / exp) % 10]--;
        }
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void radixsort(Point arr[], int n) {
        int m = getMax(arr, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
    static void print(Point arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i].x + " ");
        System.out.println();
        for (int i = 0; i < n; i++)
            System.out.print(arr[i].y + " ");
    }
    public static void main(String[] args) {
        int queueSize = 20;
        Point[] queue = new Point[queueSize];
        for (int i = 0; i < queueSize; i++){
            int x1 = (int) ((Math.random() * (20 - 0)) + 0);
            int y1 = (int) ((Math.random() * (20 - 0)) + 0);
            Point p1 = new Point(x1, y1);
            queue[i] = p1;
        }
        int n = queue.length;
        radixsort(queue, n);
        print(queue, n);
    }
}

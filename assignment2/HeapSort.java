package com.akamenev.assignment2;

public class HeapSort {
    public void sort(Point arr[]) {
        int n = arr.length;
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Point temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    // To heapify a subtree rooted with node i
    void heapify(Point arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = (i << 1) + 1; // left = 2*i + 1
        int r = (i << 1) + 2; // right = 2*i + 2

        // if left child > root
        if (l < n){
            if (arr[l].x > arr[largest].x){
                largest = l;
            } else if (arr[l].x == arr[largest].x) {
                if (arr[l].y > arr[largest].y){
                    largest = l;
                }
            } else {
                largest = i;
            }
        }
        // if right child > largest
        if (r < n){
            if (arr[r].x > arr[largest].x){
                largest = r;
            } else if (arr[r].x == arr[largest].x) {
                if (arr[r].y > arr[largest].y){
                    largest = r;
                }
            }
        }

        // If largest != root
        if (largest != i) {
            Point swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively call
            heapify(arr, n, largest);
        }
    }
    // print array
    static void printArray(Point arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i].x + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i].y + " ");
        }
        System.out.println();
    }
    // For testing
    public static void main(String args[]) {
        Point[] arr = new Point[20];
        for (int i = 0; i < 20; i++){
            int x1 = (int) (22);
            int y1 = (int) ((Math.random() * (20 - 0)) + 1);
            Point p1 = new Point(x1, y1);
            arr[i] = p1;
        }
        int n = arr.length;

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        printArray(arr);
    }
}
/*************************************************************************
 *
 *  Pace University
 *  Fall 2020
 *  Advanced Data Structures and Computational Geometry
 *
 *  Course: CS 398F
 *  Author: Aleksandar Kamenev
 *  Collaborators: None
 *
 *  Assignment: 1
 *  Problem: PUT THE MAIN PROBLEM NAME HERE - Do later
 *  Description: PUT A BRIEF DESCRIPTION HERE - Do later
 *
 *  Input:
 *  Output:
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  COPY SIGNATURE OF VISIBLE METHODS HERE
 *
 *
 *   Remarks
 *   -------
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *
 *
 *************************************************************************/
package com.akamenev.assignment1;

public class BinaryHeapPriorityQueue<T> {

    //default number of elements that can be stored in Heap
    static final int DEF_SIZE = 128;
    //size of heap
    public int heapSize = 0;
    //declare array of object to hold entries
    Entry[] HeapArray;

    public BinaryHeapPriorityQueue() {
        HeapArray = new Entry[DEF_SIZE];
    }
    public BinaryHeapPriorityQueue(int size) {
        HeapArray = new Entry[size];
    }
    public void maxHeapify( Entry<T>[] array, int i) {
        int left = childLeft(i);
        int right = childRight(i);
        int largest = i;
        if ((left <= heapSize) && (array[left].getKey() > array[i].getKey())) {
            largest = left;
        }
        if ((right <= heapSize) && (array[right].getKey() > array[largest].getKey())){
            largest = right;
        }
        if (largest != i) {
            Entry<T> temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapify(array, largest);
        }
    }
    public T heapMaximum() {
        if (heapSize > 0) {
            return (T) HeapArray[1].getValue();
        } else {
            throw new ArithmeticException("Error Underflow");
        }
    }
    public Entry<T> heapExtractMax() {
        if(heapSize < 1) {
            throw new ArithmeticException("Error Underflow");
        }
        Entry<T> max = HeapArray[1];
        HeapArray[1] = HeapArray[heapSize];
        heapSize--;
        maxHeapify(HeapArray, 1);
        return max;
    }
    public void increaseKey(T value, int key){
        int min = 1;
        int max = heapSize;
        int range = (max - min) + 1;
        int positionInArray = (int)(Math.random() * range) + min;
        Entry<T> currentEntry = new Entry<>(value, key);
        heapIncreaseKey(positionInArray ,currentEntry);
    }
    public void heapIncreaseKey(int i,Entry<T> currentEntry) {
        if (currentEntry.getKey() < HeapArray[i].getKey()) {
//            System.out.println("Operation can not be complited");
            throw new ArithmeticException("New key is smaller than current key.");
        }
        else {
            HeapArray[i] = currentEntry;
            while ((i > 1) && (HeapArray[parentPosition(i)].getKey() < HeapArray[i].getKey())) {
                Entry<T> temp = HeapArray[i];
                HeapArray[i] = HeapArray[parentPosition(i)];
                HeapArray[parentPosition(i)] = temp;
                i = parentPosition(i);
            }
        }
    }
    public void heapInsert(T value, int key) {
        Entry<T> newEntry = new Entry<T>(value, key);
        Entry<T> placeHolderEntry = new Entry<T>(value, (int) Double.NEGATIVE_INFINITY);
        heapSize++;
        HeapArray[heapSize] = placeHolderEntry;
        heapIncreaseKey(heapSize, newEntry);
    }
    private int parentPosition(int i) {
        return (i>>1);
    }
    private int childLeft(int i) {
        return (i<<1);
    }
    private int childRight(int i) {
        return ((i<<1) + 1);
    }
    public Entry[] getHeapArray() {
        return HeapArray;
    }
    public int getHeapSize(){
        return heapSize;
    }
    static class Entry<T> {

        private T value;
        private int key;

        public Entry(T value, int key) {
            this.value = value;
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }
    public static void main(String[] args) {
        BinaryHeapPriorityQueue newQueue = new BinaryHeapPriorityQueue((int) Math.pow(2, (2*3+1)));
        newQueue.heapInsert(70, 70);
        newQueue.heapInsert(80, 80);
        newQueue.heapInsert(90, 90);
        newQueue.heapInsert(100, 100);
        newQueue.heapInsert(110, 110);
        newQueue.heapInsert(120, 120);
        newQueue.heapInsert(10, 10);
        newQueue.heapInsert(20, 20);
        newQueue.heapInsert(30, 30);
        newQueue.heapInsert(40, 40);
        newQueue.heapInsert(50, 50);
        newQueue.heapInsert(60, 60);
        newQueue.heapInsert(7, 7);
        newQueue.heapInsert(8, 8);
        newQueue.heapInsert(9, 9);
        newQueue.heapInsert(1, 1);
        newQueue.heapInsert(11, 11);
        newQueue.heapInsert(12, 12);
        newQueue.heapInsert(1, 1);
        newQueue.heapInsert(2, 2);
        newQueue.heapInsert(3, 3);
        newQueue.heapInsert(4, 4);
        newQueue.heapInsert(5, 5);
        newQueue.heapInsert(6, 6);
        int end = newQueue.getHeapSize();
        for (int j = 1; j <= end; j++) {
            int value = (int) Math.random();
            int key = (int) ((Math.random() * (20 - 0)) + 0);
            try {
                newQueue.increaseKey(value, key);
            }
            catch (ArithmeticException e) {
                System.out.println("Unable to increase key as new key is smaller.");
            }
            for (int i = 1; i <= newQueue.getHeapSize(); i++) {

                System.out.print(newQueue.HeapArray[i].getKey() + " ");
            }

            System.out.println("\n" + newQueue.heapExtractMax().getKey());
        }
    }
}
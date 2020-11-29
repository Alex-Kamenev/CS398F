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
        int largest;
        if ((left <= heapSize) && (array[left].getKey() > array[i].getKey())) {
            largest = left;
        }
        else {
            largest = i;
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

    public void IncreaseKey(T value, int key){
        int min = 1;
        int max = heapSize;
        int range = (max - min) + 1;
        int positionInArray = (int)(Math.random() * range) + min;
        Entry<T> currentEntry = new Entry<>(value, key);
        heapIncreaseKey(positionInArray ,currentEntry);
    }

    public void heapIncreaseKey(int i,Entry<T> currentEntry) {
        if (currentEntry.getKey() < HeapArray[i].getKey()) {
            throw new ArithmeticException("New key is smaller than current key.");
        }
        HeapArray[i] = currentEntry;
        while((i > 1) && (HeapArray[parentPosition(i)].getKey() < HeapArray[i].getKey())){
            Entry<T> temp = HeapArray[i];
            HeapArray[i] = HeapArray[parentPosition(i)];
            HeapArray[parentPosition(i)] = temp;
            i = parentPosition(i);
        }
    }

    public void heapInsert(T value, int key) {
        Entry<T> newEntry = new Entry<T>(value, key);
        Entry<T> placeHolderEntry = new Entry<T>(value, (int) Double.NEGATIVE_INFINITY);
        heapSize++;
        //current version of the Queue does not support expansion but it easily could
//        if(heapSize >= HeapArray.length){
//            Entry<T>[] NewHeapArray;
//            NewHeapArray = Arrays.copyOf(HeapArray, (HeapArray.length*2));
//            HeapArray = NewHeapArray;
//        }
        HeapArray[heapSize] = placeHolderEntry;
        heapIncreaseKey(heapSize, newEntry);
    }

    private int parentPosition(int i) {
        return i/2;
    }
    private int childLeft(int i) {
        return 2*i;
    }
    private int childRight(int i) {
        return (2*i) + 1;
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
        for (int j = 1; j <= 12; j++) {
            newQueue.IncreaseKey(50, 190);
            for (int i = 1; i <= newQueue.getHeapSize(); i++) {

                System.out.print(newQueue.HeapArray[i].getKey() + " ");
            }

            System.out.println("\n" + newQueue.heapExtractMax().getKey());
        }
    }
}
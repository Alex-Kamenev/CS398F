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

import java.util.Arrays;

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

    public Object heapMaximum() {
        if (heapSize > 0) {
            return HeapArray[1].getValue();
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

    //public void IncreaseKey(T value, int key){}

    public void heapIncreaseKey(Entry<T>[] array, int i,Entry<T> currentEntry) {
        if (currentEntry.getKey() < array[i].getKey()) {
            throw new ArithmeticException("New key is smaller than current key.");
        }
        array[i] = currentEntry;
        while((i > 1) && (array[parentPosition(i)].getKey() < array[i].getKey())){
            Entry<T> temp = array[i];
            array[i] = array[parentPosition(i)];
            array[parentPosition(i)] = temp;
            i = parentPosition(i);
        }
    }

    public void heapInsert(T value, int key) {
        Entry<T> newEntry = new Entry<T>(value, key);
        Entry<T> placeHolderEntry = new Entry<T>(value, (int) Double.NEGATIVE_INFINITY);
        heapSize++;
        if(heapSize >= HeapArray.length){
            Entry<T>[] NewHeapArray;
            NewHeapArray = Arrays.copyOf(HeapArray, (HeapArray.length*2));
            HeapArray = NewHeapArray;
        }
        HeapArray[heapSize] = placeHolderEntry;
        heapIncreaseKey(HeapArray, heapSize, newEntry);
    }

    public int parentPosition(int i) {
        return i/2;
    }
    public int childLeft(int i) {
        return 2*i;
    }
    public int childRight(int i) {
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

        public String toString() {
            return "value = " + this.getValue() + ", key = " + this.getKey();
        }
    }


    public static void main(String[] args) {
//        BinaryHeapPriorityQueue newQueue = new BinaryHeapPriorityQueue();
//        newQueue.heapInsert("2", 2000);
//        newQueue.heapInsert("3", 3);
//        newQueue.heapInsert("8", 8);
//        newQueue.heapInsert("4", 4);
//        newQueue.heapInsert("20", 20);
//        newQueue.heapInsert("40", 40);
//        newQueue.heapInsert("84", 84);
//        newQueue.heapInsert("421", 421);
//        newQueue.heapInsert("22", 22);
//        newQueue.heapInsert("80", 80);
//        for (int j = 1; j <= 10; j++) {
//            for (int i = 1; i <= newQueue.getHeapSize(); i++) {
//                System.out.print(newQueue.HeapArray[i].getValue() + " ");
//            }
//            System.out.println("\n" + newQueue.heapExtractMax().getValue());
//        }
//        System.out.println(Math.pow(2, Math.ceil(((Math.log(32))/2))));
//        System.out.println(Math.floor(((Math.log(32))/2)));
//        int u = (int) Math.ceil(Math.sqrt(32));
//        System.out.println(u);
        System.out.println((Integer) null);

    }

}
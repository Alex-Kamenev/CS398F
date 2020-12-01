package com.akamenev.assignment2;
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
 *  Problem: vEB priority queue
 *  Description: Implements a vEB priority queue
 *
 *  Input: size of desired vEB queue - such that size = 2*k where k is a positive integer
 *  Output: empty vEB data structure
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  public VEBQueue(int size)
 *  public int vEBmax(VEBQueue vEB)
 *  public int vEBmin(VEBQueue vEB)
 *  public void vEBInsert(VEBQueue vEB, int x, T data)
 *  public int vEBSuccessor(VEBQueue vEB, int x)
 *  public int vEBPredecessor(VEBQueue vEB, int x)
 *  public boolean vEBMember(VEBQueue vEB, int x)
 *  public void vEBDelete(VEBQueue vEB, int x)
 *  public T extractMax(VEBQueue vEB)
 *  public T extractMin(VEBQueue vEB)
 *  public void changePriority(VEBQueue vEB, int oldKey, int newKey)
 *
 *
 *   Remarks
 *   -------
 *   Some constrains and use instructions:
 *   This implementation of a vEB priority queue does not support
 *   identical keys and so all keys must be unique positive integers.
 *   You may have any type of data associated with a given key.
 *
 *
 *
 *   PUT ALL NON-CODING ANSWERS HERE
 *
 *
 *************************************************************************/
public class VEBQueue<T> {

    private int u;
    private int min;
    private int max;
    private T min_data;
    private T max_data;
    private VEBQueue<T> summary;
    private VEBQueue<T>[] cluster;

    //helper method for higher sqrt of u
    private int highRoot (int universe){
        return (int) Math.pow(2.0 , Math.ceil((Math.log(universe)/Math.log(2.0)) / 2.0));
    }
    //helper method for lower sqrt of u
    private int lowRoot (int universe){
        return (int) Math.pow(2.0 , Math.floor((Math.log(universe)/Math.log(2.0)) / 2.0));
    }
    //Cluster in which Entry is
    private int high(int x) {
        double div =  lowRoot(u);
        return (int) Math.floor(x / div);
    }
    //Position of Entry in cluster
    private int low(int x) {
        double mod =  lowRoot(u);
        return (int)(x % mod);
    }
    //Index of Entry
    private int vEBIndex(int x, int y) {
        double ru =  lowRoot(u);
        return (int) ((x*ru) + y);
    }
    //Constructor
    public VEBQueue(int size) {
        u = size;
        min = -1;
        max = -1;
        min_data = null;
        max_data = null;

        //Base case when u <= 2
        if(size <= 2) {
            summary = null;
            cluster = null;
        }
        //if u > 2
        else {
            int uReduced = (highRoot(u));
            //declare summary with size sqrt u
            summary = new VEBQueue<>(uReduced);

            //set cluster array size
            cluster = new VEBQueue[uReduced];

            //populate cluster array
            for (int i = 0; i < uReduced; i++) {
                cluster[i] = new VEBQueue<>(lowRoot(u));
            }
        }
    }

    //Change priority
    //There definitely are more elegant implementation of this
    //but this is sufficient for the task. A but expensive in terms of
    //time but still O(lglgu)
    //only changes priority of the new priority doesn't exist already
    public void changePriority(VEBQueue<T> vEB, int oldKey, int newKey){
        if(!(vEB.vEBMember(vEB, newKey)) && (newKey > -1 && newKey < vEB.u)) {
            T data = vEB.getData(vEB, oldKey);
            vEB.vEBDelete(vEB, oldKey);
            vEB.vEBInsert(vEB, newKey, data);
        }
    }

    //Get Max and Remove it
    public T extractMax(VEBQueue<T> vEB){
        int key = vEBmax(vEB);
        T result = getData(vEB, key);
        vEBDelete(vEB,key);
        return result;
    }

    //Get Min and Remove it
    public T extractMin(VEBQueue<T> vEB){
        int key = vEBmin(vEB);
        T result = getData(vEB, key);
        vEBDelete(vEB,key);
        return result;
    }

    //Get value of max Entry(min entry is such that key of min Entry is bigger than all other keys)
    public int vEBmax(VEBQueue<T> vEB){
        if (vEB.max == -1){
            return -1;
        }
        else {
            return vEB.max;
        }
    }

    //Get get value of min Entry(min entry is such that key of min Entry is smaller than all other keys)
    public int vEBmin(VEBQueue<T> vEB){
        if (vEB.min == -1){
            return -1;
        }
        else {
            return vEB.min;
        }
    }

    //Check if a given priority/key is present
    public boolean vEBMember(VEBQueue<T> vEB, int x) {
        if((x == vEB.min) || (x == vEB.max)){
            return true;
        }
        else {
            if (vEB.u == 2) {
                return false;
            }
            else {
                return vEBMember(vEB.cluster[vEB.high(x)], vEB.low(x));
            }
        }
    }

    //Return data associated with a given priority
    public T getData(VEBQueue<T> vEB, int x){
        if(x == vEB.min){
            return vEB.min_data;
        }
        if (x == vEB.max){
            return vEB.max_data;
        }
        else {
            return getData(vEB.cluster[vEB.high(x)], vEB.low(x));
        }
    }

    //Find the element after a given one - return the priority not the element it self
    public int vEBSuccessor(VEBQueue<T> vEB, int x){
        if (vEB.u == 2){
            if((x == 0) && (vEB.max == 1)){
                return 1;
            }
            else {
                return -1;
            }
        }
        else if ((vEB.min != -1) && (x < vEB.min)){
            return vEB.min;
        }
        else {
            int maxInCluster = vEBmax(vEB.cluster[vEB.high(x)]);
            int offset, sucCluster;
            if((maxInCluster != -1) && (vEB.low(x) < maxInCluster)){
                offset = vEBSuccessor(vEB.cluster[vEB.high(x)], vEB.low(x));
                return vEB.vEBIndex(vEB.high(x), offset);
            }
            else {
                sucCluster = vEBSuccessor(vEB.summary, vEB.high(x));
                if (sucCluster == -1) {
                    return -1;
                }
                else {
                    offset = vEBmin(vEB.cluster[sucCluster]);
                    return vEBIndex(sucCluster, offset);
                }
            }
        }
    }

    //Find the element before a given one - return the priority not the element it self
    public int vEBPredecessor(VEBQueue<T> vEB, int x){
        if (vEB.u == 2){
            if((x == 1) && (vEB.min == 0)){
                return 0;
            }
            else {
                return -1;
            }
        }
        else if ((vEB.max != -1) && (x > vEB.max)){
            return vEB.max;
        }
        else {
            int minInCluster = vEBmin(vEB.cluster[vEB.high(x)]);
            int offset;
            if((minInCluster != -1) && vEB.low(x) > minInCluster){
                offset = vEBPredecessor(vEB.cluster[vEB.high(x)], vEB.low(x));
                return vEBIndex(vEB.high(x), offset);
            }
            else {
                int preCluster = vEBPredecessor(vEB.summary, vEB.high(x));
                if (preCluster == -1) {
                    if ((vEB.min != -1) && (x > vEB.min)) {
                        return vEB.min;
                    }
                    else {
                        return -1;
                    }
                }
                else {
                    offset = vEBmax(vEB.cluster[preCluster]);
                    return vEBIndex(preCluster, offset);
                }
            }
        }
    }

    //Base case for insertion
    private void baseInsert(VEBQueue<T> vEB, int x, T data){
        vEB.max = x;
        vEB.min = x;
        vEB.max_data = data;
        vEB.min_data = data;
    }

    // Insert element in vEB
    public void vEBInsert(VEBQueue<T> vEB, int x, T data) {
        if (vEB.min == -1){
            baseInsert(vEB, x, data);
        } else {
            if (x < vEB.min){
                int tempX = x;
                x = vEB.min;
                vEB.min = tempX;
                T tempD = data;
                data = vEB.min_data;
                vEB.min_data = tempD;
            }
            if (vEB.u > 2){
                if (vEBmin(vEB.cluster[vEB.high(x)]) == -1) {
                    vEBInsert(vEB.summary, vEB.high(x), data);
//                    vEB.cluster[vEB.high(x)].min = vEB.low(x);
//                    vEB.cluster[vEB.high(x)].max = vEB.low(x);
                    baseInsert(vEB.cluster[vEB.high(x)], vEB.low(x), data);

                } else {
                    vEBInsert(vEB.cluster[vEB.high(x)], vEB.low(x), data);
                }
            }
            if (x > vEB.max){
                vEB.max = x;
                vEB.max_data = data;
            }
        }

    }

    //Delete operation
    public void vEBDelete(VEBQueue<T> vEB, int x) {
        if (vEB.min == vEB.max) {
            vEB.min = -1;
            vEB.max = -1;
            vEB.min_data = null;
            vEB.max_data = null;
        } else if (vEB.u == 2) {
            if (x == 0) {
                vEB.min = 1;
                vEB.min_data = vEB.max_data;
            }
            else {
                vEB.min = 0;
            }
            vEB.max = vEB.min;
            vEB.max_data = vEB.min_data;
        } else {
            if (x == vEB.min) {
                int firstCluster = vEBmin(vEB.summary);
                x = vEB.vEBIndex(firstCluster, vEBmin(vEB.cluster[firstCluster]));
                vEB.min = x;
                vEB.min_data = vEB.cluster[firstCluster].min_data;
            }
            vEBDelete(vEB.cluster[vEB.high(x)], vEB.low(x));

            if (vEBmin(vEB.cluster[vEB.high(x)]) == -1) {
                vEBDelete(vEB.summary, vEB.high(x));
                if (x == vEB.max) {
                    int summaryMax = vEBmax(vEB.summary);
                    if (summaryMax == -1) {
                        vEB.max = vEB.min;
                        vEB.max_data = vEB.min_data;
                    } else {
                        vEB.max = vEB.vEBIndex(summaryMax, vEBmax(vEB.cluster[summaryMax]));
                        vEB.max_data = vEB.cluster[summaryMax].max_data;
                    }
                }
            }
            else if (x == vEB.max) {
                vEB.max = vEB.vEBIndex(vEB.high(x), vEBmax(vEB.cluster[vEB.high(x)]));
                vEB.max_data = vEB.cluster[vEB.high(x)].max_data;
            }
        }
    }

    //Driver class for testing
    public static void main(String[] args) {
        VEBQueue<Object> newQueue = new VEBQueue<>((int) Math.pow(2, (2*3+1)));
        newQueue.vEBInsert(newQueue, 70, "70");
        newQueue.vEBInsert(newQueue, 80, "80");
        newQueue.vEBInsert(newQueue, 90, "90");
        newQueue.vEBInsert(newQueue, 100, "100");
        newQueue.vEBInsert(newQueue, 110, "110");
        newQueue.vEBInsert(newQueue, 120, "120");
        newQueue.vEBInsert(newQueue, 10, "10");
        newQueue.vEBInsert(newQueue, 20, "20");
        newQueue.vEBInsert(newQueue, 30, "30");
        newQueue.vEBInsert(newQueue, 40, "40");
        newQueue.vEBInsert(newQueue, 50, "50");
        newQueue.vEBInsert(newQueue, 60, "60");
        System.out.println("U : " + newQueue.u);
        System.out.println("Current max: " + newQueue.vEBmax(newQueue) + " Current max data : " + newQueue.extractMax(newQueue));
        System.out.println("Current max: " + newQueue.vEBmax(newQueue) + " Current max data : " + newQueue.extractMax(newQueue));
        newQueue.changePriority(newQueue, 40, 122);
        System.out.println("Current max: " + newQueue.vEBmax(newQueue) + " Current max data : " + newQueue.extractMax(newQueue));

        System.out.println("Current max: " + newQueue.vEBmax(newQueue) + " Current max data : " + newQueue.extractMax(newQueue));
        System.out.println("Current max: " + newQueue.vEBmax(newQueue) + " Current max data : " + newQueue.extractMax(newQueue));
        System.out.println("Current max: " + newQueue.vEBmax(newQueue) + " Current max data : " + newQueue.extractMax(newQueue));
        System.out.println("Current min: " + newQueue.vEBmin(newQueue) + " Current min data : " + newQueue.extractMin(newQueue));
        System.out.println("Current min: " + newQueue.vEBmin(newQueue) + " Current min data : " + newQueue.extractMin(newQueue));
        System.out.println("Current min: " + newQueue.vEBmin(newQueue) + " Current min data : " + newQueue.extractMin(newQueue));
        System.out.println("Current min: " + newQueue.vEBmin(newQueue) + " Current min data : " + newQueue.extractMin(newQueue));
        System.out.println("Current min: " + newQueue.vEBmin(newQueue) + " Current min data : " + newQueue.extractMin(newQueue));
        System.out.println("Current min: " + newQueue.vEBmin(newQueue) + " Current min data : " + newQueue.extractMin(newQueue));
        newQueue.vEBInsert(newQueue, 40, "40");
        newQueue.vEBInsert(newQueue, 50, "50");
        System.out.println("Predecessor of key 50: " + newQueue.vEBPredecessor(newQueue, 50));
        System.out.println("Successor of key 40: " + newQueue.vEBSuccessor(newQueue, 40));

    }
}
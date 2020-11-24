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

public class PriorityQueueVanEmdeBoasTree<T> {

    final private int NIL = -1;
    int u;
    private int min;
    private int max;
    PriorityQueueVanEmdeBoasTree summary;
    PriorityQueueVanEmdeBoasTree[] cluster;

    //Cluster in which Entry is
    private int high(int x) {
        int divisor  = (int) Math.ceil(Math.sqrt(u));
        int highB = (x/divisor);
        return highB;
    }

    //Position of Entry in cluster
    private int low(int x) {
        int lowB = (int) (x % Math.ceil(Math.sqrt(u)));
        return lowB;
    }

    //Index of Entry
    private int index(int x, int y) {
        int rootDownU = (int) Math.ceil(Math.sqrt(u));
        return ((x*rootDownU) + y);
    }

    //Constructor
    public PriorityQueueVanEmdeBoasTree(int size) {
        u = size;
        min = NIL;
        min = NIL;

        //Base case when u <= 2
        if(size <= 2) {
            summary = null;
            cluster = null;
        }
        //if u > 2
        else {
            //new u = sqrt of
            int newU = (int) Math.ceil(Math.sqrt(u));
            //Math.pow(2, Math.ceil((Math.log(u)/2)));

            //declare summary with size u
            summary = new PriorityQueueVanEmdeBoasTree(newU);

            //set cluster array size
            cluster = new PriorityQueueVanEmdeBoasTree[newU];

            //populate cluster array
            for (int i = 0; i < newU; i++) {
                cluster[i] = new PriorityQueueVanEmdeBoasTree((int) Math.ceil(Math.sqrt(u)));
            }
        }
    }

    //Get get value of max Entry(min entry is such that key of min Entry is bigger than all other keys)
    public int vEBmax(PriorityQueueVanEmdeBoasTree vEB){
        return ( vEB.max);
    }

    //Get get value of min Entry(min entry is such that key of min Entry is smaller than all other keys)
    public int vEBmin(PriorityQueueVanEmdeBoasTree vEB){
        return ( vEB.min);
    }

    //Get get value of max Entry(min entry is such that key of min Entry is bigger than all other keys)
//    public T vEBmaxValue(PriorityQueueVanEmdeBoasTree vEB){
//        return (T) vEB.max_data;
//    }
//
//    //Get get value of min Entry(min entry is such that key of min Entry is smaller than all other keys)
//    public T vEBminValue(PriorityQueueVanEmdeBoasTree vEB){
//        return (T) vEB.min_data;
//    }

    //Check membership by priority/key
    private boolean vEBMember(PriorityQueueVanEmdeBoasTree vEB, int x) {
        if((x == vEB.min) || (x == vEB.max)){
            return true;
        } else if (vEB.u == 2) {
            return false;
        } else {
            return vEBMember(vEB.cluster[high(x)], low(x));
        }
    }

    //Base case for Insert
    private void vEBBaseInsert(PriorityQueueVanEmdeBoasTree vEB, int x){
        vEB.min = x;
        vEB.max = x;
    }

    // Insert element in vEB
    public void vEBInset(PriorityQueueVanEmdeBoasTree vEB, int x) {
        if (vEB.min == NIL){
            vEBBaseInsert(vEB, x);
        } else {
            if (x < vEB.min){
                int tempX = x;
                x = vEB.min;
                vEB.min = tempX;
            }
            if (vEB.u > 2){
                if (vEBmin(vEB.cluster[high(x)]) == NIL) {
                    vEBInset(vEB.summary, high(x));
                    vEBBaseInsert(vEB.cluster[high(x)], low(x));
                } else {
                    vEBInset(vEB.cluster[high(x)], vEB.low(x));
                }
            }
            if (x > vEB.max){
                vEB.max = x;
            }
        }

    }

    //Delete operation
    public void vEBDelete(PriorityQueueVanEmdeBoasTree vEB, int x) {
        if (vEB.min == vEB.max) {
            vEB.min = NIL;
            vEB.max = NIL;
        } else if (vEB.u == 2) {
            if (x == 0) {
                vEB.min = 1;
            }
            else {
                vEB.min = 0;
            }
            vEB.max = vEB.min;
        } else {
            if (x == vEB.min) {
                int firstCluster = vEBmin(vEB.summary);
                x = vEB.index(firstCluster, vEBmin(vEB.cluster[firstCluster]));
                vEB.min = x;
            }
            vEBDelete(vEB.cluster[high(x)], low(x));

            if (vEBmin(vEB.cluster[high(x)]) == NIL) {
                vEBDelete(vEB.summary, high(x));
                if (x == vEB.max) {
                    int summaryMax = vEBmax(vEB.summary);
                    if (summaryMax == NIL) {
                        vEB.max = vEB.min;
                    } else {
                        vEB.max = index(summaryMax, vEBmax(vEB.cluster[summaryMax]));
                    }
                }
            }
            else if (x == vEB.max) {
                vEB.max = index(high(x), vEBmax(vEB.cluster[high(x)]));
            }
        }
    }

    public int extractMax(PriorityQueueVanEmdeBoasTree<T> vEB) {
        int result = vEB.max;
        vEBDelete(vEB, result);
        return result;
    }
    //Successor
    public int vEBSuccessor(PriorityQueueVanEmdeBoasTree vEB, int x){
        if (vEB.u ==2){
            if((x == 0) && (vEB.max == 1)){
                return 1;
            }
            else return NIL;
        } else if ((vEB.min != NIL) && (x < vEB.min)){
            return vEB.min;
        } else {
            int maxLow = vEBmax(vEB.cluster[high(x)]);
            if((maxLow != NIL) && low(x) < maxLow){
                int offset = vEBSuccessor(vEB.cluster[high(x)],low(x));
                return index(high(x), offset);
            }
            else {
                int sucCluster = vEBSuccessor(vEB.summary, high(x));
                if (sucCluster == NIL) {
                    return NIL;
                }
                else {
                    int offset = vEBmin(vEB.cluster[sucCluster]);
                    return index(sucCluster, offset);
                }
            }
        }
    }


    public static void main(String[] args) {
        PriorityQueueVanEmdeBoasTree newQueue = new PriorityQueueVanEmdeBoasTree(1024);

        newQueue.vEBInset(newQueue, 2);
        newQueue.vEBInset(newQueue, 444);
        newQueue.vEBInset(newQueue, 4);
        newQueue.vEBInset(newQueue, 44);
        newQueue.vEBInset(newQueue, 22);
        newQueue.vEBInset(newQueue, 1);
        newQueue.vEBInset(newQueue, 43);
        newQueue.vEBInset(newQueue, 773);
        newQueue.vEBInset(newQueue, 443);
        newQueue.vEBInset(newQueue, 223);
        newQueue.vEBInset(newQueue, 13);
        System.out.println(newQueue.extractMax(newQueue));
        System.out.println(newQueue.extractMax(newQueue));
        System.out.println(newQueue.extractMax(newQueue));
        System.out.println(newQueue.extractMax(newQueue));
    }
}
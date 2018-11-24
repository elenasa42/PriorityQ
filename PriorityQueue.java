/*
 * PriorityQueue.java
 */

import java.util.Comparator;

public class PriorityQueue {
    //Array based heap representation
    private Object[] heap;

    //Number of object in the heap
    private int size;

    //Comparator
    protected Comparator cmp;

    /**
     *  Creates heap with a given capacity and default comparator.
     * @param capacity The capacity of the heap being created.
     */
    public PriorityQueue(int capacity){
        this(capacity, new DefaultComparator());
    }
    /**
     * Creates heap with a given capacity and comparator.
     * @param capacity The capacity of the heap being created.
     * @param cmp The comparator that will be used.
     */
    public PriorityQueue(int capacity, Comparator cmp){
        if(capacity < 1) throw new IllegalArgumentException();
        this.heap = new Object[capacity];
        this.size = 0;
        this.cmp = cmp;
    }

    /**
     * Inserts an object in the heap.
     * throws exception if heap capacity is exceeded.
     * @param object The object we want to insert
     */
    public void insert(Object object){
        //Ensures object isn't null
        if(object == null){
            throw new IllegalArgumentException();
        }
        //Checks available space
        //We start placing objects from position 1 in the heap
        if(this.size == this.heap.length - 1){
            throw new IllegalArgumentException();
        }
        //Place object at position size, then increase the size
        heap[++size] = object;
        //The newly added abjects swims
        swim(this.size);
    }

    /**
     * Removes the object at the root of this heap.
     * throws IllegalStateException if heap is empty.
     * return The object removed.
     */
    public Object getMax(){
        //Ensures the heap isn't empty
        if(this.size == 0){
            throw new IllegalStateException();
        }
        //Keep a reference to the root object
        Object object = this.heap[1];

        //Replace root object with the one at the rightmost leaf
        if(this.size > 1){
            this.heap[1] = this.heap[size];
        }

        //Dispose the rightmost leaf
        heap[size--] = null;

        //Sink the new root element
        this.sink(1);

        return object;

    }

    /**
     * Fixes the heap above, item is placed at the end of the heap and swims up
     * @param i current position of the newly added item
     */
    public void swim(int i){
        //if i is at the root
        if(i == 1){return; }

        while(i > 1){
            //find parent
            int p = i/2;
            Object node = this.heap[i];
            Object parent = this.heap[p];

            //Comparing parent with child i
            //if child <= parent
            if(this.cmp.compare(node, parent) <= 0){
                return;
            }
            //Else swap odjects and indexes
            swap(i,p);
            i = p;

        }
    }

    /**
     * Shifts the root element down, fixes heap below
     */
    private void sink(int i){
        //Determine left and right child
        int left = 2*i;
        int right = 2*i + 1;
        int max = left;

        //Find the max of childs
        while (left <= this.size) {

            if(right <= this.size){
                int res = this.cmp.compare(this.heap[left], this.heap[right]);
                if(res > 0){
                    max = left;
                }else{
                    max = right;
                }
            }

            if(this.cmp.compare(heap[i], heap[max]) >= 0){
                return;
            }
            swap(i,max);
            i = max;
            left = 2*i;
            right = 2*i+1;
            max = left;

        }
    }

    /**
     * Interchanges two array elements.
     */
    public void swap(int i, int j){
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    /**
     * Prints the objects of the heap.
     */
    public void print() {
        for (int i=1; i<=size; i++){
            System.out.print(heap[i]+ " ");
        }
        System.out.println();
    }
    /**
     * Chekcs if heap is empty.
     */
    boolean empty(){
        return size == 0;
    }

    public static void main(String args[]){

        PriorityQueue pq = new PriorityQueue(10);
        pq.insert(5);
        pq.print();
        pq.insert(15);
        pq.print();
        pq.insert(13);
        pq.print();
        pq.insert(7);
        pq.print();
        pq.insert(8);
        pq.print();

        pq.getMax();
        pq.print();

    }
}

package week_2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private Item[] s;
    private int N=0;
    public RandomizedQueue(){
        s = (Item[]) new Object[2];
    }                 // construct an empty randomized queue

    public boolean isEmpty(){
        return N==0;
    }                 // is the queue empty?
    public int size(){
        return N;
    }                        // return the number of items on the queue
    private void resize(int capacity)
    {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            temp[i] = s[i];
        s = temp;
    }

    public void enqueue(Item item){
        if(item == null) throw new java.lang.NullPointerException();
        if(N == s.length) resize(2*s.length);
        s[N++] = item;
    }           // add the item

    public Item dequeue(){
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int index = (int) (Math.random()*N);
        Item item = s[index];
        if(index != N-1) s[index] = s[N-1];
        s[N-1] = null;
        N--;
        if(N>0 && N==s.length/4) resize(s.length/2);
        return item;
    }                    // delete and return a random item

    public Item sample(){
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int index = (int) (Math.random()*N);
        Item item = s[index];
        return item;
    }                     // return (but do not delete) a random item

    public Iterator<Item> iterator(){
        return new RQIterator();
    }         // return an independent iterator over items in random order

    private class RQIterator implements Iterator<Item> {
        private int index = 0;
        private Item[] r;
        public RQIterator() {
            r = (Item[]) new Object[N];
            for(int i=0; i<N; i++)
                r[i] = s[i];
            StdRandom.shuffle(r);
        }
        public boolean hasNext() {
            return index < N;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item item = r[index++];
            return item;
        }
    }
    public static void main(String[] args){}   // unit testing
}
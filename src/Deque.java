import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;





public class Deque<Item> implements Iterable<Item> {
    private int N;               // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    public Deque(){
        first = null;
        last  = null;
        N = 0;
    }                           // construct an empty deque

    public boolean isEmpty(){
        return N==0;
    }                 // is the deque empty?

    public int size(){
        return N;
    }                        // return the number of items on the deque

    public void addFirst(Item item){
        if(item == null) throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        //first.pre = null;
        first.next=oldfirst;
        if (isEmpty()) last = first;
        else oldfirst.pre = first;
        N++;

    }          // insert the item at the front

    public void addLast(Item item){
        if(item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        //last.next = null;
        last.pre=oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }           // insert the item at the end

    public Item removeFirst(){
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Queue underflow");
        }
        Item item=first.item;
        first=first.next;
        N--;
        if(isEmpty())
            last=null;
        else first.pre=null;
        return item;

    }                // delete and return the item at the front

    public Item removeLast(){
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Queue underflow");
        }
        Item item=last.item;
        last=last.pre;
        N--;
        if(isEmpty())
            first=null;
        else last.next=null;
        return item;
    }                 // delete and return the item at the end

    public Iterator<Item> iterator(){
        return new ListIterator();
    }         // return an iterator over items in order from front to end

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next()
        {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }
    public static void main(String[] args){
        Deque<String> deque = new Deque<String> ();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if(!s.equals("-")) {
                StdOut.println("1->deque.size()=" +deque.size());
                deque.addFirst(s);
                StdOut.println("2->deque.size()=" +deque.size());
            }
            else if(!deque.isEmpty()) {
                StdOut.println(deque.removeFirst() + " ");
                StdOut.println("3->deque.size()=" +deque.size());
            }
        }
        StdOut.println("(" + deque.size() +" left on the deque)");
    }

}   // unit testing
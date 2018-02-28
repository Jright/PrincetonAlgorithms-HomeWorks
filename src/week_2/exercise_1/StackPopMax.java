package week_2.exercise_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 第 2 个问题
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation.
 * Assume the elements are reals numbers so that you can compare them.
 */
public class StackPopMax implements Iterable<Integer>{

    private Node first;     // top of stack
    private int n;                // size of the stack


    public static void main(String[] args){
        StackPopMax stackPopMax = new StackPopMax();

        for(int i = 0; i < 133; i++){
            Node node = new Node();
            node.integer = i;
            stackPopMax.push(node);
        }

        int maxElement = stackPopMax.findMaxElement();
        System.out.println("Max element: " + maxElement);
    }

    private static class Node{
        private int integer;
        private Node next;
    }

    public void StackPopMax(){
        first = null;
        n = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    public void push(Node node){
        Node oldFirst = first;
        first = new Node();
        first.integer = node.integer;
        first.next = oldFirst;
        n++;
    }

    public Node pop(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Node popNode = first;
        first = first.next;
        n--;
        return popNode;
    }


    public int findMaxElement(){
        int max = (int) first.integer;
        Iterator<Integer> iterator = iterator();
        while(iterator.hasNext()){
            int next = (int) iterator.next();
            if(next > max){
                max = next;
            }
        }
        return max;
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<Integer> iterator() {
        return new ListIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator {
        private Node current;

        public ListIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int item = current.integer;
            current = current.next;
            return java.lang.Integer.valueOf(item);
        }
    }

}

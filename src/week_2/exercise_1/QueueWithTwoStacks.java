package week_2.exercise_1;

import edu.princeton.cs.algs4.Stack;


/**
 * Queue with two stacks.
 * Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
 */
public class QueueWithTwoStacks<Item>{

    private Stack<Item> stackPush;
    private Stack<Item> stackPop;

    public QueueWithTwoStacks(){
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void enqueue(Item obj){
        stackPush.push(obj);
    }

    public Item dequeue(){

        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        if(stackPop.isEmpty()){
            for(Item obj : stackPush){
                stackPop.push(obj);
            }
        }

        return stackPop.pop();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return stackPop.size() + stackPush.size();
    }


    // unit testing
    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> squeue = new QueueWithTwoStacks<>();

        int i = 0;
        int N = 17;
        squeue.enqueue(i);

        while (i <= N) {
            if (i % 3 == 0) {
                System.out.println("Dequeue: " + squeue.dequeue());
            } else {
                squeue.enqueue(i);
                System.out.println("Enqueue: " + i);
            }
            ++i;
        }
        System.out.println("Size: " + squeue.size());
        while (!squeue.isEmpty()) {
            System.out.println("Dequeue: " + squeue.dequeue());
        }
        System.out.println("Size: " + squeue.size());
    }


}

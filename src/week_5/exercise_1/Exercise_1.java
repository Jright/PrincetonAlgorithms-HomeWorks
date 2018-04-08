package week_5.exercise_1;


import edu.princeton.cs.algs4.*;

public class Exercise_1 {

    //Question 1
//    Red–black BST with no extra memory. Describe how to save the memory for storing the color information when implementing a red–black BST.
    //Answer: By storing the color information of the link connected with its parent of a Node

    //Question 2
//    Document search. Design an algorithm that takes a sequence of n document words and a sequence of m query words and find the shortest interval in which
//    the m query words appear in the document in the order given. The length of an interval is the number of words in that interval.
    public static void main(String[] args){

        //Idea: Mapping the words in m and its indices of appearing position in n.
        //Since the question ask to find m words in order. So basically we just have to find the first appearance of 0-position word in m, then save
        //its position and find the second word's appearance position, and it has to be bigger than the first position. And so on.
        //To locate each of the m words more efficiently, we store all words in n using red-black trees.

        //https://github.com/phareskrad/algs4/blob/master/jobinterviewquestions/BalancedSearchTrees.java

        In in = new In("");
        String[] words = in.readAllStrings();

        //Key is each string, Value is the index of these key words show up.
        ST<String, Queue<Integer>> wordIndices = new ST<>();

        //Record each key word's appearance in indices.
        for (int i = 0; i < words.length; i++) {
            if (!wordIndices.contains(words[i])) {
                Queue<Integer> tmp = new Queue<>();
                tmp.enqueue(i);
                wordIndices.put(words[i], tmp);
            }
            else {
                Queue<Integer> tmp = wordIndices.get(words[i]);
                tmp.enqueue(i);
                wordIndices.put(words[i], tmp);
            }
        }

        int bestLow = -1;
        int bestHigh = words.length;
        String[] query = StdIn.readAllStrings();

        //queues stores the of each word in m.
        Queue<Integer>[] keyWordIndexQueues = (Queue<Integer>[]) new Queue[query.length];
        for (int i = 0; i < query.length; i++) {
            keyWordIndexQueues[i] = wordIndices.get(query[i]);
        }

        Queue<Integer> firstQueryWordIndices = wordIndices.get(query[0]);

        //Loop through the all indices of the first word in m
        for (Integer start: firstQueryWordIndices) {
            boolean end = true;
            int low = start;
            int high = low;

            for (int i = 1; i < keyWordIndexQueues.length; i++) {

                //Remove the indices that were smaller than the high position of last word
                //Because the question asked to find the m words in exact same order given
                while (!keyWordIndexQueues[i].isEmpty() && keyWordIndexQueues[i].peek() <= high){
                    keyWordIndexQueues[i].dequeue();
                }

                //If no indices are higher than last word's high position then this problem is unsolvable
                if (keyWordIndexQueues[i].isEmpty()) {
                    end = false;
                    break;
                } else {
                    high = keyWordIndexQueues[i].peek();
                }

            }

            if (end && high - low < bestHigh - bestLow) {
                bestHigh = high;
                bestLow = low;
            }
        }

        if (bestLow >= 0) {
            int interval = bestHigh - bestLow;
            System.out.println("Shortest interval found: " + interval);
        } else {
            System.out.println("Not found");
        }
    }

    //Question 3
//    Generalized queue. Design a generalized queue data type that supports all of the following operations in logarithmic time (or better) in the worst case.
//
//    Create an empty data structure.
//    Append an item to the end of the queue.
//    Remove an item from the front of the queue.
//    Return the ith item in the queue.
//    Remove the ith item from the queue.

    private class GeneralizedQueue<Item>{

        private int index;
        private RedBlackBST<Integer, Item> store;

        public GeneralizedQueue(){
            index = 0;
            store = new RedBlackBST<>();
        }

        public void append(Item item){
            store.put(index++,item);
        }

        public void removeFront(){
            store.deleteMin();
        }

        public Item getItem(int index){
            if(!store.contains(index)){
                return null;
            }

            Item item;
            int rank = store.rank(index);
            item = store.get(rank);
            return item;
        }

        public void removeItem(int index){
            if(!store.contains(index)){
                throw new IllegalArgumentException("You've put a invalid remove index");
            }
            int rank = store.rank(index);
            store.delete(rank);
        }

    }

}

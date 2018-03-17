package week_4.priorityQueue;

import week_3.AlgorithmUtil;

public class UnsortedMaxPQ<Key extends Comparable<Key>> {

    private Key[] queue;
    private int N;

    public UnsortedMaxPQ(int capacity){
        queue = (Key[]) new Comparable[capacity];
        N = capacity;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void insert(Key key){
        queue[N++] = key;
    }

    public Key deleteMax(){
        int max = 0;
        for(int i = 0; i < queue.length; i++){
            if(AlgorithmUtil.less(queue[max], queue[i])){
                max = i;
            }
        }
        AlgorithmUtil.exchange(queue, max, N - 1);
        return queue[--N];
    }
}

package week_4.priorityQueue;

import week_3.AlgorithmUtil;

public class MaxPriorityQueue extends UnsortedMaxPQ{

    public MaxPriorityQueue(int capacity) {
        super(capacity);
    }

    /**
     *
     * @return
     */
    @Override
    public Comparable deleteMax() {
        Comparable max = queue[1];
        AlgorithmUtil.exchange(queue, 1, N--);
        sink(1);
        queue[N + 1] = null;
        return max;
    }

    /**
     * Make comparison of point index to its children, exchange value if violated the given sequence
     * @param index
     */
    public void sink(int index){
        while(index * 2 <= N){
            int j = index * 2;
            if(j < N && AlgorithmUtil.less(queue[j], queue[j + 1])){
                j++;
            }
            if(AlgorithmUtil.less(queue[index], queue[j])){
                AlgorithmUtil.exchange(queue, index, j);
                index = j;
            }
        }
    }

    /**
     * Make element in the bottom swim over to the proper position
     * @param j
     */
    public void swim(int j){
        while(j > 1 && AlgorithmUtil.less(queue[j / 2], queue[j])){
            AlgorithmUtil.exchange(queue, j, j / 2);
            j = j / 2;
        }
    }

    /**
     * insert a
     * @param k
     */
    public void insert(int k){
        queue[++N] = k;
        swim(N);
    }


}

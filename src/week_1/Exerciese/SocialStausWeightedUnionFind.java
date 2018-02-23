package week_1.Exerciese;



import java.util.Arrays;

/**
 * Social network connectivity.
 * Given a social network containing n members and a log file containing m timestamps at which times pairs of members formed friendships,
 * design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ...of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or better
 * and use extra space proportional to n.
 *
 *  Sample structure of log file:
 *
 * T1: A B T2: C D T3: A D . . . etc.
 *
 * "T1: A B" => This means A and B became friends at time T1. Note that T1 < T2
 * < T3 etc. (i.e. file is sorted based on timestamps in ascending order)
 *
 * Note: this solution is mainly inspired by https://github.com/kowshik/big-o/blob/master/java/src/graphs/SocialNetworkConnectivity.java Thanks mate
 */

public class SocialStausWeightedUnionFind {

    //Person A is Person B's friend, so graph[A] = B.
    private final int[] graph;

    //the size array of each tree, for example, graph[C] = B , graph[B] = A, graph[A] = A ,then sizes[C] = 3, sizes[B] = 2, etc...
    private final int[] sizes;

    //How many roots this network graph has, initially everyone's root is him/herself
    private int numRoots;

    //How many people are in this social network
    private final int size;


    public SocialStausWeightedUnionFind(int size){
        if (size < 0) {
            throw new IllegalArgumentException(String.format(
                    "Size of the graph has to be non-negative. You passed: %d",
                    size));
        }
        this.size = size;
        graph = new int[size];
        numRoots = size;

        for (int index = 0; index < size; index++) {
            graph[index] = index; // each node is a separate component.
        }

        sizes = new int[size];
        Arrays.fill(sizes, 1);
    }

    public int getRoot(int person){
        while(person != graph[person]){
            person = graph[person];
        }
        return person;
    }

    private void checkNodeIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                    String.format(
                            "Index of a node in the graph has to be >= 0 and < %d. You passed: %d",
                            size, index));
        }
    }

    /**
     * union two people p1 and p2 as friends, using the idea of weighted union-find
     * @param p1
     * @param p2
     */
    public void union(int p1, int p2){
        if(!allConnected()){
            checkNodeIndex(p1);
            checkNodeIndex(p2);

            int root1 = getRoot(p1);
            int root2 = getRoot(p2);

            if(root1 != root2){

                numRoots -= 1;

                if(sizes[p1] >= sizes[p2]){//p2 is the smaller tree, we merge it to the bigger tree, so that wo can get a new merged tree with minimum height
                    graph[root2] = root1;
                    sizes[root1] += sizes[root2];
                }else{
                    graph[root1] = root2;
                    sizes[root2] += sizes[root1];
                }
            }
        }
    }

    public boolean allConnected(){
        return numRoots == 1;
    }

    public boolean isConnected(int p1,int p2){
        checkNodeIndex(p1);
        checkNodeIndex(p2);

        return getRoot(p1) == getRoot(p2);
    }

    public static void main(String[] args) {
        SocialStausWeightedUnionFind w = new SocialStausWeightedUnionFind(6);

        int foo = 0;
        int bar = 5;
        w.union(foo, bar);
        System.out.printf("%d => %d\n", foo, bar);
        System.out.printf("all connected? => %s\n", w.allConnected());

        foo = 1;
        bar = 5;
        w.union(foo, bar);
        System.out.printf("%d => %d\n", foo, bar);
        System.out.printf("all connected? => %s\n", w.allConnected());

        foo = 2;
        bar = 0;
        w.union(foo, bar);
        System.out.printf("%d => %d\n", foo, bar);
        System.out.printf("all connected? => %s\n", w.allConnected());

        foo = 3;
        bar = 4;
        w.union(foo, bar);
        System.out.printf("%d => %d\n", foo, bar);
        System.out.printf("all connected? => %s\n", w.allConnected());

        foo = 3;
        bar = 0;
        w.union(foo, bar);
        System.out.printf("%d => %d\n", foo, bar);
        System.out.printf("all connected? => %s\n", w.allConnected());
    }

}



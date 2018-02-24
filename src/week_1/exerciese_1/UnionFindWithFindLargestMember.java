package week_1.exerciese_1;



import java.util.ArrayList;
import java.util.Collections;

/**
 * Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the largest element
 * in the connected component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements
 * in the connected components.
 *
 * Note: This Union-Find is not weighted. The official course note gives a hint,"maintain an extra array to the weighted quick-union data structure that stores for each
 * root i the large element in the connected component containing i."
 */
public class UnionFindWithFindLargestMember {

    private final int[] graph;
    private int size;

    public UnionFindWithFindLargestMember(int size){
        this.size = size;
        graph = new int[size];

        for (int index = 0; index < size; index++) {
            graph[index] = index; // each node is a separate component.
        }
    }

    public void checkIndexValid(int index){
        if(index < 0 || index > size - 1){
            throw new IllegalArgumentException("The index you put is invalid");
        }
    }

    public void union(int foo,int bar){
        checkIndexValid(foo);
        checkIndexValid(bar);

        int fooRoot = getRoot(foo);
        int barRoot = getRoot(bar);

        if(fooRoot != barRoot){
            graph[fooRoot] = barRoot;
        }
    }

    public int getRoot(int index){
        checkIndexValid(index);

        while(index != graph[index]){
            index = graph[index];
        }

        return index;
    }

    public int find(int index){
        ArrayList<Integer> findResults = new ArrayList<>();

        findResults.add(index);
        while(index != graph[index]){
            index = graph[index];
            findResults.add(index);
        }

        Collections.sort(findResults);

        return findResults.get(findResults.size() - 1);
    }

    public boolean connected(int foo,int bar){
        checkIndexValid(foo);
        checkIndexValid(bar);

        return getRoot(foo) == getRoot(bar);
    }

    public static void main(String[] args){
        UnionFindWithFindLargestMember unionFind = new UnionFindWithFindLargestMember(10);

        unionFind.union(0,1);

        unionFind.union(1,2);

        unionFind.union(4,5);


        System.out.println("find 0: " + unionFind.find(0));

        System.out.println("find 4: " + unionFind.find(4));

        System.out.println("find 8: " + unionFind.find(8));

    }

}

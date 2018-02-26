package week_1.exercise_1;

import java.util.ArrayList;
import java.util.Collections;

/*
Successor with delete. Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:

        Remove x from S
        Find the successor of x: the smallest y in S such that y≥x.
        design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
*/

/**
 * Hint: use the modification of the union−find data discussed in the previous question.
 *
 * //TODO 删除操作弹出的数字还不对。
 */
public class SuccessorWithDelete {

    private final int[] graph;
    private int size;

    public SuccessorWithDelete(int size){
        this.size = size;
        graph = new int[size];

        for (int index = 0; index < size; index++) {
            graph[index] = index; // each node is a separate component.
            if(index != size - 1){
                union(index,index + 1);
            }
        }
    }

    //FIXME 删除之后的返回不对
    public int deleteElement(int index){
        int initialIndex = index;
        for(; index < size - 1; index ++){
            graph[index] = graph[index + 1];
        }
        return graph[initialIndex];
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
//        UnionFindWithFindLargestMember unionFind = new UnionFindWithFindLargestMember(10);
//
//        unionFind.union(0,1);
//
//        unionFind.union(1,2);
//
//        unionFind.union(4,5);

        SuccessorWithDelete successorWithDelete = new SuccessorWithDelete(10);

        System.out.println("delete 0: " + successorWithDelete.deleteElement(4));

//        System.out.println("delete 4: " + successorWithDelete.deleteElement(4));

//        System.out.println("delete 8: " + successorWithDelete.deleteElement(8));

    }

}

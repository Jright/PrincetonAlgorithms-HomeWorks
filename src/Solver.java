import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class Solver {

    private Board mInitialBoard;

    private class SearchNode{
        SearchNode predecessor;
        Board currentBoard;
        int manhattanCount;
    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        this.mInitialBoard = initial;

    }

    // is the initial board solvable?
    public boolean isSolvable(){
        int reverseNum = 0;

        Queue<Integer> allElements = mInitialBoard.getAllElements();
        while(allElements.size() > 1){
            Integer dequeue = allElements.dequeue();
            for (Integer next : allElements) {
                if (dequeue > next) {
                    reverseNum++;
                }
            }
        }

        return reverseNum % 2 == 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if(!isSolvable()){
            return -1;
        }

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){

    }

    // solve a slider puzzle (given below)
    public static void main(String[] args){

    }

}
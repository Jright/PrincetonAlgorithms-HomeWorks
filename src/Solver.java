import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private Board mInitialBoard;
    private SearchNode mGoalNode;

    private class SearchNode implements Comparable<SearchNode> {

        SearchNode predecessor;
        Board currentBoard;
        int manhattanCount;
        int moves;

        public SearchNode(SearchNode predecessor, Board board){
            this.predecessor = predecessor;
            this.currentBoard = board;
            this.manhattanCount = currentBoard.manhattan();
            if(predecessor != null){
                this.moves = predecessor.moves + 1;
            }else{
                this.moves = 0;
            }
        }

        @Override
        public int compareTo(SearchNode o) {
            int priorityThis = this.moves + this.manhattanCount;
            int priorityThat = o.moves + o.manhattanCount;
            int result = priorityThis - priorityThat;
            return Integer.compare(result, 0);
        }
    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if(initial == null){
            throw new IllegalArgumentException("Cannot input a null object");
        }
        this.mInitialBoard = initial;

        MinPQ<SearchNode> minPQNodes = new MinPQ<>();
        MinPQ<SearchNode> minPQTwinNodes = new MinPQ<>();

        SearchNode initNode = new SearchNode(null, mInitialBoard);
        //
        SearchNode initTwinNode = new SearchNode(null, mInitialBoard.twin());

        minPQNodes.insert(initNode);
        minPQTwinNodes.insert(initTwinNode);

        while(true){

            if(!minPQNodes.isEmpty()){
                initNode = minPQNodes.delMin();
            }else{
                break;
            }

            if(initNode != null && initNode.currentBoard.isGoal()){
                mGoalNode = initNode;
                return;
            }

            if(!minPQTwinNodes.isEmpty()){
                initTwinNode = minPQTwinNodes.delMin();
            }else{
                break;
            }

            if(initTwinNode != null && initTwinNode.currentBoard.isGoal()){
                mGoalNode = initNode;
                return;
            }

            for(Board neighborBoard : initNode.currentBoard.neighbors()){
                insertNeighborBoards(minPQNodes, initNode, neighborBoard);
            }

            for(Board neighborBoard : initTwinNode.currentBoard.neighbors()){
                insertNeighborBoards(minPQTwinNodes,initTwinNode, neighborBoard);
            }
        }
    }

    private void insertNeighborBoards(MinPQ<SearchNode> nodes, SearchNode currentNode, Board neighbor){
        if(!currentNode.predecessor.currentBoard.equals(neighbor)){
            nodes.insert(new SearchNode(currentNode, neighbor));
        }
    }

    // is the initial board solvable?
    // Tricky move given by https://math.stackexchange.com/a/838818 don't know the theory behind it.
    public boolean isSolvable(){

        if(mInitialBoard == null){
            throw new IllegalArgumentException("InitBoard cannot be null!");
        }

        return mGoalNode != null;
//        int reverseNum = 0;
//
//        Queue<Integer> allElements = getAllElements(mInitialBoard.mBlocks);
//        while(allElements.size() > 1){
//            Integer dequeue = allElements.dequeue();
//            for (Integer next : allElements) {
//                if (dequeue > next) {
//                    reverseNum++;
//                }
//            }
//        }
//
//        return reverseNum % 2 == 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if(!isSolvable() || mGoalNode == null){
            return -1;
        }else{
            return mGoalNode.moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if(!isSolvable() || mGoalNode == null){
            return null;
        }else{
            SearchNode node = mGoalNode;
            Stack<Board> stack = new Stack<>();
            stack.push(node.currentBoard);
            while(node.predecessor != null){
                stack.push(node.predecessor.currentBoard);
                node = node.predecessor;
            }
            return stack;
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args){

    }

}
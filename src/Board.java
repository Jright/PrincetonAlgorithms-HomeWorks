import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class Board {

    private int[][] mBlocks;
    private int mBlankRow, mBlankColumn;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        mBlocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++){
            for(int j = 0; j < blocks.length; j++){
                mBlocks[i][j] = blocks[i][j];
                if(mBlocks[i][j] == 0){
                    mBlankRow = i;
                    mBlankColumn = j;
                }
            }
        }
    }

    // board dimension n
    //Since it is a n * n block, so the length can be fetched directly from blocks.length
    public int dimension() {
        return mBlocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for(int i = 0; i <= mBlocks.length; i++){
            for(int j = 0; j < mBlocks[i].length; j++){

                if(mBlocks[i][j] == 0){
                    continue;
                }

                if(mBlocks[i][j] != i * dimension() + j){
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for(int i = 1; i <= mBlocks.length; i++){
            for(int j = 1; j <= mBlocks[i].length; j++){

                //The right number for this specific location
                int origin = (i - 1) * dimension() + j;
                //The num difference of the number currently occupying this position, compare to the original(right) number
                int difference = mBlocks[i][j] - origin;

                if(difference == 0 || mBlocks[i][j] == 0){
                    continue;
                }
                //By lifting/dropping one grid vertically, the number we choose will add/minus a value of the dimension()
                //horizontally move -> +1 or -1
                //So we can calculate the total move for a wrong positioned number to its difference with the original number
                //with the result of its current number divide and take the remainder with the dimension.

                int remainder = difference % dimension();
                int divideResult = difference / dimension();

                count = count + remainder + divideResult;

            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int rowA = StdRandom.uniform(dimension());
        int rowB = StdRandom.uniform(dimension());
        int columnA = StdRandom.uniform(dimension());
        int columnB = StdRandom.uniform(dimension());

        int[][] newBlocks = new int[mBlocks.length][mBlocks.length];
        for (int i = 0; i < newBlocks.length; i++){
            System.arraycopy( mBlocks[i], 0, newBlocks[i], 0, newBlocks[i].length);
        }

        int temp = newBlocks[rowA][columnA];
        newBlocks[rowA][columnA] = newBlocks[rowB][columnB];
        newBlocks[rowB][columnB] = temp;

        return new Board(newBlocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if(y == null){
            return false;
        }else if(y.getClass() != this.getClass()){
            return false;
        }else if(y == this){
            return true;
        }else{

            Board obj = (Board) y;
            if(obj.hamming() != ((Board) y).hamming() || obj.mBlocks.length != this.mBlocks.length){
                return false;
            }

            int n = mBlocks.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (obj.mBlocks[i][j] != this.mBlocks[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighborBoards = new Queue<>();

        if(mBlankRow > 0){
            neighborBoards.enqueue(swap(this, mBlankRow, mBlankColumn, mBlankRow - 1, mBlankColumn));
        }

        if(mBlankRow < dimension() - 1){
            neighborBoards.enqueue(swap(this, mBlankRow, mBlankColumn, mBlankRow + 1, mBlankColumn));
        }

        if(mBlankColumn > 0){
            neighborBoards.enqueue(swap(this, mBlankRow, mBlankColumn, mBlankRow, mBlankColumn - 1));
        }

        if(mBlankColumn < dimension() - 1){
            neighborBoards.enqueue(swap(this, mBlankRow, mBlankColumn, mBlankRow, mBlankColumn + 1));
        }

        return neighborBoards;

    }

    private Board swap(Board board, int rowSrc, int columnSrc, int rowDest, int columnDest){
        int[][] blocks = board.mBlocks;
        int temp = blocks[rowSrc][columnSrc];
        blocks[rowSrc][columnSrc] = blocks[rowDest][columnDest];
        blocks[rowDest][columnDest] = temp;
        return new Board(blocks);
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(dimension()).append('\n');
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                builder.append(String.format("%2d ", mBlocks[i][j]));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }

}

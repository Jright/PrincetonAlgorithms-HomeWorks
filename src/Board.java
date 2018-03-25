import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class Board {

    private int[] mBlocks;
    private int mBlankRow, mBlankColumn;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)

    public Board(int[][] blocks) {
        if(blocks == null){
            throw new IllegalArgumentException("Cannot input a null object");
        }

        mBlocks = new int[blocks.length * blocks.length];
        for (int i = 0; i < blocks.length; i++){
            for (int j = 0; j < blocks.length; j++){
                this.mBlocks[i * blocks.length + j] = blocks[i][j];
                if(blocks[i][j] == 0){
                    this.mBlankRow = i;
                    this.mBlankColumn = j;
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
        int cnt = 0;
        for (int i = 0; i < mBlocks.length; i++){
            if (mBlocks[i] != 0 && mBlocks[i] != i+1){
                cnt++;
            }
        }
        return cnt;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum;
        int row, col;

        sum = 0;
        for (int i = 0; i < mBlocks.length; i++) {
            if (mBlocks[i] != 0 && mBlocks[i] != i+1) {
                col = Math.abs(i % mBlocks.length - (mBlocks[i]-1) % mBlocks.length);
                row = Math.abs(i / mBlocks.length - (mBlocks[i]-1) / mBlocks.length);
                sum += (row + col);
            }
        }
        return sum;
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
                    if (obj.mBlocks[i * mBlocks.length + j] != this.mBlocks[i * mBlocks.length + j]){
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
        int[] blocks = board.mBlocks;
        int temp = blocks[rowSrc * blocks.length + columnSrc];
        blocks[rowSrc * blocks.length + columnSrc] = blocks[rowDest * blocks.length + columnDest];
        blocks[rowDest * blocks.length + columnDest] = temp;

        int[][] newBlock = new int[blocks.length][blocks.length];
        for(int i = 0; i < newBlock.length; i++){
            for (int j = 0; j < newBlock[i].length; j++){
                newBlock[i][j] = blocks[i * blocks.length + j];
            }
        }
        return new Board(newBlock);
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(dimension()).append('\n');
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                builder.append(String.format("%2d ", mBlocks[i * mBlocks.length + j]));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }

}

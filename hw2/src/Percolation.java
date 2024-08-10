import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    public WeightedQuickUnionUF board;
    private int numberOfOpenSites;
    private int topNum;
    private int bottomNum;
    private int[] openSites;
    private int oneRow;
    private WeightedQuickUnionUF counterpart;
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if(N <= 0) {
            throw new IllegalArgumentException();
        }
        board = new WeightedQuickUnionUF(N * N + 2);
        counterpart = new WeightedQuickUnionUF(N * N + 1);
        numberOfOpenSites = 0;
        openSites = new int[N * N];
        topNum = N * N;
        bottomNum = N * N + 1;
        oneRow = N; // quantity of sqrt(board)
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        validate(row,col);
        openSites[xyTo1D(row,col)] = 1;
        numberOfOpenSites++;
        if(xyTo1D(row,col) >= (topNum - oneRow)){
            board.union(xyTo1D(row,col),bottomNum);
        }
        if(xyTo1D(row,col) < oneRow){
            board.union(xyTo1D(row,col),topNum);
            counterpart.union(xyTo1D(row,col),topNum);
            return;
        }
        //check one whether union around with itself
        int[] x = new int[] {-1,0,1,0};
        int[] y = new int[] {0,1,0,-1};
        for(int i = 0; i < x.length; i++){
           int col_val = col + y[i];
           int row_val = row + x[i];
           if((xyTo1D(col_val,row_val) >= 0 && xyTo1D(col_val,row_val) < topNum && isOpen(row_val,col_val))){
               board.union(xyTo1D(row_val,col_val),xyTo1D(row,col));
               counterpart.union(xyTo1D(row_val,col_val),xyTo1D(row,col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if(xyTo1D(row,col) < 0 || xyTo1D(row,col) >= topNum) return false;
        return openSites[xyTo1D(row,col)] == 1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row,col);
        return counterpart.connected(xyTo1D(row,col),topNum);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return board.connected(topNum,bottomNum);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private int xyTo1D(int row, int col){
        return (oneRow * row) + col;
    }

    private void validate(int row, int col) {
        if(xyTo1D(row,col) < 0 || xyTo1D(row,col) >= topNum) {
            throw new IndexOutOfBoundsException();
        }
    }


}

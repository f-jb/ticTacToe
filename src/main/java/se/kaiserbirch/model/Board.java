package se.kaiserbirch.model;

class Board {
    private final int rows;
    private final int columns;
    private Square[][] board;
    protected Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        board = new Square[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Square();
            }
        }
    }


    protected void reset(){
        board = new Square[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Square();
            }
        }
    }

    protected Square[][] getBoard() {
        return board;
    }

    protected void play(int row, int column, State state) {
        board[row][column].setState(state);
    }

}

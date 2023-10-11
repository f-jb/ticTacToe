package se.kaiserbirch.model;

import static se.kaiserbirch.model.Mark.*;

public class Board {
    private final int boardWidth;
    private Mark[][] board;
    private Mark winningMark;
    private boolean crossTurn, gameOver;
    private int availableMoves;

    public Board(int boardWidth){
        this.boardWidth = boardWidth;
        this.crossTurn = true;
        this.gameOver =false;
        this.winningMark = BLANK;
        availableMoves = boardWidth * boardWidth;
        init();
    }

    protected void init(){
        this.board = new Mark[boardWidth][boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                board[i][j] = BLANK;
            }
        }
    }

    public boolean placeMark(int row, int column) {
        if( row < 0 || row >= boardWidth || column < 0 || column >= boardWidth || isTileMarked(row, column) || gameOver){
            return false;
        }
        availableMoves--;
        board[row][column] = crossTurn ? CROSS : CIRCLE;
        togglePlayer();
        checkWin(row, column);
        return true;
    }
    void checkWin(int row, int column){
        int rowSum = 0;
        // check rows wins
        for (int iterColumn = 0; iterColumn < boardWidth; iterColumn++) {
            rowSum += getMarkAt(row,iterColumn).getMark();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningMark + " has won via row: " + row + " column: " + column);
            return;
        }


        // check columns wins
        rowSum = 0;
        for (int iterRow = 0; iterRow < boardWidth; iterRow++) {
            rowSum += getMarkAt(iterRow,column).getMark();
        }
        if(calcWinner(rowSum) != BLANK){
            System.out.println(winningMark + " has won via row: " + row + " column: " + column);
            return;
        }


        //check diagonal
        rowSum = 0;
        for (int i = 0; i< boardWidth; i++) {
            rowSum += getMarkAt(i,i).getMark();
        }
        if(calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " has won via row: " + row + " column: " + column);
            return;
        }

        rowSum = 0;
        int indexMax = boardWidth - 1;
        for (int i = 0; i< indexMax; i++) {
            rowSum += getMarkAt(i,indexMax - i).getMark();
        }
        if(calcWinner(rowSum) != BLANK) {
            System.out.println(winningMark + " has won via row: " + row + " column: " + column);
            return;
        }
        if(!anyAvailableMoves()){
            gameOver = true;
            System.out.println("tie");
        }


    }

    private Mark calcWinner(int rowSum) {
        int crossWin = CROSS.getMark() * boardWidth;
        int circleWin = CIRCLE.getMark() * boardWidth;
        if(rowSum == crossWin){
            gameOver =true;
            winningMark = CROSS;
            return CROSS;
        } else if(rowSum == circleWin){
            gameOver =true;
            winningMark = CIRCLE;
            return CIRCLE;
        }
        return BLANK;
    }

    private Mark getMarkAt(int row, int column) {
        return board[row][column];
    }

    private void togglePlayer() {
        crossTurn = !crossTurn;
    }

    private boolean isTileMarked(int row, int col) {
        return board[row][col].isMarked();
    }

    protected Mark[][] getBoard() {
        return board;
    }

    public int getWidth() {
        return boardWidth;
    }

    public boolean isCrossTurn() {
        return crossTurn;
    }

    public boolean anyAvailableMoves() {
        return availableMoves > 0;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Mark getWinningMark() {
        return winningMark;
    }
    public void setMarkAt(int row, int column, Mark newMark) {
        board[row][column] = newMark;
    }
}

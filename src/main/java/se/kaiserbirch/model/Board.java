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

    protected boolean placeMark(int row, int column) {
        if( row < 0 || row >= boardWidth || column < 0 || column >= boardWidth || isTileMarked(row, column) || gameOver){
            return false;
        }
        availableMoves--;
        board[row][column] = crossTurn ? CROSS : CIRCLE;
        togglePlayer();
        Logic.checkWin(this, row, column);
        return true;
    }


    protected void setWinningMark(Mark winningMark) {
        this.winningMark = winningMark;
    }

    protected void setGameOver() {
        this.gameOver = true;
    }

    protected Mark getMarkAt(int row, int column) {
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

    public boolean isMarkedTile(int row, int column) {
        return board[row][column].isMarked();
    }
}

package se.kaiserbirch.controller;

import se.kaiserbirch.model.Mark;

public class UiState {
    private final Mark[][] board;
    private final int[] recommendedMove;
    private final boolean gameOver;
    private final Mark winningMark;

    public boolean isGameOver(){
        return gameOver;
    }
    public Mark getWinningMark(){
        return winningMark;
    }

    UiState(Builder builder){
        this.recommendedMove = builder.recommendedMove;
        this.board = builder.board;
        this.gameOver = builder.gameOver;
        this.winningMark = builder.winningMark;

    }

    public int[] getRecommendedMove() {
        return recommendedMove;
    }

    public Mark[][] getBoard() {
        return board;
    }

    public static class Builder{
        private Mark[][] board;
        private int[] recommendedMove = {-1,-1};
        private boolean gameOver;
        private Mark winningMark;
        Builder setGameOver(boolean gameOver){
            this.gameOver = gameOver;
            return this;
        }
        Builder setWinningMark(Mark winningMark){
            this.winningMark = winningMark;
            return this;
        }

        Builder setBoard(Mark[][] board){
            this.board = board;
            return this;
        }
        Builder setRecommendedMove(int[] recommendedMove){
            this.recommendedMove = recommendedMove;
            return this;
        }
        UiState build(){
            return new UiState(this);
        }
    }

}

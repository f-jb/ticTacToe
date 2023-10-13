package se.kaiserbirch.controller;

import se.kaiserbirch.model.Mark;

public class UiState {
    private final Mark[][] board;
    private final int[] recommendedMove;

    UiState(Builder builder){
        this.recommendedMove = builder.recommendedMove;
        this.board = builder.board;
    }

    public int[] getRecommendedMove() {
        return recommendedMove;
    }

    public Mark[][] getBoard() {
        return board;
    }

    public static class Builder{
        private Mark[][] board;
        private int[] recommendedMove;
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

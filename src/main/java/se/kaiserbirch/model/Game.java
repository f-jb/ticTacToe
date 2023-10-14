package se.kaiserbirch.model;

import se.kaiserbirch.model.ai.AiController;

public class Game implements GameInterface {
    final Board board;
    final boolean versusComputer = true;
    final AiController aiController = new AiController();
    public Game(int width){
        board = new Board(width);
    }
    public int[] getMove(){
        return aiController.getMove(board);
    }

    @Override
    public void reset(){
        board.init();
    }

    @Override
    public Mark[][] getBoard(){
       return board.getBoard();
    }

    @Override
    public void play(int row, int column){
        if(!board.isGameOver()) {
            board.placeMark(row, column);
            if(versusComputer){
                board.placeMark(getMove());
            }
        }
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public Mark getWinningMark() {
        return board.getWinningMark();
    }
}

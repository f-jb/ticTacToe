package se.kaiserbirch.model;

import se.kaiserbirch.model.ai.AiController;
import se.kaiserbirch.model.ai.AiControllerInterface;

public class Game implements GameInterface {
    final Board board;
    final boolean versusComputer = true;
    final AiControllerInterface aiController = new AiController();
    public Game(int width){
        board = new Board(width);
    }
    @Override
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

    @Override
    public boolean isGameOver() {
        return board.isGameOver();
    }

    @Override
    public Mark getWinningMark() {
        return board.getWinningMark();
    }
}

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

    public void showBoard(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("X123");
        stringBuilder.append('\n');
        for (Mark[] row: board.getBoard()) {
            stringBuilder.append("1");
            for (Mark mark:row) {
                stringBuilder.append(mark);
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
    }

    @Override
    public void play(int row, int column){
        if(!board.isGameOver()) {
            board.placeMark(row, column);
            if(versusComputer){
                board.placeMark(getMove());

            }
            showBoard();
        }
        if(board.isGameOver()){
            System.out.println(board.getWinningMark() + " has won.");
        }
    }

    public void play(int[] choice){
        if(!board.isGameOver()) {
            board.placeMark(choice[0], choice[1]);
            showBoard();
        }
        if(board.isGameOver()){
            System.out.println(board.getWinningMark() + " has won.");
        }
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public Mark getWinningMark() {
        return board.getWinningMark();
    }
}

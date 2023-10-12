package se.kaiserbirch.model;

import se.kaiserbirch.ai.MinMax;

public class Game implements GameInterface {
    Board board;
    public Game(int width){
        board = new Board(width);
    }
    public int[] getBestMove(){
        return MinMax.getBestMove(board);
    }

    @Override
    public void reset(){
        board.init();
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

}

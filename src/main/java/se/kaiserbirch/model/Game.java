package se.kaiserbirch.model;

public class Game implements GameInterface {
    Board board;
    public Game(int width){
        board = new Board(width);
    }

    @Override
    public void reset(){
        board.init();
    }

    public void showBoard(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Mark[] row: board.getBoard()) {
            for (Mark mark:row) {
                stringBuilder.append(mark);
                stringBuilder.append(" ");
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
    }

    @Override
    public void play(int row, int column){
        board.placeMark(row,column);
    }
}

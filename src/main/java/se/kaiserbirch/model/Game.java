package se.kaiserbirch.model;

public class Game implements GameInterface {
    Board board;
    public Game(int rows, int columns){
        board = new Board(rows,columns);
    }

    @Override
    public void reset(){
        board.reset();
    }

    public void showBoard(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Square[] row: board.getBoard()) {
            for (Square square:row) {
                stringBuilder.append(square);
                stringBuilder.append(" ");
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
    }

    @Override
    public void play(int row, int column, State state){
        board.play(row,column,state);
        State whoHasWon = Logic.checkWin(board.getBoard());
        if(whoHasWon != State.BLANK){
            System.out.println(whoHasWon + " has Won!");
        }
    }
}

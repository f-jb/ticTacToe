package se.kaiserbirch.model;

import java.util.Arrays;

public class Logic {
    static State checkWin(Square[][] board){
        Square[] firstRowWin = new Square[]{board[0][0],board[0][1],board[0][2]};
        Square[] secondRowWin= new Square[]{board[1][0],board[1][1],board[1][2]};
        Square[] thirdRowWin= new Square[]{board[2][0],board[2][1],board[2][2]};
        Square[] firstColumnWin= new Square[]{board[0][0],board[1][0],board[2][0]};
        Square[] secondColumnWin= new Square[]{board[0][1],board[1][1],board[2][1]};
        Square[] thirdColumnWin= new Square[]{board[0][2],board[1][2],board[2][2]};
        Square[] firstDiagonalWin= new Square[]{board[0][0],board[1][1],board[2][2]};
        Square[] secondDiagonalWin= new Square[]{board[2][2],board[1][1],board[0][0]};
        Square[][] winStates = new Square[][]{firstRowWin,secondRowWin,thirdRowWin,firstColumnWin,secondColumnWin,thirdColumnWin,firstDiagonalWin,secondDiagonalWin};
        for (Square[] possibleWins: winStates) {
            State winning = checkForWinningStates(possibleWins);
            if(winning != State.BLANK){
                return winning;
            }
        }
       return State.BLANK;
    }
    static State checkForWinningStates(Square[] row){
        if(Arrays.stream(row).anyMatch( square -> square.getState() == State.BLANK)){
            return State.BLANK;
        } else if(Arrays.stream(row).allMatch( square -> square.getState() == State.CIRCLE)){
            return State.CIRCLE;
        } else if(Arrays.stream(row).allMatch( square -> square.getState() == State.CROSS)){
            return State.CROSS;
        }
        return State.BLANK;

    }
}

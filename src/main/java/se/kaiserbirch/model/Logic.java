package se.kaiserbirch.model;

import java.util.Arrays;

public class Logic {
    static Mark checkWin(Mark[][] board, int row, int column){
        /*
        for(int i = 0; i>board.getBoardWidth(); i++){

        }
        Mark[] firstRowWin = new Mark[]{board[0][0],board[0][1],board[0][2]};
        Mark[] secondRowWin= new Mark[]{board[1][0],board[1][1],board[1][2]};
        Mark[] thirdRowWin= new Mark[]{board[2][0],board[ 2][1],board[2][2]};
        Mark[] firstColumnWin= new Mark[]{board[0][0],board[1][0],board[2][0]};
        Mark[] secondColumnWin= new Mark[]{board[0][1],board[1][1],board[2][1]};
        Mark[] thirdColumnWin= new Mark[]{board[0][2],board[1][2],board[2][2]};
        Mark[] firstDiagonalWin= new Mark[]{board[0][0],board[1][1],board[2][2]};
        Mark[] secondDiagonalWin= new Mark[]{board[2][2],board[1][1],board[0][0]};
        Mark[][] winStates = new Mark[][]{firstRowWin,secondRowWin,thirdRowWin,firstColumnWin,secondColumnWin,thirdColumnWin,firstDiagonalWin,secondDiagonalWin};
        for (Mark[] possibleWins: winStates) {
            Mark winning = checkForWinningStates(possibleWins);
            if(winning != Mark.BLANK){
                return winning;
            }
        }
         */
       return Mark.BLANK;

    }
    static Mark checkForWinningStates(Mark[] row){
        if(Arrays.stream(row).anyMatch( mark -> mark == Mark.BLANK)){
            return Mark.BLANK;
        } else if(Arrays.stream(row).allMatch( mark -> mark == Mark.CIRCLE)){
            return Mark.CIRCLE;
        } else if(Arrays.stream(row).allMatch( mark -> mark == Mark.CROSS)){
            return Mark.CROSS;
        }
        return Mark.BLANK;

    }
}

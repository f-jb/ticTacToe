package se.kaiserbirch.model;

import static se.kaiserbirch.model.Mark.*;
import static se.kaiserbirch.model.Mark.CIRCLE;

public class Logic {
    static void checkWin(Board board, int row, int column){
        int boardWidth = board.getWidth();
        int rowSum = 0;
        Mark winningMark;

        // Check rows wins
        for (int iterColumn = 0; iterColumn < boardWidth; iterColumn++) {
            rowSum += board.getMarkAt(row,iterColumn).getMark();
        }
        winningMark = calcWinner(rowSum, boardWidth);
        if(winningMark != BLANK){
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Check columns wins
        rowSum = 0;
        for (int iterRow = 0; iterRow < boardWidth; iterRow++) {
            rowSum += board.getMarkAt(iterRow,column).getMark();
        }
        winningMark = calcWinner(rowSum, boardWidth);
        if(winningMark != BLANK){
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Check upper left to lower right diagonal
        rowSum = 0;
        for (int i = 0; i< boardWidth; i++) {
            rowSum += board.getMarkAt(i,i).getMark();
        }
        winningMark = calcWinner(rowSum, boardWidth);
        if(winningMark != BLANK){
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Check lower left to upper right diagonal
        rowSum = 0;
        int indexMax = boardWidth - 1;
        for (int i = 0; i< indexMax; i++) {
            rowSum += board.getMarkAt(i,indexMax - i).getMark();
        }
        winningMark = calcWinner(rowSum, boardWidth);
        if(winningMark != BLANK){
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }
        if(!board.anyAvailableMoves()){
            board.setGameOver();
        }
    }

    private static Mark calcWinner(int rowSum, int boardWidth) {
        int crossWin = CROSS.getMark() * boardWidth;
        int circleWin = CIRCLE.getMark() * boardWidth;
        if(rowSum == crossWin){
            return CROSS;
        } else if(rowSum == circleWin){
            return CIRCLE;
        }
        return BLANK;
    }
}

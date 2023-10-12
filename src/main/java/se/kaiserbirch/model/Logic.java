package se.kaiserbirch.model;

import static se.kaiserbirch.model.Mark.*;

public class Logic {
    private static Mark checkLowerLeftToUpperRightWin(Board board, int boardWidth){
        int rowSum = 0;
        int indexMax = boardWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += board.getMarkAt(i, indexMax - i).getMark();
        }
        return calcWinner(rowSum, boardWidth);
    }
    private static Mark checkUpperLeftToLowerRightWin(Board board, int boardWidth){
        int rowSum = 0;
        for (int i = 0; i < boardWidth; i++) {
            rowSum += board.getMarkAt(i, i).getMark();
        }
        return calcWinner(rowSum, boardWidth);
    }
    private static Mark checkRowWins(Board board, int boardWidth){
        int rowSum = 0;
        Mark winningMark = BLANK;
        for (int row = 0; row < boardWidth; row++) {
            for (int column = 0; column < boardWidth; column++) {
                rowSum += board.getMarkAt(row, column).getMark();
            }
           winningMark = calcWinner(rowSum, boardWidth);
            if (winningMark != BLANK) {
                return winningMark;
            }
        }
        return winningMark;

    }
    private static Mark checkColumnWins(Board board, int boardWidth){
        int rowSum = 0;
        Mark winningMark = BLANK;
        for (int column = 0; column < boardWidth; column++) {
            for (int row = 0; row < boardWidth; row++) {
                rowSum += board.getMarkAt(row, column).getMark();
            }
            winningMark = calcWinner(rowSum, boardWidth);
            if (winningMark != BLANK) {
                return winningMark;
            }
        }
        return winningMark;
    }

    public static Mark checkWin(Board board) {
        int boardWidth = board.getWidth();
        Mark winningMark;

        winningMark = checkRowWins(board,boardWidth);

        if(winningMark != BLANK){
            return winningMark;
        }

        winningMark = checkColumnWins(board,boardWidth);
        if(winningMark != BLANK){
            return winningMark;
        }

        winningMark = checkUpperLeftToLowerRightWin(board,boardWidth);
        if(winningMark != BLANK){
            return winningMark;
        }

        winningMark = checkLowerLeftToUpperRightWin(board,boardWidth);

        return winningMark;
    }

    public static void checkWin(Board board, int row, int column) {
        int boardWidth = board.getWidth();
        int rowSum = 0;
        Mark winningMark;

        // Check rows wins
        for (int iterationColumn = 0; iterationColumn < boardWidth; iterationColumn++) {
            rowSum += board.getMarkAt(row, iterationColumn).getMark();
        }
        winningMark = calcWinner(rowSum, boardWidth);
        if (winningMark != BLANK) {
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Check columns wins
        rowSum = 0;
        for (int iterationRow = 0; iterationRow < boardWidth; iterationRow++) {
            rowSum += board.getMarkAt(iterationRow, column).getMark();
        }
        winningMark = calcWinner(rowSum, boardWidth);
        if (winningMark != BLANK) {
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Check upper left to lower right diagonal
        winningMark = checkUpperLeftToLowerRightWin(board, boardWidth);
        if (winningMark != BLANK) {
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Check lower left to upper right diagonal
        winningMark = checkLowerLeftToUpperRightWin(board,boardWidth);
        if (winningMark != BLANK) {
            board.setWinningMark(winningMark);
            board.setGameOver();
            return;
        }

        // Set tie
        if (!board.anyAvailableMoves()) {
            board.setGameOver();
        }
    }

    private static Mark calcWinner(int rowSum, int boardWidth) {
        int crossWin = CROSS.getMark() * boardWidth;
        int circleWin = CIRCLE.getMark() * boardWidth;
        if (rowSum == crossWin) {
            return CROSS;
        } else if (rowSum == circleWin) {
            return CIRCLE;
        }
        return BLANK;
    }
}

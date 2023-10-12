package se.kaiserbirch.ai;

import se.kaiserbirch.model.Board;
import se.kaiserbirch.model.Logic;
import se.kaiserbirch.model.Mark;

import static se.kaiserbirch.model.Mark.*;

public class MinMax {
    static final int MAX_DEPTH = 6;
    public static int[] getBestMove(Board board){
        int[] bestMove = new int[]{-1,-1};

        if( board.isCrossTurn()) {
            int largestValue = Integer.MIN_VALUE;
           for (int row = 0; row < board.getWidth(); row++) {
               for (int column = 0; column < board.getWidth(); column++) {
                   if (!board.isMarkedTile(row, column)) {
                       board.setMarkAt(row, column, CROSS);
                       int moveValue = minMax(board, MAX_DEPTH, true);
                       board.setMarkAt(row, column, BLANK);
                       if (moveValue > largestValue) {
                           bestMove[0] = row;
                           bestMove[1] = column;
                           largestValue= moveValue;
                       }
                   }
               }
           }
        }
       else {
           int smallestValue = Integer.MAX_VALUE;
           for (int row = 0; row < board.getWidth(); row++) {
               for (int column = 0; column < board.getWidth(); column++) {
                   if (!board.isMarkedTile(row, column)) {
                       board.setMarkAt(row, column, CIRCLE);
                       int moveValue = minMax(board, MAX_DEPTH, false);
                       board.setMarkAt(row, column, BLANK);
                       if (moveValue < smallestValue) {
                           bestMove[0] = row;
                           bestMove[1] = column;
                           smallestValue= moveValue;
                       }
                   }
               }
           }
        }
        return bestMove;
    }

    private static int evaluateBoard(Board board) {
        Mark winningMark = Logic.checkWin(board);
        if (winningMark == CROSS) {
            return 1;
        } else if (winningMark == CIRCLE) {
            return -1;
        } else {
            return 0;
        }
    }

    private static int minMax(Board board, int depth, boolean maximizingPlayer) {
        int boardVal = evaluateBoard(board);
        if (Math.abs(boardVal) == 1 || depth == 0 || board.anyAvailableMoves()) {
            return boardVal;
        }
        int value;
        if (maximizingPlayer) {
            value = Integer.MIN_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int column = 0; column < board.getWidth(); column++) {
                    if (!board.isMarkedTile(row, column)) {
                        board.setMarkAt(row, column, CROSS);
                        value = Math.max(value, minMax(board, depth - 1, false));
                        board.setMarkAt(row, column, BLANK);
                    }
                }
            }
        } else {
            value = Integer.MAX_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int column = 0; column > board.getWidth(); column++) {
                    if (!board.isMarkedTile(row, column)) {
                        board.setMarkAt(row, column, CIRCLE);
                        value = Math.min(value, minMax(board, depth - 1, true));
                        board.setMarkAt(row, column, BLANK);
                    }
                }
            }
        }
        return value;
    }
}
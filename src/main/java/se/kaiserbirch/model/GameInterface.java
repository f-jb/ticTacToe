package se.kaiserbirch.model;

public interface GameInterface {
    void reset();

    void play(int row, int column);
    Mark[][] getBoard();

    int[] getMove();

    boolean isGameOver();

    Mark getWinningMark();
}

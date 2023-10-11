package se.kaiserbirch.model;

public interface GameInterface {
    void play(int row, int column, State state);
    void reset();
}

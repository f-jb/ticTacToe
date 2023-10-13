package se.kaiserbirch.controller;

public interface ControllerInterface {
    void newGame(int width);
    void play(int row, int column);
    void reset();
}

package se.kaiserbirch;

import se.kaiserbirch.model.Game;
import se.kaiserbirch.model.State;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(3,3);
        game.play(0,0,State.CROSS);
        game.play(1,1,State.CROSS);
        game.play(2,2,State.CROSS);
        game.showBoard();
    }
}
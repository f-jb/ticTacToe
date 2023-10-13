package se.kaiserbirch;

import se.kaiserbirch.model.Game;


public class Main {
    public static void main(String[] args) {
        Game game = new Game(3);

        game.play(0,0);
        game.play(1,1);
        game.play(0,1);
        game.play(0,2);
        game.play(1,2);
        game.play(game.getMove());



    }

}
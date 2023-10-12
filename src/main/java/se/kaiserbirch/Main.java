package se.kaiserbirch;

import se.kaiserbirch.ai.MinMax;
import se.kaiserbirch.model.Board;
import se.kaiserbirch.model.Game;
import se.kaiserbirch.model.Mark;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(3);

        game.play(0,0);
        game.play(1,1);
        game.play(0,1);
        game.play(0,2);
        game.play(1,2);
        game.play(game.getBestMove());
        game.showBoard();



    }

}
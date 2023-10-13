package se.kaiserbirch.model.ai;

import se.kaiserbirch.model.Board;

import java.util.Arrays;

public class AiController implements AiControllerInterface{
    Algorithm currentAlgo = Algorithm.MinMax;
    public int[] getMove(Board board){
        switch (currentAlgo){
            case MinMax -> {
                return MinMax.getBestMove(board);
            }
            default -> throw new IllegalStateException("Unexpected value: " + currentAlgo);
        }
    }

    @Override
    public void setAlgorithm(Algorithm algorithm) {
        this.currentAlgo = algorithm;
    }

    @Override
    public String getAvailableAlgorithms() {
        return Arrays.toString(Algorithm.values());
    }
}

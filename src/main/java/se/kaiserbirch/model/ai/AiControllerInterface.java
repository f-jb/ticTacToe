package se.kaiserbirch.model.ai;

import se.kaiserbirch.model.Board;

public interface AiControllerInterface {
    int[] getMove(Board board);
    void setAlgorithm(Algorithm algorithm);
    String getAvailableAlgorithms();

}

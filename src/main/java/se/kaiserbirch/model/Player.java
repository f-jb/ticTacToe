package se.kaiserbirch.model;

import java.util.ArrayList;

public class Player {
    private final int[] movesOnBoard = new int[]{0, 0, 0};
    ArrayList<int[]> winStates = new ArrayList<>(8);
    Player(){
        winStates.add(new int[]{1,1,1});
        winStates.add(new int[]{2,2,2});
        winStates.add(new int[]{4,4,4});
        winStates.add(new int[]{7,0,0});
        winStates.add(new int[]{0,7,0});
        winStates.add(new int[]{0,0,7});
        winStates.add(new int[]{1,2,4});
        winStates.add(new int[]{4,2,1});
    }
    void play(int[] move){
        for (int i = 0; i<3; i++){
            movesOnBoard[i] += move[i];
        }



    }
    void pruneWinState(int[] opponentsMovesOnBoard){
        if(!winStates.isEmpty()){
            ArrayList<Integer> statesToRemove = new ArrayList<>();
            for ( int[] winState : winStates ) {
                for(int i = 0; i < 3; i++){
                    // checks if the opponent already has taken the square
                   if(opponentsMovesOnBoard[i] == winState[i]) {
                       statesToRemove.add(i);
                       // if opponent has taken a square in the row, remove whole row winState
                   } else if (opponentsMovesOnBoard[i] > 0 && winState[i] == 7){
                       statesToRemove.add(i);
                    }
                }

            }
            for (int stateToRemove:statesToRemove) {
                winStates.remove(stateToRemove);
            }
        }
    }


}


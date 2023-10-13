package se.kaiserbirch.controller;

import se.kaiserbirch.model.Game;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;


public class Controller implements Flow.Publisher<UiState>, ControllerInterface {
    private UiState currentUiState;
    private Game currentGame;
    private final SubmissionPublisher<UiState> submissionPublisher = new SubmissionPublisher<>();
    public Controller(){
    }

    @Override
    public void subscribe(Flow.Subscriber<? super UiState> subscriber) {
        submissionPublisher.subscribe(subscriber);
    }
    @Override
    public void play(int row, int column){
        currentGame.play(row, column);
        updateCurrentUiState();
        submissionPublisher.submit(currentUiState);
    }

    @Override
    public void newGame(int width){
        this.currentGame = new Game(width);
        this.currentUiState = new UiState.Builder()
                .setBoard(currentGame.getBoard())
                .setRecommendedMove(currentGame.getMove())
                .build();
    }

    @Override
    public void reset() {
        currentGame.reset();
    }



    private void updateCurrentUiState(){
        this.currentUiState = new UiState.Builder()
                .setBoard(currentGame.getBoard())
                .setRecommendedMove(currentGame.getMove())
                .build();
    }

    public UiState getUiState() {
        return currentUiState;
    }
}

package se.kaiserbirch.controller;

import se.kaiserbirch.model.Game;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;


public class Controller implements Flow.Publisher<UiState>, ControllerInterface {
    private UiState currentUiState;
    private final Game currentGame;
    private final SubmissionPublisher<UiState> submissionPublisher = new SubmissionPublisher<>();
    Controller(){
        this.currentGame = new Game(3);
        this.currentUiState = new UiState.Builder()
                .setBoard(currentGame.getBoard())
                .setRecommendedMove(currentGame.getMove())
                .build();
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
    public void reset() {
        currentGame.reset();
    }

    private void updateCurrentUiState(){
        this.currentUiState = new UiState.Builder()
                .setBoard(currentGame.getBoard())
                .setRecommendedMove(currentGame.getMove())
                .build();
    }
}

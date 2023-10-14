package se.kaiserbirch.controller;

import se.kaiserbirch.model.Game;
import se.kaiserbirch.model.GameInterface;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;


public class Controller implements Flow.Publisher<UiState>, ControllerInterface {
    private UiState currentUiState;
    private GameInterface currentGame;
    private final SubmissionPublisher<UiState> submissionPublisher = new SubmissionPublisher<>();

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
        submissionPublisher.submit(currentUiState);
    }

    @Override
    public void reset() {
        currentGame.reset();
        updateCurrentUiState();
        submissionPublisher.submit(currentUiState);
    }



    private void updateCurrentUiState(){
        this.currentUiState = new UiState.Builder()
                .setBoard(currentGame.getBoard())
                .setRecommendedMove(currentGame.getMove())
                .setGameOver(currentGame.isGameOver())
                .setWinningMark(currentGame.getWinningMark())
                .build();
    }

    public UiState getUiState() {
        return currentUiState;
    }
}

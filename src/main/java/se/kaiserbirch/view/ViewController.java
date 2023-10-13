package se.kaiserbirch.view;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.controller.UiState;
import se.kaiserbirch.view.views.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class ViewController implements Flow.Subscriber<UiState> {
    private Controller controller;
    JFrame frame = new JFrame("Tic-Tac-Toe");
    private UiState currentUiState;
    private MainView mainView;
    private Flow.Subscription subscription;
    public ViewController(Controller controller){
        this.controller = controller;
        controller.subscribe(this);
        currentUiState = controller.getUiState();
    }
    private void onButtonClick(ActionEvent e){
        int row = ((Integer) ((JButton) e.getSource()).getClientProperty("row"));
        int column = ((Integer) ((JButton) e.getSource()).getClientProperty("column"));
        System.out.println("row is " + row + " column is " + column);
        controller.play(row,column);
    }

    public void init() {
        this.mainView = new MainView.Builder()
                .setPlayAction(this::onButtonClick)
                .setBoard(currentUiState.getBoard())
                .build();

        frame.setLayout(new GridBagLayout());
        frame.setContentPane(mainView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(UiState uiState) {
        this.currentUiState = uiState;
        MainView updatedMainView = new MainView.Builder().setBoard(uiState.getBoard()).setPlayAction(this::onButtonClick).build();
        frame.setContentPane(updatedMainView);
        frame.validate();
        frame.repaint();
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

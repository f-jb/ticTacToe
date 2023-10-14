package se.kaiserbirch.view;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.controller.ControllerInterface;
import se.kaiserbirch.controller.UiState;
import se.kaiserbirch.model.Mark;
import se.kaiserbirch.view.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.Flow;

public class ViewController implements Flow.Subscriber<UiState> {
    private final ControllerInterface controller;
    final JFrame frame = new JFrame("Tic-Tac-Toe");
    private UiState currentUiState;
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
        MainView mainView = new MainView.Builder()
                .setPlayAction(this::onButtonClick)
                .setBoard(currentUiState.getBoard())
                .setRecommendedMove(currentUiState.getRecommendedMove())
                .build();

        JMenuBar jMenuBar = getMenuBar();
        frame.setJMenuBar(jMenuBar);

//        frame.setLayout(new GridBagLayout());
        frame.setContentPane(mainView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

    }

    private JMenuBar getMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu size = new JMenu("Size");
        JMenuItem smallSize = new JMenuItem("Small");
        JMenuItem mediumSize = new JMenuItem("Medium");
        JMenuItem largeSize = new JMenuItem("Large");
        smallSize.addActionListener(e -> controller.newGame(3));
        mediumSize.addActionListener(e -> controller.newGame(4));
        largeSize.addActionListener(e -> controller.newGame(5));
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(e -> controller.reset());
        size.add(smallSize);
        size.add(mediumSize);
        size.add(largeSize);
        jMenuBar.add(restart);
        jMenuBar.add(size);
        return jMenuBar;
    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(UiState uiState) {
        this.currentUiState = uiState;
        MainView updatedMainView = new MainView.Builder()
                .setBoard(uiState.getBoard())
                .setPlayAction(this::onButtonClick)
                .setRecommendedMove(uiState.getRecommendedMove())
                .build();
        frame.setContentPane(updatedMainView);
        frame.validate();
        frame.repaint();
        if(uiState.isGameOver()){
            if(uiState.getWinningMark() == Mark.BLANK) {
                JOptionPane.showMessageDialog(null, "It is a tie.");
            } else {
                JOptionPane.showMessageDialog(null, uiState.getWinningMark() + " has won!");
            }
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

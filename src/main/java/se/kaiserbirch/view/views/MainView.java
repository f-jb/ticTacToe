package se.kaiserbirch.view.views;

import se.kaiserbirch.model.Mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JPanel {

    public MainView(Builder builder){
        Mark[][] board = builder.board;
        setLayout(new GridBagLayout());
        int[] recommendedMove = builder.recommendedMove;
        ActionListener playButtonAction = builder.playButtonAction;
        int gridX = 0;
        int gridY = 0;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10,10,10,10);
        gridBagConstraints.gridx = gridX;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.weightx =1;
        gridBagConstraints.weighty =1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        for (Mark[] row: board) {
            for (Mark square:row) {
                JButton button = new JButton();
                button.setText(square.toString());
                button.setFont(new Font("Arial", Font.BOLD,80));
                if(square.isMarked()){
                    button.setEnabled(false);
                }
                if(recommendedMove[0] == gridY && recommendedMove[1] == gridX){
                    button.setBackground(Color.GREEN);
                }
                button.putClientProperty("row", gridY);
                button.putClientProperty("column", gridX);
                button.addActionListener(playButtonAction);
                add(button,gridBagConstraints);
                gridX++;
                gridBagConstraints.gridx = gridX;
            }
            gridX = 0;
            gridBagConstraints.gridx = gridX;
            gridY++;
            gridBagConstraints.gridy = gridY;
        }
    }

    public static class Builder{
        private int[] recommendedMove;
        private Mark[][] board;
        private ActionListener playButtonAction;
        public Builder setRecommendedMove(int[] recommendedMove){
            this.recommendedMove = recommendedMove;
            return this;
        }
        public Builder setBoard(Mark[][] board){
            this.board = board;
            return this;
        }
        public Builder setPlayAction(ActionListener playButtonAction){
            this.playButtonAction = playButtonAction;
            return this;
        }
        public MainView build(){
            return new MainView(this);
        }
    }
}
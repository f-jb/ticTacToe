package se.kaiserbirch;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.model.Game;
import se.kaiserbirch.view.ViewController;


public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.newGame(3);
        ViewController viewController = new ViewController(controller);
        controller.play(0,0);
        controller.play(1,1);
        controller.play(0,1);

        viewController.init();

        /*
        controller.play(0,2);
        controller.play(1,2);

         */



    }

}
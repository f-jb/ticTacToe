package se.kaiserbirch;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.view.ViewController;


public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.newGame(3);
        ViewController viewController = new ViewController(controller);

        viewController.init();
    }
}
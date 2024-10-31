package commons;

import controller.Controller;
import model.User;

import java.util.Scanner;

public abstract class Screen {
    protected final Controller controller;
    protected final Scanner scanner;
    protected User user;

    public Screen(Scanner scanner){
        this.scanner = scanner;
        this.controller = Controller.getControllerInstance();
    }

    public Screen(Scanner scanner, User user){
        this.scanner = scanner;
        this.user = user;
        this.controller = Controller.getControllerInstance();
    }

    // Used to move to the next screen
    protected void nextScreen(Screen screen){
        screen.runScreenLoop();
    }

    // The respective screen's logic goes here
    public abstract void runScreenLoop();

    // Use it in the beginning
    public static void startScreen(Screen screen){
        screen.runScreenLoop();
    }
}

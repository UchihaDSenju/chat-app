import commons.Screen;
import controller.Controller;
import screens.LoginScreen;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            Screen.startScreen(new LoginScreen(scanner));
        }
    }
}
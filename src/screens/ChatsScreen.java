package screens;

import commons.Screen;

import java.util.Scanner;

public class ChatsScreen extends Screen {
    public ChatsScreen(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void runScreenLoop() {
        System.out.println("Chats will be displayed here");
    }
}

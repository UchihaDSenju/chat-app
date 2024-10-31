package screens;

import commons.Screen;
import model.Message;
import model.User;

import java.util.Scanner;

public class LoginScreen extends Screen {
    public LoginScreen(Scanner scanner){
        super(scanner);
        init();
    }

    @Override
    public void runScreenLoop() {
        System.out.println("Login-Screen");
        System.out.println("Welcome to chat System");
        while(true){
            System.out.print("Enter username: ");
            String uName = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            User user = controller.checkCredentials(uName, password);
            if(user!=null){
                nextScreen(new ChatsScreen(scanner, user));
                return;
            }
        }

    }

    public void init(){
        controller.addUser(new User("Tariq", "123"));
        controller.addUser(new User("Luffy", "123"));
        controller.addUser(new User("Eren", "123"));

//        for(int i = 0; i<10; i++){
            controller.sendMessage(new Message("Hello", "Tariq", "Luffy"));
            controller.sendMessage(new Message("Wassup", "Tariq", "Luffy"));
            controller.sendMessage(new Message("Hey", "Eren", "Luffy"));
            controller.sendMessage(new Message("Hello", "Luffy", "Tariq"));
            controller.sendMessage(new Message("Hello", "Tariq", "Eren"));
//        }
    }
}

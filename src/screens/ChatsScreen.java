package screens;

import commons.Constants;
import commons.Screen;
import model.Message;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ChatsScreen extends Screen {

    HashMap<String, Integer> chats = new LinkedHashMap<>();
    public ChatsScreen(Scanner scanner) {
        super(scanner);
    }

    public ChatsScreen(Scanner scanner, User user){
        super(scanner, user);
    }

    @Override
    public void runScreenLoop() {
        while(true){
            System.out.println("Welcome "+user.getUserName());
            System.out.println("Chats");
            ArrayList<Message> userMessages = controller.fetchMessages(user);
//            System.out.println(userMessages);
            for(Message message : userMessages){
                String chatUser = message.getSender();
                if(message.getStatus().equals(Constants.Status.SENT)){
                    if(chats.get(chatUser) != null){
                        chats.put(chatUser, chats.get(chatUser)+1);
//                        System.out.println(chats);
                    }
                    else{
                        chats.put(chatUser, 1);
                    }
                }
                chats.putIfAbsent(chatUser, 0);
            }
            System.out.println(chats);
            int a = scanner.nextInt();
        }
    }
}

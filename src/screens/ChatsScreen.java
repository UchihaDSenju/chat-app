package screens;

import commons.Constants;
import commons.Screen;
import model.Message;
import model.User;

import java.util.*;

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
        String userName = user.getUserName();
        while(true){
            System.out.println("Welcome "+userName);
            System.out.println("Chats");
            ArrayList<Message> userMessages = controller.fetchMessages(user);
//            System.out.println(userMessages);
            for(Message message : userMessages){
                String chatUser = message.getSender();
                if(message.getStatus().equals(Constants.Status.SENT) && !Objects.equals(message.getSender(), userName)){
                    //                        System.out.println(chats);
                    chats.merge(chatUser, 1, Integer::sum);
                }
                chats.putIfAbsent(chatUser, 0);
            }
            System.out.println(chats);
            int a = scanner.nextInt();
        }
    }
}

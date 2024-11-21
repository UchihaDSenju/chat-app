package screens;

import commons.Constants;
import commons.Screen;
import model.Message;
import model.User;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ChatsScreen extends Screen {

    Map<String, Pair<ArrayList<Message>, Integer>> userVsMessageUnseen = new HashMap<>();

    public ChatsScreen(Scanner scanner) {
        super(scanner);
    }

    public ChatsScreen(Scanner scanner, User user) {
        super(scanner, user);
    }

    @Override
    public void runScreenLoop() {
        String userName = user.getUserName();
        while (true) {
            System.out.println("Welcome " + userName);
            System.out.println("Chats");
            populateInternalChatsBuffer(userName);
            System.out.println(userVsMessageUnseen);
            break;// need to do something to break this
        }
    }

    private void populateInternalChatsBuffer(String userName) {
        ArrayList<Message> userMessages = controller.fetchMessages(user);
        for (Message message : userMessages) {
            String chatUser = message.getSender();
            if (!Objects.equals(message.getSender(), userName)) {
                Pair<ArrayList<Message>, Integer> messageVsUnread = userVsMessageUnseen.computeIfAbsent(chatUser, k -> new MutablePair<>(new ArrayList<>(), 0));
                messageVsUnread.getKey().add(message);
                if (message.getStatus().equals(Constants.Status.SENT)) {
                    Integer currentUnread = messageVsUnread.getValue();
                    messageVsUnread.setValue(currentUnread + 1);
                }
            }
        }
    }
}
package controller;

import Database.Database;
import model.Message;
import model.User;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controller {

    private static final Object LOCK = new Object();

    private final Database database = Database.getDatabaseInstance();
    private static volatile Controller controllerInstance;

    private Controller(){}

    public static Controller getControllerInstance(){
        if(controllerInstance==null){
            synchronized (LOCK){
                if(controllerInstance==null){
                    return controllerInstance = new Controller();
                }
            }
        }
        return controllerInstance;
    }

    public void addUser(User user){
        database.writeUser(user);
    }

    public void sendMessage(Message message){
        database.writeMessage(message);
    }

    public User checkCredentials(String uName, String password){
        var result = database.getUser().stream().filter((u)-> u.getUserName().equals(uName) && u.getPassword().equals(password)).findFirst().orElse(null);
        System.out.println("Result: "+result);//for logging
        return result;
    }

    public ArrayList<Message> fetchMessages(User user){
        ArrayList<Message> messages = new ArrayList<>();
        CopyOnWriteArrayList<Message> messagesFromDatabase = database.getMessages();
        for(Message message : messagesFromDatabase){
            if(message.getReceiver().equals(user.getUserName())){
                messages.add(message);
            }
        }
        return messages;
    }

}

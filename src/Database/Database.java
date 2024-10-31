package Database;

import model.Message;
import model.User;

import java.util.concurrent.CopyOnWriteArrayList;

public class Database {

    private static volatile Database databaseInstance = null;

    private final CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    static final Object LOCK = new Object();

    private Database(){}

    public static Database getDatabaseInstance(){
        if(databaseInstance == null){
            synchronized (LOCK){
                if(databaseInstance == null){
                    return databaseInstance = new Database();
                }
            }
        }
        return databaseInstance;
    }

    //create operations
    public void writeMessage(Message message){
        messages.add(message);
    }

    public void writeUser(User user){
        users.add(user);
    }

    //Read operations
    public CopyOnWriteArrayList<User> getUser(){
        return users;
    }

    public CopyOnWriteArrayList<Message> getMessages(){
        return messages;
    }

}

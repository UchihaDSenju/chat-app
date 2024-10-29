package controller;

import Database.Database;
import model.Message;
import model.User;

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

    public boolean checkCredentials(String uName, String password){
        var result = database.getUser().stream().filter((u)-> u.getUserName().equals(uName) && u.getPassword().equals(password)).findFirst().orElse(null);
        System.out.println("Result: "+result);//for logging
        return result != null;
    }

}

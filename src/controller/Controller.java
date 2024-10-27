package controller;

import Database.Database;

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
}

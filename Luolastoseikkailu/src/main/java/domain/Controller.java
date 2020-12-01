package domain;

import dao.CaventureDao;
import java.util.ArrayDeque;

public class Controller {
    private CaventureDao dao;
    private String loggedInUser;
    
    public Controller() {
        this.dao = new CaventureDao();
        this.loggedInUser = null;
    }
    
    public void createDatabaseIfDoesntExist() {
        this.dao.createDB();
    }
    
    public boolean askIfUserExists(String username) {
        return this.dao.containsUser(username);
    }
    
    public boolean askIfPasswordMatches(String user, String password) {
        return this.dao.passwordMatches(user, password);
    }
    
    public boolean createUser(String username, String password) {
        return this.dao.addUser(username, password);
    }
    
    public void logInUser(String username) {
        this.loggedInUser = username;
    }
    
    public String getLoggedInUser() {
        return this.loggedInUser;
    }
    
    public ArrayDeque<CharacterInfo> getCharacters(String username) {
        return this.dao.getCharacters(username);
    }
}

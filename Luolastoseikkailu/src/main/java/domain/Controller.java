package domain;

import dao.CaventureDao;
import java.util.ArrayList;

public class Controller {
    private CaventureDao dao;
    private String loggedInUser;
    private SessionInfo session;
    
    public Controller() {
        this.dao = new CaventureDao();
        this.loggedInUser = null;
        this.session = null;
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
    
    public ArrayList<CharacterInfo> getCharacters(String username) {
        return this.dao.getCharacters(username);
    }
    
    public boolean addCharacter(String user, String character) {
        return this.dao.addCharacter(user, character);
    }
    
    public void createSession(String character, int xp, int gold) {
        this.session = new SessionInfo(character, xp, gold);
    }
    
    public SessionInfo getCurrentSession() {
        return this.session;
    }
    
    public void eraseSession() {
        this.session = null;
    }
}

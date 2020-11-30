package domain;

import dao.CaventureDao;

public class Controller {
    private CaventureDao dao;
    
    public Controller() {
        this.dao = new CaventureDao();
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
}

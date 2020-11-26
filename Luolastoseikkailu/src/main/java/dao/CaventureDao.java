package dao;

import java.sql.*;
import java.util.ArrayDeque;
import domain.CharacterInfo;

public class CaventureDao {
    
    private String database;
    
    public CaventureDao() {
        this.database = "jdbc:sqlite:database.db";
    }
    
    public void createDB() throws SQLException {
        Connection db = createConnection();
        Statement s = db.createStatement();
        s.execute("PRAGMA foreign_keys = ON");
        s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, username TEXT UNIQUE NOT NULL, password TEXT NOT NULL)");
        s.execute("CREATE TABLE Characters (id INTEGER PRIMARY KEY, user_id INTEGER REFERENCES Users, name TEXT UNIQUE NOT NULL, experience INTEGER DEFAULT 0, gold INTEGER DEFAULT 0)");
        db.close();
    }
    
    public boolean addUser(String user, String pass) throws SQLException {
        if (containsUser(user)) {
            return false;
        }
        
        Connection db = createConnection();
        
        PreparedStatement p = db.prepareStatement("INSERT INTO Users (username, password) VALUES (?,?)");
        p.setString(1, user);
        p.setString(2, pass);
        
        p.execute();
        
        db.close();
        return true;
    }
    
    public boolean containsUser(String user) throws SQLException {
        Connection db = createConnection();
        
        PreparedStatement check = db.prepareStatement("SELECT Users.username FROM Users WHERE Users.username=?");
        check.setString(1, user);
        
        ResultSet r = check.executeQuery();
        
        return r.next();
    }
    
    public boolean passwordMatches(String user, String pass) throws SQLException {
        if (!containsUser(user)) {
            return false;
        }
        
        Connection db = createConnection();
        
        PreparedStatement p = db.prepareStatement("SELECT Users.password FROM Users WHERE Users.username=?");
        p.setString(1, user);
        
        ResultSet r = p.executeQuery();
        
        r.next();
        String resultPassword = r.getString("password");
        
        db.close();
        return pass.equals(resultPassword);
    }
    
    private int getUserId(String user) throws SQLException {
        if (!containsUser(user)) {
            return -1;
        }
        
        Connection db = createConnection();
        
        PreparedStatement p = db.prepareStatement("SELECT Users.id FROM Users WHERE Users.username=?");
        p.setString(1, user);
        
        ResultSet r = p.executeQuery();
        
        return r.getInt("id");
    }
    
    public boolean addCharacter(String user, String name) throws SQLException {
        if (containsCharacter(name)) {
            return false;
        }
        
        int UserId = getUserId(user);
        
        if (UserId == -1) {
            return false;
        }
        
        Connection db = createConnection();
        
        PreparedStatement addingCharacter = db.prepareStatement("INSERT INTO Characters (user_id, name) VALUES (?,?)");
        addingCharacter.setInt(1, UserId);
        addingCharacter.setString(2, name);
        
        addingCharacter.execute();
        
        db.close();
        return true;
    }
    
    public void updateCharacterExperience(String name, int experience) throws SQLException {
        Connection db = createConnection();
        
        PreparedStatement updateCharacter = db.prepareStatement("UPDATE Characters SET Characters.experience=Characters.experience+? WHERE Characters.name=?");
        updateCharacter.setInt(1, experience);
        updateCharacter.setString(1, name);
        
        updateCharacter.executeUpdate();
        
        db.close();
    }
    
    public void updateCharacterGold(String name, int gold) throws SQLException {
        Connection db = createConnection();
        
        PreparedStatement updateCharacter = db.prepareStatement("UPDATE Characters SET Characters.gold=Characters.gold+? WHERE Characters.name=?");
        updateCharacter.setInt(1, gold);
        updateCharacter.setString(1, name);
        
        updateCharacter.executeUpdate();
        
        db.close();
    }
    
    private boolean containsCharacter(String name) throws SQLException {
        Connection db = createConnection();
        
        PreparedStatement p = db.prepareStatement("SELECT Characters.name FROM Characters WHERE Characters.name=?");
        p.setString(1, name);
        
        ResultSet r = p.executeQuery();
        
        db.close();
        return r.next();
    }
    
    public ArrayDeque<CharacterInfo> getCharacters(String user) throws SQLException {
        ArrayDeque<CharacterInfo> characters = new ArrayDeque<>();
        
        Connection db = createConnection();
        
        PreparedStatement getCharacters = db.prepareStatement("SELECT Characters.name, Characters.experience, Characters.gold FROM Users, Characters WHERE Users.id=Characters.user_id AND Users.name=? ORDER BY Characters.name");
        getCharacters.setString(1, user);
        
        ResultSet r = getCharacters.executeQuery();
        
        while (r.next()) {
            String name = r.getString("name");
            int experience = r.getInt("experience");
            int gold = r.getInt("gold");
            
            CharacterInfo character = new CharacterInfo(name, experience, gold);
            
            characters.addLast(character);
        }
        
        db.close();
        return characters;
    }

    private Connection createConnection() throws SQLException {
        Connection db = DriverManager.getConnection(this.database);
        return db;
    }
}

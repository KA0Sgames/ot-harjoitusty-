package dao;

import java.sql.*;
import java.util.ArrayList;
import domain.CharacterInfo;

public class CaventureDao {
    
    private String database;
    
    public CaventureDao() {
        this.database = "jdbc:sqlite:database.db";
    }
    
    public void createDB() {
        Connection db = createConnection();
        
        try {

            Statement s = db.createStatement();
            s.execute("PRAGMA foreign_keys = ON");
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, username TEXT UNIQUE NOT NULL, password TEXT NOT NULL)");
            s.execute("CREATE TABLE Characters (id INTEGER PRIMARY KEY, user_id INTEGER REFERENCES Users, name TEXT UNIQUE NOT NULL, experience INTEGER DEFAULT 0, gold INTEGER DEFAULT 0)");
        
            db.close();
        } catch (SQLException e) {
            return;
        }
    }

    public boolean addUser(String user, String pass) {
        if (containsUser(user)) {
            return false;
        }
        
        Connection db = createConnection();

        try {
            PreparedStatement p = db.prepareStatement("INSERT INTO Users (username, password) VALUES (?,?)");
            p.setString(1, user);
            p.setString(2, pass);
        
            p.execute();
        
            db.close();
        
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean containsUser(String user) {
        Connection db = createConnection();
        
        try {
            PreparedStatement check = db.prepareStatement("SELECT Users.username FROM Users WHERE Users.username=?");
            check.setString(1, user);
        
            ResultSet r = check.executeQuery();
        
            db.close();
            return r.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean passwordMatches(String user, String pass) {
        if (!containsUser(user)) {
            return false;
        }
        
        Connection db = createConnection();
        
        try {
        
            PreparedStatement p = db.prepareStatement("SELECT Users.password FROM Users WHERE Users.username=?");
            p.setString(1, user);
        
            ResultSet r = p.executeQuery();
        
            r.next();
            String resultPassword = r.getString("password");
        
            db.close();
            return pass.equals(resultPassword);
        } catch (SQLException e) {
            return false;
        }
    }
    
    private int getUserId(String user) {
        if (!containsUser(user)) {
            return -1;
        }
        
        Connection db = createConnection();
        
        try {
            PreparedStatement p = db.prepareStatement("SELECT Users.id FROM Users WHERE Users.username=?");
            p.setString(1, user);
        
            ResultSet r = p.executeQuery();
        
            r.next();
            int userId = r.getInt("id");
            
            db.close();
            return userId;
        } catch (SQLException e) {
            return -1;
        }
    }
    
    public boolean addCharacter(String user, String name) {
        if (containsCharacter(name)) {
            return false;
        }
        
        int userId = getUserId(user);
        
        if (userId == -1) {
            return false;
        }
        
        Connection db = createConnection();
        
        try {
            PreparedStatement addingCharacter = db.prepareStatement("INSERT INTO Characters (user_id, name) VALUES (?,?)");
            addingCharacter.setInt(1, userId);
            addingCharacter.setString(2, name);
        
            addingCharacter.execute();
        
            db.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
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
    
    private boolean containsCharacter(String name) {
        Connection db = createConnection();
        
        try {
            PreparedStatement p = db.prepareStatement("SELECT Characters.name FROM Characters WHERE Characters.name=?");
            p.setString(1, name);
        
            ResultSet r = p.executeQuery();
        
            db.close();
            return r.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public ArrayList<CharacterInfo> getCharacters(String user) {
        ArrayList<CharacterInfo> characters = new ArrayList<>();
        
        Connection db = createConnection();
        
        try {
            PreparedStatement getCharacters = db.prepareStatement("SELECT Characters.name, Characters.experience, Characters.gold FROM Users, Characters WHERE Users.id=Characters.user_id AND Users.name=? ORDER BY Characters.name");
            getCharacters.setString(1, user);
        
            ResultSet r = getCharacters.executeQuery();
        
            while (r.next()) {
                String name = r.getString("name");
                int experience = r.getInt("experience");
                int gold = r.getInt("gold");
            
                CharacterInfo character = new CharacterInfo(name, experience, gold);
            
                characters.add(character);
            }
        
            db.close();
        } catch (SQLException e) {
            return characters;
        }
        return characters;
    }

    private Connection createConnection() {
        try {
            Connection db = DriverManager.getConnection(this.database);
            return db;
        } catch (SQLException e) {
            return null;
        }
    }
}

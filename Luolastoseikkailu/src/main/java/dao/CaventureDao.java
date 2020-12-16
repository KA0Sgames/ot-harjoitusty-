package dao;

import java.sql.*;
import java.util.ArrayList;
import domain.CharacterInfo;


/**
 * Class for database interaction.
 */
public class CaventureDao {
    
    private String database;
    
    /**
     * Constructor creating CaventureDao object.
     * @param database Address to used database.
     */
    public CaventureDao(String database) {
        this.database = database;
    }
    
    /**
     * Initializing database, creating tables.
     * Creates tables if there are none in defined database. In case tables already exist, returns doing nothing through exception.
     */
    public void createDB() {
        Connection db = createConnection();
        
        try {

            Statement s = db.createStatement();
            s.execute("BEGIN TRANSACTION");
            s.execute("PRAGMA foreign_keys = ON");
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, username TEXT UNIQUE NOT NULL, password TEXT NOT NULL)");
            s.execute("CREATE TABLE Characters (id INTEGER PRIMARY KEY, user_id INTEGER REFERENCES Users, name TEXT UNIQUE NOT NULL, experience INTEGER DEFAULT 0, gold INTEGER DEFAULT 0)");
            s.execute("COMMIT");
            
            db.close();
        } catch (SQLException e) {
            return;
        }
    }

    /**
     * Adding new user to database.
     * @param user username of the user.
     * @param pass password of the user.
     * @return returns true if adding user succeeds, false otherwise, like in case of the username already being reserved.
     */
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
            System.out.println("Exception in addUser: " + e);
            return false;
        }
        return true;
    }
    
    /**
     * Checking if database already contains the username in question.
     * @param user username which will be checked if database contains it.
     * @return returns true if username is already in database, false otherwise.
     */
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
    
    /**
     * Checking if given password matches given username in database.
     * @param user given username to be checked.
     * @param pass given password to be checked.
     * @return true if password matches the given username, false otherwise.
     */
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
    
    /**
     * Adding new character to database.
     * @param user username of the user adding character.
     * @param name characters name to be added.
     * @return true if adding the character succeeds, false otherwise, like in case character with that name already exists.
     */
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
        
            addingCharacter.executeUpdate();
        
            db.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Updating characters experience field.
     * @param name characters name in database.
     * @param experience amount of experience which will be added to previous amount of experience.
     */
    public void updateCharacterExperience(String name, int experience) {
        Connection db = createConnection();
        
        try {
            PreparedStatement updateCharacter = db.prepareStatement("UPDATE Characters SET experience=experience+? WHERE Characters.name=?");
            updateCharacter.setInt(1, experience);
            updateCharacter.setString(2, name);
        
            updateCharacter.executeUpdate();
            
            db.close();
        } catch (SQLException e) {
            System.out.println("SQLException in updateCharacterExperience: " + e);
        }
    }
    
    /**
     * Updating characters gold field.
     * @param name characters name in database.
     * @param gold amount of gold which will be added to previous amount of gold.
     */
    public void updateCharacterGold(String name, int gold) {
        Connection db = createConnection();
        
        try {
            PreparedStatement updateCharacter = db.prepareStatement("UPDATE Characters SET gold=gold+? WHERE Characters.name=?");
            updateCharacter.setInt(1, gold);
            updateCharacter.setString(2, name);
        
            updateCharacter.executeUpdate();
        
            db.close();
        } catch (SQLException e) {
            
        }
    }
    
    /**
     * Removing of character from database.
     * @param name characters name in database.
     * @return true if removing character with given name succeeds, false otherwise.
     */
    public boolean removeCharacter(String name) {
        if (!containsCharacter(name)) {
            return false;
        }
        
        Connection db = createConnection();
        
        try {
            PreparedStatement charRemoval = db.prepareStatement("DELETE FROM Characters WHERE name=?");
            charRemoval.setString(1, name);
            
            charRemoval.executeUpdate();
            
            db.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
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
    
    /**
     * Retrieving information of all characters of given user.
     * @param user username of the user whose characters are retrieved.
     * @return ArrayList of CharacterInfo objects which each contain information of one character. In case retrieval is unsuccessful, empty list is returned.  
     */
    public ArrayList<CharacterInfo> getCharacters(String user) {
        ArrayList<CharacterInfo> characters = new ArrayList<>();
        
        Connection db = createConnection();
        
        try {
            PreparedStatement getCharacters = db.prepareStatement("SELECT Characters.name, Characters.experience, Characters.gold FROM Users, Characters WHERE Users.id=Characters.user_id AND Users.username=? ORDER BY Characters.name");
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
    
    /**
     * Remove all lines from all the tables in the database.
     */
    public void emptyTables() {
        Connection db = createConnection();
        
        try {
            PreparedStatement emptyCharacters = db.prepareStatement("DELETE FROM Characters");
            emptyCharacters.executeUpdate();
            
            PreparedStatement emptyUsers = db.prepareStatement("DELETE FROM Users");
            emptyUsers.executeUpdate();
            
            db.close();
        } catch (SQLException e) {
            System.out.println("SQLException in CaventureDao emptyTables(): " + e);
        }
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

package dao;

import java.sql.*;

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
    
    private Connection createConnection() throws SQLException {
        Connection db = DriverManager.getConnection(this.database);
        return db;
    }
}

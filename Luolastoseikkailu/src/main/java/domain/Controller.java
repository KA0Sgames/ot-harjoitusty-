package domain;

import dao.CaventureDao;
import java.util.ArrayList;
import java.util.Random;
import domain.creatures.Creature;
import domain.creatures.Spider;
import domain.creatures.Player;
import domain.creatures.CreatureUpdater;

/**
 * Class responsible of connections between classes.
 */

public class Controller {
    private CaventureDao dao;
    private String loggedInUser;
    private SessionInfo session;
    private SpawnPoints spawnPoints;
    private Player player;
    private CreatureUpdater creatureUpdater;
    private Random random;
    
    /**
     * Constructor for creating Controller object.
     * @param database definition of used database as String.
     * @param random Random object to be passed to methods that use random generation.
     */
    public Controller(String database, Random random) {
        this.dao = new CaventureDao(database);
        createDatabaseIfDoesntExist();
        this.loggedInUser = null;
        this.session = null;
        this.spawnPoints = new SpawnPoints();
        this.creatureUpdater = new CreatureUpdater();
        this.random = random;
    }
    
    /**
     * Calling used Dao object to create tables for database if they don't exist yet.
     */
    public void createDatabaseIfDoesntExist() {
        this.dao.createDB();
    }
    
    /**
     * Calling Dao object to check if user with given username exist in the database.
     * @param username username of the given user.
     * @return true if given username is in database, false otherwise.
     */
    public boolean askIfUserExists(String username) {
        return this.dao.containsUser(username);
    }
    
    /**
     * Calling Dao object to check if given password matches given username in the database.
     * @param user given username to be checked.
     * @param password given password to be checked.
     * @return true if password matches given username, false otherwise.
     */
    public boolean askIfPasswordMatches(String user, String password) {
        return this.dao.passwordMatches(user, password);
    }
    
    /**
     * Calling Dao object to remove all lines from all tables in the database.
     */
    public void emptyDatabase() {
        this.dao.emptyTables();
    }
    
    /**
     * Calling Dao object to create new user into the database.
     * @param username given username for the new user.
     * @param password given password for the new user.
     * @return true if adding user was successful, false otherwise.
     */
    public boolean createUser(String username, String password) {
        return this.dao.addUser(username, password);
    }
    
    /**
     * Setter to add given username in String.
     * @param username username of the given user.
     */
    public void logInUser(String username) {
        this.loggedInUser = username;
    }
    
    /**
     * Erasing info of logged user and current session.
     */
    public void logOutUser() {
        this.loggedInUser = null;
        this.session = null;
    }
    
    /**
     * Getter to retrieve currently logged users username in String.
     * @return currently logged users username.
     */
    public String getLoggedInUser() {
        return this.loggedInUser;
    }
    
    /**
     * Calling Dao object to retrieve all characters of the given user as list of CharacterInfo objects.
     * @param username username of the given user.
     * @return ArrayList of CharacterInfo objects containing information of all characters in the database of the given user.
     */
    public ArrayList<CharacterInfo> getCharacters(String username) {
        return this.dao.getCharacters(username);
    }
    
    /**
     * Calling Dao object to add a new character into the database for given user.
     * @param user username of the given user.
     * @param character name of the character to be created.
     * @return true if adding character into the database was successful, false otherwise.
     */
    public boolean addCharacter(String user, String character) {
        return this.dao.addCharacter(user, character);
    }
    
    /**
     * Calling Dao object to remove character of the given name from database.
     * @param name name of the character to be removed.
     * @return true if removal operation was successful, false otherwise, like in case character of given name doesn't exist in database.
     */
    public boolean removeCharacter(String name) {
        return this.dao.removeCharacter(name);
    }
    
    /**
     * Creating session as SessionInfo object which contains information of current game status.
     * @param character name of the character currently in the game.
     * @param xp experience points the character currently in the game has gained.
     * @param gold amount of gold the character currently in the game has collected.
     */
    public void createSession(String character, int xp, int gold) {
        this.session = new SessionInfo(character, xp, gold);
    }
    
    /**
     * Getter to retrieve SessionInfo object which tracks current game information.
     * @return SessionInfo object keeping track of current game session.
     */
    public SessionInfo getCurrentSession() {
        return this.session;
    }
    
    /**
     * Replacing current SessionInfo object with null pointer.
     */
    public void eraseSession() {
        this.session = null;
    }
    
    private ArrayList<Creature> createStartCreatures() {
        ArrayList<Creature> startCreatures = new ArrayList<>();
        int ammount = this.random.nextInt(4) + 1;
        
        for (int i = 0; i < ammount; i++) {
            SpawnPoint spawn = this.spawnPoints.getRandomSpawn(this.random);
            Spider spider = new Spider(spawn.getX(), spawn.getY(), this.random);
            startCreatures.add(spider);
        }
        
        return startCreatures;
    }
    
    /**
     * Initializing starting creatures for the game session and adding them for creatureUpdater.
     * @return ArrayList of Creature objects which will be in the game at the start.
     */
    public ArrayList<Creature> initGame() {
        ArrayList<Creature> startCreatures = createStartCreatures();
        for (Creature creature: startCreatures) {
            this.creatureUpdater.addCreature(creature);
        }
        
        return startCreatures;
    }
    
    /**
     * Initializing object for the players character in the game and adding it for creatureUpdater.
     * @return Player object representing the character in the game.
     */
    public Player initPlayer() {
        this.player = new Player(50, 400, this.random);
        this.creatureUpdater.addPlayer(player);
        return this.player;
    }
    
    /**
     * Calling creatureUpdater to perform each task to be performed within cycle for all creatures.
     */
    public void updateCreatures() {
        this.creatureUpdater.checkTargetDistance();
        this.creatureUpdater.moveAll(this.random);
    }
    
    /**
     * Retrieving CreatureUpdater object which contains all creatures currently in the game.
     * @return CreatureUpdater object containing all creatures currently in the game.
     */
    public CreatureUpdater getCreatureUpdater() {
        return this.creatureUpdater;
    }
}

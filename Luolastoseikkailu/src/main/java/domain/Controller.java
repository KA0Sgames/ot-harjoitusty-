package domain;

import dao.CaventureDao;
import java.util.ArrayList;
import java.util.Random;
import domain.creatures.Creature;
import domain.creatures.Spider;
import domain.creatures.Player;
import domain.creatures.CreatureUpdater;

public class Controller {
    private CaventureDao dao;
    private String loggedInUser;
    private SessionInfo session;
    private int nextCreatureId;
    private SpawnPoints spawnPoints;
    private Player player;
    private CreatureUpdater creatureUpdater;
    
    public Controller() {
        this.dao = new CaventureDao();
        createDatabaseIfDoesntExist();
        this.loggedInUser = null;
        this.session = null;
        this.nextCreatureId = 1;
        this.spawnPoints = new SpawnPoints();
        this.creatureUpdater = new CreatureUpdater();
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
    
    public void logOutUser() {
        this.loggedInUser = null;
        this.session = null;
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
    
    private ArrayList<Creature> createStartCreatures() {
        ArrayList<Creature> startCreatures = new ArrayList<>();
        Random random = new Random();
        int ammount = random.nextInt(4) + 1;
        
        for (int i = 0; i < ammount; i++) {
            SpawnPoint spawn = this.spawnPoints.getRandomSpawn();
            Spider spider = new Spider(spawn.getX(), spawn.getY());
            this.nextCreatureId++;
            startCreatures.add(spider);
        }
        
        return startCreatures;
    }
    
    public ArrayList<Creature> initGame() {
        ArrayList<Creature> startCreatures = createStartCreatures();
        for (Creature creature: startCreatures) {
            this.creatureUpdater.addCreature(creature);
        }
        
        return startCreatures;
    }
    
    public Player initPlayer() {
        this.player = new Player(50, 400);
        creatureUpdater.addPlayer(player);
        return this.player;
    }
    
    public void UpdateCreatures() {
        this.creatureUpdater.checkTargetDistance();
        this.creatureUpdater.moveAll();
    }
}

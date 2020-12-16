package domaintests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Controller;
import domain.SessionInfo;
import domain.CharacterInfo;
import domain.creatures.Player;
import domain.creatures.Spider;
import java.util.ArrayList;
import java.util.Random;

public class ControllerTest {
    
    Controller controller;
    
    public ControllerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.controller = new Controller("jdbc:sqlite:testdatabase.db", new Random(1337));
    }
    
    @Test
    public void createdControllerExists() {
        assertTrue(this.controller!=null);
    }
    
    @Test
    public void loggedInUserNullAtStart() {
        assertTrue(this.controller.getLoggedInUser()==null);
    }
    
    @Test
    public void loggedInUserNotNullAfterLoggingIn() {
        this.controller.logInUser("Jack");
        
        assertTrue(this.controller.getLoggedInUser()!=null);
    }
    
    @Test
    public void loggedInUserCorrect() {
        this.controller.logInUser("Jack");
        
        assertEquals("Jack", this.controller.getLoggedInUser());
    }
    
    @Test
    public void logOutUserWorksCorrectly() {
        this.controller.logInUser("Jack");
        this.controller.createSession("Roger", 500, 100);
        
        assertEquals("Jack", this.controller.getLoggedInUser());
        assertEquals("Roger", this.controller.getCurrentSession().getCharacter());
        
        this.controller.logOutUser();
        
        assertTrue(this.controller.getLoggedInUser()==null);
        assertTrue(this.controller.getCurrentSession()==null);
    }
    
    @Test
    public void askIfUserExistsWorksCorrectlyWithNonexistingUser() {
        assertFalse(this.controller.askIfUserExists("Jack"));
    }
    
    @Test
    public void askIfUserExistsWorksCorrectlyWithExistingUser() {
        this.controller.createUser("Jack", "password");
        
        assertTrue(this.controller.askIfUserExists("Jack"));
    }
    
    @Test
    public void sessionNullAtStart() {
        assertTrue(this.controller.getCurrentSession()==null);
    }
    
    @Test
    public void addedSessionNotNull() {
        this.controller.createSession("Roger", 500, 100);
        
        assertTrue(this.controller.getCurrentSession()!=null);
    }
    
    @Test
    public void addedSessionCorrectly() {
        this.controller.createSession("Roger", 500, 100);
        
        SessionInfo session = this.controller.getCurrentSession();
        
        assertEquals("Roger", session.getCharacter());
        
        assertEquals(500, session.getXP());
        
        assertEquals(100, session.getPrevGold());
    }
    
    @Test
    public void eraseSessionWorksCorrectly() {
        this.controller.createSession("Roger", 500, 100);
        
        assertFalse(this.controller.getCurrentSession()==null);
        
        this.controller.eraseSession();
        
        assertTrue(this.controller.getCurrentSession()==null);
    }
    
    @Test
    public void askIfPasswordMatchesWorksCorrectlyWithNonexistingUser() {
        assertFalse(this.controller.askIfPasswordMatches("Jack", "password"));
    }
    
    @Test
    public void askIfPasswordMatchesWorksCorrectlyWithWrongPassword() {
        this.controller.createUser("Jack", "password");
        
        assertFalse(this.controller.askIfPasswordMatches("Jack", "wrongpassword"));
    }
    
    @Test
    public void askIfPasswordMatchesWorksCorrectlyWithCorrectPassword() {
        this.controller.createUser("Jack", "password");
        
        assertTrue(this.controller.askIfPasswordMatches("Jack", "password"));
    }
    
    @Test
    public void addCharacterAddsCharacterIntoDatabase() {
        this.controller.createUser("Jack", "password");
        this.controller.addCharacter("Jack", "Roger");
        
        ArrayList characters = this.controller.getCharacters("Jack");
        
        assertFalse(characters.isEmpty());
    }
    
    @Test
    public void addCharacterAddsCorrectCharacterIntoDatabase() {
        this.controller.createUser("Jack", "password");
        this.controller.addCharacter("Jack", "Roger");
        
        CharacterInfo character = this.controller.getCharacters("Jack").get(0);
        
        assertEquals("Roger", character.getName());
        assertEquals(0, character.getExperience());
        assertEquals(0, character.getGold());
    }
    
    @Test
    public void initPlayerReturnsCorrectPlayer() {
        Player player = this.controller.initPlayer();
        
        assertEquals(50, player.getX());
    }
    
    @Test
    public void initPlayerAddsPlayerToCreatureUpdater() {
        this.controller.initPlayer();
        
        assertFalse(this.controller.getCreatureUpdater().getPlayer()==null);
        assertEquals(50, this.controller.getCreatureUpdater().getPlayer().getX());
    }
    
    @Test
    public void initGameReturnsListOfStartCreatures() {
        this.controller.initGame();
        
        assertFalse(this.controller.getCreatureUpdater().getCreatures().isEmpty());
    }
    
    @Test
    public void initGameAddsCorrectAmmountOfCreatures() {
        this.controller.initGame();
        
        assertEquals(3, this.controller.getCreatureUpdater().getCreatures().size());
    }
    
    @Test
    public void initGameAddsCorrectCreaturesToCreatureUpdater() {
        this.controller.initGame();
        
        assertEquals("spider", this.controller.getCreatureUpdater().getCreatures().get(1).getName());
    }
    
    @Test
    public void updateCreaturesMovesCreatures() {
        this.controller.initPlayer();
        this.controller.initGame();
        this.controller.updateCreatures();
        
        assertEquals(431, this.controller.getCreatureUpdater().getCreatures().get(0).getX());
    }
    
    @Test
    public void updateCreaturesChecksTargetsCorrectly1() {
        this.controller.initPlayer();
        this.controller.initGame();
        this.controller.updateCreatures();
        
        assertTrue(this.controller.getCreatureUpdater().getCreatures().get(0).getTarget()==null);
    }
    
    @Test
    public void updateCreaturesChecksTargetsCorrectly2() {
        this.controller.initPlayer();
        this.controller.getCreatureUpdater().addCreature(new Spider(60, 450, new Random(1337)));
        
        this.controller.updateCreatures();
        
        assertFalse(this.controller.getCreatureUpdater().getCreatures().get(0).getTarget()==null);
    }
            
    @After
    public void tearDown() {
        this.controller.emptyDatabase();
    }
}

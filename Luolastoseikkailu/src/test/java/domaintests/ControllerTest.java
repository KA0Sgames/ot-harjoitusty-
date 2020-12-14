package domaintests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Controller;
import domain.SessionInfo;

public class ControllerTest {
    
    Controller controller;
    
    public ControllerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.controller = new Controller("jdbc:sqlite:testdatabase.db");
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
    
    @After
    public void tearDown() {
        this.controller.emptyDatabase();
    }
}

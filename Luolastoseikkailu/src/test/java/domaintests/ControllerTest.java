package domaintests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Controller;

public class ControllerTest {
    
    Controller controller;
    
    public ControllerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.controller = new Controller();
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
    public void sessionNullAtStart() {
        assertTrue(this.controller.getCurrentSession()==null);
    }
    
    @Test
    public void loggedInUserNotNullAfterLoggingIn() {
        this.controller.logInUser("Jaska");
        
        assertTrue(this.controller.getLoggedInUser()!=null);
    }
    
    @Test
    public void loggedInUserCorrect() {
        this.controller.logInUser("Jaska");
        
        assertEquals("Jaska", this.controller.getLoggedInUser());
    }
    
    @Test
    public void addedSessionNotNull() {
        this.controller.createSession("Jaska", 500, 100);
        
        assertTrue(this.controller.getCurrentSession()!=null);
    }
}

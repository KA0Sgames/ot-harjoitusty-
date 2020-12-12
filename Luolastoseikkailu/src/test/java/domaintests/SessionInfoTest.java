package domaintests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.SessionInfo;

public class SessionInfoTest {
    
    SessionInfo session;
    
    public SessionInfoTest() {
        
    }
    
    @Before
    public void setUp() {
        this.session = new SessionInfo("Jaska", 500, 100);
    }
    
    @Test
    public void createdSessionExists() {
        assertTrue(this.session!=null);
    }
    
    @Test
    public void sessionNameCorrectly() {
        assertEquals("Jaska", this.session.getCharacter());
    }
    
    @Test
    public void xpCorrectAtStart() {
        assertEquals(500, this.session.getXP());
    }
    
    @Test
    public void prevGoldCorrectAtStart() {
        assertEquals(100, this.session.getPrevGold());
    }
    
    @Test
    public void collectedGoldCorrectlyAtStart() {
        assertEquals(0, this.session.getCollectedGold());
    }
    
    @Test
    public void addingXpWorksCorrectly() {
        this.session.addXP(100);
        
        assertEquals(600, this.session.getXP());
    }
    
    @Test
    public void collectingGoldWorksCorrectly() {
        this.session.addGold(50);
        
        assertEquals(50, this.session.getCollectedGold());
    }
}

package daotests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import dao.CaventureDao;

public class CaventureDaoTest {
    CaventureDao dao;
    
    public CaventureDaoTest() {
        
    }
    
    @Before
    public void setUp() {
        this.dao = new CaventureDao("jdbc:sqlite::memory:");
        this.dao.createDB();
    }
    
    @Test
    public void createdCaventureDaoExists() {
        assertTrue(this.dao!=null);
    }
    
    @Test
    public void userThatHasntBeenAddedDoesntExist() {
        assertEquals(false, this.dao.containsUser("Jaska"));
    }
    
    @Test
    public void addingUserCorrectlyPlacesItIntoDatabase() {
        this.dao.addUser("Jaska", "password");
        
        assertEquals(true, this.dao.containsUser("Jaska"));
    }
    
    @Test
    public void passwordMatchesWorksCorrectlyWithNonExistingUser() {
        assertEquals(false, this.dao.passwordMatches("Teppo", "password"));
    }
    
    @Test
    public void passwordMatchesWorksCorrectlyWithWrongPassword() {
        assertEquals(false, this.dao.passwordMatches("Jaska", "wrongPassword"));
    }
    
    @Test
    public void passwordMatchesWorksCorrectlyWithRightPassword() {
        assertEquals(true, this.dao.passwordMatches("Jaska", "password"));
    }
}

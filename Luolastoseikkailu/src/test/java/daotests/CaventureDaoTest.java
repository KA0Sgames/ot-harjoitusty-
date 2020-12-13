package daotests;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import dao.CaventureDao;
import domain.CharacterInfo;

public class CaventureDaoTest {
    CaventureDao dao;
    
    public CaventureDaoTest() {
        
    }
    
    @Before
    public void setUp() {
        this.dao = new CaventureDao("jdbc:sqlite:testdatabase.db");
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
        this.dao.addUser("Jack", "password");
        
        assertEquals(true, this.dao.containsUser("Jack"));
    }
    
    @Test
    public void passwordMatchesWorksCorrectlyWithNonExistingUser() {
        assertEquals(false, this.dao.passwordMatches("Peter", "password"));
    }
    
    @Test
    public void passwordMatchesWorksCorrectlyWithWrongPassword() {
        this.dao.addUser("Jack", "password");
        
        assertEquals(false, this.dao.passwordMatches("Jack", "wrongPassword"));
    }
    
    @Test
    public void passwordMatchesWorksCorrectlyWithRightPassword() {
        this.dao.addUser("Jack", "password");
        
        assertEquals(true, this.dao.passwordMatches("Jack", "password"));
    }
    
    @Test
    public void addCharacterAddsCharacterIntoDatabase() {
        this.dao.addUser("Jack", "password");
        this.dao.addCharacter("Jack", "Roger");
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertFalse(characters.isEmpty());
    }
    
    @Test
    public void addingCharacterAddsCharacterCorrectly() {
        this.dao.addUser("Jack", "password");
        this.dao.addCharacter("Jack", "Roger");
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertEquals("Roger", characters.get(0).getName());
    }
    
    @Test
    public void addingCharacterExperienceWorksCorrectly() {
        this.dao.addUser("Jack", "password");
        this.dao.addCharacter("Jack", "Roger");
        
        this.dao.updateCharacterExperience("Roger", 100);
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertEquals(100, characters.get(0).getExperience());
    }
    
    @Test
    public void addingCharacterGoldWorksCorrectly() {
        this.dao.addUser("Jack", "password");
        this.dao.addCharacter("Jack", "Roger");
        
        this.dao.updateCharacterGold("Roger", 150);
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertEquals(150, characters.get(0).getGold());
    }
    
    @Test
    public void removingCharacterWorksCorrectly() {
        this.dao.addUser("Jack", "password");
        this.dao.addCharacter("Jack", "Roger");
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertFalse(characters.isEmpty());
        
        this.dao.removeCharacter("Roger");
        
        characters = this.dao.getCharacters("Jack");
        
        assertTrue(characters.isEmpty());
    }
    
    @Test
    public void getCharactersReturnsCorrectAmmountOfCharactersWithZeroCharacters() {
        this.dao.addUser("Jack", "password");
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertTrue(characters.isEmpty());
    }
    
    @Test
    public void getCharactersReturnsCorrectAmmountOfCharactersWithTwoCharacters() {
        this.dao.addUser("Jack", "password");
        this.dao.addCharacter("Jack", "Roger");
        this.dao.addCharacter("Jack", "Rabbit");
        
        ArrayList<CharacterInfo> characters = this.dao.getCharacters("Jack");
        
        assertEquals(2, characters.size());
    }
    
    @After
    public void tearDown() {
        this.dao.emptyTables();
    }
}

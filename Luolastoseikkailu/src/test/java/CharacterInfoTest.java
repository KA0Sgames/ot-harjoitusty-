import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.CharacterInfo;

public class CharacterInfoTest {
    CharacterInfo info;
    
    public CharacterInfoTest() {
        
    }
    
    @Before
    public void setUp() {
        this.info = new CharacterInfo("Jaska", 500, 100);
    }
    
    
    @Test
    public void createdCharacterInfoExists() {
        assertTrue(this.info!=null);
    }
    
    @Test
    public void characterHasCorrectName() {
        assertEquals("Jaska", this.info.getName());
    }
    
    @Test
    public void characterHasCorrectExperience() {
        assertEquals(500, this.info.getExperience());
    }
    
    @Test
    public void characterHasCorrectGold() {
        assertEquals(100, this.info.getGold());
    }
}

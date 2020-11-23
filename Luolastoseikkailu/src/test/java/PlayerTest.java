import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Player;

public class PlayerTest {
    
    Player character;
    
    public PlayerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.character = new Player(10, 100);
    }
    
    @Test
    public void createdCharacterExists() {
        assertTrue(character!=null);
    }
    
    @Test
    public void xCoordinateCorrectlyAtStart() {
        assertEquals(10.0, this.character.getCharacter().getTranslateX(), 0.01);
    }
    
    @Test
    public void yCoordinateCorrectlyAtStart() {
        assertEquals(100.0, this.character.getCharacter().getTranslateY(), 0.01);
    }
    
    @Test
    public void moveLeftWorksCorrectly() {
        this.character.moveLeft();
        
        assertEquals(9.0, this.character.getCharacter().getTranslateX(), 0.01);
    }
    
    @Test
    public void moreRightWorksCorrectly() {
        this.character.moveRight();
        
        assertEquals(11.0, this.character.getCharacter().getTranslateX(), 0.01);
    }
    
    @Test
    public void moveUpWorksCorrectly() {
        this.character.moveUp();
        
        assertEquals(99.0, this.character.getCharacter().getTranslateY(), 0.01);
    }
    
    @Test
    public void moveDownWorksCorrectly() {
        this.character.moveDown();
        
        assertEquals(101.0, this.character.getCharacter().getTranslateY(), 0.01);
    }
}

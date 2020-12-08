package domainTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.creatures.Player;

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
        assertEquals(10, this.character.getX());
    }
    
    @Test
    public void yCoordinateCorrectlyAtStart() {
        assertEquals(100, this.character.getY());
    }
    
    @Test
    public void moveLeftWorksCorrectly() {
        this.character.moveLeft();
        
        assertEquals(9, this.character.getX());
    }
    
    @Test
    public void moreRightWorksCorrectly() {
        this.character.moveRight();
        
        assertEquals(11, this.character.getX());
    }
    
    @Test
    public void moveUpWorksCorrectly() {
        this.character.moveUp();
        
        assertEquals(99, this.character.getY());
    }
    
    @Test
    public void moveDownWorksCorrectly() {
        this.character.moveDown();
        
        assertEquals(101, this.character.getY());
    }
}

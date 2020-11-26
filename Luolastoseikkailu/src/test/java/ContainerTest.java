import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Container;
import domain.CharacterInfo;

public class ContainerTest {
    private Container container;
    private CharacterInfo character;
    
    public ContainerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.container = new Container();
        this.character = new CharacterInfo("Jaska", 500, 100);
    }
    
    @Test
    public void containerExists() {
        assertTrue(this.container!=null);
    }
    
    @Test
    public void containerHasArrayList() {
        assertTrue(this.container.getCharacters()!=null);
    }
    
    @Test
    public void newContainerIsEmpty() {
        assertTrue(this.container.getCharacters().isEmpty());
    }
    
    @Test
    public void containerWithAddedCharacterIsNotEmpty() {
        this.container.addCharacter(this.character);
        
        assertEquals(1, this.container.getCharacters().size());
    }

}

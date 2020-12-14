package domaintests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.SpawnPoint;

public class SpawnPointTest {
    SpawnPoint spawnpoint;
    
    @Before
    public void setUp() {
        this.spawnpoint = new SpawnPoint(10, 25);
    }
    
    @Test
    public void createdSpawnPointExists() {
        assertTrue(spawnpoint!=null);
    }
    
    @Test
    public void getXWorksCorrectly() {
        assertEquals(10, this.spawnpoint.getX());
    }
    
    @Test
    public void getYWorksCorrectly() {
        assertEquals(25, this.spawnpoint.getY());
    }
}

package domaintests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import domain.SpawnPoints;
import domain.SpawnPoint;
    

public class SpawnPointsTest {
    SpawnPoints spawnpoints;
    Random random;
    
    @Before
    public void setUp() {
        this.spawnpoints = new SpawnPoints();
        this.random = new Random(1337);
    }
    
    @Test
    public void createdSpawnPointsExists() {
        assertTrue(spawnpoints!=null);
    }
    
    @Test
    public void getRandomSpawnWorksCorrectly1() {
        SpawnPoint point = this.spawnpoints.getRandomSpawn(random);
        
        assertEquals(900, point.getX());
    }
    
    @Test
    public void getRandomSpawnWorksCorrectly2() {
        SpawnPoint point = this.spawnpoints.getRandomSpawn(random);
        
        assertEquals(380, point.getY());
    }
    
    @Test
    public void getRandomSpawnWorksCorrectly3() {
        SpawnPoint point = this.spawnpoints.getRandomSpawn(new Random(500));
        
        assertEquals(370, point.getX());
    }
}

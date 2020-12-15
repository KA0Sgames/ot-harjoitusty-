package domaintests.creaturetests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.creatures.CreatureUpdater;
import domain.creatures.Player;
import domain.creatures.Spider;
import java.util.Random;

public class CreatureUpdaterTest {
    CreatureUpdater updater;
    Player player;
    Spider spider;
    
    public CreatureUpdaterTest() {
        
    }
    
    @Before
    public void setUp() {
        this.updater = new CreatureUpdater();
        this.player = new Player(10, 25, new Random(1337));
        this.spider = new Spider(30, 50, new Random(1337));
    }
    
    @Test
    public void createdCreatureUpdaterExists() {
        assertTrue(this.updater!=null);
    }
    
    @Test
    public void createdCreatureUpdaterHasNullAsPlayerAtStart() {
        assertTrue(this.updater.getPlayer()==null);
    }
    
    @Test
    public void creatureUpdaterHasCreaturelistAtStart() {
        assertFalse(this.updater.getCreatures()==null);
    }
    
    @Test
    public void creaturelistIsEmptyAtStart() {
        assertTrue(this.updater.getCreatures().isEmpty());
    }
    
    @Test
    public void addPlayerAddsPlayer() {
        this.updater.addPlayer(this.player);
        
        assertFalse(this.updater.getPlayer()==null);
    }
    
    @Test
    public void addPlayerAddsCorrectPlayer() {
        this.updater.addPlayer(this.player);
        
        assertEquals(10, this.updater.getPlayer().getX());
    }
    
    @Test
    public void addCreatureAddsCreature() {
        this.updater.addCreature(this.spider);
        
        assertFalse(this.updater.getCreatures().isEmpty());
    }
    
    @Test
    public void addCreatureAddsCorrectCreature() {
        this.updater.addCreature(this.spider);
        
        assertEquals(50, this.updater.getCreatures().get(0).getY());
    }
    
    @Test
    public void moveAllWorksCorrectly1() {
        this.updater.addCreature(this.spider);
        
        this.updater.moveAll(new Random(1337));
        
        assertEquals(29, this.updater.getCreatures().get(0).getX());
        assertEquals(49, this.updater.getCreatures().get(0).getY());
    }
    
    @Test
    public void moveAllWorksCorrectly2() {
        this.updater.addCreature(this.player);
        this.updater.addCreature(this.spider);
        
        this.updater.moveAll(new Random(1337));
        
        assertEquals(9, this.updater.getCreatures().get(0).getX());
        assertEquals(24, this.updater.getCreatures().get(0).getY());
        assertEquals(29, this.updater.getCreatures().get(1).getX());
        assertEquals(49, this.updater.getCreatures().get(1).getY());
    }
    
    @Test
    public void checkTargetDistanceWorksCorrectly1() {
        this.updater.addPlayer(this.player);
        this.updater.addCreature(this.spider);
        
        this.updater.checkTargetDistance();
        
        assertFalse(this.updater.getCreatures().get(0).getTarget()==null);
        assertEquals(this.player, this.updater.getCreatures().get(0).getTarget());
    }
    
    public void checkTargetDistanceWorksCorrectly2() {
        this.updater.addPlayer(this.player);
        this.updater.addCreature(new Spider(100, 75, new Random(1337)));
        
        this.updater.checkTargetDistance();
        
        assertTrue(this.updater.getCreatures().get(0).getTarget()==null);
    }
}

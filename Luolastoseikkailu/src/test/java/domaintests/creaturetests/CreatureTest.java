package domaintests.creaturetests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.creatures.Creature;
import domain.creatures.Spider;
import domain.creatures.Direction;
import java.util.Random;

public class CreatureTest {
    Creature creature;
    Random random;
    
    public CreatureTest() {
        
    }
    
    @Before
    public void setUp() {
        this.random = new Random(1337);
        this.creature = new Spider(50, 60, this.random);
    }
    
    @Test
    public void createdCreatureExists() {
        assertTrue(this.creature!=null);
    }
    
    @Test
    public void getMaxHPWorksCorrectly() {
        assertEquals(7, this.creature.getMaxHP());
    }
    
    @Test
    public void getHPWorksCorrectly() {
        assertEquals(7, this.creature.getHP());
    }
    
    @Test
    public void setHPDoesntSetHPOverMaxHP() {
        this.creature.setHP(5);
        
        assertEquals(7, this.creature.getHP());
    }
    
    @Test
    public void setHPCanLowerHP() {
        this.creature.setHP(-5);
        
        assertEquals(2, this.creature.getHP());
    }
    
    @Test
    public void setHPRaisesHPCorrectly1() {
        this.creature.setHP(-5);
        this.creature.setHP(3);
        
        assertEquals(5, this.creature.getHP());
    }
    
    @Test
    public void setHPRaisesHPCorrectly2() {
        this.creature.setHP(-2);
        this.creature.setHP(5);
        
        assertEquals(7, this.creature.getHP());
    }
    
    @Test
    public void moveMovesCreature() {
        this.creature.move(this.random);
        
        assertEquals(49, this.creature.getX());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection1() {
        this.creature.setDirection(Direction.UP);
        this.creature.move(this.random);
        
        assertEquals(50, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection2() {
        this.creature.setDirection(Direction.DOWN);
        this.creature.move(this.random);
        
        assertEquals(50, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection3() {
        this.creature.setDirection(Direction.RIGHT);
        this.creature.move(this.random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(60, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection4() {
        this.creature.setDirection(Direction.LEFT);
        this.creature.move(this.random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(60, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection5() {
        this.creature.setDirection(Direction.UPRIGHT);
        this.creature.move(this.random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection6() {
        this.creature.setDirection(Direction.UPLEFT);
        this.creature.move(this.random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection7() {
        this.creature.setDirection(Direction.DOWNRIGHT);
        this.creature.move(this.random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void moveMovesCreatureIntoRightDirection8() {
        this.creature.setDirection(Direction.DOWNLEFT);
        this.creature.move(this.random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void changeDirectionWorks() {
        assertEquals(Direction.UPLEFT, this.creature.getDirection());
        
        for(int i = 0; i < 150; i++) {
            this.creature.move(this.random);
        }
        
        assertEquals(Direction.DOWN, this.creature.getDirection());
    }
    
    @Test
    public void moveTowardsTargetWorks1() {
        this.creature.setTarget(new Spider(60, 58, this.random));
        this.creature.move(random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(60, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks2() {
        this.creature.setTarget(new Spider(60, 85, this.random));
        this.creature.move(random);
        
        assertEquals(50, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks3() {
        this.creature.setTarget(new Spider(60, 35, this.random));
        this.creature.move(random);
        
        assertEquals(50, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks4() {
        this.creature.setTarget(new Spider(60, 70, this.random));
        this.creature.move(random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks5() {
        this.creature.setTarget(new Spider(60, 50, this.random));
        this.creature.move(random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks6() {
        this.creature.setTarget(new Spider(30, 55, this.random));
        this.creature.move(random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(60, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks7() {
        this.creature.setTarget(new Spider(40, 85, this.random));
        this.creature.move(random);
        
        assertEquals(50, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks8() {
        this.creature.setTarget(new Spider(40, 35, this.random));
        this.creature.move(random);
        
        assertEquals(50, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks9() {
        this.creature.setTarget(new Spider(40, 70, this.random));
        this.creature.move(random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void moveTowardsTargetWorks10() {
        this.creature.setTarget(new Spider(40, 50, this.random));
        this.creature.move(random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders1() {
        this.creature.setX(5);
        this.creature.setDirection(Direction.LEFT);
        this.creature.move(random);
        
        assertEquals(5, this.creature.getX());
    }
    
    @Test
    public void creaturesDontMoveOverBorders2() {
        this.creature.setX(1195);
        this.creature.setDirection(Direction.RIGHT);
        this.creature.move(random);
        
        assertEquals(1195, this.creature.getX());
    }
    
    @Test
    public void creaturesDontMoveOverBorders3() {
        this.creature.setY(5);
        this.creature.setDirection(Direction.UP);
        this.creature.move(random);
        
        assertEquals(5, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders4() {
        this.creature.setY(795);
        this.creature.setDirection(Direction.DOWN);
        this.creature.move(random);
        
        assertEquals(795, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders5() {
        this.creature.setX(5);
        this.creature.setDirection(Direction.UPLEFT);
        this.creature.move(random);
        
        assertEquals(5, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders6() {
        this.creature.setY(5);
        this.creature.setDirection(Direction.UPLEFT);
        this.creature.move(random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(5, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders7() {
        this.creature.setX(5);
        this.creature.setY(5);
        this.creature.setDirection(Direction.UPLEFT);
        
        assertEquals(5, this.creature.getX());
        assertEquals(5, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders8() {
        this.creature.setX(1195);
        this.creature.setDirection(Direction.UPRIGHT);
        this.creature.move(random);
        
        assertEquals(1195, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders9() {
        this.creature.setY(5);
        this.creature.setDirection(Direction.UPRIGHT);
        this.creature.move(random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(5, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders10() {
        this.creature.setX(1195);
        this.creature.setY(5);
        this.creature.setDirection(Direction.UPRIGHT);
        
        assertEquals(1195, this.creature.getX());
        assertEquals(5, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders11() {
        this.creature.setX(5);
        this.creature.setDirection(Direction.DOWNLEFT);
        this.creature.move(random);
        
        assertEquals(5, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders12() {
        this.creature.setY(795);
        this.creature.setDirection(Direction.DOWNLEFT);
        this.creature.move(random);
        
        assertEquals(49, this.creature.getX());
        assertEquals(795, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders13() {
        this.creature.setX(5);
        this.creature.setY(795);
        this.creature.setDirection(Direction.DOWNLEFT);
        
        assertEquals(5, this.creature.getX());
        assertEquals(795, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders14() {
        this.creature.setX(1195);
        this.creature.setDirection(Direction.DOWNRIGHT);
        this.creature.move(random);
        
        assertEquals(1195, this.creature.getX());
        assertEquals(61, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders15() {
        this.creature.setY(795);
        this.creature.setDirection(Direction.DOWNRIGHT);
        this.creature.move(random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(795, this.creature.getY());
    }
    
    @Test
    public void creaturesDontMoveOverBorders16() {
        this.creature.setX(1195);
        this.creature.setY(795);
        this.creature.setDirection(Direction.DOWNRIGHT);
        
        assertEquals(1195, this.creature.getX());
        assertEquals(795, this.creature.getY());
    }
    
    @Test
    public void creaturesMoveCorrectlyWithoutTarget() {
        this.creature.setDirection(Direction.UPRIGHT);
        this.creature.move(random);
        
        assertEquals(51, this.creature.getX());
        assertEquals(59, this.creature.getY());
    }
}

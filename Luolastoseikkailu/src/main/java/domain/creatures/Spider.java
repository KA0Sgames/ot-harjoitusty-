package domain.creatures;

import java.util.Random;

/**
 * Class defines Spider creature.
 */

public class Spider extends Creature {
    
    /**
     * Constructor for creating spider creature which calls superclass Creature in setting default values of Spider.
     * @param x x-coordinate of this objects starting point in the game. 
     * @param y y-coordinate of this objects starting point in the game.
     * @param random Random object not actually used in this class, but which superclass needs for generating first moving direction.
     */
    public Spider(int x, int y, Random random) {
        super("spider", x, y, 7, random);
    }
}

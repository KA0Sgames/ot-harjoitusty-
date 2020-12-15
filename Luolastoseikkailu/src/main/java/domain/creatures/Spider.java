package domain.creatures;

import java.util.Random;

/**
 * Class defines Spider creature
 */

public class Spider extends Creature {
    public Spider(int x, int y, Random random) {
        super("spider", x, y, 7, random);
    }
}

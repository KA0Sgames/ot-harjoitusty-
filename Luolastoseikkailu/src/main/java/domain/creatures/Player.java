package domain.creatures;

import java.util.Random;

/**
 * Class depicting players character in the game.
 */

public class Player extends Creature {
    
    /**
     * Constructor for creating object depicting players character.
     * @param x x-coordinate of players starting location in the game.
     * @param y y-coordinate of players starting location in the game.
     * @param random Random object not actually used in this class, but which superclass needs.
     */
    public Player(int x, int y, Random random) {
        super("Player", x, y, 17, random);
    }
    
    /**
     * Moving players character one step towards the left side if character isn't right by the side.
     * In this case right by the side is five pixels from the side.
     */
    public void moveLeft() {
        if (this.getX() > 5) {
            this.setX(this.getX() - 1);
        }
    }
    
    /**
     * Moving players character one step towards the right side if character isn't right by the side.
     * In this case right by the side is five pixels from the side.
     */
    public void moveRight() {
        if (this.getX() < 1195) {
            this.setX(this.getX() + 1);
        }
    }
    
    /**
     * Moving players character one step towards the top side if character isn't right by the side.
     * In this case right by the side is five pixels from the side.
     */
    public void moveUp() {
        if (this.getY() > 5) {
            this.setY(this.getY() - 1);
        }
    }
    
    /**
     * Moving players character one step towards the bottom side if character isn't right by the side.
     * In this case right by the side is five pixels from the side.
     */
    public void moveDown() {
        if (this.getY() < 795) {
            this.setY(this.getY() + 1);
        }
    }
}

package domain;

/**
 * Class representing single spawn point.
 * That is pair of coordinates at which point creatures may enter into the game.
 */
public class SpawnPoint {
    private final int x;
    private final int y;
    
    /**
     * Constructor for creating SpawnPoint object.
     * @param x x-coordinate of this spawn point.
     * @param y y-coordinate of this spawn point.
     */
    public SpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Getter to retrieve x-coordinate of this spawn point.
     * @return x-coordinate of this spawn point.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Getter to retrieve y-coordinate of this spawn point.
     * @return y-coordinate of this spawn point.
     */
    public int getY() {
        return this.y;
    }
}

package domain;

public class SpawnPoint {
    private final int x;
    private final int y;
    
    public SpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}

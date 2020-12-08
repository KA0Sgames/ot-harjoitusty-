package domain.creatures;

public class Player extends Creature {
    
    public Player(int x, int y) {
        super("Player", x, y, 17);
        
        // this.movement = new Point2D(0, 0);
    }
    
    public void moveLeft() {
        this.setX(this.getX() - 1);
    }
    
    public void moveRight() {
        this.setX(this.getX() + 1);
    }
    
    public void moveUp() {
        this.setY(this.getY() - 1);
    }
    
    public void moveDown() {
        this.setY(this.getY() + 1);
    }
}

package domain.creatures;

import javafx.scene.shape.Polygon;

public class Player {
    private Polygon character;
    // private Point2D movement;
    
    public Player(int x, int y) {
        this.character = new Polygon(-10, 0, 0, -10, 10, 0, 5, 10, -5, 10);
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        // this.movement = new Point2D(0, 0);
    }
    
    public void moveLeft() {
        this.character.setTranslateX(this.character.getTranslateX() - 1);
    }
    
    public void moveRight() {
        this.character.setTranslateX(this.character.getTranslateX() + 1);
    }
    
    public void moveUp() {
        this.character.setTranslateY(this.character.getTranslateY() - 1);
    }
    
    public void moveDown() {
        this.character.setTranslateY(this.character.getTranslateY() + 1);
    }
    
    public Polygon getCharacter() {
        return character;
    }
    
    // public void move() {
    //    this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
    //    this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
    //}
}

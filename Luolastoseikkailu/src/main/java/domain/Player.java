package domain;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Player {
    private Polygon character;
    private Point2D movement;
    
    public Player(int x, int y) {
        this.character = new Polygon(-5, 0, 0, 5, 5, 0, 2, -5, -2, -5);
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.movement = new Point2D(0, 0);
    }
    
    public void moveLeft() {
        this.character.setTranslateX(this.character.getTranslateX() - 1);
    }
    
    public void moveRight() {
        this.character.setTranslateX(this.character.getTranslateX() + 1);
    }
    
    public Polygon getCharacter() {
        return character;
    }
    
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
    }
}

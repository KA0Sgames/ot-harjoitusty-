package domain.creatures;

import java.util.Random;

public abstract class Creature {
    private final String name;
    private int x;
    private int y;
    private final int maxHP;
    private int HP;
    private Direction direction;
    private Creature target;
    private int directionCounter;
    
    public Creature(String name, int x, int y, int maxHP) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.target = null;
        changeDirection();
        this.directionCounter = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getMaxHP() {
        return this.maxHP;
    }
    
    public int getHP() {
        return this.HP;
    }
    
    public void setHP(int change) {
        this.HP += change;
    }
    
    public void move() {
        if (target == null) {
            switch (this.direction) {
                case UP:
                    this.y -= 1;
                    break;
                case DOWN:
                    this.y += 1;
                    break;
                case RIGHT:
                    this.x +=1;
                    break;
                case LEFT:
                    this.x -= 1;
                    break;
                case UPRIGHT:
                    this.x += 1;
                    this.y -= 1;
                    break;
                case UPLEFT:
                    this.x -= 1;
                    this.y -= 1;
                    break;
                case DOWNRIGHT:
                    this.x += 1;
                    this.y += 1;
                    break;
                case DOWNLEFT:
                    this.x -= 1;
                    this.y += 1;
                    break;
            }
            
            this.directionCounter += 1;
            
            if (this.directionCounter == 10000) {
                changeDirection();
            }
        }
    }
    
    private void changeDirection() {
        Random directionRandomizer = new Random();
        int newDirection = directionRandomizer.nextInt(8);
        
        switch (newDirection) {
            case 0:
                this.direction = Direction.UP;
                break;
            case 1:
                this.direction = Direction.DOWN;
                break;
            case 2:
                this.direction = Direction.RIGHT;
                break;
            case 3:
                this.direction = Direction.LEFT;
                break;
            case 4:
                this.direction = Direction.UPRIGHT;
                break;
            case 5:
                this.direction = Direction.UPLEFT;
                break;
            case 6:
                this.direction = Direction.DOWNRIGHT;
                break;
            case 7:
                this.direction = Direction.DOWNLEFT;
                break;
        }
    }
}

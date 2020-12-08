package domain.creatures;

import java.util.Random;

public abstract class Creature {
    private final int id;
    private final String name;
    private int x;
    private int y;
    private final int maxHP;
    private int HP;
    private Direction direction;
    private Creature target;
    private int directionCounter;
    
    public Creature(int id, String name, int x, int y, int maxHP) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.target = null;
        changeDirection();
        this.directionCounter = 0;
    }
    
    public int getId() {
        return this.id;
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
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
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
                    if (this.y > 5) {
                        this.y -= 1;
                    }
                    break;
                case DOWN:
                    if (this.y < 795) {
                        this.y += 1;
                    }
                    break;
                case RIGHT:
                    if (this.x < 1195) {
                        this.x +=1;
                    }
                    break;
                case LEFT:
                    if (this.x > 5) {
                        this.x -= 1;
                    }
                    break;
                case UPRIGHT:
                    if (this.y > 5 && this.x < 1195) {
                        this.x += 1;
                        this.y -= 1;
                    } else if (this.y > 5) {
                        this.y -= 1;
                    } else if (this.x < 1195) {
                        this.x += 1;
                    }
                    break;
                case UPLEFT:
                    if (this.y > 5 && this.x > 5) {
                        this.x -= 1;
                        this.y -= 1;
                    } else if (this.y > 5) {
                        this.y -= 1;
                    } else if (this.x > 5) {
                        this.x -= 1;
                    }
                    break;
                case DOWNRIGHT:
                    if (this.y < 795 && this.x < 1195) {
                        this.x += 1;
                        this.y += 1;
                    } else if (this.y < 795) {
                        this.y += 1;
                    } else if (this.x < 1195) {
                        this.x += 1;
                    }
                    break;
                case DOWNLEFT:
                    if (this.y < 795 && this.x > 5) {
                        this.x -= 1;
                        this.y += 1;
                    } else if (this.y < 795) {
                        this.y += 1;
                    } else if (this.x > 5) {
                        this.x -= 1;
                    }
                    break;
            }
            
            this.directionCounter += 1;
            
            if (this.directionCounter == 150) {
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

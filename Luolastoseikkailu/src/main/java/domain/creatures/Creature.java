package domain.creatures;

import java.util.Random;

public abstract class Creature {
    private final String name;
    private int x;
    private int y;
    private final int maxHP;
    private int health;
    private Direction direction;
    private Creature target;
    private int directionCounter;
    
    public Creature(String name, int x, int y, int maxHP, Random randomizer) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHP = maxHP;
        this.health = maxHP;
        this.target = null;
        changeDirection(randomizer);
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
        return this.health;
    }
    
    public void setHP(int change) {
        this.health += change;
        
        if (this.health > this.maxHP) {
            this.health = this.maxHP;
        }
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setTarget(Creature target) {
        this.target = target;
    }
    
    public Creature getTarget() {
        return this.target;
    }
    
    public void move(Random randomizer) {
        if (target == null) {
            moveToRandomDirection();
            
            this.directionCounter += 1;
            
            if (this.directionCounter == 100) {
                this.directionCounter = 0;
                changeDirection(randomizer);
            }
        } else {
            moveTowardsTarget();
        }
    }
    
    private void changeDirection(Random directionRandomizer) {
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
    
    private void moveToRandomDirection() {
        switch (this.direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UPRIGHT:
                moveUpRight();
                break;
            case UPLEFT:
                moveUpLeft();
                break;
            case DOWNRIGHT:
                moveDownRight();
                break;
            case DOWNLEFT:
                moveDownLeft();
                break;
        }
    }
    
    private void moveTowardsTarget() {
        int distanceX = Math.abs(this.getX() - this.target.getX());
        int distanceY = Math.abs(this.getY() - this.target.getY());
        
        if (this.getX() - this.target.getX() < 0) {
            if (distanceX > distanceY * 2) {
                moveRight();
            } else if (distanceX < distanceY / 2) {
                if (this.getY() - this.target.getY() < 0) {
                    moveDown();
                } else {
                    moveUp();
                }
            } else if (this.getY() - this.target.getY() < 0) {
                moveDownRight();
            } else {
                moveUpRight();
            }
        } else {
            if (distanceX > distanceY * 2) {
                moveLeft();
            } else if (distanceX < distanceY / 2) {
                if (this.getY() - this.target.getY() < 0) {
                    moveDown();
                } else {
                    moveUp();
                }
            } else if (this.getY() - this.target.getY() < 0) {
                moveDownLeft();
            } else {
                moveUpLeft();
            }
        }
    }
    
    private void moveUp() {
        if (this.y > 5) {
            this.y -= 1;
        }
    }
    
    private void moveDown() {
        if (this.y < 795) {
            this.y += 1;
        }
    }
    
    private void moveRight() {
        if (this.x < 1195) {
            this.x += 1;
        }
    }
    
    private void moveLeft() {
        if (this.x > 5) {
            this.x -= 1;
        }
    }
    
    private void moveUpRight() {
        if (this.y > 5 && this.x < 1195) {
            this.x += 1;
            this.y -= 1;
        } else if (this.y > 5) {
            this.y -= 1;
        } else if (this.x < 1195) {
            this.x += 1;
        }
    }
    
    private void moveUpLeft() {
        if (this.y > 5 && this.x > 5) {
            this.x -= 1;
            this.y -= 1;
        } else if (this.y > 5) {
            this.y -= 1;
        } else if (this.x > 5) {
            this.x -= 1;
        }
    }
    
    private void moveDownRight() {
        if (this.y < 795 && this.x < 1195) {
            this.x += 1;
            this.y += 1;
        } else if (this.y < 795) {
            this.y += 1;
        } else if (this.x < 1195) {
            this.x += 1;
        }
    }
    
    private void moveDownLeft() {
        if (this.y < 795 && this.x > 5) {
            this.x -= 1;
            this.y += 1;
        } else if (this.y < 795) {
            this.y += 1;
        } else if (this.x > 5) {
            this.x -= 1;
        }
    }
}

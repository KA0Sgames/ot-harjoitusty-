package domain.creatures;

import java.util.Random;

/**
 * Abstract class describing common attributes and functionality between all child classes of creature.
 * Serves as a basic template for monsters at the game.
 */

public abstract class Creature {
    private final String name;
    private int x;
    private int y;
    private final int maxHP;
    private int health;
    private Direction direction;
    private Creature target;
    private int directionCounter;
    
    /**
     * Constructor called from child class constructors to set basic attributes of all monsters.
     * @param name name distinguishing different type of creatures from each other, for example spider.
     * @param x creatures starting points x-coordinate.
     * @param y creatures starting points y-coordinate.
     * @param maxHP creatures maximum amount of health points. When health attribute is equal to maxHP, creature is in perfect health.
     * @param randomizer Random object used for picking arbitrary first moving direction. 
     */
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
    
    /**
     * Getter for retrieving creatures name (type in game).
     * @return returns creatures name (type in game) as String.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for retrieving creatures current x-coordinate on game screen.
     * @return integer value of x-coordinate of creatures current position on game screen.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Getter for retrieving creatures current y-coordinate on game screen.
     * @return integer value of y-coordinate of creatures current position on game screen.
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Setter to specify an x-coordinate where to move creature. Mainly used in tests.
     * @param x new integer value for x-coordinate describing position of the creature.
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Setter to specify an y-coordinate where to move creature. Mainly used in tests.
     * @param y new integer value for y-coordinate describing position of the creature.
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Getter to retrieve amount of maximum health points of the creature.
     * @return amount of maximum health points of the creature.
     */
    public int getMaxHP() {
        return this.maxHP;
    }
    
    /**
     * Getter to retrieve amount of current health points of the creature.
     * @return amount of current health points of the creature.
     */
    public int getHP() {
        return this.health;
    }
    
    /**
     * Method to modify creatures current health points. If health points are attempted to set over maximum health points, they are set equal to maximum.
     * @param change amount of health points added to current health points. Negative values lower current health points.
     */
    public void setHP(int change) {
        this.health += change;
        
        if (this.health > this.maxHP) {
            this.health = this.maxHP;
        }
    }
    
    /**
     * Setter to specify certain direction into which creature will move on next move command if it has no target.
     * @param direction Direction enum specifying moving direction.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    /**
     * Getter to retrieve next moving direction unless altered before moving command in the case that creature has no target.
     * @return Direction enum describing next moving direction.
     */
    public Direction getDirection() {
        return this.direction;
    }
    
    /**
     * Setter to specify target creature which this creature approaches and tries to attack.
     * @param target Creature which this creature will move towards and tries to attack.
     */
    public void setTarget(Creature target) {
        this.target = target;
    }
    
    /**
     * Getter to retrieve this creatures target towards which it will move and tries to attack.
     * @return Creature object towards which this creature moves and tries to attack. Null in the case that no target has been acquired by this creature.
     */
    public Creature getTarget() {
        return this.target;
    }
    
    /**
     * General method to call to move this creature.
     * @param randomizer Random object to be passed over to method responsible of changing direction at certain points.
     */
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

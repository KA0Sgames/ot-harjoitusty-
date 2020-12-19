package domain.creatures;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class holds creatures currently in the game and provides methods to update status of all creatures that are present.
 */

public class CreatureUpdater {
    private Player player;
    private ArrayList<Creature> creatures;
    
    /**
     * Constructor creating list for creatures to be added to. Player is set to null and has to be specified later.
     */
    public CreatureUpdater() {
        this.player = null;
        this.creatures = new ArrayList<>();
        
    }
    
    /**
     * Setter to specify players character for updater.
     * @param player Player object depicting users character.
     */
    public void addPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Getter to retrieve object depicting players character.
     * @return Player object depicting players character in the game.
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * Add given creature to the list of the updater for performing cyclic tasks on.
     * @param creature creature object which will be added to the game.
     */
    public void addCreature(Creature creature) {
        this.creatures.add(creature);
    }
    
    /**
     * Getter to retrieve all creatures currently in the game.
     * @return ArrayList of Creature objects which depict monsters currently in the game.
     */
    public ArrayList<Creature> getCreatures() {
        return this.creatures;
    }
    
    /**
     * Method to move all creatures currently in the game one step.
     * @param randomizer Random object to be passed on to move method of all the creatures.
     * @see domain.creatures.Creature#move(java.util.Random) move(Random randomizer)
     */
    public void moveAll(Random randomizer) {
        for (Creature creature: this.creatures) {
            creature.move(randomizer);
        }
    }
    
    /**
     * Method for each creature currently in the game to check if they have target in range to be attacked to at.
     * This method is applied on all the monsters, not the players character.
     * Possible target in this case is the players character, which in case is close enough will be set as target for the creature in question.
     */
    public void checkTargetDistance() {
        for (Creature creature: this.creatures) {
            int differenceInX = Math.abs(creature.getX() - this.player.getX());
            int differenceInY = Math.abs(creature.getY() - this.player.getY());
            
            double distance = Math.sqrt((Math.pow(differenceInX, 2) + (Math.pow(differenceInY, 2))));
            
            if (distance < 150) {
                creature.setTarget(this.player);
            }
        }
    }
}

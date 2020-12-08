package domain.creatures;

import java.util.ArrayList;

public class CreatureUpdater {
    private Player player;
    private ArrayList<Creature> creatures;
    
    public CreatureUpdater() {
        this.creatures = new ArrayList<>();
    }
    
    public void addPlayer(Player player) {
        this.player = player;
    }
    
    public void addCreature(Creature creature) {
        this.creatures.add(creature);
    }
    
    public void moveAll() {
        for (Creature creature: this.creatures) {
            creature.move();
        }
    }
    
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

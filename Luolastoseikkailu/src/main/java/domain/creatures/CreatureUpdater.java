package domain.creatures;

import java.util.ArrayList;

public class CreatureUpdater {
    private ArrayList<Creature> creatures;
    
    public CreatureUpdater() {
        this.creatures = new ArrayList<>();
    }
    
    public void addCreature(Creature creature) {
        this.creatures.add(creature);
    }
    
    public void moveAll() {
        for (Creature creature: this.creatures) {
            creature.move();
        }
    }
    
    private void checkTargetDistance() {
        
    }
}

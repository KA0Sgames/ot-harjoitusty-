package domain.creatures;

import java.util.ArrayList;

public class CreatureUpdater {
    private ArrayList<Creature> creatures;
    
    public CreatureUpdater() {
        this.creatures = new ArrayList<>();
    }
    
    public void moveAll() {
        for (Creature creature: this.creatures) {
            creature.move();
        }
    }
    
    private void checkTargetDistance() {
        
    }
}

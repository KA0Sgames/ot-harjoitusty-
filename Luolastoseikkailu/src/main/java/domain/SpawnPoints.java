package domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class defines possible points at game window where creatures may spawn (start at).
 * Class also has method for generating random spawn point out of the possible points.
 */

public class SpawnPoints {
    private ArrayList<SpawnPoint> spawnPoints;
    
    /**
     * Constructor which creates ArrayList of predetermined spawn points as SpawnPoint objects.
     */
    public SpawnPoints() {
        this.spawnPoints = new ArrayList<>();
        createList();
    }
    
    private void createList() {
        this.spawnPoints.add(new SpawnPoint(370, 400));
        this.spawnPoints.add(new SpawnPoint(430, 670));
        this.spawnPoints.add(new SpawnPoint(500, 250));
        this.spawnPoints.add(new SpawnPoint(690, 100));
        this.spawnPoints.add(new SpawnPoint(740, 620));
        this.spawnPoints.add(new SpawnPoint(900, 380));
        this.spawnPoints.add(new SpawnPoint(1070, 180));
    }
    
    /**
     * Method to retrieve random SpawnPoint object from predetermined list of possible options.
     * @param random Random object used in picking one option from predetermined list.
     * @return SpawnPoint object describing certain point at the game screen, picked out of predetermined list.
     */
    public SpawnPoint getRandomSpawn(Random random) {
        return this.spawnPoints.get(random.nextInt(this.spawnPoints.size()));
    }
}

package domain;

import java.util.ArrayList;
import java.util.Random;

public class SpawnPoints {
    private ArrayList<SpawnPoint> spawnPoints;
    
    public SpawnPoints() {
        this.spawnPoints = new ArrayList<>();
        createList();
    }
    
    private void createList() {
        this.spawnPoints.add(new SpawnPoint(150, 150));
        this.spawnPoints.add(new SpawnPoint(170, 400));
        this.spawnPoints.add(new SpawnPoint(230, 670));
        this.spawnPoints.add(new SpawnPoint(500, 250));
        this.spawnPoints.add(new SpawnPoint(690, 100));
        this.spawnPoints.add(new SpawnPoint(740, 620));
        this.spawnPoints.add(new SpawnPoint(900, 380));
    }
    
    public SpawnPoint getRandomSpawn() {
        Random random = new Random();
        
        return this.spawnPoints.get(random.nextInt(this.spawnPoints.size()));
    }
}

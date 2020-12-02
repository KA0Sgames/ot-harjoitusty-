package domain;

public class SessionInfo {
    private final String character;
    private int experience;
    private int collectedGold;
    private final int previousGold;
    
    public SessionInfo(String name, int xp, int prevGold) {
        this.character = name;
        this.experience = xp;
        this.previousGold = prevGold;
        this.collectedGold = 0;
    }
    
    public String getCharacter() {
        return this.character;
    }
    
    public int getXP() {
        return this.experience;
    }
    
    public int getCollectedGold() {
        return this.collectedGold;
    }
    
    public int getPrevGold() {
        return this.previousGold;
    }
    
    public void addXP(int xp) {
        this.experience += xp;
    }
    
    public void addGold(int gold) {
        this.collectedGold += gold;
    }
}

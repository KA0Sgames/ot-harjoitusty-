package domain;

public class SessionInfo {
    private String character;
    private int collectedXP;
    private int collectedGold;
    
    public SessionInfo(String name) {
        this.character = name;
        this.collectedXP = 0;
        this.collectedGold = 0;
    }
    
    public String getCharacter() {
        return this.character;
    }
    
    public int getXP() {
        return this.collectedXP;
    }
    
    public int getGold() {
        return this.collectedGold;
    }
    
    public void addXP(int xp) {
        this.collectedXP += xp;
    }
    
    public void addGold(int gold) {
        this.collectedGold += gold;
    }
}

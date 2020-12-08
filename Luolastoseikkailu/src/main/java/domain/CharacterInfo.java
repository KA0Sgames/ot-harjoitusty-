package domain;

/**
 * Class works as a container when moving character info from database to game logic
 */

public class CharacterInfo {
    String name;
    int experience;
    int gold;
    
    public CharacterInfo(String name, int experience, int gold) {
        this.name = name;
        this.experience = experience;
        this.gold = gold;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getExperience() {
        return this.experience;
    }
    
    public int getGold() {
        return this.gold;
    }
}

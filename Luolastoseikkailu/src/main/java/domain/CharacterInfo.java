package domain;

/**
 * Class works as a container when moving character info from database to game logic.
 */

public class CharacterInfo {
    String name;
    int experience;
    int gold;
    
    
    /**
     * Constructor to create CharacterInfo object.
     * @param name name of the character.
     * @param experience experience of the character.
     * @param gold amount of gold of the character.
     */
    public CharacterInfo(String name, int experience, int gold) {
        this.name = name;
        this.experience = experience;
        this.gold = gold;
    }
    
    /**
     * Getter to retrieve characters name.
     * @return name of the character.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter to retrieve characters experience points.
     * @return experience points of the character.
     */
    public int getExperience() {
        return this.experience;
    }
    
    /**
     * Getter to retrieve amount of gold character has.
     * @return amount of gold of the character.
     */
    public int getGold() {
        return this.gold;
    }
}

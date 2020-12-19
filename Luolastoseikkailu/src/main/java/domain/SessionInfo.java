package domain;

/**
 * Class holding information about current game session.
 */

public class SessionInfo {
    private final String character;
    private int experience;
    private int collectedGold;
    private final int previousGold;
    
    /**
     * Constructor for creating SessionInfo object.
     * @param name characters name for current game session.
     * @param xp experience of the character in current game session.
     * @param prevGold amount of gold character has at the start of the new game session.
     */
    public SessionInfo(String name, int xp, int prevGold) {
        this.character = name;
        this.experience = xp;
        this.previousGold = prevGold;
        this.collectedGold = 0;
    }
    
    /**
     * Getter to retrieve name of the character in the current game session.
     * @return name of the character as String.
     */
    public String getCharacter() {
        return this.character;
    }
    
    /**
     * Getter to retrieve amount of characters experience points in the current game session.
     * @return amount of experience points of the character.
     */
    public int getXP() {
        return this.experience;
    }
    
    /**
     * Getter to retrieve amount of gold character has collected during this game session.
     * @return amount of gold collected during this game session.
     */
    public int getCollectedGold() {
        return this.collectedGold;
    }
    
    /**
     * Getter to retrieve amount of gold character has collected during previous game sessions.
     * @return amount of gold character had at the start of this game session.
     */
    public int getPrevGold() {
        return this.previousGold;
    }
    
    /**
     * Setter to increase amount of experience points in this game session.
     * @param xp amount of points to be added to the total sum of experience.
     */
    public void addXP(int xp) {
        this.experience += xp;
    }
    
    /**
     * Setter to increase amount of gold collected during this game session.
     * @param gold  amount of gold to be added to the sum of gold collected during this game session.
     */
    public void addGold(int gold) {
        this.collectedGold += gold;
    }
}

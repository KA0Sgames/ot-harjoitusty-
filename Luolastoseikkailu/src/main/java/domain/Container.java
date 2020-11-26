package domain;

import java.util.ArrayList;

public class Container {
    private ArrayList<CharacterInfo> characters;
    
    public Container() {
        this.characters = new ArrayList<>();
    }
    
    public ArrayList<CharacterInfo> getCharacters() {
        return this.characters;
    }
    
    public void addCharacter(CharacterInfo character) {
        this.characters.add(character);
    }
}

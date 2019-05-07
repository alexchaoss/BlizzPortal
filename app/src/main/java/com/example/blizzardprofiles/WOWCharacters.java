package com.example.blizzardprofiles;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;

public class WOWCharacters {

    private JSONObject characterList;
    private JSONArray characters;

    private ArrayList<String> characterNamesList = new ArrayList<>();

    public WOWCharacters(JSONObject characterList){
        this.characterList = characterList;
        this.characters = getCharacter();
    }

    private JSONArray getCharacter() {
        characters = (JSONArray) characterList.get("characters");
        for(int i = 0; i<characters.size(); i++){
            JSONObject characterNames = (JSONObject) characters.get(i);
            characterNamesList.add(characterNames.get("name").toString());
        }
        return characters;
    }

    public ArrayList<String> getCharacterNamesList() {
        return characterNamesList;
    }
}

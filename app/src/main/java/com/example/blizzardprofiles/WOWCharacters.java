package com.example.blizzardprofiles;

import android.util.Log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;

public class WOWCharacters {

    private JSONObject characterList;
    private JSONArray characterInfo;

    private ArrayList<String> characterNamesList = new ArrayList<>();
    private ArrayList<String> realmList = new ArrayList<>();
    private ArrayList<String> levelList = new ArrayList<>();

    private ArrayList<String> urlThumbnail = new ArrayList<>();
    private ArrayList<String> classListNumber = new ArrayList<>();
    private ArrayList<String> classList = new ArrayList<>();

    public WOWCharacters(JSONObject characterList){
        this.characterList = characterList;
        this.characterInfo = getCharacterInfo();
    }

    private JSONArray getCharacterInfo() {
        characterInfo = (JSONArray) characterList.get("characters");
        for(int i = 0; i< characterInfo.size(); i++){
            JSONObject characters = (JSONObject) this.characterInfo.get(i);

            try{
                characterNamesList.add(characters.get("name").toString());
                realmList.add(characters.get("realm").toString());
                levelList.add(characters.get("level").toString());
                urlThumbnail.add(characters.get("thumbnail").toString());
               // classListNumber.add(characters.get("class").toString());
            }catch (Exception e){
                Log.e("Error", e.toString());
            }
        }
        return characterInfo;
    }

    public ArrayList<String> getCharacterNamesList() {
        return characterNamesList;
    }

    public ArrayList<String> getRealmsList(){
        return realmList;
    }

    public ArrayList<String> getLevelList(){
        return levelList;
    }

    public ArrayList<String> getUrlThumbnail() {
        return urlThumbnail;
    }

    public ArrayList<String> getClassList() {
        Log.i("Classes", classListNumber.toString());
        for(String number: classListNumber){
            classList.add(WoWClassEnum.fromOrdinal(Integer.parseInt(number)-1).toString());
        }
        Log.i("Classes", classList.toString());
        return classList;
    }
}

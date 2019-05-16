package com.example.blizzardprofiles.warcraft;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WowCharacters {

    private JSONObject characterList;
    private JSONArray characterInfo;

    private ArrayList<String> characterNamesList = new ArrayList<>();
    private ArrayList<String> realmList = new ArrayList<>();
    private ArrayList<String> levelList = new ArrayList<>();

    private ArrayList<String> urlThumbnail = new ArrayList<>();
    private ArrayList<String> classListNumber = new ArrayList<>();
    private ArrayList<String> classList = new ArrayList<>();
    private ArrayList<String> genderList = new ArrayList<>();

    private ArrayList<String> raceList = new ArrayList<>();

    public WowCharacters(JSONObject characterList){
        this.characterList = characterList;
        getCharacterInfo();
    }

    private void getCharacterInfo() {
        try{
            if(characterList.has("characters")) {
                characterInfo = (JSONArray) characterList.get("characters");

                for (int i = 0; i < characterInfo.length(); i++) {
                    JSONObject characters = (JSONObject) this.characterInfo.get(i);

                    characterNamesList.add(characters.get("name").toString());
                    realmList.add(characters.get("realm").toString());
                    levelList.add(characters.get("level").toString());
                    urlThumbnail.add(characters.get("thumbnail").toString());
                    classListNumber.add(characters.get("class").toString());
                    genderList.add(characters.get("gender").toString());
                    raceList.add(characters.get("race").toString());
                }
            }else{
                characterNamesList.add(characterList.get("name").toString());
                realmList.add(characterList.get("realm").toString());
                levelList.add(characterList.get("level").toString());
                urlThumbnail.add(characterList.get("thumbnail").toString());
                classListNumber.add(characterList.get("class").toString());
                genderList.add(characterList.get("gender").toString());
                raceList.add(characterList.get("race").toString());
            }
            }catch (Exception e){
                Log.e("Error WowCharacters", e.toString());
            }
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
        for(String number: classListNumber){
            classList.add(WowClassEnum.fromOrdinal(Integer.parseInt(number)-1).toString());
        }
        return classList;
    }

    public ArrayList<String> getGenderList(){
        return genderList;
    }

    public ArrayList<String> getRaceList() {
        return raceList;
    }
}

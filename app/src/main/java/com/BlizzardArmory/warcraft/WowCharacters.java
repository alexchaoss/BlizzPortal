package com.BlizzardArmory.warcraft;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    private ArrayList<String> raceListNumber = new ArrayList<>();
    private ArrayList<String> raceList = new ArrayList<>();
    private ArrayList<String> factionList = new ArrayList<>();

    public WowCharacters(JSONObject characterList) {
        this.characterList = characterList;
        getCharacterInfo();
    }

    private void getCharacterInfo() {
        try {
            if (characterList.has("characters")) {
                characterInfo = (JSONArray) characterList.get("characters");

                characterInfo = sortInfo(characterInfo);

                for (int i = 0; i < characterInfo.length(); i++) {
                    JSONObject characters = (JSONObject) this.characterInfo.get(i);

                    characterNamesList.add(characters.get("name").toString());
                    realmList.add(characters.get("realm").toString());
                    levelList.add(characters.get("level").toString());
                    urlThumbnail.add(characters.get("thumbnail").toString());
                    classListNumber.add(characters.get("class").toString());
                    genderList.add(characters.get("gender").toString());
                    raceListNumber.add(characters.get("race").toString());
                }
            } else {
                characterNamesList.add(characterList.get("name").toString());
                realmList.add(characterList.get("realm").toString());
                levelList.add(characterList.get("level").toString());
                urlThumbnail.add(characterList.get("thumbnail").toString());
                classListNumber.add(characterList.get("class").toString());
                genderList.add(characterList.get("gender").toString());
                raceListNumber.add(characterList.get("race").toString());
            }
        } catch (Exception e) {
            Log.e("Error WowCharacters", e.toString());
        }
    }

    public ArrayList<String> getCharacterNamesList() {
        return characterNamesList;
    }

    public ArrayList<String> getRealmsList() {
        return realmList;
    }

    public ArrayList<String> getLevelList() {
        return levelList;
    }

    public ArrayList<String> getUrlThumbnail() {
        return urlThumbnail;
    }

    public ArrayList<String> getClassList() {
        for (String number : classListNumber) {
            classList.add(ClassEnum.fromOrdinal(Integer.parseInt(number) - 1).toString());
        }
        return classList;
    }

    public ArrayList<String> getGenderList() {
        return genderList;
    }

    public ArrayList<String> getRaceListString() {

        for (String number : raceListNumber) {
            raceList.add(RaceEnum.fromOrdinal(Integer.parseInt(number) - 1));
        }
        return raceList;
    }

    public ArrayList<String> getRaceList() {
        return raceListNumber;
    }

    public ArrayList<String> getFactionList() {
        for (String number : raceListNumber) {
            int tempNum = RaceEnum.fromOrdinalFaction(Integer.parseInt(number) - 1);
            if(tempNum == 0){
                factionList.add("Alliance");
            }else if(tempNum == 1){
                factionList.add("Horde");
            }else{
                factionList.add("Neutral");
            }
        }
        return factionList;
    }

    public JSONArray sortInfo(JSONArray characterInfo) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<>();
        try {
            for (int i = 0; i < characterInfo.length(); i++) {
                jsonValues.add(characterInfo.getJSONObject(i));
            }
        } catch (JSONException e) {
            Log.e("Error", e.toString());
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            private static final String LEVEL = "level";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                int valA = 0;
                int valB = 0;

                try {
                    valA = (int) a.get(LEVEL);
                    valB = (int) b.get(LEVEL);
                } catch (JSONException e) {
                    Log.e("Error", e.toString());
                }

                if (valA > valB) {
                    return -1;
                }

                return 0;
            }
        });

        for (int i = 0; i < characterInfo.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;
    }
}

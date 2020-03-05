
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Character {

    @SerializedName("character")
    @Expose
    private Character_ character;
    @SerializedName("protected_character")
    @Expose
    private ProtectedCharacter protectedCharacter;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("realm")
    @Expose
    private Realm realm;
    @SerializedName("playable_class")
    @Expose
    private PlayableClass playableClass;
    @SerializedName("playable_race")
    @Expose
    private PlayableRace playableRace;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("faction")
    @Expose
    private Faction faction;
    @SerializedName("level")
    @Expose
    private int level;

    public Character_ getCharacter() {
        return character;
    }

    public void setCharacter(Character_ character) {
        this.character = character;
    }

    public ProtectedCharacter getProtectedCharacter() {
        return protectedCharacter;
    }

    public void setProtectedCharacter(ProtectedCharacter protectedCharacter) {
        this.protectedCharacter = protectedCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public PlayableClass getPlayableClass() {
        return playableClass;
    }

    public void setPlayableClass(PlayableClass playableClass) {
        this.playableClass = playableClass;
    }

    public PlayableRace getPlayableRace() {
        return playableRace;
    }

    public void setPlayableRace(PlayableRace playableRace) {
        this.playableRace = playableRace;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

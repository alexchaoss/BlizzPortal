
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Character.
 */
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
    private String level;

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character_ getCharacter() {
        return character;
    }

    /**
     * Sets character.
     *
     * @param character the character
     */
    public void setCharacter(Character_ character) {
        this.character = character;
    }

    /**
     * Gets protected character.
     *
     * @return the protected character
     */
    public ProtectedCharacter getProtectedCharacter() {
        return protectedCharacter;
    }

    /**
     * Sets protected character.
     *
     * @param protectedCharacter the protected character
     */
    public void setProtectedCharacter(ProtectedCharacter protectedCharacter) {
        this.protectedCharacter = protectedCharacter;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets realm.
     *
     * @return the realm
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Sets realm.
     *
     * @param realm the realm
     */
    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    /**
     * Gets playable class.
     *
     * @return the playable class
     */
    public PlayableClass getPlayableClass() {
        return playableClass;
    }

    /**
     * Sets playable class.
     *
     * @param playableClass the playable class
     */
    public void setPlayableClass(PlayableClass playableClass) {
        this.playableClass = playableClass;
    }

    /**
     * Gets playable race.
     *
     * @return the playable race
     */
    public PlayableRace getPlayableRace() {
        return playableRace;
    }

    /**
     * Sets playable race.
     *
     * @param playableRace the playable race
     */
    public void setPlayableRace(PlayableRace playableRace) {
        this.playableRace = playableRace;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets faction.
     *
     * @return the faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Sets faction.
     *
     * @param faction the faction
     */
    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(String level) {
        this.level = level;
    }

}

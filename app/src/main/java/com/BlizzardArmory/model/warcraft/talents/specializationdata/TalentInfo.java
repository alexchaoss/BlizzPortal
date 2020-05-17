package com.BlizzardArmory.model.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Talent info.
 */
public class TalentInfo {

    @SerializedName("key")
    @Expose
    private Key key;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private int id;

    /**
     * No args constructor for use in serialization
     */
    public TalentInfo() {
    }

    /**
     * Instantiates a new Talent info.
     *
     * @param key  the key
     * @param name the name
     * @param id   the id
     */
    public TalentInfo(Key key, String name, int id) {
        super();
        this.key = key;
        this.name = name;
        this.id = id;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(Key key) {
        this.key = key;
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
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

}

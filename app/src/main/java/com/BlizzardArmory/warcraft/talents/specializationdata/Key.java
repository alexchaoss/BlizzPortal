package com.BlizzardArmory.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Key.
 */
public class Key {

    @SerializedName("href")
    @Expose
    private String href;

    /**
     * No args constructor for use in serialization
     */
    public Key() {
    }

    /**
     * Instantiates a new Key.
     *
     * @param href the href
     */
    public Key(String href) {
        super();
        this.href = href;
    }

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets href.
     *
     * @param href the href
     */
    public void setHref(String href) {
        this.href = href;
    }

}

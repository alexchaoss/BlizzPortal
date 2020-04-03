package com.BlizzardArmory.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Self.
 */
public class Self {

    @SerializedName("href")
    @Expose
    private String href;

    /**
     * No args constructor for use in serialization
     */
    public Self() {
    }

    /**
     * Instantiates a new Self.
     *
     * @param href the href
     */
    public Self(String href) {
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

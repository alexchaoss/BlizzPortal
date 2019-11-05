package com.BlizzardArmory.warcraft.Talents.SpecializationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
     * @param href
     */
    public Key(String href) {
        super();
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

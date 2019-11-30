package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellPrice {

    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("display_strings")
    @Expose
    private DisplayStrings displayStrings;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DisplayStrings getDisplayStrings() {
        return displayStrings;
    }

    public void setDisplayStrings(DisplayStrings displayStrings) {
        this.displayStrings = displayStrings;
    }

}

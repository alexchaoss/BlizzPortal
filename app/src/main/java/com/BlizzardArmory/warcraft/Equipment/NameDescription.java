package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameDescription {

    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("color")
    @Expose
    private Color color;

    public NameDescription(String displayString, Color color){
        this.displayString = displayString;
        this.color = color;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
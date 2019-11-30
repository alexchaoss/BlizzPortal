package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("is_negated")
    @Expose
    private boolean isNegated;
    @SerializedName("is_equip_bonus")
    @Expose
    private boolean isEquipBonus;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public boolean isIsNegated() {
        return isNegated;
    }

    public void setIsNegated(boolean isNegated) {
        this.isNegated = isNegated;
    }

    public boolean isIsEquipBonus() {
        return isEquipBonus;
    }

    public void setIsEquipBonus(boolean isEquipBonus) {
        this.isEquipBonus = isEquipBonus;
    }

}

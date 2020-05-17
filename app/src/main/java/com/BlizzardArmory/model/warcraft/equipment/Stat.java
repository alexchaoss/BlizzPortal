package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Stat.
 */
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
    @SerializedName("display")
    @Expose
    private Display display;
    @SerializedName("is_negated")
    @Expose
    private boolean isNegated;
    @SerializedName("is_equip_bonus")
    @Expose
    private boolean isEquipBonus;

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets display string.
     *
     * @return the display string
     */
    public String getDisplay_string() {
        return displayString;
    }

    /**
     * Sets display string.
     *
     * @param displayString the display string
     */
    public void setDisplay_string(String displayString) {
        this.displayString = displayString;
    }

    /**
     * Gets display string.
     *
     * @return the display string
     */
    public Display getDisplayString() {
        return display;
    }

    /**
     * Sets display string.
     *
     * @param display the display
     */
    public void setDisplayString(Display display) {
        this.display = display;
    }

    /**
     * Is is negated boolean.
     *
     * @return the boolean
     */
    public boolean isIsNegated() {
        return isNegated;
    }

    /**
     * Sets is negated.
     *
     * @param isNegated the is negated
     */
    public void setIsNegated(boolean isNegated) {
        this.isNegated = isNegated;
    }

    /**
     * Is is equip bonus boolean.
     *
     * @return the boolean
     */
    public boolean isIsEquipBonus() {
        return isEquipBonus;
    }

    /**
     * Sets is equip bonus.
     *
     * @param isEquipBonus the is equip bonus
     */
    public void setIsEquipBonus(boolean isEquipBonus) {
        this.isEquipBonus = isEquipBonus;
    }

}

package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Display strings.
 */
public class DisplayStrings {

    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("gold")
    @Expose
    private String gold;
    @SerializedName("silver")
    @Expose
    private String silver;
    @SerializedName("copper")
    @Expose
    private String copper;

    /**
     * Gets header.
     *
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Sets header.
     *
     * @param header the header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Gets gold.
     *
     * @return the gold
     */
    public String getGold() {
        return gold;
    }

    /**
     * Sets gold.
     *
     * @param gold the gold
     */
    public void setGold(String gold) {
        this.gold = gold;
    }

    /**
     * Gets silver.
     *
     * @return the silver
     */
    public String getSilver() {
        return silver;
    }

    /**
     * Sets silver.
     *
     * @param silver the silver
     */
    public void setSilver(String silver) {
        this.silver = silver;
    }

    /**
     * Gets copper.
     *
     * @return the copper
     */
    public String getCopper() {
        return copper;
    }

    /**
     * Sets copper.
     *
     * @param copper the copper
     */
    public void setCopper(String copper) {
        this.copper = copper;
    }

}

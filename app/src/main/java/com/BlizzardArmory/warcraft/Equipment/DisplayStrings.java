package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getSilver() {
        return silver;
    }

    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getCopper() {
        return copper;
    }

    public void setCopper(String copper) {
        this.copper = copper;
    }

}

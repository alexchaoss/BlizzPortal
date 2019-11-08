
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("realm")
    @Expose
    private int realm;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("portrait")
    @Expose
    private String portrait;
    @SerializedName("decalTerran")
    @Expose
    private String decalTerran;
    @SerializedName("decalProtoss")
    @Expose
    private String decalProtoss;
    @SerializedName("decalZerg")
    @Expose
    private String decalZerg;
    @SerializedName("totalSwarmLevel")
    @Expose
    private int totalSwarmLevel;
    @SerializedName("totalAchievementPoints")
    @Expose
    private int totalAchievementPoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRealm() {
        return realm;
    }

    public void setRealm(int realm) {
        this.realm = realm;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDecalTerran() {
        return decalTerran;
    }

    public void setDecalTerran(String decalTerran) {
        this.decalTerran = decalTerran;
    }

    public String getDecalProtoss() {
        return decalProtoss;
    }

    public void setDecalProtoss(String decalProtoss) {
        this.decalProtoss = decalProtoss;
    }

    public String getDecalZerg() {
        return decalZerg;
    }

    public void setDecalZerg(String decalZerg) {
        this.decalZerg = decalZerg;
    }

    public int getTotalSwarmLevel() {
        return totalSwarmLevel;
    }

    public void setTotalSwarmLevel(int totalSwarmLevel) {
        this.totalSwarmLevel = totalSwarmLevel;
    }

    public int getTotalAchievementPoints() {
        return totalAchievementPoints;
    }

    public void setTotalAchievementPoints(int totalAchievementPoints) {
        this.totalAchievementPoints = totalAchievementPoints;
    }

}

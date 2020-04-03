
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Summary.
 */
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
    @SerializedName("clanName")
    @Expose
    private String clanName;
    @SerializedName("clanTag")
    @Expose
    private String clanTag;
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets realm.
     *
     * @return the realm
     */
    public int getRealm() {
        return realm;
    }

    /**
     * Sets realm.
     *
     * @param realm the realm
     */
    public void setRealm(int realm) {
        this.realm = realm;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets display name.
     *
     * @param displayName the display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets clan name.
     *
     * @return the clan name
     */
    public String getClanName() {
        return clanName;
    }

    /**
     * Sets clan name.
     *
     * @param clanName the clan name
     */
    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    /**
     * Gets clan tag.
     *
     * @return the clan tag
     */
    public String getClanTag() {
        return clanTag;
    }

    /**
     * Sets clan tag.
     *
     * @param clanTag the clan tag
     */
    public void setClanTag(String clanTag) {
        this.clanTag = clanTag;
    }

    /**
     * Gets portrait.
     *
     * @return the portrait
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * Sets portrait.
     *
     * @param portrait the portrait
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * Gets decal terran.
     *
     * @return the decal terran
     */
    public String getDecalTerran() {
        return decalTerran;
    }

    /**
     * Sets decal terran.
     *
     * @param decalTerran the decal terran
     */
    public void setDecalTerran(String decalTerran) {
        this.decalTerran = decalTerran;
    }

    /**
     * Gets decal protoss.
     *
     * @return the decal protoss
     */
    public String getDecalProtoss() {
        return decalProtoss;
    }

    /**
     * Sets decal protoss.
     *
     * @param decalProtoss the decal protoss
     */
    public void setDecalProtoss(String decalProtoss) {
        this.decalProtoss = decalProtoss;
    }

    /**
     * Gets decal zerg.
     *
     * @return the decal zerg
     */
    public String getDecalZerg() {
        return decalZerg;
    }

    /**
     * Sets decal zerg.
     *
     * @param decalZerg the decal zerg
     */
    public void setDecalZerg(String decalZerg) {
        this.decalZerg = decalZerg;
    }

    /**
     * Gets total swarm level.
     *
     * @return the total swarm level
     */
    public int getTotalSwarmLevel() {
        return totalSwarmLevel;
    }

    /**
     * Sets total swarm level.
     *
     * @param totalSwarmLevel the total swarm level
     */
    public void setTotalSwarmLevel(int totalSwarmLevel) {
        this.totalSwarmLevel = totalSwarmLevel;
    }

    /**
     * Gets total achievement points.
     *
     * @return the total achievement points
     */
    public int getTotalAchievementPoints() {
        return totalAchievementPoints;
    }

    /**
     * Sets total achievement points.
     *
     * @param totalAchievementPoints the total achievement points
     */
    public void setTotalAchievementPoints(int totalAchievementPoints) {
        this.totalAchievementPoints = totalAchievementPoints;
    }

}

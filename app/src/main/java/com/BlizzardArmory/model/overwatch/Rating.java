package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Rating.
 */
public class Rating {

    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("roleIcon")
    @Expose
    private String roleIcon;
    @SerializedName("rankIcon")
    @Expose
    private String rankIcon;

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets role icon.
     *
     * @return the role icon
     */
    public String getRoleIcon() {
        return roleIcon;
    }

    /**
     * Sets role icon.
     *
     * @param roleIcon the role icon
     */
    public void setRoleIcon(String roleIcon) {
        this.roleIcon = roleIcon;
    }

    /**
     * Gets rank icon.
     *
     * @return the rank icon
     */
    public String getRankIcon() {
        return rankIcon;
    }

    /**
     * Sets rank icon.
     *
     * @param rankIcon the rank icon
     */
    public void setRankIcon(String rankIcon) {
        this.rankIcon = rankIcon;
    }

}

package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Best 1 v 1 finish.
 */
public class Best1v1Finish {

    @SerializedName("leagueName")
    @Expose
    private String leagueName;
    @SerializedName("timesAchieved")
    @Expose
    private int timesAchieved;

    /**
     * Gets league name.
     *
     * @return the league name
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * Sets league name.
     *
     * @param leagueName the league name
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * Gets times achieved.
     *
     * @return the times achieved
     */
    public int getTimesAchieved() {
        return timesAchieved;
    }

    /**
     * Sets times achieved.
     *
     * @param timesAchieved the times achieved
     */
    public void setTimesAchieved(int timesAchieved) {
        this.timesAchieved = timesAchieved;
    }

}

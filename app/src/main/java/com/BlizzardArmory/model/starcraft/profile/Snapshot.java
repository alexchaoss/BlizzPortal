
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Snapshot.
 */
public class Snapshot {

    @SerializedName("seasonSnapshot")
    @Expose
    private SeasonSnapshot seasonSnapshot;
    @SerializedName("totalRankedSeasonGamesPlayed")
    @Expose
    private int totalRankedSeasonGamesPlayed;

    /**
     * Gets season snapshot.
     *
     * @return the season snapshot
     */
    public SeasonSnapshot getSeasonSnapshot() {
        return seasonSnapshot;
    }

    /**
     * Sets season snapshot.
     *
     * @param seasonSnapshot the season snapshot
     */
    public void setSeasonSnapshot(SeasonSnapshot seasonSnapshot) {
        this.seasonSnapshot = seasonSnapshot;
    }

    /**
     * Gets total ranked season games played.
     *
     * @return the total ranked season games played
     */
    public int getTotalRankedSeasonGamesPlayed() {
        return totalRankedSeasonGamesPlayed;
    }

    /**
     * Sets total ranked season games played.
     *
     * @param totalRankedSeasonGamesPlayed the total ranked season games played
     */
    public void setTotalRankedSeasonGamesPlayed(int totalRankedSeasonGamesPlayed) {
        this.totalRankedSeasonGamesPlayed = totalRankedSeasonGamesPlayed;
    }

}

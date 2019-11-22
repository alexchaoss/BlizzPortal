package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snapshot {

    @SerializedName("seasonSnapshot")
    @Expose
    private SeasonSnapshot seasonSnapshot;
    @SerializedName("totalRankedSeasonGamesPlayed")
    @Expose
    private int totalRankedSeasonGamesPlayed;

    public SeasonSnapshot getSeasonSnapshot() {
        return seasonSnapshot;
    }

    public void setSeasonSnapshot(SeasonSnapshot seasonSnapshot) {
        this.seasonSnapshot = seasonSnapshot;
    }

    public int getTotalRankedSeasonGamesPlayed() {
        return totalRankedSeasonGamesPlayed;
    }

    public void setTotalRankedSeasonGamesPlayed(int totalRankedSeasonGamesPlayed) {
        this.totalRankedSeasonGamesPlayed = totalRankedSeasonGamesPlayed;
    }

}

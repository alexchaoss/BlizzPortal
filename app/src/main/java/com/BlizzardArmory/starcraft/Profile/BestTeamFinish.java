package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestTeamFinish {

    @SerializedName("leagueName")
    @Expose
    private String leagueName;
    @SerializedName("timesAchieved")
    @Expose
    private int timesAchieved;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getTimesAchieved() {
        return timesAchieved;
    }

    public void setTimesAchieved(int timesAchieved) {
        this.timesAchieved = timesAchieved;
    }

}

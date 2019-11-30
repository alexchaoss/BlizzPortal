
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Best1v1Finish {

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


package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestTeamFinish {

    @SerializedName("leagueName")
    @Expose
    private Object leagueName;
    @SerializedName("timesAchieved")
    @Expose
    private int timesAchieved;

    public Object getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(Object leagueName) {
        this.leagueName = leagueName;
    }

    public int getTimesAchieved() {
        return timesAchieved;
    }

    public void setTimesAchieved(int timesAchieved) {
        this.timesAchieved = timesAchieved;
    }

}

package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CareerStats {

    @SerializedName("careerStats")
    @Expose
    private CareerStats careerStats;

    public CareerStats getCareerStats() {
        return careerStats;
    }

    public void setCareerStats(CareerStats careerStats) {
        this.careerStats = careerStats;
    }

}

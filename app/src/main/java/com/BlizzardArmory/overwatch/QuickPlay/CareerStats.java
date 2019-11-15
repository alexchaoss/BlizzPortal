
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CareerStats {

    @SerializedName("careerStats")
    @Expose
    private CareerStats_ careerStats;

    public CareerStats_ getCareerStats() {
        return careerStats;
    }

    public void setCareerStats(CareerStats_ careerStats) {
        this.careerStats = careerStats;
    }

}

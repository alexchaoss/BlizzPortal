
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competitive {

    @SerializedName("competitiveStats")
    @Expose
    private CompetitiveStats competitiveStats;

    public CompetitiveStats getCompetitiveStats() {
        return competitiveStats;
    }

    public void setCompetitiveStats(CompetitiveStats competitiveStats) {
        this.competitiveStats = competitiveStats;
    }

}

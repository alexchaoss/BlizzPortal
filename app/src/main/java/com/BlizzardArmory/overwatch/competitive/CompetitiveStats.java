package com.BlizzardArmory.overwatch.competitive;

import com.BlizzardArmory.overwatch.Awards;
import com.BlizzardArmory.overwatch.CareerStats;
import com.BlizzardArmory.overwatch.Games;
import com.BlizzardArmory.overwatch.topheroes.TopHeroes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetitiveStats {

    @SerializedName("awards")
    @Expose
    private Awards awards;
    @SerializedName("careerStats")
    @Expose
    private CareerStats careerStats;
    @SerializedName("games")
    @Expose
    private Games games;
    @SerializedName("topHeroes")
    @Expose
    private TopHeroes topHeroes;

    public Awards getAwards() {
        return awards;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    public CareerStats getCareerStats() {
        return careerStats;
    }

    public void setCareerStats(CareerStats careerStats) {
        this.careerStats = careerStats;
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    public TopHeroes getTopHeroes() {
        return topHeroes;
    }

    public void setTopHeroes(TopHeroes topHeroes) {
        this.topHeroes = topHeroes;
    }

}

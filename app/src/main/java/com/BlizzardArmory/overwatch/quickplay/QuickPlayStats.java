package com.BlizzardArmory.overwatch.quickplay;

import com.BlizzardArmory.overwatch.Awards;
import com.BlizzardArmory.overwatch.CareerStats;
import com.BlizzardArmory.overwatch.Games;
import com.BlizzardArmory.overwatch.topheroes.TopHeroes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Quick play stats.
 */
public class QuickPlayStats {

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

    /**
     * Gets awards.
     *
     * @return the awards
     */
    public Awards getAwards() {
        return awards;
    }

    /**
     * Sets awards.
     *
     * @param awards the awards
     */
    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    /**
     * Gets career stats.
     *
     * @return the career stats
     */
    public CareerStats getCareerStats() {
        return careerStats;
    }

    /**
     * Sets career stats.
     *
     * @param careerStats the career stats
     */
    public void setCareerStats(CareerStats careerStats) {
        this.careerStats = careerStats;
    }

    /**
     * Gets games.
     *
     * @return the games
     */
    public Games getGames() {
        return games;
    }

    /**
     * Sets games.
     *
     * @param games the games
     */
    public void setGames(Games games) {
        this.games = games;
    }

    /**
     * Gets top heroes.
     *
     * @return the top heroes
     */
    public TopHeroes getTopHeroes() {
        return topHeroes;
    }

    /**
     * Sets top heroes.
     *
     * @param topHeroes the top heroes
     */
    public void setTopHeroes(TopHeroes topHeroes) {
        this.topHeroes = topHeroes;
    }

}

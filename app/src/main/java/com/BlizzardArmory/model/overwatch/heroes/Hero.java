package com.BlizzardArmory.model.overwatch.heroes;

import com.BlizzardArmory.model.overwatch.Assists;
import com.BlizzardArmory.model.overwatch.Average;
import com.BlizzardArmory.model.overwatch.Best;
import com.BlizzardArmory.model.overwatch.Combat;
import com.BlizzardArmory.model.overwatch.Game;
import com.BlizzardArmory.model.overwatch.HeroSpecific;
import com.BlizzardArmory.model.overwatch.MatchAwards;
import com.BlizzardArmory.model.overwatch.Miscellaneous;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Hero.
 */
public class Hero {
    @SerializedName("assists")
    @Expose
    private Assists assists;
    @SerializedName("average")
    @Expose
    private Average average;
    @SerializedName("best")
    @Expose
    private Best best;
    @SerializedName("combat")
    @Expose
    private Combat combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private HeroSpecific heroSpecific;
    @SerializedName("game")
    @Expose
    private Game game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Miscellaneous miscellaneous;

    /**
     * Gets assists.
     *
     * @return the assists
     */
    public Assists getAssists() {
        return assists;
    }

    /**
     * Sets assists.
     *
     * @param assists the assists
     */
    public void setAssists(Assists assists) {
        this.assists = assists;
    }

    /**
     * Gets average.
     *
     * @return the average
     */
    public Average getAverage() {
        return average;
    }

    /**
     * Sets average.
     *
     * @param average the average
     */
    public void setAverage(Average average) {
        this.average = average;
    }

    /**
     * Gets best.
     *
     * @return the best
     */
    public Best getBest() {
        return best;
    }

    /**
     * Sets best.
     *
     * @param best the best
     */
    public void setBest(Best best) {
        this.best = best;
    }

    /**
     * Gets combat.
     *
     * @return the combat
     */
    public Combat getCombat() {
        return combat;
    }

    /**
     * Sets combat.
     *
     * @param combat the combat
     */
    public void setCombat(Combat combat) {
        this.combat = combat;
    }

    /**
     * Gets deaths.
     *
     * @return the deaths
     */
    public Object getDeaths() {
        return deaths;
    }

    /**
     * Sets deaths.
     *
     * @param deaths the deaths
     */
    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    /**
     * Gets hero specific.
     *
     * @return the hero specific
     */
    public HeroSpecific getHeroSpecific() {
        return heroSpecific;
    }

    /**
     * Sets hero specific.
     *
     * @param heroSpecific the hero specific
     */
    public void setHeroSpecific(HeroSpecific heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets match awards.
     *
     * @return the match awards
     */
    public MatchAwards getMatchAwards() {
        return matchAwards;
    }

    /**
     * Sets match awards.
     *
     * @param matchAwards the match awards
     */
    public void setMatchAwards(MatchAwards matchAwards) {
        this.matchAwards = matchAwards;
    }

    /**
     * Gets miscellaneous.
     *
     * @return the miscellaneous
     */
    public Miscellaneous getMiscellaneous() {
        return miscellaneous;
    }

    /**
     * Sets miscellaneous.
     *
     * @param miscellaneous the miscellaneous
     */
    public void setMiscellaneous(Miscellaneous miscellaneous) {
        this.miscellaneous = miscellaneous;
    }
}

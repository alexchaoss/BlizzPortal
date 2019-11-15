
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Junkrat {

    @SerializedName("assists")
    @Expose
    private Object assists;
    @SerializedName("average")
    @Expose
    private Average_ average;
    @SerializedName("best")
    @Expose
    private Best_ best;
    @SerializedName("combat")
    @Expose
    private Combat_ combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private HeroSpecific heroSpecific;
    @SerializedName("game")
    @Expose
    private Game_ game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards_ matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Object miscellaneous;

    public Object getAssists() {
        return assists;
    }

    public void setAssists(Object assists) {
        this.assists = assists;
    }

    public Average_ getAverage() {
        return average;
    }

    public void setAverage(Average_ average) {
        this.average = average;
    }

    public Best_ getBest() {
        return best;
    }

    public void setBest(Best_ best) {
        this.best = best;
    }

    public Combat_ getCombat() {
        return combat;
    }

    public void setCombat(Combat_ combat) {
        this.combat = combat;
    }

    public Object getDeaths() {
        return deaths;
    }

    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    public HeroSpecific getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(HeroSpecific heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    public Game_ getGame() {
        return game;
    }

    public void setGame(Game_ game) {
        this.game = game;
    }

    public MatchAwards_ getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards_ matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Object getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Object miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}


package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reaper {

    @SerializedName("assists")
    @Expose
    private Object assists;
    @SerializedName("average")
    @Expose
    private Average_____ average;
    @SerializedName("best")
    @Expose
    private Best_____ best;
    @SerializedName("combat")
    @Expose
    private Combat_____ combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private Object heroSpecific;
    @SerializedName("game")
    @Expose
    private Game_____ game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards_____ matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Object miscellaneous;

    public Object getAssists() {
        return assists;
    }

    public void setAssists(Object assists) {
        this.assists = assists;
    }

    public Average_____ getAverage() {
        return average;
    }

    public void setAverage(Average_____ average) {
        this.average = average;
    }

    public Best_____ getBest() {
        return best;
    }

    public void setBest(Best_____ best) {
        this.best = best;
    }

    public Combat_____ getCombat() {
        return combat;
    }

    public void setCombat(Combat_____ combat) {
        this.combat = combat;
    }

    public Object getDeaths() {
        return deaths;
    }

    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    public Object getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(Object heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    public Game_____ getGame() {
        return game;
    }

    public void setGame(Game_____ game) {
        this.game = game;
    }

    public MatchAwards_____ getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards_____ matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Object getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Object miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

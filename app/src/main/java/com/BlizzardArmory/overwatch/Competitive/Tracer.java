
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracer {

    @SerializedName("assists")
    @Expose
    private Object assists;
    @SerializedName("average")
    @Expose
    private Average______ average;
    @SerializedName("best")
    @Expose
    private Best______ best;
    @SerializedName("combat")
    @Expose
    private Combat______ combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private HeroSpecific____ heroSpecific;
    @SerializedName("game")
    @Expose
    private Game______ game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards______ matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Object miscellaneous;

    public Object getAssists() {
        return assists;
    }

    public void setAssists(Object assists) {
        this.assists = assists;
    }

    public Average______ getAverage() {
        return average;
    }

    public void setAverage(Average______ average) {
        this.average = average;
    }

    public Best______ getBest() {
        return best;
    }

    public void setBest(Best______ best) {
        this.best = best;
    }

    public Combat______ getCombat() {
        return combat;
    }

    public void setCombat(Combat______ combat) {
        this.combat = combat;
    }

    public Object getDeaths() {
        return deaths;
    }

    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    public HeroSpecific____ getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(HeroSpecific____ heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    public Game______ getGame() {
        return game;
    }

    public void setGame(Game______ game) {
        this.game = game;
    }

    public MatchAwards______ getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards______ matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Object getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Object miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

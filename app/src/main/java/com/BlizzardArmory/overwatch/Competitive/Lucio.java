
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lucio {

    @SerializedName("assists")
    @Expose
    private Assists_ assists;
    @SerializedName("average")
    @Expose
    private Average__ average;
    @SerializedName("best")
    @Expose
    private Best__ best;
    @SerializedName("combat")
    @Expose
    private Combat__ combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private HeroSpecific_ heroSpecific;
    @SerializedName("game")
    @Expose
    private Game__ game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards__ matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Object miscellaneous;

    public Assists_ getAssists() {
        return assists;
    }

    public void setAssists(Assists_ assists) {
        this.assists = assists;
    }

    public Average__ getAverage() {
        return average;
    }

    public void setAverage(Average__ average) {
        this.average = average;
    }

    public Best__ getBest() {
        return best;
    }

    public void setBest(Best__ best) {
        this.best = best;
    }

    public Combat__ getCombat() {
        return combat;
    }

    public void setCombat(Combat__ combat) {
        this.combat = combat;
    }

    public Object getDeaths() {
        return deaths;
    }

    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    public HeroSpecific_ getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(HeroSpecific_ heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    public Game__ getGame() {
        return game;
    }

    public void setGame(Game__ game) {
        this.game = game;
    }

    public MatchAwards__ getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards__ matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Object getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Object miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracer {

    @SerializedName("assists")
    @Expose
    private Object assists;
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
    private Object miscellaneous;

    public Object getAssists() {
        return assists;
    }

    public void setAssists(Object assists) {
        this.assists = assists;
    }

    public Average getAverage() {
        return average;
    }

    public void setAverage(Average average) {
        this.average = average;
    }

    public Best getBest() {
        return best;
    }

    public void setBest(Best best) {
        this.best = best;
    }

    public Combat getCombat() {
        return combat;
    }

    public void setCombat(Combat combat) {
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public MatchAwards getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Object getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Object miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

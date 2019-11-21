package com.BlizzardArmory.overwatch.Heroes;

import com.BlizzardArmory.overwatch.Average;
import com.BlizzardArmory.overwatch.Assists;
import com.BlizzardArmory.overwatch.Best;
import com.BlizzardArmory.overwatch.Combat;
import com.BlizzardArmory.overwatch.Game;
import com.BlizzardArmory.overwatch.MatchAwards;
import com.BlizzardArmory.overwatch.Miscellaneous;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllHeroes {

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
    private Object heroSpecific;
    @SerializedName("game")
    @Expose
    private Game game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Miscellaneous miscellaneous;

    public Assists getAssists() {
        return assists;
    }

    public void setAssists(Assists assists) {
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

    public Object getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(Object heroSpecific) {
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

    public Miscellaneous getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Miscellaneous miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

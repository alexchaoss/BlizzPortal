
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mei {

    @SerializedName("assists")
    @Expose
    private Object assists;
    @SerializedName("average")
    @Expose
    private Average___ average;
    @SerializedName("best")
    @Expose
    private Best___ best;
    @SerializedName("combat")
    @Expose
    private Combat___ combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private HeroSpecific__ heroSpecific;
    @SerializedName("game")
    @Expose
    private Game___ game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards___ matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Miscellaneous_ miscellaneous;

    public Object getAssists() {
        return assists;
    }

    public void setAssists(Object assists) {
        this.assists = assists;
    }

    public Average___ getAverage() {
        return average;
    }

    public void setAverage(Average___ average) {
        this.average = average;
    }

    public Best___ getBest() {
        return best;
    }

    public void setBest(Best___ best) {
        this.best = best;
    }

    public Combat___ getCombat() {
        return combat;
    }

    public void setCombat(Combat___ combat) {
        this.combat = combat;
    }

    public Object getDeaths() {
        return deaths;
    }

    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    public HeroSpecific__ getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(HeroSpecific__ heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    public Game___ getGame() {
        return game;
    }

    public void setGame(Game___ game) {
        this.game = game;
    }

    public MatchAwards___ getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards___ matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Miscellaneous_ getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Miscellaneous_ miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

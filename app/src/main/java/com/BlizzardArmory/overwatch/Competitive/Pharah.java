
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pharah {

    @SerializedName("assists")
    @Expose
    private Object assists;
    @SerializedName("average")
    @Expose
    private Average____ average;
    @SerializedName("best")
    @Expose
    private Best____ best;
    @SerializedName("combat")
    @Expose
    private Combat____ combat;
    @SerializedName("deaths")
    @Expose
    private Object deaths;
    @SerializedName("heroSpecific")
    @Expose
    private HeroSpecific___ heroSpecific;
    @SerializedName("game")
    @Expose
    private Game____ game;
    @SerializedName("matchAwards")
    @Expose
    private MatchAwards____ matchAwards;
    @SerializedName("miscellaneous")
    @Expose
    private Miscellaneous__ miscellaneous;

    public Object getAssists() {
        return assists;
    }

    public void setAssists(Object assists) {
        this.assists = assists;
    }

    public Average____ getAverage() {
        return average;
    }

    public void setAverage(Average____ average) {
        this.average = average;
    }

    public Best____ getBest() {
        return best;
    }

    public void setBest(Best____ best) {
        this.best = best;
    }

    public Combat____ getCombat() {
        return combat;
    }

    public void setCombat(Combat____ combat) {
        this.combat = combat;
    }

    public Object getDeaths() {
        return deaths;
    }

    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    public HeroSpecific___ getHeroSpecific() {
        return heroSpecific;
    }

    public void setHeroSpecific(HeroSpecific___ heroSpecific) {
        this.heroSpecific = heroSpecific;
    }

    public Game____ getGame() {
        return game;
    }

    public void setGame(Game____ game) {
        this.game = game;
    }

    public MatchAwards____ getMatchAwards() {
        return matchAwards;
    }

    public void setMatchAwards(MatchAwards____ matchAwards) {
        this.matchAwards = matchAwards;
    }

    public Miscellaneous__ getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Miscellaneous__ miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}

package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssistsHero {

    @SerializedName("defensiveAssists")
    @Expose
    private double defensiveAssists;
    @SerializedName("defensiveAssistsAvgPer10Min")
    @Expose
    private float defensiveAssistsAvgPer10Min;
    @SerializedName("defensiveAssistsMostInGame")
    @Expose
    private double defensiveAssistsMostInGame;
    @SerializedName("healingDone")
    @Expose
    private double healingDone;
    @SerializedName("healingDoneAvgPer10Min")
    @Expose
    private double healingDoneAvgPer10Min;
    @SerializedName("healingDoneMostInGame")
    @Expose
    private double healingDoneMostInGame;
    @SerializedName("offensiveAssists")
    @Expose
    private double offensiveAssists;
    @SerializedName("offensiveAssistsAvgPer10Min")
    @Expose
    private float offensiveAssistsAvgPer10Min;
    @SerializedName("offensiveAssistsMostInGame")
    @Expose
    private double offensiveAssistsMostInGame;

    public double getDefensiveAssists() {
        return defensiveAssists;
    }

    public void setDefensiveAssists(double defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    public float getDefensiveAssistsAvgPer10Min() {
        return defensiveAssistsAvgPer10Min;
    }

    public void setDefensiveAssistsAvgPer10Min(float defensiveAssistsAvgPer10Min) {
        this.defensiveAssistsAvgPer10Min = defensiveAssistsAvgPer10Min;
    }

    public double getDefensiveAssistsMostInGame() {
        return defensiveAssistsMostInGame;
    }

    public void setDefensiveAssistsMostInGame(double defensiveAssistsMostInGame) {
        this.defensiveAssistsMostInGame = defensiveAssistsMostInGame;
    }

    public double getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(double healingDone) {
        this.healingDone = healingDone;
    }

    public double getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    public void setHealingDoneAvgPer10Min(double healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    public double getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    public void setHealingDoneMostInGame(double healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

    public double getOffensiveAssists() {
        return offensiveAssists;
    }

    public void setOffensiveAssists(double offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    public float getOffensiveAssistsAvgPer10Min() {
        return offensiveAssistsAvgPer10Min;
    }

    public void setOffensiveAssistsAvgPer10Min(float offensiveAssistsAvgPer10Min) {
        this.offensiveAssistsAvgPer10Min = offensiveAssistsAvgPer10Min;
    }

    public double getOffensiveAssistsMostInGame() {
        return offensiveAssistsMostInGame;
    }

    public void setOffensiveAssistsMostInGame(double offensiveAssistsMostInGame) {
        this.offensiveAssistsMostInGame = offensiveAssistsMostInGame;
    }

}

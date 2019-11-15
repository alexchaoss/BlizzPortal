
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssistsHero {

    @SerializedName("defensiveAssists")
    @Expose
    private int defensiveAssists;
    @SerializedName("defensiveAssistsAvgPer10Min")
    @Expose
    private float defensiveAssistsAvgPer10Min;
    @SerializedName("defensiveAssistsMostInGame")
    @Expose
    private int defensiveAssistsMostInGame;
    @SerializedName("healingDone")
    @Expose
    private int healingDone;
    @SerializedName("healingDoneAvgPer10Min")
    @Expose
    private int healingDoneAvgPer10Min;
    @SerializedName("healingDoneMostInGame")
    @Expose
    private int healingDoneMostInGame;
    @SerializedName("offensiveAssists")
    @Expose
    private int offensiveAssists;
    @SerializedName("offensiveAssistsAvgPer10Min")
    @Expose
    private float offensiveAssistsAvgPer10Min;
    @SerializedName("offensiveAssistsMostInGame")
    @Expose
    private int offensiveAssistsMostInGame;

    public int getDefensiveAssists() {
        return defensiveAssists;
    }

    public void setDefensiveAssists(int defensiveAssists) {
        this.defensiveAssists = defensiveAssists;
    }

    public float getDefensiveAssistsAvgPer10Min() {
        return defensiveAssistsAvgPer10Min;
    }

    public void setDefensiveAssistsAvgPer10Min(float defensiveAssistsAvgPer10Min) {
        this.defensiveAssistsAvgPer10Min = defensiveAssistsAvgPer10Min;
    }

    public int getDefensiveAssistsMostInGame() {
        return defensiveAssistsMostInGame;
    }

    public void setDefensiveAssistsMostInGame(int defensiveAssistsMostInGame) {
        this.defensiveAssistsMostInGame = defensiveAssistsMostInGame;
    }

    public int getHealingDone() {
        return healingDone;
    }

    public void setHealingDone(int healingDone) {
        this.healingDone = healingDone;
    }

    public int getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    public void setHealingDoneAvgPer10Min(int healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    public int getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    public void setHealingDoneMostInGame(int healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

    public int getOffensiveAssists() {
        return offensiveAssists;
    }

    public void setOffensiveAssists(int offensiveAssists) {
        this.offensiveAssists = offensiveAssists;
    }

    public float getOffensiveAssistsAvgPer10Min() {
        return offensiveAssistsAvgPer10Min;
    }

    public void setOffensiveAssistsAvgPer10Min(float offensiveAssistsAvgPer10Min) {
        this.offensiveAssistsAvgPer10Min = offensiveAssistsAvgPer10Min;
    }

    public int getOffensiveAssistsMostInGame() {
        return offensiveAssistsMostInGame;
    }

    public void setOffensiveAssistsMostInGame(int offensiveAssistsMostInGame) {
        this.offensiveAssistsMostInGame = offensiveAssistsMostInGame;
    }

}


package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lucio_ {

    @SerializedName("timePlayed")
    @Expose
    private String timePlayed;
    @SerializedName("gamesWon")
    @Expose
    private int gamesWon;
    @SerializedName("winPercentage")
    @Expose
    private int winPercentage;
    @SerializedName("weaponAccuracy")
    @Expose
    private int weaponAccuracy;
    @SerializedName("eliminationsPerLife")
    @Expose
    private int eliminationsPerLife;
    @SerializedName("multiKillBest")
    @Expose
    private int multiKillBest;
    @SerializedName("objectiveKills")
    @Expose
    private int objectiveKills;

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(int winPercentage) {
        this.winPercentage = winPercentage;
    }

    public int getWeaponAccuracy() {
        return weaponAccuracy;
    }

    public void setWeaponAccuracy(int weaponAccuracy) {
        this.weaponAccuracy = weaponAccuracy;
    }

    public int getEliminationsPerLife() {
        return eliminationsPerLife;
    }

    public void setEliminationsPerLife(int eliminationsPerLife) {
        this.eliminationsPerLife = eliminationsPerLife;
    }

    public int getMultiKillBest() {
        return multiKillBest;
    }

    public void setMultiKillBest(int multiKillBest) {
        this.multiKillBest = multiKillBest;
    }

    public int getObjectiveKills() {
        return objectiveKills;
    }

    public void setObjectiveKills(int objectiveKills) {
        this.objectiveKills = objectiveKills;
    }

}

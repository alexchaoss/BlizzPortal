package com.BlizzardArmory.overwatch.QuickPlay.TopHeroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doomfist {

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
    private float eliminationsPerLife;
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

    public float getEliminationsPerLife() {
        return eliminationsPerLife;
    }

    public void setEliminationsPerLife(float eliminationsPerLife) {
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

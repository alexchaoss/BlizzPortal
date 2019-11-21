package com.BlizzardArmory.overwatch.TopHeroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hero {

    @SerializedName("timePlayed")
    @Expose
    private String timePlayed;
    @SerializedName("gamesWon")
    @Expose
    private double gamesWon;
    @SerializedName("winPercentage")
    @Expose
    private double winPercentage;
    @SerializedName("weaponAccuracy")
    @Expose
    private double weaponAccuracy;
    @SerializedName("eliminationsPerLife")
    @Expose
    private float eliminationsPerLife;
    @SerializedName("multiKillBest")
    @Expose
    private double multiKillBest;
    @SerializedName("objectiveKills")
    @Expose
    private double objectiveKills;

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public double getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(double gamesWon) {
        this.gamesWon = gamesWon;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public double getWeaponAccuracy() {
        return weaponAccuracy;
    }

    public void setWeaponAccuracy(double weaponAccuracy) {
        this.weaponAccuracy = weaponAccuracy;
    }

    public float getEliminationsPerLife() {
        return eliminationsPerLife;
    }

    public void setEliminationsPerLife(float eliminationsPerLife) {
        this.eliminationsPerLife = eliminationsPerLife;
    }

    public double getMultiKillBest() {
        return multiKillBest;
    }

    public void setMultiKillBest(double multiKillBest) {
        this.multiKillBest = multiKillBest;
    }

    public double getObjectiveKills() {
        return objectiveKills;
    }

    public void setObjectiveKills(double objectiveKills) {
        this.objectiveKills = objectiveKills;
    }
}

package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroSpecific {

    @SerializedName("enemiesTrapped")
    @Expose
    private int enemiesTrapped;
    @SerializedName("enemiesTrappedAvgPer10Min")
    @Expose
    private float enemiesTrappedAvgPer10Min;
    @SerializedName("enemiesTrappedMostInGame")
    @Expose
    private int enemiesTrappedMostInGame;
    @SerializedName("ripTireKills")
    @Expose
    private int ripTireKills;
    @SerializedName("ripTireKillsAvgPer10Min")
    @Expose
    private float ripTireKillsAvgPer10Min;
    @SerializedName("ripTireKillsMostInGame")
    @Expose
    private int ripTireKillsMostInGame;

    public int getEnemiesTrapped() {
        return enemiesTrapped;
    }

    public void setEnemiesTrapped(int enemiesTrapped) {
        this.enemiesTrapped = enemiesTrapped;
    }

    public float getEnemiesTrappedAvgPer10Min() {
        return enemiesTrappedAvgPer10Min;
    }

    public void setEnemiesTrappedAvgPer10Min(float enemiesTrappedAvgPer10Min) {
        this.enemiesTrappedAvgPer10Min = enemiesTrappedAvgPer10Min;
    }

    public int getEnemiesTrappedMostInGame() {
        return enemiesTrappedMostInGame;
    }

    public void setEnemiesTrappedMostInGame(int enemiesTrappedMostInGame) {
        this.enemiesTrappedMostInGame = enemiesTrappedMostInGame;
    }

    public int getRipTireKills() {
        return ripTireKills;
    }

    public void setRipTireKills(int ripTireKills) {
        this.ripTireKills = ripTireKills;
    }

    public float getRipTireKillsAvgPer10Min() {
        return ripTireKillsAvgPer10Min;
    }

    public void setRipTireKillsAvgPer10Min(float ripTireKillsAvgPer10Min) {
        this.ripTireKillsAvgPer10Min = ripTireKillsAvgPer10Min;
    }

    public int getRipTireKillsMostInGame() {
        return ripTireKillsMostInGame;
    }

    public void setRipTireKillsMostInGame(int ripTireKillsMostInGame) {
        this.ripTireKillsMostInGame = ripTireKillsMostInGame;
    }

}

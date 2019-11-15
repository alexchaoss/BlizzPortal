
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroSpecific___ {

    @SerializedName("barrageKills")
    @Expose
    private int barrageKills;
    @SerializedName("barrageKillsAvgPer10Min")
    @Expose
    private float barrageKillsAvgPer10Min;
    @SerializedName("barrageKillsMostInGame")
    @Expose
    private int barrageKillsMostInGame;
    @SerializedName("directHitsAccuracy")
    @Expose
    private String directHitsAccuracy;
    @SerializedName("rocketDirectHits")
    @Expose
    private int rocketDirectHits;
    @SerializedName("rocketDirectHitsAvgPer10Min")
    @Expose
    private float rocketDirectHitsAvgPer10Min;
    @SerializedName("rocketDirectHitsMostInGame")
    @Expose
    private int rocketDirectHitsMostInGame;

    public int getBarrageKills() {
        return barrageKills;
    }

    public void setBarrageKills(int barrageKills) {
        this.barrageKills = barrageKills;
    }

    public float getBarrageKillsAvgPer10Min() {
        return barrageKillsAvgPer10Min;
    }

    public void setBarrageKillsAvgPer10Min(float barrageKillsAvgPer10Min) {
        this.barrageKillsAvgPer10Min = barrageKillsAvgPer10Min;
    }

    public int getBarrageKillsMostInGame() {
        return barrageKillsMostInGame;
    }

    public void setBarrageKillsMostInGame(int barrageKillsMostInGame) {
        this.barrageKillsMostInGame = barrageKillsMostInGame;
    }

    public String getDirectHitsAccuracy() {
        return directHitsAccuracy;
    }

    public void setDirectHitsAccuracy(String directHitsAccuracy) {
        this.directHitsAccuracy = directHitsAccuracy;
    }

    public int getRocketDirectHits() {
        return rocketDirectHits;
    }

    public void setRocketDirectHits(int rocketDirectHits) {
        this.rocketDirectHits = rocketDirectHits;
    }

    public float getRocketDirectHitsAvgPer10Min() {
        return rocketDirectHitsAvgPer10Min;
    }

    public void setRocketDirectHitsAvgPer10Min(float rocketDirectHitsAvgPer10Min) {
        this.rocketDirectHitsAvgPer10Min = rocketDirectHitsAvgPer10Min;
    }

    public int getRocketDirectHitsMostInGame() {
        return rocketDirectHitsMostInGame;
    }

    public void setRocketDirectHitsMostInGame(int rocketDirectHitsMostInGame) {
        this.rocketDirectHitsMostInGame = rocketDirectHitsMostInGame;
    }

}

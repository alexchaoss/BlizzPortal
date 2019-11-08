
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Career {

    @SerializedName("terranWins")
    @Expose
    private int terranWins;
    @SerializedName("zergWins")
    @Expose
    private int zergWins;
    @SerializedName("protossWins")
    @Expose
    private int protossWins;
    @SerializedName("totalCareerGames")
    @Expose
    private int totalCareerGames;
    @SerializedName("totalGamesThisSeason")
    @Expose
    private int totalGamesThisSeason;
    @SerializedName("current1v1LeagueName")
    @Expose
    private Object current1v1LeagueName;
    @SerializedName("currentBestTeamLeagueName")
    @Expose
    private Object currentBestTeamLeagueName;
    @SerializedName("best1v1Finish")
    @Expose
    private Best1v1Finish best1v1Finish;
    @SerializedName("bestTeamFinish")
    @Expose
    private BestTeamFinish bestTeamFinish;

    public int getTerranWins() {
        return terranWins;
    }

    public void setTerranWins(int terranWins) {
        this.terranWins = terranWins;
    }

    public int getZergWins() {
        return zergWins;
    }

    public void setZergWins(int zergWins) {
        this.zergWins = zergWins;
    }

    public int getProtossWins() {
        return protossWins;
    }

    public void setProtossWins(int protossWins) {
        this.protossWins = protossWins;
    }

    public int getTotalCareerGames() {
        return totalCareerGames;
    }

    public void setTotalCareerGames(int totalCareerGames) {
        this.totalCareerGames = totalCareerGames;
    }

    public int getTotalGamesThisSeason() {
        return totalGamesThisSeason;
    }

    public void setTotalGamesThisSeason(int totalGamesThisSeason) {
        this.totalGamesThisSeason = totalGamesThisSeason;
    }

    public Object getCurrent1v1LeagueName() {
        return current1v1LeagueName;
    }

    public void setCurrent1v1LeagueName(Object current1v1LeagueName) {
        this.current1v1LeagueName = current1v1LeagueName;
    }

    public Object getCurrentBestTeamLeagueName() {
        return currentBestTeamLeagueName;
    }

    public void setCurrentBestTeamLeagueName(Object currentBestTeamLeagueName) {
        this.currentBestTeamLeagueName = currentBestTeamLeagueName;
    }

    public Best1v1Finish getBest1v1Finish() {
        return best1v1Finish;
    }

    public void setBest1v1Finish(Best1v1Finish best1v1Finish) {
        this.best1v1Finish = best1v1Finish;
    }

    public BestTeamFinish getBestTeamFinish() {
        return bestTeamFinish;
    }

    public void setBestTeamFinish(BestTeamFinish bestTeamFinish) {
        this.bestTeamFinish = bestTeamFinish;
    }

}

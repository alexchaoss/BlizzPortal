
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Career.
 */
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
    private String current1v1LeagueName;
    @SerializedName("currentBestTeamLeagueName")
    @Expose
    private Object currentBestTeamLeagueName;
    @SerializedName("best1v1Finish")
    @Expose
    private Best1v1Finish best1v1Finish;
    @SerializedName("bestTeamFinish")
    @Expose
    private BestTeamFinish bestTeamFinish;

    /**
     * Gets terran wins.
     *
     * @return the terran wins
     */
    public int getTerranWins() {
        return terranWins;
    }

    /**
     * Sets terran wins.
     *
     * @param terranWins the terran wins
     */
    public void setTerranWins(int terranWins) {
        this.terranWins = terranWins;
    }

    /**
     * Gets zerg wins.
     *
     * @return the zerg wins
     */
    public int getZergWins() {
        return zergWins;
    }

    /**
     * Sets zerg wins.
     *
     * @param zergWins the zerg wins
     */
    public void setZergWins(int zergWins) {
        this.zergWins = zergWins;
    }

    /**
     * Gets protoss wins.
     *
     * @return the protoss wins
     */
    public int getProtossWins() {
        return protossWins;
    }

    /**
     * Sets protoss wins.
     *
     * @param protossWins the protoss wins
     */
    public void setProtossWins(int protossWins) {
        this.protossWins = protossWins;
    }

    /**
     * Gets total career games.
     *
     * @return the total career games
     */
    public int getTotalCareerGames() {
        return totalCareerGames;
    }

    /**
     * Sets total career games.
     *
     * @param totalCareerGames the total career games
     */
    public void setTotalCareerGames(int totalCareerGames) {
        this.totalCareerGames = totalCareerGames;
    }

    /**
     * Gets total games this season.
     *
     * @return the total games this season
     */
    public int getTotalGamesThisSeason() {
        return totalGamesThisSeason;
    }

    /**
     * Sets total games this season.
     *
     * @param totalGamesThisSeason the total games this season
     */
    public void setTotalGamesThisSeason(int totalGamesThisSeason) {
        this.totalGamesThisSeason = totalGamesThisSeason;
    }

    /**
     * Gets current 1 v 1 league name.
     *
     * @return the current 1 v 1 league name
     */
    public String getCurrent1v1LeagueName() {
        return current1v1LeagueName;
    }

    /**
     * Sets current 1 v 1 league name.
     *
     * @param current1v1LeagueName the current 1 v 1 league name
     */
    public void setCurrent1v1LeagueName(String current1v1LeagueName) {
        this.current1v1LeagueName = current1v1LeagueName;
    }

    /**
     * Gets current best team league name.
     *
     * @return the current best team league name
     */
    public Object getCurrentBestTeamLeagueName() {
        return currentBestTeamLeagueName;
    }

    /**
     * Sets current best team league name.
     *
     * @param currentBestTeamLeagueName the current best team league name
     */
    public void setCurrentBestTeamLeagueName(Object currentBestTeamLeagueName) {
        this.currentBestTeamLeagueName = currentBestTeamLeagueName;
    }

    /**
     * Gets best 1 v 1 finish.
     *
     * @return the best 1 v 1 finish
     */
    public Best1v1Finish getBest1v1Finish() {
        return best1v1Finish;
    }

    /**
     * Sets best 1 v 1 finish.
     *
     * @param best1v1Finish the best 1 v 1 finish
     */
    public void setBest1v1Finish(Best1v1Finish best1v1Finish) {
        this.best1v1Finish = best1v1Finish;
    }

    /**
     * Gets best team finish.
     *
     * @return the best team finish
     */
    public BestTeamFinish getBestTeamFinish() {
        return bestTeamFinish;
    }

    /**
     * Sets best team finish.
     *
     * @param bestTeamFinish the best team finish
     */
    public void setBestTeamFinish(BestTeamFinish bestTeamFinish) {
        this.bestTeamFinish = bestTeamFinish;
    }

}


package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Archon.
 */
public class Archon {

    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("leagueName")
    @Expose
    private String leagueName;
    @SerializedName("totalGames")
    @Expose
    private int totalGames;
    @SerializedName("totalWins")
    @Expose
    private int totalWins;

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Gets league name.
     *
     * @return the league name
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * Sets league name.
     *
     * @param leagueName the league name
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * Gets total games.
     *
     * @return the total games
     */
    public int getTotalGames() {
        return totalGames;
    }

    /**
     * Sets total games.
     *
     * @param totalGames the total games
     */
    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    /**
     * Gets total wins.
     *
     * @return the total wins
     */
    public int getTotalWins() {
        return totalWins;
    }

    /**
     * Sets total wins.
     *
     * @param totalWins the total wins
     */
    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

}

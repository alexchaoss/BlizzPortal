
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _1v1 {

    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("leagueName")
    @Expose
    private Object leagueName;
    @SerializedName("totalGames")
    @Expose
    private int totalGames;
    @SerializedName("totalWins")
    @Expose
    private int totalWins;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Object getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(Object leagueName) {
        this.leagueName = leagueName;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

}

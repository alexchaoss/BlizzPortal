package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Games.
 */
public class Games {

    @SerializedName("played")
    @Expose
    private int played;
    @SerializedName("won")
    @Expose
    private int won;

    /**
     * Gets played.
     *
     * @return the played
     */
    public int getPlayed() {
        return played;
    }

    /**
     * Sets played.
     *
     * @param played the played
     */
    public void setPlayed(int played) {
        this.played = played;
    }

    /**
     * Gets won.
     *
     * @return the won
     */
    public int getWon() {
        return won;
    }

    /**
     * Sets won.
     *
     * @param won the won
     */
    public void setWon(int won) {
        this.won = won;
    }

}

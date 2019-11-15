
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Games_ {

    @SerializedName("played")
    @Expose
    private int played;
    @SerializedName("won")
    @Expose
    private int won;

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

}

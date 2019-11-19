package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Miscellaneous {

    @SerializedName("turretsDestroyed")
    @Expose
    private int turretsDestroyed;

    public int getTurretsDestroyed() {
        return turretsDestroyed;
    }

    public void setTurretsDestroyed(int turretsDestroyed) {
        this.turretsDestroyed = turretsDestroyed;
    }

}

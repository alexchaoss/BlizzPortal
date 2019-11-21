package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Miscellaneous {

    @SerializedName("teleporterPadsDestroyed")
    @Expose
    private double teleporterPadsDestroyed;
    @SerializedName("turretsDestroyed")
    @Expose
    private double turretsDestroyed;

    public double getTeleporterPadsDestroyed() {
        return teleporterPadsDestroyed;
    }

    public void setTeleporterPadsDestroyed(double teleporterPadsDestroyed) {
        this.teleporterPadsDestroyed = teleporterPadsDestroyed;
    }

    public double getTurretsDestroyed() {
        return turretsDestroyed;
    }

    public void setTurretsDestroyed(double turretsDestroyed) {
        this.turretsDestroyed = turretsDestroyed;
    }

}


package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Miscellaneous {

    @SerializedName("teleporterPadsDestroyed")
    @Expose
    private int teleporterPadsDestroyed;
    @SerializedName("turretsDestroyed")
    @Expose
    private int turretsDestroyed;

    public int getTeleporterPadsDestroyed() {
        return teleporterPadsDestroyed;
    }

    public void setTeleporterPadsDestroyed(int teleporterPadsDestroyed) {
        this.teleporterPadsDestroyed = teleporterPadsDestroyed;
    }

    public int getTurretsDestroyed() {
        return turretsDestroyed;
    }

    public void setTurretsDestroyed(int turretsDestroyed) {
        this.turretsDestroyed = turretsDestroyed;
    }

}

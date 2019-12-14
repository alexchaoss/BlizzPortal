package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

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

    public HashMap<String, String> getMisc() {
        HashMap<String, String> miscList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        miscList.put("TELEPORTER PADS DESTROYED", formatter.format(teleporterPadsDestroyed));
        miscList.put("TURRETS DESTROYED", formatter.format(turretsDestroyed));

        return miscList;
    }
}

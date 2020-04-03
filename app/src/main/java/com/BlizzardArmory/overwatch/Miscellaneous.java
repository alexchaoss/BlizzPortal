package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Miscellaneous.
 */
public class Miscellaneous {

    @SerializedName("teleporterPadsDestroyed")
    @Expose
    private double teleporterPadsDestroyed;
    @SerializedName("turretsDestroyed")
    @Expose
    private double turretsDestroyed;

    /**
     * Gets teleporter pads destroyed.
     *
     * @return the teleporter pads destroyed
     */
    public double getTeleporterPadsDestroyed() {
        return teleporterPadsDestroyed;
    }

    /**
     * Sets teleporter pads destroyed.
     *
     * @param teleporterPadsDestroyed the teleporter pads destroyed
     */
    public void setTeleporterPadsDestroyed(double teleporterPadsDestroyed) {
        this.teleporterPadsDestroyed = teleporterPadsDestroyed;
    }

    /**
     * Gets turrets destroyed.
     *
     * @return the turrets destroyed
     */
    public double getTurretsDestroyed() {
        return turretsDestroyed;
    }

    /**
     * Sets turrets destroyed.
     *
     * @param turretsDestroyed the turrets destroyed
     */
    public void setTurretsDestroyed(double turretsDestroyed) {
        this.turretsDestroyed = turretsDestroyed;
    }

    /**
     * Gets misc.
     *
     * @return the misc
     */
    public HashMap<String, String> getMisc() {
        HashMap<String, String> miscList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        miscList.put("TELEPORTER PADS DESTROYED", formatter.format(teleporterPadsDestroyed));
        miscList.put("TURRETS DESTROYED", formatter.format(turretsDestroyed));

        return miscList;
    }
}

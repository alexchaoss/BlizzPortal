
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Earned.
 */
public class Earned {

    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("startTime")
    @Expose
    private int startTime;

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

}

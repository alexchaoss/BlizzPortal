
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Earned {

    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("startTime")
    @Expose
    private int startTime;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

}

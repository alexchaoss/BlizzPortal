package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Links.
 */
public class Links {

    @SerializedName("self")
    @Expose
    private Self self;

    /**
     * Gets self.
     *
     * @return the self
     */
    public Self getSelf() {
        return self;
    }

    /**
     * Sets self.
     *
     * @param self the self
     */
    public void setSelf(Self self) {
        this.self = self;
    }

}

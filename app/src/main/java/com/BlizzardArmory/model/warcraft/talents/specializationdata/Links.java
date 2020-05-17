package com.BlizzardArmory.model.warcraft.talents.specializationdata;

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
     * No args constructor for use in serialization
     */
    public Links() {
    }

    /**
     * Instantiates a new Links.
     *
     * @param self the self
     */
    public Links(Self self) {
        super();
        this.self = self;
    }

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

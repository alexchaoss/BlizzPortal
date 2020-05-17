package com.BlizzardArmory.model.diablo.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Attributes.
 */
public class Attributes {


    @SerializedName("other")
    @Expose
    private List<Object> other;
    @SerializedName("primary")
    @Expose
    private List<Primary> primary;
    @SerializedName("secondary")
    @Expose
    private List<Secondary> secondary;

    /**
     * Gets other.
     *
     * @return the other
     */
    public List<Object> getOther() {
        return other;
    }

    /**
     * Gets primary.
     *
     * @return the primary
     */
    public List<Primary> getPrimary() {
        return primary;
    }

    /**
     * Gets secondary.
     *
     * @return the secondary
     */
    public List<Secondary> getSecondary() {
        return secondary;
    }

}

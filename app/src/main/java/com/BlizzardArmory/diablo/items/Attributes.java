package com.BlizzardArmory.diablo.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Attributes.
 */
public class Attributes {

    @SerializedName("primary")
    @Expose
    private List<String> primary = null;
    @SerializedName("secondary")
    @Expose
    private List<String> secondary = null;

    /**
     * Gets primary.
     *
     * @return the primary
     */
    public List<String> getPrimary() {
        return primary;
    }

    /**
     * Sets primary.
     *
     * @param primary the primary
     */
    public void setPrimary(List<String> primary) {
        this.primary = primary;
    }

    /**
     * Gets secondary.
     *
     * @return the secondary
     */
    public List<String> getSecondary() {
        return secondary;
    }

    /**
     * Sets secondary.
     *
     * @param secondary the secondary
     */
    public void setSecondary(List<String> secondary) {
        this.secondary = secondary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("primary", primary).append("secondary", secondary).toString();
    }

}

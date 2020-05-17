package com.BlizzardArmory.model.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Skills.
 */
public class Skills {

    @SerializedName("active")
    @Expose
    private List<Active> active = null;
    @SerializedName("passive")
    @Expose
    private List<Passive> passive = null;

    /**
     * Gets active.
     *
     * @return the active
     */
    public List<Active> getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(List<Active> active) {
        this.active = active;
    }

    /**
     * Gets passive.
     *
     * @return the passive
     */
    public List<Passive> getPassive() {
        return passive;
    }

    /**
     * Sets passive.
     *
     * @param passive the passive
     */
    public void setPassive(List<Passive> passive) {
        this.passive = passive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("active", active).append("passive", passive).toString();
    }

}

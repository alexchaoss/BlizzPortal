package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Death.
 */
public class Death {

    @SerializedName("killer")
    @Expose
    private Integer killer;
    @SerializedName("time")
    @Expose
    private Integer time;

    /**
     * Gets killer.
     *
     * @return the killer
     */
    public Integer getKiller() {
        return killer;
    }

    /**
     * Sets killer.
     *
     * @param killer the killer
     */
    public void setKiller(Integer killer) {
        this.killer = killer;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("killer", killer).append("time", time).toString();
    }

}
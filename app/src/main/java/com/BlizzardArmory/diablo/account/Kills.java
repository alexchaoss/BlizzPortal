package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Kills.
 */
public class Kills {

    @SerializedName("monsters")
    @Expose
    private Integer monsters;
    @SerializedName("elites")
    @Expose
    private Integer elites;
    @SerializedName("hardcoreMonsters")
    @Expose
    private Integer hardcoreMonsters;

    /**
     * Gets monsters.
     *
     * @return the monsters
     */
    public Integer getMonsters() {
        return monsters;
    }

    /**
     * Sets monsters.
     *
     * @param monsters the monsters
     */
    public void setMonsters(Integer monsters) {
        this.monsters = monsters;
    }

    /**
     * Gets elites.
     *
     * @return the elites
     */
    public Integer getElites() {
        return elites;
    }

    /**
     * Sets elites.
     *
     * @param elites the elites
     */
    public void setElites(Integer elites) {
        this.elites = elites;
    }

    /**
     * Gets hardcore monsters.
     *
     * @return the hardcore monsters
     */
    public Integer getHardcoreMonsters() {
        return hardcoreMonsters;
    }

    /**
     * Sets hardcore monsters.
     *
     * @param hardcoreMonsters the hardcore monsters
     */
    public void setHardcoreMonsters(Integer hardcoreMonsters) {
        this.hardcoreMonsters = hardcoreMonsters;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("monsters", monsters).append("elites", elites).append("hardcoreMonsters", hardcoreMonsters).toString();
    }

}

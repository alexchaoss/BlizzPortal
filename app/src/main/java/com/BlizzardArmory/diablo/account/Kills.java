package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    public Integer getMonsters() {
        return monsters;
    }

    public void setMonsters(Integer monsters) {
        this.monsters = monsters;
    }

    public Integer getElites() {
        return elites;
    }

    public void setElites(Integer elites) {
        this.elites = elites;
    }

    public Integer getHardcoreMonsters() {
        return hardcoreMonsters;
    }

    public void setHardcoreMonsters(Integer hardcoreMonsters) {
        this.hardcoreMonsters = hardcoreMonsters;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("monsters", monsters).append("elites", elites).append("hardcoreMonsters", hardcoreMonsters).toString();
    }

}

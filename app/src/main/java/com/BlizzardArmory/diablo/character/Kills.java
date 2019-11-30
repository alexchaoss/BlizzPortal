package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Kills {

    @SerializedName("elites")
    @Expose
    private Integer elites;

    public Integer getElites() {
        return elites;
    }

    public void setElites(Integer elites) {
        this.elites = elites;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("elites", elites).toString();
    }

}
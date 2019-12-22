package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TimePlayed {

    @SerializedName("demon-hunter")
    @Expose
    private Double demonHunter;
    @SerializedName("barbarian")
    @Expose
    private Double barbarian;
    @SerializedName("witch-doctor")
    @Expose
    private Double witchDoctor;
    @SerializedName("necromancer")
    @Expose
    private Double necromancer;
    @SerializedName("wizard")
    @Expose
    private Double wizard;
    @SerializedName("monk")
    @Expose
    private Double monk;
    @SerializedName("crusader")
    @Expose
    private Double crusader;

    public Double getDemonHunter() {
        return demonHunter;
    }

    public void setDemonHunter(Double demonHunter) {
        this.demonHunter = demonHunter;
    }

    public Double getBarbarian() {
        return barbarian;
    }

    public void setBarbarian(Double barbarian) {
        this.barbarian = barbarian;
    }

    public Double getWitchDoctor() {
        return witchDoctor;
    }

    public void setWitchDoctor(Double witchDoctor) {
        this.witchDoctor = witchDoctor;
    }

    public Double getNecromancer() {
        return necromancer;
    }

    public void setNecromancer(Double necromancer) {
        this.necromancer = necromancer;
    }

    public Double getWizard() {
        return wizard;
    }

    public void setWizard(Double wizard) {
        this.wizard = wizard;
    }

    public Double getMonk() {
        return monk;
    }

    public void setMonk(Double monk) {
        this.monk = monk;
    }

    public Double getCrusader() {
        return crusader;
    }

    public void setCrusader(Double crusader) {
        this.crusader = crusader;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("demonHunter", demonHunter).append("barbarian", barbarian).append("witchDoctor", witchDoctor).append("necromancer", necromancer).append("wizard", wizard).append("monk", monk).append("crusader", crusader).toString();
    }

}
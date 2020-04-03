package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Time played.
 */
public class TimePlayed {

    @SerializedName("demon-hunter")
    @Expose
    private Double demonHunter = 0.0;
    @SerializedName("barbarian")
    @Expose
    private Double barbarian = 0.0;
    @SerializedName("witch-doctor")
    @Expose
    private Double witchDoctor = 0.0;
    @SerializedName("necromancer")
    @Expose
    private Double necromancer = 0.0;
    @SerializedName("wizard")
    @Expose
    private Double wizard = 0.0;
    @SerializedName("monk")
    @Expose
    private Double monk = 0.0;
    @SerializedName("crusader")
    @Expose
    private Double crusader = 0.0;

    /**
     * Gets demon hunter.
     *
     * @return the demon hunter
     */
    public Double getDemonHunter() {
        return demonHunter;
    }

    /**
     * Sets demon hunter.
     *
     * @param demonHunter the demon hunter
     */
    public void setDemonHunter(Double demonHunter) {
        this.demonHunter = demonHunter;
    }

    /**
     * Gets barbarian.
     *
     * @return the barbarian
     */
    public Double getBarbarian() {
        return barbarian;
    }

    /**
     * Sets barbarian.
     *
     * @param barbarian the barbarian
     */
    public void setBarbarian(Double barbarian) {
        this.barbarian = barbarian;
    }

    /**
     * Gets witch doctor.
     *
     * @return the witch doctor
     */
    public Double getWitchDoctor() {
        return witchDoctor;
    }

    /**
     * Sets witch doctor.
     *
     * @param witchDoctor the witch doctor
     */
    public void setWitchDoctor(Double witchDoctor) {
        this.witchDoctor = witchDoctor;
    }

    /**
     * Gets necromancer.
     *
     * @return the necromancer
     */
    public Double getNecromancer() {
        return necromancer;
    }

    /**
     * Sets necromancer.
     *
     * @param necromancer the necromancer
     */
    public void setNecromancer(Double necromancer) {
        this.necromancer = necromancer;
    }

    /**
     * Gets wizard.
     *
     * @return the wizard
     */
    public Double getWizard() {
        return wizard;
    }

    /**
     * Sets wizard.
     *
     * @param wizard the wizard
     */
    public void setWizard(Double wizard) {
        this.wizard = wizard;
    }

    /**
     * Gets monk.
     *
     * @return the monk
     */
    public Double getMonk() {
        return monk;
    }

    /**
     * Sets monk.
     *
     * @param monk the monk
     */
    public void setMonk(Double monk) {
        this.monk = monk;
    }

    /**
     * Gets crusader.
     *
     * @return the crusader
     */
    public Double getCrusader() {
        return crusader;
    }

    /**
     * Sets crusader.
     *
     * @param crusader the crusader
     */
    public void setCrusader(Double crusader) {
        this.crusader = crusader;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("demonHunter", demonHunter).append("barbarian", barbarian).append("witchDoctor", witchDoctor).append("necromancer", necromancer).append("wizard", wizard).append("monk", monk).append("crusader", crusader).toString();
    }

}
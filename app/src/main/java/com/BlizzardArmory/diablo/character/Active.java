package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Active.
 */
public class Active {

    @SerializedName("skill")
    @Expose
    private Skill skill;
    @SerializedName("rune")
    @Expose
    private Rune rune;

    /**
     * Gets skill.
     *
     * @return the skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets skill.
     *
     * @param skill the skill
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * Gets rune.
     *
     * @return the rune
     */
    public Rune getRune() {
        return rune;
    }

    /**
     * Sets rune.
     *
     * @param rune the rune
     */
    public void setRune(Rune rune) {
        this.rune = rune;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("skill", skill).append("rune", rune).toString();
    }

}
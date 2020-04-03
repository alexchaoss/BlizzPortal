package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Passive.
 */
public class Passive {

    @SerializedName("skill")
    @Expose
    private Skill skill;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("skill", skill).toString();
    }

}
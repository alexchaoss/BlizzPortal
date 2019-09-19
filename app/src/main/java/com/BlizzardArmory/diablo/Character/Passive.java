package com.BlizzardArmory.diablo.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Passive {

    @SerializedName("skill")
    @Expose
    private Skill skill;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("skill", skill).toString();
    }

}
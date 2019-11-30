package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Active {

    @SerializedName("skill")
    @Expose
    private Skill skill;
    @SerializedName("rune")
    @Expose
    private Rune rune;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("skill", skill).append("rune", rune).toString();
    }

}
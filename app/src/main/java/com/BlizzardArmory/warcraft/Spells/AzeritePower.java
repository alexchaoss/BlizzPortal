package com.BlizzardArmory.warcraft.Spells;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AzeritePower {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("spellId")
    @Expose
    private Integer spellId;
    @SerializedName("bonusListId")
    @Expose
    private Integer bonusListId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getSpellId() {
        return spellId;
    }

    public void setSpellId(Integer spellId) {
        this.spellId = spellId;
    }

    public Integer getBonusListId() {
        return bonusListId;
    }

    public void setBonusListId(Integer bonusListId) {
        this.bonusListId = bonusListId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("tier", tier).append("spellId", spellId).append("bonusListId", bonusListId).toString();
    }

}
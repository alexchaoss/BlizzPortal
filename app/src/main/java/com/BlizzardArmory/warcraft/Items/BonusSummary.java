package com.BlizzardArmory.warcraft.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class BonusSummary {

    @SerializedName("defaultBonusLists")
    @Expose
    private List<Object> defaultBonusLists = null;
    @SerializedName("chanceBonusLists")
    @Expose
    private List<Object> chanceBonusLists = null;
    @SerializedName("bonusChances")
    @Expose
    private List<Object> bonusChances = null;

    public List<Object> getDefaultBonusLists() {
        return defaultBonusLists;
    }

    public void setDefaultBonusLists(List<Object> defaultBonusLists) {
        this.defaultBonusLists = defaultBonusLists;
    }

    public List<Object> getChanceBonusLists() {
        return chanceBonusLists;
    }

    public void setChanceBonusLists(List<Object> chanceBonusLists) {
        this.chanceBonusLists = chanceBonusLists;
    }

    public List<Object> getBonusChances() {
        return bonusChances;
    }

    public void setBonusChances(List<Object> bonusChances) {
        this.bonusChances = bonusChances;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("defaultBonusLists", defaultBonusLists).append("chanceBonusLists", chanceBonusLists).append("bonusChances", bonusChances).toString();
    }
}

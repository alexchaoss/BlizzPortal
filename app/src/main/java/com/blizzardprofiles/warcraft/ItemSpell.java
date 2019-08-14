package com.blizzardprofiles.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ItemSpell {

    @SerializedName("spellId")
    @Expose
    private Integer spellId;
    @SerializedName("nCharges")
    @Expose
    private Integer nCharges;
    @SerializedName("consumable")
    @Expose
    private Boolean consumable;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("trigger")
    @Expose
    private String trigger;
    @SerializedName("scaledDescription")
    @Expose
    private String scaledDescription;

    public Integer getSpellId() {
        return spellId;
    }

    public void setSpellId(Integer spellId) {
        this.spellId = spellId;
    }

    public Integer getNCharges() {
        return nCharges;
    }

    public void setNCharges(Integer nCharges) {
        this.nCharges = nCharges;
    }

    public Boolean getConsumable() {
        return consumable;
    }

    public void setConsumable(Boolean consumable) {
        this.consumable = consumable;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getScaledDescription() {
        return scaledDescription;
    }

    public void setScaledDescription(String scaledDescription) {
        this.scaledDescription = scaledDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("spellId", spellId).append("nCharges", nCharges).append("consumable", consumable).append("categoryId", categoryId).append("trigger", trigger).append("scaledDescription", scaledDescription).toString();
    }
}

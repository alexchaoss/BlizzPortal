package com.BlizzardArmory.warcraft.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Bonus {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("srcItemId")
    @Expose
    private Integer srcItemId;
    @SerializedName("requiredSkillId")
    @Expose
    private Integer requiredSkillId;
    @SerializedName("requiredSkillRank")
    @Expose
    private Integer requiredSkillRank;
    @SerializedName("minLevel")
    @Expose
    private Integer minLevel;
    @SerializedName("itemLevel")
    @Expose
    private Integer itemLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSrcItemId() {
        return srcItemId;
    }

    public void setSrcItemId(Integer srcItemId) {
        this.srcItemId = srcItemId;
    }

    public Integer getRequiredSkillId() {
        return requiredSkillId;
    }

    public void setRequiredSkillId(Integer requiredSkillId) {
        this.requiredSkillId = requiredSkillId;
    }

    public Integer getRequiredSkillRank() {
        return requiredSkillRank;
    }

    public void setRequiredSkillRank(Integer requiredSkillRank) {
        this.requiredSkillRank = requiredSkillRank;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("srcItemId", srcItemId).append("requiredSkillId", requiredSkillId).append("requiredSkillRank", requiredSkillRank).append("minLevel", minLevel).append("itemLevel", itemLevel).toString();
    }

}

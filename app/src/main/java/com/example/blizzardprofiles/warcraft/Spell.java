package com.example.blizzardprofiles.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Spell {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("castTime")
    @Expose
    private String castTime;
    @SerializedName("range")
    @Expose
    private String range;
    @SerializedName("powerCost")
    @Expose
    private String powerCost;
    @SerializedName("cooldown")
    @Expose
    private String cooldown;
    @SerializedName("subtext")
    @Expose
    private String subtext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(String powerCost) {
        this.powerCost = powerCost;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("description", description).append("castTime", castTime).toString();
    }

}
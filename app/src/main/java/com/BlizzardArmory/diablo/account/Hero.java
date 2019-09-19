package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hero {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("classSlug")
    @Expose
    private String classSlug;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("kills")
    @Expose
    private EliteKills kills;
    @SerializedName("paragonLevel")
    @Expose
    private Integer paragonLevel;
    @SerializedName("hardcore")
    @Expose
    private Boolean hardcore;
    @SerializedName("seasonal")
    @Expose
    private Boolean seasonal;
    @SerializedName("dead")
    @Expose
    private Boolean dead;
    @SerializedName("last-updated")
    @Expose
    private Integer lastUpdated;

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

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getClassSlug() {
        return classSlug;
    }

    public void setClassSlug(String classSlug) {
        this.classSlug = classSlug;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public EliteKills getKills() {
        return kills;
    }

    public void setKills(EliteKills kills) {
        this.kills = kills;
    }

    public Integer getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(Integer paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public Boolean getHardcore() {
        return hardcore;
    }

    public void setHardcore(Boolean hardcore) {
        this.hardcore = hardcore;
    }

    public Boolean getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(Boolean seasonal) {
        this.seasonal = seasonal;
    }

    public Boolean getDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("_class", _class).append("classSlug", classSlug).append("gender", gender).append("level", level).append("kills", kills).append("paragonLevel", paragonLevel).append("hardcore", hardcore).append("seasonal", seasonal).append("dead", dead).append("lastUpdated", lastUpdated).toString();
    }

}

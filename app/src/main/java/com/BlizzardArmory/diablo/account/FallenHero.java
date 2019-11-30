package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FallenHero {

    @SerializedName("heroId")
    @Expose
    private Integer heroId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("elites")
    @Expose
    private Integer elites;
    @SerializedName("hardcore")
    @Expose
    private Boolean hardcore;
    @SerializedName("death")
    @Expose
    private Death death;
    @SerializedName("gender")
    @Expose
    private Integer gender;

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getElites() {
        return elites;
    }

    public void setElites(Integer elites) {
        this.elites = elites;
    }

    public Boolean getHardcore() {
        return hardcore;
    }

    public void setHardcore(Boolean hardcore) {
        this.hardcore = hardcore;
    }

    public Death getDeath() {
        return death;
    }

    public void setDeath(Death death) {
        this.death = death;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("heroId", heroId).append("name", name).append("_class", _class).append("level", level).append("elites", elites).append("hardcore", hardcore).append("death", death).append("gender", gender).toString();
    }

}

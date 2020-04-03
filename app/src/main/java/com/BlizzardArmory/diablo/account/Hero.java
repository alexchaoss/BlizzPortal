package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Hero.
 */
public class Hero {

    @SerializedName("id")
    @Expose
    private long id;
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
    private long lastUpdated;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets class.
     *
     * @return the class
     */
    public String getClass_() {
        return _class;
    }

    /**
     * Sets class.
     *
     * @param _class the class
     */
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * Gets class slug.
     *
     * @return the class slug
     */
    public String getClassSlug() {
        return classSlug;
    }

    /**
     * Sets class slug.
     *
     * @param classSlug the class slug
     */
    public void setClassSlug(String classSlug) {
        this.classSlug = classSlug;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Gets kills.
     *
     * @return the kills
     */
    public EliteKills getKills() {
        return kills;
    }

    /**
     * Sets kills.
     *
     * @param kills the kills
     */
    public void setKills(EliteKills kills) {
        this.kills = kills;
    }

    /**
     * Gets paragon level.
     *
     * @return the paragon level
     */
    public Integer getParagonLevel() {
        return paragonLevel;
    }

    /**
     * Sets paragon level.
     *
     * @param paragonLevel the paragon level
     */
    public void setParagonLevel(Integer paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    /**
     * Gets hardcore.
     *
     * @return the hardcore
     */
    public Boolean getHardcore() {
        return hardcore;
    }

    /**
     * Sets hardcore.
     *
     * @param hardcore the hardcore
     */
    public void setHardcore(Boolean hardcore) {
        this.hardcore = hardcore;
    }

    /**
     * Gets seasonal.
     *
     * @return the seasonal
     */
    public Boolean getSeasonal() {
        return seasonal;
    }

    /**
     * Sets seasonal.
     *
     * @param seasonal the seasonal
     */
    public void setSeasonal(Boolean seasonal) {
        this.seasonal = seasonal;
    }

    /**
     * Gets dead.
     *
     * @return the dead
     */
    public Boolean getDead() {
        return dead;
    }

    /**
     * Sets dead.
     *
     * @param dead the dead
     */
    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    /**
     * Gets last updated.
     *
     * @return the last updated
     */
    public long getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets last updated.
     *
     * @param lastUpdated the last updated
     */
    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("_class", _class).append("classSlug", classSlug).append("gender", gender).append("level", level).append("kills", kills).append("paragonLevel", paragonLevel).append("hardcore", hardcore).append("seasonal", seasonal).append("dead", dead).append("lastUpdated", lastUpdated).toString();
    }

}

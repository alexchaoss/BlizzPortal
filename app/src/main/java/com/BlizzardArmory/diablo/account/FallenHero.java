package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Fallen hero.
 */
public class FallenHero {

    @SerializedName("heroId")
    @Expose
    private long heroId;
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

    /**
     * Gets hero id.
     *
     * @return the hero id
     */
    public double getHeroId() {
        return heroId;
    }

    /**
     * Sets hero id.
     *
     * @param heroId the hero id
     */
    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
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
     * Gets elites.
     *
     * @return the elites
     */
    public Integer getElites() {
        return elites;
    }

    /**
     * Sets elites.
     *
     * @param elites the elites
     */
    public void setElites(Integer elites) {
        this.elites = elites;
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
     * Gets death.
     *
     * @return the death
     */
    public Death getDeath() {
        return death;
    }

    /**
     * Sets death.
     *
     * @param death the death
     */
    public void setDeath(Death death) {
        this.death = death;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("heroId", heroId).append("name", name).append("_class", _class).append("level", level).append("elites", elites).append("hardcore", hardcore).append("death", death).append("gender", gender).toString();
    }

}

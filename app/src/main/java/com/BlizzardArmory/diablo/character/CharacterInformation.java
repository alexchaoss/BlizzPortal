package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Character information.
 */
public class CharacterInformation {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("paragonLevel")
    @Expose
    private int paragonLevel;
    @SerializedName("kills")
    @Expose
    private Kills kills;
    @SerializedName("hardcore")
    @Expose
    private Boolean hardcore;
    @SerializedName("seasonal")
    @Expose
    private Boolean seasonal;
    @SerializedName("seasonCreated")
    @Expose
    private int seasonCreated;
    @SerializedName("skills")
    @Expose
    private Skills skills;
    @SerializedName("itemsCharacter")
    @Expose
    private ItemsCharacter itemsCharacter;
    @SerializedName("followers")
    @Expose
    private Followers followers;
    @SerializedName("legendaryPowers")
    @Expose
    private List<LegendaryPower> legendaryPowers = null;
    @SerializedName("progression")
    @Expose
    private Progression progression;
    @SerializedName("alive")
    @Expose
    private Boolean alive;
    @SerializedName("lastUpdated")
    @Expose
    private int lastUpdated;
    @SerializedName("highestSoloRiftCompleted")
    @Expose
    private int highestSoloRiftCompleted;
    @SerializedName("stats")
    @Expose
    private Stats stats;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
     * Gets gender.
     *
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets paragon level.
     *
     * @return the paragon level
     */
    public int getParagonLevel() {
        return paragonLevel;
    }

    /**
     * Sets paragon level.
     *
     * @param paragonLevel the paragon level
     */
    public void setParagonLevel(int paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    /**
     * Gets kills.
     *
     * @return the kills
     */
    public Kills getKills() {
        return kills;
    }

    /**
     * Sets kills.
     *
     * @param kills the kills
     */
    public void setKills(Kills kills) {
        this.kills = kills;
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
     * Gets season created.
     *
     * @return the season created
     */
    public int getSeasonCreated() {
        return seasonCreated;
    }

    /**
     * Sets season created.
     *
     * @param seasonCreated the season created
     */
    public void setSeasonCreated(int seasonCreated) {
        this.seasonCreated = seasonCreated;
    }

    /**
     * Gets skills.
     *
     * @return the skills
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * Sets skills.
     *
     * @param skills the skills
     */
    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    /**
     * Gets items character.
     *
     * @return the items character
     */
    public ItemsCharacter getItemsCharacter() {
        return itemsCharacter;
    }

    /**
     * Sets items character.
     *
     * @param itemsCharacter the items character
     */
    public void setItemsCharacter(ItemsCharacter itemsCharacter) {
        this.itemsCharacter = itemsCharacter;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public Followers getFollowers() {
        return followers;
    }

    /**
     * Sets followers.
     *
     * @param followers the followers
     */
    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    /**
     * Gets legendary powers.
     *
     * @return the legendary powers
     */
    public List<LegendaryPower> getLegendaryPowers() {
        return legendaryPowers;
    }

    /**
     * Sets legendary powers.
     *
     * @param legendaryPowers the legendary powers
     */
    public void setLegendaryPowers(List<LegendaryPower> legendaryPowers) {
        this.legendaryPowers = legendaryPowers;
    }

    /**
     * Gets progression.
     *
     * @return the progression
     */
    public Progression getProgression() {
        return progression;
    }

    /**
     * Sets progression.
     *
     * @param progression the progression
     */
    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    /**
     * Gets alive.
     *
     * @return the alive
     */
    public Boolean getAlive() {
        return alive;
    }

    /**
     * Sets alive.
     *
     * @param alive the alive
     */
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets last updated.
     *
     * @return the last updated
     */
    public int getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets last updated.
     *
     * @param lastUpdated the last updated
     */
    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets highest solo rift completed.
     *
     * @return the highest solo rift completed
     */
    public int getHighestSoloRiftCompleted() {
        return highestSoloRiftCompleted;
    }

    /**
     * Sets highest solo rift completed.
     *
     * @param highestSoloRiftCompleted the highest solo rift completed
     */
    public void setHighestSoloRiftCompleted(int highestSoloRiftCompleted) {
        this.highestSoloRiftCompleted = highestSoloRiftCompleted;
    }

    /**
     * Gets stats.
     *
     * @return the stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Sets stats.
     *
     * @param stats the stats
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("_class", _class).append("gender", gender).append("level", level).append("paragonLevel", paragonLevel).append("kills", kills).append("hardcore", hardcore).append("seasonal", seasonal).append("seasonCreated", seasonCreated).append("skills", skills).append("itemsCharacter", itemsCharacter).append("followers", followers).append("legendaryPowers", legendaryPowers).append("progression", progression).append("alive", alive).append("lastUpdated", lastUpdated).append("highestSoloRiftCompleted", highestSoloRiftCompleted).append("stats", stats).toString();
    }

}

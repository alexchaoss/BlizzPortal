package com.BlizzardArmory.diablo.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class CharacterInformation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("paragonLevel")
    @Expose
    private Integer paragonLevel;
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
    private Integer seasonCreated;
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
    private Integer lastUpdated;
    @SerializedName("highestSoloRiftCompleted")
    @Expose
    private Integer highestSoloRiftCompleted;
    @SerializedName("stats")
    @Expose
    private Stats stats;

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

    public Integer getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(Integer paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public Kills getKills() {
        return kills;
    }

    public void setKills(Kills kills) {
        this.kills = kills;
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

    public Integer getSeasonCreated() {
        return seasonCreated;
    }

    public void setSeasonCreated(Integer seasonCreated) {
        this.seasonCreated = seasonCreated;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public ItemsCharacter getItemsCharacter() {
        return itemsCharacter;
    }

    public void setItemsCharacter(ItemsCharacter itemsCharacter) {
        this.itemsCharacter = itemsCharacter;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public List<LegendaryPower> getLegendaryPowers() {
        return legendaryPowers;
    }

    public void setLegendaryPowers(List<LegendaryPower> legendaryPowers) {
        this.legendaryPowers = legendaryPowers;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getHighestSoloRiftCompleted() {
        return highestSoloRiftCompleted;
    }

    public void setHighestSoloRiftCompleted(Integer highestSoloRiftCompleted) {
        this.highestSoloRiftCompleted = highestSoloRiftCompleted;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("_class", _class).append("gender", gender).append("level", level).append("paragonLevel", paragonLevel).append("kills", kills).append("hardcore", hardcore).append("seasonal", seasonal).append("seasonCreated", seasonCreated).append("skills", skills).append("itemsCharacter", itemsCharacter).append("followers", followers).append("legendaryPowers", legendaryPowers).append("progression", progression).append("alive", alive).append("lastUpdated", lastUpdated).append("highestSoloRiftCompleted", highestSoloRiftCompleted).append("stats", stats).toString();
    }

}

package com.example.blizzardprofiles.warcraft;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CharacterInformation {

    @SerializedName("lastModified")
    @Expose
    private long lastModified;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("realm")
    @Expose
    private String realm;
    @SerializedName("battlegroup")
    @Expose
    private String battlegroup;
    @SerializedName("class")
    @Expose
    private Integer _class;
    @SerializedName("race")
    @Expose
    private Integer race;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("achievementPoints")
    @Expose
    private Integer achievementPoints;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("calcClass")
    @Expose
    private String calcClass;
    @SerializedName("faction")
    @Expose
    private Integer faction;
    @SerializedName("talents")
    @Expose
    private List<Talent> talents = null;
    @SerializedName("totalHonorableKills")
    @Expose
    private Integer totalHonorableKills;

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Integer lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getBattlegroup() {
        return battlegroup;
    }

    public void setBattlegroup(String battlegroup) {
        this.battlegroup = battlegroup;
    }

    public Integer getClass_() {
        return _class;
    }

    public void setClass_(Integer _class) {
        this._class = _class;
    }

    public Integer getRace() {
        return race;
    }

    public void setRace(Integer race) {
        this.race = race;
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

    public Integer getAchievementPoints() {
        return achievementPoints;
    }

    public void setAchievementPoints(Integer achievementPoints) {
        this.achievementPoints = achievementPoints;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCalcClass() {
        return calcClass;
    }

    public void setCalcClass(String calcClass) {
        this.calcClass = calcClass;
    }

    public Integer getFaction() {
        return faction;
    }

    public void setFaction(Integer faction) {
        this.faction = faction;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    public Integer getTotalHonorableKills() {
        return totalHonorableKills;
    }

    public void setTotalHonorableKills(Integer totalHonorableKills) {
        this.totalHonorableKills = totalHonorableKills;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("lastModified", lastModified).append("name", name).append("realm", realm).append("battlegroup", battlegroup).append("_class", _class).append("race", race).append("gender", gender).append("level", level).append("achievementPoints", achievementPoints).append("thumbnail", thumbnail).append("calcClass", calcClass).append("faction", faction).append("talents", talents).append("totalHonorableKills", totalHonorableKills).toString();
    }

}


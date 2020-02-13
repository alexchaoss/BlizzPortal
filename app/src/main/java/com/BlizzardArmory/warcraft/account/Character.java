
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Character {

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
    private int _class;
    @SerializedName("race")
    @Expose
    private int race;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("achievementPoints")
    @Expose
    private int achievementPoints;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("spec")
    @Expose
    private Spec spec;
    @SerializedName("guild")
    @Expose
    private String guild;
    @SerializedName("guildRealm")
    @Expose
    private String guildRealm;
    @SerializedName("lastModified")
    @Expose
    private long lastModified;

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

    public String getClass_() {
        return ClassEnum.fromOrdinal(_class - 1).toString();
    }

    public void setClass_(int _class) {
        this._class = _class;
    }

    public int getRaceNumber() {
        return race;
    }

    public String getRace() {
        return RaceEnum.fromOrdinal(race - 1);
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAchievementPoints() {
        return achievementPoints;
    }

    public void setAchievementPoints(int achievementPoints) {
        this.achievementPoints = achievementPoints;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public String getGuildRealm() {
        return guildRealm;
    }

    public void setGuildRealm(String guildRealm) {
        this.guildRealm = guildRealm;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getFaction() {
        int tempNum = RaceEnum.fromOrdinalFaction(race - 1);
        if (tempNum == 0) {
            return "Alliance";
        } else if (tempNum == 1) {
            return "Horde";
        } else {
            return "Neutral";
        }
    }
}

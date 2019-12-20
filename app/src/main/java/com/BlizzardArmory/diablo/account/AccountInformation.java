package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AccountInformation {

    @SerializedName("battleTag")
    @Expose
    private String battleTag;
    @SerializedName("paragonLevel")
    @Expose
    private Integer paragonLevel;
    @SerializedName("paragonLevelHardcore")
    @Expose
    private Integer paragonLevelHardcore;
    @SerializedName("paragonLevelSeason")
    @Expose
    private Integer paragonLevelSeason;
    @SerializedName("paragonLevelSeasonHardcore")
    @Expose
    private Integer paragonLevelSeasonHardcore;
    @SerializedName("guildName")
    @Expose
    private String guildName;
    @SerializedName("heroes")
    @Expose
    private List<Hero> heroes = null;
    @SerializedName("lastHeroPlayed")
    @Expose
    private float lastHeroPlayed;
    @SerializedName("lastUpdated")
    @Expose
    private float lastUpdated;
    @SerializedName("kills")
    @Expose
    private Kills kills;
    @SerializedName("highestHardcoreLevel")
    @Expose
    private Integer highestHardcoreLevel;
    @SerializedName("timePlayed")
    @Expose
    private TimePlayed timePlayed;
    @SerializedName("progression")
    @Expose
    private Progression progression;
    @SerializedName("fallenHeroes")
    @Expose
    private List<FallenHero> fallenHeroes = null;
    @SerializedName("blacksmith")
    @Expose
    private Blacksmith blacksmith;
    @SerializedName("jeweler")
    @Expose
    private Jeweler jeweler;
    @SerializedName("mystic")
    @Expose
    private Mystic mystic;
    @SerializedName("jewelerSeason")
    @Expose
    private JewelerSeason jewelerSeason;
    @SerializedName("blacksmithSeason")
    @Expose
    private BlacksmithSeason blacksmithSeason;
    @SerializedName("mysticSeason")
    @Expose
    private MysticSeason mysticSeason;
    @SerializedName("blacksmithHardcore")
    @Expose
    private BlacksmithHardcore blacksmithHardcore;
    @SerializedName("jewelerHardcore")
    @Expose
    private JewelerHardcore jewelerHardcore;
    @SerializedName("mysticHardcore")
    @Expose
    private MysticHardcore mysticHardcore;
    @SerializedName("blacksmithSeasonHardcore")
    @Expose
    private BlacksmithSeasonHardcore blacksmithSeasonHardcore;
    @SerializedName("jewelerSeasonHardcore")
    @Expose
    private JewelerSeasonHardcore jewelerSeasonHardcore;
    @SerializedName("mysticSeasonHardcore")
    @Expose
    private MysticSeasonHardcore mysticSeasonHardcore;

    public String getBattleTag() {
        return battleTag;
    }

    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
    }

    public Integer getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(Integer paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public Integer getParagonLevelHardcore() {
        return paragonLevelHardcore;
    }

    public void setParagonLevelHardcore(Integer paragonLevelHardcore) {
        this.paragonLevelHardcore = paragonLevelHardcore;
    }

    public Integer getParagonLevelSeason() {
        return paragonLevelSeason;
    }

    public void setParagonLevelSeason(Integer paragonLevelSeason) {
        this.paragonLevelSeason = paragonLevelSeason;
    }

    public Integer getParagonLevelSeasonHardcore() {
        return paragonLevelSeasonHardcore;
    }

    public void setParagonLevelSeasonHardcore(Integer paragonLevelSeasonHardcore) {
        this.paragonLevelSeasonHardcore = paragonLevelSeasonHardcore;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public float getLastHeroPlayed() {
        return lastHeroPlayed;
    }

    public void setLastHeroPlayed(Integer lastHeroPlayed) {
        this.lastHeroPlayed = lastHeroPlayed;
    }

    public float getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Kills getKills() {
        return kills;
    }

    public void setKills(Kills kills) {
        this.kills = kills;
    }

    public Integer getHighestHardcoreLevel() {
        return highestHardcoreLevel;
    }

    public void setHighestHardcoreLevel(Integer highestHardcoreLevel) {
        this.highestHardcoreLevel = highestHardcoreLevel;
    }

    public TimePlayed getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(TimePlayed timePlayed) {
        this.timePlayed = timePlayed;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    public List<FallenHero> getFallenHeroes() {
        return fallenHeroes;
    }

    public void setFallenHeroes(List<FallenHero> fallenHeroes) {
        this.fallenHeroes = fallenHeroes;
    }

    public Blacksmith getBlacksmith() {
        return blacksmith;
    }

    public void setBlacksmith(Blacksmith blacksmith) {
        this.blacksmith = blacksmith;
    }

    public Jeweler getJeweler() {
        return jeweler;
    }

    public void setJeweler(Jeweler jeweler) {
        this.jeweler = jeweler;
    }

    public Mystic getMystic() {
        return mystic;
    }

    public void setMystic(Mystic mystic) {
        this.mystic = mystic;
    }

    public JewelerSeason getJewelerSeason() {
        return jewelerSeason;
    }

    public void setJewelerSeason(JewelerSeason jewelerSeason) {
        this.jewelerSeason = jewelerSeason;
    }

    public BlacksmithSeason getBlacksmithSeason() {
        return blacksmithSeason;
    }

    public void setBlacksmithSeason(BlacksmithSeason blacksmithSeason) {
        this.blacksmithSeason = blacksmithSeason;
    }

    public MysticSeason getMysticSeason() {
        return mysticSeason;
    }

    public void setMysticSeason(MysticSeason mysticSeason) {
        this.mysticSeason = mysticSeason;
    }

    public BlacksmithHardcore getBlacksmithHardcore() {
        return blacksmithHardcore;
    }

    public void setBlacksmithHardcore(BlacksmithHardcore blacksmithHardcore) {
        this.blacksmithHardcore = blacksmithHardcore;
    }

    public JewelerHardcore getJewelerHardcore() {
        return jewelerHardcore;
    }

    public void setJewelerHardcore(JewelerHardcore jewelerHardcore) {
        this.jewelerHardcore = jewelerHardcore;
    }

    public MysticHardcore getMysticHardcore() {
        return mysticHardcore;
    }

    public void setMysticHardcore(MysticHardcore mysticHardcore) {
        this.mysticHardcore = mysticHardcore;
    }

    public BlacksmithSeasonHardcore getBlacksmithSeasonHardcore() {
        return blacksmithSeasonHardcore;
    }

    public void setBlacksmithSeasonHardcore(BlacksmithSeasonHardcore blacksmithSeasonHardcore) {
        this.blacksmithSeasonHardcore = blacksmithSeasonHardcore;
    }

    public JewelerSeasonHardcore getJewelerSeasonHardcore() {
        return jewelerSeasonHardcore;
    }

    public void setJewelerSeasonHardcore(JewelerSeasonHardcore jewelerSeasonHardcore) {
        this.jewelerSeasonHardcore = jewelerSeasonHardcore;
    }

    public MysticSeasonHardcore getMysticSeasonHardcore() {
        return mysticSeasonHardcore;
    }

    public void setMysticSeasonHardcore(MysticSeasonHardcore mysticSeasonHardcore) {
        this.mysticSeasonHardcore = mysticSeasonHardcore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("battleTag", battleTag).append("paragonLevel", paragonLevel).append("paragonLevelHardcore", paragonLevelHardcore).append("paragonLevelSeason", paragonLevelSeason).append("paragonLevelSeasonHardcore", paragonLevelSeasonHardcore).append("guildName", guildName).append("heroes", heroes).append("lastHeroPlayed", lastHeroPlayed).append("lastUpdated", lastUpdated).append("kills", kills).append("highestHardcoreLevel", highestHardcoreLevel).append("timePlayed", timePlayed).append("progression", progression).append("fallenHeroes", fallenHeroes).append("blacksmith", blacksmith).append("jeweler", jeweler).append("mystic", mystic).append("jewelerSeason", jewelerSeason).append("blacksmithSeason", blacksmithSeason).append("mysticSeason", mysticSeason).append("blacksmithHardcore", blacksmithHardcore).append("jewelerHardcore", jewelerHardcore).append("mysticHardcore", mysticHardcore).append("blacksmithSeasonHardcore", blacksmithSeasonHardcore).append("jewelerSeasonHardcore", jewelerSeasonHardcore).append("mysticSeasonHardcore", mysticSeasonHardcore).toString();
    }

}
package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Account information.
 */
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
    private long lastHeroPlayed;
    @SerializedName("lastUpdated")
    @Expose
    private long lastUpdated;
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

    /**
     * Gets battle tag.
     *
     * @return the battle tag
     */
    public String getBattleTag() {
        return battleTag;
    }

    /**
     * Sets battle tag.
     *
     * @param battleTag the battle tag
     */
    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
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
     * Gets paragon level hardcore.
     *
     * @return the paragon level hardcore
     */
    public Integer getParagonLevelHardcore() {
        return paragonLevelHardcore;
    }

    /**
     * Sets paragon level hardcore.
     *
     * @param paragonLevelHardcore the paragon level hardcore
     */
    public void setParagonLevelHardcore(Integer paragonLevelHardcore) {
        this.paragonLevelHardcore = paragonLevelHardcore;
    }

    /**
     * Gets paragon level season.
     *
     * @return the paragon level season
     */
    public Integer getParagonLevelSeason() {
        return paragonLevelSeason;
    }

    /**
     * Sets paragon level season.
     *
     * @param paragonLevelSeason the paragon level season
     */
    public void setParagonLevelSeason(Integer paragonLevelSeason) {
        this.paragonLevelSeason = paragonLevelSeason;
    }

    /**
     * Gets paragon level season hardcore.
     *
     * @return the paragon level season hardcore
     */
    public Integer getParagonLevelSeasonHardcore() {
        return paragonLevelSeasonHardcore;
    }

    /**
     * Sets paragon level season hardcore.
     *
     * @param paragonLevelSeasonHardcore the paragon level season hardcore
     */
    public void setParagonLevelSeasonHardcore(Integer paragonLevelSeasonHardcore) {
        this.paragonLevelSeasonHardcore = paragonLevelSeasonHardcore;
    }

    /**
     * Gets guild name.
     *
     * @return the guild name
     */
    public String getGuildName() {
        return guildName;
    }

    /**
     * Sets guild name.
     *
     * @param guildName the guild name
     */
    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    /**
     * Gets heroes.
     *
     * @return the heroes
     */
    public List<Hero> getHeroes() {
        return heroes;
    }

    /**
     * Sets heroes.
     *
     * @param heroes the heroes
     */
    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    /**
     * Gets last hero played.
     *
     * @return the last hero played
     */
    public long getLastHeroPlayed() {
        return lastHeroPlayed;
    }

    /**
     * Sets last hero played.
     *
     * @param lastHeroPlayed the last hero played
     */
    public void setLastHeroPlayed(Integer lastHeroPlayed) {
        this.lastHeroPlayed = lastHeroPlayed;
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
     * Gets highest hardcore level.
     *
     * @return the highest hardcore level
     */
    public Integer getHighestHardcoreLevel() {
        return highestHardcoreLevel;
    }

    /**
     * Sets highest hardcore level.
     *
     * @param highestHardcoreLevel the highest hardcore level
     */
    public void setHighestHardcoreLevel(Integer highestHardcoreLevel) {
        this.highestHardcoreLevel = highestHardcoreLevel;
    }

    /**
     * Gets time played.
     *
     * @return the time played
     */
    public TimePlayed getTimePlayed() {
        return timePlayed;
    }

    /**
     * Sets time played.
     *
     * @param timePlayed the time played
     */
    public void setTimePlayed(TimePlayed timePlayed) {
        this.timePlayed = timePlayed;
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
     * Gets fallen heroes.
     *
     * @return the fallen heroes
     */
    public List<FallenHero> getFallenHeroes() {
        return fallenHeroes;
    }

    /**
     * Sets fallen heroes.
     *
     * @param fallenHeroes the fallen heroes
     */
    public void setFallenHeroes(List<FallenHero> fallenHeroes) {
        this.fallenHeroes = fallenHeroes;
    }

    /**
     * Gets blacksmith.
     *
     * @return the blacksmith
     */
    public Blacksmith getBlacksmith() {
        return blacksmith;
    }

    /**
     * Sets blacksmith.
     *
     * @param blacksmith the blacksmith
     */
    public void setBlacksmith(Blacksmith blacksmith) {
        this.blacksmith = blacksmith;
    }

    /**
     * Gets jeweler.
     *
     * @return the jeweler
     */
    public Jeweler getJeweler() {
        return jeweler;
    }

    /**
     * Sets jeweler.
     *
     * @param jeweler the jeweler
     */
    public void setJeweler(Jeweler jeweler) {
        this.jeweler = jeweler;
    }

    /**
     * Gets mystic.
     *
     * @return the mystic
     */
    public Mystic getMystic() {
        return mystic;
    }

    /**
     * Sets mystic.
     *
     * @param mystic the mystic
     */
    public void setMystic(Mystic mystic) {
        this.mystic = mystic;
    }

    /**
     * Gets jeweler season.
     *
     * @return the jeweler season
     */
    public JewelerSeason getJewelerSeason() {
        return jewelerSeason;
    }

    /**
     * Sets jeweler season.
     *
     * @param jewelerSeason the jeweler season
     */
    public void setJewelerSeason(JewelerSeason jewelerSeason) {
        this.jewelerSeason = jewelerSeason;
    }

    /**
     * Gets blacksmith season.
     *
     * @return the blacksmith season
     */
    public BlacksmithSeason getBlacksmithSeason() {
        return blacksmithSeason;
    }

    /**
     * Sets blacksmith season.
     *
     * @param blacksmithSeason the blacksmith season
     */
    public void setBlacksmithSeason(BlacksmithSeason blacksmithSeason) {
        this.blacksmithSeason = blacksmithSeason;
    }

    /**
     * Gets mystic season.
     *
     * @return the mystic season
     */
    public MysticSeason getMysticSeason() {
        return mysticSeason;
    }

    /**
     * Sets mystic season.
     *
     * @param mysticSeason the mystic season
     */
    public void setMysticSeason(MysticSeason mysticSeason) {
        this.mysticSeason = mysticSeason;
    }

    /**
     * Gets blacksmith hardcore.
     *
     * @return the blacksmith hardcore
     */
    public BlacksmithHardcore getBlacksmithHardcore() {
        return blacksmithHardcore;
    }

    /**
     * Sets blacksmith hardcore.
     *
     * @param blacksmithHardcore the blacksmith hardcore
     */
    public void setBlacksmithHardcore(BlacksmithHardcore blacksmithHardcore) {
        this.blacksmithHardcore = blacksmithHardcore;
    }

    /**
     * Gets jeweler hardcore.
     *
     * @return the jeweler hardcore
     */
    public JewelerHardcore getJewelerHardcore() {
        return jewelerHardcore;
    }

    /**
     * Sets jeweler hardcore.
     *
     * @param jewelerHardcore the jeweler hardcore
     */
    public void setJewelerHardcore(JewelerHardcore jewelerHardcore) {
        this.jewelerHardcore = jewelerHardcore;
    }

    /**
     * Gets mystic hardcore.
     *
     * @return the mystic hardcore
     */
    public MysticHardcore getMysticHardcore() {
        return mysticHardcore;
    }

    /**
     * Sets mystic hardcore.
     *
     * @param mysticHardcore the mystic hardcore
     */
    public void setMysticHardcore(MysticHardcore mysticHardcore) {
        this.mysticHardcore = mysticHardcore;
    }

    /**
     * Gets blacksmith season hardcore.
     *
     * @return the blacksmith season hardcore
     */
    public BlacksmithSeasonHardcore getBlacksmithSeasonHardcore() {
        return blacksmithSeasonHardcore;
    }

    /**
     * Sets blacksmith season hardcore.
     *
     * @param blacksmithSeasonHardcore the blacksmith season hardcore
     */
    public void setBlacksmithSeasonHardcore(BlacksmithSeasonHardcore blacksmithSeasonHardcore) {
        this.blacksmithSeasonHardcore = blacksmithSeasonHardcore;
    }

    /**
     * Gets jeweler season hardcore.
     *
     * @return the jeweler season hardcore
     */
    public JewelerSeasonHardcore getJewelerSeasonHardcore() {
        return jewelerSeasonHardcore;
    }

    /**
     * Sets jeweler season hardcore.
     *
     * @param jewelerSeasonHardcore the jeweler season hardcore
     */
    public void setJewelerSeasonHardcore(JewelerSeasonHardcore jewelerSeasonHardcore) {
        this.jewelerSeasonHardcore = jewelerSeasonHardcore;
    }

    /**
     * Gets mystic season hardcore.
     *
     * @return the mystic season hardcore
     */
    public MysticSeasonHardcore getMysticSeasonHardcore() {
        return mysticSeasonHardcore;
    }

    /**
     * Sets mystic season hardcore.
     *
     * @param mysticSeasonHardcore the mystic season hardcore
     */
    public void setMysticSeasonHardcore(MysticSeasonHardcore mysticSeasonHardcore) {
        this.mysticSeasonHardcore = mysticSeasonHardcore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("battleTag", battleTag).append("paragonLevel", paragonLevel).append("paragonLevelHardcore", paragonLevelHardcore).append("paragonLevelSeason", paragonLevelSeason).append("paragonLevelSeasonHardcore", paragonLevelSeasonHardcore).append("guildName", guildName).append("heroes", heroes).append("lastHeroPlayed", lastHeroPlayed).append("lastUpdated", lastUpdated).append("kills", kills).append("highestHardcoreLevel", highestHardcoreLevel).append("timePlayed", timePlayed).append("progression", progression).append("fallenHeroes", fallenHeroes).append("blacksmith", blacksmith).append("jeweler", jeweler).append("mystic", mystic).append("jewelerSeason", jewelerSeason).append("blacksmithSeason", blacksmithSeason).append("mysticSeason", mysticSeason).append("blacksmithHardcore", blacksmithHardcore).append("jewelerHardcore", jewelerHardcore).append("mysticHardcore", mysticHardcore).append("blacksmithSeasonHardcore", blacksmithSeasonHardcore).append("jewelerSeasonHardcore", jewelerSeasonHardcore).append("mysticSeasonHardcore", mysticSeasonHardcore).toString();
    }

}
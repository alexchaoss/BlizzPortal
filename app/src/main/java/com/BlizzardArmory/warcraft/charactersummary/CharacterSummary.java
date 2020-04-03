package com.BlizzardArmory.warcraft.charactersummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Character summary.
 */
public class CharacterSummary {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("faction")
    @Expose
    private Faction faction;
    @SerializedName("race")
    @Expose
    private Race race;
    @SerializedName("character_class")
    @Expose
    private CharacterClass characterClass;
    @SerializedName("active_spec")
    @Expose
    private ActiveSpec activeSpec;
    @SerializedName("realm")
    @Expose
    private Realm realm;
    @SerializedName("guild")
    @Expose
    private Guild guild;
    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("experience")
    @Expose
    private int experience;
    @SerializedName("achievement_points")
    @Expose
    private int achievementPoints;
    @SerializedName("achievements")
    @Expose
    private Achievements achievements;
    @SerializedName("titles")
    @Expose
    private Titles titles;
    @SerializedName("pvp_summary")
    @Expose
    private PvpSummary pvpSummary;
    @SerializedName("raid_progression")
    @Expose
    private RaidProgression raidProgression;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("last_login_timestamp")
    @Expose
    private long lastLoginTimestamp;
    @SerializedName("average_item_level")
    @Expose
    private int averageItemLevel;
    @SerializedName("equipped_item_level")
    @Expose
    private int equippedItemLevel;
    @SerializedName("specializations")
    @Expose
    private Specializations specializations;
    @SerializedName("statistics")
    @Expose
    private Statistics statistics;
    @SerializedName("mythic_keystone_profile")
    @Expose
    private MythicKeystoneProfile mythicKeystoneProfile;
    @SerializedName("equipment")
    @Expose
    private Equipment equipment;
    @SerializedName("appearance")
    @Expose
    private Appearance appearance;
    @SerializedName("collections")
    @Expose
    private Collections collections;
    @SerializedName("active_title")
    @Expose
    private ActiveTitle activeTitle;
    @SerializedName("reputations")
    @Expose
    private Reputations reputations;

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

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
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets faction.
     *
     * @return the faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Sets faction.
     *
     * @param faction the faction
     */
    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    /**
     * Gets race.
     *
     * @return the race
     */
    public Race getRace() {
        return race;
    }

    /**
     * Sets race.
     *
     * @param race the race
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Gets character class.
     *
     * @return the character class
     */
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    /**
     * Sets character class.
     *
     * @param characterClass the character class
     */
    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    /**
     * Gets active spec.
     *
     * @return the active spec
     */
    public ActiveSpec getActiveSpec() {
        return activeSpec;
    }

    /**
     * Sets active spec.
     *
     * @param activeSpec the active spec
     */
    public void setActiveSpec(ActiveSpec activeSpec) {
        this.activeSpec = activeSpec;
    }

    /**
     * Gets realm.
     *
     * @return the realm
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Sets realm.
     *
     * @param realm the realm
     */
    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    /**
     * Gets guild.
     *
     * @return the guild
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * Sets guild.
     *
     * @param guild the guild
     */
    public void setGuild(Guild guild) {
        this.guild = guild;
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
     * Gets experience.
     *
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets experience.
     *
     * @param experience the experience
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Gets achievement points.
     *
     * @return the achievement points
     */
    public int getAchievementPoints() {
        return achievementPoints;
    }

    /**
     * Sets achievement points.
     *
     * @param achievementPoints the achievement points
     */
    public void setAchievementPoints(int achievementPoints) {
        this.achievementPoints = achievementPoints;
    }

    /**
     * Gets achievements.
     *
     * @return the achievements
     */
    public Achievements getAchievements() {
        return achievements;
    }

    /**
     * Sets achievements.
     *
     * @param achievements the achievements
     */
    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    /**
     * Gets titles.
     *
     * @return the titles
     */
    public Titles getTitles() {
        return titles;
    }

    /**
     * Sets titles.
     *
     * @param titles the titles
     */
    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    /**
     * Gets pvp summary.
     *
     * @return the pvp summary
     */
    public PvpSummary getPvpSummary() {
        return pvpSummary;
    }

    /**
     * Sets pvp summary.
     *
     * @param pvpSummary the pvp summary
     */
    public void setPvpSummary(PvpSummary pvpSummary) {
        this.pvpSummary = pvpSummary;
    }

    /**
     * Gets raid progression.
     *
     * @return the raid progression
     */
    public RaidProgression getRaidProgression() {
        return raidProgression;
    }

    /**
     * Sets raid progression.
     *
     * @param raidProgression the raid progression
     */
    public void setRaidProgression(RaidProgression raidProgression) {
        this.raidProgression = raidProgression;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * Gets last login timestamp.
     *
     * @return the last login timestamp
     */
    public long getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    /**
     * Sets last login timestamp.
     *
     * @param lastLoginTimestamp the last login timestamp
     */
    public void setLastLoginTimestamp(int lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    /**
     * Gets average item level.
     *
     * @return the average item level
     */
    public int getAverageItemLevel() {
        return averageItemLevel;
    }

    /**
     * Sets average item level.
     *
     * @param averageItemLevel the average item level
     */
    public void setAverageItemLevel(int averageItemLevel) {
        this.averageItemLevel = averageItemLevel;
    }

    /**
     * Gets equipped item level.
     *
     * @return the equipped item level
     */
    public int getEquippedItemLevel() {
        return equippedItemLevel;
    }

    /**
     * Sets equipped item level.
     *
     * @param equippedItemLevel the equipped item level
     */
    public void setEquippedItemLevel(int equippedItemLevel) {
        this.equippedItemLevel = equippedItemLevel;
    }

    /**
     * Gets specializations.
     *
     * @return the specializations
     */
    public Specializations getSpecializations() {
        return specializations;
    }

    /**
     * Sets specializations.
     *
     * @param specializations the specializations
     */
    public void setSpecializations(Specializations specializations) {
        this.specializations = specializations;
    }

    /**
     * Gets statistics.
     *
     * @return the statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Sets statistics.
     *
     * @param statistics the statistics
     */
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    /**
     * Gets mythic keystone profile.
     *
     * @return the mythic keystone profile
     */
    public MythicKeystoneProfile getMythicKeystoneProfile() {
        return mythicKeystoneProfile;
    }

    /**
     * Sets mythic keystone profile.
     *
     * @param mythicKeystoneProfile the mythic keystone profile
     */
    public void setMythicKeystoneProfile(MythicKeystoneProfile mythicKeystoneProfile) {
        this.mythicKeystoneProfile = mythicKeystoneProfile;
    }

    /**
     * Gets equipment.
     *
     * @return the equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * Sets equipment.
     *
     * @param equipment the equipment
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * Gets appearance.
     *
     * @return the appearance
     */
    public Appearance getAppearance() {
        return appearance;
    }

    /**
     * Sets appearance.
     *
     * @param appearance the appearance
     */
    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public Collections getCollections() {
        return collections;
    }

    /**
     * Sets collections.
     *
     * @param collections the collections
     */
    public void setCollections(Collections collections) {
        this.collections = collections;
    }

    /**
     * Gets active title.
     *
     * @return the active title
     */
    public ActiveTitle getActiveTitle() {
        return activeTitle;
    }

    /**
     * Sets active title.
     *
     * @param activeTitle the active title
     */
    public void setActiveTitle(ActiveTitle activeTitle) {
        this.activeTitle = activeTitle;
    }

    /**
     * Gets reputations.
     *
     * @return the reputations
     */
    public Reputations getReputations() {
        return reputations;
    }

    /**
     * Sets reputations.
     *
     * @param reputations the reputations
     */
    public void setReputations(Reputations reputations) {
        this.reputations = reputations;
    }

}

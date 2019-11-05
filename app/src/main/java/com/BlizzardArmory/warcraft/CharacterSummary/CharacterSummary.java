package com.BlizzardArmory.warcraft.CharacterSummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public ActiveSpec getActiveSpec() {
        return activeSpec;
    }

    public void setActiveSpec(ActiveSpec activeSpec) {
        this.activeSpec = activeSpec;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAchievementPoints() {
        return achievementPoints;
    }

    public void setAchievementPoints(int achievementPoints) {
        this.achievementPoints = achievementPoints;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    public Titles getTitles() {
        return titles;
    }

    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    public PvpSummary getPvpSummary() {
        return pvpSummary;
    }

    public void setPvpSummary(PvpSummary pvpSummary) {
        this.pvpSummary = pvpSummary;
    }

    public RaidProgression getRaidProgression() {
        return raidProgression;
    }

    public void setRaidProgression(RaidProgression raidProgression) {
        this.raidProgression = raidProgression;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public long getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(int lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public int getAverageItemLevel() {
        return averageItemLevel;
    }

    public void setAverageItemLevel(int averageItemLevel) {
        this.averageItemLevel = averageItemLevel;
    }

    public int getEquippedItemLevel() {
        return equippedItemLevel;
    }

    public void setEquippedItemLevel(int equippedItemLevel) {
        this.equippedItemLevel = equippedItemLevel;
    }

    public Specializations getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Specializations specializations) {
        this.specializations = specializations;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public MythicKeystoneProfile getMythicKeystoneProfile() {
        return mythicKeystoneProfile;
    }

    public void setMythicKeystoneProfile(MythicKeystoneProfile mythicKeystoneProfile) {
        this.mythicKeystoneProfile = mythicKeystoneProfile;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Collections getCollections() {
        return collections;
    }

    public void setCollections(Collections collections) {
        this.collections = collections;
    }

    public ActiveTitle getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(ActiveTitle activeTitle) {
        this.activeTitle = activeTitle;
    }

    public Reputations getReputations() {
        return reputations;
    }

    public void setReputations(Reputations reputations) {
        this.reputations = reputations;
    }

}

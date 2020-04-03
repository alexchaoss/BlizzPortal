
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Profile.
 */
public class Profile {

    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("snapshot")
    @Expose
    private Snapshot snapshot;
    @SerializedName("career")
    @Expose
    private Career career;
    @SerializedName("swarmLevels")
    @Expose
    private SwarmLevels swarmLevels;
    @SerializedName("campaign")
    @Expose
    private Campaign campaign;
    @SerializedName("categoryPointProgress")
    @Expose
    private List<CategoryPointProgress> categoryPointProgress = null;
    @SerializedName("achievementShowcase")
    @Expose
    private List<String> achievementShowcase = null;
    @SerializedName("earnedRewards")
    @Expose
    private List<EarnedReward> earnedRewards = null;
    @SerializedName("earnedAchievements")
    @Expose
    private List<EarnedAchievement> earnedAchievements = null;

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    /**
     * Gets snapshot.
     *
     * @return the snapshot
     */
    public Snapshot getSnapshot() {
        return snapshot;
    }

    /**
     * Sets snapshot.
     *
     * @param snapshot the snapshot
     */
    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * Gets career.
     *
     * @return the career
     */
    public Career getCareer() {
        return career;
    }

    /**
     * Sets career.
     *
     * @param career the career
     */
    public void setCareer(Career career) {
        this.career = career;
    }

    /**
     * Gets swarm levels.
     *
     * @return the swarm levels
     */
    public SwarmLevels getSwarmLevels() {
        return swarmLevels;
    }

    /**
     * Sets swarm levels.
     *
     * @param swarmLevels the swarm levels
     */
    public void setSwarmLevels(SwarmLevels swarmLevels) {
        this.swarmLevels = swarmLevels;
    }

    /**
     * Gets campaign.
     *
     * @return the campaign
     */
    public Campaign getCampaign() {
        return campaign;
    }

    /**
     * Sets campaign.
     *
     * @param campaign the campaign
     */
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    /**
     * Gets category point progress.
     *
     * @return the category point progress
     */
    public List<CategoryPointProgress> getCategoryPointProgress() {
        return categoryPointProgress;
    }

    /**
     * Sets category point progress.
     *
     * @param categoryPointProgress the category point progress
     */
    public void setCategoryPointProgress(List<CategoryPointProgress> categoryPointProgress) {
        this.categoryPointProgress = categoryPointProgress;
    }

    /**
     * Gets achievement showcase.
     *
     * @return the achievement showcase
     */
    public List<String> getAchievementShowcase() {
        return achievementShowcase;
    }

    /**
     * Sets achievement showcase.
     *
     * @param achievementShowcase the achievement showcase
     */
    public void setAchievementShowcase(List<String> achievementShowcase) {
        this.achievementShowcase = achievementShowcase;
    }

    /**
     * Gets earned rewards.
     *
     * @return the earned rewards
     */
    public List<EarnedReward> getEarnedRewards() {
        return earnedRewards;
    }

    /**
     * Sets earned rewards.
     *
     * @param earnedRewards the earned rewards
     */
    public void setEarnedRewards(List<EarnedReward> earnedRewards) {
        this.earnedRewards = earnedRewards;
    }

    /**
     * Gets earned achievements.
     *
     * @return the earned achievements
     */
    public List<EarnedAchievement> getEarnedAchievements() {
        return earnedAchievements;
    }

    /**
     * Sets earned achievements.
     *
     * @param earnedAchievements the earned achievements
     */
    public void setEarnedAchievements(List<EarnedAchievement> earnedAchievements) {
        this.earnedAchievements = earnedAchievements;
    }

}


package com.BlizzardArmory.starcraft.Profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public SwarmLevels getSwarmLevels() {
        return swarmLevels;
    }

    public void setSwarmLevels(SwarmLevels swarmLevels) {
        this.swarmLevels = swarmLevels;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public List<CategoryPointProgress> getCategoryPointProgress() {
        return categoryPointProgress;
    }

    public void setCategoryPointProgress(List<CategoryPointProgress> categoryPointProgress) {
        this.categoryPointProgress = categoryPointProgress;
    }

    public List<String> getAchievementShowcase() {
        return achievementShowcase;
    }

    public void setAchievementShowcase(List<String> achievementShowcase) {
        this.achievementShowcase = achievementShowcase;
    }

    public List<EarnedReward> getEarnedRewards() {
        return earnedRewards;
    }

    public void setEarnedRewards(List<EarnedReward> earnedRewards) {
        this.earnedRewards = earnedRewards;
    }

    public List<EarnedAchievement> getEarnedAchievements() {
        return earnedAchievements;
    }

    public void setEarnedAchievements(List<EarnedAchievement> earnedAchievements) {
        this.earnedAchievements = earnedAchievements;
    }

}

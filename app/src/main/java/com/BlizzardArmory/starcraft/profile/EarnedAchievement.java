
package com.BlizzardArmory.starcraft.profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarnedAchievement {

    @SerializedName("achievementId")
    @Expose
    private String achievementId;
    @SerializedName("completionDate")
    @Expose
    private float completionDate;
    @SerializedName("numCompletedAchievementsInSeries")
    @Expose
    private int numCompletedAchievementsInSeries;
    @SerializedName("totalAchievementsInSeries")
    @Expose
    private int totalAchievementsInSeries;
    @SerializedName("isComplete")
    @Expose
    private boolean isComplete;
    @SerializedName("inProgress")
    @Expose
    private boolean inProgress;
    @SerializedName("criteria")
    @Expose
    private List<Criterium> criteria = null;

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public float getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(int completionDate) {
        this.completionDate = completionDate;
    }

    public int getNumCompletedAchievementsInSeries() {
        return numCompletedAchievementsInSeries;
    }

    public void setNumCompletedAchievementsInSeries(int numCompletedAchievementsInSeries) {
        this.numCompletedAchievementsInSeries = numCompletedAchievementsInSeries;
    }

    public int getTotalAchievementsInSeries() {
        return totalAchievementsInSeries;
    }

    public void setTotalAchievementsInSeries(int totalAchievementsInSeries) {
        this.totalAchievementsInSeries = totalAchievementsInSeries;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public List<Criterium> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }

}

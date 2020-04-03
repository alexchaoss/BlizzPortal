
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Earned achievement.
 */
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

    /**
     * Gets achievement id.
     *
     * @return the achievement id
     */
    public String getAchievementId() {
        return achievementId;
    }

    /**
     * Sets achievement id.
     *
     * @param achievementId the achievement id
     */
    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * Gets completion date.
     *
     * @return the completion date
     */
    public float getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets completion date.
     *
     * @param completionDate the completion date
     */
    public void setCompletionDate(int completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Gets num completed achievements in series.
     *
     * @return the num completed achievements in series
     */
    public int getNumCompletedAchievementsInSeries() {
        return numCompletedAchievementsInSeries;
    }

    /**
     * Sets num completed achievements in series.
     *
     * @param numCompletedAchievementsInSeries the num completed achievements in series
     */
    public void setNumCompletedAchievementsInSeries(int numCompletedAchievementsInSeries) {
        this.numCompletedAchievementsInSeries = numCompletedAchievementsInSeries;
    }

    /**
     * Gets total achievements in series.
     *
     * @return the total achievements in series
     */
    public int getTotalAchievementsInSeries() {
        return totalAchievementsInSeries;
    }

    /**
     * Sets total achievements in series.
     *
     * @param totalAchievementsInSeries the total achievements in series
     */
    public void setTotalAchievementsInSeries(int totalAchievementsInSeries) {
        this.totalAchievementsInSeries = totalAchievementsInSeries;
    }

    /**
     * Is is complete boolean.
     *
     * @return the boolean
     */
    public boolean isIsComplete() {
        return isComplete;
    }

    /**
     * Sets is complete.
     *
     * @param isComplete the is complete
     */
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Is in progress boolean.
     *
     * @return the boolean
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * Sets in progress.
     *
     * @param inProgress the in progress
     */
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    /**
     * Gets criteria.
     *
     * @return the criteria
     */
    public List<Criterium> getCriteria() {
        return criteria;
    }

    /**
     * Sets criteria.
     *
     * @param criteria the criteria
     */
    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }

}

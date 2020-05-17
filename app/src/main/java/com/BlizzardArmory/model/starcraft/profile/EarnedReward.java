
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Earned reward.
 */
public class EarnedReward {

    @SerializedName("rewardId")
    @Expose
    private String rewardId;
    @SerializedName("achievementId")
    @Expose
    private String achievementId;
    @SerializedName("selected")
    @Expose
    private boolean selected;

    /**
     * Gets reward id.
     *
     * @return the reward id
     */
    public String getRewardId() {
        return rewardId;
    }

    /**
     * Sets reward id.
     *
     * @param rewardId the reward id
     */
    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

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
     * Is selected boolean.
     *
     * @return the boolean
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets selected.
     *
     * @param selected the selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

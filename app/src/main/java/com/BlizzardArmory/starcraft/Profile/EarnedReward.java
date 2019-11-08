
package com.BlizzardArmory.starcraft.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarnedReward {

    @SerializedName("rewardId")
    @Expose
    private String rewardId;
    @SerializedName("selected")
    @Expose
    private boolean selected;

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

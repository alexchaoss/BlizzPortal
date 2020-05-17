
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Category point progress.
 */
public class CategoryPointProgress {

    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("pointsEarned")
    @Expose
    private int pointsEarned;

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Sets category id.
     *
     * @param categoryId the category id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets points earned.
     *
     * @return the points earned
     */
    public int getPointsEarned() {
        return pointsEarned;
    }

    /**
     * Sets points earned.
     *
     * @param pointsEarned the points earned
     */
    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

}

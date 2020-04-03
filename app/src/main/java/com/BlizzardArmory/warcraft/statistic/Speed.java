package com.BlizzardArmory.warcraft.statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Speed.
 */
public class Speed {

    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("rating_bonus")
    @Expose
    private float ratingBonus;

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets rating bonus.
     *
     * @return the rating bonus
     */
    public float getRatingBonus() {
        return ratingBonus;
    }

    /**
     * Sets rating bonus.
     *
     * @param ratingBonus the rating bonus
     */
    public void setRatingBonus(float ratingBonus) {
        this.ratingBonus = ratingBonus;
    }

}
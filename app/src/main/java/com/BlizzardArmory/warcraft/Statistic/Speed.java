package com.BlizzardArmory.warcraft.Statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speed {

    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("rating_bonus")
    @Expose
    private float ratingBonus;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getRatingBonus() {
        return ratingBonus;
    }

    public void setRatingBonus(float ratingBonus) {
        this.ratingBonus = ratingBonus;
    }

}
package com.BlizzardArmory.warcraft.statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeleeHaste {

    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("rating_bonus")
    @Expose
    private float ratingBonus;
    @SerializedName("value")
    @Expose
    private float value;

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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
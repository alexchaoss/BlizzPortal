package com.BlizzardArmory.overwatch.quickplay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuickPlay {

    @SerializedName("quickPlayStats")
    @Expose
    private QuickPlayStats quickPlayStats;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("ratingIcon")
    @Expose
    private String ratingIcon;
    @SerializedName("ratings")
    @Expose
    private Object ratings;

    public QuickPlayStats getQuickPlayStats() {
        return quickPlayStats;
    }

    public void setQuickPlayStats(QuickPlayStats quickPlayStats) {
        this.quickPlayStats = quickPlayStats;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatingIcon() {
        return ratingIcon;
    }

    public void setRatingIcon(String ratingIcon) {
        this.ratingIcon = ratingIcon;
    }

    public Object getRatings() {
        return ratings;
    }

    public void setRatings(Object ratings) {
        this.ratings = ratings;
    }

}

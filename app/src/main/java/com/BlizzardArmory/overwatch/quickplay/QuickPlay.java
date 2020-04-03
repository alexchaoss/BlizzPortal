package com.BlizzardArmory.overwatch.quickplay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Quick play.
 */
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

    /**
     * Gets quick play stats.
     *
     * @return the quick play stats
     */
    public QuickPlayStats getQuickPlayStats() {
        return quickPlayStats;
    }

    /**
     * Sets quick play stats.
     *
     * @param quickPlayStats the quick play stats
     */
    public void setQuickPlayStats(QuickPlayStats quickPlayStats) {
        this.quickPlayStats = quickPlayStats;
    }

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
     * Gets rating icon.
     *
     * @return the rating icon
     */
    public String getRatingIcon() {
        return ratingIcon;
    }

    /**
     * Sets rating icon.
     *
     * @param ratingIcon the rating icon
     */
    public void setRatingIcon(String ratingIcon) {
        this.ratingIcon = ratingIcon;
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    public Object getRatings() {
        return ratings;
    }

    /**
     * Sets ratings.
     *
     * @param ratings the ratings
     */
    public void setRatings(Object ratings) {
        this.ratings = ratings;
    }

}

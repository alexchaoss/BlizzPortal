package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Profile.
 */
public class Profile {

    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("levelIcon")
    @Expose
    private String levelIcon;
    @SerializedName("prestige")
    @Expose
    private int prestige;
    @SerializedName("prestigeIcon")
    @Expose
    private String prestigeIcon;
    @SerializedName("endorsement")
    @Expose
    private int endorsement;
    @SerializedName("endorsementIcon")
    @Expose
    private String endorsementIcon;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("ratingIcon")
    @Expose
    private String ratingIcon;
    @SerializedName("ratings")
    @Expose
    private List<Rating> ratings = null;
    @SerializedName("gamesWon")
    @Expose
    private int gamesWon;
    @SerializedName("quickPlayStats")
    @Expose
    private com.BlizzardArmory.model.overwatch.quickplay.QuickPlayStats quickPlayStats;
    @SerializedName("competitiveStats")
    @Expose
    private com.BlizzardArmory.model.overwatch.competitive.CompetitiveStats competitiveStats;

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets level icon.
     *
     * @return the level icon
     */
    public String getLevelIcon() {
        return levelIcon;
    }

    /**
     * Sets level icon.
     *
     * @param levelIcon the level icon
     */
    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }

    /**
     * Gets prestige.
     *
     * @return the prestige
     */
    public int getPrestige() {
        return prestige;
    }

    /**
     * Sets prestige.
     *
     * @param prestige the prestige
     */
    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    /**
     * Gets prestige icon.
     *
     * @return the prestige icon
     */
    public String getPrestigeIcon() {
        return prestigeIcon;
    }

    /**
     * Sets prestige icon.
     *
     * @param prestigeIcon the prestige icon
     */
    public void setPrestigeIcon(String prestigeIcon) {
        this.prestigeIcon = prestigeIcon;
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
    public List<Rating> getRatings() {
        return ratings;
    }

    /**
     * Sets ratings.
     *
     * @param ratings the ratings
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * Gets games won.
     *
     * @return the games won
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets games won.
     *
     * @param gamesWon the games won
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Gets quick play stats.
     *
     * @return the quick play stats
     */
    public com.BlizzardArmory.model.overwatch.quickplay.QuickPlayStats getQuickPlayStats() {
        return quickPlayStats;
    }

    /**
     * Sets quick play stats.
     *
     * @param quickPlayStats the quick play stats
     */
    public void setQuickPlayStats(com.BlizzardArmory.model.overwatch.quickplay.QuickPlayStats quickPlayStats) {
        this.quickPlayStats = quickPlayStats;
    }

    /**
     * Gets competitive stats.
     *
     * @return the competitive stats
     */
    public com.BlizzardArmory.model.overwatch.competitive.CompetitiveStats getCompetitiveStats() {
        return competitiveStats;
    }

    /**
     * Sets competitive stats.
     *
     * @param competitiveStats the competitive stats
     */
    public void setCompetitiveStats(com.BlizzardArmory.model.overwatch.competitive.CompetitiveStats competitiveStats) {
        this.competitiveStats = competitiveStats;
    }


    /**
     * Gets endorsement.
     *
     * @return the endorsement
     */
    public int getEndorsement() {
        return endorsement;
    }

    /**
     * Sets endorsement.
     *
     * @param endorsement the endorsement
     */
    public void setEndorsement(int endorsement) {
        this.endorsement = endorsement;
    }

    /**
     * Gets endorsement icon.
     *
     * @return the endorsement icon
     */
    public String getEndorsementIcon() {
        return endorsementIcon;
    }

    /**
     * Sets endorsement icon.
     *
     * @param endorsementIcon the endorsement icon
     */
    public void setEndorsementIcon(String endorsementIcon) {
        this.endorsementIcon = endorsementIcon;
    }


}

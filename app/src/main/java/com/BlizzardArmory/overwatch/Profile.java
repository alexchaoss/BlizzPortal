
package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("ratingIcon")
    @Expose
    private String ratingIcon;
    @SerializedName("gamesWon")
    @Expose
    private int gamesWon;
    @SerializedName("quickPlayStats")
    @Expose
    private QuickPlayStats quickPlayStats;
    @SerializedName("competitiveStats")
    @Expose
    private CompetitiveStats competitiveStats;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public String getPrestigeIcon() {
        return prestigeIcon;
    }

    public void setPrestigeIcon(String prestigeIcon) {
        this.prestigeIcon = prestigeIcon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingIcon() {
        return ratingIcon;
    }

    public void setRatingIcon(String ratingIcon) {
        this.ratingIcon = ratingIcon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public QuickPlayStats getQuickPlayStats() {
        return quickPlayStats;
    }

    public void setQuickPlayStats(QuickPlayStats quickPlayStats) {
        this.quickPlayStats = quickPlayStats;
    }

    public CompetitiveStats getCompetitiveStats() {
        return competitiveStats;
    }

    public void setCompetitiveStats(CompetitiveStats competitiveStats) {
        this.competitiveStats = competitiveStats;
    }

}

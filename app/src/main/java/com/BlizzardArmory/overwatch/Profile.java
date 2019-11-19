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
    @SerializedName("gamesWon")
    @Expose
    private int gamesWon;
    @SerializedName("quickPlayStats")
    @Expose
    private com.BlizzardArmory.overwatch.QuickPlay.QuickPlayStats quickPlayStats;
    @SerializedName("competitiveStats")
    @Expose
    private com.BlizzardArmory.overwatch.Competitive.CompetitiveStats competitiveStats;

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

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public com.BlizzardArmory.overwatch.QuickPlay.QuickPlayStats getQuickPlayStats() {
        return quickPlayStats;
    }

    public void setQuickPlayStats(com.BlizzardArmory.overwatch.QuickPlay.QuickPlayStats quickPlayStats) {
        this.quickPlayStats = quickPlayStats;
    }

    public com.BlizzardArmory.overwatch.Competitive.CompetitiveStats getCompetitiveStats() {
        return competitiveStats;
    }

    public void setCompetitiveStats(com.BlizzardArmory.overwatch.Competitive.CompetitiveStats competitiveStats) {
        this.competitiveStats = competitiveStats;
    }


    public int getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(int endorsement) {
        this.endorsement = endorsement;
    }

    public String getEndorsementIcon() {
        return endorsementIcon;
    }

    public void setEndorsementIcon(String endorsementIcon) {
        this.endorsementIcon = endorsementIcon;
    }



}

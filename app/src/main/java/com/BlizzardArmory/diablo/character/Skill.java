package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Skill {

    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("tooltipUrl")
    @Expose
    private String tooltipUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("descriptionHtml")
    @Expose
    private String descriptionHtml;
    @SerializedName("flavorText")
    @Expose
    private String flavorText;

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTooltipUrl() {
        return tooltipUrl;
    }

    public void setTooltipUrl(String tooltipUrl) {
        this.tooltipUrl = tooltipUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("slug", slug).append("name", name).append("icon", icon).append("level", level).append("tooltipUrl", tooltipUrl).append("description", description).append("descriptionHtml", descriptionHtml).toString();
    }

}

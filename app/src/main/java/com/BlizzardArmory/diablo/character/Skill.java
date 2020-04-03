package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Skill.
 */
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

    /**
     * Gets flavor text.
     *
     * @return the flavor text
     */
    public String getFlavorText() {
        return flavorText;
    }

    /**
     * Sets flavor text.
     *
     * @param flavorText the flavor text
     */
    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    /**
     * Gets slug.
     *
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Sets slug.
     *
     * @param slug the slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
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
     * Gets level.
     *
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Gets tooltip url.
     *
     * @return the tooltip url
     */
    public String getTooltipUrl() {
        return tooltipUrl;
    }

    /**
     * Sets tooltip url.
     *
     * @param tooltipUrl the tooltip url
     */
    public void setTooltipUrl(String tooltipUrl) {
        this.tooltipUrl = tooltipUrl;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets description html.
     *
     * @return the description html
     */
    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    /**
     * Sets description html.
     *
     * @param descriptionHtml the description html
     */
    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("slug", slug).append("name", name).append("icon", icon).append("level", level).append("tooltipUrl", tooltipUrl).append("description", description).append("descriptionHtml", descriptionHtml).toString();
    }

}

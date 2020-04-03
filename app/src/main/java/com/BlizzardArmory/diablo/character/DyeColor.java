package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Dye color.
 */
public class DyeColor {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("tooltipParams")
    @Expose
    private String tooltipParams;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
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
     * Gets tooltip params.
     *
     * @return the tooltip params
     */
    public String getTooltipParams() {
        return tooltipParams;
    }

    /**
     * Sets tooltip params.
     *
     * @param tooltipParams the tooltip params
     */
    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("tooltipParams", tooltipParams).toString();
    }

}

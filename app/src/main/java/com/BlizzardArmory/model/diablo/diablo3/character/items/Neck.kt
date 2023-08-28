package com.BlizzardArmory.model.diablo.diablo3.character.items

import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

/**
 * The type Neck.
 */

class Neck {
    /**
     * Gets id.
     *
     * @return the id
     */
    /**
     * Sets id.
     *
     * @param id the id
     */
    @SerializedName("id")
    var id: String? = null

    /**
     * Gets name.
     *
     * @return the name
     */
    /**
     * Sets name.
     *
     * @param name the name
     */
    @SerializedName("name")
    var name: String? = null

    /**
     * Gets icon.
     *
     * @return the icon
     */
    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    @SerializedName("icon")
    var icon: String? = null

    /**
     * Gets display color.
     *
     * @return the display color
     */
    /**
     * Sets display color.
     *
     * @param displayColor the display color
     */
    @SerializedName("displayColor")
    var displayColor: String? = null

    /**
     * Gets tooltip params.
     *
     * @return the tooltip params
     */
    /**
     * Sets tooltip params.
     *
     * @param tooltipParams the tooltip params
     */
    @SerializedName("tooltipParams")
    var tooltipParams: String? = null

    override fun toString(): String {
        return ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon)
            .append("displayColor", displayColor).append("tooltipParams", tooltipParams).toString()
    }
}
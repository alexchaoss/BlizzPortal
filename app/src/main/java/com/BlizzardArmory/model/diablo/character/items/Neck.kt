package com.BlizzardArmory.model.diablo.character.items

import com.google.gson.annotations.Expose
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
    @Expose
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
    @Expose
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
    @Expose
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
    @Expose
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
    @Expose
    var tooltipParams: String? = null

    override fun toString(): String {
        return ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("displayColor", displayColor).append("tooltipParams", tooltipParams).toString()
    }
}
package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Socket.
 */
class Socket {
    /**
     * Gets socket type.
     *
     * @return the socket type
     */
    /**
     * Sets socket type.
     *
     * @param socketType the socket type
     */
    @SerializedName("socket_type")
    @Expose
    var socketType: SocketType? = null

    /**
     * Gets item.
     *
     * @return the item
     */
    /**
     * Sets item.
     *
     * @param item the item
     */
    @SerializedName("item")
    @Expose
    var item: Item? = null

    /**
     * Gets context.
     *
     * @return the context
     */
    /**
     * Sets context.
     *
     * @param context the context
     */
    @SerializedName("context")
    @Expose
    var context = 0

    /**
     * Gets display string.
     *
     * @return the display string
     */
    /**
     * Sets display string.
     *
     * @param displayString the display string
     */
    @SerializedName("display_string")
    @Expose
    var displayString: String? = null

    /**
     * Gets media.
     *
     * @return the media
     */
    /**
     * Sets media.
     *
     * @param media the media
     */
    @SerializedName("media")
    @Expose
    var media: Media? = null

    /**
     * Gets bonus list.
     *
     * @return the bonus list
     */
    /**
     * Sets bonus list.
     *
     * @param bonusList the bonus list
     */
    @SerializedName("bonus_list")
    @Expose
    var bonusList: List<Int>? = null

}
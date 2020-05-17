package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Socket.
 */
public class Socket {

    @SerializedName("socket_type")
    @Expose
    private SocketType socketType;
    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("context")
    @Expose
    private int context;
    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("bonus_list")
    @Expose
    private List<Integer> bonusList = null;

    /**
     * Gets socket type.
     *
     * @return the socket type
     */
    public SocketType getSocketType() {
        return socketType;
    }

    /**
     * Sets socket type.
     *
     * @param socketType the socket type
     */
    public void setSocketType(SocketType socketType) {
        this.socketType = socketType;
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public int getContext() {
        return context;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(int context) {
        this.context = context;
    }

    /**
     * Gets display string.
     *
     * @return the display string
     */
    public String getDisplayString() {
        return displayString;
    }

    /**
     * Sets display string.
     *
     * @param displayString the display string
     */
    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * Gets bonus list.
     *
     * @return the bonus list
     */
    public List<Integer> getBonusList() {
        return bonusList;
    }

    /**
     * Sets bonus list.
     *
     * @param bonusList the bonus list
     */
    public void setBonusList(List<Integer> bonusList) {
        this.bonusList = bonusList;
    }

}

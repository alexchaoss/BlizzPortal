package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public SocketType getSocketType() {
        return socketType;
    }

    public void setSocketType(SocketType socketType) {
        this.socketType = socketType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getContext() {
        return context;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<Integer> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Integer> bonusList) {
        this.bonusList = bonusList;
    }

}

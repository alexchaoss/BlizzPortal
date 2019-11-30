package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MainHand {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("displayColor")
    @Expose
    private String displayColor;
    @SerializedName("tooltipParams")
    @Expose
    private String tooltipParams;
    @SerializedName("transmogItem")
    @Expose
    private TransmogItem transmogItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public TransmogItem getTransmogItem() {
        return transmogItem;
    }

    public void setTransmogItem(TransmogItem transmogItem) {
        this.transmogItem = transmogItem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("displayColor", displayColor).append("tooltipParams", tooltipParams).append("transmogItem", transmogItem).toString();
    }

}

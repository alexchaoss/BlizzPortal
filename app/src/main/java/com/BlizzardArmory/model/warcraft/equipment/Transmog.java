package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Transmog.
 */
public class Transmog {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("item_modified_appearance_id")
    @Expose
    private int itemModifiedAppearanceId;

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
     * Gets item modified appearance id.
     *
     * @return the item modified appearance id
     */
    public int getItemModifiedAppearanceId() {
        return itemModifiedAppearanceId;
    }

    /**
     * Sets item modified appearance id.
     *
     * @param itemModifiedAppearanceId the item modified appearance id
     */
    public void setItemModifiedAppearanceId(int itemModifiedAppearanceId) {
        this.itemModifiedAppearanceId = itemModifiedAppearanceId;
    }

}

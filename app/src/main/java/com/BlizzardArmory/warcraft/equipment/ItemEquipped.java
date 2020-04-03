package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Item equipped.
 */
public class ItemEquipped {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("is_equipped")
    @Expose
    private boolean isEquipped = false;

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
     * Is is equipped boolean.
     *
     * @return the boolean
     */
    public boolean isIsEquipped() {
        return isEquipped;
    }

    /**
     * Sets is equipped.
     *
     * @param isEquipped the is equipped
     */
    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

}

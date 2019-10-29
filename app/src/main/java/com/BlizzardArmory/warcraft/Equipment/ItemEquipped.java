
package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemEquipped {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("is_equipped")
    @Expose
    private boolean isEquipped;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isIsEquipped() {
        return isEquipped;
    }

    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

}


package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Set {

    @SerializedName("item_set")
    @Expose
    private ItemSet itemSet;
    @SerializedName("items")
    @Expose
    private List<ItemEquipped> items = null;
    @SerializedName("effects")
    @Expose
    private List<Effect> effects = null;
    @SerializedName("display_string")
    @Expose
    private String displayString;

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public List<ItemEquipped> getItems() {
        return items;
    }

    public void setItems(List<ItemEquipped> items) {
        this.items = items;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

}

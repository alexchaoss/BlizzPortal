
package com.BlizzardArmory.warcraft.Equipment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EquippedItemSet {

    @SerializedName("itemset")
    @Expose
    private ItemSet itemSet;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("effects")
    @Expose
    private List<Effect> effects = null;
    @SerializedName("displaystring")
    @Expose
    private String displayString;

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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

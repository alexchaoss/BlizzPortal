package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Set.
 */
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

    /**
     * Gets item set.
     *
     * @return the item set
     */
    public ItemSet getItemSet() {
        return itemSet;
    }

    /**
     * Sets item set.
     *
     * @param itemSet the item set
     */
    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<ItemEquipped> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<ItemEquipped> items) {
        this.items = items;
    }

    /**
     * Gets effects.
     *
     * @return the effects
     */
    public List<Effect> getEffects() {
        return effects;
    }

    /**
     * Sets effects.
     *
     * @param effects the effects
     */
    public void setEffects(List<Effect> effects) {
        this.effects = effects;
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

}

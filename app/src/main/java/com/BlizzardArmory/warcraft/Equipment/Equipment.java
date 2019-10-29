
package com.BlizzardArmory.warcraft.Equipment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Equipment {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("character")
    @Expose
    private Character character;
    @SerializedName("equipped_items")
    @Expose
    private List<EquippedItem> equippedItems = null;
    @SerializedName("equipped_item_sets")
    @Expose
    private List<EquippedItemSet> equippedItemSets = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<EquippedItem> getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(List<EquippedItem> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public List<EquippedItemSet> getEquippedItemSets() {
        return equippedItemSets;
    }

    public void setEquippedItemSets(List<EquippedItemSet> equippedItemSets) {
        this.equippedItemSets = equippedItemSets;
    }

}

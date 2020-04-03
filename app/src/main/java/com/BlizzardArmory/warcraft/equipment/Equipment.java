package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Equipment.
 */
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

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets character.
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Gets equipped items.
     *
     * @return the equipped items
     */
    public List<EquippedItem> getEquippedItems() {
        return equippedItems;
    }

    /**
     * Sets equipped items.
     *
     * @param equippedItems the equipped items
     */
    public void setEquippedItems(List<EquippedItem> equippedItems) {
        this.equippedItems = equippedItems;
    }

    /**
     * Gets equipped item sets.
     *
     * @return the equipped item sets
     */
    public List<EquippedItemSet> getEquippedItemSets() {
        return equippedItemSets;
    }

    /**
     * Sets equipped item sets.
     *
     * @param equippedItemSets the equipped item sets
     */
    public void setEquippedItemSets(List<EquippedItemSet> equippedItemSets) {
        this.equippedItemSets = equippedItemSets;
    }

}

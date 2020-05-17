package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Enchantment.
 */
public class Enchantment {

    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("enchantment_id")
    @Expose
    private int enchantmentId;
    @SerializedName("enchantment_slot")
    @Expose
    private EnchantmentSlot enchantmentSlot;

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
     * Gets enchantment id.
     *
     * @return the enchantment id
     */
    public int getEnchantmentId() {
        return enchantmentId;
    }

    /**
     * Sets enchantment id.
     *
     * @param enchantmentId the enchantment id
     */
    public void setEnchantmentId(int enchantmentId) {
        this.enchantmentId = enchantmentId;
    }

    /**
     * Gets enchantment slot.
     *
     * @return the enchantment slot
     */
    public EnchantmentSlot getEnchantmentSlot() {
        return enchantmentSlot;
    }

    /**
     * Sets enchantment slot.
     *
     * @param enchantmentSlot the enchantment slot
     */
    public void setEnchantmentSlot(EnchantmentSlot enchantmentSlot) {
        this.enchantmentSlot = enchantmentSlot;
    }

}

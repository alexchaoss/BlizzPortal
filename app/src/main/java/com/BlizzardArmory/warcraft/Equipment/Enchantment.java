package com.BlizzardArmory.warcraft.Equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public int getEnchantmentId() {
        return enchantmentId;
    }

    public void setEnchantmentId(int enchantmentId) {
        this.enchantmentId = enchantmentId;
    }

    public EnchantmentSlot getEnchantmentSlot() {
        return enchantmentSlot;
    }

    public void setEnchantmentSlot(EnchantmentSlot enchantmentSlot) {
        this.enchantmentSlot = enchantmentSlot;
    }

}

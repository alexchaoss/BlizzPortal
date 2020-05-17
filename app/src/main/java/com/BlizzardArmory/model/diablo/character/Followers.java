package com.BlizzardArmory.model.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Followers.
 */
public class Followers {

    @SerializedName("templar")
    @Expose
    private Templar templar;
    @SerializedName("scoundrel")
    @Expose
    private Scoundrel scoundrel;
    @SerializedName("enchantress")
    @Expose
    private Enchantress enchantress;

    /**
     * Gets templar.
     *
     * @return the templar
     */
    public Templar getTemplar() {
        return templar;
    }

    /**
     * Sets templar.
     *
     * @param templar the templar
     */
    public void setTemplar(Templar templar) {
        this.templar = templar;
    }

    /**
     * Gets scoundrel.
     *
     * @return the scoundrel
     */
    public Scoundrel getScoundrel() {
        return scoundrel;
    }

    /**
     * Sets scoundrel.
     *
     * @param scoundrel the scoundrel
     */
    public void setScoundrel(Scoundrel scoundrel) {
        this.scoundrel = scoundrel;
    }

    /**
     * Gets enchantress.
     *
     * @return the enchantress
     */
    public Enchantress getEnchantress() {
        return enchantress;
    }

    /**
     * Sets enchantress.
     *
     * @param enchantress the enchantress
     */
    public void setEnchantress(Enchantress enchantress) {
        this.enchantress = enchantress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("templar", templar).append("scoundrel", scoundrel).append("enchantress", enchantress).toString();
    }

}
package com.BlizzardArmory.diablo.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

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

    public Templar getTemplar() {
        return templar;
    }

    public void setTemplar(Templar templar) {
        this.templar = templar;
    }

    public Scoundrel getScoundrel() {
        return scoundrel;
    }

    public void setScoundrel(Scoundrel scoundrel) {
        this.scoundrel = scoundrel;
    }

    public Enchantress getEnchantress() {
        return enchantress;
    }

    public void setEnchantress(Enchantress enchantress) {
        this.enchantress = enchantress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("templar", templar).append("scoundrel", scoundrel).append("enchantress", enchantress).toString();
    }

}
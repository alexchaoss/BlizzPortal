package com.BlizzardArmory.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class WeaponInfo {

    @SerializedName("damage")
    @Expose
    private Damage damage;
    @SerializedName("weaponSpeed")
    @Expose
    private Double weaponSpeed;
    @SerializedName("dps")
    @Expose
    private Double dps;

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public Double getWeaponSpeed() {
        return weaponSpeed;
    }

    public void setWeaponSpeed(Double weaponSpeed) {
        this.weaponSpeed = weaponSpeed;
    }

    public Double getDps() {
        return dps;
    }

    public void setDps(Double dps) {
        this.dps = dps;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("damage", damage).append("weaponSpeed", weaponSpeed).append("dps", dps).toString();
    }

}

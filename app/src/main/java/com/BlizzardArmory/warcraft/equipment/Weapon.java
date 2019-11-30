package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weapon {

    @SerializedName("damage")
    @Expose
    private Damage damage;
    @SerializedName("attack_speed")
    @Expose
    private AttackSpeed attackSpeed;
    @SerializedName("dps")
    @Expose
    private Dps dps;

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public AttackSpeed getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(AttackSpeed attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Dps getDps() {
        return dps;
    }

    public void setDps(Dps dps) {
        this.dps = dps;
    }

}

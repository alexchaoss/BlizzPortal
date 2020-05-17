package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Weapon.
 */
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

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public Damage getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    /**
     * Gets attack speed.
     *
     * @return the attack speed
     */
    public AttackSpeed getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Sets attack speed.
     *
     * @param attackSpeed the attack speed
     */
    public void setAttackSpeed(AttackSpeed attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Gets dps.
     *
     * @return the dps
     */
    public Dps getDps() {
        return dps;
    }

    /**
     * Sets dps.
     *
     * @param dps the dps
     */
    public void setDps(Dps dps) {
        this.dps = dps;
    }

}

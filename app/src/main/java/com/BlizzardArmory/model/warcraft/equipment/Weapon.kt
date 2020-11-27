package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.SerializedName


/**
 * The type Weapon.
 */
data class Weapon(

        @SerializedName("damage")
        var damage: Damage,

        @SerializedName("attack_speed")
        var attackSpeed: AttackSpeed,

        @SerializedName("dps")
        var dps: Dps

)
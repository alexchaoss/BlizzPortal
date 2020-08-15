package com.BlizzardArmory.model.warcraft.equipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Weapon.
 */
data class Weapon(

        @SerializedName("damage")
        @Expose
        var damage: Damage,

        @SerializedName("attack_speed")
        @Expose
        var attackSpeed: AttackSpeed,

        @SerializedName("dps")
        @Expose
        var dps: Dps

)
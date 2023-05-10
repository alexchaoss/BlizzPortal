package com.BlizzardArmory.model.warcraft.equipment

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/**
 * The type Weapon.
 */
@Keep
data class Weapon(

    @SerializedName("damage")
    var damage: Damage,

    @SerializedName("attack_speed")
    var attackSpeed: AttackSpeed,

    @SerializedName("dps")
    var dps: Dps

)
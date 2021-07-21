package com.BlizzardArmory.model.diablo.character

import com.google.gson.annotations.SerializedName


/**
 * The type Stats.
 */
data class Stats(

    @SerializedName("life")
    var life: Double,

    @SerializedName("damage")
    var damage: Double,

    @SerializedName("toughness")
    var toughness: Double,

    @SerializedName("healing")
    var healing: Double,

    @SerializedName("attackSpeed")
    var attackSpeed: Double,

    @SerializedName("armor")
    var armor: Double,

    @SerializedName("strength")
    var strength: Double,

    @SerializedName("dexterity")
    var dexterity: Double,

    @SerializedName("vitality")
    var vitality: Double,

    @SerializedName("Doubleelligence")
    var intelligence: Double,

    @SerializedName("physicalResist")
    var physicalResist: Double,

    @SerializedName("fireResist")
    var fireResist: Double,

    @SerializedName("coldResist")
    var coldResist: Double,

    @SerializedName("lightningResist")
    var lightningResist: Double,

    @SerializedName("poisonResist")
    var poisonResist: Double,

    @SerializedName("arcaneResist")
    var arcaneResist: Double,

    @SerializedName("blockChance")
    var blockChance: Double,

    @SerializedName("blockAmountMin")
    var blockAmountMin: Double,

    @SerializedName("blockAmountMax")
    var blockAmountMax: Double,

    @SerializedName("goldFind")
    var goldFind: Double,

    @SerializedName("critChance")
    var critChance: Double,

    @SerializedName("thorns")
    var thorns: Double,

    @SerializedName("lifeSteal")
    var lifeSteal: Double,

    @SerializedName("lifePerKill")
    var lifePerKill: Double,

    @SerializedName("lifeOnHit")
    var lifeOnHit: Double,

    @SerializedName("primaryResource")
    var primaryResource: Double,

    @SerializedName("secondaryResource")
    var secondaryResource: Double

)
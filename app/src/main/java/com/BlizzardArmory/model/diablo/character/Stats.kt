package com.BlizzardArmory.model.diablo.character

import com.google.gson.annotations.SerializedName


/**
 * The type Stats.
 */
data class Stats(

        @SerializedName("life")
        var life: Double,

        @SerializedName("damage")
        var damage: Int,

        @SerializedName("toughness")
        var toughness: Int,

        @SerializedName("healing")
        var healing: Int,

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

        @SerializedName("intelligence")
        var intelligence: Double,

        @SerializedName("physicalResist")
        var physicalResist: Int,

        @SerializedName("fireResist")
        var fireResist: Int,

        @SerializedName("coldResist")
        var coldResist: Int,

        @SerializedName("lightningResist")
        var lightningResist: Int,

        @SerializedName("poisonResist")
        var poisonResist: Int,

        @SerializedName("arcaneResist")
        var arcaneResist: Int,

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
        var primaryResource: Int,

        @SerializedName("secondaryResource")
        var secondaryResource: Int

)
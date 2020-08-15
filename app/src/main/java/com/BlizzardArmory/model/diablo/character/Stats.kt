package com.BlizzardArmory.model.diablo.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Stats.
 */
data class Stats(

        @SerializedName("life")
        @Expose
        var life: Double,

        @SerializedName("damage")
        @Expose
        var damage: Int,

        @SerializedName("toughness")
        @Expose
        var toughness: Int,

        @SerializedName("healing")
        @Expose
        var healing: Int,

        @SerializedName("attackSpeed")
        @Expose
        var attackSpeed: Double,

        @SerializedName("armor")
        @Expose
        var armor: Double,

        @SerializedName("strength")
        @Expose
        var strength: Double,

        @SerializedName("dexterity")
        @Expose
        var dexterity: Double,

        @SerializedName("vitality")
        @Expose
        var vitality: Double,

        @SerializedName("intelligence")
        @Expose
        var intelligence: Double,

        @SerializedName("physicalResist")
        @Expose
        var physicalResist: Int,

        @SerializedName("fireResist")
        @Expose
        var fireResist: Int,

        @SerializedName("coldResist")
        @Expose
        var coldResist: Int,

        @SerializedName("lightningResist")
        @Expose
        var lightningResist: Int,

        @SerializedName("poisonResist")
        @Expose
        var poisonResist: Int,

        @SerializedName("arcaneResist")
        @Expose
        var arcaneResist: Int,

        @SerializedName("blockChance")
        @Expose
        var blockChance: Double,

        @SerializedName("blockAmountMin")
        @Expose
        var blockAmountMin: Double,

        @SerializedName("blockAmountMax")
        @Expose
        var blockAmountMax: Double,

        @SerializedName("goldFind")
        @Expose
        var goldFind: Double,

        @SerializedName("critChance")
        @Expose
        var critChance: Double,

        @SerializedName("thorns")
        @Expose
        var thorns: Double,

        @SerializedName("lifeSteal")
        @Expose
        var lifeSteal: Double,

        @SerializedName("lifePerKill")
        @Expose
        var lifePerKill: Double,

        @SerializedName("lifeOnHit")
        @Expose
        var lifeOnHit: Double,

        @SerializedName("primaryResource")
        @Expose
        var primaryResource: Int,

        @SerializedName("secondaryResource")
        @Expose
        var secondaryResource: Int

)
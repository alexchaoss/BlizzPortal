package com.BlizzardArmory.model.warcraft.statistic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Statistic.
 */
data class Statistic(

        @SerializedName("_links")
        @Expose
        var links: Links,

        @SerializedName("health")
        @Expose
        var health: Int,

        @SerializedName("power")
        @Expose
        var power: Int,

        @SerializedName("power_type")
        @Expose
        var powerType: PowerType,

        @SerializedName("speed")
        @Expose
        var speed: Speed,

        @SerializedName("strength")
        @Expose
        var strength: Strength,

        @SerializedName("agility")
        @Expose
        var agility: Agility,

        @SerializedName("intellect")
        @Expose
        var intellect: Intellect,

        @SerializedName("stamina")
        @Expose
        var stamina: Stamina,

        @SerializedName("melee_crit")
        @Expose
        var meleeCrit: MeleeCrit,

        @SerializedName("melee_haste")
        @Expose
        var meleeHaste: MeleeHaste,

        @SerializedName("mastery")
        @Expose
        var mastery: Mastery,

        @SerializedName("bonus_armor")
        @Expose
        var bonusArmor: Int,

        @SerializedName("lifesteal")
        @Expose
        var lifesteal: Lifesteal,

        @SerializedName("versatility")
        @Expose
        var versatility: Float,

        @SerializedName("versatility_damage_done_bonus")
        @Expose
        var versatilityDamageDoneBonus: Float,

        @SerializedName("versatility_healing_done_bonus")
        @Expose
        var versatilityHealingDoneBonus: Float,

        @SerializedName("versatility_damage_taken_bonus")
        @Expose
        var versatilityDamageTakenBonus: Float,

        @SerializedName("avoidance")
        @Expose
        var avoidance: Avoidance,

        @SerializedName("attack_power")
        @Expose
        var attackPower: Int,

        @SerializedName("main_hand_damage_min")
        @Expose
        var mainHandDamageMin: Float,

        @SerializedName("main_hand_damage_max")
        @Expose
        var mainHandDamageMax: Float,

        @SerializedName("main_hand_speed")
        @Expose
        var mainHandSpeed: Float,

        @SerializedName("main_hand_dps")
        @Expose
        var mainHandDps: Float,

        @SerializedName("off_hand_damage_min")
        @Expose
        var offHandDamageMin: Float,

        @SerializedName("off_hand_damage_max")
        @Expose
        var offHandDamageMax: Float,

        @SerializedName("off_hand_speed")
        @Expose
        var offHandSpeed: Float,

        @SerializedName("off_hand_dps")
        @Expose
        var offHandDps: Float,

        @SerializedName("spell_power")
        @Expose
        var spellPower: Int,

        @SerializedName("spell_penetration")
        @Expose
        var spellPenetration: Int,

        @SerializedName("spell_crit")
        @Expose
        var spellCrit: SpellCrit,

        @SerializedName("mana_regen")
        @Expose
        var manaRegen: Float,

        @SerializedName("mana_regen_combat")
        @Expose
        var manaRegenCombat: Float,

        @SerializedName("armor")
        @Expose
        var armor: Armor,

        @SerializedName("dodge")
        @Expose
        var dodge: Dodge,

        @SerializedName("parry")
        @Expose
        var parry: Parry,

        @SerializedName("block")
        @Expose
        var block: Block,

        @SerializedName("ranged_crit")
        @Expose
        var rangedCrit: RangedCrit,

        @SerializedName("ranged_haste")
        @Expose
        var rangedHaste: RangedHaste,

        @SerializedName("spell_haste")
        @Expose
        var spellHaste: SpellHaste,

        @SerializedName("character")
        @Expose
        var character: Character
)
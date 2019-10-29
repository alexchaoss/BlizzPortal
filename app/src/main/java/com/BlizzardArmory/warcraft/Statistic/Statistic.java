package com.BlizzardArmory.warcraft.Statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistic {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("health")
    @Expose
    private int health;
    @SerializedName("power")
    @Expose
    private int power;
    @SerializedName("power_type")
    @Expose
    private PowerType powerType;
    @SerializedName("speed")
    @Expose
    private Speed speed;
    @SerializedName("strength")
    @Expose
    private Strength strength;
    @SerializedName("agility")
    @Expose
    private Agility agility;
    @SerializedName("intellect")
    @Expose
    private Intellect intellect;
    @SerializedName("stamina")
    @Expose
    private Stamina stamina;
    @SerializedName("melee_crit")
    @Expose
    private MeleeCrit meleeCrit;
    @SerializedName("melee_haste")
    @Expose
    private MeleeHaste meleeHaste;
    @SerializedName("mastery")
    @Expose
    private Mastery mastery;
    @SerializedName("bonus_armor")
    @Expose
    private int bonusArmor;
    @SerializedName("lifesteal")
    @Expose
    private Lifesteal lifesteal;
    @SerializedName("versatility")
    @Expose
    private float versatility;
    @SerializedName("versatility_damage_done_bonus")
    @Expose
    private float versatilityDamageDoneBonus;
    @SerializedName("versatility_healing_done_bonus")
    @Expose
    private float versatilityHealingDoneBonus;
    @SerializedName("versatility_damage_taken_bonus")
    @Expose
    private float versatilityDamageTakenBonus;
    @SerializedName("avoidance")
    @Expose
    private Avoidance avoidance;
    @SerializedName("attack_power")
    @Expose
    private int attackPower;
    @SerializedName("main_hand_damage_min")
    @Expose
    private float mainHandDamageMin;
    @SerializedName("main_hand_damage_max")
    @Expose
    private float mainHandDamageMax;
    @SerializedName("main_hand_speed")
    @Expose
    private float mainHandSpeed;
    @SerializedName("main_hand_dps")
    @Expose
    private float mainHandDps;
    @SerializedName("off_hand_damage_min")
    @Expose
    private float offHandDamageMin;
    @SerializedName("off_hand_damage_max")
    @Expose
    private float offHandDamageMax;
    @SerializedName("off_hand_speed")
    @Expose
    private float offHandSpeed;
    @SerializedName("off_hand_dps")
    @Expose
    private float offHandDps;
    @SerializedName("spell_power")
    @Expose
    private int spellPower;
    @SerializedName("spell_penetration")
    @Expose
    private int spellPenetration;
    @SerializedName("spell_crit")
    @Expose
    private SpellCrit spellCrit;
    @SerializedName("mana_regen")
    @Expose
    private float manaRegen;
    @SerializedName("mana_regen_combat")
    @Expose
    private float manaRegenCombat;
    @SerializedName("armor")
    @Expose
    private Armor armor;
    @SerializedName("dodge")
    @Expose
    private Dodge dodge;
    @SerializedName("parry")
    @Expose
    private Parry parry;
    @SerializedName("block")
    @Expose
    private Block block;
    @SerializedName("ranged_crit")
    @Expose
    private RangedCrit rangedCrit;
    @SerializedName("ranged_haste")
    @Expose
    private RangedHaste rangedHaste;
    @SerializedName("spell_haste")
    @Expose
    private SpellHaste spellHaste;
    @SerializedName("character")
    @Expose
    private Character character;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Strength getStrength() {
        return strength;
    }

    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    public Agility getAgility() {
        return agility;
    }

    public void setAgility(Agility agility) {
        this.agility = agility;
    }

    public Intellect getIntellect() {
        return intellect;
    }

    public void setIntellect(Intellect intellect) {
        this.intellect = intellect;
    }

    public Stamina getStamina() {
        return stamina;
    }

    public void setStamina(Stamina stamina) {
        this.stamina = stamina;
    }

    public MeleeCrit getMeleeCrit() {
        return meleeCrit;
    }

    public void setMeleeCrit(MeleeCrit meleeCrit) {
        this.meleeCrit = meleeCrit;
    }

    public MeleeHaste getMeleeHaste() {
        return meleeHaste;
    }

    public void setMeleeHaste(MeleeHaste meleeHaste) {
        this.meleeHaste = meleeHaste;
    }

    public Mastery getMastery() {
        return mastery;
    }

    public void setMastery(Mastery mastery) {
        this.mastery = mastery;
    }

    public int getBonusArmor() {
        return bonusArmor;
    }

    public void setBonusArmor(int bonusArmor) {
        this.bonusArmor = bonusArmor;
    }

    public Lifesteal getLifesteal() {
        return lifesteal;
    }

    public void setLifesteal(Lifesteal lifesteal) {
        this.lifesteal = lifesteal;
    }

    public float getVersatility() {
        return versatility;
    }

    public void setVersatility(float versatility) {
        this.versatility = versatility;
    }

    public float getVersatilityDamageDoneBonus() {
        return versatilityDamageDoneBonus;
    }

    public void setVersatilityDamageDoneBonus(float versatilityDamageDoneBonus) {
        this.versatilityDamageDoneBonus = versatilityDamageDoneBonus;
    }

    public float getVersatilityHealingDoneBonus() {
        return versatilityHealingDoneBonus;
    }

    public void setVersatilityHealingDoneBonus(float versatilityHealingDoneBonus) {
        this.versatilityHealingDoneBonus = versatilityHealingDoneBonus;
    }

    public float getVersatilityDamageTakenBonus() {
        return versatilityDamageTakenBonus;
    }

    public void setVersatilityDamageTakenBonus(float versatilityDamageTakenBonus) {
        this.versatilityDamageTakenBonus = versatilityDamageTakenBonus;
    }

    public Avoidance getAvoidance() {
        return avoidance;
    }

    public void setAvoidance(Avoidance avoidance) {
        this.avoidance = avoidance;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public float getMainHandDamageMin() {
        return mainHandDamageMin;
    }

    public void setMainHandDamageMin(float mainHandDamageMin) {
        this.mainHandDamageMin = mainHandDamageMin;
    }

    public float getMainHandDamageMax() {
        return mainHandDamageMax;
    }

    public void setMainHandDamageMax(float mainHandDamageMax) {
        this.mainHandDamageMax = mainHandDamageMax;
    }

    public float getMainHandSpeed() {
        return mainHandSpeed;
    }

    public void setMainHandSpeed(float mainHandSpeed) {
        this.mainHandSpeed = mainHandSpeed;
    }

    public float getMainHandDps() {
        return mainHandDps;
    }

    public void setMainHandDps(float mainHandDps) {
        this.mainHandDps = mainHandDps;
    }

    public float getOffHandDamageMin() {
        return offHandDamageMin;
    }

    public void setOffHandDamageMin(float offHandDamageMin) {
        this.offHandDamageMin = offHandDamageMin;
    }

    public float getOffHandDamageMax() {
        return offHandDamageMax;
    }

    public void setOffHandDamageMax(float offHandDamageMax) {
        this.offHandDamageMax = offHandDamageMax;
    }

    public float getOffHandSpeed() {
        return offHandSpeed;
    }

    public void setOffHandSpeed(float offHandSpeed) {
        this.offHandSpeed = offHandSpeed;
    }

    public float getOffHandDps() {
        return offHandDps;
    }

    public void setOffHandDps(float offHandDps) {
        this.offHandDps = offHandDps;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }

    public int getSpellPenetration() {
        return spellPenetration;
    }

    public void setSpellPenetration(int spellPenetration) {
        this.spellPenetration = spellPenetration;
    }

    public SpellCrit getSpellCrit() {
        return spellCrit;
    }

    public void setSpellCrit(SpellCrit spellCrit) {
        this.spellCrit = spellCrit;
    }

    public float getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(float manaRegen) {
        this.manaRegen = manaRegen;
    }

    public float getManaRegenCombat() {
        return manaRegenCombat;
    }

    public void setManaRegenCombat(float manaRegenCombat) {
        this.manaRegenCombat = manaRegenCombat;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Dodge getDodge() {
        return dodge;
    }

    public void setDodge(Dodge dodge) {
        this.dodge = dodge;
    }

    public Parry getParry() {
        return parry;
    }

    public void setParry(Parry parry) {
        this.parry = parry;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public RangedCrit getRangedCrit() {
        return rangedCrit;
    }

    public void setRangedCrit(RangedCrit rangedCrit) {
        this.rangedCrit = rangedCrit;
    }

    public RangedHaste getRangedHaste() {
        return rangedHaste;
    }

    public void setRangedHaste(RangedHaste rangedHaste) {
        this.rangedHaste = rangedHaste;
    }

    public SpellHaste getSpellHaste() {
        return spellHaste;
    }

    public void setSpellHaste(SpellHaste spellHaste) {
        this.spellHaste = spellHaste;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}
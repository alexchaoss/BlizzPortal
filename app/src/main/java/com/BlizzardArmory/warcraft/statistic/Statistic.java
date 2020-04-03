package com.BlizzardArmory.warcraft.statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Statistic.
 */
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

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets power.
     *
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets power.
     *
     * @param power the power
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Gets power type.
     *
     * @return the power type
     */
    public PowerType getPowerType() {
        return powerType;
    }

    /**
     * Sets power type.
     *
     * @param powerType the power type
     */
    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    /**
     * Gets strength.
     *
     * @return the strength
     */
    public Strength getStrength() {
        return strength;
    }

    /**
     * Sets strength.
     *
     * @param strength the strength
     */
    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    /**
     * Gets agility.
     *
     * @return the agility
     */
    public Agility getAgility() {
        return agility;
    }

    /**
     * Sets agility.
     *
     * @param agility the agility
     */
    public void setAgility(Agility agility) {
        this.agility = agility;
    }

    /**
     * Gets intellect.
     *
     * @return the intellect
     */
    public Intellect getIntellect() {
        return intellect;
    }

    /**
     * Sets intellect.
     *
     * @param intellect the intellect
     */
    public void setIntellect(Intellect intellect) {
        this.intellect = intellect;
    }

    /**
     * Gets stamina.
     *
     * @return the stamina
     */
    public Stamina getStamina() {
        return stamina;
    }

    /**
     * Sets stamina.
     *
     * @param stamina the stamina
     */
    public void setStamina(Stamina stamina) {
        this.stamina = stamina;
    }

    /**
     * Gets melee crit.
     *
     * @return the melee crit
     */
    public MeleeCrit getMeleeCrit() {
        return meleeCrit;
    }

    /**
     * Sets melee crit.
     *
     * @param meleeCrit the melee crit
     */
    public void setMeleeCrit(MeleeCrit meleeCrit) {
        this.meleeCrit = meleeCrit;
    }

    /**
     * Gets melee haste.
     *
     * @return the melee haste
     */
    public MeleeHaste getMeleeHaste() {
        return meleeHaste;
    }

    /**
     * Sets melee haste.
     *
     * @param meleeHaste the melee haste
     */
    public void setMeleeHaste(MeleeHaste meleeHaste) {
        this.meleeHaste = meleeHaste;
    }

    /**
     * Gets mastery.
     *
     * @return the mastery
     */
    public Mastery getMastery() {
        return mastery;
    }

    /**
     * Sets mastery.
     *
     * @param mastery the mastery
     */
    public void setMastery(Mastery mastery) {
        this.mastery = mastery;
    }

    /**
     * Gets bonus armor.
     *
     * @return the bonus armor
     */
    public int getBonusArmor() {
        return bonusArmor;
    }

    /**
     * Sets bonus armor.
     *
     * @param bonusArmor the bonus armor
     */
    public void setBonusArmor(int bonusArmor) {
        this.bonusArmor = bonusArmor;
    }

    /**
     * Gets lifesteal.
     *
     * @return the lifesteal
     */
    public Lifesteal getLifesteal() {
        return lifesteal;
    }

    /**
     * Sets lifesteal.
     *
     * @param lifesteal the lifesteal
     */
    public void setLifesteal(Lifesteal lifesteal) {
        this.lifesteal = lifesteal;
    }

    /**
     * Gets versatility.
     *
     * @return the versatility
     */
    public float getVersatility() {
        return versatility;
    }

    /**
     * Sets versatility.
     *
     * @param versatility the versatility
     */
    public void setVersatility(float versatility) {
        this.versatility = versatility;
    }

    /**
     * Gets versatility damage done bonus.
     *
     * @return the versatility damage done bonus
     */
    public float getVersatilityDamageDoneBonus() {
        return versatilityDamageDoneBonus;
    }

    /**
     * Sets versatility damage done bonus.
     *
     * @param versatilityDamageDoneBonus the versatility damage done bonus
     */
    public void setVersatilityDamageDoneBonus(float versatilityDamageDoneBonus) {
        this.versatilityDamageDoneBonus = versatilityDamageDoneBonus;
    }

    /**
     * Gets versatility healing done bonus.
     *
     * @return the versatility healing done bonus
     */
    public float getVersatilityHealingDoneBonus() {
        return versatilityHealingDoneBonus;
    }

    /**
     * Sets versatility healing done bonus.
     *
     * @param versatilityHealingDoneBonus the versatility healing done bonus
     */
    public void setVersatilityHealingDoneBonus(float versatilityHealingDoneBonus) {
        this.versatilityHealingDoneBonus = versatilityHealingDoneBonus;
    }

    /**
     * Gets versatility damage taken bonus.
     *
     * @return the versatility damage taken bonus
     */
    public float getVersatilityDamageTakenBonus() {
        return versatilityDamageTakenBonus;
    }

    /**
     * Sets versatility damage taken bonus.
     *
     * @param versatilityDamageTakenBonus the versatility damage taken bonus
     */
    public void setVersatilityDamageTakenBonus(float versatilityDamageTakenBonus) {
        this.versatilityDamageTakenBonus = versatilityDamageTakenBonus;
    }

    /**
     * Gets avoidance.
     *
     * @return the avoidance
     */
    public Avoidance getAvoidance() {
        return avoidance;
    }

    /**
     * Sets avoidance.
     *
     * @param avoidance the avoidance
     */
    public void setAvoidance(Avoidance avoidance) {
        this.avoidance = avoidance;
    }

    /**
     * Gets attack power.
     *
     * @return the attack power
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Sets attack power.
     *
     * @param attackPower the attack power
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Gets main hand damage min.
     *
     * @return the main hand damage min
     */
    public float getMainHandDamageMin() {
        return mainHandDamageMin;
    }

    /**
     * Sets main hand damage min.
     *
     * @param mainHandDamageMin the main hand damage min
     */
    public void setMainHandDamageMin(float mainHandDamageMin) {
        this.mainHandDamageMin = mainHandDamageMin;
    }

    /**
     * Gets main hand damage max.
     *
     * @return the main hand damage max
     */
    public float getMainHandDamageMax() {
        return mainHandDamageMax;
    }

    /**
     * Sets main hand damage max.
     *
     * @param mainHandDamageMax the main hand damage max
     */
    public void setMainHandDamageMax(float mainHandDamageMax) {
        this.mainHandDamageMax = mainHandDamageMax;
    }

    /**
     * Gets main hand speed.
     *
     * @return the main hand speed
     */
    public float getMainHandSpeed() {
        return mainHandSpeed;
    }

    /**
     * Sets main hand speed.
     *
     * @param mainHandSpeed the main hand speed
     */
    public void setMainHandSpeed(float mainHandSpeed) {
        this.mainHandSpeed = mainHandSpeed;
    }

    /**
     * Gets main hand dps.
     *
     * @return the main hand dps
     */
    public float getMainHandDps() {
        return mainHandDps;
    }

    /**
     * Sets main hand dps.
     *
     * @param mainHandDps the main hand dps
     */
    public void setMainHandDps(float mainHandDps) {
        this.mainHandDps = mainHandDps;
    }

    /**
     * Gets off hand damage min.
     *
     * @return the off hand damage min
     */
    public float getOffHandDamageMin() {
        return offHandDamageMin;
    }

    /**
     * Sets off hand damage min.
     *
     * @param offHandDamageMin the off hand damage min
     */
    public void setOffHandDamageMin(float offHandDamageMin) {
        this.offHandDamageMin = offHandDamageMin;
    }

    /**
     * Gets off hand damage max.
     *
     * @return the off hand damage max
     */
    public float getOffHandDamageMax() {
        return offHandDamageMax;
    }

    /**
     * Sets off hand damage max.
     *
     * @param offHandDamageMax the off hand damage max
     */
    public void setOffHandDamageMax(float offHandDamageMax) {
        this.offHandDamageMax = offHandDamageMax;
    }

    /**
     * Gets off hand speed.
     *
     * @return the off hand speed
     */
    public float getOffHandSpeed() {
        return offHandSpeed;
    }

    /**
     * Sets off hand speed.
     *
     * @param offHandSpeed the off hand speed
     */
    public void setOffHandSpeed(float offHandSpeed) {
        this.offHandSpeed = offHandSpeed;
    }

    /**
     * Gets off hand dps.
     *
     * @return the off hand dps
     */
    public float getOffHandDps() {
        return offHandDps;
    }

    /**
     * Sets off hand dps.
     *
     * @param offHandDps the off hand dps
     */
    public void setOffHandDps(float offHandDps) {
        this.offHandDps = offHandDps;
    }

    /**
     * Gets spell power.
     *
     * @return the spell power
     */
    public int getSpellPower() {
        return spellPower;
    }

    /**
     * Sets spell power.
     *
     * @param spellPower the spell power
     */
    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }

    /**
     * Gets spell penetration.
     *
     * @return the spell penetration
     */
    public int getSpellPenetration() {
        return spellPenetration;
    }

    /**
     * Sets spell penetration.
     *
     * @param spellPenetration the spell penetration
     */
    public void setSpellPenetration(int spellPenetration) {
        this.spellPenetration = spellPenetration;
    }

    /**
     * Gets spell crit.
     *
     * @return the spell crit
     */
    public SpellCrit getSpellCrit() {
        return spellCrit;
    }

    /**
     * Sets spell crit.
     *
     * @param spellCrit the spell crit
     */
    public void setSpellCrit(SpellCrit spellCrit) {
        this.spellCrit = spellCrit;
    }

    /**
     * Gets mana regen.
     *
     * @return the mana regen
     */
    public float getManaRegen() {
        return manaRegen;
    }

    /**
     * Sets mana regen.
     *
     * @param manaRegen the mana regen
     */
    public void setManaRegen(float manaRegen) {
        this.manaRegen = manaRegen;
    }

    /**
     * Gets mana regen combat.
     *
     * @return the mana regen combat
     */
    public float getManaRegenCombat() {
        return manaRegenCombat;
    }

    /**
     * Sets mana regen combat.
     *
     * @param manaRegenCombat the mana regen combat
     */
    public void setManaRegenCombat(float manaRegenCombat) {
        this.manaRegenCombat = manaRegenCombat;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public Armor getArmor() {
        return armor;
    }

    /**
     * Sets armor.
     *
     * @param armor the armor
     */
    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * Gets dodge.
     *
     * @return the dodge
     */
    public Dodge getDodge() {
        return dodge;
    }

    /**
     * Sets dodge.
     *
     * @param dodge the dodge
     */
    public void setDodge(Dodge dodge) {
        this.dodge = dodge;
    }

    /**
     * Gets parry.
     *
     * @return the parry
     */
    public Parry getParry() {
        return parry;
    }

    /**
     * Sets parry.
     *
     * @param parry the parry
     */
    public void setParry(Parry parry) {
        this.parry = parry;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Sets block.
     *
     * @param block the block
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * Gets ranged crit.
     *
     * @return the ranged crit
     */
    public RangedCrit getRangedCrit() {
        return rangedCrit;
    }

    /**
     * Sets ranged crit.
     *
     * @param rangedCrit the ranged crit
     */
    public void setRangedCrit(RangedCrit rangedCrit) {
        this.rangedCrit = rangedCrit;
    }

    /**
     * Gets ranged haste.
     *
     * @return the ranged haste
     */
    public RangedHaste getRangedHaste() {
        return rangedHaste;
    }

    /**
     * Sets ranged haste.
     *
     * @param rangedHaste the ranged haste
     */
    public void setRangedHaste(RangedHaste rangedHaste) {
        this.rangedHaste = rangedHaste;
    }

    /**
     * Gets spell haste.
     *
     * @return the spell haste
     */
    public SpellHaste getSpellHaste() {
        return spellHaste;
    }

    /**
     * Sets spell haste.
     *
     * @param spellHaste the spell haste
     */
    public void setSpellHaste(SpellHaste spellHaste) {
        this.spellHaste = spellHaste;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets character.
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

}
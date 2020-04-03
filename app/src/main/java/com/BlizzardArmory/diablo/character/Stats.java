package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Stats.
 */
public class Stats {

    @SerializedName("life")
    @Expose
    private double life;
    @SerializedName("damage")
    @Expose
    private int damage;
    @SerializedName("toughness")
    @Expose
    private int toughness;
    @SerializedName("healing")
    @Expose
    private int healing;
    @SerializedName("attackSpeed")
    @Expose
    private double attackSpeed;
    @SerializedName("armor")
    @Expose
    private double armor;
    @SerializedName("strength")
    @Expose
    private double strength;
    @SerializedName("dexterity")
    @Expose
    private double dexterity;
    @SerializedName("vitality")
    @Expose
    private double vitality;
    @SerializedName("intelligence")
    @Expose
    private double intelligence;
    @SerializedName("physicalResist")
    @Expose
    private int physicalResist;
    @SerializedName("fireResist")
    @Expose
    private int fireResist;
    @SerializedName("coldResist")
    @Expose
    private int coldResist;
    @SerializedName("lightningResist")
    @Expose
    private int lightningResist;
    @SerializedName("poisonResist")
    @Expose
    private int poisonResist;
    @SerializedName("arcaneResist")
    @Expose
    private int arcaneResist;
    @SerializedName("blockChance")
    @Expose
    private double blockChance;
    @SerializedName("blockAmountMin")
    @Expose
    private double blockAmountMin;
    @SerializedName("blockAmountMax")
    @Expose
    private double blockAmountMax;
    @SerializedName("goldFind")
    @Expose
    private double goldFind;
    @SerializedName("critChance")
    @Expose
    private double critChance;
    @SerializedName("thorns")
    @Expose
    private double thorns;
    @SerializedName("lifeSteal")
    @Expose
    private double lifeSteal;
    @SerializedName("lifePerKill")
    @Expose
    private double lifePerKill;
    @SerializedName("lifeOnHit")
    @Expose
    private double lifeOnHit;
    @SerializedName("primaryResource")
    @Expose
    private int primaryResource;
    @SerializedName("secondaryResource")
    @Expose
    private int secondaryResource;

    /**
     * Gets life.
     *
     * @return the life
     */
    public double getLife() {
        return life;
    }

    /**
     * Sets life.
     *
     * @param life the life
     */
    public void setLife(double life) {
        this.life = life;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets toughness.
     *
     * @return the toughness
     */
    public int getToughness() {
        return toughness;
    }

    /**
     * Sets toughness.
     *
     * @param toughness the toughness
     */
    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    /**
     * Gets healing.
     *
     * @return the healing
     */
    public int getHealing() {
        return healing;
    }

    /**
     * Sets healing.
     *
     * @param healing the healing
     */
    public void setHealing(int healing) {
        this.healing = healing;
    }

    /**
     * Gets attack speed.
     *
     * @return the attack speed
     */
    public double getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Sets attack speed.
     *
     * @param attackSpeed the attack speed
     */
    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public double getArmor() {
        return armor;
    }

    /**
     * Sets armor.
     *
     * @param armor the armor
     */
    public void setArmor(double armor) {
        this.armor = armor;
    }

    /**
     * Gets strength.
     *
     * @return the strength
     */
    public double getStrength() {
        return strength;
    }

    /**
     * Sets strength.
     *
     * @param strength the strength
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    /**
     * Gets dexterity.
     *
     * @return the dexterity
     */
    public double getDexterity() {
        return dexterity;
    }

    /**
     * Sets dexterity.
     *
     * @param dexterity the dexterity
     */
    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Gets vitality.
     *
     * @return the vitality
     */
    public double getVitality() {
        return vitality;
    }

    /**
     * Sets vitality.
     *
     * @param vitality the vitality
     */
    public void setVitality(double vitality) {
        this.vitality = vitality;
    }

    /**
     * Gets intelligence.
     *
     * @return the intelligence
     */
    public double getIntelligence() {
        return intelligence;
    }

    /**
     * Sets intelligence.
     *
     * @param intelligence the intelligence
     */
    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * Gets physical resist.
     *
     * @return the physical resist
     */
    public int getPhysicalResist() {
        return physicalResist;
    }

    /**
     * Sets physical resist.
     *
     * @param physicalResist the physical resist
     */
    public void setPhysicalResist(int physicalResist) {
        this.physicalResist = physicalResist;
    }

    /**
     * Gets fire resist.
     *
     * @return the fire resist
     */
    public int getFireResist() {
        return fireResist;
    }

    /**
     * Sets fire resist.
     *
     * @param fireResist the fire resist
     */
    public void setFireResist(int fireResist) {
        this.fireResist = fireResist;
    }

    /**
     * Gets cold resist.
     *
     * @return the cold resist
     */
    public int getColdResist() {
        return coldResist;
    }

    /**
     * Sets cold resist.
     *
     * @param coldResist the cold resist
     */
    public void setColdResist(int coldResist) {
        this.coldResist = coldResist;
    }

    /**
     * Gets lightning resist.
     *
     * @return the lightning resist
     */
    public int getLightningResist() {
        return lightningResist;
    }

    /**
     * Sets lightning resist.
     *
     * @param lightningResist the lightning resist
     */
    public void setLightningResist(int lightningResist) {
        this.lightningResist = lightningResist;
    }

    /**
     * Gets poison resist.
     *
     * @return the poison resist
     */
    public int getPoisonResist() {
        return poisonResist;
    }

    /**
     * Sets poison resist.
     *
     * @param poisonResist the poison resist
     */
    public void setPoisonResist(int poisonResist) {
        this.poisonResist = poisonResist;
    }

    /**
     * Gets arcane resist.
     *
     * @return the arcane resist
     */
    public int getArcaneResist() {
        return arcaneResist;
    }

    /**
     * Sets arcane resist.
     *
     * @param arcaneResist the arcane resist
     */
    public void setArcaneResist(int arcaneResist) {
        this.arcaneResist = arcaneResist;
    }

    /**
     * Gets block chance.
     *
     * @return the block chance
     */
    public double getBlockChance() {
        return blockChance;
    }

    /**
     * Sets block chance.
     *
     * @param blockChance the block chance
     */
    public void setBlockChance(double blockChance) {
        this.blockChance = blockChance;
    }

    /**
     * Gets block amount min.
     *
     * @return the block amount min
     */
    public double getBlockAmountMin() {
        return blockAmountMin;
    }

    /**
     * Sets block amount min.
     *
     * @param blockAmountMin the block amount min
     */
    public void setBlockAmountMin(double blockAmountMin) {
        this.blockAmountMin = blockAmountMin;
    }

    /**
     * Gets block amount max.
     *
     * @return the block amount max
     */
    public double getBlockAmountMax() {
        return blockAmountMax;
    }

    /**
     * Sets block amount max.
     *
     * @param blockAmountMax the block amount max
     */
    public void setBlockAmountMax(double blockAmountMax) {
        this.blockAmountMax = blockAmountMax;
    }

    /**
     * Gets gold find.
     *
     * @return the gold find
     */
    public double getGoldFind() {
        return goldFind;
    }

    /**
     * Sets gold find.
     *
     * @param goldFind the gold find
     */
    public void setGoldFind(double goldFind) {
        this.goldFind = goldFind;
    }

    /**
     * Gets crit chance.
     *
     * @return the crit chance
     */
    public double getCritChance() {
        return critChance;
    }

    /**
     * Sets crit chance.
     *
     * @param critChance the crit chance
     */
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    /**
     * Gets thorns.
     *
     * @return the thorns
     */
    public double getThorns() {
        return thorns;
    }

    /**
     * Sets thorns.
     *
     * @param thorns the thorns
     */
    public void setThorns(double thorns) {
        this.thorns = thorns;
    }

    /**
     * Gets life steal.
     *
     * @return the life steal
     */
    public double getLifeSteal() {
        return lifeSteal;
    }

    /**
     * Sets life steal.
     *
     * @param lifeSteal the life steal
     */
    public void setLifeSteal(double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    /**
     * Gets life per kill.
     *
     * @return the life per kill
     */
    public double getLifePerKill() {
        return lifePerKill;
    }

    /**
     * Sets life per kill.
     *
     * @param lifePerKill the life per kill
     */
    public void setLifePerKill(double lifePerKill) {
        this.lifePerKill = lifePerKill;
    }

    /**
     * Gets life on hit.
     *
     * @return the life on hit
     */
    public double getLifeOnHit() {
        return lifeOnHit;
    }

    /**
     * Sets life on hit.
     *
     * @param lifeOnHit the life on hit
     */
    public void setLifeOnHit(double lifeOnHit) {
        this.lifeOnHit = lifeOnHit;
    }

    /**
     * Gets primary resource.
     *
     * @return the primary resource
     */
    public int getPrimaryResource() {
        return primaryResource;
    }

    /**
     * Sets primary resource.
     *
     * @param primaryResource the primary resource
     */
    public void setPrimaryResource(int primaryResource) {
        this.primaryResource = primaryResource;
    }

    /**
     * Gets secondary resource.
     *
     * @return the secondary resource
     */
    public int getSecondaryResource() {
        return secondaryResource;
    }

    /**
     * Sets secondary resource.
     *
     * @param secondaryResource the secondary resource
     */
    public void setSecondaryResource(int secondaryResource) {
        this.secondaryResource = secondaryResource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("life", life).append("damage", damage).append("toughness", toughness).append("healing", healing).append("attackSpeed", attackSpeed).append("armor", armor).append("strength", strength).append("dexterity", dexterity).append("vitality", vitality).append("intelligence", intelligence).append("physicalResist", physicalResist).append("fireResist", fireResist).append("coldResist", coldResist).append("lightningResist", lightningResist).append("poisonResist", poisonResist).append("arcaneResist", arcaneResist).append("blockChance", blockChance).append("blockAmountMin", blockAmountMin).append("blockAmountMax", blockAmountMax).append("goldFind", goldFind).append("critChance", critChance).append("thorns", thorns).append("lifeSteal", lifeSteal).append("lifePerKill", lifePerKill).append("lifeOnHit", lifeOnHit).append("primaryResource", primaryResource).append("secondaryResource", secondaryResource).toString();
    }

}
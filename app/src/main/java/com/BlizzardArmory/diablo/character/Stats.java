package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Stats {

    @SerializedName("life")
    @Expose
    private double life;
    @SerializedName("damage")
    @Expose
    private double damage;
    @SerializedName("toughness")
    @Expose
    private double toughness;
    @SerializedName("healing")
    @Expose
    private double healing;
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
    private double physicalResist;
    @SerializedName("fireResist")
    @Expose
    private double fireResist;
    @SerializedName("coldResist")
    @Expose
    private double coldResist;
    @SerializedName("lightningResist")
    @Expose
    private double lightningResist;
    @SerializedName("poisonResist")
    @Expose
    private double poisonResist;
    @SerializedName("arcaneResist")
    @Expose
    private double arcaneResist;
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
    private double primaryResource;
    @SerializedName("secondaryResource")
    @Expose
    private double secondaryResource;

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getToughness() {
        return toughness;
    }

    public void setToughness(double toughness) {
        this.toughness = toughness;
    }

    public double getHealing() {
        return healing;
    }

    public void setHealing(double healing) {
        this.healing = healing;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getVitality() {
        return vitality;
    }

    public void setVitality(double vitality) {
        this.vitality = vitality;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getPhysicalResist() {
        return physicalResist;
    }

    public void setPhysicalResist(double physicalResist) {
        this.physicalResist = physicalResist;
    }

    public double getFireResist() {
        return fireResist;
    }

    public void setFireResist(double fireResist) {
        this.fireResist = fireResist;
    }

    public double getColdResist() {
        return coldResist;
    }

    public void setColdResist(double coldResist) {
        this.coldResist = coldResist;
    }

    public double getLightningResist() {
        return lightningResist;
    }

    public void setLightningResist(double lightningResist) {
        this.lightningResist = lightningResist;
    }

    public double getPoisonResist() {
        return poisonResist;
    }

    public void setPoisonResist(double poisonResist) {
        this.poisonResist = poisonResist;
    }

    public double getArcaneResist() {
        return arcaneResist;
    }

    public void setArcaneResist(double arcaneResist) {
        this.arcaneResist = arcaneResist;
    }

    public double getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(double blockChance) {
        this.blockChance = blockChance;
    }

    public double getBlockAmountMin() {
        return blockAmountMin;
    }

    public void setBlockAmountMin(double blockAmountMin) {
        this.blockAmountMin = blockAmountMin;
    }

    public double getBlockAmountMax() {
        return blockAmountMax;
    }

    public void setBlockAmountMax(double blockAmountMax) {
        this.blockAmountMax = blockAmountMax;
    }

    public double getGoldFind() {
        return goldFind;
    }

    public void setGoldFind(double goldFind) {
        this.goldFind = goldFind;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public double getThorns() {
        return thorns;
    }

    public void setThorns(double thorns) {
        this.thorns = thorns;
    }

    public double getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public double getLifePerKill() {
        return lifePerKill;
    }

    public void setLifePerKill(double lifePerKill) {
        this.lifePerKill = lifePerKill;
    }

    public double getLifeOnHit() {
        return lifeOnHit;
    }

    public void setLifeOnHit(double lifeOnHit) {
        this.lifeOnHit = lifeOnHit;
    }

    public double getPrimaryResource() {
        return primaryResource;
    }

    public void setPrimaryResource(double primaryResource) {
        this.primaryResource = primaryResource;
    }

    public double getSecondaryResource() {
        return secondaryResource;
    }

    public void setSecondaryResource(double secondaryResource) {
        this.secondaryResource = secondaryResource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("life", life).append("damage", damage).append("toughness", toughness).append("healing", healing).append("attackSpeed", attackSpeed).append("armor", armor).append("strength", strength).append("dexterity", dexterity).append("vitality", vitality).append("intelligence", intelligence).append("physicalResist", physicalResist).append("fireResist", fireResist).append("coldResist", coldResist).append("lightningResist", lightningResist).append("poisonResist", poisonResist).append("arcaneResist", arcaneResist).append("blockChance", blockChance).append("blockAmountMin", blockAmountMin).append("blockAmountMax", blockAmountMax).append("goldFind", goldFind).append("critChance", critChance).append("thorns", thorns).append("lifeSteal", lifeSteal).append("lifePerKill", lifePerKill).append("lifeOnHit", lifeOnHit).append("primaryResource", primaryResource).append("secondaryResource", secondaryResource).toString();
    }

}
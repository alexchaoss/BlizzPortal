package com.BlizzardArmory.diablo.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Stats {

    @SerializedName("life")
    @Expose
    private Double life;
    @SerializedName("damage")
    @Expose
    private Double damage;
    @SerializedName("toughness")
    @Expose
    private Double toughness;
    @SerializedName("healing")
    @Expose
    private Double healing;
    @SerializedName("attackSpeed")
    @Expose
    private Double attackSpeed;
    @SerializedName("armor")
    @Expose
    private Double armor;
    @SerializedName("strength")
    @Expose
    private Double strength;
    @SerializedName("dexterity")
    @Expose
    private Double dexterity;
    @SerializedName("vitality")
    @Expose
    private Double vitality;
    @SerializedName("intelligence")
    @Expose
    private Double intelligence;
    @SerializedName("physicalResist")
    @Expose
    private Double physicalResist;
    @SerializedName("fireResist")
    @Expose
    private Double fireResist;
    @SerializedName("coldResist")
    @Expose
    private Double coldResist;
    @SerializedName("lightningResist")
    @Expose
    private Double lightningResist;
    @SerializedName("poisonResist")
    @Expose
    private Double poisonResist;
    @SerializedName("arcaneResist")
    @Expose
    private Double arcaneResist;
    @SerializedName("blockChance")
    @Expose
    private Double blockChance;
    @SerializedName("blockAmountMin")
    @Expose
    private Double blockAmountMin;
    @SerializedName("blockAmountMax")
    @Expose
    private Double blockAmountMax;
    @SerializedName("goldFind")
    @Expose
    private Double goldFind;
    @SerializedName("critChance")
    @Expose
    private Double critChance;
    @SerializedName("thorns")
    @Expose
    private Double thorns;
    @SerializedName("lifeSteal")
    @Expose
    private Double lifeSteal;
    @SerializedName("lifePerKill")
    @Expose
    private Double lifePerKill;
    @SerializedName("lifeOnHit")
    @Expose
    private Double lifeOnHit;
    @SerializedName("primaryResource")
    @Expose
    private Double primaryResource;
    @SerializedName("secondaryResource")
    @Expose
    private Double secondaryResource;

    public Double getLife() {
        return life;
    }

    public void setLife(Double life) {
        this.life = life;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Double getToughness() {
        return toughness;
    }

    public void setToughness(Double toughness) {
        this.toughness = toughness;
    }

    public Double getHealing() {
        return healing;
    }

    public void setHealing(Double healing) {
        this.healing = healing;
    }

    public Double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(Double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Double getArmor() {
        return armor;
    }

    public void setArmor(Double armor) {
        this.armor = armor;
    }

    public Double getStrength() {
        return strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Double getDexterity() {
        return dexterity;
    }

    public void setDexterity(Double dexterity) {
        this.dexterity = dexterity;
    }

    public Double getVitality() {
        return vitality;
    }

    public void setVitality(Double vitality) {
        this.vitality = vitality;
    }

    public Double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Double intelligence) {
        this.intelligence = intelligence;
    }

    public Double getPhysicalResist() {
        return physicalResist;
    }

    public void setPhysicalResist(Double physicalResist) {
        this.physicalResist = physicalResist;
    }

    public Double getFireResist() {
        return fireResist;
    }

    public void setFireResist(Double fireResist) {
        this.fireResist = fireResist;
    }

    public Double getColdResist() {
        return coldResist;
    }

    public void setColdResist(Double coldResist) {
        this.coldResist = coldResist;
    }

    public Double getLightningResist() {
        return lightningResist;
    }

    public void setLightningResist(Double lightningResist) {
        this.lightningResist = lightningResist;
    }

    public Double getPoisonResist() {
        return poisonResist;
    }

    public void setPoisonResist(Double poisonResist) {
        this.poisonResist = poisonResist;
    }

    public Double getArcaneResist() {
        return arcaneResist;
    }

    public void setArcaneResist(Double arcaneResist) {
        this.arcaneResist = arcaneResist;
    }

    public Double getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Double blockChance) {
        this.blockChance = blockChance;
    }

    public Double getBlockAmountMin() {
        return blockAmountMin;
    }

    public void setBlockAmountMin(Double blockAmountMin) {
        this.blockAmountMin = blockAmountMin;
    }

    public Double getBlockAmountMax() {
        return blockAmountMax;
    }

    public void setBlockAmountMax(Double blockAmountMax) {
        this.blockAmountMax = blockAmountMax;
    }

    public Double getGoldFind() {
        return goldFind;
    }

    public void setGoldFind(Double goldFind) {
        this.goldFind = goldFind;
    }

    public Double getCritChance() {
        return critChance;
    }

    public void setCritChance(Double critChance) {
        this.critChance = critChance;
    }

    public Double getThorns() {
        return thorns;
    }

    public void setThorns(Double thorns) {
        this.thorns = thorns;
    }

    public Double getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(Double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public Double getLifePerKill() {
        return lifePerKill;
    }

    public void setLifePerKill(Double lifePerKill) {
        this.lifePerKill = lifePerKill;
    }

    public Double getLifeOnHit() {
        return lifeOnHit;
    }

    public void setLifeOnHit(Double lifeOnHit) {
        this.lifeOnHit = lifeOnHit;
    }

    public Double getPrimaryResource() {
        return primaryResource;
    }

    public void setPrimaryResource(Double primaryResource) {
        this.primaryResource = primaryResource;
    }

    public Double getSecondaryResource() {
        return secondaryResource;
    }

    public void setSecondaryResource(Double secondaryResource) {
        this.secondaryResource = secondaryResource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("life", life).append("damage", damage).append("toughness", toughness).append("healing", healing).append("attackSpeed", attackSpeed).append("armor", armor).append("strength", strength).append("dexterity", dexterity).append("vitality", vitality).append("intelligence", intelligence).append("physicalResist", physicalResist).append("fireResist", fireResist).append("coldResist", coldResist).append("lightningResist", lightningResist).append("poisonResist", poisonResist).append("arcaneResist", arcaneResist).append("blockChance", blockChance).append("blockAmountMin", blockAmountMin).append("blockAmountMax", blockAmountMax).append("goldFind", goldFind).append("critChance", critChance).append("thorns", thorns).append("lifeSteal", lifeSteal).append("lifePerKill", lifePerKill).append("lifeOnHit", lifeOnHit).append("primaryResource", primaryResource).append("secondaryResource", secondaryResource).toString();
    }

}
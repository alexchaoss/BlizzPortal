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
    private Integer damage;
    @SerializedName("toughness")
    @Expose
    private Integer toughness;
    @SerializedName("healing")
    @Expose
    private Integer healing;
    @SerializedName("attackSpeed")
    @Expose
    private Double attackSpeed;
    @SerializedName("armor")
    @Expose
    private Integer armor;
    @SerializedName("strength")
    @Expose
    private Integer strength;
    @SerializedName("dexterity")
    @Expose
    private Integer dexterity;
    @SerializedName("vitality")
    @Expose
    private Integer vitality;
    @SerializedName("intelligence")
    @Expose
    private Integer intelligence;
    @SerializedName("physicalResist")
    @Expose
    private Integer physicalResist;
    @SerializedName("fireResist")
    @Expose
    private Integer fireResist;
    @SerializedName("coldResist")
    @Expose
    private Integer coldResist;
    @SerializedName("lightningResist")
    @Expose
    private Integer lightningResist;
    @SerializedName("poisonResist")
    @Expose
    private Integer poisonResist;
    @SerializedName("arcaneResist")
    @Expose
    private Integer arcaneResist;
    @SerializedName("blockChance")
    @Expose
    private Integer blockChance;
    @SerializedName("blockAmountMin")
    @Expose
    private Integer blockAmountMin;
    @SerializedName("blockAmountMax")
    @Expose
    private Integer blockAmountMax;
    @SerializedName("goldFind")
    @Expose
    private Double goldFind;
    @SerializedName("critChance")
    @Expose
    private Double critChance;
    @SerializedName("thorns")
    @Expose
    private Integer thorns;
    @SerializedName("lifeSteal")
    @Expose
    private Integer lifeSteal;
    @SerializedName("lifePerKill")
    @Expose
    private Integer lifePerKill;
    @SerializedName("lifeOnHit")
    @Expose
    private Integer lifeOnHit;
    @SerializedName("primaryResource")
    @Expose
    private Integer primaryResource;
    @SerializedName("secondaryResource")
    @Expose
    private Integer secondaryResource;

    public Double getLife() {
        return life;
    }

    public void setLife(Double life) {
        this.life = life;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getToughness() {
        return toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    public Integer getHealing() {
        return healing;
    }

    public void setHealing(Integer healing) {
        this.healing = healing;
    }

    public Double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(Double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getPhysicalResist() {
        return physicalResist;
    }

    public void setPhysicalResist(Integer physicalResist) {
        this.physicalResist = physicalResist;
    }

    public Integer getFireResist() {
        return fireResist;
    }

    public void setFireResist(Integer fireResist) {
        this.fireResist = fireResist;
    }

    public Integer getColdResist() {
        return coldResist;
    }

    public void setColdResist(Integer coldResist) {
        this.coldResist = coldResist;
    }

    public Integer getLightningResist() {
        return lightningResist;
    }

    public void setLightningResist(Integer lightningResist) {
        this.lightningResist = lightningResist;
    }

    public Integer getPoisonResist() {
        return poisonResist;
    }

    public void setPoisonResist(Integer poisonResist) {
        this.poisonResist = poisonResist;
    }

    public Integer getArcaneResist() {
        return arcaneResist;
    }

    public void setArcaneResist(Integer arcaneResist) {
        this.arcaneResist = arcaneResist;
    }

    public Integer getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Integer blockChance) {
        this.blockChance = blockChance;
    }

    public Integer getBlockAmountMin() {
        return blockAmountMin;
    }

    public void setBlockAmountMin(Integer blockAmountMin) {
        this.blockAmountMin = blockAmountMin;
    }

    public Integer getBlockAmountMax() {
        return blockAmountMax;
    }

    public void setBlockAmountMax(Integer blockAmountMax) {
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

    public Integer getThorns() {
        return thorns;
    }

    public void setThorns(Integer thorns) {
        this.thorns = thorns;
    }

    public Integer getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(Integer lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public Integer getLifePerKill() {
        return lifePerKill;
    }

    public void setLifePerKill(Integer lifePerKill) {
        this.lifePerKill = lifePerKill;
    }

    public Integer getLifeOnHit() {
        return lifeOnHit;
    }

    public void setLifeOnHit(Integer lifeOnHit) {
        this.lifeOnHit = lifeOnHit;
    }

    public Integer getPrimaryResource() {
        return primaryResource;
    }

    public void setPrimaryResource(Integer primaryResource) {
        this.primaryResource = primaryResource;
    }

    public Integer getSecondaryResource() {
        return secondaryResource;
    }

    public void setSecondaryResource(Integer secondaryResource) {
        this.secondaryResource = secondaryResource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("life", life).append("damage", damage).append("toughness", toughness).append("healing", healing).append("attackSpeed", attackSpeed).append("armor", armor).append("strength", strength).append("dexterity", dexterity).append("vitality", vitality).append("intelligence", intelligence).append("physicalResist", physicalResist).append("fireResist", fireResist).append("coldResist", coldResist).append("lightningResist", lightningResist).append("poisonResist", poisonResist).append("arcaneResist", arcaneResist).append("blockChance", blockChance).append("blockAmountMin", blockAmountMin).append("blockAmountMax", blockAmountMax).append("goldFind", goldFind).append("critChance", critChance).append("thorns", thorns).append("lifeSteal", lifeSteal).append("lifePerKill", lifePerKill).append("lifeOnHit", lifeOnHit).append("primaryResource", primaryResource).append("secondaryResource", secondaryResource).toString();
    }

}
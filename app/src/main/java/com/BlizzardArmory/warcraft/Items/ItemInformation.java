package com.BlizzardArmory.warcraft.Items;

import com.BlizzardArmory.warcraft.Spells.ItemSpell;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class ItemInformation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("stackable")
    @Expose
    private Integer stackable;
    @SerializedName("allowableClasses")
    @Expose
    private List<Integer> allowableClasses = null;
    @SerializedName("itemBind")
    @Expose
    private Integer itemBind;
    @SerializedName("bonusStats")
    @Expose
    private List<BonusStat> bonusStats = null;
    @SerializedName("itemSpells")
    @Expose
    private List<ItemSpell> itemSpells = null;
    @SerializedName("buyPrice")
    @Expose
    private Integer buyPrice;
    @SerializedName("itemClass")
    @Expose
    private Integer itemClass;
    @SerializedName("itemSubClass")
    @Expose
    private Integer itemSubClass;
    @SerializedName("containerSlots")
    @Expose
    private Integer containerSlots;
    @SerializedName("weaponInfo")
    @Expose
    private WeaponInfo weaponInfo;
    @SerializedName("inventoryType")
    @Expose
    private Integer inventoryType;
    @SerializedName("equippable")
    @Expose
    private Boolean equippable;
    @SerializedName("itemLevel")
    @Expose
    private Integer itemLevel;
    @SerializedName("maxCount")
    @Expose
    private Integer maxCount;
    @SerializedName("maxDurability")
    @Expose
    private Integer maxDurability;
    @SerializedName("minFactionId")
    @Expose
    private Integer minFactionId;
    @SerializedName("minReputation")
    @Expose
    private Integer minReputation;
    @SerializedName("quality")
    @Expose
    private Integer quality;
    @SerializedName("sellPrice")
    @Expose
    private Integer sellPrice;
    @SerializedName("requiredSkill")
    @Expose
    private Integer requiredSkill;
    @SerializedName("requiredLevel")
    @Expose
    private Integer requiredLevel;
    @SerializedName("requiredSkillRank")
    @Expose
    private Integer requiredSkillRank;
    @SerializedName("itemSource")
    @Expose
    private ItemSource itemSource;
    @SerializedName("baseArmor")
    @Expose
    private Integer baseArmor;
    @SerializedName("hasSockets")
    @Expose
    private Boolean hasSockets;
    @SerializedName("isAuctionable")
    @Expose
    private Boolean isAuctionable;
    @SerializedName("armor")
    @Expose
    private Integer armor;
    @SerializedName("displayInfoId")
    @Expose
    private Integer displayInfoId;
    @SerializedName("nameDescription")
    @Expose
    private String nameDescription;
    @SerializedName("nameDescriptionColor")
    @Expose
    private String nameDescriptionColor;
    @SerializedName("upgradable")
    @Expose
    private Boolean upgradable;
    @SerializedName("heroicTooltip")
    @Expose
    private Boolean heroicTooltip;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("bonusLists")
    @Expose
    private List<Object> bonusLists = null;
    @SerializedName("availableContexts")
    @Expose
    private List<String> availableContexts = null;
    @SerializedName("bonusSummary")
    @Expose
    private BonusSummary bonusSummary;
    @SerializedName("artifactId")
    @Expose
    private Integer artifactId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStackable() {
        return stackable;
    }

    public void setStackable(Integer stackable) {
        this.stackable = stackable;
    }

    public List<Integer> getAllowableClasses() {
        return allowableClasses;
    }

    public void setAllowableClasses(List<Integer> allowableClasses) {
        this.allowableClasses = allowableClasses;
    }

    public Integer getItemBind() {
        return itemBind;
    }

    public void setItemBind(Integer itemBind) {
        this.itemBind = itemBind;
    }

    public List<BonusStat> getBonusStats() {
        return bonusStats;
    }

    public void setBonusStats(List<BonusStat> bonusStats) {
        this.bonusStats = bonusStats;
    }

    public List<ItemSpell> getItemSpells() {
        return itemSpells;
    }

    public void setItemSpells(List<ItemSpell> itemSpells) {
        this.itemSpells = itemSpells;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getItemClass() {
        return itemClass;
    }

    public void setItemClass(Integer itemClass) {
        this.itemClass = itemClass;
    }

    public Integer getItemSubClass() {
        return itemSubClass;
    }

    public void setItemSubClass(Integer itemSubClass) {
        this.itemSubClass = itemSubClass;
    }

    public Integer getContainerSlots() {
        return containerSlots;
    }

    public void setContainerSlots(Integer containerSlots) {
        this.containerSlots = containerSlots;
    }

    public WeaponInfo getWeaponInfo() {
        return weaponInfo;
    }

    public void setWeaponInfo(WeaponInfo weaponInfo) {
        this.weaponInfo = weaponInfo;
    }

    public Integer getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(Integer inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Boolean getEquippable() {
        return equippable;
    }

    public void setEquippable(Boolean equippable) {
        this.equippable = equippable;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(Integer maxDurability) {
        this.maxDurability = maxDurability;
    }

    public Integer getMinFactionId() {
        return minFactionId;
    }

    public void setMinFactionId(Integer minFactionId) {
        this.minFactionId = minFactionId;
    }

    public Integer getMinReputation() {
        return minReputation;
    }

    public void setMinReputation(Integer minReputation) {
        this.minReputation = minReputation;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(Integer requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public Integer getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Integer requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public Integer getRequiredSkillRank() {
        return requiredSkillRank;
    }

    public void setRequiredSkillRank(Integer requiredSkillRank) {
        this.requiredSkillRank = requiredSkillRank;
    }

    public ItemSource getItemSource() {
        return itemSource;
    }

    public void setItemSource(ItemSource itemSource) {
        this.itemSource = itemSource;
    }

    public Integer getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(Integer baseArmor) {
        this.baseArmor = baseArmor;
    }

    public Boolean getHasSockets() {
        return hasSockets;
    }

    public void setHasSockets(Boolean hasSockets) {
        this.hasSockets = hasSockets;
    }

    public Boolean getIsAuctionable() {
        return isAuctionable;
    }

    public void setIsAuctionable(Boolean isAuctionable) {
        this.isAuctionable = isAuctionable;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Integer displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public String getNameDescription() {
        return nameDescription;
    }

    public void setNameDescription(String nameDescription) {
        this.nameDescription = nameDescription;
    }

    public String getNameDescriptionColor() {
        return nameDescriptionColor;
    }

    public void setNameDescriptionColor(String nameDescriptionColor) {
        this.nameDescriptionColor = nameDescriptionColor;
    }

    public Boolean getUpgradable() {
        return upgradable;
    }

    public void setUpgradable(Boolean upgradable) {
        this.upgradable = upgradable;
    }

    public Boolean getHeroicTooltip() {
        return heroicTooltip;
    }

    public void setHeroicTooltip(Boolean heroicTooltip) {
        this.heroicTooltip = heroicTooltip;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Object> getBonusLists() {
        return bonusLists;
    }

    public void setBonusLists(List<Object> bonusLists) {
        this.bonusLists = bonusLists;
    }

    public List<String> getAvailableContexts() {
        return availableContexts;
    }

    public void setAvailableContexts(List<String> availableContexts) {
        this.availableContexts = availableContexts;
    }

    public BonusSummary getBonusSummary() {
        return bonusSummary;
    }

    public void setBonusSummary(BonusSummary bonusSummary) {
        this.bonusSummary = bonusSummary;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("description", description).append("name", name).append("icon", icon).append("stackable", stackable).append("allowableClasses", allowableClasses).append("itemBind", itemBind).append("bonusStats", bonusStats).append("itemSpells", itemSpells).append("buyPrice", buyPrice).append("itemClass", itemClass).append("itemSubClass", itemSubClass).append("containerSlots", containerSlots).append("weaponInfo", weaponInfo).append("inventoryType", inventoryType).append("equippable", equippable).append("itemLevel", itemLevel).append("maxCount", maxCount).append("maxDurability", maxDurability).append("minFactionId", minFactionId).append("minReputation", minReputation).append("quality", quality).append("sellPrice", sellPrice).append("requiredSkill", requiredSkill).append("requiredLevel", requiredLevel).append("requiredSkillRank", requiredSkillRank).append("itemSource", itemSource).append("baseArmor", baseArmor).append("hasSockets", hasSockets).append("isAuctionable", isAuctionable).append("armor", armor).append("displayInfoId", displayInfoId).append("nameDescription", nameDescription).append("nameDescriptionColor", nameDescriptionColor).append("upgradable", upgradable).append("heroicTooltip", heroicTooltip).append("context", context).append("bonusLists", bonusLists).append("availableContexts", availableContexts).append("bonusSummary", bonusSummary).append("artifactId", artifactId).toString();
    }


}

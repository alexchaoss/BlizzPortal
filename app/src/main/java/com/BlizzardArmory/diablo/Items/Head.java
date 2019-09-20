package com.BlizzardArmory.diablo.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Head {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("displayColor")
    @Expose
    private String displayColor;
    @SerializedName("tooltipParams")
    @Expose
    private String tooltipParams;
    @SerializedName("requiredLevel")
    @Expose
    private Double requiredLevel;
    @SerializedName("itemLevel")
    @Expose
    private Double itemLevel;
    @SerializedName("stackSizeMax")
    @Expose
    private Double stackSizeMax;
    @SerializedName("accountBound")
    @Expose
    private Boolean accountBound;
    @SerializedName("flavorText")
    @Expose
    private String flavorText;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("armor")
    @Expose
    private Double armor;
    @SerializedName("attacksPerSecond")
    @Expose
    private Double attacksPerSecond;
    @SerializedName("minDamage")
    @Expose
    private Double minDamage;
    @SerializedName("maxDamage")
    @Expose
    private Double maxDamage;
    @SerializedName("slots")
    @Expose
    private String slots;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("attributesHtml")
    @Expose
    private AttributesHtml attributesHtml;
    @SerializedName("openSockets")
    @Expose
    private Double openSockets;
    @SerializedName("gems")
    @Expose
    private List<Gem> gems = null;
    @SerializedName("set")
    @Expose
    private Set set;
    @SerializedName("seasonRequiredToDrop")
    @Expose
    private Double seasonRequiredToDrop;
    @SerializedName("dye")
    @Expose
    private Dye dye;
    @SerializedName("transmog")
    @Expose
    private Transmog transmog;
    @SerializedName("isSeasonRequiredToDrop")
    @Expose
    private Boolean isSeasonRequiredToDrop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public Double getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Double requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public Double getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Double itemLevel) {
        this.itemLevel = itemLevel;
    }

    public Double getStackSizeMax() {
        return stackSizeMax;
    }

    public void setStackSizeMax(Double stackSizeMax) {
        this.stackSizeMax = stackSizeMax;
    }

    public Boolean getAccountBound() {
        return accountBound;
    }

    public void setAccountBound(Boolean accountBound) {
        this.accountBound = accountBound;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getArmor() {
        return armor;
    }

    public void setArmor(Double armor) {
        this.armor = armor;
    }

    public Double getAttacksPerSecond() {
        return attacksPerSecond;
    }

    public void setAttacksPerSecond(Double attacksPerSecond) {
        this.attacksPerSecond = attacksPerSecond;
    }

    public Double getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(Double minDamage) {
        this.minDamage = minDamage;
    }

    public Double getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(Double maxDamage) {
        this.maxDamage = maxDamage;
    }

    public String getSlots() {
        return slots;
    }

    public void setSlots(String slots) {
        this.slots = slots;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public AttributesHtml getAttributesHtml() {
        return attributesHtml;
    }

    public void setAttributesHtml(AttributesHtml attributesHtml) {
        this.attributesHtml = attributesHtml;
    }

    public Double getOpenSockets() {
        return openSockets;
    }

    public void setOpenSockets(Double openSockets) {
        this.openSockets = openSockets;
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Double getSeasonRequiredToDrop() {
        return seasonRequiredToDrop;
    }

    public void setSeasonRequiredToDrop(Double seasonRequiredToDrop) {
        this.seasonRequiredToDrop = seasonRequiredToDrop;
    }

    public Dye getDye() {
        return dye;
    }

    public void setDye(Dye dye) {
        this.dye = dye;
    }

    public Transmog getTransmog() {
        return transmog;
    }

    public void setTransmog(Transmog transmog) {
        this.transmog = transmog;
    }

    public Boolean getIsSeasonRequiredToDrop() {
        return isSeasonRequiredToDrop;
    }

    public void setIsSeasonRequiredToDrop(Boolean isSeasonRequiredToDrop) {
        this.isSeasonRequiredToDrop = isSeasonRequiredToDrop;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("displayColor", displayColor).append("tooltipParams", tooltipParams).append("requiredLevel", requiredLevel).append("itemLevel", itemLevel).append("stackSizeMax", stackSizeMax).append("accountBound", accountBound).append("flavorText", flavorText).append("typeName", typeName).append("type", type).append("armor", armor).append("attacksPerSecond", attacksPerSecond).append("minDamage", minDamage).append("maxDamage", maxDamage).append("slots", slots).append("attributes", attributes).append("attributesHtml", attributesHtml).append("openSockets", openSockets).append("gems", gems).append("set", set).append("seasonRequiredToDrop", seasonRequiredToDrop).append("dye", dye).append("transmog", transmog).append("isSeasonRequiredToDrop", isSeasonRequiredToDrop).toString();
    }

}

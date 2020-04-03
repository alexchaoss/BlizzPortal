package com.BlizzardArmory.diablo.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Item.
 */
public class Item {

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
    @SerializedName("elementalType")
    @Expose
    private String elementalType;
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets display color.
     *
     * @return the display color
     */
    public String getDisplayColor() {
        return displayColor;
    }

    /**
     * Sets display color.
     *
     * @param displayColor the display color
     */
    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    /**
     * Gets tooltip params.
     *
     * @return the tooltip params
     */
    public String getTooltipParams() {
        return tooltipParams;
    }

    /**
     * Sets tooltip params.
     *
     * @param tooltipParams the tooltip params
     */
    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    /**
     * Gets required level.
     *
     * @return the required level
     */
    public Double getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Sets required level.
     *
     * @param requiredLevel the required level
     */
    public void setRequiredLevel(Double requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    /**
     * Gets item level.
     *
     * @return the item level
     */
    public Double getItemLevel() {
        return itemLevel;
    }

    /**
     * Sets item level.
     *
     * @param itemLevel the item level
     */
    public void setItemLevel(Double itemLevel) {
        this.itemLevel = itemLevel;
    }

    /**
     * Gets stack size max.
     *
     * @return the stack size max
     */
    public Double getStackSizeMax() {
        return stackSizeMax;
    }

    /**
     * Sets stack size max.
     *
     * @param stackSizeMax the stack size max
     */
    public void setStackSizeMax(Double stackSizeMax) {
        this.stackSizeMax = stackSizeMax;
    }

    /**
     * Gets account bound.
     *
     * @return the account bound
     */
    public Boolean getAccountBound() {
        return accountBound;
    }

    /**
     * Sets account bound.
     *
     * @param accountBound the account bound
     */
    public void setAccountBound(Boolean accountBound) {
        this.accountBound = accountBound;
    }

    /**
     * Gets flavor text.
     *
     * @return the flavor text
     */
    public String getFlavorText() {
        return flavorText;
    }

    /**
     * Sets flavor text.
     *
     * @param flavorText the flavor text
     */
    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    /**
     * Gets type name.
     *
     * @return the type name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets type name.
     *
     * @param typeName the type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public Double getArmor() {
        return armor;
    }

    /**
     * Sets armor.
     *
     * @param armor the armor
     */
    public void setArmor(Double armor) {
        this.armor = armor;
    }

    /**
     * Gets attacks per second.
     *
     * @return the attacks per second
     */
    public Double getAttacksPerSecond() {
        return attacksPerSecond;
    }

    /**
     * Sets attacks per second.
     *
     * @param attacksPerSecond the attacks per second
     */
    public void setAttacksPerSecond(Double attacksPerSecond) {
        this.attacksPerSecond = attacksPerSecond;
    }

    /**
     * Gets min damage.
     *
     * @return the min damage
     */
    public Double getMinDamage() {
        return minDamage;
    }

    /**
     * Sets min damage.
     *
     * @param minDamage the min damage
     */
    public void setMinDamage(Double minDamage) {
        this.minDamage = minDamage;
    }

    /**
     * Gets max damage.
     *
     * @return the max damage
     */
    public Double getMaxDamage() {
        return maxDamage;
    }

    /**
     * Sets max damage.
     *
     * @param maxDamage the max damage
     */
    public void setMaxDamage(Double maxDamage) {
        this.maxDamage = maxDamage;
    }


    /**
     * Gets elemental type.
     *
     * @return the elemental type
     */
    public String getElementalType() {
        return elementalType;
    }

    /**
     * Sets elemental type.
     *
     * @param elementalType the elemental type
     */
    public void setElementalType(String elementalType) {
        this.elementalType = elementalType;
    }


    /**
     * Gets slots.
     *
     * @return the slots
     */
    public String getSlots() {
        return slots;
    }

    /**
     * Sets slots.
     *
     * @param slots the slots
     */
    public void setSlots(String slots) {
        this.slots = slots;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets attributes html.
     *
     * @return the attributes html
     */
    public AttributesHtml getAttributesHtml() {
        return attributesHtml;
    }

    /**
     * Sets attributes html.
     *
     * @param attributesHtml the attributes html
     */
    public void setAttributesHtml(AttributesHtml attributesHtml) {
        this.attributesHtml = attributesHtml;
    }

    /**
     * Gets open sockets.
     *
     * @return the open sockets
     */
    public Double getOpenSockets() {
        return openSockets;
    }

    /**
     * Sets open sockets.
     *
     * @param openSockets the open sockets
     */
    public void setOpenSockets(Double openSockets) {
        this.openSockets = openSockets;
    }

    /**
     * Gets gems.
     *
     * @return the gems
     */
    public List<Gem> getGems() {
        return gems;
    }

    /**
     * Sets gems.
     *
     * @param gems the gems
     */
    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    /**
     * Gets set.
     *
     * @return the set
     */
    public Set getSet() {
        return set;
    }

    /**
     * Sets set.
     *
     * @param set the set
     */
    public void setSet(Set set) {
        this.set = set;
    }

    /**
     * Gets season required to drop.
     *
     * @return the season required to drop
     */
    public Double getSeasonRequiredToDrop() {
        return seasonRequiredToDrop;
    }

    /**
     * Sets season required to drop.
     *
     * @param seasonRequiredToDrop the season required to drop
     */
    public void setSeasonRequiredToDrop(Double seasonRequiredToDrop) {
        this.seasonRequiredToDrop = seasonRequiredToDrop;
    }

    /**
     * Gets dye.
     *
     * @return the dye
     */
    public Dye getDye() {
        return dye;
    }

    /**
     * Sets dye.
     *
     * @param dye the dye
     */
    public void setDye(Dye dye) {
        this.dye = dye;
    }

    /**
     * Gets transmog.
     *
     * @return the transmog
     */
    public Transmog getTransmog() {
        return transmog;
    }

    /**
     * Sets transmog.
     *
     * @param transmog the transmog
     */
    public void setTransmog(Transmog transmog) {
        this.transmog = transmog;
    }

    /**
     * Gets is season required to drop.
     *
     * @return the is season required to drop
     */
    public Boolean getIsSeasonRequiredToDrop() {
        return isSeasonRequiredToDrop;
    }

    /**
     * Sets is season required to drop.
     *
     * @param isSeasonRequiredToDrop the is season required to drop
     */
    public void setIsSeasonRequiredToDrop(Boolean isSeasonRequiredToDrop) {
        this.isSeasonRequiredToDrop = isSeasonRequiredToDrop;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("displayColor", displayColor).append("tooltipParams", tooltipParams).append("requiredLevel", requiredLevel).append("itemLevel", itemLevel).append("stackSizeMax", stackSizeMax).append("accountBound", accountBound).append("flavorText", flavorText).append("typeName", typeName).append("type", type).append("armor", armor).append("attacksPerSecond", attacksPerSecond).append("minDamage", minDamage).append("maxDamage", maxDamage).append("slots", slots).append("attributes", attributes).append("attributesHtml", attributesHtml).append("openSockets", openSockets).append("gems", gems).append("set", set).append("seasonRequiredToDrop", seasonRequiredToDrop).append("dye", dye).append("transmog", transmog).append("isSeasonRequiredToDrop", isSeasonRequiredToDrop).toString();
    }
}
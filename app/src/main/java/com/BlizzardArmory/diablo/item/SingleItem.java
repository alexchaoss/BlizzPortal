package com.BlizzardArmory.diablo.item;

import com.BlizzardArmory.diablo.items.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * The type Single item.
 */
public class SingleItem {

    @SerializedName("accountBound")
    @Expose
    private Boolean accountBound;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("damage")
    @Expose
    private String damage;
    @SerializedName("damageHtml")
    @Expose
    private String damageHtml;
    @SerializedName("p")
    @Expose
    private String dps;
    @SerializedName("flavorText")
    @Expose
    private String flavorText;
    @SerializedName("flavorTextHtml")
    @Expose
    private String flavorTextHtml;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("isSeasonRequiredToDrop")
    @Expose
    private Boolean isSeasonRequiredToDrop;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("randomAffixes")
    @Expose
    private List<RandomAffix> randomAffixes;
    @SerializedName("requiredLevel")
    @Expose
    private Long requiredLevel;
    @SerializedName("seasonRequiredToDrop")
    @Expose
    private Long seasonRequiredToDrop;
    @SerializedName("setItems")
    @Expose
    private List<Object> setItems;
    @SerializedName("slots")
    @Expose
    private List<String> slots;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("stackSizeMax")
    @Expose
    private Long stackSizeMax;
    @SerializedName("tooltipParams")
    @Expose
    private String tooltipParams;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("typeName")
    @Expose
    private String typeName;

    /**
     * Gets account bound.
     *
     * @return the account bound
     */
    public Boolean getAccountBound() {
        return accountBound;
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
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public String getDamage() {
        return damage;
    }

    /**
     * Gets damage html.
     *
     * @return the damage html
     */
    public String getDamageHtml() {
        return damageHtml;
    }

    /**
     * Gets dps.
     *
     * @return the dps
     */
    public String getDps() {
        return dps;
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
     * Gets flavor text html.
     *
     * @return the flavor text html
     */
    public String getFlavorTextHtml() {
        return flavorTextHtml;
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
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets random affixes.
     *
     * @return the random affixes
     */
    public List<RandomAffix> getRandomAffixes() {
        return randomAffixes;
    }

    /**
     * Gets required level.
     *
     * @return the required level
     */
    public Long getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Gets season required to drop.
     *
     * @return the season required to drop
     */
    public Long getSeasonRequiredToDrop() {
        return seasonRequiredToDrop;
    }

    /**
     * Gets set items.
     *
     * @return the set items
     */
    public List<Object> getSetItems() {
        return setItems;
    }

    /**
     * Gets slots.
     *
     * @return the slots
     */
    public List<String> getSlots() {
        return slots;
    }

    /**
     * Gets slug.
     *
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Gets stack size max.
     *
     * @return the stack size max
     */
    public Long getStackSizeMax() {
        return stackSizeMax;
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
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets type name.
     *
     * @return the type name
     */
    public String getTypeName() {
        return typeName;
    }

}

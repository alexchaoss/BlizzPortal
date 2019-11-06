
package com.BlizzardArmory.diablo.Item;

import java.util.List;

import com.BlizzardArmory.diablo.Items.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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

    public Boolean getAccountBound() {
        return accountBound;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public String getColor() {
        return color;
    }

    public String getDamage() {
        return damage;
    }

    public String getDamageHtml() {
        return damageHtml;
    }

    public String getDps() {
        return dps;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public String getFlavorTextHtml() {
        return flavorTextHtml;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public Boolean getIsSeasonRequiredToDrop() {
        return isSeasonRequiredToDrop;
    }

    public String getName() {
        return name;
    }

    public List<RandomAffix> getRandomAffixes() {
        return randomAffixes;
    }

    public Long getRequiredLevel() {
        return requiredLevel;
    }

    public Long getSeasonRequiredToDrop() {
        return seasonRequiredToDrop;
    }

    public List<Object> getSetItems() {
        return setItems;
    }

    public List<String> getSlots() {
        return slots;
    }

    public String getSlug() {
        return slug;
    }

    public Long getStackSizeMax() {
        return stackSizeMax;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public Type getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

}

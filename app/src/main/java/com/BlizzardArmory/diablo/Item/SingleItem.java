package com.BlizzardArmory.diablo.Item;

import com.BlizzardArmory.diablo.Items.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SingleItem {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("tooltipParams")
    @Expose
    private String tooltipParams;
    @SerializedName("requiredLevel")
    @Expose
    private float requiredLevel;
    @SerializedName("stackSizeMax")
    @Expose
    private float stackSizeMax;
    @SerializedName("accountBound")
    @Expose
    private boolean accountBound;
    @SerializedName("flavorText")
    @Expose
    private String flavorText;
    @SerializedName("flavorTextHtml")
    @Expose
    private String flavorTextHtml;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("type")
    @Expose
    private Type TypeString;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("tooltipParams")
    @Expose
    private boolean isSeasonRequiredToDrop;
    @SerializedName("seasonRequiredToDrop")
    @Expose
    private float seasonRequiredToDrop;
    @SerializedName("slots")
    @Expose
    private ArrayList< String > slots = new ArrayList <> ();
    @SerializedName("attributes")
    @Expose
    private Attributes AttributesString;
    @SerializedName("randomAffixes")
    @Expose
    private ArrayList < String > randomAffixes = new ArrayList <> ();
    @SerializedName("setItems")
    @Expose
    private ArrayList < String > setItems = new ArrayList <> ();


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public float getRequiredLevel() {
        return requiredLevel;
    }

    public float getStackSizeMax() {
        return stackSizeMax;
    }

    public boolean getAccountBound() {
        return accountBound;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public String getFlavorTextHtml() {
        return flavorTextHtml;
    }

    public String getTypeName() {
        return typeName;
    }

    public Type getType() {
        return TypeString;
    }

    public String getColor() {
        return color;
    }

    public boolean getIsSeasonRequiredToDrop() {
        return isSeasonRequiredToDrop;
    }

    public float getSeasonRequiredToDrop() {
        return seasonRequiredToDrop;
    }

    public Attributes getAttributes() {
        return AttributesString;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public void setRequiredLevel(float requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public void setStackSizeMax(float stackSizeMax) {
        this.stackSizeMax = stackSizeMax;
    }

    public void setAccountBound(boolean accountBound) {
        this.accountBound = accountBound;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public void setFlavorTextHtml(String flavorTextHtml) {
        this.flavorTextHtml = flavorTextHtml;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setType(Type typeString) {
        this.TypeString = typeString;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIsSeasonRequiredToDrop(boolean isSeasonRequiredToDrop) {
        this.isSeasonRequiredToDrop = isSeasonRequiredToDrop;
    }

    public void setSeasonRequiredToDrop(float seasonRequiredToDrop) {
        this.seasonRequiredToDrop = seasonRequiredToDrop;
    }

    public void setAttributes(Attributes attributesString) {
        this.AttributesString = attributesString;
    }
}

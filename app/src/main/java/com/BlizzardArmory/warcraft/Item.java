package com.BlizzardArmory.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("quality")
    @Expose
    private Integer quality;
    @SerializedName("itemLevel")
    @Expose
    private Integer itemLevel;
    @SerializedName("tooltipParams")
    @Expose
    private TooltipParams tooltipParams;
    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;
    @SerializedName("armor")
    @Expose
    private Integer armor;
    @SerializedName("weaponInfo")
    @Expose
    private WeaponInfo weaponInfo;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("bonusLists")
    @Expose
    private List<Integer> bonusLists = null;
    @SerializedName("displayInfoId")
    @Expose
    private Integer displayInfoId;
    @SerializedName("artifactId")
    @Expose
    private Integer artifactId;
    @SerializedName("artifactAppearanceId")
    @Expose
    private Integer artifactAppearanceId;
    @SerializedName("artifactTraits")
    @Expose
    private List<Object> artifactTraits = null;
    @SerializedName("relics")
    @Expose
    private List<Object> relics = null;
    @SerializedName("azeriteItem")
    @Expose
    private AzeriteItem azeriteItem;
    @SerializedName("azeriteEmpoweredItem")
    @Expose
    private AzeriteEmpoweredItem azeriteEmpoweredItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    public TooltipParams getTooltipParams() {
        return tooltipParams;
    }

    public void setTooltipParams(TooltipParams tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public WeaponInfo getWeaponInfo() {
        return weaponInfo;
    }

    public void setWeaponInfo(WeaponInfo weaponInfo) {
        this.weaponInfo = weaponInfo;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Integer> getBonusLists() {
        return bonusLists;
    }

    public void setBonusLists(List<Integer> bonusLists) {
        this.bonusLists = bonusLists;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public Integer getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Integer displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public Integer getArtifactAppearanceId() {
        return artifactAppearanceId;
    }

    public void setArtifactAppearanceId(Integer artifactAppearanceId) {
        this.artifactAppearanceId = artifactAppearanceId;
    }

    public List<Object> getArtifactTraits() {
        return artifactTraits;
    }

    public void setArtifactTraits(List<Object> artifactTraits) {
        this.artifactTraits = artifactTraits;
    }

    public List<Object> getRelics() {
        return relics;
    }

    public void setRelics(List<Object> relics) {
        this.relics = relics;
    }

    public AzeriteItem getAzeriteItem() {
        return azeriteItem;
    }

    public void setAzeriteItem(AzeriteItem azeriteItem) {
        this.azeriteItem = azeriteItem;
    }

    public AzeriteEmpoweredItem getAzeriteEmpoweredItem() {
        return azeriteEmpoweredItem;
    }

    public void setAzeriteEmpoweredItem(AzeriteEmpoweredItem azeriteEmpoweredItem) {
        this.azeriteEmpoweredItem = azeriteEmpoweredItem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("quality", quality).append("itemLevel", itemLevel).append("tooltipParams", tooltipParams).append("stats", stats).append("armor", armor).append("context", context).append("bonusLists", bonusLists).append("displayInfoId", displayInfoId).append("artifactId", artifactId).append("artifactAppearanceId", artifactAppearanceId).append("artifactTraits", artifactTraits).append("relics", relics).append("azeriteItem", azeriteItem).append("azeriteEmpoweredItem", azeriteEmpoweredItem).toString();
    }

}
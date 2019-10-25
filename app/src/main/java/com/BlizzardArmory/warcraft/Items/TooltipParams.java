package com.BlizzardArmory.warcraft.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class TooltipParams {

    @SerializedName("gem0")
    @Expose
    private Integer gem0;
    @SerializedName("gem1")
    @Expose
    private Integer gem1;
    @SerializedName("gem2")
    @Expose
    private Integer gem2;
    @SerializedName("enchant")
    @Expose
    private Integer enchant;
    @SerializedName("set")
    @Expose
    private List<Integer> set = null;
    @SerializedName("transmogItem")
    @Expose
    private Integer transmogItem;
    @SerializedName("timewalkerLevel")
    @Expose
    private Integer timewalkerLevel;
    @SerializedName("azeritePower0")
    @Expose
    private Integer azeritePower0;
    @SerializedName("azeritePower1")
    @Expose
    private Integer azeritePower1;
    @SerializedName("azeritePower2")
    @Expose
    private Integer azeritePower2;
    @SerializedName("azeritePower3")
    @Expose
    private Integer azeritePower3;
    @SerializedName("azeritePowerLevel")
    @Expose
    private Integer azeritePowerLevel;
    @SerializedName("azeritePower4")
    @Expose
    private Integer azeritePower4;

    public Integer getGem0() {
        return gem0;
    }

    public void setGem0(Integer gem0) {
        this.gem0 = gem0;
    }

    public Integer getGem1() {
        return gem1;
    }

    public void setGem1(Integer gem1) {
        this.gem1 = gem1;
    }

    public Integer getGem2() {
        return gem2;
    }

    public void setGem2(Integer gem2) {
        this.gem2 = gem2;
    }

    public Integer getEnchant() {
        return enchant;
    }

    public void setEnchant(Integer enchant) {
        this.enchant = enchant;
    }

    public List<Integer> getSet() {
        return set;
    }

    public void setSet(List<Integer> set) {
        this.set = set;
    }

    public Integer getTimewalkerLevel() {
        return timewalkerLevel;
    }

    public void setTimewalkerLevel(Integer timewalkerLevel) {
        this.timewalkerLevel = timewalkerLevel;
    }

    public Integer getAzeritePower0() {
        return azeritePower0;
    }

    public void setAzeritePower0(Integer azeritePower0) {
        this.azeritePower0 = azeritePower0;
    }

    public Integer getAzeritePower1() {
        return azeritePower1;
    }

    public void setAzeritePower1(Integer azeritePower1) {
        this.azeritePower1 = azeritePower1;
    }

    public Integer getAzeritePower2() {
        return azeritePower2;
    }

    public void setAzeritePower2(Integer azeritePower2) {
        this.azeritePower2 = azeritePower2;
    }

    public Integer getAzeritePower3() {
        return azeritePower3;
    }

    public void setAzeritePower3(Integer azeritePower3) {
        this.azeritePower3 = azeritePower3;
    }

    public Integer getAzeritePowerLevel() {
        return azeritePowerLevel;
    }

    public void setAzeritePowerLevel(Integer azeritePowerLevel) {
        this.azeritePowerLevel = azeritePowerLevel;
    }

    public Integer getAzeritePower4() {
        return azeritePower4;
    }

    public void setAzeritePower4(Integer azeritePower4) {
        this.azeritePower4 = azeritePower4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("transmogItem", transmogItem).append("timewalkerLevel", timewalkerLevel).append("azeritePower0", azeritePower0).append("azeritePower1", azeritePower1).append("azeritePower2", azeritePower2).append("azeritePower3", azeritePower3).append("azeritePowerLevel", azeritePowerLevel).append("azeritePower4", azeritePower4).toString();
    }

}
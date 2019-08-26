package com.BlizzardArmory.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TooltipParams {

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
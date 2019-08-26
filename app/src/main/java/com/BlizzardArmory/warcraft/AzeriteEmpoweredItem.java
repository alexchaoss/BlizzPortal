package com.BlizzardArmory.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class AzeriteEmpoweredItem {

    @SerializedName("azeritePowers")
    @Expose
    private List<AzeritePower> azeritePowers = new ArrayList<>();

    public List<AzeritePower> getAzeritePowers() {
        return azeritePowers;
    }

    public void setAzeritePowers(List<AzeritePower> azeritePowers) {
        this.azeritePowers = azeritePowers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("azeritePowers", azeritePowers).toString();
    }

}
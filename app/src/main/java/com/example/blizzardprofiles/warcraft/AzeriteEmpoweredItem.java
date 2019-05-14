package com.example.blizzardprofiles.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class AzeriteEmpoweredItem {

    @SerializedName("azeritePowers")
    @Expose
    private List<Object> azeritePowers = null;

    public List<Object> getAzeritePowers() {
        return azeritePowers;
    }

    public void setAzeritePowers(List<Object> azeritePowers) {
        this.azeritePowers = azeritePowers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("azeritePowers", azeritePowers).toString();
    }

}
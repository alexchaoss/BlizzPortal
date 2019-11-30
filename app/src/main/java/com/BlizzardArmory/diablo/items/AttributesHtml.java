package com.BlizzardArmory.diablo.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AttributesHtml {

    @SerializedName("primary")
    @Expose
    private List<String> primary = null;
    @SerializedName("secondary")
    @Expose
    private List<String> secondary = null;

    public List<String> getPrimary() {
        return primary;
    }

    public void setPrimary(List<String> primary) {
        this.primary = primary;
    }

    public List<String> getSecondary() {
        return secondary;
    }

    public void setSecondary(List<String> secondary) {
        this.secondary = secondary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("primary", primary).append("secondary", secondary).toString();
    }

}

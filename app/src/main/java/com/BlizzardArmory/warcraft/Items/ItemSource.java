package com.BlizzardArmory.warcraft.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ItemSource {

    @SerializedName("sourceId")
    @Expose
    private Integer sourceId;
    @SerializedName("sourceType")
    @Expose
    private String sourceType;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sourceId", sourceId).append("sourceType", sourceType).toString();
    }
}

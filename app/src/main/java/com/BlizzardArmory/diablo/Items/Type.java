package com.BlizzardArmory.diablo.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Type {

    @SerializedName("twoHanded")
    @Expose
    private Boolean twoHanded;
    @SerializedName("id")
    @Expose
    private String id;

    public Boolean getTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(Boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("twoHanded", twoHanded).append("id", id).toString();
    }

}

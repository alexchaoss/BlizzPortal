package com.BlizzardArmory.diablo.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Type.
 */
public class Type {

    @SerializedName("twoHanded")
    @Expose
    private Boolean twoHanded;
    @SerializedName("oneHanded")
    @Expose
    private Boolean oneHanded;
    @SerializedName("id")
    @Expose
    private String id;

    /**
     * Gets two handed.
     *
     * @return the two handed
     */
    public Boolean getTwoHanded() {
        return twoHanded;
    }

    /**
     * Sets two handed.
     *
     * @param twoHanded the two handed
     */
    public void setTwoHanded(Boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    /**
     * Gets one handed.
     *
     * @return the one handed
     */
    public Boolean getOneHanded() {
        return oneHanded;
    }

    /**
     * Sets one handed.
     *
     * @param oneHanded the one handed
     */
    public void setOneHanded(Boolean oneHanded) {
        this.oneHanded = oneHanded;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("twoHanded", twoHanded).append("id", id).toString();
    }

}

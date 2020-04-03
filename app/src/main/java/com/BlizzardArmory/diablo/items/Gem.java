package com.BlizzardArmory.diablo.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Gem.
 */
public class Gem {

    @SerializedName("item")
    @Expose
    private GemInfo item;
    @SerializedName("attributes")
    @Expose
    private List<String> attributes = null;
    @SerializedName("isGem")
    @Expose
    private Boolean isGem;
    @SerializedName("isJewel")
    @Expose
    private Boolean isJewel;

    /**
     * Gets item.
     *
     * @return the item
     */
    public GemInfo getItem() {
        return item;
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem(GemInfo item) {
        this.item = item;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public List<String> getAttributes() {
        return attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets is gem.
     *
     * @return the is gem
     */
    public Boolean getIsGem() {
        return isGem;
    }

    /**
     * Sets is gem.
     *
     * @param isGem the is gem
     */
    public void setIsGem(Boolean isGem) {
        this.isGem = isGem;
    }

    /**
     * Gets is jewel.
     *
     * @return the is jewel
     */
    public Boolean getIsJewel() {
        return isJewel;
    }

    /**
     * Sets is jewel.
     *
     * @param isJewel the is jewel
     */
    public void setIsJewel(Boolean isJewel) {
        this.isJewel = isJewel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("item", item).append("attributes", attributes).append("isGem", isGem).append("isJewel", isJewel).toString();
    }

}

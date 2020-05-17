package com.BlizzardArmory.model.warcraft.equipment.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Media.
 */
public class Media {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("assets")
    @Expose
    private List<Asset> assets = null;

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * Gets assets.
     *
     * @return the assets
     */
    public List<Asset> getAssets() {
        return assets;
    }

    /**
     * Sets assets.
     *
     * @param assets the assets
     */
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

}

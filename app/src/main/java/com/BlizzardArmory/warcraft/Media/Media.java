
package com.BlizzardArmory.warcraft.Media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("assets")
    @Expose
    private List<Asset> assets = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

}

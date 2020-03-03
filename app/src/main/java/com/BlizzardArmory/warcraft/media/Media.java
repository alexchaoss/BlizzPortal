
package com.BlizzardArmory.warcraft.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("character")
    @Expose
    private Character character;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("bust_url")
    @Expose
    private String bustUrl;
    @SerializedName("render_url")
    @Expose
    private String renderUrl;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBustUrl() {
        return bustUrl;
    }

    public void setBustUrl(String bustUrl) {
        this.bustUrl = bustUrl;
    }

    public String getRenderUrl() {
        return renderUrl;
    }

    public void setRenderUrl(String renderUrl) {
        this.renderUrl = renderUrl;
    }

}

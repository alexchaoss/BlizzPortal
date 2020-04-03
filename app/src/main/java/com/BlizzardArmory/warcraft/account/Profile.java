
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Profile.
 */
public class Profile {

    @SerializedName("href")
    @Expose
    private String href;

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets href.
     *
     * @param href the href
     */
    public void setHref(String href) {
        this.href = href;
    }

}

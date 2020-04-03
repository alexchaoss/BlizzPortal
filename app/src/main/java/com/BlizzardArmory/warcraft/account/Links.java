
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Links.
 */
public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("profile")
    @Expose
    private Profile profile;

    /**
     * Gets self.
     *
     * @return the self
     */
    public Self getSelf() {
        return self;
    }

    /**
     * Sets self.
     *
     * @param self the self
     */
    public void setSelf(Self self) {
        this.self = self;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets profile.
     *
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets profile.
     *
     * @param profile the profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}

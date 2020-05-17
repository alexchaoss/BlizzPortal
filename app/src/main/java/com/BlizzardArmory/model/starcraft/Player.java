package com.BlizzardArmory.model.starcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Player.
 */
public class Player {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profileUrl")
    @Expose
    private String profileUrl;
    @SerializedName("avatarUrl")
    @Expose
    private String avatarUrl;
    @SerializedName("profileId")
    @Expose
    private String profileId;
    @SerializedName("regionId")
    @Expose
    private int regionId;
    @SerializedName("realmId")
    @Expose
    private int realmId;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets profile url.
     *
     * @return the profile url
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * Sets profile url.
     *
     * @param profileUrl the profile url
     */
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     * Gets avatar url.
     *
     * @return the avatar url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets avatar url.
     *
     * @param avatarUrl the avatar url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Gets profile id.
     *
     * @return the profile id
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Sets profile id.
     *
     * @param profileId the profile id
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Gets region id.
     *
     * @return the region id
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * Sets region id.
     *
     * @param regionId the region id
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * Gets realm id.
     *
     * @return the realm id
     */
    public int getRealmId() {
        return realmId;
    }

    /**
     * Sets realm id.
     *
     * @param realmId the realm id
     */
    public void setRealmId(int realmId) {
        this.realmId = realmId;
    }

}

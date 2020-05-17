package com.BlizzardArmory.model;

import com.google.gson.annotations.SerializedName;

/**
 * The type User information.
 */
public class UserInformation {

    @SerializedName("sub")
    private String sub;

    @SerializedName("id")
    private String userID;

    @SerializedName("battletag")
    private String battleTag;

    /**
     * Gets sub.
     *
     * @return the sub
     */
    public String getSub() {
        return sub;
    }

    /**
     * Gets battle tag.
     *
     * @return the battle tag
     */
    public String getBattleTag() {
        return battleTag;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }
}

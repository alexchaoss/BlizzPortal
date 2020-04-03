package com.BlizzardArmory;

import com.google.gson.annotations.SerializedName;

public class UserInformation {

    @SerializedName("sub")
    private String sub;

    @SerializedName("id")
    private String userID;

    @SerializedName("battletag")
    private String battleTag;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        sub = sub;
    }

    public String getBattleTag() {
        return battleTag;
    }

    public void setBattleTag(String battleTag) {
        battleTag = battleTag;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        userID = userID;
    }
}

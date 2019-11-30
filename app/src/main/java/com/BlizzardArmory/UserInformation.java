package com.BlizzardArmory;

public class UserInformation {

    private static String userID;

    private static String battleTag;

    public static String getBattleTag() {
        return battleTag;
    }

    public static void setBattleTag(String battleTag) {
        UserInformation.battleTag = battleTag;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserInformation.userID = userID;
    }
}

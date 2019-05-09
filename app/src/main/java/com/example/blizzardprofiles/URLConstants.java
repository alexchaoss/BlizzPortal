package com.example.blizzardprofiles;

import com.example.blizzardprofiles.activities.MainActivity;

public class URLConstants {
    //Callback URL
    public final static String CALLBACK_URL = "https://localhost";

    //Account information
    public final static String BASE_URL_USER_INFO = "https://zone.battle.net";
    public final static String END_USER_INFO_URL = "/oauth/userinfo?";
    public final static String BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn";

    //Base URL for APIs
    public final static String BASE_URL_API = "https://zone.api.blizzard.com";
    public final static String BASE_URL_CN_API = "https://gateway.battlenet.com.cn";

    //Access Token Query
    public final static String ACCESS_TOKEN_QUERY = "access_token=";

    //URLs for WoW images
    public final static String WOW_CHARACTER_THUMNAIL_URL = "http://render-zone.worldofwarcraft.com/character/";
    public final static String WOW_ICONS_URL = "http://media.blizzard.com/wow/icons/";

    //URLs for WoW characters and queries
    public final static String WOW_CHAR_URL = "/wow/user/characters";
    public final static String WOW_ITEM_QUERY = "/realm/character/?fields=items&locale=en_US&";

    public static String getBaseURLforUserInformation() {
        if (MainActivity.selectedRegion.equals("cn")) {
            return URLConstants.BASE_URL_CN_USER_INFO;
        } else {
            return URLConstants.BASE_URL_USER_INFO.replace("zone", MainActivity.selectedRegion);
        }
    }

    public static String getBaseURLforAPI() {
        if (MainActivity.selectedRegion.equals("cn")) {
            return URLConstants.BASE_URL_CN_API;
        } else {
            return URLConstants.BASE_URL_API.replace("zone", MainActivity.selectedRegion);
        }
    }

    public static String getRenderZoneURL(){
        return WOW_CHARACTER_THUMNAIL_URL.replace("zone", MainActivity.selectedRegion);
    }
}

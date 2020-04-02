package com.BlizzardArmory;

import com.BlizzardArmory.ui.MainActivity;

public class URLConstants {
    //Loading boolean
    public static boolean loading = false;

    //Callback URL
    public final static String CALLBACK_URL = "https://alexchaoss.github.io/BnetAuthorize";

    //Account information
    public final static String BASE_URL_USER_INFO = "https://zone.battle.net";
    public final static String END_USER_INFO_URL = "/oauth/userinfo?";
    public final static String BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn";

    //Base URL for APIs
    public final static String BASE_URL_API = "https://zone.api.blizzard.com/";
    public final static String BASE_URL_CN_API = "https://gateway.battlenet.com.cn/";

    //Access Token Query
    public final static String ACCESS_TOKEN_QUERY = "access_token=";

    //URLs for WoW images
    public final static String NOT_FOUND_URL_AVATAR = "?alt=/wow/static/images/2d/avatar/";
    public final static String MEDIA_QUERY = "/profile/wow/character/realm/charactername/character-media?namespace=profile-us&locale=en_US&access_token=";

    //URLS for D3 characters
    public final static String D3_ICON_ITEMS = "http://media.blizzard.com/d3/icons/items/large/icon.png";
    public final static String D3_ICON_SKILLS = "http://media.blizzard.com/d3/icons/skills/64/url.png";

    //URL Overwwatch
    public final static String OW_PROFILE = "https://ow-api.com/v1/stats/:platform/:region/:battletag/complete";

    public static String getBaseURLforUserInformation() {
        if (MainActivity.selectedRegion.toLowerCase().equals("cn")) {
            return URLConstants.BASE_URL_CN_USER_INFO;
        } else {
            return URLConstants.BASE_URL_USER_INFO.replace("zone", MainActivity.selectedRegion.toLowerCase());
        }
    }

    public static String getBaseURLforAPI(String region) {
        if (region == null || "".equalsIgnoreCase(region)) {
            if (MainActivity.selectedRegion.equals("cn")) {
                return URLConstants.BASE_URL_CN_API;
            } else {
                return URLConstants.BASE_URL_API.replace("zone", MainActivity.selectedRegion.toLowerCase());
            }
        } else {
            return URLConstants.BASE_URL_API.replace("zone", region.toLowerCase());
        }
    }

    public static String getRegion() {
        return MainActivity.selectedRegion.toLowerCase();
    }

    public static String getOWProfile(String username, String platform) {
        String url;
        if (platform.equalsIgnoreCase("PC")) {
            url = OW_PROFILE.replace(":battletag", username.replace("#", "-"));
            url = url.replace(":platform", "pc");
            if (MainActivity.selectedRegion.toLowerCase().equals("cn") || MainActivity.selectedRegion.toLowerCase().equals("tw")) {
                url = url.replace(":region", "asia");
            } else {
                url = url.replace(":region", MainActivity.selectedRegion.toLowerCase());
            }
        } else {
            url = OW_PROFILE.replace(":battletag", username);
            url = url.replace(":platform", platform);
            url = url.replace(":region", "global");
        }
        return url;
    }
}

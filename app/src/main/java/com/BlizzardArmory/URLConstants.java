package com.BlizzardArmory;

import com.BlizzardArmory.ui.MainActivity;

/**
 * The type Url constants.
 */
public class URLConstants {
    /**
     * The constant loading.
     */
//Loading boolean
    public static boolean loading = false;

    /**
     * The constant paypalURL.
     */
//Paypal
    public final static String paypalURL = "https://paypal.me/astpierredev";

    /**
     * The constant CALLBACK_URL.
     */
//Callback URL
    public final static String CALLBACK_URL = "https://alexchaoss.github.io/BnetAuthorize";

    /**
     * The constant BASE_URL_USER_INFO.
     */
//Account information
    public final static String BASE_URL_USER_INFO = "https://zone.battle.net/";
    /**
     * The constant BASE_URL_CN_USER_INFO.
     */
    public final static String BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn";

    /**
     * The constant BASE_URL_API.
     */
//Base URL for APIs
    public final static String BASE_URL_API = "https://zone.api.blizzard.com/";
    /**
     * The constant BASE_URL_CN_API.
     */
    public final static String BASE_URL_CN_API = "https://gateway.battlenet.com.cn/";

    /**
     * The constant ACCESS_TOKEN_QUERY.
     */
//Access Token Query
    public final static String ACCESS_TOKEN_QUERY = "access_token=";

    /**
     * The constant NOT_FOUND_URL_AVATAR.
     */
//URLs for WoW images
    public final static String NOT_FOUND_URL_AVATAR = "?alt=/wow/static/images/2d/avatar/";
    /**
     * The constant MEDIA_QUERY.
     */
    public final static String MEDIA_QUERY = "/profile/wow/character/realm/charactername/character-media?namespace=profile-us&locale=en_US&access_token=";

    /**
     * The constant D3_ICON_ITEMS.
     */
//URLS for D3 characters
    public final static String D3_ICON_ITEMS = "http://media.blizzard.com/d3/icons/items/large/icon.png";
    /**
     * The constant D3_ICON_SKILLS.
     */
    public final static String D3_ICON_SKILLS = "http://media.blizzard.com/d3/icons/skills/64/url.png";

    /**
     * The constant OW_PROFILE.
     */
//URL Overwwatch
    public final static String OW_PROFILE = "https://ow-api.com/v1/stats/:platform/:region/:battletag/complete";

    /**
     * Gets base ur lfor user information.
     *
     * @return the base ur lfor user information
     */
    public static String getBaseURLforUserInformation() {
        if (MainActivity.selectedRegion.toLowerCase().equals("cn")) {
            return URLConstants.BASE_URL_CN_USER_INFO;
        } else {
            return URLConstants.BASE_URL_USER_INFO.replace("zone", MainActivity.selectedRegion.toLowerCase());
        }
    }

    /**
     * Gets base ur lfor api.
     *
     * @param region the region
     * @return the base ur lfor api
     */
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

    /**
     * Gets region.
     *
     * @return the region
     */
    public static String getRegion() {
        return MainActivity.selectedRegion.toLowerCase();
    }

    /**
     * Gets ow profile.
     *
     * @param username the username
     * @param platform the platform
     * @return the ow profile
     */
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

package com.BlizzardArmory.connection;

import com.BlizzardArmory.ui.MainActivity;

/**
 * The type Url constants.
 */
public class URLConstants {
    /**
     * The constant loading.
     */
    public static boolean loading = false;

    public static String HEROKU_AUTHENTICATE = "https://blizzardgamesprofiles.herokuapp.com/";

    /**
     * The constant paypalURL.
     */
    public final static String PAYPAL_URL = "https://paypal.me/astpierredev";

    /**
     * The constant CALLBACK_URL.
     */
    public final static String CALLBACK_URL = "https://alexchaoss.github.io/BnetAuthorize";

    public final static String LOGOUT_URL = "https://battle.net/login/logout";

    /**
     * The constant BASE_URL_USER_INFO.
     */
    public final static String BASE_URL_USER_INFO = "https://zone.battle.net/";
    /**
     * The constant BASE_URL_CN_USER_INFO.
     */
    public final static String BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn";

    /**
     * The constant BASE_URL_API.
     */
    public final static String BASE_URL_API = "https://zone.api.blizzard.com/";
    /**
     * The constant BASE_URL_CN_API.
     */
    public final static String BASE_URL_CN_API = "https://gateway.battlenet.com.cn/";

    /**
     * The constant NOT_FOUND_URL_AVATAR.
     */
    public final static String NOT_FOUND_URL_AVATAR = "?alt=/wow/static/images/2d/avatar/";

    /**
     * The constant D3_ICON_ITEMS.
     */
    public final static String D3_ICON_ITEMS = "http://media.blizzard.com/d3/icons/items/large/icon.png";
    /**
     * The constant D3_ICON_SKILLS.
     */
    public final static String D3_ICON_SKILLS = "http://media.blizzard.com/d3/icons/skills/64/url.png";

    /**
     * The constant OW_PROFILE.
     */
    public final static String OW_PROFILE = "https://ow-api.com/v1/stats/:platform/:region/:battletag/complete";

    /**
     * Gets base url for user information.
     *
     * @return the base url for user information
     */
    public static String getBaseURLforUserInformation() {
        if (MainActivity.selectedRegion.equalsIgnoreCase("cn")) {
            return URLConstants.BASE_URL_CN_USER_INFO;
        } else {
            return URLConstants.BASE_URL_USER_INFO.replace("zone", MainActivity.selectedRegion.toLowerCase());
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
            if (MainActivity.selectedRegion.equalsIgnoreCase("cn") || MainActivity.selectedRegion.equalsIgnoreCase("tw")) {
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

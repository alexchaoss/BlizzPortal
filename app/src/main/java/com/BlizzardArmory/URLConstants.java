package com.BlizzardArmory;

import com.BlizzardArmory.ui.MainActivity;

public class URLConstants {
    //Callback URL
    public final static String CALLBACK_URL = "https://alexchaoss.github.io/BnetAuthorize";

    //Account information
    public final static String BASE_URL_USER_INFO = "https://zone.battle.net";
    public final static String END_USER_INFO_URL = "/oauth/userinfo?";
    public final static String BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn";

    //Base URL for APIs
    public final static String BASE_URL_API = "https://zone.api.blizzard.com";
    public final static String BASE_URL_CN_API = "https://gateway.battlenet.com.cn";

    //Access Token Query
    public final static String ACCESS_TOKEN_QUERY = "access_token=";
    public final static String ACCESS_TOKEN_AND_LOCALE = "&locale=en_US&access_token=";

    //URLs for WoW images
    public final static String WOW_CHARACTER_THUMNAIL_URL = "http://render-zone.worldofwarcraft.com/character/";
    public final static String NOT_FOUND_URL_AVATAR = "?alt=/wow/static/images/2d/avatar/";

    //URLS for D3 characters and queries
    public final static String D3_PROFILE = "/d3/profile/btag/?locale=en_US&";
    public final static String D3_CHARACTER = "/d3/profile/btag/hero/id?locale=en_US&";
    public final static String D3_CHARACTER_ITEMS = "/d3/profile/btag/hero/id/items?locale=en_US&";
    public final static String D3_ITEM = "/d3/data/item?locale=en_US&";
    public final static String D3_ICON_ITEMS = "http://media.blizzard.com/d3/icons/items/large/icon.png";
    public final static String D3_ICON_SKILLS = "http://media.blizzard.com/d3/icons/skills/64/url.png";

    //URLs for WoW characters and queries
    public final static String WOW_CHAR_URL = "/wow/user/characters";
    public final static String WOW_ITEM_QUERY = "/profile/wow/character/realm/characterName/equipment?namespace=profile-zone&locale=en_US&access_token=TOKEN";
    public final static String WOW_STATS_QUERY = "/profile/wow/character/realm/characterName/statistics?namespace=profile-zone&locale=en_US&access_token=TOKEN";
    public final static String WOW_TALENT_QUERY = "/profile/wow/character/realm/characterName/specializations?namespace=profile-zone&locale=en_US&access_token=TOKEN";
    public final static String WOW_CHARACTER_QUERY = "/profile/wow/character/realm/characterName?namespace=profile-zone&locale=en_US&access_token=TOKEN";

    //URLs for SC2
    public final static String SC2_PROFILE = "/sc2/player/id?";
    public final static String SC2_PROFILE_INFO = "/sc2/profile/region_id/realm_id/profile_id?locale=en_US&access_token=";

    //URL Overwwatch
    public final static String OW_PROFILE = "https://ow-api.com/v1/stats/:platform/:region/:battletag/complete";

    public static String getD3URLBtagProfile() {
        return URLConstants.D3_PROFILE.replace("btag", UserInformation.getBattleTag().replace("#", "-"));
    }

    public static String getD3HeroURL(long id) {
        //Log.i("ID", "" + id);
        String url = D3_CHARACTER.replace("btag", UserInformation.getBattleTag().replace("#", "-"));
        return url.replace("id", String.valueOf(id));
    }

    public static String getD3HeroItemsURL(long id) {
        //Log.i("ID", "" + id);
        String url = D3_CHARACTER_ITEMS.replace("btag", UserInformation.getBattleTag().replace("#", "-"));
        return url.replace("id", String.valueOf(id));
    }

    public static String getBaseURLforUserInformation() {
        if (MainActivity.selectedRegion.equals("cn")) {
            return URLConstants.BASE_URL_CN_USER_INFO;
        } else {
            return URLConstants.BASE_URL_USER_INFO.replace("zone", MainActivity.selectedRegion.toLowerCase());
        }
    }

    public static String getBaseURLforAPI() {
        if (MainActivity.selectedRegion.equals("cn")) {
            return URLConstants.BASE_URL_CN_API;
        } else {
            return URLConstants.BASE_URL_API.replace("zone", MainActivity.selectedRegion.toLowerCase());
        }
    }

    public static String getRenderZoneURL() {
        return WOW_CHARACTER_THUMNAIL_URL.replace("zone", MainActivity.selectedRegion.toLowerCase());
    }

    public static String getRegion() {
        return MainActivity.selectedRegion.toLowerCase();
    }

    public static String getOWProfile(String username, String platform) {
        String url;
        if (platform.equalsIgnoreCase("PC")) {
            url = OW_PROFILE.replace(":battletag", UserInformation.getBattleTag().replace("#", "-"));
            url = url.replace(":platform", "pc");
            if (MainActivity.selectedRegion.toLowerCase().equals("cn") || MainActivity.selectedRegion.toLowerCase().equals("tw")) {
                url = url.replace(":region", "asia");
            } else {
                url = url.replace(":region", MainActivity.selectedRegion.toLowerCase());
            }
        } else {
            url = OW_PROFILE.replace(":battletag", username);
            url = url.replace(":platform", platform);
            url = url.replace("/:region", "");
        }
        return url;
    }
}

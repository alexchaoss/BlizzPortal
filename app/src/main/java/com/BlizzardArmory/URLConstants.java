package com.BlizzardArmory;

import com.BlizzardArmory.UI.MainActivity;

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
    public final static String WOW_ICONS_URL = "https://render-us.worldofwarcraft.com/icons/";
    public final static String NOT_FOUND_URL_AVATAR = "?alt=/wow/static/images/2d/avatar/";

    //URLs for D3 images
    public final static String D3_ICONS_ITEMS_URL = "http://media.blizzard.com/d3/icons/items/large/";
    public final static String D3_ICONS_SKILLS_URL = "http://media.blizzard.com/d3/icons/skills/64/";

    //URLS for D3 characters and queries
    public final static String D3_PROFILE = "/d3/profile/btag/?locale=en_US&";
    public final static String D3_CHARACTER = "/d3/profile/btag/hero/id?locale=en_US&";

    //URLs for WoW characters and queries
    public final static String WOW_CHAR_URL = "/wow/user/characters";
    public final static String WOW_ITEM_QUERY = "/wow/character/realm/character?fields=items,stats,talents&locale=en_US&";
    public final static String BONUSID_QUERY = "/wow/item/id?b1=bonusList&locale=en_US&";
    public final static String SPELL_ID_QUERY = "/wow/spell/";

    //No connection message
    public final static String NO_CONNECTION = "No Internet Connection\nMake sure that Wi-Fi or mobile data is turned on, then try again.";

    public static String getD3URLBtagProfile() {
        return URLConstants.D3_PROFILE.replace("btag", UserInformation.getBattleTag().replace("#", "-"));
    }

    public static String getD3HeroURL(int id) {
        return D3_CHARACTER.replace("id", String.valueOf(id));
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
}

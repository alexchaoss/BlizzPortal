package com.BlizzardArmory.connection

import com.BlizzardArmory.ui.MainActivity

/**
 * The type Url constants.
 */
object URLConstants {
    /**
     * The constant loading.
     */
    @JvmField
    var loading = false
    var HEROKU_AUTHENTICATE = "https://blizzardgamesprofiles.herokuapp.com/"

    /**
     * The constant paypalURL.
     */
    const val PAYPAL_URL = "https://paypal.me/astpierredev"

    /**
     * The constant CALLBACK_URL.
     */
    const val CALLBACK_URL = "https://alexchaoss.github.io/BnetAuthorize"
    const val LOGOUT_URL = "https://battle.net/login/logout"

    /**
     * The constant BASE_URL_USER_INFO.
     */
    const val BASE_URL_USER_INFO = "https://zone.battle.net/"

    /**
     * The constant BASE_URL_CN_USER_INFO.
     */
    const val BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn"

    /**
     * The constant NOT_FOUND_URL_AVATAR.
     */
    const val NOT_FOUND_URL_AVATAR = "?alt=/wow/static/images/2d/avatar/"

    /**
     * The constant D3_ICON_ITEMS.
     */
    const val D3_ICON_ITEMS = "http://media.blizzard.com/d3/icons/items/large/icon.png"

    /**
     * D3 images
     */
    const val D3_ASSETS = "https://alexchaoss.github.io/BnetAuthorize/img/d3/image.png"

    /**
     * The constant D3_ICON_SKILLS.
     */
    const val D3_ICON_SKILLS = "http://media.blizzard.com/d3/icons/skills/64/url.png"

    /**
     * The constant OW_PROFILE.
     */
    const val OW_PROFILE = "https://ow-api.com/v1/stats/:platform/:region/:battletag/complete"

    /**
     * Overwatch images
     */
    const val OW_ASSETS = "https://alexchaoss.github.io/BnetAuthorize/img/ow/image.png"

    /**
     * SC2 images
     */
    const val SC2_ASSETS = "https://alexchaoss.github.io/BnetAuthorize/img/sc2/image.jpg"

    /**
     * WoW images
     */
    const val WOW_ASSETS = "https://alexchaoss.github.io/BnetAuthorize/img/wow/image.jpg"

    /**
     * Gets base url for user information.
     *
     * @return the base url for user information
     */
    @JvmStatic
    val baseURLforUserInformation: String
        get() = if (MainActivity.selectedRegion.equals("cn", ignoreCase = true)) {
            BASE_URL_CN_USER_INFO
        } else {
            BASE_URL_USER_INFO.replace("zone", MainActivity.selectedRegion.toLowerCase())
        }

    /**
     * Gets region.
     *
     * @return the region
     */
    val region: String
        get() = MainActivity.selectedRegion.toLowerCase()

    /**
     * Gets ow profile.
     *
     * @param username the username
     * @param platform the platform
     * @return the ow profile
     */
    @JvmStatic
    fun getOWProfile(username: String, platform: String): String {
        var url: String
        if (platform.equals("PC", ignoreCase = true)) {
            url = OW_PROFILE.replace(":battletag", username.replace("#", "-"))
            url = url.replace(":platform", "pc")
            url = if (MainActivity.selectedRegion.equals("cn", ignoreCase = true) || MainActivity.selectedRegion.equals("tw", ignoreCase = true)) {
                url.replace(":region", "asia")
            } else {
                url.replace(":region", MainActivity.selectedRegion.toLowerCase())
            }
        } else {
            url = OW_PROFILE.replace(":battletag", username)
            url = url.replace(":platform", platform)
            url = url.replace(":region", "global")
        }
        return url
    }

    @JvmStatic
    fun getOWPortraitImage(character: String): String {
        return OW_ASSETS.replace("image", character + "_portrait")
    }

    fun getOWIconImage(character: String): String {
        return OW_ASSETS.replace("image", character + "_icon")
    }

    fun getD3Asset(name: String): String {
        return D3_ASSETS.replace("image", name)
    }

    fun getSC2Asset(name: String): String {
        return SC2_ASSETS.replace("image", name)
    }

    fun getWoWAsset(name: String): String {
        return WOW_ASSETS.replace("image", name)
    }

    fun selectAchievementsURLFromLocale(locale: String): String {
        return when (locale) {
            "en_US" -> "https://www.wow-query.dev/%E2%9A%A1/3d218bc078f415aa.json"
            "es_ES" -> "https://www.wow-query.dev/%E2%9A%A1/fa67cc53c8e645c2.json"
            "fr_FR" -> "https://www.wow-query.dev/%E2%9A%A1/f4a82af9f9a3b06e.json"
            "ru_RU" -> "https://www.wow-query.dev/%E2%9A%A1/5add80989ba11424.json"
            "de_DE" -> "https://www.wow-query.dev/%E2%9A%A1/6b01d52d98a226d8.json"
            "pt_BR" -> "https://www.wow-query.dev/%E2%9A%A1/09cd13db05b498c1.json"
            "it_IT" -> "https://www.wow-query.dev/%E2%9A%A1/db57e9fc66b34ad2.json"
            "ko_KR" -> "https://www.wow-query.dev/%E2%9A%A1/aa40432c0e2ce6b8.json"
            "zh_CN" -> "https://www.wow-query.dev/%E2%9A%A1/f8b7ceaacd7035e6.json"
            "zh_TW" -> "https://www.wow-query.dev/%E2%9A%A1/7bcf03198334e45e.json"
            else -> "https://www.wow-query.dev/%E2%9A%A1/3d218bc078f415aa.json"
        }
    }

    fun selectAchievementCategoriesURLFromLocale(locale: String): String {
        return when (locale) {
            "en_US" -> "https://www.wow-query.dev/%E2%9A%A1/f5162c8709c85d38.json"
            "es_ES" -> "https://www.wow-query.dev/%E2%9A%A1/eb00c34ad013d02a.json"
            "fr_FR" -> "https://www.wow-query.dev/%E2%9A%A1/91deb47b92935735.json"
            "ru_RU" -> "https://www.wow-query.dev/%E2%9A%A1/c86e44ace6711849.json"
            "de_DE" -> "https://www.wow-query.dev/%E2%9A%A1/6c8376291e5e1427.json"
            "pt_BR" -> "https://www.wow-query.dev/%E2%9A%A1/5105a4ac0e1ebbea.json"
            "it_IT" -> "https://www.wow-query.dev/%E2%9A%A1/73aa4b8220559fff.json"
            "ko_KR" -> "https://www.wow-query.dev/%E2%9A%A1/0323d735a1b3a67d.json"
            "zh_CN" -> "https://www.wow-query.dev/%E2%9A%A1/77183b7db846654b.json"
            "zh_TW" -> "https://www.wow-query.dev/%E2%9A%A1/cb910864ffde2f28.json"
            else -> "https://www.wow-query.dev/%E2%9A%A1/f5162c8709c85d38.json"
        }
    }
}
package com.BlizzardArmory.network

import android.net.Uri
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.Version
import java.util.Locale

/**
 * The type Url constants.
 */
object NetworkUtils {
    /**
     * The constant loading.
     */
    @JvmField
    var loading = false
    var PROXY_BASE_URL = "https://proxy.astpierre.dev/"
    var API_BASE_URL = "https://api.astpierre.dev/"

    const val logs = false

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

    const val WOW_DUNGEON_ASSETS = "https://render.worldofwarcraft.com/us/zones/image.jpg"

    const val WOW_SERVER = "https://blizzard-armory-server.herokuapp.com/"

    /**
     * Gets region.
     *
     * @return the region
     */
    val region: String
        get() = NavigationActivity.selectedRegion.lowercase(Locale.getDefault())

    val locale: String
        get() = NavigationActivity.locale

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
            url =
                if (region.equals("cn", ignoreCase = true) || region.equals("tw", ignoreCase = true)) {
                    url.replace(":region", "asia")
                } else {
                    url.replace(":region", region.lowercase(Locale.getDefault()))
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
        return OW_ASSETS.replace("image", character + "_portrait") + "?version=${Version.code()}"
    }

    fun getOWIconImage(character: String): String {
        return OW_ASSETS.replace("image", character + "_icon") + "?version=${Version.code()}"
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

    fun getWoWDungeonAsset(name: String): String {
        return WOW_DUNGEON_ASSETS.replace("image", name)
    }

    /*
    TO BE CHANGED
     */
    fun getOverwatchLeagueData(year: String): String {
        return "https://public-archived-data.s3.us-east-2.amazonaws.com/standings/archived+standings+$year.json"
    }

    fun getExpansionFromRIO(expansionId: Int): String {
        return "https://raider.io/api/v1/mythic-plus/static-data?expansion_id=$expansionId"
    }

    fun replaceUriParameter(uri: Uri, key: String, newValue: String): Uri? {
        val params: Set<String> = uri.queryParameterNames
        val newUri: Uri.Builder = uri.buildUpon().clearQuery()
        for (param in params) {
            newUri.appendQueryParameter(
                param,
                if (param == key) newValue else uri.getQueryParameter(param)
            )
        }
        return newUri.build()
    }

}
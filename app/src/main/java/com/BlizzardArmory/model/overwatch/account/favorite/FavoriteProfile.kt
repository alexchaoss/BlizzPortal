package com.BlizzardArmory.model.overwatch.account.favorite

import com.BlizzardArmory.model.overwatch.account.Profile
import com.google.gson.annotations.SerializedName


class FavoriteProfile(platform: String?, username: String?, profile: Profile?) {

    @SerializedName("platform")
    var platform: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("profile")
    var profile: Profile? = null

    init {
        this.profile = profile
        this.platform = platform
        this.username = username
    }
}
package com.BlizzardArmory.model.overwatch.account.favorite

import com.google.gson.annotations.SerializedName


class FavoriteProfiles {

    @SerializedName("profile_list")
    var profiles = arrayListOf<FavoriteProfile>()
}
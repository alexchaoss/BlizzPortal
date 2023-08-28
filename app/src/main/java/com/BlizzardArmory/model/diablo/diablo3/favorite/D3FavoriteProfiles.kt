package com.BlizzardArmory.model.diablo.diablo3.favorite

import com.google.gson.annotations.SerializedName


class D3FavoriteProfiles {

    @SerializedName("profiles")
    val profiles = arrayListOf<D3FavoriteProfile>()
}
package com.BlizzardArmory.model.diablo.diablo4.account

import com.google.gson.annotations.SerializedName


data class Account(
    @SerializedName("dungeons_completed") var dungeonsCompleted: Int? = null,
    @SerializedName("players_killed") var playersKilled: Int? = null,
    @SerializedName("bosses_killed") var bossesKilled: Int? = null,
    @SerializedName("characters") var characters: List<Characters> = arrayListOf()
)
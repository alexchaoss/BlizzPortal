package com.BlizzardArmory.model.diablo.diablo4.character

import com.google.gson.annotations.SerializedName


data class Character(
    @SerializedName("character") var character: String? = null,
    @SerializedName("queue") var queue: Int? = null,
    @SerializedName("lastUpdate") var lastUpdate: Long? = null,
    @SerializedName("accountLastUpdate") var accountLastUpdate: Int? = null,
    @SerializedName("class") var charClass: String? = null,
    @SerializedName("level") var level: Int? = null,
    @SerializedName("skills") var skills: ArrayList<Skills> = arrayListOf(),
    @SerializedName("equipment") var equipment: ArrayList<Equipment> = arrayListOf(),
    @SerializedName("secondsPlayed") var secondsPlayed: Long? = null,
    @SerializedName("lastLogin") var lastLogin: Long? = null,
    @SerializedName("worldTier") var worldTier: Int? = null,
    @SerializedName("createdAt") var createdAt: Long? = null,
    @SerializedName("monstersKilled") var monstersKilled: Int? = null,
    @SerializedName("elitesKilled") var elitesKilled: Int? = null,
    @SerializedName("goldCollected") var goldCollected: Int? = null,
    @SerializedName("power") var power: Int? = null,
    @SerializedName("hardcore") var hardcore: Boolean? = null,
    @SerializedName("dead") var dead: Boolean? = null
)
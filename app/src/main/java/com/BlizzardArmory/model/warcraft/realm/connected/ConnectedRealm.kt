package com.BlizzardArmory.model.warcraft.realm.connected

import com.google.gson.annotations.SerializedName


data class ConnectedRealm(

    @SerializedName("realms") val realms: List<Realms>,
    @SerializedName("id") val id: Int,
    @SerializedName("has_queue") val has_queue: Boolean,
    @SerializedName("status") val status: Status,
    @SerializedName("population") val population: Population
)
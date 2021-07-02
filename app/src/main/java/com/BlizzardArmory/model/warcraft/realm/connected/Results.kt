package com.BlizzardArmory.model.warcraft.realm.connected

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class Results(

    @SerializedName("key") val key: Key,
    @SerializedName("data") val connectedRealm: ConnectedRealm
)
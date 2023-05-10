package com.BlizzardArmory.model.warcraft.realm.connected

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Results(

    @SerializedName("key") val key: Key,
    @SerializedName("data") val connectedRealm: ConnectedRealm
)
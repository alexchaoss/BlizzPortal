package com.BlizzardArmory.model.diablo.diablo4

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("character_service") var characterService: Boolean? = null,
    @SerializedName("event_service") var eventService: Boolean? = null,
)

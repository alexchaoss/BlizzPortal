package com.BlizzardArmory.model.diablo.diablo4.character

import com.google.gson.annotations.SerializedName


data class Skills(
    @SerializedName("name") var name: String? = null,
    @SerializedName("desc") var desc: String? = null
)
package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.SerializedName


/**
 * The type Spec.
 */
data class Spec(

    @SerializedName("name")
    var name: String,

    @SerializedName("role")
    var role: String,

    @SerializedName("backgroundImage")
    var backgroundImage: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("order")
    var order: Int

)
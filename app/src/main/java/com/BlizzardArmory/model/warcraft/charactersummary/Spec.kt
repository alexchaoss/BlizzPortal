package com.BlizzardArmory.model.warcraft.charactersummary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Spec.
 */
data class Spec(

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("role")
        @Expose
        var role: String,

        @SerializedName("backgroundImage")
        @Expose
        var backgroundImage: String,

        @SerializedName("icon")
        @Expose
        var icon: String,

        @SerializedName("description")
        @Expose
        var description: String,

        @SerializedName("order")
        @Expose
        var order: Int

)
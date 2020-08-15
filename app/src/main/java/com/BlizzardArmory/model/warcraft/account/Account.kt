package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Account.
 */
data class Account(

        @field:Expose
        @field:SerializedName("_links")
        var links: Links,

        @field:Expose
        @field:SerializedName("id")
        var id: Long,

        @SerializedName("wow_accounts")
        @Expose
        var wowAccounts: List<WowAccount>,

        @SerializedName("collections")
        @Expose
        var collections: Collections
)
package com.BlizzardArmory.model.warcraft.account

import com.google.gson.annotations.SerializedName


/**
 * The type Account.
 */
data class Account(

    @SerializedName("_links")
    var links: Links,

    @SerializedName("id")
    var id: Long,

    @SerializedName("wow_accounts")
    var wowAccounts: List<WowAccount>,

    @SerializedName("collections")
    var collections: Collections
)
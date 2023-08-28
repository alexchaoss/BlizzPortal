package com.BlizzardArmory.model.diablo.diablo3.favorite

import com.BlizzardArmory.model.diablo.diablo3.account.AccountInformation
import com.google.gson.annotations.SerializedName

class D3FavoriteProfile(accountInformation: AccountInformation, region: String, battletag: String) {

    @SerializedName("account")
    var accountInformation: AccountInformation? = null

    @SerializedName("battletag")
    var battletag: String? = null

    @SerializedName("region")
    var region: String? = null

    init {
        this.accountInformation = accountInformation
        this.battletag = battletag
        this.region = region
    }

}
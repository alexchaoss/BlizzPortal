package com.BlizzardArmory.network.oauth

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("scope") val scope: String
)
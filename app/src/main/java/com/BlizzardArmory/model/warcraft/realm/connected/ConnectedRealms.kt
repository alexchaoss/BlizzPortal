package com.BlizzardArmory.model.warcraft.realm.connected

import com.google.gson.annotations.SerializedName


data class ConnectedRealms(

    @SerializedName("page") val page: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("maxPageSize") val maxPageSize: Int,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("results") val results: List<Results>
)
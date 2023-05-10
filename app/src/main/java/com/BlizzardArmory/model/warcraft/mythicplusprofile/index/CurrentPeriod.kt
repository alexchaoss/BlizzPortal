package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import androidx.annotation.Keep
import com.BlizzardArmory.model.warcraft.mythicplusprofile.BestRuns
import com.google.gson.annotations.SerializedName


@Keep
data class CurrentPeriod(
    @SerializedName("period") var period: Period? = Period(),
    @SerializedName("best_runs") var bestRuns: ArrayList<BestRuns> = arrayListOf()
)
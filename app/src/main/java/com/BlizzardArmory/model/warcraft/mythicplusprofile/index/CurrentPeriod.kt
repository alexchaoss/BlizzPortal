package com.BlizzardArmory.model.warcraft.mythicplusprofile.index

import com.BlizzardArmory.model.warcraft.mythicplusprofile.BestRuns
import com.google.gson.annotations.SerializedName


data class CurrentPeriod(
    @SerializedName("period") var period: Period? = Period(),
    @SerializedName("best_runs") var bestRuns: ArrayList<BestRuns> = arrayListOf()
)
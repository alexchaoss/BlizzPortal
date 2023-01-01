package com.BlizzardArmory.model.warcraft.talents.trees

import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


data class SpecTalentTrees (

  @SerializedName("key") var key : Key,
  @SerializedName("name") var name : String? = null

)
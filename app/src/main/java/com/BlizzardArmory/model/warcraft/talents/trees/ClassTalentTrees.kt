package com.BlizzardArmory.model.warcraft.talents.trees

import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Key
import com.google.gson.annotations.SerializedName


@Keep
data class ClassTalentTrees (

  @SerializedName("key") var key  : Key?,
  @SerializedName("name") var name : String

)
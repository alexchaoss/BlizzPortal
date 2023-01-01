package com.BlizzardArmory.model.warcraft.talents.trees

import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


data class TalentTrees (
  @SerializedName("_links") var Links : Links,
  @SerializedName("spec_talent_trees") var specTalentTrees : ArrayList<SpecTalentTrees> = arrayListOf(),
  @SerializedName("class_talent_trees") var classTalentTrees : ArrayList<ClassTalentTrees> = arrayListOf()
)
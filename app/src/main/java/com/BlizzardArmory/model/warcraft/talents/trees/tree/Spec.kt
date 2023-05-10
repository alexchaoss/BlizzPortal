package com.BlizzardArmory.model.warcraft.talents.trees.tree
import androidx.annotation.Keep
import com.BlizzardArmory.model.common.Links
import com.google.gson.annotations.SerializedName


@Keep
data class Spec (

	@SerializedName("_links") val _links : Links,
	@SerializedName("id") val id : Int,
	@SerializedName("playable_class") val playable_class : PlayableClass,
	@SerializedName("playable_specialization") val playable_specialization : Playable_specialization,
	@SerializedName("name") val name : String,
	@SerializedName("media") val media : Media,
	@SerializedName("restriction_lines") val restriction_lines : List<RestrictionLines>,
	@SerializedName("class_talent_nodes") val class_talent_nodes : List<ClassTalentNodes>,
	@SerializedName("spec_talent_nodes") val spec_talent_nodes : List<SpecTalentNodes>
)
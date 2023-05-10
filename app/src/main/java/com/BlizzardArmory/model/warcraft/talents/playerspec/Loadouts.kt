package com.BlizzardArmory.model.warcraft.talents.playerspec

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Loadouts (

	@SerializedName("is_active") val isActive : Boolean,
	@SerializedName("talent_loadout_code") val talentLoadoutCode : String,
	@SerializedName("selected_class_talents") val selectedClassTalents : List<SelectedClassTalents>,
	@SerializedName("selected_spec_talents") val selectedSpecTalents : List<SelectedSpecTalents>
)
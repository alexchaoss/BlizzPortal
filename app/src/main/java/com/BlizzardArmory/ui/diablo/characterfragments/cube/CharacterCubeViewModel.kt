package com.BlizzardArmory.ui.diablo.characterfragments.cube

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.item.SingleItem
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CharacterCubeViewModel : BaseViewModel() {

    private val singleItem: MutableLiveData<SingleItem> = MutableLiveData()

    fun getItem(): LiveData<SingleItem> {
        return singleItem
    }

    fun downloadCubeItems(characterInformation: CharacterInformation) {
        for (i in characterInformation.legendaryPowers.indices) {
            Log.i("Cube", characterInformation.legendaryPowers[i].tooltipParams)
            val endpoint = characterInformation.legendaryPowers[i].tooltipParams.replace("/item/", "")
            val job = coroutineScope.launch {
                val response = RetroClient.getClient().getItem(endpoint, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        singleItem.value = response.body()!!
                        if (i == characterInformation.legendaryPowers.size - 1) {
                            URLConstants.loading = false
                        }
                    }
                }
            }
            jobs.add(job)
        }
    }
}
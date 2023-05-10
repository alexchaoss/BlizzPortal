package com.BlizzardArmory.ui.warcraft.character.mythicplus

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicplusprofile.KeystoneAffixes
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AffixViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_mplus_affix, parent, false)) {

    private var icon: ImageView? = null

    init {
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(affixes: KeystoneAffixes) {
        downloadAffixMedia(affixes.id)
    }

    private fun downloadAffixMedia(id: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val response = RetroClient.getWoWClient(context, cacheTime = 365L)
                .getMythicKeystoneAffixMedia(id, "static-${NetworkUtils.region}")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Glide.with(itemView).load(response.body()?.assets!![0].value).centerCrop()
                        .into(icon!!)
                } else {
                    Log.d("Asset Affix", "Error downloading asset")
                }
            }
        }
    }
}
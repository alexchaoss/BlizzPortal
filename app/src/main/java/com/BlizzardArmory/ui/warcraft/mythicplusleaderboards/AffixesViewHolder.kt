package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.KeystoneAffixes
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AffixesViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_mplus_affixes, parent, false)) {

    var name: TextView? = null
    var icon: ImageView? = null

    init {
        name = itemView.findViewById(R.id.affixname)
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(affix: KeystoneAffixes) {
        name?.text = affix.keystone_affixstone_affix.name
        downloadAffixMedia(affix.keystone_affixstone_affix.id)
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

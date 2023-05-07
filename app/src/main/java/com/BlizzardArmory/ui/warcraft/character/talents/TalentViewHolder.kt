package com.BlizzardArmory.ui.warcraft.character.talents

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.talents.playerspec.SelectedClassTalents
import com.BlizzardArmory.model.warcraft.talents.playerspec.SelectedSpecTalents
import com.BlizzardArmory.model.warcraft.talents.playerspec.Tooltip
import com.BlizzardArmory.network.RetroClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TalentViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_talent_list, parent, false)) {

    private var icon: ImageView? = null
    private var name: TextView? = null
    private var rank: TextView? = null
    private var spellCost: TextView? = null
    private var spellCast: TextView? = null
    private var spellDesc: TextView? = null
    private var spellCD: TextView? = null

    init {
        icon = itemView.findViewById(R.id.icon)
        name = itemView.findViewById(R.id.name)
        rank = itemView.findViewById(R.id.rank)
        spellCost = itemView.findViewById(R.id.spell_cost)
        spellCast = itemView.findViewById(R.id.spell_cast)
        spellDesc = itemView.findViewById(R.id.spell_description)
        spellCD = itemView.findViewById(R.id.spell_cd)
    }

    fun <T>bind(talent: T) {
        var tooltip: Tooltip? = null
        var talentRank = 0
        if (talent is SelectedClassTalents) {
            tooltip = talent.tooltip
            talentRank = talent.rank
        } else if (talent is SelectedSpecTalents) {
            tooltip = talent.tooltip
            talentRank = talent.rank
        }
        downloadMedia(tooltip!!.spellTooltip.spell.id)
        name?.text = tooltip.talent.name
        rank?.text = "Rank $talentRank"

        if (tooltip.spellTooltip.powerCost == null) {
            spellCost?.visibility = View.GONE
        }
        spellCost?.text = tooltip.spellTooltip.powerCost
        spellCast?.text = tooltip.spellTooltip.castTime
        spellDesc?.text = tooltip.spellTooltip.description
        spellCD?.text = tooltip.spellTooltip.cooldown
    }

    private fun downloadMedia(id: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val response = RetroClient.getWoWClient(context, cacheTime = 365L).getSpellMedia(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val media = response.body()!!
                    Glide.with(itemView).load(media.assets?.get(0)?.value).into(icon!!)
                } else {
                    Log.e("Error", "Code: ${response.code()} Message: ${response.message()}")
                }
            }
        }
    }
}
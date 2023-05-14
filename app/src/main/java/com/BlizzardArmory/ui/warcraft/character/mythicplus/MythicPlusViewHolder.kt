package com.BlizzardArmory.ui.warcraft.character.mythicplus

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.mythicplusprofile.BestRuns
import com.BlizzardArmory.network.NetworkUtils
import com.bumptech.glide.Glide

class MythicPlusViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_mythicplus_list, parent, false)) {


    private var parentLayout: ConstraintLayout? = null
    private var keystoneLevel: TextView? = null
    private var dgName: TextView? = null
    private var time: TextView? = null
    private var rating: TextView? = null
    private var banner: ImageView? = null
    private var recyclerView: RecyclerView? = null


    init {
        parentLayout = itemView.findViewById(R.id.parentLayout)
        keystoneLevel = itemView.findViewById(R.id.keystonelevel)
        time = itemView.findViewById(R.id.time)
        dgName = itemView.findViewById(R.id.dg_name)
        rating = itemView.findViewById(R.id.rating)
        banner = itemView.findViewById(R.id.dg_image)
        recyclerView = itemView.findViewById(R.id.affix_recycler)
    }

    fun bind(bestRuns: BestRuns) {
        val ratingColor = bestRuns.mythicRating.color
        val color = Color.argb(255, ratingColor.r, ratingColor.g, ratingColor.b)
        keystoneLevel?.text = bestRuns.keystoneLevel.toString()
        parentLayout?.setBackgroundColor(color)
        rating?.setTextColor(color)
        dgName?.text = bestRuns.dungeon.name
        rating?.text = "Rating: ${bestRuns.mythicRating.rating.toInt()}"
        time?.text = parseTime(bestRuns.duration)
        Glide.with(itemView.context).load(NetworkUtils.getWoWDungeonAsset(parseDungeonName(bestRuns.dungeon.name))).into(banner!!)
        recyclerView?.apply {
            adapter = AffixAdapter(bestRuns.keystoneAffixes, context)
        }
    }

    private fun parseTime(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60)) % 60
        val hours = milliseconds / (1000 * 60 * 60)
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

    private fun parseDungeonName(name: String): String {
        return "${name.replace("'", "").replace(",", "").replace(":", "").replace(" ", "-").lowercase()}-small"
    }
}
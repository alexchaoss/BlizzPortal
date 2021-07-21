package com.BlizzardArmory.ui.warcraft.pvpleaderboards

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.pvp.leaderboards.Entries
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LeaderboardViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.wow_pvp_leaderboard_list, parent, false)) {

    var rank: TextView? = null
    var player: TextView? = null
    var rating: TextView? = null
    var tier: ImageView? = null
    var faction: ImageView? = null
    var win: TextView? = null
    var loss: TextView? = null

    lateinit var characterSummary: CharacterSummary

    init {
        rank = itemView.findViewById(R.id.rank)
        player = itemView.findViewById(R.id.name)
        rating = itemView.findViewById(R.id.rating)
        tier = itemView.findViewById(R.id.tier)
        faction = itemView.findViewById(R.id.faction)
        win = itemView.findViewById(R.id.win)
        loss = itemView.findViewById(R.id.loss)
    }

    fun bind(entry: Entries, region: String) {
        rank?.text = entry.rank.toString()
        player?.text = entry.character.name
        if (entry.faction.type.lowercase() == "alliance") {
            faction?.setImageResource(R.drawable.alliance_logo)
        } else {
            faction?.setImageResource(R.drawable.horde_logo)
        }
        setTierImage(tier!!, entry.tier.id)

        win?.text = entry.season_matchStatistics.won.toString()

        loss?.text = entry.season_matchStatistics.lost.toString()

        rating?.text = entry.rating.toString()

        onClickCharacter(entry, region)

        CoroutineScope(Dispatchers.Default).launch {
            val response = RetroClient.getWoWClient(context)
                .getCharacter(entry.character.name, entry.character.realm.slug, region)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    characterSummary = response.body()!!
                    player?.setTextColor(Color.parseColor(WoWClassColor.getClassColor(characterSummary.characterClass.id)))
                } else {
                    Log.i("Error", "Couldn't download character summary")
                }
            }
        }
    }

    private fun setTierImage(imageView: ImageView, tier: Int) {
        when (tier) {
            1, 8, 16 -> imageView.setImageResource(R.drawable.unranked)
            2, 9, 17 -> imageView.setImageResource(R.drawable.combatant)
            3, 11, 18 -> imageView.setImageResource(R.drawable.challenger)
            4, 12, 19 -> imageView.setImageResource(R.drawable.rival)
            5, 13, 20 -> imageView.setImageResource(R.drawable.duelist)
            6, 14, 21 -> imageView.setImageResource(R.drawable.elite)
        }
    }

    private fun onClickCharacter(entry: Entries, region: String) {
        player?.setOnClickListener {
            val woWNavFragment = WoWNavFragment.newInstance(entry.character.name, entry.character.realm.slug, "null", region)
            (context as NavigationActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
                .add(R.id.fragment, woWNavFragment, "NAV_FRAGMENT")
                .addToBackStack("wow_nav").commit()
            context.supportFragmentManager.executePendingTransactions()
        }
    }
}

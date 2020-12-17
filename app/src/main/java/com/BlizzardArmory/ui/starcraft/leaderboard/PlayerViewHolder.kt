package com.BlizzardArmory.ui.starcraft.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.starcraft.leaderboard.Character
import com.BlizzardArmory.model.starcraft.leaderboard.LadderMembers
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.diablo.account.D3Fragment
import com.BlizzardArmory.ui.navigation.GamesActivity


class PlayerViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_player_list, parent, false)) {

    var name: TextView? = null
    var icon: ImageView? = null

    init {
        name = itemView.findViewById(R.id.btag)
        icon = itemView.findViewById(R.id.icon)
    }

    fun bind(player: LadderMembers) {


        onClickCharacter(player.character)
    }

    private fun onClickCharacter(player: Character) {
        val battlenetOAuth2Params: BattlenetOAuth2Params? = (context as GamesActivity).intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        itemView.setOnClickListener {
            val fragment: Fragment = D3Fragment()
            context.supportFragmentManager.beginTransaction().add(R.id.fragment, fragment, "d3fragment").addToBackStack("d3fragment").commit()
            context.supportFragmentManager.executePendingTransactions()
        }
    }

}

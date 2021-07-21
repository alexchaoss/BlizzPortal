package com.BlizzardArmory.ui.diablo.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfile
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.diablo.account.D3Fragment


class FavoritesViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_favorite_profile, parent, false)) {

    private var btag: TextView? = null
    private var eliteKills: TextView? = null
    private var paragon: TextView? = null
    private var lifetimeKills: TextView? = null
    private var profileLayout: ConstraintLayout? = null
    private var fragmentManager: FragmentManager? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null

    init {
        btag = itemView.findViewById(R.id.battletag)
        paragon = itemView.findViewById(R.id.paragon)
        eliteKills = itemView.findViewById(R.id.elite)
        lifetimeKills = itemView.findViewById(R.id.lifetime)
        profileLayout = itemView.findViewById(R.id.profile_layout)
    }

    fun bind(profile: D3FavoriteProfile, fragmentManager: FragmentManager, battlenetOAuth2Params: BattlenetOAuth2Params) {
        this.fragmentManager = fragmentManager
        this.battlenetOAuth2Params = battlenetOAuth2Params
        btag?.text = profile.battletag
        eliteKills?.text = profile.accountInformation?.kills?.elites.toString()
        lifetimeKills?.text = profile.accountInformation?.kills?.monsters.toString()
        paragon?.text = profile.accountInformation?.paragonLevel.toString()
        onClickCharacter(profile, fragmentManager)
    }

    private fun onClickCharacter(profile: D3FavoriteProfile, fragmentManager: FragmentManager) {
        profileLayout?.setOnClickListener {
            val fragment: Fragment = D3Fragment()
            val bundle = Bundle()
            bundle.putString("battletag", profile.battletag)
            bundle.putString("region", profile.region)
            bundle.putParcelable(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
            fragment.arguments = bundle
            fragmentManager.beginTransaction().add(R.id.fragment, fragment, "d3fragment")
                .addToBackStack("d3_account").commit()
            fragmentManager.executePendingTransactions()
        }
    }

}

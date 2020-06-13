package com.BlizzardArmory.ui.ui_diablo.favorites

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
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfile
import com.BlizzardArmory.ui.ui_diablo.D3Fragment


class FavoritesViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.d3_favorite_profile, parent, false)) {

    private var btag: TextView? = null
    private var eliteKills: TextView? = null
    private var paragon: TextView? = null
    private var lifetimeKills: TextView? = null
    private var profileLayout: ConstraintLayout? = null
    private var fragmentManager: FragmentManager? = null
    private var bnOAuth2Params: BnOAuth2Params? = null

    init {
        btag = itemView.findViewById(R.id.battletag)
        paragon = itemView.findViewById(R.id.paragon)
        eliteKills = itemView.findViewById(R.id.elite)
        lifetimeKills = itemView.findViewById(R.id.lifetime)
        profileLayout = itemView.findViewById(R.id.profile_layout)
    }

    fun bind(profile: D3FavoriteProfile, fragmentManager: FragmentManager, bnOAuth2Params: BnOAuth2Params) {
        this.fragmentManager = fragmentManager
        this.bnOAuth2Params = bnOAuth2Params
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
            bundle.putParcelable(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
            fragment.arguments = bundle
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment, "d3fragment").commit()
            fragmentManager.executePendingTransactions()
        }
    }

}

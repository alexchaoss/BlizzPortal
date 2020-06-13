package com.BlizzardArmory.ui.ui_diablo.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfiles
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.wow_favorites.*

class D3FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder().create()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Params: BnOAuth2Params? = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)

        val favoriteProfileString = prefs.getString("d3-favorites", "DEFAULT")
        if (favoriteProfileString != null && favoriteProfileString != "DEFAULT") {
            val favoriteCharacters = gson.fromJson(favoriteProfileString, D3FavoriteProfiles::class.java)
            characters_recycler.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = FavoritesAdapter(favoriteCharacters.profiles, parentFragmentManager, bnOAuth2Params!!, requireActivity())
            }
        }
    }
}
package com.BlizzardArmory.ui.ui_warcraft.favorites

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacters
import com.BlizzardArmory.ui.ui_warcraft.account.FavoritesAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.wow_favorites.*

class WoWFavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder().create()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Params: BnOAuth2Params? = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)

        var favoriteCharactersString = prefs.getString("wow-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "{\"characters\":[]}" && favoriteCharactersString != "DEFAULT") {
            val favoriteCharacters = gson.fromJson(favoriteCharactersString, FavoriteCharacters::class.java)
            characters_recycler.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = FavoritesAdapter(favoriteCharacters.characters, parentFragmentManager, requireActivity(), bnOAuth2Params!!)
            }
        }
    }
}
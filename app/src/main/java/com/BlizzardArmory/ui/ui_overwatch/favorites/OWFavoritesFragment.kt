package com.BlizzardArmory.ui.ui_overwatch.favorites

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfiles
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.wow_favorites.*

class OWFavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder().create()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        if (prefs.contains("ow-favorites")) {
            val profiles = gson.fromJson(prefs.getString("ow-favorites", "DEFAULT"), FavoriteProfiles::class.java)
            characters_recycler.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = FavoritesAdapter(profiles.profiles, parentFragmentManager, requireActivity())
            }
        }
    }
}
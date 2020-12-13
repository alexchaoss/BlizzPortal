package com.BlizzardArmory.ui.warcraft.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.databinding.WowFavoritesBinding
import com.BlizzardArmory.model.warcraft.favorite.FavoriteCharacters
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.ui.warcraft.account.FavoritesAdapter
import com.google.gson.GsonBuilder

class WoWFavoritesFragment : Fragment() {

    private var _binding: WowFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = WowFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder().create()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val battlenetOAuth2Params: BattlenetOAuth2Params? = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        val favoriteCharactersString = prefs.getString("wow-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "{\"characters\":[]}" && favoriteCharactersString != "DEFAULT") {
            val favoriteCharacters = gson.fromJson(favoriteCharactersString, FavoriteCharacters::class.java)
            binding.charactersRecycler.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = FavoritesAdapter(favoriteCharacters.characters, parentFragmentManager, requireActivity(), battlenetOAuth2Params!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        fun addOnBackPressCallback(activity: GamesActivity){
            activity.onBackPressedDispatcher.addCallback {
                NewsPageFragment.addOnBackPressCallback(activity)
                activity.supportFragmentManager.popBackStack()
            }
        }
    }
}
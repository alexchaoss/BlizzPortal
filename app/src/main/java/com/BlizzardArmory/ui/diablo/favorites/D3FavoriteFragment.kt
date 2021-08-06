package com.BlizzardArmory.ui.diablo.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.databinding.D3FavoritesBinding
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfiles
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.google.gson.GsonBuilder

class D3FavoriteFragment : Fragment() {

    private var _binding: D3FavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = D3FavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder().create()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val battlenetOAuth2Params: BattlenetOAuth2Params? = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

        val favoriteProfileString = prefs.getString("d3-favorites", "DEFAULT")
        if (favoriteProfileString != null && favoriteProfileString != "DEFAULT") {
            val favoriteCharacters = gson.fromJson(favoriteProfileString, D3FavoriteProfiles::class.java)
            binding.charactersRecycler.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = FavoritesAdapter(favoriteCharacters.profiles, parentFragmentManager, battlenetOAuth2Params!!, requireActivity())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                NewsPageFragment.addOnBackPressCallback(activity)
                activity.supportFragmentManager.popBackStack()
            }
        }
    }
}
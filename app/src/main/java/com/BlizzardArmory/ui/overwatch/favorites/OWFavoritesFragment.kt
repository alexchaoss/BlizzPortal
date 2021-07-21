package com.BlizzardArmory.ui.overwatch.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.databinding.WowFavoritesBinding
import com.BlizzardArmory.model.overwatch.favorite.FavoriteProfiles
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.google.gson.GsonBuilder

class OWFavoritesFragment : Fragment() {

    private var _binding: WowFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder().create()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        if (prefs.contains("ow-favorites")) {
            val profiles = gson.fromJson(prefs.getString("ow-favorites", "DEFAULT"), FavoriteProfiles::class.java)
            binding.charactersRecycler.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = FavoritesAdapter(profiles.profiles, parentFragmentManager, requireActivity())
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
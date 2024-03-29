package com.BlizzardArmory.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.HelpFragmentBinding
import com.BlizzardArmory.model.help.Help
import com.BlizzardArmory.ui.diablo.diablo3.account.D3Fragment
import com.BlizzardArmory.ui.diablo.diablo3.characterfragments.stats.CharacterStatsFragment
import com.BlizzardArmory.ui.diablo.diablo3.favorites.D3FavoriteFragment
import com.BlizzardArmory.ui.diablo.diablo3.leaderboard.D3LeaderboardFragment
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.ui.overwatch.account.OWFragment
import com.BlizzardArmory.ui.overwatch.favorites.OWFavoritesFragment
import com.BlizzardArmory.ui.starcraft.leaderboard.SC2LeaderboardFragment
import com.BlizzardArmory.ui.starcraft.profile.SC2Fragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.mythicplusleaderboards.MPlusLeaderboardsFragment
import com.BlizzardArmory.ui.warcraft.mythicraidleaderboards.MRaidLeaderboardsFragment
import com.BlizzardArmory.ui.warcraft.pvpleaderboards.PvpLeaderboardsFragment
import com.BlizzardArmory.util.state.FragmentTag
import com.google.gson.Gson


class HelpFragment : Fragment() {
    private lateinit var binding: HelpFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(requireActivity() as NavigationActivity)
        binding = HelpFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.closeButton.setOnClickListener {
            closeFragment(activity as NavigationActivity)
            requireActivity().supportFragmentManager.popBackStack()
        }
        val helpItems = Gson().fromJson(
            resources.openRawResource(R.raw.help_items).bufferedReader().use { it.readText() },
            Help::class.java
        )
        binding.recycler.apply {
            adapter = HelpAdapter(helpItems.helpItemList)
        }
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            closeFragment(activity)
        }

        private fun closeFragment(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (activity.supportFragmentManager.backStackEntryCount > 1) {
                    when (activity.supportFragmentManager.fragments[activity.supportFragmentManager.backStackEntryCount - 2].tag) {
                        FragmentTag.WOWFAVORITES.name -> WoWFavoritesFragment.addOnBackPressCallback(activity)
                        FragmentTag.D3FAVORITES.name -> D3FavoriteFragment.addOnBackPressCallback(activity)
                        FragmentTag.OWFAVORITES.name -> OWFavoritesFragment.addOnBackPressCallback(activity)
                        FragmentTag.D3NAV.name -> CharacterStatsFragment.addOnBackPressCallback(activity)
                        FragmentTag.D3FRAGMENT.name -> D3Fragment.addOnBackPressCallback(activity)
                        FragmentTag.D3LEADERBOARD.name -> D3LeaderboardFragment.addOnBackPressCallback(activity)
                        FragmentTag.NAVFRAGMENT.name -> WoWCharacterFragment.addOnBackPressCallback(activity)
                        FragmentTag.WOWFRAGMENT.name -> AccountFragment.addOnBackPressCallback(activity)
                        FragmentTag.WOWGUILDNAVFRAGMENT.name -> ActivityFragment.addOnBackPressCallback(activity)
                        FragmentTag.WOWMPLUSLEADERBOARD.name -> MPlusLeaderboardsFragment.addOnBackPressCallback(activity)
                        FragmentTag.WOWPVPLEADERBOARD.name -> PvpLeaderboardsFragment.addOnBackPressCallback(activity)
                        FragmentTag.WOWRAIDLEADERBOARD.name -> MRaidLeaderboardsFragment.addOnBackPressCallback(activity)
                        FragmentTag.SC2FRAGMENT.name -> SC2Fragment.addOnBackPressCallback(activity)
                        FragmentTag.SC2LEADERBOARD.name -> SC2LeaderboardFragment.addOnBackPressCallback(activity)
                        FragmentTag.OVERWATCHFRAGMENT.name -> OWFragment.addOnBackPressCallback(activity)
                        else -> NewsPageFragment.addOnBackPressCallback(activity)
                    }
                } else {
                    NewsPageFragment.addOnBackPressCallback(activity)
                }
                activity.supportFragmentManager.popBackStack()
            }
        }
    }
}
package com.BlizzardArmory.ui.warcraft.guild.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowGuildNavbarFragmentBinding
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.list.NewsListFragment
import com.BlizzardArmory.ui.warcraft.character.armory.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.mythicraidleaderboards.MRaidLeaderboardsFragment
import com.BlizzardArmory.util.events.NetworkEvent
import com.BlizzardArmory.util.state.FragmentTag
import com.discord.panels.PanelsChildGestureRegionObserver
import com.google.android.material.tabs.TabLayoutMediator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class GuildNavFragment : Fragment() {

    private var _binding: WowGuildNavbarFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowGuildNavbarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabsText = listOf("Activity", "Roster", resources.getString(R.string.achievements))
        val bundle = requireArguments()
        binding.wowPager.offscreenPageLimit = 2
        binding.wowPager.adapter = NavAdapter(childFragmentManager, this.lifecycle, binding.navBar.tabCount, bundle)
        TabLayoutMediator(binding.navBar, binding.wowPager) { tab, position ->
            tab.text = tabsText[position]
            binding.wowPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PanelsChildGestureRegionObserver.Provider.get().unregister(binding.wowPager)
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            when {
                requireActivity().supportFragmentManager.findFragmentByTag(FragmentTag.NAVFRAGMENT.name) != null -> {
                    WoWCharacterFragment.addOnBackPressCallback(activity as NavigationActivity)
                    requireActivity().supportFragmentManager.popBackStack()
                }
                requireActivity().supportFragmentManager.findFragmentByTag(FragmentTag.WOWRAIDLEADERBOARD.name) != null -> {
                    MRaidLeaderboardsFragment.addOnBackPressCallback(activity as NavigationActivity)
                    requireActivity().supportFragmentManager.popBackStack()
                }
                requireActivity().supportFragmentManager.findFragmentByTag(FragmentTag.WOWGUILDNAVFRAGMENT.name) != null -> {
                    ActivityFragment.addOnBackPressCallback(activity as NavigationActivity)
                    requireActivity().supportFragmentManager.popBackStack()
                }
                else -> {
                    NewsListFragment.addOnBackPressCallback(activity as NavigationActivity)
                    requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }
    }
}

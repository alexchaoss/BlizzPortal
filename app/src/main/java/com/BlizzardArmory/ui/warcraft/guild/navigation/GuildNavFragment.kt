package com.BlizzardArmory.ui.warcraft.guild.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.BlizzardArmory.databinding.WowGuildNavbarFragmentBinding
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.ui.warcraft.character.WoWCharacterFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.ui.warcraft.mythicraidleaderboard.MRaidLeaderboardsFragment
import com.BlizzardArmory.util.events.NetworkEvent
import com.discord.panels.PanelsChildGestureRegionObserver
import com.google.android.material.tabs.TabLayout
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class GuildNavFragment : Fragment() {

    private var guildName: String? = null
    private var realm: String? = null
    private var region: String? = null

    private var _binding: WowGuildNavbarFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowGuildNavbarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()
        binding.wowPager.offscreenPageLimit = 2
        val adapter = NavAdapter(childFragmentManager, binding.navBar.tabCount, bundle)
        binding.wowPager.adapter = adapter
        binding.wowPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.navBar))

        binding.navBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.wowPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PanelsChildGestureRegionObserver.Provider.get().remove(binding.wowPager.id)
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            when {
                requireActivity().supportFragmentManager.findFragmentByTag("NAV_FRAGMENT") != null -> {
                    WoWCharacterFragment.addOnBackPressCallback(activity as GamesActivity)
                    GamesActivity.favorite?.visibility = View.VISIBLE
                    requireActivity().supportFragmentManager.popBackStack()
                }
                requireActivity().supportFragmentManager.findFragmentByTag("mraidleaderboard") != null -> {
                    MRaidLeaderboardsFragment.addOnBackPressCallback(activity as GamesActivity)
                    requireActivity().supportFragmentManager.popBackStack()
                }
                requireActivity().supportFragmentManager.findFragmentByTag("guild_nav_fragment") != null -> {
                    ActivityFragment.addOnBackPressCallback(activity as GamesActivity)
                    requireActivity().supportFragmentManager.popBackStack()
                }
                else -> {
                    NewsListFragment.addOnBackPressCallback(activity as GamesActivity)
                    requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }
    }
}

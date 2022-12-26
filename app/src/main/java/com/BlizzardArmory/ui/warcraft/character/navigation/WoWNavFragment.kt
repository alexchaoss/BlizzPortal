package com.BlizzardArmory.ui.warcraft.character.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowNavbarFragmentBinding
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.list.NewsListFragment
import com.BlizzardArmory.ui.warcraft.account.AccountFragment
import com.BlizzardArmory.ui.warcraft.favorites.WoWFavoritesFragment
import com.BlizzardArmory.ui.warcraft.guild.activity.ActivityFragment
import com.BlizzardArmory.util.events.NetworkEvent
import com.BlizzardArmory.util.state.FragmentTag
import com.discord.panels.PanelsChildGestureRegionObserver
import com.google.android.material.tabs.TabLayoutMediator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class WoWNavFragment : Fragment() {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null

    private var _binding: WowNavbarFragmentBinding? = null
    private val binding get() = _binding!!

    private val tabsText = listOf(R.string.character, /*R.string.talents,*/ R.string.reputation, R.string.progress2, R.string.pvp, R.string.achievements)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getString(CHARACTER)
            realm = it.getString(REALM)
            media = it.getString(MEDIA)
            region = it.getString(REGION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowNavbarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        bundle.putString("character", character)
        bundle.putString("realm", realm)
        bundle.putString("media", media)
        bundle.putString("region", region)

        binding.wowPager.offscreenPageLimit = 5
        binding.wowPager.adapter = NavAdapter(childFragmentManager, this.lifecycle, binding.navBar.tabCount, bundle)
        TabLayoutMediator(binding.navBar, binding.wowPager) { tab, position ->
            tab.text = resources.getString(tabsText[position])
            binding.wowPager.setCurrentItem(tab.position, true)
        }.attach()
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
                activity?.supportFragmentManager?.findFragmentByTag(FragmentTag.WOWGUILDNAVFRAGMENT.name) != null -> {
                    ActivityFragment.addOnBackPressCallback(activity as NavigationActivity)
                }
                activity?.supportFragmentManager?.findFragmentByTag(FragmentTag.WOWFRAGMENT.name) != null -> {
                    AccountFragment.addOnBackPressCallback(activity as NavigationActivity)
                    activity?.supportFragmentManager?.popBackStack()
                }
                activity?.supportFragmentManager?.findFragmentByTag(FragmentTag.WOWFAVORITES.name) != null -> {
                    WoWFavoritesFragment.addOnBackPressCallback(activity as NavigationActivity)
                    activity?.supportFragmentManager?.popBackStack()
                }
                activity?.supportFragmentManager?.findFragmentByTag(FragmentTag.WOWMPLUSLEADERBOARD.name) != null -> {
                    ActivityFragment.addOnBackPressCallback(activity as NavigationActivity)
                    activity?.supportFragmentManager?.popBackStack()
                }
                activity?.supportFragmentManager?.findFragmentByTag(FragmentTag.WOWPVPLEADERBOARD.name) != null -> {
                    ActivityFragment.addOnBackPressCallback(activity as NavigationActivity)
                    activity?.supportFragmentManager?.popBackStack()
                }
                else -> {
                    NewsListFragment.addOnBackPressCallback(activity as NavigationActivity)
                    activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, media: String?, region: String) =
            WoWNavFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER, character)
                    putString(REALM, realm)
                    putString(MEDIA, media)
                    putString(REGION, region)
                }
            }
    }
}

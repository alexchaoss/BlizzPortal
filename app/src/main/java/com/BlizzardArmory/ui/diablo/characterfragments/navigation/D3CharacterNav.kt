package com.BlizzardArmory.ui.diablo.characterfragments.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.D3NavbarFragmentBinding
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.BlizzardArmory.util.events.NetworkEvent
import com.BlizzardArmory.util.OnFragmentResume
import com.BlizzardArmory.util.state.FragmentTag
import com.discord.panels.PanelsChildGestureRegionObserver
import com.google.android.material.tabs.TabLayoutMediator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val BTAG = "battletag"
private const val ID = "id"
private const val REGION = "region"
private const val GENDER = "gender"
private const val CHAR_CLASS = "class"


class D3CharacterNav : Fragment() {

    private var btag: String? = null
    private var id: Long? = null
    private var region: String? = null
    private var gender: Int = 0
    private var charClass: String? = null

    private var _binding: D3NavbarFragmentBinding? = null
    private val binding get() = _binding!!

    private val tabsText = listOf(R.string.stats, R.string.gear, R.string.skills, R.string.cube)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            btag = it.getString(BTAG)
            id = it.getLong(ID)
            region = it.getString(REGION)
            charClass = it.getString(CHAR_CLASS)
            gender = it.getInt(GENDER)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PanelsChildGestureRegionObserver.Provider.get().unregister(binding.wowPager)
        PanelsChildGestureRegionObserver.Provider.get().unregister(binding.navBar)
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = D3NavbarFragmentBinding.inflate(layoutInflater)
        PanelsChildGestureRegionObserver.Provider.get().unregister(binding.wowPager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wowPager.offscreenPageLimit = 3
        val bundle = Bundle()
        bundle.putString(BTAG, btag)
        bundle.putLong(ID, id!!)
        bundle.putString(REGION, region)
        bundle.putInt(GENDER, gender)
        bundle.putString(CHAR_CLASS, charClass)
        binding.wowPager.apply {
            adapter = MyAdapter(childFragmentManager, this@D3CharacterNav.lifecycle, binding.navBar.tabCount, bundle)
            PanelsChildGestureRegionObserver.Provider.get().register(this)
        }
        TabLayoutMediator(binding.navBar, binding.wowPager) { tab, position ->
            tab.text = resources.getString(tabsText[position])
            binding.wowPager.currentItem = tab.position
        }.attach()

        binding.navBar.apply {
            PanelsChildGestureRegionObserver.Provider.get().register(this)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(btag: String, id: Long, region: String, gender: Int, charClass: String) =
            D3CharacterNav().apply {
                arguments = Bundle().apply {
                    putString(BTAG, btag)
                    putLong(ID, id)
                    putString(REGION, region)
                    putInt(GENDER, gender)
                    putString(CHAR_CLASS, charClass)
                }
            }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            parentFragmentManager.popBackStack()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {

    }

}

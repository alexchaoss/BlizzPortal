package com.BlizzardArmory.ui.diablo.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.BlizzardArmory.databinding.D3NavbarFragmentBinding
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.BlizzardArmory.util.events.NetworkEvent
import com.google.android.material.tabs.TabLayout
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val BTAG = "battletag"
private const val ID = "id"
private const val REGION = "region"


class D3CharacterNav : Fragment() {

    private var btag: String? = null
    private var id: Long? = null
    private var region: String? = null

    private var _binding: D3NavbarFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            btag = it.getString(BTAG)
            id = it.getLong(ID)
            region = it.getString(REGION)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = D3NavbarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wowPager.offscreenPageLimit = 3
        val bundle = Bundle()
        bundle.putString("battletag", btag)
        bundle.putLong("id", id!!)
        bundle.putString("region", region)
        val adapter = MyAdapter(childFragmentManager, binding.navBar.tabCount, bundle)
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

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(btag: String, id: Long, region: String) =
                D3CharacterNav().apply {
                    arguments = Bundle().apply {
                        putString(BTAG, btag)
                        putLong(ID, id)
                        putString(REGION, region)
                    }
                }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun networkEventReceived(networkEvent: NetworkEvent) {
        if (networkEvent.data) {
            parentFragmentManager.popBackStack()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
    }

}

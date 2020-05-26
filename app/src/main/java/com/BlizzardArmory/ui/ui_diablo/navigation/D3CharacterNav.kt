package com.BlizzardArmory.ui.ui_diablo.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.BlizzardArmory.R
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

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            btag = it.getString(BTAG)
            id = it.getLong(ID)
            region = it.getString(REGION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.d3_navbar_fragment, container, false)

        tabLayout = view.findViewById(R.id.nav_bar)
        viewPager = view.findViewById(R.id.wow_pager)
        viewPager?.offscreenPageLimit = 3

        val bundle = Bundle()
        bundle.putString("battletag", btag)
        bundle.putLong("id", id!!)
        bundle.putString("region", region)
        val adapter = MyAdapter(childFragmentManager, tabLayout!!.tabCount, bundle)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        return view
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
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}

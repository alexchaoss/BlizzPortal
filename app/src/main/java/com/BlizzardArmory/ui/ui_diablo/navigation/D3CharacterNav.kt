package com.BlizzardArmory.ui.ui_diablo.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.ui.IOnBackPressed
import com.google.android.material.tabs.TabLayout


private const val BTAG = "battletag"
private const val ID = "id"
private const val REGION = "region"


class WoWNavFragment : Fragment(), IOnBackPressed {

    private var btag: String? = null
    private var id: String? = null
    private var region: String? = null

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            btag = it.getString(BTAG)
            id = it.getString(ID)
            region = it.getString(REGION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.wow_navbar_fragment, container, false)

        tabLayout = view.findViewById(R.id.nav_bar)
        viewPager = view.findViewById(R.id.wow_pager)
        viewPager?.offscreenPageLimit = 3

        val bundle = Bundle()
        bundle.putString("btag", btag)
        bundle.putString("id", id)
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

    companion object {
        @JvmStatic
        fun newInstance(btag: String, id: String, region: String) =
                WoWNavFragment().apply {
                    arguments = Bundle().apply {
                        putString(BTAG, btag)
                        putString(ID, id)
                        putString(REGION, region)
                    }
                }
    }

    override fun onBackPressed(): Boolean {
        return URLConstants.loading
    }
}

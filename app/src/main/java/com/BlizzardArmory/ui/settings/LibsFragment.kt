package com.BlizzardArmory.ui.settings

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.mikepenz.aboutlibraries.ui.LibsSupportFragment

class LibsFragment : LibsSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val background = activity?.findViewById<RecyclerView>(com.mikepenz.aboutlibraries.R.id.cardListView)

        background?.setBackgroundColor(Color.parseColor("#272931"))

        activity?.onBackPressedDispatcher?.addCallback {
            SettingsFragment.addBackPressedCallBack(requireActivity() as NavigationActivity)
            activity?.supportFragmentManager?.popBackStack()
        }
    }


}
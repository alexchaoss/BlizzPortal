package com.BlizzardArmory.ui.starcraft.leaderboard

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.Sc2LeaderboardsFragmentBinding
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt

class SC2LeaderboardFragment : Fragment(), SearchView.OnQueryTextListener {

    private var leaderboardList = arrayListOf("Category", "Barbarian", "Crusader", "Demon Hunter", "Monk", "Necromancer", "Witch Doctor", "Wizard", "2 Player", "3 Player", "4 Player")

    private var region: String = "US"
    private var updatedSpinners = false

    private var _binding: Sc2LeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SC2LeaderboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = Sc2LeaderboardsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        GamesActivity.binding.rightPanelGames.root.visibility = View.VISIBLE
        GamesActivity.binding.rightPanelD3.root.visibility = View.GONE
        GamesActivity.binding.rightPanelSc2.root.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GamesActivity.binding.rightPanelGames.root.visibility = View.GONE
        GamesActivity.binding.rightPanelD3.root.visibility = View.GONE
        GamesActivity.binding.rightPanelSc2.root.visibility = View.VISIBLE
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        if (!prefs?.contains("leaderboard_pulled")!!) {
            val dialog = DialogPrompt(requireContext())
            dialog.addTitle("New Feature!", 20F)
                    .addMessage("Welcome to the Starcraft 2 Leaderboards!\nPull from the right to open the Leaderboard menu!", 18F)
                    .addButton("Close", 16F, { dialog.dismiss() }).show()
            prefs.edit()?.putString("leaderboard_pulled", "done")?.apply()
        }

        GamesActivity.binding.rightPanelD3.softcore.setOnClickListener {
            GamesActivity.binding.rightPanelD3.softcore.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            GamesActivity.binding.rightPanelD3.hardcore.setBackgroundResource(R.drawable.d3_leaderboards_button)
        }

        GamesActivity.binding.rightPanelD3.hardcore.setOnClickListener {
            GamesActivity.binding.rightPanelD3.hardcore.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            GamesActivity.binding.rightPanelD3.softcore.setBackgroundResource(R.drawable.d3_leaderboards_button)
        }

        setAdapter(leaderboardList, GamesActivity.binding.rightPanelD3.leaderboard)
        setAdapter(resources.getStringArray(R.array.regions).asList(), GamesActivity.binding.rightPanelD3.region)

        setSearchButton()

        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))


        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        setObservers()
    }

    private fun setObservers() {
        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            binding.loadingCircle.visibility = View.GONE
        })
    }

    private fun setSearchButton() {
        GamesActivity.binding.rightPanelD3.search.setOnClickListener {

        }
    }

    private fun setAdapter(spinnerList: List<String>, spinner: Spinner) {
        val arrayAdapter: ArrayAdapter<*> = object : ArrayAdapter<String?>(requireContext(), android.R.layout.simple_dropdown_item_1line, spinnerList) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.textSize = 20f
                tv.gravity = Gravity.CENTER
                tv.setBackgroundColor(Color.parseColor("#000000"))
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.WHITE)
                }
                return view
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                try {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 20f
                    view.gravity = Gravity.CENTER
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        try {
            (binding.leaderboardRecycler.adapter as LeaderboardAdapter).filter(newText!!)
        } catch (e: Exception) {
            Log.e("Error", "Couldn't filter leaderboards")
        }
        return false
    }


    companion object {
        fun addOnBackPressCallback(activity: GamesActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (!URLConstants.loading) {
                    NewsPageFragment.addOnBackPressCallback(activity)
                    activity.supportFragmentManager.popBackStack()
                }
            }
        }
    }
}
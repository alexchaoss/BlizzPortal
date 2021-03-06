package com.BlizzardArmory.ui.warcraft.pvpleaderboards

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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowPvpLeaderboardsFragmentBinding
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.util.state.RightPanelState
import com.google.android.material.snackbar.Snackbar

class PvpLeaderboardsFragment : Fragment(), SearchView.OnQueryTextListener,
    FragmentManager.OnBackStackChangedListener {

    private var _binding: WowPvpLeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PvpLeaderboardsViewModel by viewModels()

    private lateinit var navigationActivity: NavigationActivity

    private var region = "US"
    private var selectedBracket = ""
    private var selectedSeason = 0

    private var hordeToggle = true
    private var allianceToggle = true

    private val bracketList = mutableListOf("Bracket", "2v2", "3v3", "RBG")
    private val seasonList = mutableListOf("Season")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowPvpLeaderboardsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()

        navigationActivity = (requireActivity() as NavigationActivity)
        navigationActivity.selectRightPanel(RightPanelState.WoWPvPLeaderboard)
        this.parentFragmentManager.addOnBackStackChangedListener(this)

        setAdapter(resources.getStringArray(R.array.regions)
            .asList(), navigationActivity.binding.rightPanelWowPvp.region)

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))

        setAdapter(bracketList, navigationActivity.binding.rightPanelWowPvp.bracket)

        setFactionToggles()

        NetworkUtils.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        setSearch()
        viewModel.downloadSeasonIndex()
    }

    private fun setFactionToggles() {
        navigationActivity.binding.rightPanelWowPvp.horde.setOnClickListener {
            if (hordeToggle) {
                navigationActivity.binding.rightPanelWowPvp.horde.setBackgroundResource(R.drawable.leaderboards_button)
            } else {
                navigationActivity.binding.rightPanelWowPvp.horde.setBackgroundResource(R.drawable.wow_mplus_leaderboards_button_selected)
            }
            hordeToggle = !hordeToggle
        }
        navigationActivity.binding.rightPanelWowPvp.alliance.setOnClickListener {
            if (allianceToggle) {
                navigationActivity.binding.rightPanelWowPvp.alliance.setBackgroundResource(R.drawable.leaderboards_button)
            } else {
                navigationActivity.binding.rightPanelWowPvp.alliance.setBackgroundResource(R.drawable.wow_mplus_leaderboards_button_selected)
            }
            allianceToggle = !allianceToggle
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        navigationActivity.selectRightPanel(RightPanelState.NewsSelection)
        this.parentFragmentManager.removeOnBackStackChangedListener(this)
    }


    private fun setObservers() {

        viewModel.getSeasonIndex().observe(viewLifecycleOwner, {
            seasonList.addAll(it.seasons.map { season -> season.id.toString() })
            selectedSeason = seasonList.last().toInt()
            setAdapter(seasonList, navigationActivity.binding.rightPanelWowPvp.season)
            viewModel.downloadLeaderboard(it.current_season.id, "3v3", region.lowercase())
        })

        viewModel.getPvpLeaderboard().observe(viewLifecycleOwner, {
            updateRecyclerViewFactionSpecific()
            binding.loadingCircle.visibility = View.GONE
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            binding.loadingCircle.visibility = View.GONE
        })
    }

    private fun setSearch() {
        navigationActivity.binding.rightPanelWowPvp.search.setOnClickListener {
            when {
                navigationActivity.binding.rightPanelWowPvp.season.selectedItemPosition == 0 -> {
                    Snackbar.make(binding.root, "Please select a season", Snackbar.LENGTH_SHORT)
                        .show()
                }
                navigationActivity.binding.rightPanelWowPvp.bracket.selectedItemPosition == 0 -> {
                    Snackbar.make(binding.root, "Please select a bracket", Snackbar.LENGTH_SHORT)
                        .show()
                }

                navigationActivity.binding.rightPanelWowPvp.region.selectedItemPosition == 0 -> {
                    Snackbar.make(binding.root, "Please select a region", Snackbar.LENGTH_SHORT)
                        .show()
                }

                !hordeToggle && !allianceToggle -> {
                    Snackbar.make(binding.root, "Please select a faction", Snackbar.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    if (navigationActivity.binding.rightPanelWowPvp.season.selectedItem.toString()
                            .toInt() == selectedSeason &&
                        navigationActivity.binding.rightPanelWowPvp.bracket.selectedItem.toString() == selectedBracket &&
                        navigationActivity.binding.rightPanelWowPvp.region.selectedItem.toString() == region) {
                        updateRecyclerViewFactionSpecific()
                    } else {
                        selectedSeason = navigationActivity.binding.rightPanelWowPvp.season.selectedItem.toString()
                            .toInt()
                        selectedBracket = navigationActivity.binding.rightPanelWowPvp.bracket.selectedItem.toString()
                        region = navigationActivity.binding.rightPanelWowPvp.region.selectedItem.toString()

                        binding.loadingCircle.visibility = View.VISIBLE
                        navigationActivity.binding.overlappingPanel.closePanels()
                        viewModel.downloadLeaderboard(selectedSeason, selectedBracket.lowercase(), region.lowercase())
                    }
                }
            }
        }
    }

    private fun updateRecyclerViewFactionSpecific() {
        if (allianceToggle && !hordeToggle) {
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(viewModel.getPvpLeaderboard().value!!.entries.filter { it.faction.type.lowercase() == "alliance" }, context, region)
                adapter?.notifyDataSetChanged()
            }
        } else if (!allianceToggle && hordeToggle) {
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(viewModel.getPvpLeaderboard().value!!.entries.filter { it.faction.type.lowercase() == "horde" }, context, region)
                adapter?.notifyDataSetChanged()
            }
        } else {
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(viewModel.getPvpLeaderboard().value!!.entries, context, region)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun setAdapter(spinnerList: List<String>, spinner: Spinner) {
        val arrayAdapter: ArrayAdapter<*> = object :
            ArrayAdapter<String?>(requireContext(), android.R.layout.simple_dropdown_item_1line, spinnerList) {
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
            Log.e("Error", e.localizedMessage!!)
        }
        return false
    }

    override fun onBackStackChanged() {
        if (requireActivity().supportFragmentManager.fragments.last().tag == this.tag) {
            navigationActivity.selectRightPanel(RightPanelState.WoWPvPLeaderboard)
        } else {
            navigationActivity.selectRightPanel(RightPanelState.NewsSelection)
        }
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                if (!NetworkUtils.loading) {
                    NewsPageFragment.addOnBackPressCallback(activity)
                    activity.supportFragmentManager.popBackStack()
                }
            }
        }
    }

}
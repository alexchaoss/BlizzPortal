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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.Sc2LeaderboardsFragmentBinding
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.state.RightPanelState
import com.google.android.material.snackbar.Snackbar

class SC2LeaderboardFragment : Fragment(), SearchView.OnQueryTextListener,
    FragmentManager.OnBackStackChangedListener {

    private var seasonList = arrayListOf<String>()
    private var leagueList = arrayListOf("Select League", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Grandmaster")

    private var regionId: Int = 1
    private var region: String = NetworkUtils.region

    private var v1Toggle = true
    private var v2Toggle = false
    private var v3Toggle = false
    private var v4Toggle = false

    private var randomToggle = false

    private var currentTier = 0
    private var currentDivision = 0

    private var currentPlayerCountRank = 1

    private var firstPage = true
    private var backPage = false
    private var prevCount = 0

    private var _binding: Sc2LeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SC2LeaderboardViewModel by activityViewModels()

    private lateinit var rightPanel: NavigationActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = Sc2LeaderboardsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        rightPanel.selectRightPanel(RightPanelState.NewsSelection)
        requireActivity().viewModelStore.clear()
        this.parentFragmentManager.removeOnBackStackChangedListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rightPanel = (requireActivity() as NavigationActivity)
        rightPanel.selectRightPanel(RightPanelState.Sc2Leaderboard)
        this.parentFragmentManager.addOnBackStackChangedListener(this)
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        if (!prefs?.contains("leaderboard_pulled_sc2")!!) {
            val dialog = DialogPrompt(requireContext())
            dialog.addTitle("New Feature!", 20F)
                .addMessage("Welcome to the Starcraft 2 Leaderboards!\nPull from the right to open the Leaderboard menu!", 18F)
                .addButtons(dialog.Button("Close", 16F, { dialog.dismiss() })).show()
            prefs.edit()?.putString("leaderboard_pulled_sc2", "done")?.apply()
        }

        binding.prevPage.visibility = View.INVISIBLE

        setPageNavButtons()

        setAdapter(leagueList, rightPanel.binding.rightPanelSc2.league)
        setAdapter(resources.getStringArray(R.array.regions)
            .asList(), rightPanel.binding.rightPanelSc2.region)
        setQueueIdButtons()
        setTeamTypeButtons()
        setSearchButton()

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))


        NetworkUtils.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        setObservers()
        viewModel.downloadCurrentSeason(1, region)
    }

    private fun setPageNavButtons() {
        binding.prevPage.setOnClickListener {
            if (currentTier >= 0) {
                binding.nextPage.visibility = View.VISIBLE
                backPage = true
                if (currentTier - 1 == 0) {
                    binding.prevPage.visibility = View.INVISIBLE
                }
                if (currentDivision > 0) {
                    currentDivision--
                    downloadLeaderboard()
                } else if (currentTier != 0) {
                    currentTier--
                    currentDivision = 0
                    downloadLeaderboard()
                }
            }
        }

        binding.nextPage.setOnClickListener {
            if (currentTier < viewModel.getLeague().value!!.tier.size - 1) {
                binding.prevPage.visibility = View.VISIBLE
                firstPage = false
                backPage = false
                if (currentTier + 1 == viewModel.getLeague().value!!.tier.size - 1) {
                    binding.nextPage.visibility = View.INVISIBLE
                }
                if (currentDivision < viewModel.getLeague().value!!.tier[currentTier].division.size - 1) {
                    currentDivision++
                    downloadLeaderboard()
                } else {
                    currentTier++
                    currentDivision = 0
                    downloadLeaderboard()
                }
            }
        }
    }

    private fun downloadLeaderboard() {
        binding.loadingCircle.visibility = View.VISIBLE
        binding.leaderboardRecycler.apply {
            adapter = LeaderboardAdapter(listOf(), requireContext(), currentPlayerCountRank)
        }
        viewModel.downloadLeaderboard(regionId,
            viewModel.getLeague().value!!.tier[currentTier].division[currentDivision].ladder_id, region)
    }

    private fun setTeamTypeButtons() {
        rightPanel.binding.rightPanelSc2.arranged.setOnClickListener {
            rightPanel.binding.rightPanelSc2.arranged.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            rightPanel.binding.rightPanelSc2.random.setBackgroundResource(R.drawable.leaderboards_button)
            randomToggle = false
        }

        rightPanel.binding.rightPanelSc2.random.setOnClickListener {
            rightPanel.binding.rightPanelSc2.random.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            rightPanel.binding.rightPanelSc2.arranged.setBackgroundResource(R.drawable.leaderboards_button)
            randomToggle = true
        }
    }

    private fun setQueueIdButtons() {
        rightPanel.binding.rightPanelSc2.v1.setOnClickListener {
            rightPanel.binding.rightPanelSc2.v1.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            rightPanel.binding.rightPanelSc2.v2.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v3.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v4.setBackgroundResource(R.drawable.leaderboards_button)
            v1Toggle = true
            v2Toggle = false
            v3Toggle = false
            v4Toggle = false
            viewModel.queueId = rightPanel.binding.rightPanelSc2.v1.text.toString()
        }

        rightPanel.binding.rightPanelSc2.v2.setOnClickListener {
            rightPanel.binding.rightPanelSc2.v1.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v2.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            rightPanel.binding.rightPanelSc2.v3.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v4.setBackgroundResource(R.drawable.leaderboards_button)
            v1Toggle = false
            v2Toggle = true
            v3Toggle = false
            v4Toggle = false
            viewModel.queueId = rightPanel.binding.rightPanelSc2.v2.text.toString()
        }

        rightPanel.binding.rightPanelSc2.v3.setOnClickListener {
            rightPanel.binding.rightPanelSc2.v1.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v2.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v3.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            rightPanel.binding.rightPanelSc2.v4.setBackgroundResource(R.drawable.leaderboards_button)
            v1Toggle = false
            v2Toggle = false
            v3Toggle = true
            v4Toggle = false
            viewModel.queueId = rightPanel.binding.rightPanelSc2.v3.text.toString()
        }

        rightPanel.binding.rightPanelSc2.v4.setOnClickListener {
            rightPanel.binding.rightPanelSc2.v1.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v2.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v3.setBackgroundResource(R.drawable.leaderboards_button)
            rightPanel.binding.rightPanelSc2.v4.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            v1Toggle = false
            v2Toggle = false
            v3Toggle = false
            v4Toggle = true
            viewModel.queueId = rightPanel.binding.rightPanelSc2.v4.text.toString()
        }
    }

    private fun setObservers() {
        viewModel.getCurrentSeason().observe(viewLifecycleOwner, {
            seasonList.add("Select Season")
            for (i in 28..it.seasonId) {
                seasonList.add(i.toString())
            }
            setAdapter(seasonList, rightPanel.binding.rightPanelSc2.season)
            viewModel.queueId = "1v1"
            viewModel.leagueString = leagueList.last()
            viewModel.seasonId = it.seasonId
            viewModel.downloadLeague(region)
        })
        viewModel.getLeague().observe(viewLifecycleOwner, {
            if (it.tier.size == 1 && !it.tier[0].division.isNullOrEmpty() && it.tier[0].division.size == 1) {
                binding.nextPage.visibility = View.INVISIBLE
            }
            prevCount = 0
            currentPlayerCountRank = 1
            backPage = false
            viewModel.downloadLeaderboard(regionId, it.tier[currentTier].division[currentDivision].ladder_id, region)
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            binding.loadingCircle.visibility = View.GONE
            val dialog = DialogPrompt(requireActivity())
            dialog.addTitle(requireActivity().resources.getString(R.string.error), 20f, "title")
                .addMessage(requireActivity().resources.getString(R.string.unexpected), 18f, "message")
                .addButtons(
                    dialog.Button(requireActivity().resources.getString(R.string.retry), 18f, {
                        dialog.dismiss()
                        viewModel.downloadCurrentSeason(1, region)
                        binding.loadingCircle.visibility = View.VISIBLE
                        NetworkUtils.loading = true
                    }, "retry"), dialog.Button(
                        requireActivity().resources.getString(R.string.back), 18f,
                        {
                            dialog.dismiss()
                            parentFragmentManager.popBackStack()
                            NewsPageFragment.addOnBackPressCallback(activity as NavigationActivity)
                        }, "back"
                    )
                ).show()
        })

        viewModel.getLeaderboard().observe(viewLifecycleOwner, {
            if (!firstPage) {
                if (backPage) {
                    currentPlayerCountRank -= it.size
                } else {
                    currentPlayerCountRank += prevCount
                }

            }
            prevCount = it.size
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(it, context, currentPlayerCountRank)
                binding.loadingCircle.visibility = View.GONE
            }
        })
    }

    private fun setSearchButton() {
        rightPanel.binding.rightPanelSc2.search.setOnClickListener {
            if (rightPanel.binding.rightPanelSc2.season.selectedItemPosition == 0) {
                Snackbar.make(binding.root, "Please select a season", Snackbar.LENGTH_SHORT).show()
            } else if (rightPanel.binding.rightPanelSc2.region.selectedItemPosition == 0) {
                Snackbar.make(binding.root, "Please select a region", Snackbar.LENGTH_SHORT).show()
            } else if (rightPanel.binding.rightPanelSc2.league.selectedItemPosition == 0) {
                Snackbar.make(binding.root, "Please select a league", Snackbar.LENGTH_SHORT).show()
            } else if (rightPanel.binding.rightPanelSc2.league.selectedItem == "Grandmaster" && !v1Toggle) {
                Snackbar.make(binding.root, "Grandmaster league can only be 1v1", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.seasonId = rightPanel.binding.rightPanelSc2.season.selectedItem.toString()
                    .toInt()
                viewModel.leagueString = rightPanel.binding.rightPanelSc2.league.selectedItem.toString()
                when (rightPanel.binding.rightPanelSc2.region.selectedItem) {
                    "US" -> regionId = 1
                    "EU" -> regionId = 2
                    "KR",
                    "TW" -> regionId = 3
                }
                region = rightPanel.binding.rightPanelSc2.region.selectedItem.toString()
                if (randomToggle) {
                    viewModel.teamType = 1
                } else {
                    viewModel.teamType = 0
                }
                currentDivision = 0
                currentTier = 0
                NetworkUtils.loading = true
                binding.nextPage.visibility = View.VISIBLE
                binding.loadingCircle.visibility = View.VISIBLE
                binding.leaderboardRecycler.apply {
                    adapter = LeaderboardAdapter(listOf(), requireContext(), currentPlayerCountRank)
                }
                viewModel.downloadLeague(region)
                rightPanel.binding.overlappingPanel.closePanels()
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
            Log.e("Error", "Couldn't filter leaderboards", e)
        }
        return false
    }

    override fun onBackStackChanged() {
        if (requireActivity().supportFragmentManager.fragments.last().tag == this.tag) {
            rightPanel.selectRightPanel(RightPanelState.Sc2Leaderboard)
        } else {
            rightPanel.selectRightPanel(RightPanelState.NewsSelection)
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
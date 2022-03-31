package com.BlizzardArmory.ui.warcraft.mythicplusleaderboards

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
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowMythicPlusLeaderboardsFragmentBinding
import com.BlizzardArmory.model.warcraft.mythicplusleaderboards.leaderboards.leaderboard.LeadingGroups
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.state.RightPanelState
import com.google.android.material.snackbar.Snackbar

class MPlusLeaderboardsFragment : Fragment(), SearchView.OnQueryTextListener,
    FragmentManager.OnBackStackChangedListener {

    private var _binding: WowMythicPlusLeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MPlusLeaderboardsViewModel by activityViewModels()

    private lateinit var navigationActivity: NavigationActivity

    private var selectedConnectedRealm = 0
    private var region = "US"
    private var selectedDungeon = 0L
    private var selectedSeason = 0

    private var dungeonList = mutableListOf("Dungeon")
    private val seasonList = mutableListOf("Season")

    private var dialog: DialogPrompt? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowMythicPlusLeaderboardsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()

        navigationActivity = (requireActivity() as NavigationActivity)
        navigationActivity.selectRightPanel(RightPanelState.WoWMPlusLeaderboard)
        this.parentFragmentManager.addOnBackStackChangedListener(this)

        navigationActivity.binding.rightPanelWowMplus.realm.setTextColor(Color.WHITE)
        navigationActivity.binding.rightPanelWowMplus.realm.textSize = 20f
        navigationActivity.binding.rightPanelWowMplus.realm.textAlignment = View.TEXT_ALIGNMENT_CENTER
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            navigationActivity.binding.rightPanelWowMplus.realm.textCursorDrawable = null
        }

        navigationActivity.binding.rightPanelWowMplus.realm.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line,
            NavigationActivity.realms.flatMap { it.value.results }
                .flatMap { it.connectedRealm.realms }.flatMap { it.name.getAllNames() }.distinct())
        )

        selectedConnectedRealm = NavigationActivity.realms["US"]!!.results.first().connectedRealm.id

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))

        setAdapter(dungeonList, navigationActivity.binding.rightPanelWowMplus.dungeon)

        NetworkUtils.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        viewModel.downloadSpecializations()
        setSearch()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        navigationActivity.selectRightPanel(RightPanelState.NewsSelection)
        requireActivity().viewModelStore.clear()
        this.parentFragmentManager.removeOnBackStackChangedListener(this)
    }


    private fun setObservers() {

        viewModel.getSpecializations().observe(viewLifecycleOwner, {
            viewModel.downloadInstances()
        })

        viewModel.getExpansions().observe(viewLifecycleOwner, {
            viewModel.downloadSeasonIndex()
            selectedDungeon = it.last().dungeons.first().challenge_mode_id
        })

        viewModel.getSeasonIndex().observe(viewLifecycleOwner, {
            seasonList.addAll(it.seasons.map { season -> season.id.toString() })
            setAdapter(seasonList, navigationActivity.binding.rightPanelWowMplus.season)
            selectedSeason = it.current_season.id
            viewModel.downloadSeason(viewModel.getSeasonIndex().value?.current_season?.id!!)
        })

        viewModel.getSeason().observe(viewLifecycleOwner, {
            viewModel.downloadMythicKeystoneLeaderboard(selectedConnectedRealm, selectedDungeon, it.periods, region)
        })

        viewModel.getMythicKeystoneLeaderboard().observe(viewLifecycleOwner, { it ->
            val leaderboards = it.filter { leaderboard -> leaderboard.leading_groups != null }

            val groups = leaderboards.flatMap { group ->
                group.leading_groups!!
            }.sortedWith(compareBy<LeadingGroups> {
                it.keystone_levelstone_level
            }.thenByDescending {
                it.duration
            }).reversed()


            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(groups, context, viewModel.getSpecializations().value!!, it, region)
                binding.loadingCircle.visibility = View.GONE
            }
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            binding.loadingCircle.visibility = View.GONE
            dialog = DialogPrompt(requireActivity())
            dialog!!.addTitle(requireActivity().resources.getString(R.string.error), 20f, "title")
                .addMessage(requireActivity().resources.getString(R.string.unexpected), 18f, "message")
                .addButtons(
                    dialog!!.Button(requireActivity().resources.getString(R.string.retry), 18f, {
                        dialog!!.dismiss()
                        viewModel.downloadSpecializations()
                        binding.loadingCircle.visibility = View.VISIBLE
                        NetworkUtils.loading = true
                    }, "retry"), dialog!!.Button(
                        requireActivity().resources.getString(R.string.back), 18f,
                        {
                            dialog!!.dismiss()
                        }, "back"
                    )
                ).show()
        })
    }

    private fun setSearch() {
        navigationActivity.binding.rightPanelWowMplus.search.setOnClickListener {
            when {
                navigationActivity.binding.rightPanelWowMplus.season.selectedItemPosition == 0 -> {
                    Snackbar.make(binding.root, "Please select a season", Snackbar.LENGTH_SHORT)
                        .show()
                }
                navigationActivity.binding.rightPanelWowMplus.dungeon.selectedItemPosition == 0 -> {
                    Snackbar.make(binding.root, "Please select a dungeon", Snackbar.LENGTH_SHORT)
                        .show()
                }
                !NavigationActivity.realms.flatMap { it.value.results }
                    .flatMap { data -> data.connectedRealm.realms }
                    .map { it.name }.flatMap { it.getAllNames() }
                    .any { it == navigationActivity.binding.rightPanelWowMplus.realm.text.toString() } -> {
                    Snackbar.make(binding.root, "Please select an existing realm", Snackbar.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    selectedConnectedRealm = NavigationActivity.realms.flatMap {
                        it.value.results
                    }.find {
                        it.connectedRealm.realms.flatMap {
                            it.name.getAllNames()
                        }
                            .contains(navigationActivity.binding.rightPanelWowMplus.realm.text.toString())
                    }?.connectedRealm?.id!!

                    region = NavigationActivity.realms.entries.find { it.value.results.any { it.connectedRealm.id == selectedConnectedRealm } }!!.key

                    selectedSeason = navigationActivity.binding.rightPanelWowMplus.season.selectedItem.toString()
                        .toInt()

                    selectedDungeon = viewModel.getExpansions().value?.flatMap { it.dungeons }
                        ?.find { it.name.contains(navigationActivity.binding.rightPanelWowMplus.dungeon.selectedItem as String) }?.challenge_mode_id!!
                    binding.loadingCircle.visibility = View.VISIBLE
                    viewModel.downloadSeason(selectedSeason)
                    navigationActivity.binding.overlappingPanel.closePanels()
                }
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

                    if (parent.getItemAtPosition(0) == "Season") {
                        dungeonList = dungeonList.subList(0, 1)
                        when (position) {
                            1, 2, 3, 4 -> {
                                dungeonList.addAll(viewModel.getExpansions().value!![1].dungeons.map { it.name })
                                setAdapter(dungeonList, navigationActivity.binding.rightPanelWowMplus.dungeon)
                            }
                            5, 6 -> {
                                dungeonList.addAll(viewModel.getExpansions().value!![2].dungeons.map { it.name })
                                setAdapter(dungeonList, navigationActivity.binding.rightPanelWowMplus.dungeon)
                            }
                        }
                    }
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
            navigationActivity.selectRightPanel(RightPanelState.WoWMPlusLeaderboard)
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
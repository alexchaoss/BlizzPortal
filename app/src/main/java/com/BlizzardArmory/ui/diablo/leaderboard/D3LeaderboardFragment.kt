package com.BlizzardArmory.ui.diablo.leaderboard

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
import com.BlizzardArmory.databinding.D3LeaderboardsFragmentBinding
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.state.RightPanelState
import com.google.android.material.snackbar.Snackbar

class D3LeaderboardFragment : Fragment(), SearchView.OnQueryTextListener,
    FragmentManager.OnBackStackChangedListener {

    private var leaderboardList = arrayListOf(
        "Category",
        "Barbarian",
        "Crusader",
        "Demon Hunter",
        "Monk",
        "Necromancer",
        "Witch Doctor",
        "Wizard",
        "2 Player",
        "3 Player",
        "4 Player"
    )

    private var hardcoreToggle = false
    private var seasonToggle = true
    private var region: String = "US"
    private var updatedSpinners = false

    private var _binding: D3LeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: D3LeaderboardViewModel by activityViewModels()

    private lateinit var rightPanel: NavigationActivity

    private var dialog: DialogPrompt? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = D3LeaderboardsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        rightPanel.selectRightPanel(RightPanelState.NewsSelection)
        requireActivity().viewModelStore.clear()
        this.parentFragmentManager.removeOnBackStackChangedListener(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rightPanel = (requireActivity() as NavigationActivity)
        rightPanel.selectRightPanel(RightPanelState.D3Leaderboard)
        this.parentFragmentManager.addOnBackStackChangedListener(this)
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        if (!prefs?.contains("leaderboard_pulled")!!) {
            val dialog = DialogPrompt(requireContext())
            dialog.addTitle("New Feature!", 20F)
                .addMessage(
                    "Welcome to the Diablo 3 Leaderboards!\nPull from the right to open the Leaderboard menu!",
                    18F
                )
                .addButtons(dialog.Button("Close", 16F, { dialog.dismiss() })).show()
            prefs.edit()?.putString("leaderboard_pulled", "done")?.apply()
        }

        rightPanel.binding.rightPanelD3.softcore.setOnClickListener {
            rightPanel.binding.rightPanelD3.softcore.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            rightPanel.binding.rightPanelD3.hardcore.setBackgroundResource(R.drawable.leaderboards_button)
            hardcoreToggle = false
        }

        rightPanel.binding.rightPanelD3.hardcore.setOnClickListener {
            rightPanel.binding.rightPanelD3.hardcore.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            rightPanel.binding.rightPanelD3.softcore.setBackgroundResource(R.drawable.leaderboards_button)
            hardcoreToggle = true
        }

        setAdapter(leaderboardList, rightPanel.binding.rightPanelD3.leaderboard)
        setAdapter(
            resources.getStringArray(R.array.regions)
                .asList(), rightPanel.binding.rightPanelD3.region
        )

        setSearchButton()

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(com.pierfrancescosoffritti.androidyoutubeplayer.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))

        NetworkUtils.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        setObservers()
        viewModel.downloadEraIndex()
        viewModel.downloadSeasonIndex()
    }

    private fun setObservers() {
        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            binding.loadingCircle.visibility = View.GONE
            if (dialog == null) {
                dialog = DialogPrompt(requireActivity())
                dialog!!.addTitle(requireActivity().resources.getString(R.string.error), 20f, "title")
                    .addMessage(
                        requireActivity().resources.getString(R.string.unexpected),
                        18f,
                        "message"
                    )
                    .addButtons(
                        dialog!!.Button(requireActivity().resources.getString(R.string.retry), 18f, {
                            dialog!!.dismiss()
                            dialog = null
                            viewModel.downloadEraIndex()
                            viewModel.downloadSeasonIndex()
                            binding.loadingCircle.visibility = View.VISIBLE
                            NetworkUtils.loading = true
                        }, "retry"), dialog!!.Button(
                            requireActivity().resources.getString(R.string.back), 18f,
                            {
                                dialog!!.dismiss()
                                dialog = null
                            }, "back"
                        )
                    ).show()
            }
        }

        viewModel.getEraIndex().observe(viewLifecycleOwner) {
            rightPanel.binding.rightPanelD3.eraButton.setOnClickListener {
                seasonToggle = false
                setAdapter(viewModel.getEraIndexList(), rightPanel.binding.rightPanelD3.spinnerId)
                rightPanel.binding.rightPanelD3.eraButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
                rightPanel.binding.rightPanelD3.seasonButton.setBackgroundResource(R.drawable.leaderboards_button)
            }
        }

        viewModel.getSeasonIndex().observe(viewLifecycleOwner) {
            rightPanel.binding.rightPanelD3.seasonButton.setOnClickListener {
                seasonToggle = true
                setAdapter(
                    viewModel.getSeasonIndexList(),
                    rightPanel.binding.rightPanelD3.spinnerId
                )
                rightPanel.binding.rightPanelD3.seasonButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
                rightPanel.binding.rightPanelD3.eraButton.setBackgroundResource(R.drawable.leaderboards_button)
            }
        }

        viewModel.getLeaderboard().observe(viewLifecycleOwner) {
            if (!updatedSpinners) {
                updatedSpinners = true
                setAdapter(
                    viewModel.getSeasonIndexList(),
                    rightPanel.binding.rightPanelD3.spinnerId
                )
            }
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(
                    viewModel.getLeaderboard().value?.row!!,
                    region,
                    requireActivity()
                )
            }
            binding.loadingCircle.visibility = View.GONE
        }
    }

    private fun setSearchButton() {
        rightPanel.binding.rightPanelD3.search.setOnClickListener {
            val leaderboard: String
            if (seasonToggle && rightPanel.binding.rightPanelD3.spinnerId.selectedItem == "Season") {
                Snackbar.make(binding.root, "Please select a season", Snackbar.LENGTH_SHORT).show()
            } else if (!seasonToggle && rightPanel.binding.rightPanelD3.spinnerId.selectedItem == "Era") {
                Snackbar.make(binding.root, "Please select an era", Snackbar.LENGTH_SHORT).show()
            } else if (rightPanel.binding.rightPanelD3.region.selectedItem == "Region") {
                Snackbar.make(binding.root, "Please select a region", Snackbar.LENGTH_SHORT).show()
            } else if (rightPanel.binding.rightPanelD3.leaderboard.selectedItem == "Category") {
                Snackbar.make(binding.root, "Please select a category", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                leaderboard = when (rightPanel.binding.rightPanelD3.leaderboard.selectedItem) {
                    "Barbarian" -> if (hardcoreToggle) "rift-hardcore-barbarian" else "rift-barbarian"
                    "Crusader" -> if (hardcoreToggle) "rift-hardcore-crusader" else "rift-crusader"
                    "Demon Hunter" -> if (hardcoreToggle) "rift-hardcore-dh" else "rift-dh"
                    "Monk" -> if (hardcoreToggle) "rift-hardcore-monk" else "rift-monk"
                    "Necromancer" -> if (hardcoreToggle) "rift-hardcore-necromancer" else "rift-necromancer"
                    "Witch Doctor" -> if (hardcoreToggle) "rift-hardcore-wd" else "rift-wd"
                    "Wizard" -> if (hardcoreToggle) "rift-hardcore-wizard" else "rift-wizard"
                    "2 Player" -> if (hardcoreToggle) "rift-hardcore-team-2" else "rift-team-2"
                    "3 Player" -> if (hardcoreToggle) "rift-hardcore-team-3" else "rift-team-3"
                    "4 Player" -> if (hardcoreToggle) "rift-hardcore-team-4" else "rift-team-4"
                    else -> "rift-barbarian"
                }
                region = rightPanel.binding.rightPanelD3.region.selectedItem.toString()
                if (seasonToggle) {
                    viewModel.downloadSeason(
                        rightPanel.binding.rightPanelD3.spinnerId.selectedItem.toString(),
                        leaderboard,
                        region
                    )
                } else {
                    viewModel.downloadEra(
                        rightPanel.binding.rightPanelD3.spinnerId.selectedItem.toString(),
                        leaderboard,
                        region
                    )
                }
                NetworkUtils.loading = true
                binding.loadingCircle.visibility = View.VISIBLE
                binding.leaderboardRecycler.apply {
                    adapter = LeaderboardAdapter(listOf(), "US", requireContext())
                }
                rightPanel.binding.overlappingPanel.closePanels()
            }
        }
    }

    private fun setAdapter(spinnerList: List<String>, spinner: Spinner) {
        val arrayAdapter: ArrayAdapter<*> = object :
            ArrayAdapter<String?>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                spinnerList
            ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
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
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {

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

    override fun onBackStackChanged() {
        if (requireActivity().supportFragmentManager.fragments.last().tag == this.tag) {
            rightPanel.selectRightPanel(RightPanelState.D3Leaderboard)
        } else {
            rightPanel.selectRightPanel(RightPanelState.NewsSelection)
        }
    }

    companion object {
        fun addOnBackPressCallback(activity: NavigationActivity) {
            activity.onBackPressedDispatcher.addCallback {
                NewsPageFragment.addOnBackPressCallback(activity)
                activity.supportFragmentManager.popBackStack()
            }
        }
    }
}
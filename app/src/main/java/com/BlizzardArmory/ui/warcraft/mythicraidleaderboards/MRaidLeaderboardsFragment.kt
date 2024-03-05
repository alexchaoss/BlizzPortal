package com.BlizzardArmory.ui.warcraft.mythicraidleaderboards

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
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowMythicRaidLeaderboardsFragmentBinding
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.state.RightPanelState
import com.google.android.material.snackbar.Snackbar

class MRaidLeaderboardsFragment : Fragment(), SearchView.OnQueryTextListener {

    private val raidList = mutableMapOf<String, ArrayList<String>>()

    private var _binding: WowMythicRaidLeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MRaidLeaderboardsViewModel by activityViewModels()
    private var dialog: DialogPrompt? = null
    private lateinit var rightPanel: NavigationActivity
    private var alliance = true
    private var horde = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = WowMythicRaidLeaderboardsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rightPanel = (requireActivity() as NavigationActivity)
        rightPanel.selectRightPanel(RightPanelState.WoWMythicRaidLeaderboard)
        rightPanel.binding.rightPanelWowRaid.allianceButton.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
        rightPanel.binding.rightPanelWowRaid.hordeButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)

        setObservers()
        addRaidsToList()

        setAdapter(raidList.keys.toList(), rightPanel.binding.rightPanelWowRaid.spinnerXpac) { selected ->
            raidList[selected]?.let {
                setAdapter(
                    it,
                    rightPanel.binding.rightPanelWowRaid.spinnerRaid
                )
            }
        }
        setAdapter(raidList.entries.first().value, rightPanel.binding.rightPanelWowRaid.spinnerRaid)

        rightPanel.binding.rightPanelWowRaid.allianceButton.setOnClickListener {
            alliance = !alliance
            if (alliance) {
                rightPanel.binding.rightPanelWowRaid.allianceButton.setBackgroundResource(R.drawable.sc2_leaderboards_button_selected)
            } else {
                rightPanel.binding.rightPanelWowRaid.allianceButton.setBackgroundResource(R.drawable.leaderboards_button)
            }
        }

        rightPanel.binding.rightPanelWowRaid.hordeButton.setOnClickListener {
            horde = !horde
            if (horde) {
                rightPanel.binding.rightPanelWowRaid.hordeButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            } else {
                rightPanel.binding.rightPanelWowRaid.hordeButton.setBackgroundResource(R.drawable.leaderboards_button)
            }
        }

        rightPanel.binding.rightPanelWowRaid.search.setOnClickListener {
            if (!horde && !alliance) {
                Snackbar.make(binding.root, "Please select a faction", Snackbar.LENGTH_SHORT).show()
            } else {
                downloadLeaderboard()
                rightPanel.binding.overlappingPanel.closePanels()
            }
        }

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(com.pierfrancescosoffritti.androidyoutubeplayer.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))

        NetworkUtils.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        raidList.entries.first().value[0].let {
            binding.raidName.text = it
            viewModel.downloadBothLeaderboard(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        rightPanel.selectRightPanel(RightPanelState.NewsSelection)
        requireActivity().viewModelStore.clear()
    }


    private fun setObservers() {
        viewModel.getEntries().observe(viewLifecycleOwner) {
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(it, context)
                binding.loadingCircle.visibility = View.GONE
            }
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            Log.e("Error", "Error code $it occured")
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
                            raidList.entries.first().value[0].let { raid -> viewModel.downloadBothLeaderboard(raid) }
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
    }

    private fun addRaidsToList() {
        raidList["Dragonflight"] = arrayListOf()
        raidList["Dragonflight"]?.addAll(arrayListOf("Amirdrassil, the Dream's Hope", "Aberrus, the Shadowed Crucible", "Vault of The Incarnates"))

        raidList["Shadowlands"] = arrayListOf()
        raidList["Shadowlands"]?.addAll(arrayListOf("Sepulcher of the First Ones", "Sanctum of Domination", "Castle Nathria"))

        raidList["Battle for Azeroth"] = arrayListOf()
        raidList["Battle for Azeroth"]?.addAll(arrayListOf("Ny'alotha The Waking City", "The Eternal Palace", "Crucible of Storms", "Battle of Dazaralor", "Uldir"))
    }

    private fun setAdapter(spinnerList: List<String>, spinner: Spinner, onItemSelected: (selected: String) -> Unit? = {}) {
        val arrayAdapter: ArrayAdapter<*> = object :
            ArrayAdapter<String?>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                spinnerList
            ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.textSize = 16f
                tv.gravity = Gravity.CENTER
                tv.setBackgroundColor(Color.parseColor("#000000"))
                tv.setTextColor(Color.WHITE)
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
                    onItemSelected(parent.getItemAtPosition(position).toString())
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 16f
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

    private fun downloadLeaderboard() {
        val spinner = rightPanel.binding.rightPanelWowRaid.spinnerRaid
        binding.loadingCircle.visibility = View.VISIBLE
        val raid = spinner.selectedItem.toString()
        binding.raidName.text = raid
        if (horde && alliance) {
            viewModel.downloadBothLeaderboard(raid)
        } else if (horde) {
            viewModel.downloadLeaderboard(raid, "horde")
        } else if (alliance) {
            viewModel.downloadLeaderboard(raid, "alliance")
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
}
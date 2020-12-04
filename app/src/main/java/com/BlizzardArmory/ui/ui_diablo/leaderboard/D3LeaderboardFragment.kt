package com.BlizzardArmory.ui.ui_diablo.leaderboard

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.databinding.D3LeaderboardsFragmentBinding
import com.BlizzardArmory.databinding.GamesActivityBinding
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt

class D3LeaderboardFragment : Fragment(), SearchView.OnQueryTextListener {

    private var leaderboardList = arrayListOf("Category", "Barbarian", "Crusader", "Demon Hunter", "Monk", "Necromancer", "Witch Doctor", "Wizard", "2 Player", "3 Player", "4 Player")

    private lateinit var toggle: ActionBarDrawerToggle

    private var hardcoreToggle = false
    private var seasonToggle = true
    private var region: String = "US"
    private var updatedSpinners = false

    private var _binding: D3LeaderboardsFragmentBinding? = null
    private var _barBinding: GamesActivityBinding? = null
    private val binding get() = _binding!!
    private val barBinding get() = _barBinding!!
    private val viewModel: D3LeaderboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = D3LeaderboardsFragmentBinding.inflate(layoutInflater)
        _barBinding = GamesActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)

        binding.navigation.itemIconTintList = null

        toggle = ActionBarDrawerToggle(activity, binding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        if (!prefs?.contains("leaderboard_pulled")!!) {
            val dialog = DialogPrompt(requireContext())
            dialog.addTitle("New Feature!", 20F)
                    .addMessage("Welcome to the Diablo 3 Leaderboards!\nPull from the right to open the Leaderboard menu!", 18F)
                    .addButton("Close", 16F, { dialog.cancel() }).show()
            prefs.edit()?.putString("leaderboard_pulled", "done")?.apply()
        }

        binding.softcore.setOnClickListener {
            binding.softcore.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            binding.hardcore.setBackgroundResource(R.drawable.d3_leaderboards_button)
            hardcoreToggle = false
        }

        binding.hardcore.setOnClickListener {
            binding.hardcore.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
            binding.softcore.setBackgroundResource(R.drawable.d3_leaderboards_button)
            hardcoreToggle = true
        }

        setAdapter(leaderboardList, binding.leaderboard)
        setAdapter(resources.getStringArray(R.array.regions).asList(), binding.region)

        setSearchButton()

        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))
        binding.searchView.setOnQueryTextListener(this)

        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        setObservers()
        viewModel.downloadEraIndex()
        viewModel.downloadSeasonIndex()
    }

    override fun onResume() {
        activity?.findViewById<DrawerLayout>(R.id.drawer_layout)?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        super.onResume()
    }

    override fun onPause() {
        activity?.findViewById<DrawerLayout>(R.id.drawer_layout)?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END)
        super.onPause()
    }

    private fun setObservers() {
        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            binding.loadingCircle.visibility = View.GONE
        })

        viewModel.getEraIndex().observe(viewLifecycleOwner, {
            binding.eraButton.setOnClickListener {
                seasonToggle = false
                setAdapter(viewModel.getEraIndexList(), binding.spinnerId)
                binding.eraButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
                binding.seasonButton.setBackgroundResource(R.drawable.d3_leaderboards_button)
            }
        })

        viewModel.getSeasonIndex().observe(viewLifecycleOwner, {
            binding.seasonButton.setOnClickListener {
                seasonToggle = true
                setAdapter(viewModel.getSeasonIndexList(), binding.spinnerId)
                binding.seasonButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
                binding.eraButton.setBackgroundResource(R.drawable.d3_leaderboards_button)
            }
        })

        viewModel.getLeaderboard().observe(viewLifecycleOwner, {
            if (!updatedSpinners) {
                updatedSpinners = true
                setAdapter(viewModel.getSeasonIndexList(), binding.spinnerId)
            }
            binding.leaderboardRecycler.apply {
                adapter = LeaderboardAdapter(viewModel.getLeaderboard().value?.row!!, region, requireActivity())
            }
            binding.loadingCircle.visibility = View.GONE
        })
    }

    private fun setSearchButton() {
        binding.search.setOnClickListener {
            var leaderboard = ""
            if (seasonToggle && binding.spinnerId.selectedItem == "Season") {
                Toast.makeText(activity, "Please select a season", Toast.LENGTH_SHORT).show()
            } else if (!seasonToggle && binding.spinnerId.selectedItem == "Era") {
                Toast.makeText(activity, "Please select an era", Toast.LENGTH_SHORT).show()
            } else if (binding.region.selectedItem == "Region") {
                Toast.makeText(activity, "Please select a region", Toast.LENGTH_SHORT).show()
            } else if (binding.leaderboard.selectedItem == "Category") {
                Toast.makeText(activity, "Please select a category", Toast.LENGTH_SHORT).show()
            } else {
                leaderboard = when (binding.leaderboard.selectedItem) {
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
                region = binding.region.selectedItem.toString()
                if (seasonToggle) {
                    viewModel.downloadSeason(binding.spinnerId.selectedItem.toString(), leaderboard, region)
                } else {
                    viewModel.downloadEra(binding.spinnerId.selectedItem.toString(), leaderboard, region)
                }
                URLConstants.loading = true
                binding.loadingCircle.visibility = View.VISIBLE
                binding.leaderboardRecycler.apply {
                    adapter = LeaderboardAdapter(listOf(), "US", requireContext())
                }
                binding.drawerLayout.closeDrawers()
            }
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
        (binding.leaderboardRecycler.adapter as LeaderboardAdapter).filter(newText!!)
        return false
    }


    companion object{
        fun addOnBackPressCallback(activity: GamesActivity){
            activity.onBackPressedDispatcher.addCallback {
                if(!URLConstants.loading) {
                    NewsPageFragment.addOnBackPressCallback(activity)
                    activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }
    }
}
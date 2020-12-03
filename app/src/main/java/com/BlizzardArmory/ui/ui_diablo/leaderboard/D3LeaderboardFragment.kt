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
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.databinding.D3LeaderboardsFragmentBinding
import com.BlizzardArmory.databinding.GamesActivityBinding
import com.BlizzardArmory.model.diablo.data.common.Leaderboard
import com.BlizzardArmory.model.diablo.data.eras.index.EraIndex
import com.BlizzardArmory.model.diablo.data.seasons.index.SeasonIndex
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class D3LeaderboardFragment : Fragment(), SearchView.OnQueryTextListener {

    private var eraIndex: EraIndex? = null
    private var seasonIndex: SeasonIndex? = null

    private var eraIndexList = arrayListOf<String>()
    private var seasonIndexList = arrayListOf<String>()
    
    private var leaderboardList = arrayListOf("Category", "Barbarian", "Crusader", "Demon Hunter", "Monk", "Necromancer", "Witch Doctor", "Wizard", "2 Player", "3 Player", "4 Player")
    
    private lateinit var toggle: ActionBarDrawerToggle

    private var hardcoreToggle = false
    private var seasonToggle = true
    
    private var _binding: D3LeaderboardsFragmentBinding? = null
    private var _barBinding: GamesActivityBinding? = null
    private val binding get() = _binding!!
    private val barBinding get() = _barBinding!!

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

        eraIndexList.add("Era")
        seasonIndexList.add("Season")
        setAdapter(resources.getStringArray(R.array.regions).asList(), binding.region)

        setSearchButton()

        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))
        binding.searchView.setOnQueryTextListener(this)

        downloadInfos()
    }

    override fun onResume() {
        activity?.findViewById<DrawerLayout>(R.id.drawer_layout)?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END)
        super.onResume()
    }

    override fun onPause() {
        activity?.findViewById<DrawerLayout>(R.id.drawer_layout)?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END)
        super.onPause()
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
                if (seasonToggle) {
                    downloadSeason(binding.spinnerId.selectedItem.toString(), leaderboard, binding.region.selectedItem.toString())
                } else {
                    downloadEra(binding.spinnerId.selectedItem.toString(), leaderboard, binding.region.selectedItem.toString())
                }
                binding.leaderboardRecycler.apply {
                    adapter = LeaderboardAdapter(listOf(), "US", requireContext())
                }
                binding.drawerLayout.closeDrawers()
            }
        }
    }

    private fun downloadInfos() {
        downloadEraIndex()
        downloadSeasonIndex()
    }

    private fun downloadSeasonIndex() {
        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        val seasonCall: Call<SeasonIndex> = RetroClient.getClient().getSeasonIndex(MainActivity.locale, MainActivity.selectedRegion)
        seasonCall.enqueue(object : Callback<SeasonIndex> {
            override fun onResponse(call: Call<SeasonIndex>, response: Response<SeasonIndex>) {
                if (response.isSuccessful) {
                    seasonIndex = response.body()
                    seasonIndex?.season?.forEachIndexed { index, season ->
                        seasonIndexList.add((index + 1).toString())
                    }
                    binding.seasonButton.setOnClickListener {
                        seasonToggle = true
                        setAdapter(seasonIndexList, binding.spinnerId)
                        binding.seasonButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
                        binding.eraButton.setBackgroundResource(R.drawable.d3_leaderboards_button)
                    }
                    downloadSeason(seasonIndexList.last(), "rift-barbarian", MainActivity.selectedRegion)
                    setAdapter(seasonIndexList, binding.spinnerId)
                }
            }

            override fun onFailure(call: Call<SeasonIndex>, t: Throwable) {
                Log.e("Error", "Season download", t)
                URLConstants.loading = false
                binding.loadingCircle.visibility = View.GONE
            }
        })
    }

    private fun downloadSeason(id: String, leaderboardString: String, region: String){
        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        val seasonCall: Call<Leaderboard> = RetroClient.getClient().getSeasonLeaderboard(id.toInt(), leaderboardString, MainActivity.locale, region)
        seasonCall.enqueue(object : Callback<Leaderboard> {
            override fun onResponse(call: Call<Leaderboard>, response: Response<Leaderboard>) {
                if (response.isSuccessful) {
                    val leaderboard = response.body()
                    binding.leaderboardRecycler.apply {
                        adapter = LeaderboardAdapter(leaderboard?.row!!, region, requireActivity())
                    }
                }
                URLConstants.loading = false
                binding.loadingCircle.visibility = View.GONE
            }

            override fun onFailure(call: Call<Leaderboard>, t: Throwable) {
                Log.e("Error", "Season download", t)
                URLConstants.loading = false
                binding.loadingCircle.visibility = View.GONE
            }
        })
    }

    private fun downloadEraIndex() {
        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        val eraCall: Call<EraIndex> = RetroClient.getClient().getEraIndex(MainActivity.locale, MainActivity.selectedRegion)
        eraCall.enqueue(object : Callback<EraIndex> {
            override fun onResponse(call: Call<EraIndex>, response: Response<EraIndex>) {
                if (response.isSuccessful) {
                    eraIndex = response.body()
                    eraIndex?.era?.forEachIndexed { index, era ->
                        eraIndexList.add((index + 1).toString())
                    }
                    binding.eraButton.setOnClickListener {
                        seasonToggle = false
                        setAdapter(eraIndexList, binding.spinnerId)
                        binding.eraButton.setBackgroundResource(R.drawable.d3_leaderboards_button_selected)
                        binding.seasonButton.setBackgroundResource(R.drawable.d3_leaderboards_button)
                    }
                }
            }

            override fun onFailure(call: Call<EraIndex>, t: Throwable) {
                Log.e("Error", "Era download", t)
                URLConstants.loading = false
                binding.loadingCircle.visibility = View.GONE
            }
        })
    }

    private fun downloadEra(id: String, leaderboardString: String, region: String) {
        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        val seasonCall: Call<Leaderboard> = RetroClient.getClient().getEraLeaderboard(id.toInt(), leaderboardString, MainActivity.locale, region)
        seasonCall.enqueue(object : Callback<Leaderboard> {
            override fun onResponse(call: Call<Leaderboard>, response: Response<Leaderboard>) {
                val leaderboard = response.body()
                binding.leaderboardRecycler.apply {
                    adapter = LeaderboardAdapter(leaderboard?.row!!, region, requireActivity())
                }
                URLConstants.loading = false
                binding.loadingCircle.visibility = View.GONE
            }

            override fun onFailure(call: Call<Leaderboard>, t: Throwable) {
                Log.e("Error", "Season download", t)
                URLConstants.loading = false
                binding.loadingCircle.visibility = View.GONE
            }
        })
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
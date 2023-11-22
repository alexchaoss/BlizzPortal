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

class MRaidLeaderboardsFragment : Fragment(), SearchView.OnQueryTextListener {

    private val raidList = arrayListOf<String>()
    private val factionList = arrayListOf("Faction", "Horde & Alliance", "Horde", "Alliance")

    private var _binding: WowMythicRaidLeaderboardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MRaidLeaderboardsViewModel by activityViewModels()
    private var dialog: DialogPrompt? = null

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
        setObservers()
        addRaidsToList()

        setAdapter(raidList, binding.raid)
        setAdapter(factionList, binding.faction)

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(com.pierfrancescosoffritti.androidyoutubeplayer.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))

        NetworkUtils.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        viewModel.downloadBothLeaderboard(raidList[1])

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
                            viewModel.downloadBothLeaderboard(raidList[1])
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
        raidList.add("Raid")
        raidList.add("Amirdrassil, the Dream's Hope")
        raidList.add("Aberrus, the Shadowed Crucible")
        raidList.add("Vault of The Incarnates")
        raidList.add("Sepulcher of the First Ones")
        raidList.add("Sanctum of Domination")
        raidList.add("Castle Nathria")
        raidList.add("Ny'alotha The Waking City")
        raidList.add("The Eternal Palace")
        raidList.add("Crucible of Storms")
        raidList.add("Battle of Dazaralor")
        raidList.add("Uldir")
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
                    downloadLeaderboard()
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
        if (binding.raid.selectedItemPosition != 0 && binding.faction.selectedItemPosition != 0) {
            binding.loadingCircle.visibility = View.VISIBLE
            if (binding.faction.selectedItem.toString() == factionList[1]) {
                viewModel.downloadBothLeaderboard(binding.raid.selectedItem.toString())
            } else {
                viewModel.downloadLeaderboard(
                    binding.raid.selectedItem.toString(),
                    binding.faction.selectedItem.toString()
                )
            }
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
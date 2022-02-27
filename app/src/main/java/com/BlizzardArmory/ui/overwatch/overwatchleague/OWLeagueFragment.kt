package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.OverwatchLeagueFragmentBinding
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.ui.news.page.NewsPageFragment

class OWLeagueFragment : Fragment() {

    private var _binding: OverwatchLeagueFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OWLeagueViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addOnBackPressCallback(activity as NavigationActivity)
        _binding = OverwatchLeagueFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        viewModel.parseCSV()
    }

    private fun setObservers() {
        viewModel.getMatches().observe(viewLifecycleOwner, {
            Log.i("TEST", it.toString())
            binding.leaderboardRecycler.apply {
                adapter = MatchesAdapter(it[0].matches, context)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
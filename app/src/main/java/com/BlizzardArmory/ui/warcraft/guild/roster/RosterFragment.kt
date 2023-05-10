package com.BlizzardArmory.ui.warcraft.guild.roster


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.WowGuildRosterBinding
import com.BlizzardArmory.model.warcraft.guild.roster.Members
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.RetryEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class RosterFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: WowGuildRosterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RosterViewModel by activityViewModels()

    private var realm: String? = null
    private var guildName: String? = null
    private var region: String? = null

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireActivity().viewModelStore.clear()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowGuildRosterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = requireArguments()
        realm = bundle.getString("realm")
        guildName = bundle.getString("guildName")
        region = bundle.getString("region")

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.queryHint = "Search.."
        val textView: TextView = binding.searchView.findViewById(com.pierfrancescosoffritti.androidyoutubeplayer.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))

        setObservers()
        viewModel.downloadGuildRoster(realm!!, guildName!!, region!!)
    }

    private fun setObservers() {
        viewModel.getGuildRoster().observe(viewLifecycleOwner) {
            binding.rosterRecyclerview.apply {
                adapter =
                    RosterAdapter(it.members.sortedWith(compareBy<Members> { it.rank }.thenByDescending { it.character.level }), requireContext(), region!!)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            viewModel.downloadGuildRoster(realm!!, guildName!!, region!!)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun factionEventReceived(factionEvent: FactionEvent) {
        setBackground(factionEvent.data)
        EventBus.getDefault().unregister(this)
    }

    private fun setBackground(faction: String) {
        if (faction == "ALLIANCE") {
            binding.background.setBackgroundColor(Color.parseColor("#090B13"))
        } else {
            binding.background.setBackgroundColor(Color.parseColor("#1A1511"))
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        try {
            (binding.rosterRecyclerview.adapter as RosterAdapter).filter(newText!!)
        } catch (e: Exception) {
            Log.e("Error", "Couldn't filter leaderboards")
        }
        return false
    }
}

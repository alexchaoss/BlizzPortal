package com.BlizzardArmory.ui.warcraft.character.mythicplus


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.databinding.WowCharacterMythicplusFragmentBinding
import com.BlizzardArmory.model.warcraft.mythicplusprofile.BestRuns
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"

class MythicPlusFragment : Fragment() {

    private var media: String? = null

    private var _binding: WowCharacterMythicplusFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MythicPlusViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.character = it.getString(CHARACTER)!!
            viewModel.realm = it.getString(REALM)!!
            media = it.getString(MEDIA)
            viewModel.region = it.getString(REGION)!!
        }
    }

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
        _binding = WowCharacterMythicplusFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charClass = EventBus.getDefault().getStickyEvent(ClassEvent::class.java)?.data
        WoWClassName.setBackground(binding.mplusLayout, binding.backgroundMplus, requireContext(), charClass)
        setObservers()
        viewModel.downloadSeasonIndex()
    }


    private fun setObservers() {
        viewModel.getIndex().observe(viewLifecycleOwner) { index ->
            val season = index.seasons.find { it.id == viewModel.getSeason().value?.id!! }
            Log.i("TEST", season.toString())
            season?.let {
                Log.i("TEST", "has DATA")
                viewModel.downloadMythicKeystoneSeason(season.id)
                val color = Color.argb(255, index.currentMythicRating.color.r, index.currentMythicRating.color.g, index.currentMythicRating.color.b)
                binding.mplusrating.setTextColor(color)
                binding.mplusrating.text = "Total M+ Rating: ${index.currentMythicRating.rating.toInt()}"
            }
            if (season == null) {
                Log.i("TEST", "TEST NO DATA")
                binding.outdated.text = "No data for current season"
                binding.outdated.visibility = View.VISIBLE
            }
        }

        viewModel.getMplusSeason().observe(viewLifecycleOwner) { season ->
            binding.raidRecycler.apply {
                adapter = MythicPlusAdapter(findBestRuns(season.bestRuns), context)
                adapter!!.notifyDataSetChanged()
            }
        }

        viewModel.getSeasonIndex().observe(viewLifecycleOwner) {
            viewModel.downloadSeason(viewModel.getSeasonIndex().value?.current_season?.id!!)
        }

        viewModel.getSeason().observe(viewLifecycleOwner) {
            viewModel.downloadMythicKeystoneIndex()
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
            showOutdatedTextView()
        }
    }

    fun findBestRuns(mythicRuns: List<BestRuns>): List<BestRuns> {
        val groupedRuns = mythicRuns.groupBy { it.dungeon.name }
        return groupedRuns.values.mapNotNull { dungeonRuns ->
            dungeonRuns.minWithOrNull(compareBy<BestRuns> { it.duration }.thenByDescending { it.keystoneLevel })
        }
    }

    private fun showOutdatedTextView() {
        binding.outdated.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, media: String, region: String) =
            WoWNavFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER, character)
                    putString(REALM, realm)
                    putString(MEDIA, media)
                    putString(REGION, region)
                }
            }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            viewModel.downloadMythicKeystoneIndex()
        }
    }
}

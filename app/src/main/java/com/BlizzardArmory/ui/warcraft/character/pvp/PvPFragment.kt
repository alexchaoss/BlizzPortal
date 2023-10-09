package com.BlizzardArmory.ui.warcraft.character.pvp


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowPvpFragmentBinding
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.RetryEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class PvPFragment : Fragment() {

    private var media: String? = null

    private var _binding: WowPvpFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PvPViewModel by activityViewModels()

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
        _binding = WowPvpFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charClass = EventBus.getDefault().getStickyEvent(ClassEvent::class.java)?.data
        val faction = EventBus.getDefault().getStickyEvent(FactionEvent::class.java)?.data
        if (faction == "horde") {
            binding.factionicon.setImageResource(R.drawable.horde_pvp_logo)
        } else {
            binding.factionicon.setImageResource(R.drawable.alliance_pvp_logo)
        }
        WoWClassName.setBackground(binding.layoutPvp, binding.backgroundPvp, requireContext(), charClass)
        setObservers()
        viewModel.downloadPvPSummary()
        viewModel.download2v2Info()
        viewModel.download3v3Info()
        viewModel.downloadRBGInfo()
    }

    private fun setObservers() {
        viewModel.geterrorBracket().observe(viewLifecycleOwner) {
            when (it) {
                "rbg" -> binding.layoutrbg.alpha = 0.4f
                "2v2" -> binding.layout2v2.alpha = 0.4f
                "3v3" -> binding.layout3v3.alpha = 0.4f
            }
        }

        viewModel.getTier2v2().observe(viewLifecycleOwner) {
            setTierImage(binding.tierimage2v2, it)
            showBracketInformationOnTouch(binding.layout2v2, it, viewModel.pvp2v2)
        }

        viewModel.getTier3v3().observe(viewLifecycleOwner) {
            setTierImage(binding.tierimage3v3, it)
            showBracketInformationOnTouch(binding.layout3v3, it, viewModel.pvp3v3)
        }

        viewModel.getTierRBG().observe(viewLifecycleOwner) {
            setTierImage(binding.tierimagerbg, it)
            showBracketInformationOnTouch(binding.layoutrbg, it, viewModel.pvpRBG)
        }

        viewModel.getSummary().observe(viewLifecycleOwner) {
            binding.kills.text = it.honorable_kills.toString()
            binding.level.text = "LEVEL ${it.honor_level}"
            setHonorRankIcon(it)
            if (it.pvp_map_statistics != null) {
                binding.recyclerviewbg.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = BattlegroundAdapter(it.pvp_map_statistics, context)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showBracketInformationOnTouch(layout: RelativeLayout, tier: Tier, bracket: BracketStatistics) {
        layout.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.title.text = tier.name
                    binding.rating.text = bracket.rating.toString()
                    binding.gamesplayed.text = bracket.season_matchStatistics.played.toString() + " games played"
                    binding.gameswon.text = bracket.season_matchStatistics.won.toString() + " games won"
                    binding.winrate.text = ((bracket.season_matchStatistics.won * 100) / bracket.season_matchStatistics.played).toString() + "% win rate"
                    binding.bracketinfo.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_UP -> binding.bracketinfo.visibility = View.GONE
            }
            return@setOnTouchListener true
        }
    }

    private fun setTierImage(imageView: ImageView, tier: Tier) {
        when (tier.name) {
            "Unranked" -> imageView.setImageResource(R.drawable.unranked)
            "Combatant" -> imageView.setImageResource(R.drawable.combatant)
            "Challenger" -> imageView.setImageResource(R.drawable.challenger)
            "Rival" -> imageView.setImageResource(R.drawable.rival)
            "Duelist" -> imageView.setImageResource(R.drawable.duelist)
            "Elite" -> imageView.setImageResource(R.drawable.elite)
        }
    }

    private fun setHonorRankIcon(pvpSummary: PvPSummary) {
        when (pvpSummary.honorable_kills) {
            in 0..99 -> binding.rankicon.setImageResource(R.drawable.rank1_honor)
            in 100..499 -> binding.rankicon.setImageResource(R.drawable.rank2_honor)
            in 500..999 -> binding.rankicon.setImageResource(R.drawable.rank3_honor)
            in 1000..4999 -> binding.rankicon.setImageResource(R.drawable.rank4_honor)
            in 5000..9999 -> binding.rankicon.setImageResource(R.drawable.rank5_honor)
            in 10000..24999 -> binding.rankicon.setImageResource(R.drawable.rank6_honor)
            in 25000..49999 -> binding.rankicon.setImageResource(R.drawable.rank7_honor)
            in 50000..99999 -> binding.rankicon.setImageResource(R.drawable.rank8_honor)
            in 100000..249999 -> binding.rankicon.setImageResource(R.drawable.rank9_honor)
            in 250000..Int.MAX_VALUE -> binding.rankicon.setImageResource(R.drawable.rank10_honor)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            viewModel.downloadPvPSummary()
            viewModel.download2v2Info()
            viewModel.download3v3Info()
            viewModel.downloadRBGInfo()
        }
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

}

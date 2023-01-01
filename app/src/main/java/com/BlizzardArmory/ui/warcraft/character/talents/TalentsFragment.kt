package com.BlizzardArmory.ui.warcraft.character.talents


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.databinding.WowTalentsFragmentBinding
import com.BlizzardArmory.model.warcraft.talents.playerspec.PlayerSpecializations
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.BlizzardArmory.util.events.TalentClickedEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class TalentsFragment : Fragment() {

    private var media: String? = null

    private var _binding: WowTalentsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TalentsViewModel by activityViewModels()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WowTalentsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        viewModel.downloadCharacterSpecialization()
    }

    private fun setTabClickListeners(playerSpecialization: PlayerSpecializations) {
        binding.classText.setOnClickListener {
            binding.specText.setTextColor(Color.parseColor("#555555"))
            binding.specText.setBackgroundColor(Color.parseColor("#10ffffff"))
            binding.classText.setTextColor(Color.parseColor("#ffffff"))
            binding.classText.setBackgroundColor(Color.parseColor("#000000"))
            val talents = playerSpecialization.specializations.find { spec -> spec.specialization.id == playerSpecialization.active_specialization.id }?.loadouts?.find { it.isActive }?.selectedClassTalents
            if(talents != null) {
                binding.classTalents.apply {
                    adapter = TalentAdapter(talents, requireContext())
                }
            }
        }
        binding.specText.setOnClickListener {
            binding.classText.setTextColor(Color.parseColor("#555555"))
            binding.classText.setBackgroundColor(Color.parseColor("#10ffffff"))
            binding.specText.setTextColor(Color.parseColor("#ffffff"))
            binding.specText.setBackgroundColor(Color.parseColor("#000000"))
            val talents = playerSpecialization.specializations.find { spec -> spec.specialization.id == playerSpecialization.active_specialization.id }?.loadouts?.find { it.isActive }?.selectedSpecTalents
            if(talents != null) {
                binding.classTalents.apply {
                    adapter = TalentAdapter(talents, requireContext())
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.getTalentTree().observe(viewLifecycleOwner) {

        }

        viewModel.getPlayerSpecialization().observe(viewLifecycleOwner) {
            setTabClickListeners(it)
            binding.specText.text = it.active_specialization.name
            val talents = it.specializations.find { spec -> spec.specialization.id == it.active_specialization.id }?.loadouts?.find { it.isActive }?.selectedClassTalents
            if (talents != null) {
                binding.classTalents.apply {
                    adapter = TalentAdapter(talents, requireContext())
                }
            }
        }

        viewModel.getPlayerClass().observe(viewLifecycleOwner) {
            binding.classText.text = it.lowercase(Locale.getDefault()).split("_").joinToString(separator = " ") { word -> word.replaceFirstChar(Char::titlecase) }
        }

        viewModel.getErrorCode().observe(viewLifecycleOwner) {
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun talentTouchedEvent(talentClickedEvent: TalentClickedEvent) {

        if (talentClickedEvent.touch && binding.classTalents.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            binding.spellTooltip.visibility = View.VISIBLE
            binding.spellName.text = talentClickedEvent.name
            binding.spellCast.text = talentClickedEvent.castTime
            binding.spellDescription.text = talentClickedEvent.description
            binding.spellCost.text = talentClickedEvent.powerCost
            binding.spellCd.text = talentClickedEvent.cooldown
        } else {
            binding.spellTooltip.visibility = View.GONE
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            viewModel.downloadCharacterSpecialization()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun classEventReceived(classEvent: ClassEvent) {
        viewModel.setPlayerClass(classEvent.data)
        var bgName = ""
        when (classEvent.data) {
            6 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }
            12 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }
            11 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
            13 -> {
                binding.talentsLayout.setBackgroundColor(Color.parseColor("#07060C"))
                bgName = "evoker_bg"
            }
        }
        Glide.with(this).load(NetworkUtils.getWoWAsset("class/$bgName")).into(binding.talentsBg)
    }
}

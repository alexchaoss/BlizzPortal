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
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.WoWClassName
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.BlizzardArmory.util.events.TalentClickedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.Locale


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
        val charClass = EventBus.getDefault().getStickyEvent(ClassEvent::class.java)?.data
        WoWClassName.setBackground(binding.talentsLayout, binding.talentsBg, requireContext(), charClass)
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
        WoWClassName.setBackground(binding.talentsLayout, binding.talentsBg, requireContext(), classEvent.data)
    }
}

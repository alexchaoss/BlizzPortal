package com.BlizzardArmory.ui.warcraft.character.covenant

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.WowCovenantFragmentBinding
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.CharacterSoulbinds
import com.BlizzardArmory.model.warcraft.covenant.character.soulbind.Soulbinds
import com.BlizzardArmory.model.warcraft.covenant.covenant.custom.CovenantSpells
import com.BlizzardArmory.model.warcraft.covenant.techtalent.TechTalent
import com.BlizzardArmory.ui.warcraft.character.navigation.WoWNavFragment
import com.BlizzardArmory.util.MetricConversion
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.TechTalentClickedEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

private const val CHARACTER = "character"
private const val REALM = "realm"
private const val REGION = "region"

class CovenantFragment : Fragment() {

    private var characterClass = 0

    private var _binding: WowCovenantFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CovenantViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.character = it.getString(CHARACTER)!!
            viewModel.realm = it.getString(REALM)!!
            viewModel.region = it.getString(REGION)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowCovenantFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        viewModel.downloadCharacterSoulbinds()
    }

    private fun setObservers() {
        viewModel.getSoulbinds().observe(viewLifecycleOwner, {
            setHeader(it)
            setAvatars(it)
            viewModel.downloadCovenantSpell(it.chosenCovenant.id)
            viewModel.downloadCovenantClassSpell(characterClass)

        })

        viewModel.getcovenantClassSpells().observe(viewLifecycleOwner, {
            Glide.with(requireContext())
                .load(it.find { covenantSpell -> covenantSpell.covenant_id == viewModel.getSoulbinds().value?.chosenCovenant?.id }?.icon)
                .into(binding.covenantClassSpell)
            try {
                setOnSpellTouched(binding.covenantClassSpell, it.find { covenantSpell -> covenantSpell.covenant_id == viewModel.getSoulbinds().value?.chosenCovenant?.id }!!)
            } catch (e: Exception) {
                binding.classSpellContainer.visibility = View.GONE
                Log.e("Error", "Covenant Class Spell", e)
            }
        })
        viewModel.getTechTalents().observe(viewLifecycleOwner, {
            val soulbind = viewModel.getSoulbinds().value!!.soulbinds[0]
            val talents = it[soulbind.soulbind.id]?.groupBy { talent -> talent.tier }
            if (!talents.isNullOrEmpty()) {
                binding.conduitRecycler.apply {
                    adapter = SoulbindAdapter(talents, soulbind.traits, requireContext())
                }
            }
            setOnAvatarClickListeners(it)
        })

        viewModel.getcovenantSpell().observe(viewLifecycleOwner, {
            try {
                //Glide.with(requireContext()).load(it[0].icon).into(binding.covenantSpell)
                //setOnSpellTouched(binding.covenantSpell, it[0])
            } catch (e: Exception) {
                Log.e("Error", "no icon", e)
            }

        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
        })
    }

    private fun setOnAvatarClickListeners(mutableMap: MutableMap<Long, List<TechTalent>>) {

        val selectedBorder = ConstraintLayout.LayoutParams(MetricConversion.getDPMetric(120, requireContext()), MetricConversion.getDPMetric(120, requireContext()))

        val unselectedBorder = ConstraintLayout.LayoutParams(MetricConversion.getDPMetric(100, requireContext()), MetricConversion.getDPMetric(120, requireContext()))

        mutableMap.forEach { map ->
            val soulbinds = viewModel.getSoulbinds().value?.soulbinds
            val talents = map.value.groupBy { it.tier }
            when (val index = soulbinds!!.indexOf(soulbinds.find { sb -> sb.soulbind.id == map.key })) {
                0 -> {
                    binding.avatar1.setOnClickListener {
                        setGreyShade(binding.avatar1, 1f)
                        setGreyShade(binding.avatar2, 0f)
                        setGreyShade(binding.avatar3, 0f)
                        binding.border1.setImageResource(R.drawable.soulbinds_portrait_selected)
                        binding.border1.layoutParams = selectedBorder
                        binding.border2.setImageResource(R.drawable.soulbinds_portrait_border)
                        binding.border2.layoutParams = unselectedBorder
                        binding.border3.setImageResource(R.drawable.soulbinds_portrait_border)
                        binding.border3.layoutParams = unselectedBorder
                        setConduitRecyclerAdapter(talents, soulbinds, index)
                    }
                }

                1 -> {
                    binding.avatar2.setOnClickListener {
                        setGreyShade(binding.avatar2, 1f)
                        setGreyShade(binding.avatar1, 0f)
                        setGreyShade(binding.avatar3, 0f)
                        binding.border1.setImageResource(R.drawable.soulbinds_portrait_border)
                        binding.border1.layoutParams = unselectedBorder
                        binding.border2.setImageResource(R.drawable.soulbinds_portrait_selected)
                        binding.border2.layoutParams = selectedBorder
                        binding.border3.setImageResource(R.drawable.soulbinds_portrait_border)
                        binding.border3.layoutParams = unselectedBorder
                        setConduitRecyclerAdapter(talents, soulbinds, index)
                    }
                }

                2 -> {
                    binding.avatar3.setOnClickListener {
                        setGreyShade(binding.avatar3, 1f)
                        setGreyShade(binding.avatar1, 0f)
                        setGreyShade(binding.avatar2, 0f)
                        binding.border1.setImageResource(R.drawable.soulbinds_portrait_border)
                        binding.border1.layoutParams = unselectedBorder
                        binding.border2.setImageResource(R.drawable.soulbinds_portrait_border)
                        binding.border2.layoutParams = unselectedBorder
                        binding.border3.setImageResource(R.drawable.soulbinds_portrait_selected)
                        binding.border3.layoutParams = selectedBorder
                        setConduitRecyclerAdapter(talents, soulbinds, index)
                    }
                }
            }
        }
    }

    private fun setConduitRecyclerAdapter(talents: Map<Int, List<TechTalent>>, soulbinds: List<Soulbinds>, index: Int) {
        if (!talents.isNullOrEmpty()) {
            binding.conduitRecycler.apply {
                adapter = if (!soulbinds[index].traits.isNullOrEmpty()) {
                    SoulbindAdapter(talents, soulbinds[index].traits, requireContext())
                } else {
                    SoulbindAdapter(talents, listOf(), requireContext())
                }
            }
        }
    }

    private fun setHeader(it: CharacterSoulbinds) {
        when (it.chosenCovenant.id) {
            1 -> binding.renownCircle.setImageResource(R.drawable.renown_level_circle_kyrian)
            2 -> binding.renownCircle.setImageResource(R.drawable.renown_level_circle_venthyr)
            3 -> binding.renownCircle.setImageResource(R.drawable.renown_level_circle_nightfae)
            4 -> binding.renownCircle.setImageResource(R.drawable.renown_level_circle_necrolord)
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

    private fun setAvatars(soulbindInformation: CharacterSoulbinds) {
        when (soulbindInformation.soulbinds.size) {
            1 -> setAvatar(binding.avatar1, binding.border1, soulbindInformation.soulbinds[0], 0)
            2 -> {
                setAvatar(binding.avatar1, binding.border1, soulbindInformation.soulbinds[0], 0)
                setAvatar(binding.avatar2, binding.border2, soulbindInformation.soulbinds[1], 1)
            }
            3 -> {
                setAvatar(binding.avatar1, binding.border1, soulbindInformation.soulbinds[0], 0)
                setAvatar(binding.avatar2, binding.border2, soulbindInformation.soulbinds[1], 1)
                setAvatar(binding.avatar3, binding.border3, soulbindInformation.soulbinds[2], 2)
            }
        }

        binding.renowmLevel.text = soulbindInformation.renownLevel.toString()
    }

    private fun setAvatar(avatar: ImageView, border: ImageView, soulbinds: Soulbinds, index: Int) {
        when (soulbinds.soulbind.id) {
            1L -> avatar.setImageResource(R.drawable.niya)
            2L -> avatar.setImageResource(R.drawable.dreamweaver)
            3L -> avatar.setImageResource(R.drawable.general_draven)
            4L -> avatar.setImageResource(R.drawable.plague_deviser_marileth)
            5L -> avatar.setImageResource(R.drawable.emeni)
            6L -> avatar.setImageResource(R.drawable.korayn)
            7L -> avatar.setImageResource(R.drawable.pelagos)
            8L -> avatar.setImageResource(R.drawable.nadjia_the_mistblade)
            9L -> avatar.setImageResource(R.drawable.theotar_the_mad_duke)
            10L -> avatar.setImageResource(R.drawable.bonesmith_heirmir)
            13L -> avatar.setImageResource(R.drawable.kleia)
            18L -> avatar.setImageResource(R.drawable.forgelite_prime_mikanikos)
        }
        if (index == 0) {
            border.setImageResource(R.drawable.soulbinds_portrait_selected)
            val params = ConstraintLayout.LayoutParams(MetricConversion.getDPMetric(120, requireContext()), MetricConversion.getDPMetric(120, requireContext()))
            border.layoutParams = params
        } else {
            setGreyShade(avatar, 0f)
        }
    }

    private fun setGreyShade(avatar: ImageView, saturation: Float) {
        val matrix = ColorMatrix()
        matrix.setSaturation(saturation)
        avatar.colorFilter = ColorMatrixColorFilter(matrix)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        Log.i("class", "received")
        characterClass = classEvent.data
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun techTalentTouchedEvent(techTalentClickedEvent: TechTalentClickedEvent) {
        if (techTalentClickedEvent.touch) {
            binding.spellCost.visibility = View.GONE
            binding.spellCd.visibility = View.GONE
            binding.spellTooltip.visibility = View.VISIBLE
            binding.spellName.text = techTalentClickedEvent.name
            binding.spellCast.text = techTalentClickedEvent.castTime
            binding.spellDescription.text = techTalentClickedEvent.description
        } else {
            binding.spellTooltip.visibility = View.GONE
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnSpellTouched(spell: ImageView, covenantSpells: CovenantSpells) {
        spell.setOnTouchListener { view, motionEvent ->
            view.performClick()
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.spellCd.visibility = View.VISIBLE
                    binding.spellTooltip.visibility = View.VISIBLE
                    if (covenantSpells.powerCost == null || covenantSpells.powerCost == "") {
                        binding.spellCost.visibility = View.GONE
                    } else {
                        binding.spellCost.visibility = View.VISIBLE
                    }
                    binding.spellName.text = covenantSpells.name
                    binding.spellCost.text = covenantSpells.powerCost
                    binding.spellCast.text = covenantSpells.castTime
                    binding.spellCd.text = covenantSpells.cooldown
                    binding.spellDescription.text = covenantSpells.description
                }
                MotionEvent.ACTION_UP -> {
                    binding.spellTooltip.visibility = View.GONE
                }
            }
            return@setOnTouchListener true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, region: String) =
            WoWNavFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER, character)
                    putString(REALM, realm)
                    putString(REGION, region)
                }
            }
    }
}
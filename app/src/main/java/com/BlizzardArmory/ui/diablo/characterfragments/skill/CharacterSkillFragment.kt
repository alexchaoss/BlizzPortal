package com.BlizzardArmory.ui.diablo.characterfragments.skill

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.D3SkillFragmentBinding
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.character.skills.Active
import com.BlizzardArmory.model.diablo.character.skills.Passive
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.util.events.*
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CharacterSkillFragment : Fragment() {

    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var characterInformation: CharacterInformation? = null
    private var closeButton: ImageButton? = null

    private var _binding: D3SkillFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterSkillViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = D3SkillFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(battlenetOAuth2Params!!)

        closeButton = ImageButton(view.context)
        closeButton!!.background = ContextCompat.getDrawable(requireContext(), R.drawable.close_button_d3)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0F)
        params.setMargins(0, 0, 0, 20)
        closeButton!!.layoutParams = params
        setCloseButton()

        val skillTooltipBG = GradientDrawable()
        skillTooltipBG.setStroke(6, Color.parseColor("#2e2a27"))
        skillTooltipBG.setColor(Color.BLACK)
        binding.skillTooltipScroll.background = skillTooltipBG
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun closeViewsWithoutButton() {
        binding.skillTooltipScroll.visibility = View.GONE
        EventBus.getDefault().post(D3SpellShownEvent(false))
        binding.skillTooltipScroll.scrollTo(0, 0)
    }

    private fun openSkillToolTip(skill: Active) {
        binding.tooltipRuneIcon.visibility = View.VISIBLE
        try {
            (closeButton!!.parent as ViewGroup).removeView(closeButton)
        } catch (e: Exception) {
            Log.e("Parent", "None")
        }
        binding.skillTooltip.addView(closeButton)
        binding.skillName.text = skill.skill.name
        Glide.with(this).load(NetworkUtils.D3_ICON_SKILLS.replace("url", skill.skill.icon))
            .into(binding.tooltipSkillIcon)
        binding.tooltipSkillIcon.setBackgroundResource(0)
        binding.tooltipSkillIcon.setPadding(0, 0, 0, 0)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.skillTooltipText.text = Html.fromHtml(skill.skill.descriptionHtml
                .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
        } else {
            binding.skillTooltipText.text = Html.fromHtml(skill.skill.descriptionHtml
                .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                .replace("</span>".toRegex(), "</font>"))
        }

        if (skill.rune != null) {
            binding.runeSeparator.visibility = View.VISIBLE
            binding.tooltipRuneIcon.setImageResource(viewModel.getRuneIcon(skill.rune.type))
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                binding.runeTooltipText.text = Html.fromHtml(("<big><font color=\"#FFFFFF\">" + skill.rune.name + "</font></big><br>"
                        + skill.rune.descriptionHtml)
                    .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                    .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                    .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                    .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
            } else {
                binding.runeTooltipText.text = Html.fromHtml(("<big><font color=\"#FFFFFF\">" + skill.rune.name + "</font></big><br>"
                        + skill.rune.descriptionHtml)
                    .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                    .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                    .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                    .replace("</span>".toRegex(), "</font>"))
            }
                }
                binding.skillTooltipScroll.visibility = View.VISIBLE
                EventBus.getDefault().post(D3SpellShownEvent(true))
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun openPassiveToolTip(skill: Passive) {

        binding.runeSeparator.visibility = View.VISIBLE
        binding.tooltipRuneIcon.setImageResource(0)
        binding.tooltipRuneIcon.visibility = View.GONE
        try {
            (closeButton!!.parent as ViewGroup).removeView(closeButton)
        } catch (e: Exception) {
            Log.e("Parent", "None")
        }
        binding.skillTooltip.addView(closeButton)
        binding.skillName.text = skill.skill.name
        Glide.with(this).load(NetworkUtils.D3_ICON_SKILLS.replace("url", skill.skill.icon))
            .into(binding.tooltipSkillIcon)
        binding.tooltipSkillIcon.setBackgroundResource(R.drawable.d3_passive_unselected)
        binding.tooltipSkillIcon.setPadding(15, 15, 15, 15)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.skillTooltipText.text = Html.fromHtml(skill.skill.descriptionHtml
                .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
        } else {
            binding.skillTooltipText.text = Html.fromHtml(skill.skill.descriptionHtml
                .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                .replace("</span>".toRegex(), "</font>"))
                }
                binding.skillTooltipScroll.visibility = View.VISIBLE
                EventBus.getDefault().post(D3SpellShownEvent(true))
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.runeTooltipText.text = Html.fromHtml("<i>" + skill.skill.flavorText + "</i>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.runeTooltipText.text = Html.fromHtml("<i>" + skill.skill.flavorText + "</i>")
                    }
                } catch (e: Exception) {
                    binding.runeSeparator.visibility = View.GONE
                }


    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setCloseButton() {
        closeButton!!.setOnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.performClick()
                Log.i("CLOSE", "CLICKED")
                binding.skillTooltipScroll.visibility = View.GONE
                EventBus.getDefault().post(D3SpellShownEvent(false))
                binding.skillTooltipScroll.scrollTo(0, 0)
            }
            false
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun characterEventReceived(woWCharacterEvent: WoWCharacterEvent) {
        characterInformation = woWCharacterEvent.data
        binding.skills.apply {
            adapter = SkillAdapter(characterInformation!!.skills.active)
        }
        binding.passives.apply {
            adapter = PassiveAdapter(characterInformation!!.skills.passive)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun skillClickedEvent(skillEvent: D3SkillEvent) {
        openSkillToolTip(skillEvent.skill)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun passiveClickedEvent(passiveEvent: D3PassiveEvent) {
        openPassiveToolTip(passiveEvent.skill)
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun closePanelReceived(d3ClosePanelEvent: D3ClosePanelEvent) {
        closeViewsWithoutButton()
    }
}
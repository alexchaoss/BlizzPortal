package com.BlizzardArmory.ui.diablo.characterfragments.skill

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.Pair
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.D3SkillFragmentBinding
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.character.skills.Active
import com.BlizzardArmory.model.diablo.character.skills.Skill
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.util.events.D3ClosePanelEvent
import com.BlizzardArmory.util.events.D3SpellShownEvent
import com.BlizzardArmory.util.events.WoWCharacterEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class CharacterSkillFragment : Fragment() {

    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var characterInformation: CharacterInformation? = null
    private var closeButton: ImageButton? = null
    private val passiveIcons = HashMap<String, Pair<Int, Skill>>()
    private val passiveList = ArrayList<ImageView>()
    private val skillIcons = HashMap<String, Pair<Int, Active>>()
    private val skillList = ArrayList<ImageView>()
    private val skillNameList = ArrayList<TextView>()
    private val skillRuneList = ArrayList<TextView>()
    private val skillLayoutList = ArrayList<ImageView>()

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
        Collections.addAll(skillList, binding.skill1Icon, binding.skill2Icon, binding.skill3Icon, binding.skill4Icon, binding.skill5Icon, binding.skill6Icon)
        Collections.addAll(skillRuneList, binding.skill1Rune, binding.skill2Rune, binding.skill3Rune, binding.skill4Rune, binding.skill5Rune, binding.skill6Rune)
        Collections.addAll(skillNameList, binding.skill1Name, binding.skill2Name, binding.skill3Name, binding.skill4Name, binding.skill5Name, binding.skill6Name)
        Collections.addAll(skillLayoutList, binding.skill1, binding.skill2, binding.skill3, binding.skill4, binding.skill5, binding.skill6)
        passiveList.add(binding.passive1)
        passiveList.add(binding.passive2)
        passiveList.add(binding.passive3)
        passiveList.add(binding.passive4)
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

    private fun downloadSkillIcons() {
        for (i in characterInformation!!.skills.active.indices) {
            val tempPair = Pair(i, characterInformation!!.skills.active[i])
            skillIcons[characterInformation!!.skills.active[i].skill.name] = tempPair
        }
        for (key in skillIcons.keys) {
            skillNameList[skillIcons[key]!!.first].text = skillIcons[key]!!.second.skill.name
            var smallRune = ""
            try {
                smallRune = viewModel.getSmallRuneIcon(skillIcons[key]!!.second.rune.type)
            } catch (e: Exception) {
                Log.e("Rune", "none")
            }
            val runeText = smallRune
            if (smallRune != "") {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    skillRuneList[skillIcons[key]!!.first].text = Html.fromHtml("<img src=\"" + smallRune + "\">" +
                            skillIcons[key]!!.second.rune.name, Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                } else {
                    skillRuneList[skillIcons[key]!!.first].text = Html.fromHtml("<img src=\"" + smallRune + "\">" +
                            skillIcons[key]!!.second.rune.name, { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                }
            }
            Glide.with(this).load(URLConstants.D3_ICON_SKILLS.replace("url", skillIcons[key]!!.second.skill.icon))
                    .placeholder(R.drawable.loading_placeholder).into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            skillList[skillIcons[key]!!.first].setImageDrawable(resource)
                            setOnTouchSkillTooltip(key, runeText, skillList[skillIcons[key]!!.first].drawable)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchSkillTooltip(tempKey: String, runeText: String, icon: Drawable) {
        skillLayoutList[skillIcons[tempKey]!!.first].setOnTouchListener { _: View?, event: MotionEvent ->
            binding.tooltipRuneIcon.visibility = View.VISIBLE
            try {
                (closeButton!!.parent as ViewGroup).removeView(closeButton)
            } catch (e: Exception) {
                Log.e("Parent", "None")
            }
            binding.skillTooltip.addView(closeButton)
            if (event.action == MotionEvent.ACTION_DOWN) {
                binding.skillName.text = skillIcons[tempKey]!!.second.skill.name
                binding.tooltipSkillIcon.setImageDrawable(icon)
                binding.tooltipSkillIcon.setBackgroundResource(0)
                binding.tooltipSkillIcon.setPadding(0, 0, 0, 0)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    binding.skillTooltipText.text = Html.fromHtml(skillIcons[tempKey]!!.second.skill.descriptionHtml
                            .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                            .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                            .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                            .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                } else {
                    binding.skillTooltipText.text = Html.fromHtml(skillIcons[tempKey]!!.second.skill.descriptionHtml
                            .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                            .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                            .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                            .replace("</span>".toRegex(), "</font>"))
                }
                if (runeText != "") {
                    binding.runeSeparator.visibility = View.VISIBLE
                    binding.tooltipRuneIcon.setImageResource(viewModel.getRuneIcon(skillIcons[tempKey]!!.second.rune.type))
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.runeTooltipText.text = Html.fromHtml(("<big><font color=\"#FFFFFF\">" + skillIcons[tempKey]!!.second.rune.name + "</font></big><br>"
                                + skillIcons[tempKey]!!.second.rune.descriptionHtml)
                                .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                                .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                                .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                                .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.runeTooltipText.text = Html.fromHtml(("<big><font color=\"#FFFFFF\">" + skillIcons[tempKey]!!.second.rune.name + "</font></big><br>"
                                + skillIcons[tempKey]!!.second.rune.descriptionHtml)
                                .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                                .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                                .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                                .replace("</span>".toRegex(), "</font>"))
                    }
                } else {
                    binding.runeTooltipText.text = ""
                    binding.tooltipRuneIcon.setImageResource(0)
                    binding.runeSeparator.visibility = View.GONE
                }
                binding.skillTooltipScroll.visibility = View.VISIBLE
                EventBus.getDefault().post(D3SpellShownEvent(true))
            }
            true
        }
    }



    private fun downloadPssiveIcons() {
        for (i in characterInformation?.skills?.passive?.indices!!) {
            val tempPair = Pair(i, characterInformation?.skills?.passive?.get(i)?.skill!!)
            passiveIcons[characterInformation?.skills?.passive?.get(i)?.skill?.name!!] = tempPair
        }
        for (key in passiveIcons.keys) {
            Glide.with(this).load(URLConstants.D3_ICON_SKILLS.replace("url", passiveIcons[key]!!.second.icon))
                    .placeholder(R.drawable.loading_placeholder).into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            passiveList[passiveIcons[key]!!.first].setImageDrawable(resource)
                            setOnTouchPassiveTooltip(key, passiveList[passiveIcons[key]!!.first].drawable)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchPassiveTooltip(tempKey: String, icon: Drawable) {
        passiveList[passiveIcons[tempKey]!!.first].setOnTouchListener { _: View?, event: MotionEvent ->
            binding.runeSeparator.visibility = View.VISIBLE
            binding.tooltipRuneIcon.setImageResource(0)
            binding.tooltipRuneIcon.visibility = View.GONE
            try {
                (closeButton!!.parent as ViewGroup).removeView(closeButton)
            } catch (e: Exception) {
                Log.e("Parent", "None")
            }
            binding.skillTooltip.addView(closeButton)
            if (event.action == MotionEvent.ACTION_DOWN) {
                binding.skillName.text = passiveIcons[tempKey]!!.second.name
                binding.tooltipSkillIcon.setImageDrawable(icon)
                binding.tooltipSkillIcon.setBackgroundResource(R.drawable.d3_passive_unselected)
                binding.tooltipSkillIcon.setPadding(15, 15, 15, 15)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    binding.skillTooltipText.text = Html.fromHtml(passiveIcons[tempKey]!!.second.descriptionHtml
                            .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                            .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                            .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                            .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                } else {
                    binding.skillTooltipText.text = Html.fromHtml(passiveIcons[tempKey]!!.second.descriptionHtml
                            .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                            .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                            .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                            .replace("</span>".toRegex(), "</font>"))
                }
                binding.skillTooltipScroll.visibility = View.VISIBLE
                EventBus.getDefault().post(D3SpellShownEvent(true))
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.runeTooltipText.text = Html.fromHtml("<i>" + passiveIcons[tempKey]!!.second.flavorText + "</i>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.runeTooltipText.text = Html.fromHtml("<i>" + passiveIcons[tempKey]!!.second.flavorText + "</i>")
                    }
                } catch (e: Exception) {
                    binding.runeSeparator.visibility = View.GONE
                }
            }
            true
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
        downloadSkillIcons()
        downloadPssiveIcons()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun closePanelReceived(d3ClosePanelEvent: D3ClosePanelEvent) {
        closeViewsWithoutButton()
    }
}
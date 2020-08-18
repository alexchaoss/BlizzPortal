package com.BlizzardArmory.ui.ui_diablo.characterfragments

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
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.character.skills.Active
import com.BlizzardArmory.model.diablo.character.skills.Skill
import com.BlizzardArmory.util.events.BackPressEvent
import com.BlizzardArmory.util.events.CharacterEvent
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.d3_skill_fragment.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.d3_skill_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        closeButton = ImageButton(view.context)
        closeButton!!.background = requireContext().getDrawable(R.drawable.close_button_d3)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0F)
        params.setMargins(0, 0, 0, 20)
        closeButton!!.layoutParams = params
        setCloseButton()

        val skillTooltipBG = GradientDrawable()
        skillTooltipBG.setStroke(6, Color.parseColor("#2e2a27"))
        skillTooltipBG.setColor(Color.BLACK)
        skill_tooltip_scroll.background = skillTooltipBG
        Collections.addAll(skillList, skill1_icon, skill2_icon, skill3_icon, skill4_icon, skill5_icon, skill6_icon)
        Collections.addAll(skillRuneList, skill1_rune, skill2_rune, skill3_rune, skill4_rune, skill5_rune, skill6_rune)
        Collections.addAll(skillNameList, skill1_name, skill2_name, skill3_name, skill4_name, skill5_name, skill6_name)
        Collections.addAll(skillLayoutList, skill1, skill2, skill3, skill4, skill5, skill6)
        passiveList.add(passive1)
        passiveList.add(passive2)
        passiveList.add(passive3)
        passiveList.add(passive4)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun closeViewsWithoutButton() {
        skill_tooltip_scroll!!.visibility = View.GONE
        skill_tooltip_scroll!!.scrollTo(0, 0)
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
                smallRune = getSmallRuneIcon(skillIcons[key]!!.second.rune.type)
            } catch (e: Exception) {
                Log.e("Rune", "none")
            }
            val runeText = smallRune
            if (smallRune != "") {
                skillRuneList[skillIcons[key]!!.first].text = Html.fromHtml("<img src=\"" + smallRune + "\">" +
                        skillIcons[key]!!.second.rune.name, Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source: String? ->
                    val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                    val drawable = resources.getDrawable(resourceId, requireContext().theme)
                    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                    drawable
                }, null)
            }
            Picasso.get().load(URLConstants.D3_ICON_SKILLS.replace("url", skillIcons[key]!!.second.skill.icon))
                    .placeholder(R.drawable.loading_placeholder).into(skillList[skillIcons[key]!!.first], object : Callback {
                        override fun onSuccess() {
                            setOnTouchSkillTooltip(key, runeText, skillList[skillIcons[key]!!.first].drawable)
                        }

                        override fun onError(e: Exception) {}
                    })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchSkillTooltip(tempKey: String, runeText: String, icon: Drawable) {
        skillLayoutList[skillIcons[tempKey]!!.first].setOnTouchListener { _: View?, event: MotionEvent ->
            tooltip_rune_icon!!.visibility = View.VISIBLE
            try {
                (closeButton!!.parent as ViewGroup).removeView(closeButton)
            } catch (e: Exception) {
                Log.e("Parent", "None")
            }
            skill_tooltip!!.addView(closeButton)
            if (event.action == MotionEvent.ACTION_DOWN) {
                skill_name!!.text = skillIcons[tempKey]!!.second.skill.name
                tooltip_skill_icon!!.setImageDrawable(icon)
                tooltip_skill_icon!!.setBackgroundResource(0)
                tooltip_skill_icon!!.setPadding(0, 0, 0, 0)
                skill_tooltip_text!!.text = Html.fromHtml(skillIcons[tempKey]!!.second.skill.descriptionHtml
                        .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                        .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                        .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                        .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                if (runeText != "") {
                    rune_separator!!.visibility = View.VISIBLE
                    tooltip_rune_icon!!.setImageResource(getRuneIcon(skillIcons[tempKey]!!.second.rune.type))
                    rune_tooltip_text!!.text = Html.fromHtml(("<big><font color=\"#FFFFFF\">" + skillIcons[tempKey]!!.second.rune.name + "</font></big><br>"
                            + skillIcons[tempKey]!!.second.rune.descriptionHtml)
                            .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                            .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                            .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                            .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                } else {
                    rune_tooltip_text!!.text = ""
                    tooltip_rune_icon!!.setImageResource(0)
                    rune_separator!!.visibility = View.GONE
                }
                skill_tooltip_scroll!!.visibility = View.VISIBLE
            }
            true
        }
    }

    private fun getSmallRuneIcon(type: String): String {
        when (type) {
            "a" -> return "small_rune_a"
            "b" -> return "small_rune_b"
            "c" -> return "small_rune_c"
            "d" -> return "small_rune_d"
            "e" -> return "small_rune_e"
        }
        return ""
    }

    private fun getRuneIcon(type: String): Int {
        when (type) {
            "a" -> return R.drawable.rune_a
            "b" -> return R.drawable.rune_b
            "c" -> return R.drawable.rune_c
            "d" -> return R.drawable.rune_d
            "e" -> return R.drawable.rune_e
        }
        return 0
    }

    private fun downloadPssiveIcons() {
        for (i in characterInformation?.skills?.passive?.indices!!) {
            val tempPair = Pair(i, characterInformation?.skills?.passive?.get(i)?.skill!!)
            passiveIcons[characterInformation?.skills?.passive?.get(i)?.skill?.name!!] = tempPair
        }
        for (key in passiveIcons.keys) {
            Picasso.get().load(URLConstants.D3_ICON_SKILLS.replace("url", passiveIcons[key]!!.second.icon))
                    .placeholder(R.drawable.loading_placeholder).into(passiveList[passiveIcons[key]!!.first], object : Callback {
                        override fun onSuccess() {
                            setOnTouchPassiveTooltip(key, passiveList[passiveIcons[key]!!.first].drawable)
                        }

                        override fun onError(e: Exception) {}
                    })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchPassiveTooltip(tempKey: String, icon: Drawable) {
        passiveList[passiveIcons[tempKey]!!.first].setOnTouchListener { _: View?, event: MotionEvent ->
            rune_separator!!.visibility = View.VISIBLE
            tooltip_rune_icon!!.setImageResource(0)
            tooltip_rune_icon!!.visibility = View.GONE
            try {
                (closeButton!!.parent as ViewGroup).removeView(closeButton)
            } catch (e: Exception) {
                Log.e("Parent", "None")
            }
            skill_tooltip!!.addView(closeButton)
            if (event.action == MotionEvent.ACTION_DOWN) {
                skill_name!!.text = passiveIcons[tempKey]!!.second.name
                tooltip_skill_icon!!.setImageDrawable(icon)
                tooltip_skill_icon!!.setBackgroundResource(R.drawable.d3_passive_unselected)
                tooltip_skill_icon!!.setPadding(15, 15, 15, 15)
                skill_tooltip_text!!.text = Html.fromHtml(passiveIcons[tempKey]!!.second.descriptionHtml
                        .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                        .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                        .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                        .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                skill_tooltip_scroll!!.visibility = View.VISIBLE
                try {
                    rune_tooltip_text!!.text = Html.fromHtml("<i>" + passiveIcons[tempKey]!!.second.flavorText + "</i>", Html.FROM_HTML_MODE_LEGACY)
                } catch (e: Exception) {
                    rune_separator!!.visibility = View.GONE
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
                skill_tooltip_scroll!!.visibility = View.GONE
                skill_tooltip_scroll!!.scrollTo(0, 0)
            }
            false
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun characterEventReceived(characterEvent: CharacterEvent) {
        characterInformation = characterEvent.data
        downloadSkillIcons()
        downloadPssiveIcons()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun backPressEventReceived(backPressEvent: BackPressEvent) {
        if (backPressEvent.data) {
            closeViewsWithoutButton()
        }
    }
}
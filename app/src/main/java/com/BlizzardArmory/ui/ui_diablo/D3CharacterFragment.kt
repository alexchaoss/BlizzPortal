package com.BlizzardArmory.ui.ui_diablo

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.text.Html.ImageGetter
import android.util.Log
import android.util.Pair
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.diablo.character.Active
import com.BlizzardArmory.diablo.character.CharacterInformation
import com.BlizzardArmory.diablo.character.Skill
import com.BlizzardArmory.diablo.item.SingleItem
import com.BlizzardArmory.diablo.items.Item
import com.BlizzardArmory.diablo.items.Items
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.d3_character_fragment.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.roundToInt

class D3CharacterFragment : Fragment(), IOnBackPressed {
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private lateinit var networkServices: NetworkServices

    private var characterInformation: CharacterInformation? = null
    private var itemsInformation: Items? = null
    private var dialog: AlertDialog? = null
    private var battletag = ""
    private var selectedRegion = ""
    private var id = 0L
    private val imageViewItem = HashMap<String, ImageView?>()
    private val itemIconURL = HashMap<String, String?>()
    private val items = ArrayList<Item>()
    private val primaryStatsMap = HashMap<Int, String>()
    private val secondaryStatsMap = HashMap<Int, String>()
    private val gemsMap = HashMap<Int, String>()
    private var closeButton: ImageButton? = null
    private var layoutParamsStats: LinearLayout.LayoutParams? = null
    private var armor: TextView? = null
    private var dps: TextView? = null
    private var primarystats: TextView? = null
    private var secondarystats: TextView? = null
    private var gems: TextView? = null
    private var set: TextView? = null
    private var transmog: TextView? = null
    private var flavortext: TextView? = null
    private var misctext: TextView? = null
    private var cubeText = ""
    private val cubeMap = HashMap<String, ImageView?>()
    private val singleItem = ArrayList<SingleItem>()
    private val passiveIcons = HashMap<String, Pair<Int, Skill>>()
    private val passiveList = ArrayList<ImageView>()
    private val skillIcons = HashMap<String, Pair<Int, Active>>()
    private val skillList = ArrayList<ImageView>()
    private val skillNameList = ArrayList<TextView>()
    private val skillRuneList = ArrayList<TextView>()
    private val skillLayoutList = ArrayList<ImageView>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.d3_character_fragment, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        URLConstants.loading = true
        val bundle = requireArguments()
        id = bundle.getLong("id")
        battletag = bundle.getString("battletag")!!
        selectedRegion = bundle.getString("region")!!

        gson = GsonBuilder().create()
        retrofit = Retrofit.Builder().baseUrl(URLConstants.getBaseURLforAPI(selectedRegion.toLowerCase(Locale.ROOT))).addConverterFactory(GsonConverterFactory.create(gson!!)).build()
        networkServices = retrofit?.create(NetworkServices::class.java)!!

        dialog = null
        closeButton = ImageButton(view.context)
        closeButton!!.background = requireContext().getDrawable(R.drawable.close_button_d3)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0F)
        params.setMargins(0, 0, 0, 20)
        closeButton!!.layoutParams = params
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
        cube_armor_item.setImageResource(0)
        cube_sword_item.setImageResource(0)
        cube_ring_item.setImageResource(0)
        item_name.setTextColor(Color.WHITE)
        item_name.gravity = View.TEXT_ALIGNMENT_CENTER
        dps = TextView(view.context)
        dps!!.setTextColor(Color.WHITE)
        armor = TextView(view.context)
        armor!!.setTextColor(Color.WHITE)
        primarystats = TextView(view.context)
        primarystats!!.setTextColor(Color.WHITE)
        secondarystats = TextView(view.context)
        secondarystats!!.setTextColor(Color.WHITE)
        gems = TextView(view.context)
        gems!!.setTextColor(Color.WHITE)
        set = TextView(view.context)
        transmog = TextView(view.context)
        transmog!!.setTextColor(Color.WHITE)
        flavortext = TextView(view.context)
        flavortext!!.setTextColor(Color.WHITE)
        misctext = TextView(view.context)
        misctext!!.setTextColor(Color.WHITE)
        layoutParamsStats = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        layoutParamsStats!!.setMargins(20, 0, 20, 0)
        setChatGem()
        addImageViewItemsToList()
        setCloseButton()
        navigateTabs()
        loading_circle.visibility = View.VISIBLE
        val startTime = System.nanoTime()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Params: BnOAuth2Params = Objects.requireNonNull(requireActivity().intent.extras).getParcelable(BnConstants.BUNDLE_BNPARAMS)!!
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)
        setAllTabs()
        val endTime = System.nanoTime()
        val duration = (endTime - startTime) / 1000000000
        Log.i("time", duration.toString())
    }

    private fun setAllTabs() {
        try {
            setCharacterInformation()
            setItemInformation()
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

    @Throws(IOException::class)
    private fun setItemInformation() {
        val call: Call<Items> = networkServices.getHeroItems(battletag, id, "profile-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : retrofit2.Callback<Items> {
            override fun onResponse(call: Call<Items>, response: retrofit2.Response<Items>) {
                when {
                    response.isSuccessful -> {
                        itemsInformation = response.body()
                        itemInformation
                        setItemBackgroundColor()
                        getItemIconURL()
                        itemIcons
                        for (i in items.indices) {
                            setItemInformation(i)
                        }
                    }
                    response.code() >= 400 -> {
                        callErrorAlertDialog(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun setCharacterInformation() {
        val call: Call<CharacterInformation> = networkServices.getD3Hero(battletag, id, "profile-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : retrofit2.Callback<CharacterInformation> {
            override fun onResponse(call: Call<CharacterInformation>, response: retrofit2.Response<CharacterInformation>) {
                when {
                    response.isSuccessful -> {
                        characterInformation = response.body()
                        setGlobes()
                        setName()
                        cubeIcons
                        downloadCubeItems()
                        downloadSkillIcons()
                        downloadPssiveIcons()
                        val primaryStats = DecimalFormat("#0")
                        strength_text?.text = primaryStats.format(characterInformation?.stats?.strength?.roundToInt()).toString()
                        dexterity_text?.text = primaryStats.format(characterInformation?.stats?.dexterity?.roundToInt()).toString()
                        intelligence_text?.text = primaryStats.format(characterInformation?.stats?.intelligence?.roundToInt()).toString()
                        vitality_text?.text = primaryStats.format(characterInformation?.stats?.vitality?.roundToInt()).toString()
                        damage?.text = Html.fromHtml("<br><br>Damage<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.damage) + "</font>", Html.FROM_HTML_MODE_LEGACY)
                        toughness?.text = Html.fromHtml("Toughness<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.toughness) + "</font>", Html.FROM_HTML_MODE_LEGACY)
                        recovery!!.text = Html.fromHtml("Recovery<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.healing) + "</font>", Html.FROM_HTML_MODE_LEGACY)
                        loading_circle!!.visibility = View.GONE
                        URLConstants.loading = false
                    }
                    response.code() >= 400 -> {
                        callErrorAlertDialog(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<CharacterInformation>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setCloseButton() {
        closeButton!!.setOnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.performClick()
                Log.i("CLOSE", "CLICKED")
                item_scroll_view!!.visibility = View.GONE
                skill_tooltip_scroll!!.visibility = View.GONE
                item_stats!!.removeView(primarystats)
                item_stats!!.removeView(secondarystats)
                item_stats!!.removeView(gems)
                item_stats!!.removeView(transmog)
                item_stats!!.removeView(flavortext)
                item_stats!!.removeView(misctext)
                armor_damage!!.removeView(dps)
                armor_damage!!.removeView(armor)
                item_stats!!.removeView(closeButton)
                item_stats!!.removeView(set)
                weapon_effect!!.setImageDrawable(null)
                item_scroll_view!!.scrollTo(0, 0)
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setChatGem() {
        chatgem_inactive.setOnTouchListener { _: View?, _: MotionEvent? ->
            if (chatgem_active.visibility == View.VISIBLE) {
                Toast.makeText(context, "Gem Deactivated", Toast.LENGTH_SHORT).show()
                chatgem_active.visibility = View.GONE
            } else {
                chatgem_active.visibility = View.VISIBLE
                val moo = Math.random()
                val perfect = Math.random()
                when {
                    perfect >= 0.98 -> {
                        Toast.makeText(context, "Perfect Gem Activated", Toast.LENGTH_SHORT).show()
                    }
                    moo >= 0.95 -> {
                        Toast.makeText(context, "Mooooooooo!", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Gem Activated", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            false
        }
    }

    private fun setGlobes() {
        val life: String = if (characterInformation!!.stats.life >= 1000) {
            (characterInformation!!.stats.life / 1000).roundToInt().toString() + "K"
        } else {
            characterInformation!!.stats.life.toString()
        }
        total_life!!.text = life
        val ressourceText: String = if (characterInformation!!.class_ == "demon-hunter") {
            characterInformation!!.stats.primaryResource.toString() + "\n" + characterInformation!!.stats.secondaryResource.toString()
        } else {
            characterInformation!!.stats.primaryResource.toString()
        }
        ressource!!.text = ressourceText
        when (characterInformation!!.class_) {
            "barbarian" -> ressource_globe!!.setImageResource(R.drawable.d3_fury)
            "wizard" -> ressource_globe!!.setImageResource(R.drawable.d3_arcane_power)
            "demon-hunter" -> ressource_globe!!.setImageResource(R.drawable.d3_hatred_disc)
            "witch-doctor" -> ressource_globe!!.setImageResource(R.drawable.d3_mana)
            "necromancer" -> ressource_globe!!.setImageResource(R.drawable.d3_essence)
            "monk" -> {
                ressource_globe!!.setImageResource(R.drawable.d3_spirit)
                ressource!!.setTextColor(Color.BLACK)
            }
            "crusader" -> {
                ressource_globe!!.setImageResource(R.drawable.d3_wrath)
                ressource!!.setTextColor(Color.BLACK)
            }
        }
    }

    private fun closeViewsWithoutButton() {
        item_scroll_view!!.visibility = View.GONE
        skill_tooltip_scroll!!.visibility = View.GONE
        item_stats!!.removeView(primarystats)
        item_stats!!.removeView(secondarystats)
        item_stats!!.removeView(gems)
        item_stats!!.removeView(transmog)
        item_stats!!.removeView(flavortext)
        item_stats!!.removeView(misctext)
        armor_damage!!.removeView(dps)
        armor_damage!!.removeView(armor)
        item_stats!!.removeView(closeButton)
        item_stats!!.removeView(set)
        weapon_effect!!.setImageDrawable(null)
        item_scroll_view!!.scrollTo(0, 0)
    }

    private fun downloadSkillIcons() {
        for (i in characterInformation!!.skills.active.indices) {
            val tempPair = Pair(i, characterInformation!!.skills.active[i])
            skillIcons[characterInformation!!.skills.active[i].skill.name] = tempPair
        }
        for (key in skillIcons.keys) {
            skillNameList[Objects.requireNonNull(skillIcons[key])!!.first].text = Objects.requireNonNull(skillIcons[key])!!.second.skill.name
            var smallRune = ""
            try {
                smallRune = getSmallRuneIcon(Objects.requireNonNull(skillIcons[key])!!.second.rune.type)
            } catch (e: Exception) {
                Log.e("Rune", "none")
            }
            val runeText = smallRune
            if (smallRune != "") {
                skillRuneList[Objects.requireNonNull(skillIcons[key])!!.first].text = Html.fromHtml("<img src=\"" + smallRune + "\">" +
                        Objects.requireNonNull(skillIcons[key])!!.second.rune.name, Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
                    val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                    val drawable = resources.getDrawable(resourceId, requireContext().theme)
                    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                    drawable
                }, null)
            }
            Picasso.get().load(URLConstants.D3_ICON_SKILLS.replace("url", Objects.requireNonNull(skillIcons[key])!!.second.skill.icon))
                    .into(skillList[Objects.requireNonNull(skillIcons[key])!!.first], object : Callback {
                        override fun onSuccess() {
                            setOnTouchSkillTooltip(key, runeText, skillList[Objects.requireNonNull(skillIcons[key])!!.first].drawable)
                        }

                        override fun onError(e: Exception) {}
                    })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchSkillTooltip(tempKey: String, runeText: String, icon: Drawable) {
        skillLayoutList[Objects.requireNonNull(skillIcons[tempKey])!!.first].setOnTouchListener { _: View?, event: MotionEvent ->
            tooltip_rune_icon!!.visibility = View.VISIBLE
            try {
                (closeButton!!.parent as ViewGroup).removeView(closeButton)
            } catch (e: Exception) {
                Log.e("Parent", "None")
            }
            skill_tooltip!!.addView(closeButton)
            if (event.action == MotionEvent.ACTION_DOWN) {
                skill_name!!.text = Objects.requireNonNull(skillIcons[tempKey])!!.second.skill.name
                tooltip_skill_icon!!.setImageDrawable(icon)
                tooltip_skill_icon!!.setBackgroundResource(0)
                tooltip_skill_icon!!.setPadding(0, 0, 0, 0)
                skill_tooltip_text!!.text = Html.fromHtml(Objects.requireNonNull(skillIcons[tempKey])!!.second.skill.descriptionHtml
                        .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                        .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                        .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                        .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                if (runeText != "") {
                    rune_separator!!.visibility = View.VISIBLE
                    tooltip_rune_icon!!.setImageResource(getRuneIcon(Objects.requireNonNull(skillIcons[tempKey])!!.second.rune.type))
                    rune_tooltip_text!!.text = Html.fromHtml(("<big><font color=\"#FFFFFF\">" + Objects.requireNonNull(skillIcons[tempKey])!!.second.rune.name + "</font></big><br>"
                            + Objects.requireNonNull(skillIcons[tempKey])!!.second.rune.descriptionHtml)
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
        for (i in characterInformation!!.skills.passive.indices) {
            val tempPair = Pair(i, characterInformation!!.skills.passive[i].skill)
            passiveIcons[characterInformation!!.skills.passive[i].skill.name] = tempPair
        }
        for (key in passiveIcons.keys) {
            Picasso.get().load(URLConstants.D3_ICON_SKILLS.replace("url", Objects.requireNonNull(passiveIcons[key])!!.second.icon))
                    .into(passiveList[Objects.requireNonNull(passiveIcons[key])!!.first], object : Callback {
                        override fun onSuccess() {
                            setOnTouchPassiveTooltip(key, passiveList[Objects.requireNonNull(passiveIcons[key])!!.first].drawable)
                        }

                        override fun onError(e: Exception) {}
                    })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchPassiveTooltip(tempKey: String, icon: Drawable) {
        passiveList[Objects.requireNonNull(passiveIcons[tempKey])!!.first].setOnTouchListener { _: View?, event: MotionEvent ->
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
                skill_name!!.text = Objects.requireNonNull(passiveIcons[tempKey])!!.second.name
                tooltip_skill_icon!!.setImageDrawable(icon)
                tooltip_skill_icon!!.setBackgroundResource(R.drawable.d3_passive_unselected)
                tooltip_skill_icon!!.setPadding(15, 15, 15, 15)
                skill_tooltip_text!!.text = Html.fromHtml(Objects.requireNonNull(passiveIcons[tempKey])!!.second.descriptionHtml
                        .replace("<span class=\"d3-color-green".toRegex(), "<font color=\"#00ff00")
                        .replace("<span class=\"d3-color-gold".toRegex(), "<font color=\"#c7b377")
                        .replace("<span class=\"d3-color-yellow".toRegex(), "<font color=\"#ffff00")
                        .replace("</span>".toRegex(), "</font>"), Html.FROM_HTML_MODE_LEGACY)
                skill_tooltip_scroll!!.visibility = View.VISIBLE
                try {
                    rune_tooltip_text!!.text = Html.fromHtml("<i>" + Objects.requireNonNull(passiveIcons[tempKey])!!.second.flavorText + "</i>", Html.FROM_HTML_MODE_LEGACY)
                } catch (e: Exception) {
                    rune_separator!!.visibility = View.GONE
                }
            }
            true
        }
    }

    private fun downloadCubeItems() {
        for (i in characterInformation!!.legendaryPowers.indices) {
            val call: Call<SingleItem> = networkServices.getItem(characterInformation!!.legendaryPowers[i].tooltipParams, "profile-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
            call.enqueue(object : retrofit2.Callback<SingleItem> {
                override fun onResponse(call: Call<SingleItem>, response: retrofit2.Response<SingleItem>) {
                    when {
                        response.isSuccessful -> {
                            singleItem.add(response.body()!!)
                            if (i == characterInformation!!.legendaryPowers.size - 1) {
                                setCubeText()
                                URLConstants.loading = false
                            }
                        }
                        response.code() >= 400 -> {
                            callErrorAlertDialog(response.code())
                        }
                        else -> {
                            callErrorAlertDialog(0)
                        }
                    }
                }

                override fun onFailure(call: Call<SingleItem>, t: Throwable) {
                    Log.e("Error", t.localizedMessage)
                    callErrorAlertDialog(0)
                }
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setCubeText() {
        for (i in singleItem.indices) {
            Objects.requireNonNull(cubeMap[singleItem[i].icon.toLowerCase(Locale.ROOT)])?.setOnTouchListener { _: View?, _: MotionEvent? ->
                for (j in singleItem[i].attributes.secondary.indices) {
                    if (singleItem[i].attributes.secondary[j].textHtml.contains("d3-color-ffff8000")) {
                        cubeText = "<big>" + singleItem[i].name + "</big><br>" + singleItem[i].attributes.secondary[j].textHtml
                                .replace("<span class=\"d3-color-ff".toRegex(), "<font color=\"#").replace("<span class=\"d3-color-magic\">".toRegex(), "<font color=\"#7979d4\">")
                                .replace("span".toRegex(), "font")
                    }
                }
                cube_text!!.visibility = View.VISIBLE
                cube_text!!.text = Html.fromHtml(cubeText, Html.FROM_HTML_MODE_LEGACY)
                false
            }
        }
    }

    private fun navigateTabs() {
        d3_nav!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        item_d3_character!!.setBackgroundResource(R.drawable.diablo3_main_window_no_separation)
                        stats_layout!!.visibility = View.VISIBLE
                        gear_layout!!.visibility = View.GONE
                        skill_layout!!.visibility = View.GONE
                        cube_layout!!.visibility = View.GONE
                        closeViewsWithoutButton()
                        chatgem_inactive.visibility = View.VISIBLE
                        chatgem_statue.visibility = View.VISIBLE
                    }
                    1 -> {
                        item_d3_character!!.setBackgroundResource(R.drawable.diablo3_main_window_no_separation)
                        stats_layout!!.visibility = View.GONE
                        gear_layout!!.visibility = View.VISIBLE
                        cube_layout!!.visibility = View.GONE
                        skill_layout!!.visibility = View.GONE
                        closeViewsWithoutButton()
                        chatgem_active.visibility = View.GONE
                        chatgem_inactive.visibility = View.GONE
                        chatgem_statue.visibility = View.GONE
                    }
                    2 -> {
                        item_d3_character!!.setBackgroundResource(R.drawable.diablo3_main_window)
                        stats_layout!!.visibility = View.GONE
                        gear_layout!!.visibility = View.GONE
                        cube_layout!!.visibility = View.GONE
                        skill_layout!!.visibility = View.VISIBLE
                        closeViewsWithoutButton()
                        chatgem_inactive.visibility = View.VISIBLE
                        chatgem_statue.visibility = View.VISIBLE
                    }
                    3 -> {
                        item_d3_character!!.setBackgroundResource(R.drawable.diablo3_main_window)
                        stats_layout!!.visibility = View.GONE
                        gear_layout!!.visibility = View.GONE
                        cube_layout!!.visibility = View.VISIBLE
                        skill_layout!!.visibility = View.GONE
                        closeViewsWithoutButton()
                        chatgem_inactive.visibility = View.VISIBLE
                        chatgem_statue.visibility = View.VISIBLE
                    }
                    else -> {
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setItemInformation(index: Int) {
        val primary = StringBuilder()
        val secondary = StringBuilder()
        val gem = StringBuilder()
        try {
            for (j in items[index].attributesHtml.primary.indices) {
                var attribute = items[index].attributesHtml.primary[j].replace("<span class=\"tooltip-icon-enchant\"></span>".toRegex(), "<img src=\"utility\">")
                attribute = attribute.replace("<span class=\"tooltip-icon-utility\"></span>".toRegex(), "<img src=\"utility\">")
                attribute = attribute.replace("<span class=\"tooltip-icon-bullet\"></span>".toRegex(), "<img src=\"primary" +
                        "\">")
                attribute = attribute.replace("span class=\"d3-color-ff".toRegex(), "font color=\"#")
                attribute = attribute.replace("span class=\"d3-color-magic".toRegex(), "font color=\"#7979d4")
                primary.append(attribute.replace("</span>".toRegex(), "</font>")).append("<br>")
            }
            Log.i("Test secondary", primary.toString())
        } catch (e: Exception) {
            primary.append("")
        }
        try {
            for (j in items[index].attributesHtml.secondary.indices) {
                var attribute = items[index].attributesHtml.secondary[j].replace("<span class=\"tooltip-icon-enchant\"></span>".toRegex(), "<img src=\"utility\">")
                attribute = attribute.replace("<span class=\"tooltip-icon-bullet\"></span>".toRegex(), "<img src=\"primary" +
                        "\">")
                attribute = attribute.replace("<span class=\"tooltip-icon-utility\"></span>".toRegex(), "<img src=\"utility\">")
                attribute = attribute.replace("span class=\"d3-color-ff".toRegex(), "font color=\"#")
                attribute = attribute.replace("span class=\"d3-color-magic".toRegex(), "font color=\"#7979d4")
                secondary.append(attribute.replace("</span>".toRegex(), "</font>")).append("<br>")
            }
            Log.i("Test secondary", secondary.toString())
        } catch (e: Exception) {
            secondary.append("")
        }
        try {
            for (j in items[index].gems.indices) {
                val gemAttributes = StringBuilder()
                if (items[index].gems[j].item.id.contains("Unique")) {
                    gemAttributes.append("<font color=\"#ff8000\"> ")
                }
                for (k in items[index].gems[j].attributes.indices) {
                    gemAttributes.append(" <img src=\"").append(items[index].gems[j].item.icon).append("\">")
                    gemAttributes.append(" <img src=\"primary\"> ")
                    gemAttributes.append(items[index].gems[j].attributes[k].replace("\\n".toRegex(), "<br>")).append("<br>")
                }
                gemAttributes.append("</font>")
                gem.append(gemAttributes)
            }
            if (items[index].openSockets > 0) {
                for (i in 0 until items[index].openSockets.toInt()) {
                    gem.append("<img src=\"empty_socket_d3\"> ").append("Empty Socket<br>")
                }
            }
        } catch (e: Exception) {
            gem.append("")
            Log.e("Error", e.toString())
        }
        primaryStatsMap[index] = primary.toString()
        secondaryStatsMap[index] = secondary.toString()
        gemsMap[index] = gem.toString()
        try {
            setOnPressItemInformation(Objects.requireNonNull(imageViewItem[items[index].slots]), index)
        } catch (e: Exception) {
            Log.e("Item", "empty")
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(imageView: ImageView?, index: Int) {
        imageView!!.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setColor(Color.parseColor("#000000"))
                backgroundStroke.setStroke(8, Color.parseColor(getItemBorderColor(index)))
                item_scroll_view!!.background = backgroundStroke
                item_name!!.background = getHeaderBackground(index)
                val background = selectBackgroundColor(items[index].displayColor)
                val backgroundStrokeTooltipIcon = GradientDrawable()
                backgroundStrokeTooltipIcon.setStroke(3, Color.parseColor(selectColor(items[index].displayColor)))
                backgroundStrokeTooltipIcon.cornerRadius = 5f
                val layers = arrayOfNulls<Drawable>(2)
                layers[0] = background
                layers[1] = backgroundStrokeTooltipIcon
                val layerList = LayerDrawable(layers)
                item_icon!!.background = layerList
                item_icon!!.setImageDrawable(imageView.drawable)
                val jewelleryParams = RelativeLayout.LayoutParams((67 * Resources.getSystem().displayMetrics.density).toInt(),
                        (67 * Resources.getSystem().displayMetrics.density).toInt())
                resources
                jewelleryParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                val normalIconParams = RelativeLayout.LayoutParams((67 * Resources.getSystem().displayMetrics.density).toInt(),
                        (130 * Resources.getSystem().displayMetrics.density).toInt())
                normalIconParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                if (items[index].slots == "neck" || items[index].slots == "leftFinger" || items[index].slots == "rightFinger" || items[index].slots == "waist") {
                    item_icon!!.layoutParams = jewelleryParams
                } else {
                    item_icon!!.layoutParams = normalIconParams
                }
                try {
                    val color = selectColor(items[index].displayColor)
                    item_name!!.text = Html.fromHtml("<font color=\"" + color + "\">" + items[index].name + "</font>", Html.FROM_HTML_MODE_LEGACY)
                    if (items[index].name.length > 23) {
                        item_name!!.textSize = 18f
                    }
                    item_name!!.gravity = Gravity.CENTER_HORIZONTAL
                    item_name!!.gravity = Gravity.CENTER_VERTICAL
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                var typeNameString = items[index].typeName
                if (typeNameString.length > 22) {
                    val lastSpace = typeNameString.lastIndexOf(" ")
                    val beforeLastSpace = typeNameString.substring(0, lastSpace)
                    val lastSpace2 = beforeLastSpace.lastIndexOf(" ")
                    typeNameString = typeNameString.substring(0, lastSpace2) + "<br>" + typeNameString.substring(lastSpace2)
                }
                typeName!!.text = Html.fromHtml(typeNameString, Html.FROM_HTML_MODE_LEGACY)
                typeName!!.setTextColor(Color.parseColor(selectColor(items[index].displayColor)))
                slot!!.text = items[index].slots
                try {
                    if (items[index].armor > 0) {
                        armor!!.text = Html.fromHtml("<big><big><big><big><big>" + Math.round(items[index].armor).toInt() + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>", Html.FROM_HTML_MODE_LEGACY)
                        armor_damage!!.addView(armor, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                try {
                    if (!items[index].type.twoHanded && items[index].minDamage > 0) {
                        slot!!.text = Html.fromHtml("1-Hand", Html.FROM_HTML_MODE_LEGACY)
                    } else if (items[index].type.twoHanded && items[index].minDamage > 0) {
                        slot!!.text = Html.fromHtml("2-Hand", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        slot!!.text = items[index].slots
                    }
                } catch (e: Exception) {
                    Log.e("Error", "No TYPE")
                }
                try {
                    if (items[index].minDamage > 0 && items[index].maxDamage > 0) {
                        val formatter: NumberFormat = DecimalFormat("#0.0")
                        val dpsText = ((items[index].minDamage + items[index].maxDamage) / 2 * items[index].attacksPerSecond * 10 / 10).roundToInt().toDouble()
                        dps!!.text = Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
                                + formatter.format(items[index].minDamage) + " - "
                                + formatter.format(items[index].maxDamage) + "<font color=\"#696969\"> Damage</font><br>"
                                + formatter.format(items[index].attacksPerSecond) + "<font color=\"#696969\"> Attacks per Second</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        armor_damage!!.addView(dps, layoutParamsStats)
                        when (items[index].elementalType) {
                            "fire" -> image_stats?.setBackgroundResource(R.drawable.fire)
                            "cold" -> image_stats?.setBackgroundResource(R.drawable.cold)
                            "holy" -> image_stats?.setBackgroundResource(R.drawable.holy)
                            "poison" -> image_stats?.setBackgroundResource(R.drawable.poison)
                            "lightning" -> image_stats?.setBackgroundResource(R.drawable.lightning)
                            "arcane" -> image_stats?.setBackgroundResource(R.drawable.arcane)
                            else -> image_stats?.setBackgroundResource(0)
                        }
                    } else {
                        image_stats?.setBackgroundResource(0)
                    }
                } catch (e: Exception) {
                    image_stats?.setBackgroundResource(0)
                    dps!!.text = ""
                }
                try {
                    primarystats!!.text = Html.fromHtml("Primary<br>" + primaryStatsMap[index], Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    item_stats!!.addView(primarystats, layoutParamsStats)
                    secondarystats!!.text = Html.fromHtml("Secondary<br>" + secondaryStatsMap[index], Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    item_stats!!.addView(secondarystats, layoutParamsStats)
                    gems!!.text = Html.fromHtml(gemsMap[index], Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    val gemParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    gemParams.setMargins(20, 0, 20, 0)
                    gems!!.gravity = Gravity.CENTER_VERTICAL
                    item_stats!!.addView(gems, gemParams)
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                try {
                    var setText = items[index].set.descriptionHtml
                    var firstPart = setText.substring(0, setText.indexOf("(2)") - 38)
                    Log.i("TEST-2", firstPart)
                    firstPart = firstPart.replace("<br />".toRegex(), "<br />&nbsp;&nbsp;&nbsp;")
                    val lastPart = setText.substring(setText.indexOf("(2)") - 38)
                    setText = firstPart + lastPart
                    setText = setText.replace("<span class=\"tooltip-icon-bullet\"></span>".toRegex(), "&nbsp;&nbsp;<img src=\"primary" +
                            "\">")
                    setText = setText.replace("<span class=\"tooltip-icon-utility\"></span>".toRegex(), "&nbsp;&nbsp;<img src=\"utility\">")
                    setText = setText.replace("<span class=\"d3-color-ff".toRegex(), "<font color=\"#").replace("</span>".toRegex(), "</font>")
                    Log.i("TEST", setText)
                    set!!.text = Html.fromHtml(setText, Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    item_stats!!.addView(set, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                try {
                    transmog!!.text = Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                            + selectColor(items[index].transmog.displayColor) + "\">" + items[index].transmog.name + "</font><br>", Html.FROM_HTML_MODE_LEGACY)
                    item_stats!!.addView(transmog, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                try {
                    if (items[index].flavorText != null) {
                        flavortext!!.text = Html.fromHtml("<font color=\"#9d7853\">\"<i>" + items[index].flavorText + "</i>\"</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        item_stats!!.addView(flavortext, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                try {
                    if (items[index].accountBound) {
                        misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + Math.round(items[index].requiredLevel).toInt() + "<br>Account bound</font>", Html.FROM_HTML_MODE_LEGACY)
                        misctext!!.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    } else {
                        misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + Math.round(items[index].requiredLevel).toInt() + "<br></font>", Html.FROM_HTML_MODE_LEGACY)
                        misctext!!.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    }
                    item_stats!!.addView(misctext, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
                try {
                    (closeButton!!.parent as ViewGroup).removeView(closeButton)
                } catch (e: Exception) {
                    Log.e("Parent", "None")
                }
                item_stats!!.addView(closeButton)
                item_scroll_view!!.visibility = View.VISIBLE
            }
            true
        }
    }

    private val itemInformation: Unit
        get() {
            items.add(itemsInformation!!.shoulders)
            items.add(itemsInformation!!.hands)
            items.add(itemsInformation!!.leftFinger)
            items.add(itemsInformation!!.mainHand)
            items.add(itemsInformation!!.head)
            items.add(itemsInformation!!.torso)
            items.add(itemsInformation!!.waist)
            items.add(itemsInformation!!.legs)
            items.add(itemsInformation!!.feet)
            items.add(itemsInformation!!.neck)
            items.add(itemsInformation!!.bracers)
            items.add(itemsInformation!!.rightFinger)
            items.add(itemsInformation!!.offHand)
        }

    private fun setName() {
        val levelClass = ("<font color=#d4a94e>" + characterInformation!!.level + "</font>" + "<font color=#555da5> (" + characterInformation!!.paragonLevel
                + ")</font> <font color=#d4a94e>" + characterInformation!!.class_)
        level_class!!.text = Html.fromHtml(levelClass, Html.FROM_HTML_MODE_LEGACY)
        if (characterInformation!!.name.length in 8..9) {
            character_name!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
        } else if (characterInformation!!.name.length > 9) {
            character_name!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
            character_name!!.setPadding(0, 10, 0, 0)
        }
        character_name!!.text = characterInformation!!.name
    }

    private val itemIcons: Unit
        get() {
            for (key in itemIconURL.keys) {
                if (itemIconURL[key] != null) {
                    Picasso.get().load(itemIconURL[key]).into(imageViewItem[key])
                }
            }
        }

    private val cubeIcons: Unit
        get() {
            val cubeURL = HashMap<String, String>()
            val armorArray = arrayOf("shoulder", "spirit", "voodoo", "wizardhat", "gloves", "helm", "chest", "cloak", "belt", "pants", "boots", "bracers")
            val ringArray = arrayOf("ring", "amulet")
            try {
                for (i in characterInformation!!.legendaryPowers.indices) {
                    when {
                        Arrays.stream(armorArray).parallel().anyMatch { s: String? -> characterInformation!!.legendaryPowers[i].icon.contains(s!!) } -> {
                            cubeURL["armor"] = URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation!!.legendaryPowers[i].icon + ".png")
                            cubeMap[characterInformation!!.legendaryPowers[i].icon.toLowerCase(Locale.ROOT)] = cube_armor_item
                        }
                        Arrays.stream(ringArray).parallel().anyMatch { s: String? -> characterInformation!!.legendaryPowers[i].icon.contains(s!!) } -> {
                            cubeURL["ring"] = URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation!!.legendaryPowers[i].icon + ".png")
                            cubeMap[characterInformation!!.legendaryPowers[i].icon.toLowerCase(Locale.ROOT)] = cube_ring_item
                        }
                        else -> {
                            cubeURL["sword"] = URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation!!.legendaryPowers[i].icon + ".png")
                            cubeMap[characterInformation!!.legendaryPowers[i].icon.toLowerCase(Locale.ROOT)] = cube_sword_item
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Power", "None")
            }
            for (key in cubeURL.keys) {
                when (key) {
                    "sword" -> {
                        cube_sword_item!!.visibility = View.VISIBLE
                        Picasso.get().load(cubeURL[key]).into(cube_sword_item)
                    }
                    "armor" -> {
                        cube_armor_item!!.visibility = View.VISIBLE
                        Picasso.get().load(cubeURL[key]).into(cube_armor_item)
                    }
                    "ring" -> {
                        cube_ring_item!!.visibility = View.VISIBLE
                        Picasso.get().load(cubeURL[key]).into(cube_ring_item)
                    }
                }
            }
        }

    private fun addImageViewItemsToList() {
        imageViewItem["shoulders"] = shoulder
        imageViewItem["hands"] = gloves
        imageViewItem["leftFinger"] = ring1
        imageViewItem["mainHand"] = main_hand
        imageViewItem["head"] = head
        imageViewItem["torso"] = chest
        imageViewItem["waist"] = belt
        imageViewItem["legs"] = legs
        imageViewItem["feet"] = boots
        imageViewItem["neck"] = amulet
        imageViewItem["bracers"] = bracers
        imageViewItem["rightFinger"] = ring2
        imageViewItem["offHand"] = off_hand
    }

    private fun setItemBackgroundColor() {
        for (i in 0 until imageViewItem.size) {
            try {
                val background = selectBackgroundColor(items[i].displayColor)
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setStroke(3, Color.parseColor(selectColor(items[i].displayColor)))
                backgroundStroke.cornerRadius = 5f
                val layers = arrayOfNulls<Drawable>(2)
                layers[0] = background
                layers[1] = backgroundStroke
                val layerList = LayerDrawable(layers)
                imageViewItem[items[i].slots]!!.background = layerList
            } catch (e: Exception) {
                Log.e("Item", "empty")
            }
        }
    }

    private fun selectBackgroundColor(color: String): Drawable {
        when (color) {
            "blue" -> return requireContext().getDrawable(R.drawable.blue_bg_item_d3)!!
            "yellow" -> return requireContext().getDrawable(R.drawable.yellow_bg_item_d3)!!
            "orange" -> return requireContext().getDrawable(R.drawable.orange_bg_item_d3)!!
            "green" -> return requireContext().getDrawable(R.drawable.green_bg_item_d3)!!
            "white" -> return requireContext().getDrawable(R.drawable.brown_bg_item_d3)!!
            else -> {
            }
        }
        return requireContext().getDrawable(R.drawable.brown_bg_item_d3)!!
    }

    private fun selectColor(color: String): String {
        when (color) {
            "blue" -> return "#7979d4"
            "yellow" -> return "#ffff00"
            "orange" -> return "#bf642f"
            "green" -> return "#00ff00"
            "white" -> return "#FFFFFF"
            else -> {
            }
        }
        return "#FFFFFF"
    }

    private fun getHeaderBackground(index: Int): Drawable {
        when {
            items[index].typeName.contains("Primal Legendary") -> {
                return requireContext().getDrawable(R.drawable.d3_item_header_legendary_primal)!!
            }
            items[index].typeName.contains("Primal Set") -> {
                return requireContext().getDrawable(R.drawable.d3_item_header_legendary_primal)!!
            }
            items[index].typeName.contains("Set") -> {
                return requireContext().getDrawable(R.drawable.d3_item_header_set)!!
            }
            items[index].typeName.contains("Legendary") -> {
                return requireContext().getDrawable(R.drawable.d3_item_header_legendary)!!
            }
            items[index].typeName.contains("Rare") -> {
                return requireContext().getDrawable(R.drawable.d3_item_header_rare)!!
            }
            items[index].typeName.contains("Magic") -> {
                return requireContext().getDrawable(R.drawable.d3_item_header_magic)!!
            }
            else -> return requireContext().getDrawable(R.drawable.d3_item_header)!!
        }
    }

    private fun getItemBorderColor(index: Int): String {
        val type = items[index].typeName
        if (type.contains("Ancient")) {
            return "#b47402"
        } else if (type.contains("Primal")) {
            return "#E52817"
        }
        return "#312a26"
    }

    private fun getItemIconURL() {
        for (i in items.indices) {
            try {
                itemIconURL[items[i].slots] = URLConstants.D3_ICON_ITEMS.replace("icon.png", items[i].icon) + ".png"
            } catch (e: Exception) {
                Log.e("Error", e.toString())
                itemIconURL["empty"] = null
            }
        }
    }

    override fun onBackPressed(): Boolean {
        return if (URLConstants.loading) {
            true
        } else {
            if (item_scroll_view!!.visibility == View.VISIBLE || skill_tooltip_scroll!!.visibility == View.VISIBLE) {
                closeViewsWithoutButton()
                true
            } else {
                false
            }
        }
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        val btn2 = AtomicBoolean(false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loading_circle!!.visibility = View.GONE
        URLConstants.loading = false
        if (dialog == null) {
            val builder = AlertDialog.Builder(requireContext(), R.style.DialogTransparent)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0, 20, 0, 0)
            val titleText = TextView(context)
            titleText.textSize = 20f
            titleText.gravity = Gravity.CENTER_HORIZONTAL
            titleText.setPadding(0, 20, 0, 20)
            titleText.layoutParams = layoutParams
            titleText.setTextColor(Color.WHITE)
            val messageText = TextView(context)
            messageText.gravity = Gravity.CENTER_HORIZONTAL
            messageText.layoutParams = layoutParams
            messageText.setTextColor(Color.WHITE)
            val button = Button(context)
            button.textSize = 18f
            button.setTextColor(Color.WHITE)
            button.gravity = Gravity.CENTER
            button.width = 200
            button.layoutParams = layoutParams
            button.background = requireContext().getDrawable(R.drawable.buttonstyle)
            val button2 = Button(context)
            button2.textSize = 18f
            button2.setTextColor(Color.WHITE)
            button2.gravity = Gravity.CENTER
            button2.width = 200
            button2.layoutParams = layoutParams
            button2.background = requireContext().getDrawable(R.drawable.buttonstyle)
            val buttonLayout = LinearLayout(context)
            buttonLayout.orientation = LinearLayout.HORIZONTAL
            buttonLayout.gravity = Gravity.CENTER
            buttonLayout.addView(button)
            when (responseCode) {
                in 400..499 -> {
                    titleText.text = ErrorMessages.INFORMATION_OUTDATED
                    messageText.text = ErrorMessages.LOGIN_TO_UPDATE
                    button.text = ErrorMessages.BACK
                }
                500 -> {
                    titleText.text = ErrorMessages.SERVERS_ERROR
                    messageText.text = ErrorMessages.BLIZZ_SERVERS_DOWN
                    button.text = ErrorMessages.BACK
                }
                else -> {
                    titleText.text = ErrorMessages.NO_INTERNET
                    messageText.text = ErrorMessages.TURN_ON_CONNECTION_MESSAGE
                    button.text = ErrorMessages.RETRY
                    button2.text = ErrorMessages.BACK
                    buttonLayout.addView(button2)
                }
            }
            dialog = builder.show()
            Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog?.window?.setLayout(800, 500)
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.gravity = Gravity.CENTER
            linearLayout.addView(titleText)
            linearLayout.addView(messageText)
            linearLayout.addView(buttonLayout)
            val layoutParamsWindow = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(20, 20, 20, 20)
            dialog?.addContentView(linearLayout, layoutParamsWindow)
            dialog?.setOnCancelListener {
                if (btn2.get()) {
                    parentFragmentManager.beginTransaction().remove(this@D3CharacterFragment).commit()
                } else {
                    if (responseCode == 0) {
                        val fragment = parentFragmentManager.findFragmentById(R.id.fragment) as D3CharacterFragment?
                        parentFragmentManager.beginTransaction()
                                .detach(Objects.requireNonNull(fragment)!!)
                                .attach(fragment!!)
                                .commit()
                    } else {
                        parentFragmentManager.beginTransaction().remove(this@D3CharacterFragment).commit()
                    }
                }
            }
            button.setOnClickListener { dialog?.cancel() }
            button2.setOnClickListener {
                btn2.set(true)
                dialog?.cancel()
            }
        }
    }
}
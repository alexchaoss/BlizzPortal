package com.BlizzardArmory.ui.ui_warcraft

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.text.Html.ImageGetter
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.activity.WoWActivity
import com.BlizzardArmory.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.warcraft.equipment.Equipment
import com.BlizzardArmory.warcraft.equipment.EquippedItem
import com.BlizzardArmory.warcraft.equipment.Set
import com.BlizzardArmory.warcraft.equipment.Socket
import com.BlizzardArmory.warcraft.media.Media
import com.BlizzardArmory.warcraft.statistic.Statistic
import com.BlizzardArmory.warcraft.talents.Talent
import com.BlizzardArmory.warcraft.talents.Talents
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.wow_character_fragment.*
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class WoWCharacterFragment : Fragment(), IOnBackPressed {
    private var characterRealm: String? = null
    private var characterClicked: String? = null
    private var region: String? = null
    private var dialog: AlertDialog? = null

    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private lateinit var networkServices: NetworkServices

    //Character information
    private lateinit var characterSummary: CharacterSummary
    private var media: Media? = null
    private lateinit var statistic: Statistic
    private lateinit var talentsInfo: Talents
    private lateinit var equipment: Equipment
    private var nameView: TextView? = null
    private var statsView: TextView? = null
    private val imageURLs = HashMap<String, String>()

    //Talents
    private var talentsTierContainer: List<TextView?>? = null
    private var talentsContainer: List<TextView?>? = null
    private var talentsTier: List<String>? = null
    private val talents: MutableList<Talent> = ArrayList()
    private var specs: TabLayout? = null
    private var spec: TextView? = null
    private var fifteen: TextView? = null
    private var thirty: TextView? = null
    private var forty_five: TextView? = null
    private var sixty: TextView? = null
    private var seventy_five: TextView? = null
    private var ninety: TextView? = null
    private var hundred: TextView? = null
    private var fifteenTalent: TextView? = null
    private var thirtyTalent: TextView? = null
    private var forty_fiveTalent: TextView? = null
    private var sixtyTalent: TextView? = null
    private var seventy_fiveTalent: TextView? = null
    private var ninetyTalent: TextView? = null
    private var hundredTalent: TextView? = null
    private val gearImageView = HashMap<String, ImageView>()
    private val stats = HashMap<String, String>()
    private val nameList = HashMap<String, String>()
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        URLConstants.loading = true
        val bundle = requireArguments()
        characterRealm = bundle.getString("realm")
        characterClicked = bundle.getString("character")
        if (Gson().fromJson(bundle.getString("media"), Media::class.java) != null) {
            media = Gson().fromJson(bundle.getString("media"), Media::class.java)
        }
        region = bundle.getString("region")

        gson = GsonBuilder().create()
        retrofit = Retrofit.Builder().baseUrl(URLConstants.getBaseURLforAPI(MainActivity.selectedRegion)).addConverterFactory(GsonConverterFactory.create(gson!!)).build()
        networkServices = retrofit?.create(NetworkServices::class.java)!!

        item_scroll_view?.setPadding(10, 10, 10, 10)
        val linearLayoutItemStats = LinearLayout(view.context)
        linearLayoutItemStats.orientation = LinearLayout.VERTICAL
        linearLayoutItemStats.gravity = Gravity.CENTER
        item_stats.addView(linearLayoutItemStats)
        statsView = TextView(view.context)
        nameView = TextView(view.context)
        linearLayoutItemStats.addView(nameView)
        linearLayoutItemStats.addView(statsView)
        loading_circle?.visibility = View.VISIBLE
        spec = view.findViewById(R.id.specialization)
        specs = view.findViewById(R.id.tabLayout)
        no_talent?.visibility = View.INVISIBLE
        fifteen = view.findViewById(R.id.fifteen)
        thirty = view.findViewById(R.id.thirty)
        forty_five = view.findViewById(R.id.forty_five)
        sixty = view.findViewById(R.id.sixty)
        seventy_five = view.findViewById(R.id.seventy_five)
        ninety = view.findViewById(R.id.ninety)
        hundred = view.findViewById(R.id.hundred)
        fifteenTalent = view.findViewById(R.id.fifteenTalent)
        thirtyTalent = view.findViewById(R.id.thirtyTalent)
        forty_fiveTalent = view.findViewById(R.id.forty_fiveTalent)
        sixtyTalent = view.findViewById(R.id.sixtyTalent)
        seventy_fiveTalent = view.findViewById(R.id.seventy_fiveTalent)
        ninetyTalent = view.findViewById(R.id.ninetyTalent)
        hundredTalent = view.findViewById(R.id.hundredTalent)
        activateCloseButton()
        val layoutParamsName = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsStats = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParamsName.setMargins(20, 20, 20, -5)
        nameView?.layoutParams = layoutParamsName
        layoutParamsStats.setMargins(20, 0, 20, 0)
        statsView?.layoutParams = layoutParamsStats
        val head = view.findViewById<ImageView>(R.id.head)
        val neck = view.findViewById<ImageView>(R.id.neck)
        val shoulder = view.findViewById<ImageView>(R.id.shoulder)
        val back = view.findViewById<ImageView>(R.id.back)
        val chest = view.findViewById<ImageView>(R.id.chest)
        val shirt = view.findViewById<ImageView>(R.id.shirt)
        val tabard = view.findViewById<ImageView>(R.id.tabard)
        val wrist = view.findViewById<ImageView>(R.id.wrist)
        val hands = view.findViewById<ImageView>(R.id.hands)
        val waist = view.findViewById<ImageView>(R.id.waist)
        val legs = view.findViewById<ImageView>(R.id.legs)
        val feet = view.findViewById<ImageView>(R.id.feet)
        val finger1 = view.findViewById<ImageView>(R.id.finger1)
        val finger2 = view.findViewById<ImageView>(R.id.finger2)
        val trinket1 = view.findViewById<ImageView>(R.id.trinket1)
        val trinket2 = view.findViewById<ImageView>(R.id.trinket2)
        val mainHand = view.findViewById<ImageView>(R.id.main_hand)
        val offHand = view.findViewById<ImageView>(R.id.off_hand)
        gearImageView["HEAD"] = head
        gearImageView["NECK"] = neck
        gearImageView["SHOULDER"] = shoulder
        gearImageView["BACK"] = back
        gearImageView["CHEST"] = chest
        gearImageView["SHIRT"] = shirt
        gearImageView["TABARD"] = tabard
        gearImageView["WRIST"] = wrist
        gearImageView["HANDS"] = hands
        gearImageView["WAIST"] = waist
        gearImageView["LEGS"] = legs
        gearImageView["FEET"] = feet
        gearImageView["FINGER_1"] = finger1
        gearImageView["FINGER_2"] = finger2
        gearImageView["TRINKET_1"] = trinket1
        gearImageView["TRINKET_2"] = trinket2
        gearImageView["MAIN_HAND"] = mainHand
        gearImageView["OFF_HAND"] = offHand
        val startTime = System.nanoTime()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Params: BnOAuth2Params = requireActivity().intent.extras!!.getParcelable(BnConstants.BUNDLE_BNPARAMS)!!
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)
        downloadInfo()
        val endTime = System.nanoTime()
        val duration = (endTime - startTime) / 1000000000
        Log.i("time", duration.toString())
    }

    private fun downloadInfo() {
        downloadBackground()
        downloadAndSetCharacterInformation()
        downloadStats()
        downloadTalents()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun activateCloseButton() {
        close_button.setOnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.performClick()
                Log.i("CLOSE", "CLICKED")
                item_scroll_view?.visibility = View.GONE
            }
            false
        }
    }

    private fun downloadTalents() {
        val call: Call<Talents> = networkServices.getSpecs(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), "profile-" + region!!.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Talents> {
            override fun onResponse(call: Call<Talents>, response: retrofit2.Response<Talents>) {
                if (response.isSuccessful) {
                    talentsInfo = response.body()!!
                    if (talentsInfo == null) {
                        no_talent?.visibility = View.VISIBLE
                        no_talent?.text = "Talent information outdated"
                    } else {
                        setTalentInformation()
                    }
                }
            }

            override fun onFailure(call: Call<Talents>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                no_talent?.visibility = View.VISIBLE
                no_talent?.text = "Talent information outdated"
            }
        })
    }

    private fun downloadStats() {
        val call: Call<Statistic> = networkServices.getStats(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), "profile-" + region!!.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Statistic> {
            override fun onResponse(call: Call<Statistic>, response: retrofit2.Response<Statistic>) {
                when {
                    response.code() in 201..1000 -> {
                        callErrorAlertDialog(response.code())
                    }
                    else -> {
                        statistic = response.body()!!
                        setCharacterInformationTextviews()
                    }
                }
            }

            override fun onFailure(call: Call<Statistic>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun downloadAndSetCharacterInformation() {
        val call: Call<CharacterSummary> = networkServices.getCharacter(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), "profile-" + region!!.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<CharacterSummary> {
            override fun onResponse(call: Call<CharacterSummary>, response: retrofit2.Response<CharacterSummary>) {
                when {
                    response.code() in 201..1000 -> {
                        callErrorAlertDialog(response.code())
                    }
                    else -> {
                        characterSummary = response.body()!!
                        character_name.text = characterSummary.name
                        EventBus.getDefault().post(FactionEvent(characterSummary.faction.name.toLowerCase(Locale.ROOT)))
                        EventBus.getDefault().post(ClassEvent(characterSummary.characterClass.id))
                        item_lvl.text = String.format("Item Level : %s", characterSummary.equippedItemLevel)
                        val levelRaceClassString = characterSummary.level.toString() + " " + characterSummary.getRace().name + " " + characterSummary.getCharacterClass().name
                        level_race_class.text = levelRaceClassString
                        downloadEquipment()
                    }
                }
            }

            override fun onFailure(call: Call<CharacterSummary>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun downloadEquipment() {
        val call2: Call<Equipment> = networkServices.getEquippedItems(characterClicked!!.toLowerCase(Locale.ROOT),
                characterRealm!!.toLowerCase(Locale.ROOT), "profile-" + region!!.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call2.enqueue(object : Callback<Equipment> {
            override fun onResponse(call: Call<Equipment>, response: retrofit2.Response<Equipment>) {
                when {
                    response.code() in 400..1000 -> {
                        callErrorAlertDialog(response.code())
                    }
                    else -> {
                        equipment = response.body()!!
                        for (i in equipment.equippedItems.indices) {
                            try {
                                downloadIcons(equipment, i, equipment.equippedItems[i].slot.type)
                            } catch (e: Exception) {
                                break
                            }
                            setCharacterItemsInformation(i)
                        }
                        for (slot in gearImageView.keys) {
                            var slotEquipped = false
                            for (i in equipment.equippedItems.indices) {
                                if (slot == equipment.equippedItems[i].slot.type) {
                                    slotEquipped = true
                                }
                            }
                            if (!slotEquipped) {
                                try {
                                    getEmptySlotIcon(slot)
                                } catch (e: Exception) {
                                    Log.e("Drawable", "Avoided crash")
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Equipment>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                callErrorAlertDialog(0)
            }
        })
    }

    private fun downloadBackground() {
        if (media != null) {
            Picasso.get().load(media?.renderUrl).into(background)
        } else {
            val call: Call<Media> = networkServices.getMedia(characterClicked!!.toLowerCase(Locale.ROOT),
                    characterRealm!!.toLowerCase(Locale.ROOT), "profile-" + region!!.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
            call.enqueue(object : Callback<Media> {
                override fun onResponse(call: Call<Media>, response: retrofit2.Response<Media>) {
                    if (response.isSuccessful) {
                        media = response.body()!!
                        Picasso.get().load(media?.renderUrl).into(background)
                    }
                }

                override fun onFailure(call: Call<Media>, t: Throwable) {
                    Log.e("Error", t.localizedMessage)
                    callErrorAlertDialog(0)
                }
            })
        }
    }

    private fun downloadIcons(equipment: Equipment?, index: Int, itemSlot: String) {
        var url = equipment?.equippedItems?.get(index)?.media?.key?.href
        if (url!!.contains("static")) {
            url = url.replace(("static-[0-9].[0-9].[0-9]_[0-9]*-" + region?.toLowerCase(Locale.ROOT)?.toRegex()).toRegex(), "static-" + region?.toLowerCase(Locale.ROOT))
            Log.i("IMAGE", url)
        }
        val call: Call<com.BlizzardArmory.warcraft.equipment.media.Media> = networkServices.getDynamicEquipmentMedia(url, MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<com.BlizzardArmory.warcraft.equipment.media.Media> {
            override fun onResponse(call: Call<com.BlizzardArmory.warcraft.equipment.media.Media>, response: retrofit2.Response<com.BlizzardArmory.warcraft.equipment.media.Media>) {
                when {
                    response.code() in 201..1000 -> {
                        if (index == equipment?.equippedItems!!.size - 1) {
                            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                            loading_circle?.visibility = View.GONE
                            URLConstants.loading = false
                        }
                        val errorIcon = resources.getDrawable(R.drawable.error_icon, requireContext().theme)
                        setIcons(itemSlot, equipment, errorIcon)
                    }
                    response.code() < 200 -> {
                        if (index == equipment?.equippedItems!!.size - 1) {
                            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                            loading_circle?.visibility = View.GONE
                            URLConstants.loading = false
                        }
                        val errorIcon = resources.getDrawable(R.drawable.error_icon, requireContext().theme)
                        setIcons(itemSlot, equipment, errorIcon)
                    }
                    else -> {
                        val mediaItem = response.body()!!
                        imageURLs[itemSlot] = mediaItem.assets[0].value
                        Log.i("Icon URL", mediaItem.assets[0].value)
                        Picasso.get().load(imageURLs[itemSlot]).into(gearImageView[itemSlot])
                        setIcons(itemSlot, equipment, null)
                        if (index == equipment?.equippedItems!!.size - 1) {
                            loading_circle?.visibility = View.GONE
                            URLConstants.loading = false
                        }
                    }
                }
            }

            override fun onFailure(call: Call<com.BlizzardArmory.warcraft.equipment.media.Media>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                if (index == equipment?.equippedItems!!.size - 1) {
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    loading_circle?.visibility = View.GONE
                    URLConstants.loading = false
                }
                val errorIcon = resources.getDrawable(R.drawable.error_icon, requireContext().theme)
                setIcons(itemSlot, equipment, errorIcon)
            }
        })
    }

    private fun setIcons(itemSlot: String, equipment: Equipment?, item: Drawable?) {
        if (item != null) {
            Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(item)
        }
        Objects.requireNonNull(gearImageView[itemSlot])?.setPadding(3, 3, 3, 3)
        Objects.requireNonNull(gearImageView[itemSlot])?.clipToOutline = true
        var backgroundStroke: Drawable? = null
        for (i in equipment?.equippedItems?.indices!!) {
            if (equipment.equippedItems[i].slot.type == itemSlot) {
                backgroundStroke = equipment.equippedItems?.get(i)?.let { itemColor(it, GradientDrawable()) }
            }
        }
        Objects.requireNonNull(gearImageView[itemSlot])?.background = backgroundStroke
    }

    private fun replaceZoneRealmCharacterNameURL(url: String): String {
        var newURL = url
        newURL = if (region == null) {
            newURL.replace("zone", URLConstants.getRegion())
        } else {
            newURL.replace("zone", region!!.toLowerCase(Locale.ROOT))
        }
        newURL = newURL.replace("realm", characterRealm!!)
        newURL = newURL.replace("characterName", characterClicked!!.toLowerCase(Locale.ROOT))
        return newURL
    }

    private fun getEmptySlotIcon(itemSlot: String) {
        Objects.requireNonNull(gearImageView[itemSlot])?.setPadding(3, 3, 3, 3)
        Objects.requireNonNull(gearImageView[itemSlot])?.clipToOutline = true
        val backgroundStroke = GradientDrawable()
        backgroundStroke.cornerRadius = 3f
        backgroundStroke.setColor(Color.parseColor("#000000"))
        backgroundStroke.setStroke(3, Color.GRAY)
        Objects.requireNonNull(gearImageView[itemSlot])?.background = backgroundStroke
        when (itemSlot) {
            "HEAD" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_head))
            "NECK" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_neck))
            "SHOULDER" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_shoulders))
            "CHEST", "BACK" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_chest))
            "SHIRT" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_shirt))
            "TABARD" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_tabard))
            "WRIST" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_wrist))
            "HANDS" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_hands))
            "WAIST" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_waist))
            "LEGS" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_legs))
            "FEET" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_feet))
            "FINGER_1", "FINGER_2" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_ring))
            "TRINKET_1", "TRINKET_2" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_trinket))
            "MAIN_HAND" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_main_hand))
            "OFF_HAND" -> Objects.requireNonNull(gearImageView[itemSlot])?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.empty_shield))
        }
    }

    private fun itemColor(equippedItem: EquippedItem, drawable: GradientDrawable): Drawable {
        drawable.cornerRadius = 3f
        drawable.setColor(Color.parseColor("#000000"))
        drawable.setStroke(3, getItemColor(equippedItem))
        return drawable
    }

    private fun getItemColor(item: EquippedItem): Int {
        try {
            when (item.quality.type) {
                "POOR" -> return Color.parseColor("#9d9d9d")
                "COMMON" -> return Color.parseColor("#ffffff")
                "UNCOMMON" -> return Color.parseColor("#1eff00")
                "RARE" -> return Color.parseColor("#0070dd")
                "EPIC" -> return Color.parseColor("#a335ee")
                "LEGENDARY" -> return Color.parseColor("#ff8000")
                "ARTIFACT" -> return Color.parseColor("#e6ca80")
                "HEIRLOOM" -> return Color.parseColor("#01c5f7")
            }
        } catch (e: Exception) {
            return Color.GRAY
        }
        return 0
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(imageView: ImageView?, index: Int) {
        imageView?.setOnTouchListener { _: View?, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                nameView?.text = nameList[equipment.equippedItems[index].slot.type]
                nameView?.setTextColor(getItemColor(equipment.equippedItems[index]))
                nameView?.textSize = 20f
                statsView?.text = Html.fromHtml(stats[equipment.equippedItems[index].slot.type], Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
                    val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                    val drawable = resources.getDrawable(resourceId, requireContext().theme)
                    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                    drawable
                }, null)
                statsView?.setTextColor(Color.WHITE)
                statsView?.textSize = 13f
                item_scroll_view?.setPadding(10, 10, 10, 10)
                item_scroll_view?.background = imageView.background
                item_scroll_view?.visibility = View.VISIBLE
                item_scroll_view?.isVerticalScrollBarEnabled = true
                return@setOnTouchListener true
            } else if (item_scroll_view?.visibility == View.VISIBLE && event.action == MotionEvent.ACTION_DOWN) {
                item_scroll_view?.visibility = View.GONE
            }
            return@setOnTouchListener true
        }
        scrollView3?.setOnScrollChangeListener { _: View?, _: Int, scrollY: Int, _: Int, oldScrollY: Int ->
            if (oldScrollY != scrollY) {
                item_scroll_view?.visibility = View.GONE
            }
        }
    }

    private fun setTalentInformation() {
        for (i in talentsInfo.specializations.indices) {
            if (talentsInfo.activeSpecialization.name == talentsInfo.specializations[i].specialization.name) {
                try {
                    talents.addAll(talentsInfo.specializations[i].talents)
                } catch (e: Exception) {
                    talents.add(Talent())
                }
                spec?.text = String.format("Specialization: %s", talentsInfo.activeSpecialization.name)
            }
        }
        if (talentsInfo.specializations.size == 4) {
            specs?.addTab(specs!!.newTab())
            specs?.getTabAt(3)?.text = talentsInfo.specializations[3].specialization.name
        }
        for (i in talentsInfo.specializations.indices) {
            val tab = specs?.getTabAt(i)
            Objects.requireNonNull(tab)?.text = talentsInfo.specializations[i].specialization.name
            Log.i("spec", talentsInfo.specializations[i].specialization.name)
        }
        if (talentsInfo.specializations.size == 2) {
            val tab3 = specs?.getTabAt(2)
            tab3!!.text = "Unavailable"
        } else if (talentsInfo.specializations.size == 1) {
            val tab2 = specs?.getTabAt(1)
            tab2!!.text = "Unavailable"
            val tab3 = specs?.getTabAt(2)
            tab3!!.text = "Unavailable"
        }
        talentsTierContainer = ArrayList(listOf(fifteen, thirty, forty_five, sixty, seventy_five, ninety, hundred))
        talentsContainer = ArrayList(listOf(fifteenTalent, thirtyTalent, forty_fiveTalent, sixtyTalent, seventy_fiveTalent, ninetyTalent, hundredTalent))
        talentsTier = ArrayList(listOf("15", "30", "45", "60", "75", "90", "100"))
        try {
            if (talents.size > 0) {
                for (i in talents.indices) {
                    no_talent?.visibility = View.GONE
                    (talentsTierContainer as ArrayList<TextView?>)[i]?.gravity = Gravity.CENTER
                    (talentsTierContainer as ArrayList<TextView?>)[i]?.text = (talentsTier as ArrayList<String>)[i]
                    if (talents[i].talent != null) {
                        no_talent?.visibility = View.GONE
                        (talentsContainer as ArrayList<TextView?>)[i]?.text = talents[i].talent.name
                    } else {
                        removeTalents()
                        no_talent?.visibility = View.VISIBLE
                    }
                }
            } else {
                removeTalents()
                no_talent?.visibility = View.VISIBLE
            }
        } catch (e: NullPointerException) {
            Log.e("Talent-Error", e.toString())
            no_talent?.visibility = View.VISIBLE
        }
        specs?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> if (tab.text != "Unavailable") {
                        no_talent?.visibility = View.GONE
                        getTalentsForSpecificSpec(0)
                    } else {
                        removeTalents()
                        no_talent?.visibility = View.VISIBLE
                    }
                    1 -> if (tab.text != "Unavailable") {
                        no_talent?.visibility = View.GONE
                        getTalentsForSpecificSpec(1)
                    } else {
                        removeTalents()
                        no_talent?.visibility = View.VISIBLE
                    }
                    2 -> if (tab.text != "Unavailable") {
                        no_talent?.visibility = View.GONE
                        getTalentsForSpecificSpec(2)
                    } else {
                        removeTalents()
                        no_talent?.visibility = View.VISIBLE
                    }
                    3 -> if (tab.text != "Unavailable") {
                        no_talent?.visibility = View.GONE
                        getTalentsForSpecificSpec(3)
                    } else {
                        removeTalents()
                        no_talent?.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun getTalentsForSpecificSpec(position: Int) {
        if (talentsInfo.specializations[position].talents == null) {
            removeTalents()
            no_talent?.visibility = View.VISIBLE
        } else {
            talents.clear()
            talents.addAll(talentsInfo.specializations[position].talents)
            no_talent?.visibility = View.GONE
            for (i in talents.indices) {
                talentsTierContainer!![i]?.gravity = Gravity.CENTER
                talentsTierContainer!![i]?.text = talentsTier?.get(i)
                talentsContainer!![i]?.text = talents[i].talent.name
            }
        }
    }

    private fun removeTalents() {
        for (i in talentsContainer!!.indices) {
            talentsTierContainer!![i]?.text = ""
            talentsContainer!![i]?.text = ""
        }
    }

    private fun setCharacterItemsInformation(index: Int) {
        Log.i("TEST", "item info")
        val equippedItem = equipment.equippedItems[index]
        nameList[equippedItem.slot.type] = equippedItem.name
        var slot = StringBuilder(equippedItem.inventoryType.name)
        val itemLvl = "<font color=#edc201>" + equippedItem.level.displayString + "</font><br>"
        var nameDescription = ""
        var damageInfo = StringBuilder()
        var durability = ""
        var transmog = ""
        var armor = ""
        var requiredLevel = ""
        var description = ""
        var trigger = ""
        var HoALevel = ""
        var bind = ""
        val itemSubclass: String
        val enchant = StringBuilder()
        val setInfo = StringBuilder()
        var socketBonus = ""
        val sockets = StringBuilder()
        val statsString = StringBuilder()
        var azeriteSpells = StringBuilder()
        val sellPrice = StringBuilder()
        try {
            assert(equippedItem.set != null)
            setInfo.append("<font color=#edc201>").append(equippedItem.set.displayString).append("</font><br>")
            setInfo.append(formatSetItemText(equippedItem.set))
        } catch (e: Exception) {
            setInfo.replace(0, setInfo.length, "")
            Log.e("set info", "none")
        }
        try {
            sellPrice.append(equippedItem.sellPrice.displayStrings.header).append(" ")
            if (equippedItem.sellPrice.displayStrings.gold != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.gold).append(" <img src=\"gold\">")
            }
            if (equippedItem.sellPrice.displayStrings.silver != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.silver).append(" <img src=\"silver\">")
            }
            if (equippedItem.sellPrice.displayStrings.copper != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.copper).append(" <img src=\"copper\">")
            }
        } catch (e: Exception) {
            Log.e("Sell price", "none")
        }
        try {
            durability = equippedItem.durability.displayString
        } catch (e: Exception) {
            Log.e("Durability", "none")
        }
        try {
            for (enchantment in equippedItem.enchantments) {
                if (enchantment.displayString != null) {
                    enchant.append("<font color=#00ff00>").append(enchantment.displayString).append("</font><br>")
                }
            }
        } catch (e: Exception) {
            Log.e("Enchant", "none")
        }
        try {
            itemSubclass = equippedItem.itemSubclass.name
            var length = slot.length + itemSubclass.length
            while (length < 43) {
                slot.append("&nbsp;")
                length++
            }
            var lengthSubClass = itemSubclass.length
            while (lengthSubClass < 14) {
                slot.append("&nbsp;")
                lengthSubClass++
            }
            Log.i("length", "" + slot.length)
            if (lengthSubClass + length == 60 && itemSubclass == "Miscellaneous") {
                slot = StringBuilder(slot.substring(0, slot.length - 12))
            }
            slot.append(itemSubclass).append("<br>")
        } catch (e: Exception) {
            Log.e("Item sub class", "none")
        }
        try {
            transmog = "<font color=#f57bf5>" + equippedItem.transmog.displayString.replace("\n", "<br>") + "</font><br>"
        } catch (e: Exception) {
            Log.e("Transmog", "none")
        }
        try {
            armor = if (equippedItem.armor.displayString == null) {
                "<font color=#" + rbgToHexHTML(equippedItem.armor.display.color) + ">" + equippedItem.armor.display.displayString + "</font><br>"
            } else {
                equippedItem.armor.displayString + "<br>"
            }
        } catch (e: Exception) {
            Log.e("Armor", "none")
        }
        try {
            requiredLevel = equippedItem.requirements.level.displayString
        } catch (e: Exception) {
            Log.e("Level", "none")
        }

        if (equippedItem.description != null) {
            description = "<font color=#edc201>" + equippedItem.description + "</font>"
        }

        if (equippedItem.nameDescription != "") {
            Log.i("name descript", equippedItem.nameDescription)
            nameDescription = "<font color=#00ff00>" + equippedItem.nameDescription + "</font><br>"
        } else {
            try {
                Log.i("name descript obj", equippedItem.nameDescriptionObject.displayString)
                nameDescription = "<font color=#" + getNameDescriptionColor(equippedItem) + ">" + equippedItem.nameDescriptionObject.displayString + "</font><br>"
            } catch (e: Exception) {
                Log.e("Error", e.toString())
                Log.e("Name description", "none")
            }
        }
        try {
            trigger = if (equippedItem.spells[0].color != null) {
                "<font color=#" + rbgToHexHTML(equippedItem.spells[0].color) + ">" + equippedItem.spells[0].description + "</font>"
            } else {
                "<font color=#00ff00>" + equippedItem.spells[0].description + "</font>"
            }
        } catch (e: Exception) {
            Log.e("trigger", "none")
        }
        try {
            if (equippedItem.item.id == 158075) {
                azeriteSpells.append("<br>")
                for (selectedEssence in equippedItem.azeriteDetails.selectedEssences) {
                    val essenceImage = "<img src=\"" + getEssenceImage(selectedEssence.slot) + "\"> "
                    val color = "<font color=#" + getEssenceRankColor(selectedEssence.rank) + ">"
                    azeriteSpells.append(essenceImage).append(color).append(selectedEssence.essence.name).append("</font><br>")
                }
            } else {
                azeriteSpells = StringBuilder("<br><font color=#edc201>" + equippedItem.azeriteDetails.selectedPowersString + "</font><br>")
                for (selectedPower in equippedItem.azeriteDetails.selectedPowers) {
                    if (!selectedPower.isIsDisplayHidden) {
                        azeriteSpells.append("- ").append(selectedPower.spellTooltip.spell.name).append("<br>")
                        azeriteSpells.append("<font color=#00ff00>").append(selectedPower.spellTooltip.description).append("</font><br>")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Azerite", "none")
        }
        try {
            for (currentSocket in equippedItem.sockets) {
                if (currentSocket.displayString == null) {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket)).append("\"> ")
                    sockets.append(currentSocket.socketType.name).append("<br>")
                } else {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket)).append("\"> ")
                    sockets.append(currentSocket.displayString).append("<br>")
                }
            }
        } catch (e: Exception) {
            Log.e("Socket", "none")
        }
        try {
            if (equippedItem.socketBonus != null) {
                socketBonus = "<font color=#00ff00>" + equippedItem.socketBonus + "</font><br>"
            }
        } catch (e: Exception) {
            Log.e("socket bonus", "none")
        }
        try {
            bind = equippedItem.binding.name + "<br>"
        } catch (e: Exception) {
            Log.e("bind", "none")
        }
        if (equippedItem.item.id == 158075) {
            HoALevel = "<font color=#edc201>" + equippedItem.azeriteDetails.level.displayString + "</font><br>"
        }
        if (equippedItem.itemClass.id == 2) {
            damageInfo = StringBuilder(equippedItem.weapon.damage.displayString)
            var length = damageInfo.length + equippedItem.weapon.attackSpeed.displayString.length
            while (length < 43) {
                damageInfo.append("&nbsp;")
                length++
            }
            damageInfo.append(equippedItem.weapon.attackSpeed.displayString).append("<br>").append(equippedItem.weapon.dps.displayString).append("<br>")
        }
        try {
            for (i in equippedItem.stats.indices) {
                statsString.append("<font color=#" + rbgToHexHTML(equippedItem.stats[i].displayString.color) + ">").append(equippedItem.stats[i].displayString.displayString).append("</font>").append("<br>")
            }
        } catch (e: Exception) {
            var i = 0
            while (i < equippedItem.stats.size) {
                if (equippedItem.stats[i].isIsEquipBonus) {
                    statsString.append("<font color=#00ff00>").append(equippedItem.stats[i].display_string).append("</font>").append("<br>")
                } else {
                    statsString.append(equippedItem.stats[i].display_string).append("<br>")
                }
                i++
            }
        }
        stats[equippedItem.slot.type] = String.format("%s%s%s%s%s%s%s%s%s", nameDescription, itemLvl, transmog, HoALevel, bind, slot.toString(), damageInfo.toString(), armor, statsString.toString())
        if (enchant.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", enchant)
        }
        stats[equippedItem.slot.type] = stats[equippedItem.slot.type] + azeriteSpells
        if (trigger != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", trigger)
        }
        if (sockets.length > 4) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", sockets)
        }
        if (socketBonus != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s", socketBonus)
        }
        if (setInfo.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", setInfo)
        }
        if (durability != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", durability)
        }
        if (requiredLevel != "") {
            if (durability == "") {
                stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", requiredLevel)
            } else {
                stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s<br>", requiredLevel)
            }
        }
        if (sellPrice.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s<br>", sellPrice)
        }
        if (equippedItem.description != null) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", description)
        }
        Log.i("Stats per slot: ", equippedItem.slot.name + ": " + stats[equippedItem.slot.type])
        setOnPressItemInformation(gearImageView[equippedItem.slot.type], index)
    }

    private fun formatSetItemText(set: Set): StringBuilder {
        var equippedSetItem = 0
        val itemRequiredForEffect = IntArray(set.effects.size)
        val setInfo = StringBuilder()
        for (i in set.effects.indices) {
            itemRequiredForEffect[i] = set.effects[i].requiredCount
        }
        for (itemEquipped in set.items) {
            if (itemEquipped.isIsEquipped) {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#ffff98>").append(itemEquipped.item.name).append("</font><br>")
                equippedSetItem++
            } else {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#6e6e6e>").append(itemEquipped.item.name).append("</font><br>")
            }
        }
        setInfo.append("<br>")
        for (i in itemRequiredForEffect.indices) {
            if (itemRequiredForEffect[i] <= equippedSetItem) {
                setInfo.append("<font color=#ffff98>").append(set.effects[i].displayString).append("</font><br>")
            } else {
                setInfo.append("<font color=#6e6e6e>").append(set.effects[i].displayString).append("</font><br>")
            }
        }
        return setInfo
    }

    private fun rbgToHexHTML(color: com.BlizzardArmory.warcraft.equipment.Color): String {
        return String.format("#%X", Color.rgb(color.r, color.g, color.b)).substring(3)
    }

    private fun getNameDescriptionColor(equippedItem: EquippedItem): String {
        var red = Integer.toHexString(equippedItem.nameDescriptionObject.color.r)
        var green = Integer.toHexString(equippedItem.nameDescriptionObject.color.g)
        var blue = Integer.toHexString(equippedItem.nameDescriptionObject.color.b)
        if (red == "0") {
            red += "0"
        }
        if (green == "0") {
            green += "0"
        }
        if (blue == "0") {
            blue += "0"
        }
        return red + green + blue
    }

    private fun getSocketColor(currentSocket: Socket): String {
        when (currentSocket.socketType.type) {
            "PRISMATIC" -> return "socket_prismatic"
            "META" -> return "socket_meta"
            "PUNCHCARDRED" -> return "socket_red"
            "PUNCHCARDYELLOW" -> return "socket_yellow"
            "PUNCHCARDBLUE" -> return "socket_blue"
        }
        return "socket_prismatic"
    }

    private fun getEssenceImage(essenceSlot: Int): String {
        when (essenceSlot) {
            0 -> return "essence_border_gold"
            1, 3 -> return "essence_border_silver"
        }
        return "essence_border_gold"
    }

    private fun getEssenceRankColor(rank: Int): String {
        when (rank) {
            1 -> return "00ff00"
            2 -> return "0070ff"
            3 -> return "663288"
            4 -> return "ff8000"
        }
        return "00ff00"
    }

    private fun setCharacterInformationTextviews() {
        health?.text = String.format("Health: %s", statistic.health)
        power?.text = String.format("%s: %s", statistic.powerType.name, statistic.power)
        strength?.text = String.format("Strength: %s", statistic.strength.effective)
        agility?.text = String.format("Agility: %s", statistic.agility.effective)
        intellect?.text = String.format("Intellect: %s", statistic.intellect.effective)
        stamina?.text = String.format("Stamina: %s", statistic.stamina.effective)
        crit?.text = String.format(Locale.ENGLISH, "Critical Strike: %.2f%%", statistic.meleeCrit.value)
        haste?.text = String.format(Locale.ENGLISH, "Haste: %.2f%%", statistic.meleeHaste.value.toDouble())
        mastery?.text = String.format(Locale.ENGLISH, "Mastery: %.2f%%", statistic.mastery.value.toDouble())
        versatility?.text = String.format(Locale.ENGLISH, "Versatility: %.2f%%", statistic.versatilityDamageDoneBonus.toDouble())
    }

    override fun onBackPressed(): Boolean {
        return URLConstants.loading
    }

    private fun callErrorAlertDialog(responseCode: Int) {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loading_circle?.visibility = View.GONE
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
            val btn2 = AtomicBoolean(false)
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
            if (responseCode >= 400) {
                titleText.text = "Information Outdated"
                messageText.text = "Please login in game to update this character's information."
                button.text = "BACK"
            } else {
                titleText.text = "No Internet Connection"
                messageText.text = "Make sure that Wi-Fi or mobile data is turned on, then try again."
                button.text = "RETRY"
                button2.text = "BACK"
                buttonLayout.addView(button2)
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
            dialog?.setOnCancelListener(DialogInterface.OnCancelListener { dialog1: DialogInterface? ->
                if (btn2.get()) {
                    Log.i("TEST", "got here")
                    (context as WoWActivity?)?.onBackPressed()
                } else {
                    if (responseCode == 0) {
                        dialog?.hide()
                        val fragment = requireActivity().supportFragmentManager.findFragmentByTag("NAV_FRAGMENT")
                        val ft = requireActivity().supportFragmentManager.beginTransaction()
                        ft.detach(fragment!!)
                        ft.attach(fragment)
                        ft.commit()
                    } else {
                        Log.i("TEST", "got here")
                        (context as WoWActivity?)?.onBackPressed()
                    }
                }
            })
            button.setOnClickListener { v: View? -> dialog?.cancel() }
            button2.setOnClickListener { v: View? ->
                btn2.set(true)
                dialog?.cancel()
            }
        }
    }

    companion object {
        var faction: String? = null
    }
}
package com.BlizzardArmory.ui.ui_diablo.characterfragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.D3GearFragmentBinding
import com.BlizzardArmory.model.diablo.items.Item
import com.BlizzardArmory.model.diablo.items.Items
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.events.D3ClosePanelEvent
import com.BlizzardArmory.util.events.D3ItemShownEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

private const val BATTLETAG = "battletag"
private const val SELECTED_REGION = "region"
private const val ID = "id"

class CharacterGearFragment : Fragment() {

    var itemPanelOpen = false

    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var closeButton: ImageButton? = null

    private var battletag = ""
    private var selectedRegion = ""
    private var id = 0L

    private var itemsInformation: Items? = null
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
    private val imageViewItem = HashMap<String, ImageView?>()
    private val itemIconURL = HashMap<String, String?>()
    private val items = ArrayList<Item>()
    private val primaryStatsMap = HashMap<Int, String>()
    private val secondaryStatsMap = HashMap<Int, String>()
    private val gemsMap = HashMap<Int, String>()

    private var _binding: D3GearFragmentBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            battletag = it.getString(BATTLETAG)!!
            selectedRegion = it.getString(SELECTED_REGION)!!
            id = it.getLong(ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = D3GearFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        URLConstants.loading = true

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        closeButton = ImageButton(view.context)
        closeButton!!.background = ContextCompat.getDrawable(requireContext(), R.drawable.close_button_d3)!!
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0F)
        params.setMargins(0, 0, 0, 20)
        closeButton!!.layoutParams = params
        setCloseButton()

        binding.itemName.setTextColor(Color.WHITE)
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

        addImageViewItemsToList()
        setItemInformation()
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

    @SuppressLint("ClickableViewAccessibility")
    private fun setCloseButton() {
        closeButton!!.setOnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                EventBus.getDefault().post(D3ItemShownEvent(false))
                v.performClick()
                Log.i("CLOSE", "CLICKED")
                binding.itemScrollView.visibility = View.GONE
                binding.itemStats.removeView(primarystats)
                binding.itemStats.removeView(secondarystats)
                binding.itemStats.removeView(gems)
                binding.itemStats.removeView(transmog)
                binding.itemStats.removeView(flavortext)
                binding.itemStats.removeView(misctext)
                binding.armorDamage.removeView(dps)
                binding.armorDamage.removeView(armor)
                binding.itemStats.removeView(closeButton)
                binding.itemStats.removeView(set)
                binding.weaponEffect.setImageDrawable(null)
                binding.itemScrollView.scrollTo(0, 0)
            }
            false
        }
    }

    private fun closeViewsWithoutButton() {
        binding.itemScrollView.visibility = View.GONE
        binding.itemStats.removeView(primarystats)
        binding.itemStats.removeView(secondarystats)
        binding.itemStats.removeView(gems)
        binding.itemStats.removeView(transmog)
        binding.itemStats.removeView(flavortext)
        binding.itemStats.removeView(misctext)
        binding.armorDamage.removeView(dps)
        binding.armorDamage.removeView(armor)
        binding.itemStats.removeView(closeButton)
        binding.itemStats.removeView(set)
        binding.weaponEffect.setImageDrawable(null)
        binding.itemScrollView.scrollTo(0, 0)
        EventBus.getDefault().post(D3ItemShownEvent(false))
    }

    @Throws(IOException::class)
    private fun setItemInformation() {
        val call: Call<Items> = GamesActivity.client!!.getHeroItems(battletag, id, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
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
                        //callErrorAlertDialog(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.e("Error", t.localizedMessage!!)
                //callErrorAlertDialog(0)
            }
        })
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

    private val itemIcons: Unit
        get() {
            for (key in itemIconURL.keys) {
                if (itemIconURL[key] != null) {
                    Glide.with(this).load(itemIconURL[key]).placeholder(R.drawable.loading_placeholder).into(imageViewItem[key]!!)
                }
            }
        }

    private fun setItemInformation(index: Int) {
        val primary = StringBuilder()
        val secondary = StringBuilder()
        val gem = StringBuilder()
        try {
            for (j in items[index].attributesHtml?.primary?.indices!!) {
                var attribute = items[index].attributesHtml?.primary!![j].replace("<span class=\"tooltip-icon-enchant\"></span>".toRegex(), "<img src=\"utility\">")
                attribute = attribute.replace("<span class=\"tooltip-icon-utility\"></span>".toRegex(), "<img src=\"utility\">")
                attribute = attribute.replace("<span class=\"tooltip-icon-bullet\"></span>".toRegex(), "<img src=\"primary" + "\">")
                attribute = attribute.replace("span class=\"d3-color-ff".toRegex(), "font color=\"#")
                attribute = attribute.replace("span class=\"d3-color-magic".toRegex(), "font color=\"#7979d4")
                primary.append(attribute.replace("</span>".toRegex(), "</font>")).append("<br>")
            }
            Log.i("Test primary", primary.toString())
        } catch (e: Exception) {
            primary.append("")
        }
        try {
            for (j in items[index].attributesHtml?.secondary?.indices!!) {
                var attribute = items[index].attributesHtml?.secondary!![j].replace("<span class=\"tooltip-icon-enchant\"></span>".toRegex(), "<img src=\"utility\">")
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
            for (j in items[index].gems?.indices!!) {
                val gemAttributes = StringBuilder()
                if (items[index].gems!![j].item.id.contains("Unique")) {
                    gemAttributes.append("<font color=\"#ff8000\"> ")
                }
                for (k in items[index].gems!![j].attributes.indices) {
                    gemAttributes.append(" <img src=\"").append(items[index].gems!![j].item.icon).append("\">")
                    gemAttributes.append(" <img src=\"primary\"> ")
                    gemAttributes.append(items[index].gems!![j].attributes[k].replace("\\n".toRegex(), "<br>")).append("<br>")
                }
                gemAttributes.append("</font>")
                gem.append(gemAttributes)
            }
            if (items[index].openSockets!! > 0) {
                for (i in 0 until items[index].openSockets?.toInt()!!) {
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
            setOnPressItemInformation(imageViewItem[items[index].slots]!!, index)
        } catch (e: Exception) {
            Log.e("Item", "empty")
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(imageView: ImageView?, index: Int) {
        imageView?.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                itemPanelOpen = true
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setColor(Color.parseColor("#000000"))
                backgroundStroke.setStroke(8, Color.parseColor(getItemBorderColor(index)))
                binding.itemScrollView.background = backgroundStroke
                binding.itemName.background = getHeaderBackground(index)
                val background = selectBackgroundColor(items[index].displayColor!!)
                val backgroundStrokeTooltipIcon = GradientDrawable()
                backgroundStrokeTooltipIcon.setStroke(3, Color.parseColor(selectColor(items[index].displayColor!!)))
                backgroundStrokeTooltipIcon.cornerRadius = 5f
                val layers = arrayOfNulls<Drawable>(2)
                layers[0] = background
                layers[1] = backgroundStrokeTooltipIcon
                val layerList = LayerDrawable(layers)
                binding.itemIcon.background = layerList
                binding.itemIcon.setImageDrawable(imageView.drawable)

                val jewelleryParams = RelativeLayout.LayoutParams((67 * Resources.getSystem().displayMetrics.density).toInt(), (67 * Resources.getSystem().displayMetrics.density).toInt())
                jewelleryParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                val normalIconParams = RelativeLayout.LayoutParams((67 * Resources.getSystem().displayMetrics.density).toInt(), (130 * Resources.getSystem().displayMetrics.density).toInt())
                normalIconParams.addRule(RelativeLayout.CENTER_IN_PARENT)

                if (items[index].slots == "neck" || items[index].slots == "leftFinger" || items[index].slots == "rightFinger" || items[index].slots == "waist") {
                    binding.itemIcon.layoutParams = jewelleryParams
                } else {
                    binding.itemIcon.layoutParams = normalIconParams
                }
                try {
                    val color = selectColor(items[index].displayColor!!)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.itemName.text = Html.fromHtml("<font color=\"" + color + "\">" + items[index].name + "</font>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.itemName.text = Html.fromHtml("<font color=\"" + color + "\">" + items[index].name + "</font>")
                    }
                    if (items[index].name?.length!! > 23) {
                        binding.itemName.textSize = 18f
                    }
                } catch (e: Exception) {
                    Log.e("Error", "Name", e)
                }
                var typeNameString = items[index].typeName
                if (typeNameString?.length!! > 22) {
                    val lastSpace = typeNameString.lastIndexOf(" ")
                    val beforeLastSpace = typeNameString.substring(0, lastSpace)
                    val lastSpace2 = beforeLastSpace.lastIndexOf(" ")
                    typeNameString = typeNameString.substring(0, lastSpace2) + "<br>" + typeNameString.substring(lastSpace2)
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    binding.typeName.text = Html.fromHtml(typeNameString, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    binding.typeName.text = Html.fromHtml(typeNameString)
                }
                binding.typeName.setTextColor(Color.parseColor(selectColor(items[index].displayColor!!)))
                binding.slot.text = items[index].slots
                try {
                    if (items[index].armor!! > 0) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            armor!!.text = Html.fromHtml("<big><big><big><big><big>" + (items[index].armor!!).roundToInt() + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            armor!!.text = Html.fromHtml("<big><big><big><big><big>" + (items[index].armor!!).roundToInt() + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>")
                        }
                        binding.armorDamage.addView(armor, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.e("Error", "Armor", e)
                }
                try {
                    if (!items[index].type?.twoHanded!! && items[index].minDamage!! > 0) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            binding.slot.text = Html.fromHtml("1-Hand", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            binding.slot.text = Html.fromHtml("1-Hand")
                        }
                    } else if (items[index].type?.twoHanded!! && items[index].minDamage!! > 0) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            binding.slot.text = Html.fromHtml("2-Hand", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            binding.slot.text = Html.fromHtml("2-Hand")
                        }
                    } else {
                        binding.slot.text = items[index].slots
                    }
                } catch (e: Exception) {
                    Log.e("Error", "No TYPE", e)
                }
                try {
                    if (items[index].minDamage!! > 0 && items[index].maxDamage!! > 0) {
                        val formatter: NumberFormat = DecimalFormat("#0.0")
                        val dpsText = ((items[index].minDamage!! + items[index].maxDamage!!) / 2 * items[index].attacksPerSecond!! * 10 / 10).roundToInt().toDouble()
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            dps?.text = Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
                                    + formatter.format(items[index].minDamage) + " - "
                                    + formatter.format(items[index].maxDamage) + "<font color=\"#696969\"> Damage</font><br>"
                                    + formatter.format(items[index].attacksPerSecond) + "<font color=\"#696969\"> Attacks per Second</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            dps?.text = Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
                                    + formatter.format(items[index].minDamage) + " - "
                                    + formatter.format(items[index].maxDamage) + "<font color=\"#696969\"> Damage</font><br>"
                                    + formatter.format(items[index].attacksPerSecond) + "<font color=\"#696969\"> Attacks per Second</font><br>")
                        }
                        binding.armorDamage.addView(dps, layoutParamsStats)
                        when (items[index].elementalType) {
                            "fire" -> binding.imageStats.setBackgroundResource(R.drawable.fire)
                            "cold" -> binding.imageStats.setBackgroundResource(R.drawable.cold)
                            "holy" -> binding.imageStats.setBackgroundResource(R.drawable.holy)
                            "poison" -> binding.imageStats.setBackgroundResource(R.drawable.poison)
                            "lightning" -> binding.imageStats.setBackgroundResource(R.drawable.lightning)
                            "arcane" -> binding.imageStats.setBackgroundResource(R.drawable.arcane)
                            else -> binding.imageStats.setBackgroundResource(0)
                        }
                    } else {
                        binding.imageStats.setBackgroundResource(0)
                    }
                } catch (e: Exception) {
                    binding.imageStats.setBackgroundResource(0)
                    dps?.text = ""
                }
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        primarystats!!.text = Html.fromHtml("Primary<br>" + primaryStatsMap[index], Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        primarystats!!.text = Html.fromHtml("Primary<br>" + primaryStatsMap[index], { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    }
                    binding.itemStats.addView(primarystats, layoutParamsStats)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        secondarystats!!.text = Html.fromHtml("Secondary<br>" + secondaryStatsMap[index], Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        secondarystats!!.text = Html.fromHtml("Secondary<br>" + secondaryStatsMap[index], { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    }
                    binding.itemStats.addView(secondarystats, layoutParamsStats)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        gems!!.text = Html.fromHtml(gemsMap[index], Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        gems!!.text = Html.fromHtml(gemsMap[index], { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    }
                    val gemParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    gemParams.setMargins(20, 0, 20, 0)
                    gems!!.gravity = Gravity.CENTER_VERTICAL
                    binding.itemStats.addView(gems, gemParams)
                } catch (e: Exception) {
                    Log.e("Error", "Gems", e)
                }
                try {
                    var setText = items[index].set?.descriptionHtml
                    var firstPart = setText?.substring(0, setText.indexOf("(2)") - 38)

                    firstPart = firstPart?.replace("<br />".toRegex(), "<br />&nbsp;&nbsp;&nbsp;")
                    val lastPart = setText?.substring(setText.indexOf("(2)") - 38)
                    setText = firstPart + lastPart
                    setText = setText.replace("<span class=\"tooltip-icon-bullet\"></span>".toRegex(), "&nbsp;&nbsp;<img src=\"primary" + "\">")
                    setText = setText.replace("<span class=\"tooltip-icon-utility\"></span>".toRegex(), "&nbsp;&nbsp;<img src=\"utility\">")
                    setText = setText.replace("<span class=\"d3-color-ff".toRegex(), "<font color=\"#").replace("</span>".toRegex(), "</font>")
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        set!!.text = Html.fromHtml(setText, Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        set!!.text = Html.fromHtml(setText, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    }
                    binding.itemStats.addView(set, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", "Set", e)
                }
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        transmog!!.text = Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                                + selectColor(items[index].transmog?.displayColor!!) + "\">" + items[index].transmog?.name + "</font><br>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        transmog!!.text = Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                                + selectColor(items[index].transmog?.displayColor!!) + "\">" + items[index].transmog?.name + "</font><br>")
                    }
                    binding.itemStats.addView(transmog, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", "Transmog", e)
                }
                try {
                    if (items[index].flavorText != null) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            flavortext!!.text = Html.fromHtml("<font color=\"#9d7853\">\"<i>" + items[index].flavorText + "</i>\"</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            flavortext!!.text = Html.fromHtml("<font color=\"#9d7853\">\"<i>" + items[index].flavorText + "</i>\"</font><br>")
                        }
                        binding.itemStats.addView(flavortext, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.e("Error", "FlavorText", e)
                }
                try {
                    if (items[index].accountBound!!) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (items[index].requiredLevel!!).roundToInt() + "<br>Account bound</font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (items[index].requiredLevel!!).roundToInt() + "<br>Account bound</font>")
                        }
                        misctext!!.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    } else {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (items[index].requiredLevel!!).roundToInt() + "<br></font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (items[index].requiredLevel!!).roundToInt() + "<br></font>")
                        }
                        misctext!!.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    }
                    binding.itemStats.addView(misctext, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", "Misc", e)
                }
                try {
                    (closeButton!!.parent as ViewGroup).removeView(closeButton)
                } catch (e: Exception) {
                    Log.e("Parent", "None")
                }
                binding.itemStats.addView(closeButton)
                binding.itemScrollView.visibility = View.VISIBLE
                EventBus.getDefault().post(D3ItemShownEvent(true))
            }
            true
        }
    }

    private fun addImageViewItemsToList() {
        imageViewItem["shoulders"] = binding.shoulder
        imageViewItem["hands"] = binding.gloves
        imageViewItem["leftFinger"] = binding.ring1
        imageViewItem["mainHand"] = binding.mainHand
        imageViewItem["head"] = binding.head
        imageViewItem["torso"] = binding.chest
        imageViewItem["waist"] = binding.belt
        imageViewItem["legs"] = binding.legs
        imageViewItem["feet"] = binding.boots
        imageViewItem["neck"] = binding.amulet
        imageViewItem["bracers"] = binding.bracers
        imageViewItem["rightFinger"] = binding.ring2
        imageViewItem["offHand"] = binding.offHand
    }

    private fun setItemBackgroundColor() {
        for (i in 0 until imageViewItem.size) {
            try {
                val background = selectBackgroundColor(items[i].displayColor!!)
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setStroke(3, Color.parseColor(selectColor(items[i].displayColor!!)))
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
            "blue" -> return ContextCompat.getDrawable(requireContext(), R.drawable.blue_bg_item_d3)!!
            "yellow" -> return ContextCompat.getDrawable(requireContext(), R.drawable.yellow_bg_item_d3)!!
            "orange" -> return ContextCompat.getDrawable(requireContext(), R.drawable.orange_bg_item_d3)!!
            "green" -> return ContextCompat.getDrawable(requireContext(), R.drawable.green_bg_item_d3)!!
            "white" -> return ContextCompat.getDrawable(requireContext(), R.drawable.brown_bg_item_d3)!!
            else -> {
            }
        }
        return ContextCompat.getDrawable(requireContext(), R.drawable.brown_bg_item_d3)!!
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
            items[index].typeName?.contains("Primal Legendary")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_legendary_primal)!!
            }
            items[index].typeName?.contains("Primal Set")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_legendary_primal)!!
            }
            items[index].typeName?.contains("Set")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_set)!!
            }
            items[index].typeName?.contains("Legendary")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_legendary)!!
            }
            items[index].typeName?.contains("Rare")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_rare)!!
            }
            items[index].typeName?.contains("Magic")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_magic)!!
            }
            else -> return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header)!!
        }
    }

    private fun getItemBorderColor(index: Int): String {
        val type = items[index].typeName
        if (type?.contains("Primal")!!) {
            return "#E52817"
        } else if (type.contains("Ancient")) {
            return "#b47402"
        }
        return "#312a26"
    }

    private fun getItemIconURL() {
        for (i in items.indices) {
            try {
                itemIconURL[items[i].slots!!] = URLConstants.D3_ICON_ITEMS.replace("icon.png", items[i].icon.toString()) + ".png"
            } catch (e: Exception) {
                Log.e("Error", e.toString())
                itemIconURL["empty"] = null
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            addImageViewItemsToList()
            setItemInformation()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun closePanelReceived(d3ClosePanelEvent: D3ClosePanelEvent) {
        closeViewsWithoutButton()
    }
}
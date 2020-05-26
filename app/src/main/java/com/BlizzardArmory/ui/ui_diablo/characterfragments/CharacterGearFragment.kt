package com.BlizzardArmory.ui.ui_diablo.characterfragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.diablo.items.Item
import com.BlizzardArmory.model.diablo.items.Items
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.events.BackPressEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.d3_gear_fragment.*
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

    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            battletag = it.getString(BATTLETAG)!!
            selectedRegion = it.getString(SELECTED_REGION)!!
            id = it.getLong(ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.d3_gear_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        URLConstants.loading = true

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)

        closeButton = ImageButton(view.context)
        closeButton!!.background = requireContext().getDrawable(R.drawable.close_button_d3)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0F)
        params.setMargins(0, 0, 0, 20)
        closeButton!!.layoutParams = params
        setCloseButton()

        item_name.setTextColor(Color.WHITE)
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

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setCloseButton() {
        closeButton!!.setOnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.performClick()
                Log.i("CLOSE", "CLICKED")
                item_scroll_view!!.visibility = View.GONE
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

    private fun closeViewsWithoutButton() {
        item_scroll_view!!.visibility = View.GONE
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

    @Throws(IOException::class)
    private fun setItemInformation() {
        val call: Call<Items> = RetroClient.getClient.getHeroItems(battletag, id, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper!!.accessToken)
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
                Log.e("Error", t.localizedMessage)
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
                    Picasso.get().load(itemIconURL[key]).placeholder(R.drawable.loading_placeholder).into(imageViewItem[key])
                }
            }
        }

    private fun setItemInformation(index: Int) {
        val primary = StringBuilder()
        val secondary = StringBuilder()
        val gem = StringBuilder()
        try {
            for (j in items[index].attributesHtml.primary.indices) {
                var attribute = items[index].attributesHtml.primary[j].replace("<span class=\"tooltip-icon-enchant\"></span>".toRegex(), "<img src=\"utility\">")
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
            setOnPressItemInformation(imageViewItem[items[index].slots]!!, index)
        } catch (e: Exception) {
            Log.e("Item", "empty")
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(imageView: ImageView?, index: Int) {
        imageView?.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setColor(Color.parseColor("#000000"))
                backgroundStroke.setStroke(8, Color.parseColor(getItemBorderColor(index)))
                item_scroll_view.background = backgroundStroke
                item_name.background = getHeaderBackground(index)
                val background = selectBackgroundColor(items[index].displayColor)
                val backgroundStrokeTooltipIcon = GradientDrawable()
                backgroundStrokeTooltipIcon.setStroke(3, Color.parseColor(selectColor(items[index].displayColor)))
                backgroundStrokeTooltipIcon.cornerRadius = 5f
                val layers = arrayOfNulls<Drawable>(2)
                layers[0] = background
                layers[1] = backgroundStrokeTooltipIcon
                val layerList = LayerDrawable(layers)
                item_icon.background = layerList
                item_icon.setImageDrawable(imageView.drawable)

                val jewelleryParams = RelativeLayout.LayoutParams((67 * Resources.getSystem().displayMetrics.density).toInt(), (67 * Resources.getSystem().displayMetrics.density).toInt())
                jewelleryParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                val normalIconParams = RelativeLayout.LayoutParams((67 * Resources.getSystem().displayMetrics.density).toInt(), (130 * Resources.getSystem().displayMetrics.density).toInt())
                normalIconParams.addRule(RelativeLayout.CENTER_IN_PARENT)

                if (items[index].slots == "neck" || items[index].slots == "leftFinger" || items[index].slots == "rightFinger" || items[index].slots == "waist") {
                    item_icon.layoutParams = jewelleryParams
                } else {
                    item_icon.layoutParams = normalIconParams
                }
                try {
                    val color = selectColor(items[index].displayColor)
                    item_name.text = Html.fromHtml("<font color=\"" + color + "\">" + items[index].name + "</font>", Html.FROM_HTML_MODE_LEGACY)
                    if (items[index].name.length > 23) {
                        item_name.textSize = 18f
                    }
                } catch (e: Exception) {
                    Log.e("Error", "Name", e)
                }
                var typeNameString = items[index].typeName
                if (typeNameString.length > 22) {
                    val lastSpace = typeNameString.lastIndexOf(" ")
                    val beforeLastSpace = typeNameString.substring(0, lastSpace)
                    val lastSpace2 = beforeLastSpace.lastIndexOf(" ")
                    typeNameString = typeNameString.substring(0, lastSpace2) + "<br>" + typeNameString.substring(lastSpace2)
                }
                typeName.text = Html.fromHtml(typeNameString, Html.FROM_HTML_MODE_LEGACY)
                typeName.setTextColor(Color.parseColor(selectColor(items[index].displayColor)))
                slot.text = items[index].slots
                try {
                    if (items[index].armor > 0) {
                        armor!!.text = Html.fromHtml("<big><big><big><big><big>" + Math.round(items[index].armor).toInt() + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>", Html.FROM_HTML_MODE_LEGACY)
                        armor_damage!!.addView(armor, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.e("Error", "Armor", e)
                }
                try {
                    if (!items[index].type.twoHanded && items[index].minDamage > 0) {
                        slot.text = Html.fromHtml("1-Hand", Html.FROM_HTML_MODE_LEGACY)
                    } else if (items[index].type.twoHanded && items[index].minDamage > 0) {
                        slot.text = Html.fromHtml("2-Hand", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        slot.text = items[index].slots
                    }
                } catch (e: Exception) {
                    Log.e("Error", "No TYPE", e)
                }
                try {
                    if (items[index].minDamage > 0 && items[index].maxDamage > 0) {
                        val formatter: NumberFormat = DecimalFormat("#0.0")
                        val dpsText = ((items[index].minDamage + items[index].maxDamage) / 2 * items[index].attacksPerSecond * 10 / 10).roundToInt().toDouble()
                        dps?.text = Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
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
                    dps?.text = ""
                }
                try {
                    primarystats!!.text = Html.fromHtml("Primary<br>" + primaryStatsMap[index], Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    item_stats!!.addView(primarystats, layoutParamsStats)
                    secondarystats!!.text = Html.fromHtml("Secondary<br>" + secondaryStatsMap[index], Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    item_stats!!.addView(secondarystats, layoutParamsStats)
                    gems!!.text = Html.fromHtml(gemsMap[index], Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source: String? ->
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
                    Log.e("Error", "Gems", e)
                }
                try {
                    var setText = items[index].set.descriptionHtml
                    var firstPart = setText.substring(0, setText.indexOf("(2)") - 38)
                    Log.i("TEST-2", firstPart)
                    firstPart = firstPart.replace("<br />".toRegex(), "<br />&nbsp;&nbsp;&nbsp;")
                    val lastPart = setText.substring(setText.indexOf("(2)") - 38)
                    setText = firstPart + lastPart
                    setText = setText.replace("<span class=\"tooltip-icon-bullet\"></span>".toRegex(), "&nbsp;&nbsp;<img src=\"primary" + "\">")
                    setText = setText.replace("<span class=\"tooltip-icon-utility\"></span>".toRegex(), "&nbsp;&nbsp;<img src=\"utility\">")
                    setText = setText.replace("<span class=\"d3-color-ff".toRegex(), "<font color=\"#").replace("</span>".toRegex(), "</font>")
                    Log.i("TEST", setText)
                    set!!.text = Html.fromHtml(setText, Html.FROM_HTML_MODE_LEGACY, Html.ImageGetter { source: String? ->
                        val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                        val drawable = resources.getDrawable(resourceId, requireContext().theme)
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        drawable
                    }, null)
                    item_stats!!.addView(set, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", "Set", e)
                }
                try {
                    transmog!!.text = Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                            + selectColor(items[index].transmog.displayColor) + "\">" + items[index].transmog.name + "</font><br>", Html.FROM_HTML_MODE_LEGACY)
                    item_stats!!.addView(transmog, layoutParamsStats)
                } catch (e: Exception) {
                    Log.e("Error", "Transmog", e)
                }
                try {
                    if (items[index].flavorText != null) {
                        flavortext!!.text = Html.fromHtml("<font color=\"#9d7853\">\"<i>" + items[index].flavorText + "</i>\"</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        item_stats!!.addView(flavortext, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.e("Error", "FlavorText", e)
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
                    Log.e("Error", "Misc", e)
                }
                try {
                    (closeButton!!.parent as ViewGroup).removeView(closeButton)
                } catch (e: Exception) {
                    Log.e("Parent", "None")
                }
                item_stats.addView(closeButton)
                item_scroll_view.visibility = View.VISIBLE
            }
            true
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun backPressReceived(backPressEvent: BackPressEvent) {
        if (backPressEvent.data) {
            closeViewsWithoutButton()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            addImageViewItemsToList()
            setItemInformation()
        }
    }
}
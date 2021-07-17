package com.BlizzardArmory.ui.diablo.characterfragments.gear

import android.annotation.SuppressLint
import android.content.SharedPreferences
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
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.D3GearFragmentBinding
import com.BlizzardArmory.model.diablo.items.Item
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.util.events.D3ClosePanelEvent
import com.BlizzardArmory.util.events.D3ItemShownEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

private const val BATTLETAG = "battletag"
private const val SELECTED_REGION = "region"
private const val ID = "id"

class CharacterGearFragment : Fragment() {

    var itemPanelOpen = false

    private var prefs: SharedPreferences? = null
    private var closeButton: ImageButton? = null

    private var battletag = ""
    private var selectedRegion = ""
    private var id = 0L

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

    private var _binding: D3GearFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterGearViewModel by viewModels()
    
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
        NetworkUtils.loading = true
        addImageViewItemsToList()
        setObservers()
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        viewModel.getBnetParams().value = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)

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
    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(it)
            viewModel.downloadItemInformation(battletag, id, selectedRegion)
        })

        viewModel.getItemsInfoSetup().observe(viewLifecycleOwner, {
            viewModel.getItems().forEachIndexed { index, item ->
                getItemIconURL(item)
                setItemBackgroundColor(item)
                if (item != null) {
                    setOnPressItemInformation(imageViewItem[item.slots], item, viewModel.getprimaryStats()[index]!!, viewModel.getsecondaryStats()[index]!!, viewModel.getgems()[index]!!)
                }
            }
            itemIcons
        })
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


    private val itemIcons: Unit
        get() {
            for (key in itemIconURL.keys) {
                if (itemIconURL[key] != null) {
                    Glide.with(this).load(itemIconURL[key]).placeholder(R.drawable.loading_placeholder).into(imageViewItem[key]!!)
                }
            }
        }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnPressItemInformation(imageView: ImageView?, item: Item, primaryStatsInfo: String, secondaryStatsInfo: String, gemsInfo: String) {
        imageView?.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                itemPanelOpen = true
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setColor(Color.parseColor("#000000"))
                backgroundStroke.setStroke(8, Color.parseColor(getItemBorderColor(item)))
                binding.itemScrollView.background = backgroundStroke
                binding.itemName.background = getHeaderBackground(item)
                val background = selectBackgroundColor(item.displayColor!!)
                val backgroundStrokeTooltipIcon = GradientDrawable()
                backgroundStrokeTooltipIcon.setStroke(3, Color.parseColor(selectColor(item.displayColor!!)))
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

                if (item.slots == "neck" || item.slots == "leftFinger" || item.slots == "rightFinger" || item.slots == "waist") {
                    binding.itemIcon.layoutParams = jewelleryParams
                } else {
                    binding.itemIcon.layoutParams = normalIconParams
                }
                try {
                    val color = selectColor(item.displayColor!!)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        binding.itemName.text = Html.fromHtml("<font color=\"" + color + "\">" + item.name + "</font>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        binding.itemName.text = Html.fromHtml("<font color=\"" + color + "\">" + item.name + "</font>")
                    }
                    if (item.name?.length!! > 23) {
                        binding.itemName.textSize = 18f
                    }
                } catch (e: Exception) {
                    Log.i("Name", "no name")
                }
                var typeNameString = item.typeName
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
                binding.typeName.setTextColor(Color.parseColor(selectColor(item.displayColor!!)))
                binding.slot.text = item.slots
                try {
                    if (item.armor!! > 0) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            armor!!.text = Html.fromHtml("<big><big><big><big><big>" + (item.armor!!).roundToInt() + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            armor!!.text = Html.fromHtml("<big><big><big><big><big>" + (item.armor!!).roundToInt() + "</big></big></big></big></big><br><font color=\"#696969\">Armor</font>")
                        }
                        binding.armorDamage.addView(armor, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.i("Armor", "no armor")
                }
                try {
                    if (!item.type?.twoHanded!! && item.minDamage!! > 0) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            binding.slot.text = Html.fromHtml("1-Hand", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            binding.slot.text = Html.fromHtml("1-Hand")
                        }
                    } else if (item.type?.twoHanded!! && item.minDamage!! > 0) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            binding.slot.text = Html.fromHtml("2-Hand", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            binding.slot.text = Html.fromHtml("2-Hand")
                        }
                    } else {
                        binding.slot.text = item.slots
                    }
                } catch (e: Exception) {
                    Log.i("Type", "no type")
                }
                try {
                    if (item.minDamage!! > 0 && item.maxDamage!! > 0) {
                        val formatter: NumberFormat = DecimalFormat("#0.0")
                        val dpsText = ((item.minDamage!! + item.maxDamage!!) / 2 * item.attacksPerSecond!! * 10 / 10).roundToInt().toDouble()
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            dps?.text = Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
                                    + formatter.format(item.minDamage) + " - "
                                    + formatter.format(item.maxDamage) + "<font color=\"#696969\"> Damage</font><br>"
                                    + formatter.format(item.attacksPerSecond) + "<font color=\"#696969\"> Attacks per Second</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            dps?.text = Html.fromHtml("<big><big><big><big><big>" + formatter.format(dpsText) + "</big></big></big></big></big><br><font color=\"#696969\">Damage Per Second</font><br><br>"
                                    + formatter.format(item.minDamage) + " - "
                                    + formatter.format(item.maxDamage) + "<font color=\"#696969\"> Damage</font><br>"
                                    + formatter.format(item.attacksPerSecond) + "<font color=\"#696969\"> Attacks per Second</font><br>")
                        }
                        binding.armorDamage.addView(dps, layoutParamsStats)
                        when (item.elementalType) {
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
                        primarystats!!.text = Html.fromHtml("Primary<br>$primaryStatsInfo", Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        primarystats!!.text = Html.fromHtml("Primary<br>$primaryStatsInfo", { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    }
                    binding.itemStats.addView(primarystats, layoutParamsStats)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        secondarystats!!.text = Html.fromHtml("Secondary<br>$secondaryStatsInfo", Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        secondarystats!!.text = Html.fromHtml("Secondary<br>$secondaryStatsInfo", { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    }
                    binding.itemStats.addView(secondarystats, layoutParamsStats)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        gems!!.text = Html.fromHtml(gemsInfo, Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                            val drawable = ContextCompat.getDrawable(requireContext(), resourceId)!!
                            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                            drawable
                        }, null)
                    } else {
                        gems!!.text = Html.fromHtml(gemsInfo, { source: String? ->
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
                    Log.i("Gems", "no gems")
                }
                try {
                    var setText = item.set?.descriptionHtml
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
                    Log.i("Set", "no set")
                }
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        transmog!!.text = Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                                + selectColor(item.transmog?.displayColor!!) + "\">" + item.transmog?.name + "</font><br>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        transmog!!.text = Html.fromHtml("<font color=\"#7979d4\">Transmogrification:</font><br>" + "<font color=\""
                                + selectColor(item.transmog?.displayColor!!) + "\">" + item.transmog?.name + "</font><br>")
                    }
                    binding.itemStats.addView(transmog, layoutParamsStats)
                } catch (e: Exception) {
                    Log.i("Transmog", "no transmog")
                }
                try {
                    if (item.flavorText != null) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            flavortext!!.text = Html.fromHtml("<font color=\"#9d7853\">\"<i>" + item.flavorText + "</i>\"</font><br>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            flavortext!!.text = Html.fromHtml("<font color=\"#9d7853\">\"<i>" + item.flavorText + "</i>\"</font><br>")
                        }
                        binding.itemStats.addView(flavortext, layoutParamsStats)
                    }
                } catch (e: Exception) {
                    Log.i("Flavor Text", "no flavor text")
                }
                try {
                    if (item.accountBound!!) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (item.requiredLevel!!).roundToInt() + "<br>Account bound</font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (item.requiredLevel!!).roundToInt() + "<br>Account bound</font>")
                        }
                        misctext!!.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    } else {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (item.requiredLevel!!).roundToInt() + "<br></font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            misctext!!.text = Html.fromHtml("<font color=\"#a99877\">Required Level: " + (item.requiredLevel!!).roundToInt() + "<br></font>")
                        }
                        misctext!!.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    }
                    binding.itemStats.addView(misctext, layoutParamsStats)
                } catch (e: Exception) {
                    Log.i("Misc", "no misc")
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

    private fun setItemBackgroundColor(item: Item) {
        for (i in 0 until imageViewItem.size) {
            try {
                val background = selectBackgroundColor(item.displayColor!!)
                val backgroundStroke = GradientDrawable()
                backgroundStroke.setStroke(3, Color.parseColor(selectColor(item.displayColor!!)))
                backgroundStroke.cornerRadius = 5f
                val layers = arrayOfNulls<Drawable>(2)
                layers[0] = background
                layers[1] = backgroundStroke
                val layerList = LayerDrawable(layers)
                imageViewItem[item.slots]!!.background = layerList
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

    private fun getHeaderBackground(item: Item): Drawable {
        when {
            item.typeName?.contains("Primal Legendary")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_legendary_primal)!!
            }
            item.typeName?.contains("Primal Set")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_legendary_primal)!!
            }
            item.typeName?.contains("Set")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_set)!!
            }
            item.typeName?.contains("Legendary")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_legendary)!!
            }
            item.typeName?.contains("Rare")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_rare)!!
            }
            item.typeName?.contains("Magic")!! -> {
                return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header_magic)!!
            }
            else -> return ContextCompat.getDrawable(requireContext(), R.drawable.d3_item_header)!!
        }
    }

    private fun getItemBorderColor(item: Item): String {
        val type = item.typeName
        if (type?.contains("Primal")!!) {
            return "#E52817"
        } else if (type.contains("Ancient")) {
            return "#b47402"
        }
        return "#312a26"
    }

    private fun getItemIconURL(item: Item) {
        try {
            itemIconURL[item.slots!!] = NetworkUtils.D3_ICON_ITEMS.replace("icon.png", item.icon.toString()) + ".png"
        } catch (e: Exception) {
            Log.e("Error", e.toString())
            itemIconURL["empty"] = null
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            addImageViewItemsToList()
            viewModel.downloadItemInformation(battletag, id, selectedRegion)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun closePanelReceived(d3ClosePanelEvent: D3ClosePanelEvent) {
        closeViewsWithoutButton()
    }
}
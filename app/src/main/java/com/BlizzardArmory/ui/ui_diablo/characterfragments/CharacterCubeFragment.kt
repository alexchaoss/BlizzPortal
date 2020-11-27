package com.BlizzardArmory.ui.ui_diablo.characterfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.D3CubeFragmentBinding
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.model.diablo.item.SingleItem
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.util.events.WoWCharacterEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import java.util.*

class CharacterCubeFragment : Fragment() {

    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var cubeText = ""
    private val cubeMap = HashMap<String, ImageView?>()
    private val singleItem = ArrayList<SingleItem>()
    private var characterInformation: CharacterInformation? = null
    
    private var _binding: D3CubeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = D3CubeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        binding.cubeArmorItem.setImageResource(0)
        binding.cubeSwordItem.setImageResource(0)
        binding.cubeRingItem.setImageResource(0)
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

    private val cubeIcons: Unit
        get() {
            val cubeURL = HashMap<String, String>()
            val armorArray = arrayOf("shoulder", "spirit", "voodoo", "wizardhat", "gloves", "helm", "chest", "cloak", "belt", "pants", "boots", "bracers")
            val ringArray = arrayOf("ring", "amulet")
            try {
                for (i in characterInformation!!.legendaryPowers.indices) {
                    when {
                        armorArray.any { s: String? -> characterInformation!!.legendaryPowers[i].icon.contains(s!!) } -> {
                            cubeURL["armor"] = URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation!!.legendaryPowers[i].icon + ".png")
                            cubeMap[characterInformation!!.legendaryPowers[i].icon.toLowerCase(Locale.ROOT)] = binding.cubeArmorItem
                        }
                        ringArray.any { s: String? -> characterInformation!!.legendaryPowers[i].icon.contains(s!!) } -> {
                            cubeURL["ring"] = URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation!!.legendaryPowers[i].icon + ".png")
                            cubeMap[characterInformation!!.legendaryPowers[i].icon.toLowerCase(Locale.ROOT)] = binding.cubeRingItem
                        }
                        else -> {
                            cubeURL["sword"] = URLConstants.D3_ICON_ITEMS.replace("icon.png", characterInformation!!.legendaryPowers[i].icon + ".png")
                            cubeMap[characterInformation!!.legendaryPowers[i].icon.toLowerCase(Locale.ROOT)] = binding.cubeSwordItem
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Power", "None")
            }
            for (key in cubeURL.keys) {
                when (key) {
                    "sword" -> {
                        binding.cubeSwordItem.visibility = View.VISIBLE
                        Glide.with(this).load(cubeURL[key]).into(binding.cubeSwordItem)
                    }
                    "armor" -> {
                        binding.cubeArmorItem.visibility = View.VISIBLE
                        Glide.with(this).load(cubeURL[key]).into(binding.cubeArmorItem)
                    }
                    "ring" -> {
                        binding.cubeRingItem.visibility = View.VISIBLE
                        Glide.with(this).load(cubeURL[key]).into(binding.cubeRingItem)
                    }
                }
            }
        }

    private fun downloadCubeItems() {
        for (i in characterInformation!!.legendaryPowers.indices) {
            Log.i("Cube", characterInformation!!.legendaryPowers[i].tooltipParams)
            val endpoint = characterInformation!!.legendaryPowers[i].tooltipParams.replace("/item/", "")
            val call: Call<SingleItem> = GamesActivity.client!!.getItem(endpoint, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale)
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
                            Log.e("Error cube item", "${response.code()}")
                        }
                        else -> {
                            Log.e("Error cube item", "${response.code()}")
                        }
                    }
                }

                override fun onFailure(call: Call<SingleItem>, t: Throwable) {
                    Log.e("Error", t.message, t)
                }
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setCubeText() {
        for (i in singleItem.indices) {
            cubeMap[singleItem[i].icon.toLowerCase(Locale.ROOT)]?.setOnTouchListener { _: View?, _: MotionEvent? ->
                for (j in singleItem[i].attributes.secondary.indices) {
                    if (singleItem[i].attributes.secondary[j].textHtml.contains("d3-color-ffff8000")) {
                        cubeText = "<big>" + singleItem[i].name + "</big><br>" + singleItem[i].attributes.secondary[j].textHtml
                                .replace("<span class=\"d3-color-ff".toRegex(), "<font color=\"#").replace("<span class=\"d3-color-magic\">".toRegex(), "<font color=\"#7979d4\">")
                                .replace("span".toRegex(), "font")
                    }
                }
                binding.cubeText.visibility = View.VISIBLE
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    binding.cubeText.text = Html.fromHtml(cubeText, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    binding.cubeText.text = Html.fromHtml(cubeText)
                }
                false
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(woWCharacterEvent: WoWCharacterEvent) {
        characterInformation = woWCharacterEvent.data
        cubeIcons
        downloadCubeItems()
    }

}
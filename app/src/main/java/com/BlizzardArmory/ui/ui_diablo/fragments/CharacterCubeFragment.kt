package com.BlizzardArmory.ui.ui_diablo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.diablo.character.CharacterInformation
import com.BlizzardArmory.diablo.item.SingleItem
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_diablo.CharacterEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.d3_cube_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import java.util.*

class CharacterCubeFragment : Fragment(), IOnBackPressed {

    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var cubeText = ""
    private val cubeMap = HashMap<String, ImageView?>()
    private val singleItem = ArrayList<SingleItem>()
    private var characterInformation: CharacterInformation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.d3_cube_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
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

    private fun downloadCubeItems() {
        for (i in characterInformation!!.legendaryPowers.indices) {
            Log.i("Cube", characterInformation!!.legendaryPowers[i].tooltipParams)
            val call: Call<SingleItem> = RetroClient.getClient.getItem(characterInformation!!.legendaryPowers[i].tooltipParams, MainActivity.locale)
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
                            //callErrorAlertDialog(response.code())
                        }
                        else -> {
                            //callErrorAlertDialog(0)
                        }
                    }
                }

                override fun onFailure(call: Call<SingleItem>, t: Throwable) {
                    Log.e("Error", t.localizedMessage)
                    //callErrorAlertDialog(0)
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
                cube_text!!.visibility = View.VISIBLE
                cube_text!!.text = Html.fromHtml(cubeText, Html.FROM_HTML_MODE_LEGACY)
                false
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(characterEvent: CharacterEvent) {
        characterInformation = characterEvent.data
        cubeIcons
        downloadCubeItems()
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }

}
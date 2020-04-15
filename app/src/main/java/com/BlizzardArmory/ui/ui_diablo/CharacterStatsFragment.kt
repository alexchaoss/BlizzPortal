package com.BlizzardArmory.ui.ui_diablo

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.diablo.character.CharacterInformation
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import kotlinx.android.synthetic.main.d3_character_fragment.*
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import java.text.DecimalFormat
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.roundToInt

class CharacterStatsFragment : Fragment(), IOnBackPressed {
    private var bnOAuth2Helper: BnOAuth2Helper? = null

    private var characterInformation: CharacterInformation? = null
    private var dialog: AlertDialog? = null
    private var battletag = ""
    private var selectedRegion = ""
    private var id = 0L


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
        dialog = null
        val skillTooltipBG = GradientDrawable()
        skillTooltipBG.setStroke(6, Color.parseColor("#2e2a27"))
        skillTooltipBG.setColor(Color.BLACK)
        //setChatGem()
        loading_circle.visibility = View.VISIBLE

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val bnOAuth2Params: BnOAuth2Params = requireActivity().intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)!!
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)

    }

    private fun setCharacterInformation() {
        val call: Call<CharacterInformation> = RetroClient.getClient.getD3Hero(battletag, id, MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : retrofit2.Callback<CharacterInformation> {
            override fun onResponse(call: Call<CharacterInformation>, response: retrofit2.Response<CharacterInformation>) {
                when {
                    response.isSuccessful -> {
                        characterInformation = response.body()
                        EventBus.getDefault().post(CharacterEvent(characterInformation!!))
                        setGlobes()
                        setName()
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


    /* @SuppressLint("ClickableViewAccessibility")
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
     }*/

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

    /*private fun closeViewsWithoutButton() {
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
    }*/


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

    override fun onBackPressed(): Boolean {
        return URLConstants.loading
    }

    fun callErrorAlertDialog(responseCode: Int) {
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
            dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
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
                    parentFragmentManager.beginTransaction().remove(this@CharacterStatsFragment).commit()
                } else {
                    if (responseCode == 0) {
                        val fragment = parentFragmentManager.findFragmentById(R.id.fragment) as CharacterStatsFragment?
                        parentFragmentManager.beginTransaction()
                                .detach(fragment!!)
                                .attach(fragment)
                                .commit()
                    } else {
                        parentFragmentManager.beginTransaction().remove(this@CharacterStatsFragment).commit()
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
package com.BlizzardArmory.ui.ui_diablo.characterfragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.D3CharacterFragmentBinding
import com.BlizzardArmory.model.diablo.character.CharacterInformation
import com.BlizzardArmory.ui.main.MainActivity
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.ui_diablo.account.D3Fragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import java.text.DecimalFormat
import java.util.*
import kotlin.math.roundToInt

class CharacterStatsFragment : Fragment() {
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null

    private var characterInformation: CharacterInformation? = null
    private var dialog: AlertDialog? = null
    private var battletag = ""
    private var selectedRegion = ""
    private var id = 0L
    private lateinit var errorMessages: ErrorMessages
    
    private var _binding: D3CharacterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = D3CharacterFragmentBinding.inflate(layoutInflater)
        return binding.root
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        errorMessages = ErrorMessages(this.resources)
        val bundle = requireArguments()
        id = bundle.getLong("id")
        battletag = bundle.getString("battletag")!!
        selectedRegion = bundle.getString("region")!!
        dialog = null
        val skillTooltipBG = GradientDrawable()
        skillTooltipBG.setStroke(6, Color.parseColor("#2e2a27"))
        skillTooltipBG.setColor(Color.BLACK)
        //setChatGem()

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val battlenetOAuth2Params: BattlenetOAuth2Params = requireActivity().intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)!!
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params)
        setCharacterInformation()
    }

    private fun setCharacterInformation() {
        URLConstants.loading = true
        binding.loadingCircle.visibility = View.VISIBLE
        dialog = null
        val call: Call<CharacterInformation> = RetroClient.getClient().getD3Hero(battletag, id, MainActivity.locale, selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : retrofit2.Callback<CharacterInformation> {
            override fun onResponse(call: Call<CharacterInformation>, response: retrofit2.Response<CharacterInformation>) {
                when {
                    response.isSuccessful -> {
                        characterInformation = response.body()
                        EventBus.getDefault().post(WoWCharacterEvent(characterInformation!!))
                        setGlobes()
                        setName()
                        val primaryStats = DecimalFormat("#0")
                        binding.strengthText.text = primaryStats.format(characterInformation?.stats?.strength?.roundToInt()).toString()
                        binding.dexterityText.text = primaryStats.format(characterInformation?.stats?.dexterity?.roundToInt()).toString()
                        binding.intelligenceText.text = primaryStats.format(characterInformation?.stats?.intelligence?.roundToInt()).toString()
                        binding.vitalityText.text = primaryStats.format(characterInformation?.stats?.vitality?.roundToInt()).toString()
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            binding.damage.text = Html.fromHtml("<br><br>Damage<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.damage) + "</font>", Html.FROM_HTML_MODE_LEGACY)
                            binding.toughness.text = Html.fromHtml("Toughness<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.toughness) + "</font>", Html.FROM_HTML_MODE_LEGACY)
                            binding.recovery.text = Html.fromHtml("Recovery<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.healing) + "</font>", Html.FROM_HTML_MODE_LEGACY)
                        } else {
                            binding.damage.text = Html.fromHtml("<br><br>Damage<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.damage) + "</font>")
                            binding.toughness.text = Html.fromHtml("Toughness<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.toughness) + "</font>")
                            binding.recovery.text = Html.fromHtml("Recovery<br><font color=\"#FFFFFF\">" + primaryStats.format(characterInformation?.stats?.healing) + "</font>")
                        }
                        binding.loadingCircle.visibility = View.GONE
                        URLConstants.loading = false
                    }
                    response.code() >= 400 -> {
                        showNoConnectionMessage(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<CharacterInformation>, t: Throwable) {
                Log.e("Error", t.localizedMessage!!)
                showNoConnectionMessage(0)
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
        binding.totalLife.text = life
        val ressourceText: String = if (characterInformation!!.class_ == "demon-hunter") {
            characterInformation!!.stats.primaryResource.toString() + "\n" + characterInformation!!.stats.secondaryResource.toString()
        } else {
            characterInformation!!.stats.primaryResource.toString()
        }
        binding.ressource.text = ressourceText
        when (characterInformation!!.class_) {
            "barbarian" -> binding.ressourceGlobe.setImageResource(R.drawable.d3_fury)
            "wizard" -> binding.ressourceGlobe.setImageResource(R.drawable.d3_arcane_power)
            "demon-hunter" -> binding.ressourceGlobe.setImageResource(R.drawable.d3_hatred_disc)
            "witch-doctor" -> binding.ressourceGlobe.setImageResource(R.drawable.d3_mana)
            "necromancer" -> binding.ressourceGlobe.setImageResource(R.drawable.d3_essence)
            "monk" -> {
                binding.ressourceGlobe.setImageResource(R.drawable.d3_spirit)
                binding.ressource.setTextColor(Color.BLACK)
            }
            "crusader" -> {
                binding.ressourceGlobe.setImageResource(R.drawable.d3_wrath)
                binding.ressource.setTextColor(Color.BLACK)
            }
        }
    }

    private fun setName() {
        val levelClass = ("<font color=#d4a94e>" + characterInformation!!.level + "</font>" + "<font color=#555da5> (" + characterInformation!!.paragonLevel
                + ")</font> <font color=#d4a94e>" + characterInformation!!.class_)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.levelClass.text = Html.fromHtml(levelClass, Html.FROM_HTML_MODE_LEGACY)
        } else {
            binding.levelClass.text = Html.fromHtml(levelClass)
        }
        if (characterInformation!!.name.length in 8..9) {
            binding.characterName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
        } else if (characterInformation!!.name.length > 9) {
            binding.characterName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
            binding.characterName.setPadding(0, 10, 0, 0)
        }
        binding.characterName.text = characterInformation!!.name
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages.LOGIN_TO_UPDATE
            }
            503, 403 -> {
                errorMessages.UNEXPECTED
            }
            500 -> {
                errorMessages.BLIZZ_SERVERS_DOWN
            }
            else -> {
                errorMessages.TURN_ON_CONNECTION_MESSAGE
            }
        }

    }

    private fun getErrorTitle(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages.INFORMATION_OUTDATED
            }
            503, 403 -> {
                errorMessages.UNAVAILABLE
            }
            500 -> {
                errorMessages.SERVERS_ERROR
            }
            else -> {
                errorMessages.NO_INTERNET
            }
        }
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        binding.loadingCircle.visibility = View.GONE
        URLConstants.loading = false

        val dialog = DialogPrompt(requireActivity())

        dialog.addTitle(getErrorTitle(responseCode), 20f, "title")
                .addMessage(getErrorMessage(responseCode), 18f, "message")
                .addSideBySideButtons(errorMessages.RETRY, 18f, errorMessages.BACK, 18f,
                        {
                            dialog.cancel()
                            setCharacterInformation()
                            EventBus.getDefault().post(RetryEvent(true))
                            binding.loadingCircle.visibility = View.VISIBLE
                            URLConstants.loading = true
                        },
                        {
                            dialog.cancel()
                            EventBus.getDefault().post(NetworkEvent(true))
                            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        },
                        "retry", "back").show()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun itemShownReceived(itemShownEvent: D3ItemShownEvent) {
        itemPanelShown = itemShownEvent.data
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun spellShownReceived(spellShownEvent: D3SpellShownEvent) {
        spellPanelShown = spellShownEvent.data
    }

    companion object{
        private var itemPanelShown = false
        private var spellPanelShown = false
        fun addOnBackPressCallback(activity : GamesActivity){
            activity.onBackPressedDispatcher.addCallback {
                if(!URLConstants.loading) {
                    if (!itemPanelShown && !spellPanelShown) {
                        D3Fragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack()
                    } else {
                        EventBus.getDefault().post(D3ClosePanelEvent())
                    }
                }
            }
        }
    }
}
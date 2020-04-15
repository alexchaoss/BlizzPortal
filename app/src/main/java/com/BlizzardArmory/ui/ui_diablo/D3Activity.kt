package com.BlizzardArmory.ui.ui_diablo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.diablo.account.AccountInformation
import com.BlizzardArmory.diablo.account.Hero
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity
import com.BlizzardArmory.ui.ui_warcraft.activity.WoWActivity
import kotlinx.android.synthetic.main.d3_activity.*
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*

class D3Activity : AppCompatActivity() {
    private var prefs: SharedPreferences? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var portraits: List<Drawable>? = null
    private var battleTag: String? = ""
    private var selectedRegion: String? = ""
    private var characterID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.d3_activity)

        loadingCircle.visibility = View.VISIBLE
        btag_header.text = GamesActivity.userInformation.battleTag
        battleTag = intent.extras?.getString("battletag")
        selectedRegion = intent.extras?.getString("region")
        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        bnOAuth2Params = intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        assert(bnOAuth2Params != null)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)
        downloadAccountInformation()

        //Button calls
        wowButton.setOnClickListener { callNextActivity(WoWActivity::class.java) }
        starcraft2Button.setOnClickListener { callNextActivity(SC2Activity::class.java) }
        overwatchButton.setOnClickListener {
            OWPlatformChoiceDialog.myProfileChosen = false
            OWPlatformChoiceDialog.overwatchPrompt(this@D3Activity, bnOAuth2Params)
        }
        search.setOnClickListener { DiabloProfileSearchDialog.diabloPrompt(this@D3Activity, bnOAuth2Params) }
    }

    private fun downloadAccountInformation() {
        val call: Call<AccountInformation> = RetroClient.getClient.getD3Profile(battleTag, MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<AccountInformation> {
            override fun onResponse(call: Call<AccountInformation>, response: retrofit2.Response<AccountInformation>) {
                if (response.isSuccessful) {
                    accountInformation = response.body()
                    sortHeroes()
                    portraits = accountInformation?.heroes?.let { getCharacterImage(it, applicationContext) }
                    val paragon = accountInformation?.paragonLevel.toString() +
                            " | " +
                            "<font color=#b00000>" +
                            accountInformation?.paragonLevelHardcore +
                            "</font>"
                    paragonLevel!!.text = Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY)
                    elite_kills!!.text = accountInformation?.kills?.elites.toString()
                    lifetime_kills!!.text = accountInformation?.kills?.monsters.toString()
                    setProgression()
                    setTimePlayed()
                    setCharacterFrames()
                    setFallenCharacterFrames()
                    loadingCircle!!.visibility = View.GONE
                } else if (response.code() >= 400) {
                    showNoConnectionMessage(this@D3Activity, response.code())
                }
            }

            override fun onFailure(call: Call<AccountInformation>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                showNoConnectionMessage(this@D3Activity, 0)
            }
        })
    }

    private fun setTimePlayed() {
        Log.i("prog", "barb: " + accountInformation?.timePlayed?.barbarian.toString()
                + ", dh: " + accountInformation?.timePlayed?.demonHunter.toString()
                + ", crusader: " + accountInformation?.timePlayed?.crusader.toString()
                + ", necro: " + accountInformation?.timePlayed?.necromancer.toString()
                + ", wd: " + accountInformation?.timePlayed?.witchDoctor.toString()
                + ", wiz: " + accountInformation?.timePlayed?.wizard.toString()
                + ", monk: " + accountInformation?.timePlayed?.monk.toString())
        barb_progress.progress = (accountInformation?.timePlayed?.barbarian?.times(100))?.toInt()!!
        crusader_progress.progress = (accountInformation?.timePlayed?.crusader?.times(100))?.toInt()!!
        dh_progress.progress = (accountInformation?.timePlayed?.demonHunter?.times(100))?.toInt()!!
        monk_progress.progress = (accountInformation?.timePlayed?.monk?.times(100))?.toInt()!!
        necro_progress.progress = (accountInformation?.timePlayed?.necromancer?.times(100))?.toInt()!!
        wd_progress.progress = (accountInformation?.timePlayed?.witchDoctor?.times(100))?.toInt()!!
        wizard_progress.progress = (accountInformation?.timePlayed?.wizard?.times(100))?.toInt()!!
    }

    private fun setFallenCharacterFrames() {
        try {
            for (i in accountInformation!!.fallenHeroes.indices) {
                val frameLayout = ConstraintLayout(applicationContext)
                frameLayout.id = (i + 1) * 2
                val frameParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                frameParams.marginEnd = 20
                frameLayout.layoutParams = frameParams
                val frame = ImageView(applicationContext)
                frame.id = (i + 10) * 2
                getFallenHeroFrame(i, frame)
                val levelFrame = ImageView(applicationContext)
                levelFrame.id = (i + 100) * 2
                levelFrame.setImageResource(R.drawable.fallen_hero_level)
                val name = TextView(applicationContext)
                name.setTextColor(Color.parseColor("#a99877"))
                name.text = accountInformation!!.fallenHeroes[i].name
                name.id = (i + 1000) * 2
                val format = SimpleDateFormat("MM/dd/yy")
                val dateFormatted = Date(accountInformation!!.fallenHeroes[i].death.time.toLong() * 1000L)
                val dateString = format.format(dateFormatted)
                val date = TextView(applicationContext)
                date.setTextColor(Color.parseColor("#937a51"))
                date.text = dateString
                date.textSize = 12f
                date.id = (i + 10000) * 2
                val level = TextView(applicationContext)
                level.setTextColor(Color.parseColor("#b00000"))
                level.textSize = 15f
                level.text = accountInformation!!.fallenHeroes[i].level.toString()
                level.setTypeface(null, Typeface.BOLD)
                level.id = (i + 100000) * 2
                frameLayout.addView(frame)
                frameLayout.addView(name)
                frameLayout.addView(date)
                frameLayout.addView(levelFrame)
                frameLayout.addView(level)
                val setFrame = ConstraintSet()
                setFrame.clone(frameLayout)
                setFrame.connect(frame.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
                setFrame.connect(frame.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
                setFrame.connect(frame.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
                setFrame.connect(frame.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
                setFrame.connect(name.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 100)
                setFrame.connect(name.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
                setFrame.connect(name.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
                setFrame.connect(name.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
                setFrame.connect(date.id, ConstraintSet.TOP, name.id, ConstraintSet.BOTTOM, 0)
                setFrame.connect(date.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
                setFrame.connect(date.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
                setFrame.connect(date.id, ConstraintSet.BOTTOM, levelFrame.id, ConstraintSet.TOP, 0)
                setFrame.connect(level.id, ConstraintSet.TOP, levelFrame.id, ConstraintSet.TOP, 0)
                setFrame.connect(level.id, ConstraintSet.LEFT, levelFrame.id, ConstraintSet.LEFT, 0)
                setFrame.connect(level.id, ConstraintSet.RIGHT, levelFrame.id, ConstraintSet.RIGHT, 0)
                setFrame.connect(level.id, ConstraintSet.BOTTOM, levelFrame.id, ConstraintSet.BOTTOM, 0)
                setFrame.connect(levelFrame.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
                setFrame.connect(levelFrame.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
                setFrame.connect(levelFrame.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, -25)
                setFrame.applyTo(frameLayout)
                fallen_character_layout!!.addView(frameLayout)
            }
        } catch (e: Exception) {
            val fallenHeroContainer = findViewById<HorizontalScrollView>(R.id.fallen_heroes_scroll)
            fallenHeroContainer.visibility = View.GONE
            val fallenHeroTitle = findViewById<TextView>(R.id.textView4)
            fallenHeroTitle.visibility = View.GONE
        }
    }

    private fun getFallenHeroFrame(i: Int, frame: ImageView) {
        when (accountInformation!!.fallenHeroes[i].class_) {
            "barbarian" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_barb_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_barb_dead)
            }
            "wizard" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_wizard_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_wizard_dead)
            }
            "demon-hunter" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_dh_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_dh_dead)
            }
            "witch-doctor" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_wd_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_wd_dead)
            }
            "necromancer" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_necromancer_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_necromancer_dead)
            }
            "monk" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_monk_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_monk_dead)
            }
            "crusader" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                frame.setImageResource(R.drawable.d3_male_crusader_dead)
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                frame.setImageResource(R.drawable.d3_female_crusader_dead)
            }
        }
    }

    private fun sortHeroes() {
        accountInformation!!.heroes.sortBy { it.lastUpdated }
        accountInformation!!.heroes.reverse()
    }

    private fun setCharacterFrames() {

        for (i in accountInformation!!.heroes.indices) {
            val constraintLayoutCharacter = ConstraintLayout(applicationContext)
            constraintLayoutCharacter.id = i
            val frameParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, 500)
            val frame = ImageView(applicationContext)
            frame.id = 100 + i
            frame.layoutParams = frameParams
            if (accountInformation!!.heroes[i].hardcore) {
                frame.setImageResource(R.drawable.d3_character_frame_hardcore)
            } else {
                frame.setImageResource(R.drawable.d3_character_frame)
            }
            val portraitParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, 320)
            val portrait = ImageView(applicationContext)
            portrait.id = i + 1000
            portrait.setImageDrawable(portraits!![i])
            portrait.layoutParams = portraitParams
            val name = TextView(applicationContext)
            name.text = accountInformation!!.heroes[i].name
            name.setTextColor(Color.parseColor("#937a51"))
            name.textSize = 17f
            name.gravity = Gravity.CENTER
            val eliteKills = TextView(applicationContext)
            eliteKills.id = 10000 + i
            val eliteKillsText = accountInformation!!.heroes[i].kills.elites.toString() + " Elite Kills"
            eliteKills.text = eliteKillsText
            eliteKills.setTextColor(Color.parseColor("#a99877"))
            eliteKills.textSize = 13f
            eliteKills.gravity = Gravity.CENTER
            val level = TextView(applicationContext)
            level.id = 100000 + i
            level.text = accountInformation!!.heroes[i].level.toString()
            level.textSize = 15f
            level.gravity = Gravity.CENTER
            level.setTypeface(null, Typeface.BOLD)
            if (accountInformation!!.heroes[i].hardcore) {
                level.setTextColor(Color.parseColor("#b00000"))
            } else {
                level.setTextColor(Color.parseColor("#a99877"))
            }
            val linearLayoutSeasonal = LinearLayout(applicationContext)
            linearLayoutSeasonal.id = 1000000 + i
            linearLayoutSeasonal.orientation = LinearLayout.HORIZONTAL
            linearLayoutSeasonal.gravity = Gravity.CENTER
            linearLayoutSeasonal.addView(name)
            if (accountInformation!!.heroes[i].seasonal) {
                val leaf = ImageView(applicationContext)
                leaf.setImageDrawable(resources.getDrawable(R.drawable.leaf_seasonal, theme))
                linearLayoutSeasonal.addView(leaf)
            }
            constraintLayoutCharacter.addView(frame)
            constraintLayoutCharacter.addView(portrait)
            constraintLayoutCharacter.addView(linearLayoutSeasonal)
            constraintLayoutCharacter.addView(eliteKills)
            constraintLayoutCharacter.addView(level)
            val setFrame = ConstraintSet()
            setFrame.clone(constraintLayoutCharacter)
            setFrame.connect(frame.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            setFrame.connect(frame.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
            setFrame.connect(frame.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
            setFrame.connect(frame.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            setFrame.connect(portrait.id, ConstraintSet.TOP, frame.id, ConstraintSet.TOP, 25)
            setFrame.connect(portrait.id, ConstraintSet.LEFT, frame.id, ConstraintSet.LEFT, 0)
            setFrame.connect(portrait.id, ConstraintSet.RIGHT, frame.id, ConstraintSet.RIGHT, 0)
            setFrame.connect(linearLayoutSeasonal.id, ConstraintSet.TOP, portrait.id, ConstraintSet.BOTTOM, 0)
            setFrame.connect(linearLayoutSeasonal.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
            setFrame.connect(linearLayoutSeasonal.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
            setFrame.connect(linearLayoutSeasonal.id, ConstraintSet.BOTTOM, eliteKills.id, ConstraintSet.TOP, 5)
            setFrame.connect(eliteKills.id, ConstraintSet.TOP, linearLayoutSeasonal.id, ConstraintSet.BOTTOM, 0)
            setFrame.connect(eliteKills.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 10)
            setFrame.connect(eliteKills.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 50)
            setFrame.connect(eliteKills.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 20)
            setFrame.connect(level.id, ConstraintSet.TOP, linearLayoutSeasonal.id, ConstraintSet.BOTTOM, 0)
            setFrame.connect(level.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 360)
            setFrame.connect(level.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0)
            setFrame.connect(level.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 25)
            setFrame.applyTo(constraintLayoutCharacter)
            character_layout!!.addView(constraintLayoutCharacter)
            setOnClickCharacterFrame(constraintLayoutCharacter)
        }
    }

    private fun setOnClickCharacterFrame(constraintLayoutCharacter: ConstraintLayout) {
        constraintLayoutCharacter.setOnClickListener {
            for (j in accountInformation!!.heroes.indices) {
                if (j == constraintLayoutCharacter.id) {
                    Log.i("ID test", "" + accountInformation!!.heroes[j].id)
                    characterID = accountInformation!!.heroes[j].id
                }
            }
            displayFragment()
        }
    }

    private fun setProgression() {
        if (accountInformation!!.progression.act1) {
            prog_act1!!.setImageDrawable(resources.getDrawable(R.drawable.act1_done, theme))
        } else {
            prog_act1!!.setImageDrawable(resources.getDrawable(R.drawable.act1_not_done, theme))
        }
        if (accountInformation!!.progression.act2) {
            prog_act2!!.setImageDrawable(resources.getDrawable(R.drawable.act2_done, theme))
        } else {
            prog_act2!!.setImageDrawable(resources.getDrawable(R.drawable.act2_not_done, theme))
        }
        if (accountInformation!!.progression.act3) {
            prog_act3!!.setImageDrawable(resources.getDrawable(R.drawable.act3_done, theme))
        } else {
            prog_act3!!.setImageDrawable(resources.getDrawable(R.drawable.act3_not_done, theme))
        }
        if (accountInformation!!.progression.act4) {
            prog_act4!!.setImageDrawable(resources.getDrawable(R.drawable.act4_done, theme))
        } else {
            prog_act4!!.setImageDrawable(resources.getDrawable(R.drawable.act4_not_done, theme))
        }
        if (accountInformation!!.progression.act5) {
            prog_act5!!.setImageDrawable(resources.getDrawable(R.drawable.act5_done, theme))
        } else {
            prog_act5!!.setImageDrawable(resources.getDrawable(R.drawable.act5_not_done, theme))
        }
    }

    private fun callNextActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
        startActivity(intent)
    }

    fun getCharacterImage(heroes: List<Hero>, context: Context): List<Drawable> {
        val portraits: MutableList<Drawable> = ArrayList()
        for (i in heroes.indices) {
            var characterImage: Drawable
            when (heroes[i].classSlug) {
                "barbarian" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.barb_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.barb_female, context.theme)
                    portraits.add(characterImage)
                }
                "wizard" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.wizard_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.wizard_female, context.theme)
                    portraits.add(characterImage)
                }
                "demon-hunter" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.dh_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.dh_female, context.theme)
                    portraits.add(characterImage)
                }
                "witch-doctor" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.wd_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.wd_female, context.theme)
                    portraits.add(characterImage)
                }
                "necromancer" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.necro_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.necro_female, context.theme)
                    portraits.add(characterImage)
                }
                "monk" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.monk_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.monk_female, context.theme)
                    portraits.add(characterImage)
                }
                "crusader" -> if (accountInformation!!.heroes[i].gender == 0) {
                    characterImage = context.resources.getDrawable(R.drawable.crusader_male, context.theme)
                    portraits.add(characterImage)
                } else if (accountInformation!!.heroes[i].gender == 1) {
                    characterImage = context.resources.getDrawable(R.drawable.crusader_female, context.theme)
                    portraits.add(characterImage)
                }
            }
        }
        return portraits
    }

    private fun displayFragment() {
        val bundle = Bundle()
        bundle.putString("battletag", battleTag)
        bundle.putLong("id", characterID)
        bundle.putString("region", selectedRegion)
        val d3CharacterFragment = CharacterStatsPage()
        d3CharacterFragment.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.replace(R.id.fragment, d3CharacterFragment)
        fragmentTransaction.addToBackStack(null).commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
            super.onBackPressed()
        } else if (!URLConstants.loading && !fragment.isAdded) {
            val intent = Intent(this, GamesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    public fun showNoConnectionMessage(context: Context, responseCode: Int) {
        loadingCircle!!.visibility = View.GONE
        val builder = AlertDialog.Builder(context, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
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
        button.layoutParams = buttonParams
        button.background = context.getDrawable(R.drawable.buttonstyle)
        val button2 = Button(context)
        button2.textSize = 20f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.height = 100
        button2.layoutParams = buttonParams
        button2.background = context.getDrawable(R.drawable.buttonstyle)
        val buttonLayout = LinearLayout(context)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        when (responseCode) {
            in 400..499 -> {
                titleText.text = ErrorMessages.INFORMATION_OUTDATED
                messageText.text = ErrorMessages.LOGIN_TO_UPDATE
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button2)
            }
            500 -> {
                titleText.text = ErrorMessages.SERVERS_ERROR
                messageText.text = ErrorMessages.BLIZZ_SERVERS_DOWN
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button2)
            }
            else -> {
                titleText.text = ErrorMessages.NO_INTERNET
                messageText.text = ErrorMessages.TURN_ON_CONNECTION_MESSAGE
                button.text = ErrorMessages.RETRY
                button2.text = ErrorMessages.BACK
                buttonLayout.addView(button)
                buttonLayout.addView(button2)
            }
        }
        val dialog = builder.show()
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        dialog.setOnCancelListener { downloadAccountInformation() }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener { onBackPressed() }
    }

    companion object {
        private var accountInformation: AccountInformation? = null
    }
}
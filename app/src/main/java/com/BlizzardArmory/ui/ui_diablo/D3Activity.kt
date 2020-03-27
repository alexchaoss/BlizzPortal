package com.BlizzardArmory.ui.ui_diablo

import android.content.Context
import android.content.DialogInterface
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
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.UserInformation
import com.BlizzardArmory.connection.RequestQueueSingleton.Companion.getInstance
import com.BlizzardArmory.diablo.account.AccountInformation
import com.BlizzardArmory.diablo.account.Hero
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_starcraft.SC2Activity
import com.BlizzardArmory.ui.ui_warcraft.activity.WoWActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.d3_activity.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class D3Activity : AppCompatActivity() {
    private var prefs: SharedPreferences? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var portraits: List<Drawable>? = null
    private var battleTag = ""
    private var D3AccountInfo: JSONObject? = null
    private var paragonLevel: TextView? = null
    private var lifetimeKills: TextView? = null
    private var eliteKills: TextView? = null
    private var linearLayoutCharacters: LinearLayout? = null
    private var fallenCharacterLayout: LinearLayout? = null
    private var act1: ImageView? = null
    private var act2: ImageView? = null
    private var act3: ImageView? = null
    private var act4: ImageView? = null
    private var act5: ImageView? = null
    private var characterID: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.d3_activity)

        val btag = findViewById<TextView>(R.id.btag_header)

        loadingCircle.visibility = View.VISIBLE
        paragonLevel = findViewById(R.id.paragonLevel)
        lifetimeKills = findViewById(R.id.lifetime_kills)
        eliteKills = findViewById(R.id.elite_kills)
        linearLayoutCharacters = findViewById(R.id.character_layout)
        fallenCharacterLayout = findViewById(R.id.fallen_character_layout)
        act1 = findViewById(R.id.prog_act1)
        act2 = findViewById(R.id.prog_act2)
        act3 = findViewById(R.id.prog_act3)
        act4 = findViewById(R.id.prog_act4)
        act5 = findViewById(R.id.prog_act5)
        btag.text = UserInformation.getBattleTag()
        battleTag = intent.extras.getString("battletag")
        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        bnOAuth2Params = Objects.requireNonNull(intent.extras).getParcelable(BnConstants.BUNDLE_BNPARAMS)
        assert(bnOAuth2Params != null)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)
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
        try {
            val gson = GsonBuilder().create()
            val jsonRequest = JsonObjectRequest(Request.Method.GET, URLConstants.getBaseURLforAPI("") + URLConstants.getD3URLBtagProfile(battleTag)
                    + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper!!.accessToken, null,
                    Response.Listener { response: JSONObject? ->
                        accountInformation = gson.fromJson(response.toString(), AccountInformation::class.java)
                        sortHeroes()
                        portraits = accountInformation?.heroes?.let { getCharacterImage(it, applicationContext) }
                        val paragon = accountInformation?.paragonLevel.toString() +
                                " | " +
                                "<font color=#b00000>" +
                                accountInformation?.paragonLevelHardcore +
                                "</font>"
                        paragonLevel!!.text = Html.fromHtml(paragon, Html.FROM_HTML_MODE_LEGACY)
                        eliteKills!!.text = accountInformation?.kills?.elites.toString()
                        lifetimeKills!!.text = accountInformation?.kills?.monsters.toString()
                        setProgression()
                        setTimePlayed()
                        setCharacterFrames()
                        setFallenCharacterFrames()
                        loadingCircle!!.visibility = View.GONE
                    },
                    Response.ErrorListener { error: VolleyError ->
                        if (error.networkResponse == null) {
                            showNoConnectionMessage(this@D3Activity, 0)
                        } else {
                            showNoConnectionMessage(this@D3Activity, error.networkResponse.statusCode)
                        }
                    })
            getInstance(this).addToRequestQueue(jsonRequest)
            Log.i("json", D3AccountInfo.toString())
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
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
                fallenCharacterLayout!!.addView(frameLayout)
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
        accountInformation!!.heroes.sortWith(Comparator sort@{ hero1: Hero, hero2: Hero ->
            if (hero1.lastUpdated > hero2.lastUpdated) {
                return@sort -1
            }
            0
        })
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
            linearLayoutCharacters!!.addView(constraintLayoutCharacter)
            setOnClickCharacterFrame(constraintLayoutCharacter)
        }
    }

    private fun setOnClickCharacterFrame(constraintLayoutCharacter: ConstraintLayout) {
        constraintLayoutCharacter.setOnClickListener { v: View? ->
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
            act1!!.setImageDrawable(resources.getDrawable(R.drawable.act1_done, theme))
        } else {
            act1!!.setImageDrawable(resources.getDrawable(R.drawable.act1_not_done, theme))
        }
        if (accountInformation!!.progression.act2) {
            act2!!.setImageDrawable(resources.getDrawable(R.drawable.act2_done, theme))
        } else {
            act2!!.setImageDrawable(resources.getDrawable(R.drawable.act2_not_done, theme))
        }
        if (accountInformation!!.progression.act3) {
            act3!!.setImageDrawable(resources.getDrawable(R.drawable.act3_done, theme))
        } else {
            act3!!.setImageDrawable(resources.getDrawable(R.drawable.act3_not_done, theme))
        }
        if (accountInformation!!.progression.act4) {
            act4!!.setImageDrawable(resources.getDrawable(R.drawable.act4_done, theme))
        } else {
            act4!!.setImageDrawable(resources.getDrawable(R.drawable.act4_not_done, theme))
        }
        if (accountInformation!!.progression.act5) {
            act5!!.setImageDrawable(resources.getDrawable(R.drawable.act5_done, theme))
        } else {
            act5!!.setImageDrawable(resources.getDrawable(R.drawable.act5_not_done, theme))
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
        val d3CharacterFragment = D3CharacterFragment()
        d3CharacterFragment.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.replace(R.id.fragment, d3CharacterFragment)
        fragmentTransaction.addToBackStack(null).commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onBackPressed() {
        getInstance(this).requestQueue.cancelAll(this)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
            super.onBackPressed()
        }
        if (fragment == null) {
            val intent = Intent(this, GamesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    fun showNoConnectionMessage(context: Context, responseCode: Int) {
        loadingCircle!!.visibility = View.GONE
        URLConstants.loading = false
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
        buttonLayout.addView(button)
        if (responseCode == 404) {
            titleText.text = "Information Outdated"
            messageText.text = "Please login in game to update your account's information."
            button2.text = "Back"
            buttonLayout.removeView(button)
            buttonLayout.addView(button2)
        } else {
            titleText.text = "No Internet Connection"
            messageText.text = "Make sure that Wi-Fi or mobile data is turned on, then try again."
            button.text = "Retry"
            button2.text = "Back"
            buttonLayout.addView(button2)
        }
        val dialog = builder.show()
        Objects.requireNonNull(dialog.window).addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        dialog.setOnCancelListener { dialog1: DialogInterface? -> downloadAccountInformation() }
        button.setOnClickListener { v: View? -> dialog.cancel() }
        button2.setOnClickListener { v: View? -> onBackPressed() }
    }

    companion object {
        private var accountInformation: AccountInformation? = null
    }
}
package com.BlizzardArmory.ui.ui_diablo

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.diablo.account.AccountInformation
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfile
import com.BlizzardArmory.model.diablo.favorite.D3FavoriteProfiles
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_diablo.navigation.D3CharacterNav
import com.BlizzardArmory.util.MetricConversion
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.d3_activity.*
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*

class D3Fragment : Fragment() {
    private var prefs: SharedPreferences? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var battleTag: String? = ""
    private var selectedRegion: String? = ""
    private var characterID: Long = 0
    private var favorite: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.d3_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        battleTag = arguments?.getString("battletag")
        selectedRegion = arguments?.getString("region")
        prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        battlenetOAuth2Params = arguments?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)
        favorite = activity?.findViewById(R.id.favorite)
        favorite?.visibility = View.VISIBLE
        favorite?.setImageResource(R.drawable.ic_star_border_black_24dp)
        favorite?.tag = R.drawable.ic_star_border_black_24dp
        downloadAccountInformation()
    }

    private fun downloadAccountInformation() {
        URLConstants.loading = true
        loadingCircle.visibility = View.VISIBLE
        val call: Call<AccountInformation> = RetroClient.getClient.getD3Profile(battleTag, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<AccountInformation> {
            override fun onResponse(call: Call<AccountInformation>, response: retrofit2.Response<AccountInformation>) {
                if (response.isSuccessful) {
                    accountInformation = response.body()
                    manageFavorite(accountInformation!!)
                    sortHeroes()
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
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    URLConstants.loading = false
                    loadingCircle.visibility = View.GONE
                } else if (response.code() >= 400) {
                    showNoConnectionMessage(requireActivity(), response.code())
                }
            }

            override fun onFailure(call: Call<AccountInformation>, t: Throwable) {
                Log.e("Error", "trace", t)
                showNoConnectionMessage(requireActivity(), 0)
            }
        })
    }

    private fun manageFavorite(accountInformation: AccountInformation) {
        var favoriteProfiles = D3FavoriteProfiles()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = GsonBuilder().create()
        val favoriteCharactersString = prefs.getString("d3-favorites", "DEFAULT")
        if (favoriteCharactersString != null && favoriteCharactersString != "DEFAULT") {
            favoriteProfiles = gson.fromJson(favoriteCharactersString, D3FavoriteProfiles::class.java)
            var indexOfProfile = -1
            var indexTemp = 0
            for (profile in favoriteProfiles.profiles) {
                if (hasCharacter(profile)) {
                    indexOfProfile = indexTemp
                    favorite?.setImageResource(R.drawable.ic_star_black_24dp)
                    favorite?.tag = R.drawable.ic_star_black_24dp
                    break
                } else {
                    addToFavorite(favoriteProfiles, accountInformation, gson, prefs)
                }
                indexTemp++
            }
            deleteFavorite(favoriteProfiles, accountInformation, indexOfProfile, gson, prefs)
        } else {
            addToFavorite(favoriteProfiles, accountInformation, gson, prefs)
        }
    }

    private fun hasCharacter(profile: D3FavoriteProfile): Boolean {
        return battleTag == profile.battletag && selectedRegion == profile.region
    }

    private fun deleteFavorite(profiles: D3FavoriteProfiles, accountInformation: AccountInformation, indexOfProfile: Int, gson: Gson, prefs: SharedPreferences) {
        var favoriteCharactersString: String
        if (favorite?.tag == R.drawable.ic_star_black_24dp && indexOfProfile != -1) {
            favorite?.setOnClickListener {
                favorite?.setImageResource(R.drawable.ic_star_border_black_24dp)
                favorite?.tag = R.drawable.ic_star_border_black_24dp
                profiles.profiles.removeAt(indexOfProfile)
                favoriteCharactersString = gson.toJson(profiles)
                prefs.edit().putString("d3-favorites", favoriteCharactersString).apply()
                Toast.makeText(requireActivity(), "Profile removed from favorites", Toast.LENGTH_SHORT).show()
                addToFavorite(profiles, accountInformation, gson, prefs)

            }
        }
    }

    private fun addToFavorite(profiles: D3FavoriteProfiles, accountInformation: AccountInformation, gson: Gson, prefs: SharedPreferences) {
        var favoriteCharactersString: String
        favorite?.setOnClickListener {
            var containsProfile = false
            var indexOfProfile = 0
            for (profile in profiles.profiles) {
                if (hasCharacter(profile)) {
                    containsProfile = true
                    break
                }
                indexOfProfile++
            }
            if (!containsProfile) {
                favorite?.setImageResource(R.drawable.ic_star_black_24dp)
                favorite?.tag = R.drawable.ic_star_black_24dp
                val profile = D3FavoriteProfile(accountInformation, selectedRegion!!, battleTag!!)
                profiles.profiles.add(profile)
                favoriteCharactersString = gson.toJson(profiles)
                prefs.edit().putString("d3-favorites", favoriteCharactersString).apply()
                Toast.makeText(requireActivity(), "Profile added to favorites", Toast.LENGTH_SHORT).show()
                deleteFavorite(profiles, accountInformation, indexOfProfile, gson, prefs)
            }
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
                val frameLayout = ConstraintLayout(requireContext())
                frameLayout.id = (i + 1) * 2
                val frameLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                frameLayoutParams.marginEnd = 20
                frameLayout.layoutParams = frameLayoutParams

                val frameParams = ConstraintLayout.LayoutParams(MetricConversion.getDPMetric(113, requireActivity()), MetricConversion.getDPMetric(154, requireActivity()))
                val frame = ImageView(requireContext())
                frame.id = (i + 10) * 2
                Glide.with(this).load(URLConstants.getD3Asset(getFallenHeroFrame(i))).into(frame)
                val levelFrame = ImageView(requireContext())
                levelFrame.id = (i + 100) * 2
                levelFrame.setImageResource(R.drawable.fallen_hero_level)
                val name = TextView(requireContext())
                name.setTextColor(Color.parseColor("#a99877"))
                name.text = accountInformation!!.fallenHeroes[i].name
                name.id = (i + 1000) * 2
                val format = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
                val dateFormatted = Date(accountInformation!!.fallenHeroes[i].death.time.toLong() * 1000L)
                val dateString = format.format(dateFormatted)
                val date = TextView(requireContext())
                date.setTextColor(Color.parseColor("#937a51"))
                date.text = dateString
                date.textSize = 12f
                date.id = (i + 10000) * 2
                val level = TextView(requireContext())
                level.setTextColor(Color.parseColor("#b00000"))
                level.textSize = 15f
                level.text = accountInformation!!.fallenHeroes[i].level.toString()
                level.setTypeface(null, Typeface.BOLD)
                level.id = (i + 100000) * 2
                frameLayout.addView(frame, frameParams)
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
            fallen_heroes_scroll.visibility = View.GONE
            textView4.visibility = View.GONE
        }
    }

    private fun getFallenHeroFrame(i: Int): String {
        var imageName = ""
        when (accountInformation!!.fallenHeroes[i].class_) {
            "barbarian" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_barb_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_barb_dead"
            }
            "wizard" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_wizard_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_wizard_dead"
            }
            "demon-hunter" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_dh_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_dh_dead"
            }
            "witch-doctor" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_wd_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_wd_dead"
            }
            "necromancer" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_necromancer_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_necromancer_dead"
            }
            "monk" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_monk_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_monk_dead"
            }
            "crusader" -> if (accountInformation!!.fallenHeroes[i].gender == 0) {
                imageName = "male_crusader_dead"
            } else if (accountInformation!!.fallenHeroes[i].gender == 1) {
                imageName = "female_crusader_dead"
            }
        }
        return imageName
    }

    private fun sortHeroes() {
        accountInformation?.heroes = accountInformation!!.heroes.sortedBy { it.lastUpdated }.reversed()
    }

    private fun setCharacterFrames() {

        for (i in accountInformation!!.heroes.indices) {
            val constraintLayoutCharacter = ConstraintLayout(requireContext())
            constraintLayoutCharacter.id = i
            val frameParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, 500)
            val frame = ImageView(requireContext())
            frame.id = 100 + i
            frame.layoutParams = frameParams
            if (accountInformation!!.heroes[i].hardcore) {
                frame.setImageResource(R.drawable.d3_character_frame_hardcore)
            } else {
                frame.setImageResource(R.drawable.d3_character_frame)
            }
            val portraitParams = ConstraintLayout.LayoutParams(MetricConversion.getDPMetric(129, requireActivity()), MetricConversion.getDPMetric(100, requireActivity()))
            val portrait = ImageView(requireContext())
            portrait.id = i + 1000
            Glide.with(this).load(URLConstants.getD3Asset(getGender(i))).into(portrait)
            portrait.layoutParams = portraitParams
            val name = TextView(requireContext())
            name.text = accountInformation!!.heroes[i].name
            name.setTextColor(Color.parseColor("#937a51"))
            name.textSize = 17f
            name.gravity = Gravity.CENTER
            val eliteKills = TextView(requireContext())
            eliteKills.id = 10000 + i
            val eliteKillsText = accountInformation!!.heroes[i].kills.elites.toString() + " Elite Kills"
            eliteKills.text = eliteKillsText
            eliteKills.setTextColor(Color.parseColor("#a99877"))
            eliteKills.textSize = 13f
            eliteKills.gravity = Gravity.CENTER
            val level = TextView(requireContext())
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
            val linearLayoutSeasonal = LinearLayout(requireContext())
            linearLayoutSeasonal.id = 1000000 + i
            linearLayoutSeasonal.orientation = LinearLayout.HORIZONTAL
            linearLayoutSeasonal.gravity = Gravity.CENTER
            linearLayoutSeasonal.addView(name)
            if (accountInformation!!.heroes[i].seasonal) {
                val leaf = ImageView(requireContext())
                leaf.setImageDrawable(resources.getDrawable(R.drawable.leaf_seasonal, activity?.theme))
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
            Glide.with(this).load(URLConstants.getD3Asset("act1_done")).into(prog_act1)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act1_not_done")).into(prog_act1)
        }
        if (accountInformation!!.progression.act2) {
            Glide.with(this).load(URLConstants.getD3Asset("act2_done")).into(prog_act2)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act2_not_done")).into(prog_act2)
        }
        if (accountInformation!!.progression.act3) {
            Glide.with(this).load(URLConstants.getD3Asset("act3_done")).into(prog_act3)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act3_not_done")).into(prog_act3)
        }
        if (accountInformation!!.progression.act4) {
            Glide.with(this).load(URLConstants.getD3Asset("act4_done")).into(prog_act4)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act4_not_done")).into(prog_act4)
        }
        if (accountInformation!!.progression.act5) {
            Glide.with(this).load(URLConstants.getD3Asset("act5_done")).into(prog_act5)
        } else {
            Glide.with(this).load(URLConstants.getD3Asset("act5_not_done")).into(prog_act5)
        }
    }

    private fun getGender(i: Int): String {
        var imageName = ""
        val hero = accountInformation?.heroes?.get(i)!!
        when (hero.classSlug) {
            "barbarian" -> if (hero.gender == 0) {
                imageName = "barb_male"
            } else if (hero.gender == 1) {
                imageName = "barb_female"
            }
            "wizard" -> if (hero.gender == 0) {
                imageName = "wizard_male"
            } else if (hero.gender == 1) {
                imageName = "wizard_female"
            }
            "demon-hunter" -> if (hero.gender == 0) {
                imageName = "dh_male"
            } else if (hero.gender == 1) {
                imageName = "dh_female"
            }
            "witch-doctor" -> if (hero.gender == 0) {
                imageName = "wd_male"
            } else if (hero.gender == 1) {
                imageName = "wd_female"
            }
            "necromancer" -> if (hero.gender == 0) {
                imageName = "necro_male"
            } else if (hero.gender == 1) {
                imageName = "necro_female"
            }
            "monk" -> if (hero.gender == 0) {
                imageName = "monk_male"
            } else if (hero.gender == 1) {
                imageName = "monk_female"
            }
            "crusader" -> if (hero.gender == 0) {
                imageName = "crusader_male"
            } else if (hero.gender == 1) {
                imageName = "crusader_female"
            }
        }
        return imageName
    }

    private fun displayFragment() {
        val bundle = Bundle()
        bundle.putString("battletag", battleTag)
        bundle.putLong("id", characterID)
        bundle.putString("region", selectedRegion)
        val d3CharacterNav = D3CharacterNav()
        d3CharacterNav.arguments = bundle
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.replace(R.id.nav_fragment, d3CharacterNav, "d3nav")
        fragmentTransaction.addToBackStack(null).commit()
        parentFragmentManager.executePendingTransactions()
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
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
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
        button2.setOnClickListener {
            dialog.dismiss()
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    companion object {
        private var accountInformation: AccountInformation? = null
    }
}
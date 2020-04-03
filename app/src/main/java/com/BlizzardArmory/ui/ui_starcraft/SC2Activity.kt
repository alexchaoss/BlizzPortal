package com.BlizzardArmory.ui.ui_starcraft

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.text.Html.ImageGetter
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.UserInformation
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.connection.RequestQueueSingleton
import com.BlizzardArmory.starcraft.Player
import com.BlizzardArmory.starcraft.profile.Profile
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_diablo.DiabloProfileSearchDialog
import com.BlizzardArmory.ui.ui_overwatch.OWPlatformChoiceDialog
import com.BlizzardArmory.ui.ui_warcraft.activity.WoWActivity
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sc2_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SC2Activity : AppCompatActivity() {
    private var prefs: SharedPreferences? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var accountInformation = listOf<Player>()
    private var sc2Profile: Profile? = null
    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private lateinit var networkServices: NetworkServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sc2_activity)

        gson = GsonBuilder().create()
        retrofit = Retrofit.Builder().baseUrl(URLConstants.getBaseURLforAPI(MainActivity.selectedRegion)).addConverterFactory(GsonConverterFactory.create(gson!!)).build()
        networkServices = retrofit?.create(NetworkServices::class.java)!!

        val summaryBG = GradientDrawable()
        summaryBG.setStroke(6, Color.parseColor("#122a42"))
        summaryBG.setColor(Color.parseColor("#75091c2e"))
        val statsBG = GradientDrawable()
        statsBG.setStroke(6, Color.parseColor("#122a42"))
        statsBG.setColor(Color.parseColor("#75091c2e"))
        val snapshotBG = GradientDrawable()
        snapshotBG.setStroke(6, Color.parseColor("#122a42"))
        snapshotBG.setColor(Color.parseColor("#75091c2e"))
        val campaignBG = GradientDrawable()
        campaignBG.setStroke(6, Color.parseColor("#122a42"))
        campaignBG.setColor(Color.parseColor("#75091c2e"))
        summary.background = summaryBG
        snapshot.background = snapshotBG
        statistics.background = statsBG
        campaign.background = campaignBG

        val startColor = -0xff9934
        val endColor = 0x00000000
        val avatarShadow = GradientDrawable()
        avatarShadow.colors = intArrayOf(endColor, startColor)
        avatarShadow.setStroke(6, Color.parseColor("#0066cc"))
        avatarShadow.gradientType = GradientDrawable.RADIAL_GRADIENT
        avatarShadow.gradientRadius = 800.0f
        val raceImageBG = GradientDrawable()
        raceImageBG.colors = intArrayOf(endColor, startColor)
        raceImageBG.setStroke(6, Color.parseColor("#122a42"))
        raceImageBG.gradientType = GradientDrawable.RADIAL_GRADIENT
        raceImageBG.gradientRadius = 800.0f

        terran_image.setImageDrawable(raceImageBG)
        zerg_image.setImageDrawable(raceImageBG)
        protoss_image.setImageDrawable(raceImageBG)
        avatar.setImageDrawable(avatarShadow)

        loadingCircle.visibility = View.VISIBLE
        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        bnOAuth2Params = Objects.requireNonNull(intent.extras).getParcelable(BnConstants.BUNDLE_BNPARAMS)
        assert(bnOAuth2Params != null)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)
        gson = GsonBuilder().create()
        downloadAccountInformation()

        //Button calls
        wowButton.setOnClickListener { callNextActivity(WoWActivity::class.java) }
        diablo3Button.setOnClickListener { DiabloProfileSearchDialog.diabloPrompt(this@SC2Activity, bnOAuth2Params) }
        overwatchButton.setOnClickListener {
            OWPlatformChoiceDialog.myProfileChosen = false
            OWPlatformChoiceDialog.overwatchPrompt(this@SC2Activity, bnOAuth2Params)
        }
    }

    private fun downloadAccountInformation() {
        val call: Call<List<Player>> = networkServices.getSc2Player(UserInformation.getUserID(), "profile-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<List<Player>> {
            override fun onResponse(call: Call<List<Player>>, response: retrofit2.Response<List<Player>>) {
                when {
                    response.isSuccessful -> {
                        accountInformation = response.body()!!
                        val call2: Call<Profile> = networkServices.getSc2Profile(accountInformation[0].regionId, accountInformation[0].realmId, accountInformation[0].profileId, "profile-" + MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
                        call2.enqueue(object : Callback<Profile> {
                            override fun onResponse(call: Call<Profile>, response: retrofit2.Response<Profile>) {
                                when {
                                    response.isSuccessful -> {
                                        sc2Profile = response.body()
                                        setSummaryInformation()
                                        setSnapshotInformation()
                                        setStatisticsInformation()
                                        setRaceLevelInformation()
                                        setCampaignInformation()
                                        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                        loadingCircle!!.visibility = View.GONE
                                        URLConstants.loading = false
                                        downloadAvatar()
                                    }
                                    response.code() >= 400 -> {
                                        Log.e("Error", "Response code: " + response.code())
                                        showNoConnectionMessage(response.code())
                                    }
                                }
                            }

                            override fun onFailure(call: Call<Profile>, t: Throwable) {
                                Log.e("Error", t.localizedMessage)
                                showNoConnectionMessage(0)
                            }
                        })
                    }
                    response.code() >= 400 -> {
                        Log.e("Error", "Response code: " + response.code() + " " + response.message())
                        showNoConnectionMessage(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                showNoConnectionMessage(0)
            }
        })
    }

    private fun setCampaignInformation() {
        if (sc2Profile!!.campaign.difficultyCompleted.wingsOfLiberty != null) {
            when (sc2Profile!!.campaign.difficultyCompleted.wingsOfLiberty) {
                "CASUAL" -> {
                    wol_icon!!.setImageResource(R.drawable.campaign_badge_wol_casual)
                    campaign_wol!!.text = "Casual Campaign Ace"
                }
                "NORMAL" -> {
                    wol_icon!!.setImageResource(R.drawable.campaign_badge_lotv_casual)
                    campaign_wol!!.text = "Normal Campaign Ace"
                }
                "HARD" -> {
                    wol_icon!!.setImageResource(R.drawable.campaign_badge_wol_hard)
                    campaign_wol!!.text = "Hard Campaign Ace"
                }
                "BRUTAL" -> {
                    wol_icon!!.setImageResource(R.drawable.campaign_badge_wol_brutal)
                    campaign_wol!!.text = "Brutal Campaign Ace"
                }
            }
        }
        if (sc2Profile!!.campaign.difficultyCompleted.heartOfTheSwarm != null) {
            when (sc2Profile!!.campaign.difficultyCompleted.heartOfTheSwarm) {
                "CASUAL" -> {
                    hots_icon!!.setImageResource(R.drawable.campaign_badge_hots_casual)
                    campaign_hots!!.text = "Casual Campaign Ace"
                }
                "NORMAL" -> {
                    hots_icon!!.setImageResource(R.drawable.campaign_badge_hots_normal)
                    campaign_hots!!.text = "Normal Campaign Ace"
                }
                "HARD" -> {
                    hots_icon!!.setImageResource(R.drawable.campaign_badge_hots_hard)
                    campaign_hots!!.text = "Hard Campaign Ace"
                }
                "BRUTAL" -> {
                    hots_icon!!.setImageResource(R.drawable.campaign_badge_hots_brutal)
                    campaign_hots!!.text = "Brutal Campaign Ace"
                }
            }
        }
        if (sc2Profile!!.campaign.difficultyCompleted.legacyOfTheVoid != null) {
            when (sc2Profile!!.campaign.difficultyCompleted.legacyOfTheVoid) {
                "CASUAL" -> {
                    lotv_icon!!.setImageResource(R.drawable.campaign_badge_lotv_casual)
                    campaign_lotv!!.text = "Casual Campaign Ace"
                }
                "NORMAL" -> {
                    lotv_icon!!.setImageResource(R.drawable.campaign_badge_lotv_normal)
                    campaign_lotv!!.text = "Normal Campaign Ace"
                }
                "HARD" -> {
                    lotv_icon!!.setImageResource(R.drawable.campaign_badge_lotv_hard)
                    campaign_lotv!!.text = "Hard Campaign Ace"
                }
                "BRUTAL" -> {
                    lotv_icon!!.setImageResource(R.drawable.campaign_badge_lotv_brutal)
                    campaign_lotv!!.text = "Brutal Campaign Ace"
                }
            }
        }
    }

    private fun setRaceLevelInformation() {
        val terranTemp = "Level " + sc2Profile!!.swarmLevels.terran.level
        val zergTemp = "Level " + sc2Profile!!.swarmLevels.zerg.level
        val protossTemp = "Level " + sc2Profile!!.swarmLevels.protoss.level
        terran_level!!.text = terranTemp
        zerg_level!!.text = zergTemp
        protoss_level!!.text = protossTemp
    }

    private fun setStatisticsInformation() {
        terran_wins!!.text = sc2Profile!!.career.terranWins.toString()
        zerg_wins!!.text = sc2Profile!!.career.zergWins.toString()
        protoss_wins!!.text = sc2Profile!!.career.protossWins.toString()
        season_played!!.text = sc2Profile!!.career.totalGamesThisSeason.toString()
        career_played!!.text = sc2Profile!!.career.totalCareerGames.toString()
        if (sc2Profile!!.career.best1v1Finish.leagueName != null) {
            setSnapshotIcons(sc2Profile!!.career.best1v1Finish.leagueName, 500, best_one_icon)
            var temp = sc2Profile!!.career.best1v1Finish.leagueName.substring(1).toLowerCase(Locale.ROOT)
            temp = sc2Profile!!.career.best1v1Finish.leagueName.substring(0, 1) + temp
            best_one!!.text = temp
        } else {
            best_one!!.visibility = View.GONE
        }
        if (sc2Profile!!.career.bestTeamFinish.leagueName != null) {
            setSnapshotIcons(sc2Profile!!.career.bestTeamFinish.leagueName, 500, best_team_icon)
            var temp = sc2Profile!!.career.bestTeamFinish.leagueName.substring(1).toLowerCase(Locale.ROOT)
            temp = sc2Profile!!.career.bestTeamFinish.leagueName.substring(0, 1) + temp
            best_team!!.text = temp
        } else {
            best_team!!.visibility = View.GONE
        }
    }

    private fun setSnapshotInformation() {
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.get1v1().leagueName, sc2Profile!!.snapshot.seasonSnapshot.get1v1().rank, one_one)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.archon.leagueName, sc2Profile!!.snapshot.seasonSnapshot.archon.rank, archon)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.get2v2().leagueName, sc2Profile!!.snapshot.seasonSnapshot.get2v2().rank, two_two)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.get3v3().leagueName, sc2Profile!!.snapshot.seasonSnapshot.get3v3().rank, three_three)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.get4v4().leagueName, sc2Profile!!.snapshot.seasonSnapshot.get4v4().rank, four_four)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.get1v1().totalGames, sc2Profile!!.snapshot.seasonSnapshot.get1v1().totalWins, one_one_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.archon.totalGames, sc2Profile!!.snapshot.seasonSnapshot.archon.totalWins, archon_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.get2v2().totalGames, sc2Profile!!.snapshot.seasonSnapshot.get2v2().totalWins, two_two_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.get3v3().totalGames, sc2Profile!!.snapshot.seasonSnapshot.get3v3().totalWins, three_three_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.get4v4().totalGames, sc2Profile!!.snapshot.seasonSnapshot.get4v4().totalWins, four_four_text)
    }

    private fun setSnapshotText(totalGames: Int, totalWins: Int, text: TextView?) {
        if (totalGames != 0) {
            val tempText = " - $totalGames Games | $totalWins Wins"
            text!!.text = tempText
        }
    }

    private fun setSnapshotIcons(league: String?, rank: Int, icon: ImageView?) {
        if (league != null) {
            when (league) {
                "GRANDMASTER" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.grandmaster_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.grandmaster_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.grandmaster_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.grandmaster_rank)
                    }
                }
                "MASTER" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.master_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.master_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.master_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.master_rank)
                    }
                }
                "DIAMOND" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.diamond_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.diamond_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.diamond_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.diamond_rank)
                    }
                }
                "PLATINUM" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.platinum_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.platinum_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.platinum_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.platinum_rank)
                    }
                }
                "GOLD" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.gold_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.gold_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.gold_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.gold_rank)
                    }
                }
                "SILVER" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.silver_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.silver_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.silver_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.silver_rank)
                    }
                }
                "BRONZE" -> when {
                    rank <= 16 -> {
                        icon!!.setImageResource(R.drawable.bronze_rank_16)
                    }
                    rank <= 50 -> {
                        icon!!.setImageResource(R.drawable.bronze_rank_50)
                    }
                    rank < 200 -> {
                        icon!!.setImageResource(R.drawable.bronze_rank_100)
                    }
                    else -> {
                        icon!!.setImageResource(R.drawable.bronze_rank)
                    }
                }
            }
        }
    }

    private fun setSummaryInformation() {
        total_level_text!!.text = sc2Profile!!.swarmLevels.level.toString()
        name!!.text = sc2Profile!!.summary.displayName
        if (sc2Profile!!.summary.clanName != null) {
            val clanName = "[" + sc2Profile!!.summary.clanTag + "] " + sc2Profile!!.summary.clanName
            clan!!.text = clanName
        } else {
            clan!!.visibility = View.GONE
        }
        achievement_points!!.text = Html.fromHtml("<img src=\"achievement_sc2\">" + sc2Profile!!.summary.totalAchievementPoints, Html.FROM_HTML_MODE_LEGACY, ImageGetter { source: String? ->
            val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
            val drawable = resources.getDrawable(resourceId, this@SC2Activity.theme)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable
        }, null)
    }

    private fun downloadAvatar() {
        Picasso.get().load(accountInformation[0].avatarUrl).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                avatar.background = BitmapDrawable(resources, bitmap)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {}
        })
    }

    private fun callNextActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
        startActivity(intent)
    }

    override fun onBackPressed() {
        RequestQueueSingleton.getInstance(this).requestQueue.cancelAll(this)
        val intent = Intent(this, GamesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        this.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingCircle!!.visibility = View.GONE
        URLConstants.loading = false
        val builder = AlertDialog.Builder(this, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        val titleText = TextView(this)
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        val messageText = TextView(this)
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.setPadding(0, 0, 0, 20)
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        val button = Button(this)
        button.textSize = 20f
        button.setTextColor(Color.WHITE)
        button.gravity = Gravity.CENTER
        button.width = 200
        button.height = 100
        button.layoutParams = buttonParams
        button.background = this.getDrawable(R.drawable.buttonstyle)
        val button2 = Button(this)
        button2.textSize = 20f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.height = 100
        button2.layoutParams = buttonParams
        button2.background = this.getDrawable(R.drawable.buttonstyle)
        val buttonLayout = LinearLayout(this)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        buttonLayout.addView(button)
        if (responseCode == 404) {
            titleText.text = "The account could not be found"
            messageText.text = "There is no Starcraft 2 profile associated with this account."
            button.text = "OK"
        } else if (responseCode == 403) {
            titleText.text = "Unavailable"
            messageText.text = "The Starcraft 2 community servers are down temporarily."
            button.text = "Back"
        } else if (responseCode == 500) {
            titleText.text = "Server error"
            messageText.text = "There seems to be some problems with Blizzard's servers, please try again later"
            button.text = "Back"
        } else {
            titleText.text = "No Internet Connection"
            messageText.text = "Make sure that Wi-Fi or mobile data is turned on, then try again."
            button.text = "Retry"
            button2.text = "Back"
            buttonLayout.addView(button2)
        }
        val dialog = builder.show()
        Objects.requireNonNull(dialog?.window)?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        if (responseCode == 404 || responseCode == 403 || responseCode == 500) {
            dialog.setOnCancelListener { finish() }
        } else {
            dialog.setOnCancelListener { downloadAccountInformation() }
        }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener { onBackPressed() }
    }
}
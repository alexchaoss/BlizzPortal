package com.BlizzardArmory.ui.ui_starcraft

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.ErrorMessages
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.starcraft.Player
import com.BlizzardArmory.model.starcraft.profile.Profile
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.sc2_activity.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class SC2Fragment : Fragment() {
    private var prefs: SharedPreferences? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private var accountInformation = listOf<Player>()
    private var sc2Profile: Profile? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sc2_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        getRaceImage(zerg_image, "summary_racelevel_zerg_image")
        getRaceImage(protoss_image, "summary_racelevel_protoss_image")
        getRaceImage(terran_image, "summary_racelevel_terran_image")

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
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)
        downloadAccountInformation()
    }

    private fun getRaceImage(imageView: ImageView, name: String) {
        Glide.with(this).load(URLConstants.getSC2Asset(name)).into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageView.background = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    private fun downloadAccountInformation() {
        URLConstants.loading = true
        val call: Call<List<Player>> = RetroClient.getClient.getSc2Player(GamesActivity.userInformation!!.userID, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<List<Player>> {
            override fun onResponse(call: Call<List<Player>>, response: retrofit2.Response<List<Player>>) {
                when {
                    response.isSuccessful -> {
                        accountInformation = response.body()!!
                        if (accountInformation.isNotEmpty()) {
                            val call2: Call<Profile> = RetroClient.getClient.getSc2Profile(parseRegionId(accountInformation[0].regionId), accountInformation[0].realmId,
                                    accountInformation[0].profileId, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
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
                                            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
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
                                    Log.e("Error", "trace", t)
                                    showNoConnectionMessage(0)
                                }
                            })
                        } else {
                            showNoConnectionMessage(404)
                        }
                    }
                    response.code() >= 400 -> {
                        Log.e("Error", "Response code: " + response.code() + " " + response.message())
                        showNoConnectionMessage(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<List<Player>>, t: Throwable) {
                Log.e("Error", "trace", t)
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

    private fun parseRegionId(regionId: Int): String {
        when (regionId) {
            1 -> return "US"
            2 -> return "EU"
            3 -> return "KO"
            5 -> return "CN"
        }
        return "US"
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
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.oneVone.leagueName, sc2Profile!!.snapshot.seasonSnapshot.oneVone.rank, one_one)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.archon.leagueName, sc2Profile!!.snapshot.seasonSnapshot.archon.rank, archon)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.twoVtwo.leagueName, sc2Profile!!.snapshot.seasonSnapshot.twoVtwo.rank, two_two)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.threeVthree.leagueName, sc2Profile!!.snapshot.seasonSnapshot.threeVthree.rank, three_three)
        setSnapshotIcons(sc2Profile!!.snapshot.seasonSnapshot.fourVfour.leagueName, sc2Profile!!.snapshot.seasonSnapshot.fourVfour.rank, four_four)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.oneVone.totalGames, sc2Profile!!.snapshot.seasonSnapshot.oneVone.totalWins, one_one_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.archon.totalGames, sc2Profile!!.snapshot.seasonSnapshot.archon.totalWins, archon_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.twoVtwo.totalGames, sc2Profile!!.snapshot.seasonSnapshot.twoVtwo.totalWins, two_two_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.threeVthree.totalGames, sc2Profile!!.snapshot.seasonSnapshot.threeVthree.totalWins, three_three_text)
        setSnapshotText(sc2Profile!!.snapshot.seasonSnapshot.fourVfour.totalGames, sc2Profile!!.snapshot.seasonSnapshot.fourVfour.totalWins, four_four_text)
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
        if (sc2Profile?.swarmLevels != null && sc2Profile?.swarmLevels?.level != null) {
            total_level_text!!.text = sc2Profile!!.swarmLevels.level.toString()
        } else {
            total_level_text!!.text = "0"
        }
        name!!.text = sc2Profile!!.summary.displayName
        if (sc2Profile!!.summary.clanName != null) {
            val clanName = "[" + sc2Profile!!.summary.clanTag + "] " + sc2Profile!!.summary.clanName
            clan!!.text = clanName
        } else {
            clan!!.visibility = View.GONE
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            achievement_points!!.text = Html.fromHtml("<img src=\"achievement_sc2\">" + sc2Profile!!.summary.totalAchievementPoints, Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                val drawable = resources.getDrawable(resourceId, requireActivity().theme)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                drawable
            }, null)
        } else {
            achievement_points!!.text = Html.fromHtml("<img src=\"achievement_sc2\">" + sc2Profile!!.summary.totalAchievementPoints, { source: String? ->
                val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                val drawable = resources.getDrawable(resourceId, requireActivity().theme)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                drawable
            }, null)
        }
    }

    private fun downloadAvatar() {
        Glide.with(this).load(accountInformation[0].avatarUrl).into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                avatar.background = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    private fun showNoConnectionMessage(responseCode: Int) {
        loadingCircle!!.visibility = View.GONE
        URLConstants.loading = false
        val builder = AlertDialog.Builder(requireActivity(), R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        val titleText = TextView(requireActivity())
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        val messageText = TextView(requireActivity())
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.setPadding(0, 0, 0, 20)
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        val button = Button(requireActivity())
        button.textSize = 20f
        button.setTextColor(Color.WHITE)
        button.gravity = Gravity.CENTER
        button.width = 200
        button.height = 100
        button.layoutParams = buttonParams
        button.background = requireActivity().getDrawable(R.drawable.buttonstyle)
        val button2 = Button(requireActivity())
        button2.textSize = 20f
        button2.setTextColor(Color.WHITE)
        button2.gravity = Gravity.CENTER
        button2.width = 200
        button2.height = 100
        button2.layoutParams = buttonParams
        button2.background = requireActivity().getDrawable(R.drawable.buttonstyle)
        val buttonLayout = LinearLayout(requireActivity())
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        buttonLayout.addView(button)
        when (responseCode) {
            404 -> {
                titleText.text = ErrorMessages.ACCOUNT_NOT_FOUND
                messageText.text = ErrorMessages.SC2_ACCOUNT_NOT_FOUND
                button.text = ErrorMessages.OK
            }
            403 -> {
                titleText.text = ErrorMessages.UNAVAILABLE
                messageText.text = ErrorMessages.SC2_SERVERS_DOWN
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
        val dialog = builder.show()
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val linearLayout = LinearLayout(requireActivity())
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(20, 20, 20, 20)
        linearLayout.addView(titleText)
        linearLayout.addView(messageText)
        linearLayout.addView(buttonLayout)
        dialog.addContentView(linearLayout, layoutParams)
        if (responseCode == 404 || responseCode == 403 || responseCode == 500) {
            dialog.setOnCancelListener { parentFragmentManager.beginTransaction().remove(this).commit() }
        } else {
            dialog.setOnCancelListener { downloadAccountInformation() }
        }
        button.setOnClickListener { dialog.cancel() }
        button2.setOnClickListener {
            dialog.dismiss()
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}
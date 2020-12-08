package com.BlizzardArmory.ui.starcraft

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import com.BlizzardArmory.BuildConfig
import com.BlizzardArmory.R
import com.BlizzardArmory.databinding.Sc2FragmentBinding
import com.BlizzardArmory.network.ErrorMessages
import com.BlizzardArmory.network.URLConstants
import com.BlizzardArmory.network.oauth.BattlenetConstants
import com.BlizzardArmory.network.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.ui.navigation.GamesActivity
import com.BlizzardArmory.ui.news.NewsPageFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class SC2Fragment : Fragment() {
    private var prefs: SharedPreferences? = null
    private lateinit var errorMessages: ErrorMessages
    
    private var _binding: Sc2FragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SC2ViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addOnBackPressCallback(activity as GamesActivity)
        _binding = Sc2FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorMessages = ErrorMessages(this.resources)
        setObservers()
        prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        viewModel.getBnetParams().value = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
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
        binding.summary.background = summaryBG
        binding.snapshot.background = snapshotBG
        binding.statistics.background = statsBG
        binding.campaign.background = campaignBG

        getRaceImage(binding.zergImage, "summary_racelevel_zerg_image")
        getRaceImage(binding.protossImage, "summary_racelevel_protoss_image")
        getRaceImage(binding.terranImage, "summary_racelevel_terran_image")

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

        binding.terranImage.setImageDrawable(raceImageBG)
        binding.zergImage.setImageDrawable(raceImageBG)
        binding.protossImage.setImageDrawable(raceImageBG)
        binding.avatar.setImageDrawable(avatarShadow)

        binding.loadingCircle.visibility = View.VISIBLE
    }

    private fun setObservers() {
        viewModel.getBnetParams().observe(viewLifecycleOwner, {
            viewModel.battlenetOAuth2Helper = BattlenetOAuth2Helper(viewModel.getBnetParams().value!!)
            viewModel.downloadAccountInformation()
        })

        viewModel.getAccount().observe(viewLifecycleOwner, {
            if (it.size > 1) {
                binding.loadingCircle.visibility = View.GONE
                val dialog = DialogPrompt(requireContext())
                dialog.addTitle("Choose Account", 20F, "title")
                it.forEach { player ->
                    dialog.addButton("${viewModel.parseRegionId(player.regionId)} - ${player.name}", 17F, {
                        viewModel.downloadProfile(player)
                        binding.loadingCircle.visibility = View.VISIBLE
                        dialog.cancel()
                    }, player.profileId)
                    val button = dialog.tagMap[player.profileId] as Button

                }
                dialog.show()
            } else {
                viewModel.downloadProfile(it[0])
            }
        })

        viewModel.getProfile().observe(viewLifecycleOwner, {
            setSummaryInformation()
            setSnapshotInformation()
            setStatisticsInformation()
            setRaceLevelInformation()
            setCampaignInformation()
            downloadAvatar()
        })

        viewModel.getErrorCode().observe(viewLifecycleOwner, {
            showNoConnectionMessage(it)
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

    private fun getRaceImage(imageView: ImageView, name: String) {
        Glide.with(this).load(URLConstants.getSC2Asset(name)).into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageView.background = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    private fun setCampaignInformation() {
        if (viewModel.getProfile().value!!.campaign.difficultyCompleted.wingsOfLiberty != null) {
            when (viewModel.getProfile().value!!.campaign.difficultyCompleted.wingsOfLiberty) {
                "CASUAL" -> {
                    binding.wolIcon.setImageResource(R.drawable.campaign_badge_wol_casual)
                    binding.campaignWol.text = "Casual Campaign Ace"
                }
                "NORMAL" -> {
                    binding.wolIcon.setImageResource(R.drawable.campaign_badge_lotv_casual)
                    binding.campaignWol.text = "Normal Campaign Ace"
                }
                "HARD" -> {
                    binding.wolIcon.setImageResource(R.drawable.campaign_badge_wol_hard)
                    binding.campaignWol.text = "Hard Campaign Ace"
                }
                "BRUTAL" -> {
                    binding.wolIcon.setImageResource(R.drawable.campaign_badge_wol_brutal)
                    binding.campaignWol.text = "Brutal Campaign Ace"
                }
            }
        }
        if (viewModel.getProfile().value!!.campaign.difficultyCompleted.heartOfTheSwarm != null) {
            when (viewModel.getProfile().value!!.campaign.difficultyCompleted.heartOfTheSwarm) {
                "CASUAL" -> {
                    binding.hotsIcon.setImageResource(R.drawable.campaign_badge_hots_casual)
                    binding.campaignHots.text = "Casual Campaign Ace"
                }
                "NORMAL" -> {
                    binding.hotsIcon.setImageResource(R.drawable.campaign_badge_hots_normal)
                    binding.campaignHots.text = "Normal Campaign Ace"
                }
                "HARD" -> {
                    binding.hotsIcon.setImageResource(R.drawable.campaign_badge_hots_hard)
                    binding.campaignHots.text = "Hard Campaign Ace"
                }
                "BRUTAL" -> {
                    binding.hotsIcon.setImageResource(R.drawable.campaign_badge_hots_brutal)
                    binding.campaignHots.text = "Brutal Campaign Ace"
                }
            }
        }
        if (viewModel.getProfile().value!!.campaign.difficultyCompleted.legacyOfTheVoid != null) {
            when (viewModel.getProfile().value!!.campaign.difficultyCompleted.legacyOfTheVoid) {
                "CASUAL" -> {
                    binding.lotvIcon.setImageResource(R.drawable.campaign_badge_lotv_casual)
                    binding.campaignLotv.text = "Casual Campaign Ace"
                }
                "NORMAL" -> {
                    binding.lotvIcon.setImageResource(R.drawable.campaign_badge_lotv_normal)
                    binding.campaignLotv.text = "Normal Campaign Ace"
                }
                "HARD" -> {
                    binding.lotvIcon.setImageResource(R.drawable.campaign_badge_lotv_hard)
                    binding.campaignLotv.text = "Hard Campaign Ace"
                }
                "BRUTAL" -> {
                    binding.lotvIcon.setImageResource(R.drawable.campaign_badge_lotv_brutal)
                    binding.campaignLotv.text = "Brutal Campaign Ace"
                }
            }
        }
    }

    private fun setRaceLevelInformation() {
        val terranTemp = "Level " + viewModel.getProfile().value!!.swarmLevels.terran.level
        val zergTemp = "Level " + viewModel.getProfile().value!!.swarmLevels.zerg.level
        val protossTemp = "Level " + viewModel.getProfile().value!!.swarmLevels.protoss.level
        binding.terranLevel.text = terranTemp
        binding.zergLevel.text = zergTemp
        binding.protossLevel.text = protossTemp
    }

    private fun setStatisticsInformation() {
        binding.terranWins.text = viewModel.getProfile().value!!.career.terranWins.toString()
        binding.zergWins.text = viewModel.getProfile().value!!.career.zergWins.toString()
        binding.protossWins.text = viewModel.getProfile().value!!.career.protossWins.toString()
        binding.seasonPlayed.text = viewModel.getProfile().value!!.career.totalGamesThisSeason.toString()
        binding.careerPlayed.text = viewModel.getProfile().value!!.career.totalCareerGames.toString()
        if (viewModel.getProfile().value!!.career.best1v1Finish.leagueName != null) {
            setSnapshotIcons(viewModel.getProfile().value!!.career.best1v1Finish.leagueName, 500, binding.bestOneIcon)
            var temp = viewModel.getProfile().value!!.career.best1v1Finish.leagueName.substring(1).toLowerCase(Locale.ROOT)
            temp = viewModel.getProfile().value!!.career.best1v1Finish.leagueName.substring(0, 1) + temp
            binding.bestOne.text = temp
        } else {
            binding.bestOne.visibility = View.GONE
        }
        if (viewModel.getProfile().value!!.career.bestTeamFinish.leagueName != null) {
            setSnapshotIcons(viewModel.getProfile().value!!.career.bestTeamFinish.leagueName, 500, binding.bestTeamIcon)
            var temp = viewModel.getProfile().value!!.career.bestTeamFinish.leagueName.substring(1).toLowerCase(Locale.ROOT)
            temp = viewModel.getProfile().value!!.career.bestTeamFinish.leagueName.substring(0, 1) + temp
            binding.bestTeam.text = temp
        } else {
            binding.bestTeam.visibility = View.GONE
        }
    }

    private fun setSnapshotInformation() {
        setSnapshotIcons(viewModel.getProfile().value!!.snapshot.seasonSnapshot.oneVone.leagueName, viewModel.getProfile().value!!.snapshot.seasonSnapshot.oneVone.rank, binding.oneOne)
        setSnapshotIcons(viewModel.getProfile().value!!.snapshot.seasonSnapshot.archon.leagueName, viewModel.getProfile().value!!.snapshot.seasonSnapshot.archon.rank, binding.archon)
        setSnapshotIcons(viewModel.getProfile().value!!.snapshot.seasonSnapshot.twoVtwo.leagueName, viewModel.getProfile().value!!.snapshot.seasonSnapshot.twoVtwo.rank, binding.twoTwo)
        setSnapshotIcons(viewModel.getProfile().value!!.snapshot.seasonSnapshot.threeVthree.leagueName, viewModel.getProfile().value!!.snapshot.seasonSnapshot.threeVthree.rank, binding.threeThree)
        setSnapshotIcons(viewModel.getProfile().value!!.snapshot.seasonSnapshot.fourVfour.leagueName, viewModel.getProfile().value!!.snapshot.seasonSnapshot.fourVfour.rank, binding.fourFour)
        setSnapshotText(viewModel.getProfile().value!!.snapshot.seasonSnapshot.oneVone.totalGames, viewModel.getProfile().value!!.snapshot.seasonSnapshot.oneVone.totalWins, binding.oneOneText)
        setSnapshotText(viewModel.getProfile().value!!.snapshot.seasonSnapshot.archon.totalGames, viewModel.getProfile().value!!.snapshot.seasonSnapshot.archon.totalWins, binding.archonText)
        setSnapshotText(viewModel.getProfile().value!!.snapshot.seasonSnapshot.twoVtwo.totalGames, viewModel.getProfile().value!!.snapshot.seasonSnapshot.twoVtwo.totalWins, binding.twoTwoText)
        setSnapshotText(viewModel.getProfile().value!!.snapshot.seasonSnapshot.threeVthree.totalGames, viewModel.getProfile().value!!.snapshot.seasonSnapshot.threeVthree.totalWins, binding.threeThreeText)
        setSnapshotText(viewModel.getProfile().value!!.snapshot.seasonSnapshot.fourVfour.totalGames, viewModel.getProfile().value!!.snapshot.seasonSnapshot.fourVfour.totalWins, binding.fourFourText)
    }

    private fun setSnapshotText(totalGames: Int, totalWins: Int, text: TextView?) {
        if (totalGames != 0) {
            val tempText = " - $totalGames Games | $totalWins Wins"
            text!!.text = tempText
        }
    }

    private fun setSnapshotIcons(league: String?, rank: Int, icon: ImageView) {

        if (league != null) {
            Log.i("LEAGUE", league)
            when (league) {
                "GRANDMASTER" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.grandmaster_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.grandmaster_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.grandmaster_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.grandmaster_rank)
                    }
                }
                "MASTER" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.master_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.master_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.master_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.master_rank)
                    }
                }
                "DIAMOND" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.diamond_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.diamond_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.diamond_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.diamond_rank)
                    }
                }
                "PLATINUM" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.platinum_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.platinum_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.platinum_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.platinum_rank)
                    }
                }
                "GOLD" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.gold_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.gold_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.gold_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.gold_rank)
                    }
                }
                "SILVER" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.silver_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.silver_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.silver_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.silver_rank)
                    }
                }
                "BRONZE" -> when {
                    rank <= 16 -> {
                        icon.setImageResource(R.drawable.bronze_rank_16)
                    }
                    rank <= 50 -> {
                        icon.setImageResource(R.drawable.bronze_rank_50)
                    }
                    rank < 200 -> {
                        icon.setImageResource(R.drawable.bronze_rank_100)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.bronze_rank)
                    }
                }
            }
        }
    }

    private fun setSummaryInformation() {
        if (viewModel.getProfile().value!!.swarmLevels != null && viewModel.getProfile().value!!.swarmLevels?.level != null) {
            binding.totalLevelText.text = viewModel.getProfile().value!!.swarmLevels.level.toString()
        } else {
            binding.totalLevelText.text = "0"
        }
        binding.name.text = viewModel.getProfile().value!!.summary.displayName
        if (viewModel.getProfile().value!!.summary.clanName != null) {
            val clanName = "[" + viewModel.getProfile().value!!.summary.clanTag + "] " + viewModel.getProfile().value!!.summary.clanName
            binding.clan.text = clanName
        } else {
            binding.clan.visibility = View.GONE
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.achievementPoints.text = Html.fromHtml("<img src=\"achievement_sc2\">" + viewModel.getProfile().value!!.summary.totalAchievementPoints, Html.FROM_HTML_MODE_LEGACY, { source: String? ->
                val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                val drawable = resources.getDrawable(resourceId, requireActivity().theme)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                drawable
            }, null)
        } else {
            binding.achievementPoints.text = Html.fromHtml("<img src=\"achievement_sc2\">" + viewModel.getProfile().value!!.summary.totalAchievementPoints, { source: String? ->
                val resourceId = resources.getIdentifier(source, "drawable", BuildConfig.APPLICATION_ID)
                val drawable = resources.getDrawable(resourceId, requireActivity().theme)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                drawable
            }, null)
        }
    }

    private fun downloadAvatar() {
        Glide.with(this).load(viewModel.getProfile().value!!.summary.portrait).into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                binding.avatar.background = resource
                binding.loadingCircle.visibility = View.GONE
                URLConstants.loading = false
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    private fun getErrorMessage(responseCode: Int): String {
        return when (responseCode) {
            404 -> {
                errorMessages.SC2_ACCOUNT_NOT_FOUND
            }
            503, 403 -> {
                errorMessages.SC2_SERVERS_DOWN
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
                errorMessages.ACCOUNT_NOT_FOUND
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
                            viewModel.downloadAccountInformation()
                            binding.loadingCircle.visibility = View.VISIBLE
                        },
                        {
                            dialog.cancel()
                            NewsPageFragment.addOnBackPressCallback(activity as GamesActivity)
                            parentFragmentManager.popBackStack()
                        },
                        "retry", "back").show()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun localeSelectedReceived(LocaleSelectedEvent: LocaleSelectedEvent) {
        activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
    }

    companion object{
        fun addOnBackPressCallback(activity: GamesActivity){

                activity.onBackPressedDispatcher.addCallback {
                    if (!URLConstants.loading) {
                        NewsPageFragment.addOnBackPressCallback(activity)
                        activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                }
        }
    }
}
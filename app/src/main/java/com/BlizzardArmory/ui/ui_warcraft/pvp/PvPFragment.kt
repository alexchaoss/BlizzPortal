package com.BlizzardArmory.ui.ui_warcraft.pvp


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.databinding.WowPvpFragmentBinding
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.bumptech.glide.Glide
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import java.util.*


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class PvPFragment : Fragment(){

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null

    private var _binding: WowPvpFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getString(CHARACTER)
            realm = it.getString(REALM)
            media = it.getString(MEDIA)
            region = it.getString(REGION)
        }
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WowPvpFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)


        downloadPvPSummary()
        download2v2Info()
        download3v3Info()
        downloadRBGInfo()
    }

    private fun downloadRBGInfo() {
        val call: Call<BracketStatistics> = GamesActivity.client!!.getPvPBrackets(character!!.toLowerCase(Locale.ROOT), realm!!.toLowerCase(Locale.ROOT),
                "rbg", MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<BracketStatistics> {
            override fun onResponse(call: Call<BracketStatistics>, response: retrofit2.Response<BracketStatistics>) {
                val pvpRBG = response.body()
                val url = pvpRBG?.tier?.key?.href?.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
                val call2: Call<Tier> = GamesActivity.client!!.getDynamicTier(url, region?.toLowerCase(Locale.ROOT), MainActivity.locale)
                call2.enqueue(object : Callback<Tier> {
                    override fun onResponse(call: Call<Tier>, response: retrofit2.Response<Tier>) {
                        if (response.isSuccessful && response.body() != null) {
                            val tier = response.body()
                            setTierImage(binding.tierimagerbg, tier!!)
                            showBracketInformationOnTouch(binding.layoutrbg, tier, pvpRBG!!)
                        } else {
                            binding.layoutrbg.alpha = 0.4f
                        }
                    }

                    override fun onFailure(call: Call<Tier>, t: Throwable) {
                        Log.e("Error", t.message, t)
                        binding.layoutrbg.alpha = 0.4f
                    }
                })
            }

            override fun onFailure(call: Call<BracketStatistics>, t: Throwable) {
                Log.e("Error", t.message, t)
                binding.layoutrbg.alpha = 0.4f
            }
        })
    }

    private fun download3v3Info() {
        val call: Call<BracketStatistics> = GamesActivity.client!!.getPvPBrackets(character!!.toLowerCase(Locale.ROOT), realm!!.toLowerCase(Locale.ROOT),
                "3v3", MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<BracketStatistics> {
            override fun onResponse(call: Call<BracketStatistics>, response: retrofit2.Response<BracketStatistics>) {
                val pvp3v3 = response.body()
                val url = pvp3v3?.tier?.key?.href?.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
                val call2: Call<Tier> = GamesActivity.client!!.getDynamicTier(url, region?.toLowerCase(Locale.ROOT), MainActivity.locale)
                call2.enqueue(object : Callback<Tier> {
                    override fun onResponse(call: Call<Tier>, response: retrofit2.Response<Tier>) {
                        if (response.isSuccessful && response.body() != null) {
                            val tier = response.body()
                            setTierImage(binding.tierimage3v3, tier!!)
                            showBracketInformationOnTouch(binding.layout3v3, tier, pvp3v3!!)
                        } else {
                            binding.layoutrbg.alpha = 0.4f
                        }
                    }

                    override fun onFailure(call: Call<Tier>, t: Throwable) {
                        Log.e("Error", t.message, t)
                        binding.layout3v3.alpha = 0.4f
                    }
                })
            }

            override fun onFailure(call: Call<BracketStatistics>, t: Throwable) {
                Log.e("Error", t.message, t)
                binding.layout3v3.alpha = 0.4f
            }
        })
    }

    private fun download2v2Info() {
        val call: Call<BracketStatistics> = GamesActivity.client!!.getPvPBrackets(character!!.toLowerCase(Locale.ROOT), realm!!.toLowerCase(Locale.ROOT),
                "2v2", MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<BracketStatistics> {
            override fun onResponse(call: Call<BracketStatistics>, response: retrofit2.Response<BracketStatistics>) {
                val pvp2v2 = response.body()
                val url = pvp2v2?.tier?.key?.href?.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
                val call2: Call<Tier> = GamesActivity.client!!.getDynamicTier(url, region?.toLowerCase(Locale.ROOT), MainActivity.locale)
                call2.enqueue(object : Callback<Tier> {
                    override fun onResponse(call: Call<Tier>, response: retrofit2.Response<Tier>) {
                        if (response.isSuccessful && response.body() != null) {
                            val tier = response.body()
                            setTierImage(binding.tierimage2v2, tier!!)
                            Log.i("2V2 there", "PVP")
                            showBracketInformationOnTouch(binding.layout2v2, tier, pvp2v2!!)
                        } else {
                            binding.layoutrbg.alpha = 0.4f
                        }
                    }

                    override fun onFailure(call: Call<Tier>, t: Throwable) {
                        Log.e("Error", t.message, t)
                        binding.layout2v2.alpha = 0.4f
                    }
                })
            }

            override fun onFailure(call: Call<BracketStatistics>, t: Throwable) {
                Log.e("Error", t.message, t)
                binding.layout2v2.alpha = 0.4f
            }
        })
    }

    private fun downloadPvPSummary() {
        val call: Call<PvPSummary> = GamesActivity.client!!.getPvPSummary(character!!.toLowerCase(Locale.ROOT),
                realm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<PvPSummary> {
            override fun onResponse(call: Call<PvPSummary>, response: retrofit2.Response<PvPSummary>) {
                val pvpSummary = response.body()
                if (pvpSummary != null) {
                    binding.kills.text = pvpSummary.honorable_kills.toString()
                    binding.level.text = "LEVEL " + pvpSummary.honor_level.toString()
                    setHonorRankIcon(pvpSummary)
                    if (pvpSummary.pvp_map_statistics != null) {
                        binding.recyclerviewbg.apply {
                            layoutManager = LinearLayoutManager(activity)
                            adapter = BattlegroundAdapter(pvpSummary.pvp_map_statistics, context)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PvPSummary>, t: Throwable) {
                Log.e("Error", t.message, t)
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showBracketInformationOnTouch(layout: RelativeLayout, tier: Tier, bracket: BracketStatistics) {
        layout.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.title.text = tier.name
                    binding.rating.text = bracket.rating.toString()
                    binding.gamesplayed.text = bracket.season_matchStatistics.played.toString() + " games played"
                    binding.gameswon.text = bracket.season_matchStatistics.won.toString() + " games won"
                    binding.winrate.text = ((bracket.season_matchStatistics.won * 100) / bracket.season_matchStatistics.played).toString() + "% win rate"
                    binding.bracketinfo.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_UP -> binding.bracketinfo.visibility = View.GONE
            }
            return@setOnTouchListener true
        }
    }

    private fun setTierImage(imageView: ImageView, tier: Tier) {
        when (tier.name) {
            "Unranked" -> imageView.setImageResource(R.drawable.unranked)
            "Combatant" -> imageView.setImageResource(R.drawable.combatant)
            "Challenger" -> imageView.setImageResource(R.drawable.challenger)
            "Rival" -> imageView.setImageResource(R.drawable.rival)
            "Duelist" -> imageView.setImageResource(R.drawable.duelist)
            "Elite" -> imageView.setImageResource(R.drawable.elite)
        }
    }

    private fun setHonorRankIcon(pvpSummary: PvPSummary) {
        when (pvpSummary.honorable_kills) {
            in 0..99 -> binding.rankicon.setImageResource(R.drawable.rank1_honor)
            in 100..499 -> binding.rankicon.setImageResource(R.drawable.rank2_honor)
            in 500..999 -> binding.rankicon.setImageResource(R.drawable.rank3_honor)
            in 1000..4999 -> binding.rankicon.setImageResource(R.drawable.rank4_honor)
            in 5000..9999 -> binding.rankicon.setImageResource(R.drawable.rank5_honor)
            in 10000..24999 -> binding.rankicon.setImageResource(R.drawable.rank6_honor)
            in 25000..49999 -> binding.rankicon.setImageResource(R.drawable.rank7_honor)
            in 50000..99999 -> binding.rankicon.setImageResource(R.drawable.rank8_honor)
            in 100000..249999 -> binding.rankicon.setImageResource(R.drawable.rank9_honor)
            in 250000..Int.MAX_VALUE -> binding.rankicon.setImageResource(R.drawable.rank10_honor)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            downloadPvPSummary()
            download2v2Info()
            download3v3Info()
            downloadRBGInfo()
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public fun factionEventReceived(factionEvent: FactionEvent) {
        if (factionEvent.data == "horde") {
            binding.factionicon.setImageResource(R.drawable.horde_pvp_logo)
        } else {
            binding.factionicon.setImageResource(R.drawable.alliance_pvp_logo)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        var bgName = ""
        when (classEvent.data) {
            6 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }
            12 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }
            11 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                binding.layoutPvp.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
        }
        Glide.with(this).load(URLConstants.getWoWAsset(bgName)).into(binding.backgroundPvp)
        EventBus.getDefault().unregister(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(character: String, realm: String, media: String, region: String) =
                WoWNavFragment().apply {
                    arguments = Bundle().apply {
                        putString(CHARACTER, character)
                        putString(REALM, realm)
                        putString(MEDIA, media)
                        putString(REGION, region)
                    }
                }
    }

}

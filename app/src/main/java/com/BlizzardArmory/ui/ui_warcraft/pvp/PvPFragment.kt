package com.BlizzardArmory.ui.ui_warcraft.pvp


import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BnConstants
import com.BlizzardArmory.connection.oauth.BnOAuth2Helper
import com.BlizzardArmory.connection.oauth.BnOAuth2Params
import com.BlizzardArmory.model.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.model.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.model.warcraft.pvp.tiers.Tier
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.IOnBackPressed
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.FactionEvent
import com.BlizzardArmory.util.events.RetryEvent
import kotlinx.android.synthetic.main.wow_pvp_fragment.*
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


class PvPFragment : Fragment(), IOnBackPressed {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null

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

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wow_pvp_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params!!)


        downloadPvPSummary()
        download2v2Info()
        download3v3Info()
        downloadRBGInfo()
    }

    private fun downloadRBGInfo() {
        val call: Call<BracketStatistics> = RetroClient.getClient.getPvPBrackets(character!!.toLowerCase(Locale.ROOT), realm!!.toLowerCase(Locale.ROOT),
                "rbg", MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<BracketStatistics> {
            override fun onResponse(call: Call<BracketStatistics>, response: retrofit2.Response<BracketStatistics>) {
                val pvpRBG = response.body()
                val url = pvpRBG?.tier?.key?.href?.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
                val call2: Call<Tier> = RetroClient.getClient.getDynamicTier(url, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
                call2.enqueue(object : Callback<Tier> {
                    override fun onResponse(call: Call<Tier>, response: retrofit2.Response<Tier>) {
                        if (response.isSuccessful && response.body() != null) {
                            val tier = response.body()
                            setTierImage(tierimagerbg, tier!!)
                            showBracketInformationOnTouch(layoutrbg, tier, pvpRBG!!)
                        } else {
                            layoutrbg.alpha = 0.4f
                        }
                    }

                    override fun onFailure(call: Call<Tier>, t: Throwable) {
                        Log.e("Error", t.localizedMessage)
                        layoutrbg.alpha = 0.4f
                    }
                })
            }

            override fun onFailure(call: Call<BracketStatistics>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                layoutrbg.alpha = 0.4f
            }
        })
    }

    private fun download3v3Info() {
        val call: Call<BracketStatistics> = RetroClient.getClient.getPvPBrackets(character!!.toLowerCase(Locale.ROOT), realm!!.toLowerCase(Locale.ROOT),
                "3v3", MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<BracketStatistics> {
            override fun onResponse(call: Call<BracketStatistics>, response: retrofit2.Response<BracketStatistics>) {
                val pvp3v3 = response.body()
                val url = pvp3v3?.tier?.key?.href?.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
                val call2: Call<Tier> = RetroClient.getClient.getDynamicTier(url, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
                call2.enqueue(object : Callback<Tier> {
                    override fun onResponse(call: Call<Tier>, response: retrofit2.Response<Tier>) {
                        if (response.isSuccessful && response.body() != null) {
                            val tier = response.body()
                            setTierImage(tierimage3v3, tier!!)
                            showBracketInformationOnTouch(layout3v3, tier, pvp3v3!!)
                        } else {
                            layoutrbg.alpha = 0.4f
                        }
                    }

                    override fun onFailure(call: Call<Tier>, t: Throwable) {
                        Log.e("Error", t.localizedMessage)
                        layout3v3.alpha = 0.4f
                    }
                })
            }

            override fun onFailure(call: Call<BracketStatistics>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                layout3v3.alpha = 0.4f
            }
        })
    }

    private fun download2v2Info() {
        val call: Call<BracketStatistics> = RetroClient.getClient.getPvPBrackets(character!!.toLowerCase(Locale.ROOT), realm!!.toLowerCase(Locale.ROOT),
                "2v2", MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<BracketStatistics> {
            override fun onResponse(call: Call<BracketStatistics>, response: retrofit2.Response<BracketStatistics>) {
                val pvp2v2 = response.body()
                val url = pvp2v2?.tier?.key?.href?.replace("https://${region?.toLowerCase(Locale.ROOT)}.api.blizzard.com/", URLConstants.HEROKU_AUTHENTICATE)
                val call2: Call<Tier> = RetroClient.getClient.getDynamicTier(url, MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT))
                call2.enqueue(object : Callback<Tier> {
                    override fun onResponse(call: Call<Tier>, response: retrofit2.Response<Tier>) {
                        if (response.isSuccessful && response.body() != null) {
                            val tier = response.body()
                            setTierImage(tierimage2v2, tier!!)
                            showBracketInformationOnTouch(layout2v2, tier, pvp2v2!!)
                        } else {
                            layoutrbg.alpha = 0.4f
                        }
                    }

                    override fun onFailure(call: Call<Tier>, t: Throwable) {
                        Log.e("Error", t.localizedMessage)
                        layout2v2.alpha = 0.4f
                    }
                })
            }

            override fun onFailure(call: Call<BracketStatistics>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                layout2v2.alpha = 0.4f
            }
        })
    }

    private fun downloadPvPSummary() {

        val call: Call<PvPSummary> = RetroClient.getClient.getPvPSummary(character!!.toLowerCase(Locale.ROOT),
                realm!!.toLowerCase(Locale.ROOT), MainActivity.locale, MainActivity.selectedRegion.toLowerCase(Locale.ROOT), bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<PvPSummary> {
            override fun onResponse(call: Call<PvPSummary>, response: retrofit2.Response<PvPSummary>) {
                val pvpSummary = response.body()
                if (pvpSummary != null) {
                    kills.text = pvpSummary.honorable_kills.toString()
                    level.text = "LEVEL " + pvpSummary.honor_level.toString()
                    setHonorRankIcon(pvpSummary)
                    if (pvpSummary.pvp_map_statistics != null) {
                        recyclerviewbg.apply {
                            layoutManager = LinearLayoutManager(activity)
                            adapter = BattlegroundAdapter(pvpSummary.pvp_map_statistics, context)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PvPSummary>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
            }
        })
    }

    private fun showBracketInformationOnTouch(layout: View, tier: Tier, bracket: BracketStatistics) {
        layout.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    title.text = tier.name
                    rating.text = bracket.rating.toString()
                    gamesplayed.text = bracket.season_matchStatistics.played.toString() + " games played"
                    gameswon.text = bracket.season_matchStatistics.won.toString() + " games won"
                    winrate.text = ((bracket.season_matchStatistics.won * 100) / bracket.season_matchStatistics.played).toString() + "% win rate"
                    bracketinfo?.visibility = View.VISIBLE
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> bracketinfo?.visibility = View.GONE
            }

            layout_pvp.setOnScrollChangeListener { _: View, scrollX: Int, _: Int, _: Int, oldScrollY: Int ->
                if (scrollX != oldScrollY) {
                    bracketinfo?.visibility = View.GONE
                }
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
            in 0..99 -> rankicon.setImageResource(R.drawable.rank1_honor)
            in 100..499 -> rankicon.setImageResource(R.drawable.rank2_honor)
            in 500..999 -> rankicon.setImageResource(R.drawable.rank3_honor)
            in 1000..4999 -> rankicon.setImageResource(R.drawable.rank4_honor)
            in 5000..9999 -> rankicon.setImageResource(R.drawable.rank5_honor)
            in 10000..24999 -> rankicon.setImageResource(R.drawable.rank6_honor)
            in 25000..49999 -> rankicon.setImageResource(R.drawable.rank7_honor)
            in 50000..99999 -> rankicon.setImageResource(R.drawable.rank8_honor)
            in 100000..249999 -> rankicon.setImageResource(R.drawable.rank9_honor)
            in 250000..Int.MAX_VALUE -> rankicon.setImageResource(R.drawable.rank10_honor)
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
            factionicon.setImageResource(R.drawable.horde_pvp_logo)
        } else {
            factionicon.setImageResource(R.drawable.alliance_pvp_logo)
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        when (classEvent.data) {
            6 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#080812"))
                background_pvp.setBackgroundResource(R.drawable.dk_bg)
            }
            12 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#000900"))
                background_pvp.setBackgroundResource(R.drawable.dh_bg)
            }
            11 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#04100a"))
                background_pvp.setBackgroundResource(R.drawable.druid_bg)
            }
            3 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#0f091b"))
                background_pvp.setBackgroundResource(R.drawable.hunter_bg)
            }
            8 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#110617"))
                background_pvp.setBackgroundResource(R.drawable.mage_bg)
            }
            10 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#040b17"))
                background_pvp.setBackgroundResource(R.drawable.monk_bg)
            }
            2 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#13040a"))
                background_pvp.setBackgroundResource(R.drawable.paladin_bg)
            }
            5 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#15060e"))
                background_pvp.setBackgroundResource(R.drawable.priest_bg)
            }
            4 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#160720"))
                background_pvp.setBackgroundResource(R.drawable.rogue_bg)
            }
            7 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#050414"))
                background_pvp.setBackgroundResource(R.drawable.shaman_bg)
            }
            9 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#080516"))
                background_pvp.setBackgroundResource(R.drawable.warlock_bg)
            }
            1 -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#1a0407"))
                background_pvp.setBackgroundResource(R.drawable.warrior_bg)
            }
        }
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

    override fun onBackPressed(): Boolean {
        return URLConstants.loading
    }
}

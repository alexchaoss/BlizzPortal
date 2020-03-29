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
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.connection.RequestQueueSingleton
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.ui_warcraft.ClassEvent
import com.BlizzardArmory.ui.ui_warcraft.FactionEvent
import com.BlizzardArmory.ui.ui_warcraft.WoWNavFragment
import com.BlizzardArmory.warcraft.pvp.bracket.BracketStatistics
import com.BlizzardArmory.warcraft.pvp.summary.PvPSummary
import com.BlizzardArmory.warcraft.pvp.tiers.Tier
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.common.base.Ascii
import com.google.gson.Gson
import kotlinx.android.synthetic.main.wow_pvp_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
        val view: View = inflater.inflate(R.layout.wow_pvp_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)

        downloadPvPSummary()
        download2v2Info()
        download3v3Info()
        downloadRBGInfo()
    }

    private fun downloadRBGInfo() {
        val urlRBG = URLConstants.getBaseURLforAPI(region) +
                URLConstants.WOW_PVP_BRACKETS.replace("zone", Ascii.toLowerCase(region)).replace("BRACKET", "rbg")
                        .replace("realm", Ascii.toLowerCase(realm!!)).replace("characterName", Ascii.toLowerCase(character!!)).replace("TOKEN", bnOAuth2Helper!!.accessToken)

        val pvpRBGRequest = JsonObjectRequest(Request.Method.GET, urlRBG, null,
                Response.Listener { response ->
                    val pvpRBG = Gson().fromJson(response.toString(), BracketStatistics::class.java)
                    val tierRequest = JsonObjectRequest(Request.Method.GET, pvpRBG.tier.key.href + URLConstants.ACCESS_TOKEN_AND_LOCALE + bnOAuth2Helper!!.accessToken, null,
                            Response.Listener { response1 ->
                                val tier = Gson().fromJson(response1.toString(), Tier::class.java)
                                setTierImage(tierimagerbg, tier)
                                showBracketInformationOnTouch(layoutrbg, tier, pvpRBG)

                            }, Response.ErrorListener {
                    })
                    activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(tierRequest)

                }, Response.ErrorListener {
            layoutrbg.alpha = 0.4f
        })
        activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(pvpRBGRequest)
    }

    private fun download3v3Info() {
        val url3v3 = URLConstants.getBaseURLforAPI(region) +
                URLConstants.WOW_PVP_BRACKETS.replace("zone", Ascii.toLowerCase(region)).replace("BRACKET", "3v3")
                        .replace("realm", Ascii.toLowerCase(realm!!)).replace("characterName", Ascii.toLowerCase(character!!)).replace("TOKEN", bnOAuth2Helper!!.accessToken)

        val pvp3v3Request = JsonObjectRequest(Request.Method.GET, url3v3, null,
                Response.Listener { response ->
                    val pvp3v3 = Gson().fromJson(response.toString(), BracketStatistics::class.java)
                    val tierRequest = JsonObjectRequest(Request.Method.GET, pvp3v3.tier.key.href + URLConstants.ACCESS_TOKEN_AND_LOCALE + bnOAuth2Helper!!.accessToken, null,
                            Response.Listener { response1 ->
                                val tier = Gson().fromJson(response1.toString(), Tier::class.java)
                                setTierImage(tierimage3v3, tier)
                                showBracketInformationOnTouch(layout3v3, tier, pvp3v3)
                            }, Response.ErrorListener {
                    })
                    activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(tierRequest)
                }, Response.ErrorListener {
            try {
                layout3v3.alpha = 0.4f
            } catch (e: Exception) {

            }
        })
        activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(pvp3v3Request)
    }

    private fun download2v2Info() {
        val url2v2 = URLConstants.getBaseURLforAPI(region) +
                URLConstants.WOW_PVP_BRACKETS.replace("zone", Ascii.toLowerCase(region)).replace("BRACKET", "2v2")
                        .replace("realm", Ascii.toLowerCase(realm!!)).replace("characterName", Ascii.toLowerCase(character!!)).replace("TOKEN", bnOAuth2Helper!!.accessToken)

        val pvp2v2Request = JsonObjectRequest(Request.Method.GET, url2v2, null,
                Response.Listener { response ->
                    val pvp2v2 = Gson().fromJson(response.toString(), BracketStatistics::class.java)
                    val tierRequest = JsonObjectRequest(Request.Method.GET, pvp2v2.tier.key.href + URLConstants.ACCESS_TOKEN_AND_LOCALE + bnOAuth2Helper!!.accessToken, null,
                            Response.Listener { response1 ->
                                Log.i("TEST", response1.toString())
                                val tier = Gson().fromJson(response1.toString(), Tier::class.java)
                                setTierImage(tierimage2v2, tier)
                                showBracketInformationOnTouch(layout2v2, tier, pvp2v2)

                            }, Response.ErrorListener {
                    })
                    activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(tierRequest)
                }, Response.ErrorListener {
            layout2v2.alpha = 0.4f
        })
        activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(pvp2v2Request)
    }

    private fun downloadPvPSummary() {
        val urlPvPSummary = URLConstants.getBaseURLforAPI(region) +
                URLConstants.WOW_PVP_SUM.replace("zone", Ascii.toLowerCase(region)).replace("realm", Ascii.toLowerCase(realm!!))
                        .replace("characterName", Ascii.toLowerCase(character!!)).replace("TOKEN", bnOAuth2Helper!!.accessToken)

        val pvpSummaryRequest = JsonObjectRequest(Request.Method.GET, urlPvPSummary, null,
                Response.Listener { response ->
                    val pvpSummary = Gson().fromJson(response.toString(), PvPSummary::class.java)
                    kills.text = pvpSummary.honorable_kills.toString()
                    level.text = "LEVEL " + pvpSummary.honor_level.toString()
                    setHonorRankIcon(pvpSummary)
                    if (pvpSummary.pvp_map_statistics != null) {
                        recyclerviewbg.apply {
                            layoutManager = LinearLayoutManager(activity)
                            adapter = BattlegroundAdapter(pvpSummary.pvp_map_statistics, context)
                        }
                    }
                }, Response.ErrorListener {
        })
        activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it) }?.addToRequestQueue(pvpSummaryRequest)
    }

    private fun showBracketInformationOnTouch(layout: View, tier: Tier, bracket: BracketStatistics) {
        layout.setOnTouchListener { v, event ->
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

            layout_pvp.setOnScrollChangeListener { view: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
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
            "Death Knight" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#080812"))
                background_pvp.setBackgroundResource(R.drawable.dk_bg)
            }
            "Demon Hunter" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#000900"))
                background_pvp.setBackgroundResource(R.drawable.dh_bg)
            }
            "Druid" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#04100a"))
                background_pvp.setBackgroundResource(R.drawable.druid_bg)
            }
            "Hunter" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#0f091b"))
                background_pvp.setBackgroundResource(R.drawable.hunter_bg)
            }
            "Mage" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#110617"))
                background_pvp.setBackgroundResource(R.drawable.mage_bg)
            }
            "Monk" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#040b17"))
                background_pvp.setBackgroundResource(R.drawable.monk_bg)
            }
            "Paladin" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#13040a"))
                background_pvp.setBackgroundResource(R.drawable.paladin_bg)
            }
            "Priest" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#15060e"))
                background_pvp.setBackgroundResource(R.drawable.priest_bg)
            }
            "Rogue" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#160720"))
                background_pvp.setBackgroundResource(R.drawable.rogue_bg)
            }
            "Shaman" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#050414"))
                background_pvp.setBackgroundResource(R.drawable.shaman_bg)
            }
            "Warlock" -> {
                layout_pvp.setBackgroundColor(Color.parseColor("#080516"))
                background_pvp.setBackgroundResource(R.drawable.warlock_bg)
            }
            "Warrior" -> {
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

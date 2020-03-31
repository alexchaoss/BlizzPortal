package com.BlizzardArmory.ui.ui_warcraft.reputations


import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.connection.RequestQueueSingleton
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.ui_warcraft.ClassEvent
import com.BlizzardArmory.ui.ui_warcraft.WoWNavFragment
import com.BlizzardArmory.warcraft.reputations.characterreputations.RepByExpansion
import com.BlizzardArmory.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.warcraft.reputations.characterreputations.Reputations
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.common.base.Ascii
import com.google.gson.Gson
import kotlinx.android.synthetic.main.wow_rep_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class ReputationsFragment : Fragment(), IOnBackPressed, SearchView.OnQueryTextListener {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private val repsByExpac = arrayListOf<ArrayList<Reputations>>()
    private val adapterList = ArrayList<ReputationsAdapter>()

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
        val view: View = inflater.inflate(R.layout.wow_rep_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)

        search_view.queryHint = "Search reputations.."
        val textView: TextView = search_view.findViewById(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))
        search_view.setOnQueryTextListener(this)

        val urlRep = URLConstants.getBaseURLforAPI(region) + URLConstants.WOW_REP.replace("zone", Ascii.toLowerCase(region)).replace("realm", Ascii.toLowerCase(realm!!))
                .replace("characterName", Ascii.toLowerCase(character!!)).replace("TOKEN", bnOAuth2Helper!!.accessToken)

        for (i in 0..8) {
            repsByExpac.add(arrayListOf())
        }

        val repRequest = JsonObjectRequest(Request.Method.GET, urlRep, null,
                Response.Listener { response ->
                    val reputation = Gson().fromJson(response.toString(), Reputation::class.java)

                    for (reps in reputation.reputations) {
                        for (enumRep in RepByExpansion.values()) {
                            if (reps.faction.name == enumRep.repName) {
                                when (enumRep.xpac) {
                                    "Classic" -> repsByExpac[8].add(reps)
                                    "Burning Crusade" -> repsByExpac[7].add(reps)
                                    "Wrath of the Lich King" -> repsByExpac[6].add(reps)
                                    "Cataclysm" -> repsByExpac[5].add(reps)
                                    "Mists of Pandaria" -> repsByExpac[4].add(reps)
                                    "Warlords of Draenor" -> repsByExpac[3].add(reps)
                                    "Legion" -> repsByExpac[2].add(reps)
                                    "Battle for Azeroth" -> repsByExpac[1].add(reps)
                                    "Shadowlands" -> repsByExpac[0].add(reps)
                                }
                            }
                        }
                    }

                    for (reputations in repsByExpac) {
                        if (reputations.size > 0) {
                            reputations.sortBy { RepByExpansion.getFaction(it.faction.name) }
                            val paramsButton: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                            val button = TextView(context)
                            button.setBackgroundResource(R.drawable.progress_collapse_header)
                            button.setTextColor(Color.WHITE)
                            button.text = "+ " + getExpansion(repsByExpac.indexOf(reputations))
                            button.textSize = 18F
                            button.layoutParams = paramsButton
                            rep_container.addView(button)

                            var expand = false
                            val paramsRecyclerView: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                            val recyclerView = context?.let { RecyclerView(it) }
                            recyclerView?.layoutParams = paramsRecyclerView
                            rep_container.addView(recyclerView)

                            recyclerView?.apply {
                                layoutManager = LinearLayoutManager(activity)
                                adapter = ReputationsAdapter(reputations, context)
                                adapter?.let { adapterList.add(it as ReputationsAdapter) }
                            }
                            recyclerView?.visibility = View.GONE

                            button.setOnClickListener {
                                if (!expand) {
                                    expand = true
                                    recyclerView?.visibility = View.VISIBLE
                                    button.text = "- " + getExpansion(repsByExpac.indexOf(reputations))
                                } else {
                                    expand = false
                                    recyclerView?.visibility = View.GONE
                                    button.text = "+ " + getExpansion(repsByExpac.indexOf(reputations))
                                }
                            }

                        }
                    }

                }, Response.ErrorListener {
            showOutdatedTextView()
        })
        activity?.applicationContext?.let { RequestQueueSingleton.getInstance(it).addToRequestQueue(repRequest) }
    }

    private fun showOutdatedTextView() {
        val outdatedInfo = TextView(context)
        outdatedInfo.text = "Outdated information\nPlease login in game to refresh data"
        outdatedInfo.setTextColor(Color.WHITE)
        outdatedInfo.gravity = Gravity.CENTER
        outdatedInfo.textSize = 20F
        outdatedInfo.setPadding(0, 50, 0, 0)
        rep_container.addView(outdatedInfo)
    }

    private fun getExpansion(index: Int): String {
        when (index) {
            8 -> return "Classic"
            7 -> return "Burning Crusade"
            6 -> return "Wrath of the Lich King"
            5 -> return "Cataclysm"
            4 -> return "Mists of Pandaria"
            3 -> return "Warlords of Draenor"
            2 -> return "Legion"
            1 -> return "Battle for Azeroth"
            0 -> return "Shadowlands"
        }
        return ""
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        when (classEvent.data) {
            "Death Knight" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#080812"))
                background_rep.setBackgroundResource(R.drawable.dk_bg)
            }
            "Demon Hunter" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#000900"))
                background_rep.setBackgroundResource(R.drawable.dh_bg)
            }
            "Druid" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#04100a"))
                background_rep.setBackgroundResource(R.drawable.druid_bg)
            }
            "Hunter" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#0f091b"))
                background_rep.setBackgroundResource(R.drawable.hunter_bg)
            }
            "Mage" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#110617"))
                background_rep.setBackgroundResource(R.drawable.mage_bg)
            }
            "Monk" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#040b17"))
                background_rep.setBackgroundResource(R.drawable.monk_bg)
            }
            "Paladin" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#13040a"))
                background_rep.setBackgroundResource(R.drawable.paladin_bg)
            }
            "Priest" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#15060e"))
                background_rep.setBackgroundResource(R.drawable.priest_bg)
            }
            "Rogue" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#160720"))
                background_rep.setBackgroundResource(R.drawable.rogue_bg)
            }
            "Shaman" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#050414"))
                background_rep.setBackgroundResource(R.drawable.shaman_bg)
            }
            "Warlock" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#080516"))
                background_rep.setBackgroundResource(R.drawable.warlock_bg)
            }
            "Warrior" -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#1a0407"))
                background_rep.setBackgroundResource(R.drawable.warrior_bg)
            }
        }
        EventBus.getDefault().unregister(this)
    }

    override fun onBackPressed(): Boolean {
        return URLConstants.loading
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        for (adapter in adapterList) {
            adapter.filter(newText!!)
        }
        return false
    }
}

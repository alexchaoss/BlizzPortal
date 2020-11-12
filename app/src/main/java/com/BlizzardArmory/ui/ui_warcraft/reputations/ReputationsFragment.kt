package com.BlizzardArmory.ui.ui_warcraft.reputations


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.URLConstants
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.RepByExpansion
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputation
import com.BlizzardArmory.model.warcraft.reputations.characterreputations.Reputations
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.IOnBackPressed
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.wow_rep_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class ReputationsFragment : Fragment(), IOnBackPressed, SearchView.OnQueryTextListener {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
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
        return inflater.inflate(R.layout.wow_rep_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        search_view.queryHint = "Search reputations.."
        val textView: TextView = search_view.findViewById(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))
        search_view.setOnQueryTextListener(this)

        for (i in 0..8) {
            repsByExpac.add(arrayListOf())
        }

        downloadReputations()
    }

    private fun downloadReputations() {
        val call: Call<Reputation> = RetroClient.getClient.getReputations(character!!.toLowerCase(Locale.ROOT),
                realm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<Reputation> {
            override fun onResponse(call: Call<Reputation>, response: Response<Reputation>) {
                val reputation = response.body()
                if (reputation != null) {
                    sortRepsByExpansions(reputation)
                    createRecyclerViews()
                } else {
                    showOutdatedTextView()
                }
            }

            override fun onFailure(call: Call<Reputation>, t: Throwable) {
                Log.e("Error", "trace: ", t)

            }
        })
    }

    private fun sortRepsByExpansions(reputation: Reputation) {
        for (reps in reputation.reputations) {
            for (enumRep in RepByExpansion.values()) {
                if (reps.faction.id == enumRep.id) {
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
    }

    private fun createRecyclerViews() {
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
    }

    private fun showOutdatedTextView() {
        val outdatedInfo = TextView(activity)
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
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            downloadReputations()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        var bgName = ""
        when (classEvent.data) {
            6 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }
            12 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }
            11 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                reputation_layout.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
        }
        Glide.with(this).load(URLConstants.getWoWAsset(bgName)).into(background_rep)
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

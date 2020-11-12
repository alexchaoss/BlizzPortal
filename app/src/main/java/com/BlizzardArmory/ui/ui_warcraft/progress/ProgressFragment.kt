package com.BlizzardArmory.ui.ui_warcraft.progress


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
import com.BlizzardArmory.model.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.model.warcraft.encounters.Expansions
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment
import com.BlizzardArmory.util.IOnBackPressed
import com.BlizzardArmory.util.events.ClassEvent
import com.BlizzardArmory.util.events.RetryEvent
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.wow_progress_fragment.*
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


class ProgressFragment : Fragment(), IOnBackPressed, SearchView.OnQueryTextListener {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var battlenetOAuth2Helper: BattlenetOAuth2Helper? = null
    private var battlenetOAuth2Params: BattlenetOAuth2Params? = null
    private val adapterList = ArrayList<EncounterAdapter>()


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
        return inflater.inflate(R.layout.wow_progress_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        battlenetOAuth2Params = activity?.intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)
        battlenetOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params!!)

        search_view.queryHint = "Search raid.."
        val textView: TextView = search_view.findViewById(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.parseColor("#ffffff"))
        textView.setHintTextColor(Color.parseColor("#ffffff"))
        search_view.setOnQueryTextListener(this)

        downloadEncounterInformation()
    }

    private fun downloadEncounterInformation() {
        val call: Call<EncountersInformation> = RetroClient.getClient.getEncounters(character!!.toLowerCase(Locale.ROOT),
                realm!!.toLowerCase(Locale.ROOT), MainActivity.locale, region?.toLowerCase(Locale.ROOT), battlenetOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<EncountersInformation> {
            override fun onResponse(call: Call<EncountersInformation>, response: retrofit2.Response<EncountersInformation>) {
                val encounters = response.body()
                if (encounters != null) {
                    if (view?.findViewWithTag<TextView>("OUTDATED") == null) {
                        setRecyclerViewForEachExpansion(encounters)
                    } else {
                        progress_container.removeAllViews()
                        setRecyclerViewForEachExpansion(encounters)
                    }
                } else {
                    showOutdatedTextView()
                }
            }

            override fun onFailure(call: Call<EncountersInformation>, t: Throwable) {
                Log.e("Error", "trace", t)
                showOutdatedTextView()
            }
        })
    }

    private fun setRecyclerViewForEachExpansion(encounters: EncountersInformation) {
        var expansions: List<Expansions>? = null
        if (!encounters.expansions.isNullOrEmpty()) {
            expansions = encounters.expansions.reversed()
        }
        if (expansions != null) {
            for (expansion in expansions) run {
                val paramsButton: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                val button = TextView(context)
                button.setBackgroundResource(R.drawable.progress_collapse_header)
                button.setTextColor(Color.WHITE)
                button.text = "+ " + expansion.expansion.name
                button.textSize = 18F
                button.layoutParams = paramsButton
                progress_container.addView(button)

                var expand = false
                val paramsRecyclerView: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                val recyclerView = context?.let { RecyclerView(it) }
                recyclerView?.layoutParams = paramsRecyclerView
                progress_container.addView(recyclerView)

                recyclerView?.apply {
                    val raidLevel = getRaidLevel(expansion)
                    layoutManager = LinearLayoutManager(activity)
                    adapter = EncounterAdapter(expansion.instances, raidLevel, context, expansion.expansion)
                    adapter?.let { adapterList.add(it as EncounterAdapter) }
                }
                recyclerView?.visibility = View.GONE

                button.setOnClickListener {
                    if (!expand) {
                        expand = true
                        recyclerView?.visibility = View.VISIBLE
                        button.text = "- " + expansion.expansion.name
                    } else {
                        expand = false
                        recyclerView?.visibility = View.GONE
                        button.text = "+ " + expansion.expansion.name
                    }
                }
            }
        } else {
            showOutdatedTextView()
        }
    }

    private fun showOutdatedTextView() {
        if (progress_container.childCount < 1) {
            val outdatedInfo = TextView(activity)
            val outdated = "Outdated information\nPlease login in game to refresh data"
            outdatedInfo.text = outdated
            outdatedInfo.setTextColor(Color.WHITE)
            outdatedInfo.gravity = Gravity.CENTER
            outdatedInfo.textSize = 20F
            outdatedInfo.setPadding(0, 50, 0, 0)
            outdatedInfo.tag = "OUTDATED"
            progress_container.addView(outdatedInfo)
        }
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

    private fun getRaidLevel(expansion: Expansions): String {
        return when (expansion.expansion.id) {
            68L -> "Level 25"
            70L -> "Level 27"
            72L -> "Level 30"
            73L -> "Level 32"
            74L -> "Level 35"
            124L -> "Level 40"
            395L -> "Level 45"
            396L -> "Level 50"
            397L -> "Level 60"
            else -> ""
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun retryEventReceived(retryEvent: RetryEvent) {
        if (retryEvent.data) {
            downloadEncounterInformation()
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        var bgName = ""
        when (classEvent.data) {
            6 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#080812"))
                bgName = "dk_bg"
            }
            12 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#000900"))
                bgName = "dh_bg"
            }
            11 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#04100a"))
                bgName = "druid_bg"
            }
            3 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#0f091b"))
                bgName = "hunter_bg"
            }
            8 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#110617"))
                bgName = "mage_bg"
            }
            10 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#040b17"))
                bgName = "monk_bg"
            }
            2 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#13040a"))
                bgName = "paladin_bg"
            }
            5 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#15060e"))
                bgName = "priest_bg"
            }
            4 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#160720"))
                bgName = "rogue_bg"
            }
            7 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#050414"))
                bgName = "shaman_bg"
            }
            9 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#080516"))
                bgName = "warlock_bg"
            }
            1 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#1a0407"))
                bgName = "warrior_bg"
            }
        }
        Glide.with(this).load(URLConstants.getWoWAsset(bgName)).into(background_progress)
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

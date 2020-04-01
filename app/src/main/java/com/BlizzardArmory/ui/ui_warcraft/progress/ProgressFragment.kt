package com.BlizzardArmory.ui.ui_warcraft.progress


import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.connection.NetworkServices
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.MainActivity.selectedRegion
import com.BlizzardArmory.ui.ui_warcraft.ClassEvent
import com.BlizzardArmory.ui.ui_warcraft.WoWNavFragment
import com.BlizzardArmory.warcraft.encounters.EncountersInformation
import com.BlizzardArmory.warcraft.encounters.Expansions
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.wow_progress_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


private const val CHARACTER = "character"
private const val REALM = "realm"
private const val MEDIA = "media"
private const val REGION = "region"


class ProgressFragment : Fragment(), IOnBackPressed {

    private var character: String? = null
    private var realm: String? = null
    private var media: String? = null
    private var region: String? = null
    private var bnOAuth2Helper: BnOAuth2Helper? = null
    private var bnOAuth2Params: BnOAuth2Params? = null
    private var retrofit: Retrofit? = null
    private var gson: Gson? = null
    private lateinit var networkServices: NetworkServices


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
        val view: View = inflater.inflate(R.layout.wow_progress_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        bnOAuth2Params = activity?.intent?.extras?.getParcelable(BnConstants.BUNDLE_BNPARAMS)
        bnOAuth2Helper = BnOAuth2Helper(prefs, bnOAuth2Params)
        gson = GsonBuilder().create()
        retrofit = Retrofit.Builder().baseUrl(URLConstants.getBaseURLforAPI(MainActivity.selectedRegion)).addConverterFactory(GsonConverterFactory.create(gson!!)).build()
        networkServices = retrofit?.create(NetworkServices::class.java)!!
        downloadEncounterInformation()
    }

    private fun downloadEncounterInformation() {
        val call: Call<EncountersInformation> = networkServices.getEncounters(character!!.toLowerCase(Locale.ROOT),
                realm!!.toLowerCase(Locale.ROOT), "profile-" + selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper!!.accessToken)
        call.enqueue(object : Callback<EncountersInformation> {
            override fun onResponse(call: Call<EncountersInformation>, response: retrofit2.Response<EncountersInformation>) {
                val encounters = response.body()
                if (encounters != null) {
                    setRecyclerViewForEachExpansion(encounters)
                } else {
                    showOutdatedTextView()
                }
            }

            override fun onFailure(call: Call<EncountersInformation>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
                showOutdatedTextView()
            }
        })
    }

    private fun setRecyclerViewForEachExpansion(encounters: EncountersInformation) {
        var expansions: List<Expansions>? = null
        if (!encounters.expansions.isNullOrEmpty()) {
            expansions = encounters.expansions.reversed();
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
        val outdatedInfo = TextView(context)
        outdatedInfo.text = "Outdated information\nPlease login in game to refresh data"
        outdatedInfo.setTextColor(Color.WHITE)
        outdatedInfo.gravity = Gravity.CENTER
        outdatedInfo.textSize = 20F
        outdatedInfo.setPadding(0, 50, 0, 0)
        progress_container.addView(outdatedInfo)
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
            68L -> "Level 60"
            70L -> "Level 70"
            72L -> "Level 80"
            73L -> "Level 85"
            74L -> "Level 90"
            124L -> "Level 100"
            395L -> "Level 110"
            396L -> "Level 120"
            397L -> "Level 60"
            else -> ""
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public fun classEventReceived(classEvent: ClassEvent) {
        when (classEvent.data) {
            6 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#080812"))
                background_progress.setBackgroundResource(R.drawable.dk_bg)
            }
            12 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#000900"))
                background_progress.setBackgroundResource(R.drawable.dh_bg)
            }
            11 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#04100a"))
                background_progress.setBackgroundResource(R.drawable.druid_bg)
            }
            3 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#0f091b"))
                background_progress.setBackgroundResource(R.drawable.hunter_bg)
            }
            8 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#110617"))
                background_progress.setBackgroundResource(R.drawable.mage_bg)
            }
            10 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#040b17"))
                background_progress.setBackgroundResource(R.drawable.monk_bg)
            }
            2 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#13040a"))
                background_progress.setBackgroundResource(R.drawable.paladin_bg)
            }
            5 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#15060e"))
                background_progress.setBackgroundResource(R.drawable.priest_bg)
            }
            4 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#160720"))
                background_progress.setBackgroundResource(R.drawable.rogue_bg)
            }
            7 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#050414"))
                background_progress.setBackgroundResource(R.drawable.shaman_bg)
            }
            9 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#080516"))
                background_progress.setBackgroundResource(R.drawable.warlock_bg)
            }
            1 -> {
                progress_layout.setBackgroundColor(Color.parseColor("#1a0407"))
                background_progress.setBackgroundResource(R.drawable.warrior_bg)
            }
        }
        EventBus.getDefault().unregister(this)
    }

    override fun onBackPressed(): Boolean {
        return URLConstants.loading
    }
}

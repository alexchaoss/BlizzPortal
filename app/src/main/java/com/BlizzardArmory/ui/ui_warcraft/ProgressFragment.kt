package com.BlizzardArmory.ui.ui_warcraft


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.BlizzardArmory.R
import com.BlizzardArmory.URLConstants
import com.BlizzardArmory.ui.IOnBackPressed
import com.BlizzardArmory.warcraft.encounters.EncountersInformation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.common.base.Ascii.toLowerCase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.wow_progress_fragment.*


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getString(CHARACTER)
            realm = it.getString(REALM)
            media = it.getString(MEDIA)
            region = it.getString(REGION)
        }
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

        val requestQueue = Volley.newRequestQueue(view.context)

        val url = URLConstants.getBaseURLforAPI(region) +
                URLConstants.WOW_ENCOUNTERS.replace("zone", toLowerCase(region))
                        .replace("realm", toLowerCase(realm!!)).replace("characterName", toLowerCase(character!!)) + bnOAuth2Helper!!.accessToken


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    Log.i("TEST", response.toString())
                    val encounters = Gson().fromJson(response.toString(), EncountersInformation::class.java)
                    progress_recycler_view.apply {
                        layoutManager = LinearLayoutManager(activity)
                        // adapter = ListAdapter(encounters.expansions)
                    }
                }, Response.ErrorListener {
            Log.e("ERROR", it.stackTrace.toString())
        })
        requestQueue.add(jsonObjectRequest)
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
        return false
    }
}

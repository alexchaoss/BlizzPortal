package com.BlizzardArmory.ui.ui_warcraft

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.RetroClient
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Helper
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.ui.MainActivity
import com.BlizzardArmory.ui.ui_warcraft.navigation.WoWNavFragment.Companion.newInstance
import com.BlizzardArmory.util.MetricConversion
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * The type Wo w character search dialog.
 */
object WoWCharacterSearchDialog {
    private var characterClicked = ""
    private var characterRealm = ""
    private var media: Media? = null
    private var selectedRegion = ""

    private fun callCharacterFragment(activity: Activity, fragment: Fragment?) {
        if (fragment != null && fragment.isVisible) {
            fragment.parentFragmentManager.beginTransaction().remove(fragment).commit()
        }
        val mediaString = Gson().toJson(media)
        val woWNavFragment = newInstance(characterClicked, characterRealm, mediaString, selectedRegion)
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.replace(R.id.nav_fragment, woWNavFragment)
        fragmentTransaction.addToBackStack(null).commit()
        activity.supportFragmentManager.executePendingTransactions()
    }

    /**
     * Character search prompt.
     *
     * @param activity the activity
     * @param fragment the fragment
     */
    fun characterSearchPrompt(activity: Activity, fragment: Fragment?) {

        val builderOW = AlertDialog.Builder(activity, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        val layoutParamsSpinner = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, MetricConversion.getDPMetric(40, activity))
        val regionList = arrayOf("Select Region", "CN", "US", "EU", "KR", "TW")
        val regions = Spinner(activity)
        regions.setBackgroundResource(R.drawable.wow_spinner)
        regions.gravity = Gravity.CENTER
        regions.layoutParams = layoutParamsSpinner
        setRegionAdapter(activity, regionList, regions)
        val titleText = TextView(activity)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        buttonParams.weight = 1f
        titleText.textSize = 18f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        titleText.text = "Character Name"
        val messageText = TextView(activity)
        messageText.textSize = 18f
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        messageText.text = "Realm"
        val searchForCharacterButton = Button(activity)
        searchForCharacterButton.text = "Go"
        searchForCharacterButton.textSize = 16f
        searchForCharacterButton.setTextColor(Color.WHITE)
        searchForCharacterButton.gravity = Gravity.CENTER
        searchForCharacterButton.layoutParams = buttonParams
        searchForCharacterButton.setPadding(10, 0, 10, 0)
        searchForCharacterButton.background = activity.getDrawable(R.drawable.buttonstyle)
        val characterField = EditText(activity)
        characterField.setTextColor(Color.WHITE)
        characterField.textSize = 16f
        val colorStateList = ColorStateList.valueOf(Color.WHITE)
        characterField.backgroundTintList = colorStateList
        val realmField = EditText(activity)
        realmField.setTextColor(Color.WHITE)
        realmField.textSize = 16f
        realmField.backgroundTintList = colorStateList
        val dialogWoW = builderOW.create()
        dialogWoW.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialogWoW.show()
        dialogWoW.window?.setGravity(Gravity.CENTER)
        dialogWoW.window?.setLayout(MetricConversion.getDPMetric(320, activity), WindowManager.LayoutParams.WRAP_CONTENT)
        dialogWoW.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        dialogWoW.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        val linearLayout = LinearLayout(activity)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(30, 30, 30, 30)
        linearLayout.addView(titleText)
        linearLayout.addView(characterField)
        linearLayout.addView(messageText)
        linearLayout.addView(realmField)
        linearLayout.addView(regions)
        linearLayout.addView(searchForCharacterButton)
        dialogWoW.addContentView(linearLayout, layoutParams)
        searchForCharacterButton.setOnClickListener {
            when {
                characterField.text.toString() == "" -> {
                    Toast.makeText(activity.applicationContext, "Please enter the character name", Toast.LENGTH_SHORT).show()
                }
                realmField.text.toString() == "" -> {
                    Toast.makeText(activity.applicationContext, "Please enter the realm", Toast.LENGTH_SHORT).show()
                }
                regions.selectedItem.toString().equals("Select Region", ignoreCase = true) -> {
                    Toast.makeText(activity.applicationContext, "Please enter the region", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    characterClicked = characterField.text.toString().toLowerCase(Locale.ROOT)
                    characterRealm = realmField.text.toString().toLowerCase(Locale.ROOT).replace(" ", "-")
                    downloadMedia(activity, dialogWoW, fragment)
                }
            }
        }
    }

    private fun setRegionAdapter(activity: Activity, regionList: Array<String>, regions: Spinner) {
        val arrayAdapter: ArrayAdapter<*> = object : ArrayAdapter<String?>(activity, android.R.layout.simple_dropdown_item_1line, regionList) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.setBackgroundColor(Color.BLACK)
                tv.textSize = 18f
                tv.gravity = Gravity.CENTER
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.WHITE)
                }
                return view
            }
        }
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        regions.adapter = arrayAdapter
        regions.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedRegion = parent.getItemAtPosition(position) as String
                try {
                    (view as TextView).setTextColor(Color.WHITE)
                    view.textSize = 18f
                    view.gravity = Gravity.CENTER
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                (parent.getChildAt(0) as TextView).gravity = Gravity.CENTER
                (parent.getChildAt(0) as TextView).setTextColor(0)
            }
        }
    }

    private fun downloadMedia(activity: Activity, dialogWoW: AlertDialog, fragment: Fragment?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        val battlenetOAuth2Params: BattlenetOAuth2Params = Objects.requireNonNull(activity).intent?.extras?.getParcelable(BattlenetConstants.BUNDLE_BNPARAMS)!!
        val bnOAuth2Helper = BattlenetOAuth2Helper(prefs, battlenetOAuth2Params)

        val call: Call<Media> = RetroClient.getClient.getMedia(characterClicked.toLowerCase(Locale.ROOT), characterRealm.toLowerCase(Locale.ROOT), MainActivity.selectedRegion.toLowerCase(Locale.ROOT), MainActivity.locale, bnOAuth2Helper.accessToken)
        call.enqueue(object : Callback<Media> {
            override fun onResponse(call: Call<Media>, response: Response<Media>) {
                media = response.body()
                dialogWoW.cancel()
                callCharacterFragment(activity, fragment)
            }

            override fun onFailure(call: Call<Media>, t: Throwable) {
                Log.e("Error", "trace", t)
                callCharacterFragment(activity, fragment)
            }
        })
    }
}
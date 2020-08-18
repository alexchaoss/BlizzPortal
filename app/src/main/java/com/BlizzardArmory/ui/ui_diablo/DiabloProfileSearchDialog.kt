package com.BlizzardArmory.ui.ui_diablo

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.BlizzardArmory.R
import com.BlizzardArmory.connection.oauth.BattlenetConstants
import com.BlizzardArmory.connection.oauth.BattlenetOAuth2Params
import com.BlizzardArmory.ui.GamesActivity
import com.BlizzardArmory.util.MetricConversion

/**
 * The type Diablo profile search dialog.
 */
object DiabloProfileSearchDialog {
    private var battleTag = ""
    private var selectedRegion = ""
    private fun callD3Activity(battlenetOAuth2Params: BattlenetOAuth2Params, fragmentManager: FragmentManager) {
        val fragment: Fragment = D3Fragment()
        val bundle = Bundle()
        bundle.putString("battletag", battleTag)
        bundle.putString("region", selectedRegion)
        bundle.putParcelable(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
        fragment.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.fragment, fragment, "d3fragment").commit()
        fragmentManager.executePendingTransactions()
    }

    /**
     * Diablo prompt.
     *
     * @param activity       the activity
     * @param battlenetOAuth2Params the bn o auth 2 params
     */
    @JvmStatic
    fun diabloPrompt(activity: Activity, battlenetOAuth2Params: BattlenetOAuth2Params, fragmentManager: FragmentManager) {
        val builderOW = AlertDialog.Builder(activity, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        val layoutParamsSpinner = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, MetricConversion.getDPMetric(40, activity))
        val REGION_LIST = arrayOf("Select Region", "CN", "US", "EU", "KR", "TW")
        val regions = Spinner(activity)
        regions.setBackgroundResource(R.drawable.wow_spinner)
        regions.gravity = Gravity.CENTER
        regions.layoutParams = layoutParamsSpinner
        val arrayAdapter: ArrayAdapter<*> = object : ArrayAdapter<String?>(activity, android.R.layout.simple_dropdown_item_1line, REGION_LIST) {
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

        val titleText = TextView(activity)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        buttonParams.weight = 1f
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        titleText.text = "Enter a BattleTag"
        val myProfile = Button(activity)
        myProfile.text = "My Profile"
        myProfile.textSize = 16f
        myProfile.setTextColor(Color.WHITE)
        myProfile.gravity = Gravity.CENTER
        myProfile.layoutParams = buttonParams
        myProfile.setPadding(10, 0, 10, 0)
        myProfile.background = activity.getDrawable(R.drawable.buttonstyle)
        val searchButton = Button(activity)
        searchButton.text = "Search"
        searchButton.textSize = 16f
        searchButton.setTextColor(Color.WHITE)
        searchButton.gravity = Gravity.CENTER
        searchButton.layoutParams = buttonParams
        searchButton.background = activity.getDrawable(R.drawable.buttonstyle)
        val battleTagField = EditText(activity)
        battleTagField.setTextColor(Color.WHITE)
        battleTagField.textSize = 16f
        val colorStateList = ColorStateList.valueOf(Color.WHITE)
        battleTagField.backgroundTintList = colorStateList
        val buttonLayout = LinearLayout(activity)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        buttonLayout.addView(searchButton)
        buttonLayout.addView(myProfile)
        val dialogD3 = builderOW.create()
        dialogD3.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialogD3.show()
        dialogD3.window?.setGravity(Gravity.CENTER)
        dialogD3.window?.setLayout(MetricConversion.getDPMetric(320, activity), WindowManager.LayoutParams.WRAP_CONTENT)
        dialogD3.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        dialogD3.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        val linearLayout = LinearLayout(activity)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(30, 30, 30, 30)
        linearLayout.addView(titleText)
        linearLayout.addView(battleTagField)
        linearLayout.addView(regions)
        linearLayout.addView(buttonLayout)
        dialogD3.addContentView(linearLayout, layoutParams)
        searchButton.setOnClickListener {
            val btagRegex = Regex(".*#[0-9]*")
            if (!battleTagField.text.toString().matches(btagRegex)) {
                Toast.makeText(activity.applicationContext, "Please enter a BattleTag", Toast.LENGTH_SHORT).show()
            } else if (regions.selectedItem.toString().equals("Select Region", ignoreCase = true)) {
                Toast.makeText(activity.applicationContext, "Please enter the region", Toast.LENGTH_SHORT).show()
            } else {
                battleTag = battleTagField.text.toString()
                dialogD3.cancel()
                callD3Activity(battlenetOAuth2Params, fragmentManager)
            }
        }
        myProfile.setOnClickListener {
            battleTag = GamesActivity.userInformation!!.battleTag
            selectedRegion = ""
            dialogD3.cancel()
            callD3Activity(battlenetOAuth2Params, fragmentManager)
        }
    }
}
package com.BlizzardArmory.ui.ui_overwatch

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.BlizzardArmory.R
import com.BlizzardArmory.ui.navigation.GamesActivity.Companion.userInformation
import com.BlizzardArmory.util.MetricConversion.getDPMetric

/**
 * The type Ow platform choice dialog.
 */
object OWPlatformChoiceDialog {
    private var username = ""
    private var platform = ""

    /**
     * The My profile chosen.
     */
    private var myProfileChosen = false
    private const val OK = "OK"
    private const val PLATFORM_CHOICE = "Choose your platform"
    private const val BATTLE_TAG = "Enter your Battle Tag"
    private const val MY_PROFILE = "My PC Profile"
    private const val PC = "PC"
    private const val XBL = "XBL"
    private const val PSN = "PSN"
    private const val ENTER_BTAG = "Enter your Battle Tag"
    private const val ENTER_XBL = "Enter your Xbox live username"
    private const val ENTER_PSN = "Enter your Playstation username"
    private fun callOverWatchActivity(fragmentManager: FragmentManager) {
        val fragment: Fragment = OWFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("platform", platform)
        fragment.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.fragment, fragment, "overwatchfragment").addToBackStack("ow_account").commit()
        fragmentManager.executePendingTransactions()
    }

    /**
     * Overwatch prompt.
     *
     * @param activity       the activity
     * @param fragmentManager the fragment manager
     */
    fun overwatchPrompt(activity: Activity, fragmentManager: FragmentManager) {
        myProfileChosen = false
        val builderOW = AlertDialog.Builder(activity, R.style.DialogTransparent)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        val titleText = TextView(activity)
        val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        buttonParams.setMargins(10, 20, 10, 20)
        buttonParams.weight = 1f
        titleText.textSize = 20f
        titleText.gravity = Gravity.CENTER_HORIZONTAL
        titleText.setPadding(0, 20, 0, 20)
        titleText.layoutParams = layoutParams
        titleText.setTextColor(Color.WHITE)
        titleText.text = PLATFORM_CHOICE
        val messageText = TextView(activity)
        messageText.textSize = 18f
        messageText.gravity = Gravity.CENTER_HORIZONTAL
        messageText.layoutParams = layoutParams
        messageText.setTextColor(Color.WHITE)
        messageText.text = BATTLE_TAG
        val myProfile = Button(activity)
        myProfile.text = MY_PROFILE
        myProfile.textSize = 16f
        myProfile.setTextColor(Color.BLACK)
        myProfile.gravity = Gravity.CENTER
        myProfile.layoutParams = buttonParams
        myProfile.setPadding(10, 0, 10, 0)
        myProfile.background = activity.getDrawable(R.drawable.buttonstyle)
        val pcButton = Button(activity)
        pcButton.text = PC
        pcButton.textSize = 16f
        pcButton.setTextColor(Color.WHITE)
        pcButton.gravity = Gravity.CENTER
        pcButton.layoutParams = buttonParams
        pcButton.background = activity.getDrawable(R.drawable.buttonstyle)
        val xboxButton = Button(activity)
        xboxButton.text = XBL
        xboxButton.textSize = 16f
        xboxButton.setTextColor(Color.BLACK)
        xboxButton.gravity = Gravity.CENTER
        xboxButton.layoutParams = buttonParams
        xboxButton.background = activity.getDrawable(R.drawable.buttonstyle)
        val psButton = Button(activity)
        psButton.text = PSN
        psButton.textSize = 16f
        psButton.setTextColor(Color.BLACK)
        psButton.gravity = Gravity.CENTER
        psButton.layoutParams = buttonParams
        psButton.background = activity.getDrawable(R.drawable.buttonstyle)
        val okButton = Button(activity)
        okButton.text = OK
        okButton.textSize = 16f
        okButton.setTextColor(Color.WHITE)
        okButton.gravity = Gravity.CENTER
        okButton.layoutParams = buttonParams
        okButton.background = activity.getDrawable(R.drawable.buttonstyle)
        val usernameField = EditText(activity)
        usernameField.setTextColor(Color.WHITE)
        usernameField.textSize = 16f
        val colorStateList = ColorStateList.valueOf(Color.WHITE)
        usernameField.backgroundTintList = colorStateList
        val buttonLayout = LinearLayout(activity)
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.gravity = Gravity.CENTER
        buttonLayout.addView(pcButton)
        buttonLayout.addView(xboxButton)
        buttonLayout.addView(psButton)
        val dialogOW = builderOW.create()
        dialogOW.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialogOW.show()
        dialogOW.window!!.setGravity(Gravity.CENTER)
        dialogOW.window!!.setLayout(getDPMetric(320, activity), WindowManager.LayoutParams.WRAP_CONTENT)
        dialogOW.window!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        dialogOW.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        val linearLayout = LinearLayout(activity)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        linearLayout.setPadding(30, 30, 30, 30)
        linearLayout.addView(titleText)
        linearLayout.addView(buttonLayout)
        linearLayout.addView(myProfile)
        dialogOW.addContentView(linearLayout, layoutParams)
        linearLayout.addView(messageText)
        linearLayout.addView(usernameField)
        linearLayout.addView(okButton)
        pcButton.setOnClickListener {
            myProfileChosen = false
            xboxButton.setTextColor(Color.BLACK)
            psButton.setTextColor(Color.BLACK)
            pcButton.setTextColor(Color.WHITE)
            myProfile.setTextColor(Color.BLACK)
            messageText.text = ENTER_BTAG
            messageText.visibility = View.VISIBLE
            usernameField.visibility = View.VISIBLE
            platform = "pc"
        }
        xboxButton.setOnClickListener {
            myProfileChosen = false
            xboxButton.setTextColor(Color.WHITE)
            psButton.setTextColor(Color.BLACK)
            pcButton.setTextColor(Color.BLACK)
            myProfile.setTextColor(Color.BLACK)
            messageText.text = ENTER_XBL
            messageText.visibility = View.VISIBLE
            usernameField.visibility = View.VISIBLE
            platform = "xbl"
        }
        psButton.setOnClickListener {
            myProfileChosen = false
            xboxButton.setTextColor(Color.BLACK)
            psButton.setTextColor(Color.WHITE)
            pcButton.setTextColor(Color.BLACK)
            myProfile.setTextColor(Color.BLACK)
            messageText.text = ENTER_PSN
            messageText.visibility = View.VISIBLE
            usernameField.visibility = View.VISIBLE
            platform = "psn"
        }
        okButton.setOnClickListener {
            if (usernameField.text.isEmpty() && !myProfileChosen) {
                Toast.makeText(activity.applicationContext, "Please enter a username", Toast.LENGTH_SHORT).show()
            } else {
                if (!myProfileChosen) {
                    username = usernameField.text.toString()
                }
                dialogOW.cancel()
                callOverWatchActivity(fragmentManager)
            }
        }
        myProfile.setOnClickListener {
            myProfileChosen = true
            xboxButton.setTextColor(Color.BLACK)
            psButton.setTextColor(Color.BLACK)
            pcButton.setTextColor(Color.BLACK)
            myProfile.setTextColor(Color.WHITE)
            messageText.visibility = View.GONE
            usernameField.visibility = View.GONE
            username = userInformation!!.battleTag
            platform = "pc"
        }
    }
}
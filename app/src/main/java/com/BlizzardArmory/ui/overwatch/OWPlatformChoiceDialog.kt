package com.BlizzardArmory.ui.overwatch

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.BlizzardArmory.R
import com.BlizzardArmory.ui.news.NewsListFragment
import com.BlizzardArmory.util.DialogPrompt
import com.BlizzardArmory.util.FragmentTag
import com.google.android.material.snackbar.Snackbar

/**
 * The type Ow platform choice dialog.
 */
object OWPlatformChoiceDialog {
    private var username = ""
    private var platform = ""

    /**
     * The My profile chosen.
     */
    private const val OK = "OK"
    private const val PLATFORM_CHOICE = "Choose your platform"
    private const val BATTLE_TAG = "Enter your Battle Tag"
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
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if (fragmentManager.findFragmentById(R.id.fragment) != null) {
            fragmentManager.beginTransaction()
                .remove(fragmentManager.findFragmentById(R.id.fragment)!!).commit()
        }
        val newsListFragment = NewsListFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment, newsListFragment, FragmentTag.NEWSPAGEFRAGMENT.name)
            .commit()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment, FragmentTag.OVERWATCHFRAGMENT.name)
            .addToBackStack("ow_account").commit()
        fragmentManager.executePendingTransactions()
    }

    /**
     * Overwatch prompt.
     *
     * @param activity       the activity
     * @param fragmentManager the fragment manager
     */
    fun overwatchPrompt(activity: Activity, fragmentManager: FragmentManager) {
        val dialog = DialogPrompt(activity)
        dialog.addTitle(PLATFORM_CHOICE, 20f, "title")
            .addButtons(
                dialog.Button(PC, 16f, { pcButtonClicked(dialog) }, PC),
                dialog.Button(XBL, 16f, { xboxButtonClicked(dialog) }, XBL),
                dialog.Button(PSN, 16f, { psButtonClicked(dialog) }, PSN)
            )
            .addMessage(ENTER_BTAG, 18f, "message")
            .addEditText("username")
            .addButtons(
                dialog.Button(OK, 16f,
                    {
                        if ((dialog.tagMap["username"] as EditText).text.isEmpty()) {
                            Snackbar.make(dialog.tagMap["main_container"]!!, "Please enter a username", Snackbar.LENGTH_SHORT)
                                .show()
                        } else {
                            username = (dialog.tagMap["username"] as EditText).text.toString()
                            dialog.dismiss()
                            callOverWatchActivity(fragmentManager)
                        }
                    })
            ).show()
        (dialog.tagMap[XBL] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap[PSN] as Button).setTextColor(Color.BLACK)
    }

    private fun pcButtonClicked(dialog: DialogPrompt) {
        (dialog.tagMap[XBL] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap[PC] as Button).setTextColor(Color.WHITE)
        (dialog.tagMap[PSN] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap["message"] as TextView).text = ENTER_BTAG
        platform = "pc"
    }

    private fun xboxButtonClicked(dialog: DialogPrompt) {
        (dialog.tagMap[XBL] as Button).setTextColor(Color.WHITE)
        (dialog.tagMap[PC] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap[PSN] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap["message"] as TextView).text = ENTER_XBL
        platform = "xbl"
    }

    private fun psButtonClicked(dialog: DialogPrompt) {
        (dialog.tagMap[XBL] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap[PC] as Button).setTextColor(Color.BLACK)
        (dialog.tagMap[PSN] as Button).setTextColor(Color.WHITE)
        (dialog.tagMap["message"] as TextView).text = ENTER_PSN
        platform = "psn"
    }
}